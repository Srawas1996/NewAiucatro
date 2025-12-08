package scheduler;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class MidnightCucumberScheduler {

    // --- Email Configuration ---
    private static final String TO_EMAIL = "salim96tr@gmail.com,Mohamad.alzein@cts.ae,edward.layoun@cts.ae,lama.ghusn@cts.ae,louana.ibrahim@cts.ae";
    private static final String FROM_EMAIL = "salim.alrawas@cts.ae";
    private static final String PASSWORD = "dghkbhsbtslmhvns"; // App Password for Gmail
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String REPORT_PATH = "target/cucumber-html-report/overview-features.html";

    public static void main(String[] args) {
        System.out.println("Scheduler started.");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        long initialDelay = getDelayUntilMidnight();
//        long initialDelay = 10;
        System.out.println("Initial delay until midnight: " + initialDelay + " seconds");

        scheduler.schedule(() -> {
            try {
                System.out.println("==== Midnight Cucumber Test Started ====");

                // Run Cucumber tests via Maven
                ProcessBuilder pb = new ProcessBuilder(
                        "C:/Program Files/apache-maven-3.9.11-bin/apache-maven-3.9.11/bin/mvn.cmd",
                        "test"
                );
                pb.inheritIO();
                Process process = pb.start();
                int exitCode = process.waitFor();

                System.out.println("==== Midnight Cucumber Test Finished with exit code: " + exitCode + " ====");

                // Send email report
                sendEmailReport();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scheduler.shutdown();
            }
        }, initialDelay, TimeUnit.SECONDS);
    }

    // --- Calculate delay until next midnight ---
    private static long getDelayUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(now, nextMidnight).getSeconds();
    }

    // --- Send HTML report via Email ---
    private static void sendEmailReport() {
        File reportFile = new File(REPORT_PATH);
        if (!reportFile.exists()) {
            System.err.println("Report file not found at: " + REPORT_PATH + ". Skipping email.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject("Automation Test Report - " + LocalDateTime.now().toLocalDate());

            // Body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello,\n\nPlease find attached the automation test report for the midnight run.\n\nBest regards,\nQA Automation Team");

            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(new FileDataSource(REPORT_PATH)));
            attachmentPart.setFileName(reportFile.getName());

            // Combine parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email report sent successfully to " + TO_EMAIL);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email report: " + e.getMessage());
        }
    }
}
