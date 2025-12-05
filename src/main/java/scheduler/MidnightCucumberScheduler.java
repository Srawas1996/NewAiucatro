package scheduler;


import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MidnightCucumberScheduler {

    // --- Configuration for Email Reporting ---
    private static final String TO_EMAIL = "salim96tr@gmail.com,Mohamad.alzein@cts.ae,edward.layoun@cts.ae"; // CHANGE THIS
    private static final String FROM_EMAIL = "Salim96tr@gmail.com"; // CHANGE THIS
    private static final String PASSWORD = "iofc hqdo tntq orbl"; // CHANGE THIS (Use App Password for Gmail/Outlook)
    private static final String SMTP_HOST = "smtp.gmail.com"; // CHANGE THIS (e.g., "smtp.office365.com" for Outlook)
    private static final String SMTP_PORT = "587"; // CHANGE THIS (usually 587 or 465)
    private static final String REPORT_PATH = "target/reports/report.html"; // VERIFY THIS PATH

    public static void main(String[] args) {
        System.out.println("Scheduler started. Waiting for midnight...");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Use the actual midnight delay logic for production
//        long initialDelay = getDelayUntilMidnight();
//        long period = TimeUnit.DAYS.toSeconds(1);

        // For testing, you can use the commented-out short delay:
         long initialDelay = 5;  // 5 seconds
         long period = 10;

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("==== Midnight cucumber test started ====");

                ProcessBuilder pb = new ProcessBuilder(
                        "C:/Program Files/apache-maven-3.9.11-bin/apache-maven-3.9.11/bin/mvn.cmd",
                        "test"
                );

                pb.inheritIO();
                Process process = pb.start();
                int exitCode = process.waitFor();

                System.out.println("==== Midnight cucumber test finished with exit code: " + exitCode + " ====");

                // --- NEW: Send Email Report ---
                sendEmailReport();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, period, TimeUnit.SECONDS);
    }

    private static long getDelayUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        // The original code was slightly off. This calculates the duration until the next midnight.
        LocalDateTime nextMidnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(now, nextMidnight).getSeconds();
    }

    private static void sendEmailReport() {
        // 1. Check if the report file exists
        File reportFile = new File(REPORT_PATH);
        if (!reportFile.exists()) {
            System.err.println("Report file not found at: " + REPORT_PATH + ". Skipping email.");
            return;
        }

        // 2. Setup mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            // 4. Create a default MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO_EMAIL));
            message.setSubject("Automation Test Report - " + LocalDateTime.now().toLocalDate());

            // 5. Create the message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached automation test report for the midnight run.");

            // 6. Create the attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(REPORT_PATH);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(reportFile.getName());

            // 7. Combine parts
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // 8. Set the combined message
            message.setContent(multipart);

            // 9. Send the message
            Transport.send(message);

            System.out.println("Email report sent successfully to " + TO_EMAIL);

        } catch (MessagingException e) {
            System.err.println("Error sending email report: " + e.getMessage());
        }
    }
}
