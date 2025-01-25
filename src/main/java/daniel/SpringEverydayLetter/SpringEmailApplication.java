package daniel.SpringEverydayLetter;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringEmailApplication {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private ChatGPTService ChatGPTService;

    private int scheduleCount = 0;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailApplication.class, args);
    }

    @Scheduled(cron = "0 0 9 * * ?")
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        scheduleCount++;
        try {
            String toEmail = senderService.getToEmail();
            ChatResponse response = ChatGPTService.generateResponse("Tell me a backend java developer tip", "gpt-4o-mini", 0.9);
            String emailContent = response.getResult().getOutput().getContent();
            senderService.sendSimpleEmail(toEmail, "Backend Developer Tip: " + scheduleCount, emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}