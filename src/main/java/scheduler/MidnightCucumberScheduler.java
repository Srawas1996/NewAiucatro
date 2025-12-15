package scheduler;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.time.LocalDateTime;

public class MidnightCucumberScheduler {

    // --- Email Configuration ---
    private static final String TO_EMAIL = "salim96tr@gmail.com,Mohamad.alzein@cts.ae,edward.layoun@cts.ae,lama.ghusn@cts.ae,louana.ibrahim@cts.ae";
    private static final String FROM_EMAIL = "salim96tr@gmail.com";
    private static final String PASSWORD = "dghkbhsbtslmhvns"; // Gmail App Password
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // --- Paths ---
    private static final String REPORT_PATH = "target/cucumber-html-report/overview-features.html";
    private static final String SCREENSHOTS_DIR = "src/main/files/screenshots";

    public static void main(String[] args) {

        // Detect if running in GitHub Actions
        boolean runningInGitHub = System.getenv("GITHUB_ACTIONS") != null;

        if (runningInGitHub) {
            System.out.println("GitHub Actions detected → running immediately (no scheduler)");
            runCucumberAndEmail();
            return;
        }

        // LOCAL MODE → Run at midnight (example: after 1 second for testing)
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        long initialDelay = 1; // seconds

        System.out.println("Local scheduler started, first run after: " + initialDelay + " seconds");

        scheduler.schedule(() -> {
            try {
                runCucumberAndEmail();
            } finally {
                scheduler.shutdown();
            }
        }, initialDelay, TimeUnit.SECONDS);
    }

    private static void runCucumberAndEmail() {
        try {
            System.out.println("==== Running Cucumber Test ====");
            cleanupOldArtifacts();

            // Determine Maven command
            String mvnCommand = getMavenCommand();

            // Run Maven test
            ProcessBuilder pb = new ProcessBuilder(mvnCommand, "clean", "test");
            pb.inheritIO(); // print Maven output
            Process process = pb.start();
            int exitCode = process.waitFor();

            System.out.println("==== Cucumber Finished, exit code: " + exitCode + " ====");

            // Send email report
            sendEmailReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getMavenCommand() {
        String os = System.getProperty("os.name").toLowerCase();
        String mvnCommand;

        if (os.contains("win")) {
            // Windows: try system mvn.cmd first
            mvnCommand = "mvn.cmd";

            // Fallback to full local path if not in PATH
            File localMvn = new File("C:/Program Files/apache-maven-3.9.11-bin/apache-maven-3.9.11/bin/mvn.cmd");
            if (!isCommandAvailable(mvnCommand) && localMvn.exists()) {
                mvnCommand = localMvn.getAbsolutePath();
            }
        } else {
            // Linux/macOS
            mvnCommand = "mvn";
        }

        return mvnCommand;
    }

    // Check if a command exists in PATH
    private static boolean isCommandAvailable(String command) {
        try {
            Process process = new ProcessBuilder(command, "-v").start();
            boolean finished = process.waitFor(5, TimeUnit.SECONDS);
            return finished && process.exitValue() == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static void cleanupOldArtifacts() {
        // Add cleanup logic if needed, e.g., delete old screenshots or reports
        File report = new File(REPORT_PATH);
        if (report.exists()) report.delete();

        File screenshots = new File(SCREENSHOTS_DIR);
        if (screenshots.exists() && screenshots.isDirectory()) {
            File[] files = screenshots.listFiles();
            if (files != null) {
                for (File f : files) f.delete();
            }
        }
    }

    private static void sendEmailReport() {
        File reportFile = new File(REPORT_PATH);
        File screenshotsFolder = new File(SCREENSHOTS_DIR);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject("Automation Test Report - " + LocalDateTime.now().toLocalDate());

            Multipart multipart = new MimeMultipart();

            // Text
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello,\n\nPlease find the attached automation test report.\n\nRegards,\nQA Automation");
            multipart.addBodyPart(textPart);

            // Attach report
            attachFile(multipart, reportFile);

            // Attach screenshots
            if (screenshotsFolder.exists()) {
                File[] screenshots = screenshotsFolder.listFiles((dir, name) ->
                        name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));
                if (screenshots != null) {
                    for (File img : screenshots) attachFile(multipart, img);
                }
            }

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email sent to: " + TO_EMAIL);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Email error: " + e.getMessage());
        }
    }

    private static void attachFile(Multipart multipart, File file) throws MessagingException {
        if (file != null && file.exists()) {
            MimeBodyPart part = new MimeBodyPart();
            part.setDataHandler(new DataHandler(new FileDataSource(file)));
            part.setFileName(file.getName());
            multipart.addBodyPart(part);
        }
    }
}