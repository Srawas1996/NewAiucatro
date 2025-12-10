package scheduler;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class MidnightCucumberScheduler {

    // --- Email Configuration ---
    private static final String TO_EMAIL = "salim96tr@gmail.com,Mohamad.alzein@cts.ae,edward.layoun@cts.ae,lama.ghusn@cts.ae,louana.ibrahim@cts.ae";
    private static final String FROM_EMAIL = "salim96tr@gmail.com";
    private static final String PASSWORD = "dghkbhsbtslmhvns"; // Gmail App Password
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // --- Paths ---
    private static final String REPORT_PATH = "target/cucumber-html-report/overview-features.html";
    private static final String RAW_LOG_PATH = "canvas_test.log";
    private static final String STEP_BY_STEP_LOG_PATH = "canvas_test_step_by_step.log";
    private static final String SCREENSHOTS_DIR = "src/main/files/screenshots";

    public static void main(String[] args) {

        // Detect if running in GitHub Actions
        boolean runningInGitHub = System.getenv("GITHUB_ACTIONS") != null;

        if (runningInGitHub) {
            System.out.println("GitHub Actions detected → running immediately (no scheduler)");
            runCucumberAndEmail();
            return;
        }

        // LOCAL MODE → Run at midnight
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        long initialDelay = getDelayUntilMidnight();

        System.out.println("Local scheduler started, first run after: " + initialDelay + " seconds");

        scheduler.schedule(() -> {
            try {
                runCucumberAndEmail();
            } finally {
                scheduler.shutdown();
            }
        }, initialDelay, TimeUnit.SECONDS);
    }

    private static long getDelayUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(now, nextMidnight).getSeconds();
    }

    /**
     * CLEAN CODE → One reusable method
     */
    private static void runCucumberAndEmail() {
        try {
            System.out.println("==== Running Cucumber Test ====");
            cleanupOldArtifacts();

            ProcessBuilder pb = new ProcessBuilder("C://Program Files//Apache//Maven//apache-maven-3.9.11//bin/mvn.cmd","clean" ,"test");

            File rawLog = new File(RAW_LOG_PATH);
            pb.redirectOutput(rawLog);
            pb.redirectErrorStream(true);

            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("==== Cucumber Finished, exit code: " + exitCode + " ====");

            sendEmailReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cleanupOldArtifacts() {
        try {
            new File(RAW_LOG_PATH).delete();
            new File(STEP_BY_STEP_LOG_PATH).delete();

            File screenshotsFolder = new File(SCREENSHOTS_DIR);
            if (screenshotsFolder.exists()) {
                File[] screenshots = screenshotsFolder.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".png") ||
                                name.toLowerCase().endsWith(".jpg") ||
                                name.toLowerCase().endsWith(".jpeg")
                );
                if (screenshots != null) {
                    for (File img : screenshots) img.delete();
                }
            }
        } catch (Exception e) {
            System.err.println("Cleanup error: " + e.getMessage());
        }
    }

    private static void sendEmailReport() {
        File reportFile = new File(REPORT_PATH);
        File rawLogFile = new File(RAW_LOG_PATH);
        File stepByStepFile = new File(STEP_BY_STEP_LOG_PATH);
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
            textPart.setText(
                    "Hello,\n\nPlease find the attached automation test report.\n\nRegards,\nQA Automation"
            );
            multipart.addBodyPart(textPart);

            // Attach results
            attachFile(multipart, reportFile);
            attachFile(multipart, rawLogFile);
            attachFile(multipart, stepByStepFile);

            // Attach screenshots
            if (screenshotsFolder.exists()) {
                File[] screenshots = screenshotsFolder.listFiles((dir, name) ->
                        name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg")
                );

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