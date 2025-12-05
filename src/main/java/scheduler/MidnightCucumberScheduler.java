package scheduler;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MidnightCucumberScheduler {

    public static void main(String[] args) {
        System.out.println("Scheduler started. Waiting for midnight...");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

//        long initialDelay = getDelayUntilMidnight();
//        long period = TimeUnit.DAYS.toSeconds(1);
        long initialDelay = 5;  // 5 seconds
        long period = 10;

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("==== Midnight cucumber test started ====");

                ProcessBuilder pb = new ProcessBuilder(
                        "C:/Program Files/apache-maven-3.9.11-bin/apache-maven-3.9.11/bin/mvn.cmd",
                        "test"
                );

                pb.inheritIO();   // So you can see console output

                Process process = pb.start();
                process.waitFor();

                System.out.println("==== Midnight cucumber test finished ====\n");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, initialDelay, period, TimeUnit.SECONDS);
    }

    private static long getDelayUntilMidnight() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Duration.between(now, midnight).getSeconds();
    }
}
