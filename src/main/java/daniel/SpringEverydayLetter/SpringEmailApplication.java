package daniel.SpringEverydayLetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringEmailApplication {

    @Autowired
    public EmailSenderService senderService;

    @Autowired
    private ChatGPTService ChatGPTService;

    private int scheduleCount = 0;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailApplication.class, args);
    }

//    @Scheduled(cron = "*/10 * * * * ?") // Every 10 seconds
    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        scheduleCount++;
        try {

            // This is the original code that was commented out
//            ChatResponse response = ChatGPTService.generateResponse("Tell me a backend java developer tip", "gpt-4o-mini", 0.9);
//            String emailContent = response.getResult().getOutput().getContent();
//            senderService.sendEmail("example@gmail.com", "Backend Developer Tip: " + scheduleCount, emailContent);

            // This is example code that was added
            senderService.sendEmail("example@gmail.com", "Example Subject: " + scheduleCount, "this is body of th email");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}