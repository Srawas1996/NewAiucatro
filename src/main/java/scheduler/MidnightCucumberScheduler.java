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

    // ===== EMAIL CONFIG =====
    private static final String TO_EMAIL =
            "salim96tr@gmail.com,Mohamad.alzein@cts.ae,edward.layoun@cts.ae,lama.ghusn@cts.ae,louana.ibrahim@cts.ae";
    private static final String FROM_EMAIL = "salim96tr@gmail.com";
    private static final String PASSWORD = "dghkbhsbtslmhvns"; // move to secrets later
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // ===== PATHS =====
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String REPORT_PATH =
            PROJECT_DIR + "target/cucumber-html-report/overview-features.html";
    private static final String SCREENSHOTS_DIR =
            PROJECT_DIR + "src/main/files/screenshots";

    public static void main(String[] args) {
        System.out.println("📧 Sending Cucumber Report Email...");
        sendEmailReport();
    }

    private static void sendEmailReport() {

        File reportFile = new File(REPORT_PATH);
        File screenshotsFolder = new File(SCREENSHOTS_DIR);

        if (!reportFile.exists()) {
            System.err.println("❌ Report not found: " + reportFile.getAbsolutePath());
            return;
        }

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
            textPart.setText("""
                    Hello Team,

                    Please find the attached Cucumber automation report.

                    Regards,
                    QA Automation
                    """);
            multipart.addBodyPart(textPart);

            // Report
            attachFile(multipart, reportFile);

            // Screenshots
            if (screenshotsFolder.exists()) {
                File[] screenshots = screenshotsFolder.listFiles(
                        (dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));
                if (screenshots != null) {
                    for (File img : screenshots) {
                        attachFile(multipart, img);
                    }
                }
            }

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("✅ Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void attachFile(Multipart multipart, File file) throws MessagingException {
        MimeBodyPart part = new MimeBodyPart();
        part.setDataHandler(new DataHandler(new FileDataSource(file)));
        part.setFileName(file.getName());
        multipart.addBodyPart(part);
    }
}