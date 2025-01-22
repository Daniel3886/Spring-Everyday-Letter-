package daniel.SpringEverydayLetter;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {

    private final OpenAiChatModel chatModel;

    public ChatGPTService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public ChatResponse generateResponse(String prompt, String model, double temperature) {
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel(model)
                .withTemperature(temperature)
                .build();

        return chatModel.call(new Prompt(prompt, options));
    }
}
