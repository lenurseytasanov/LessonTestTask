package example.bot;

import java.util.ArrayList;
import java.util.List;

public class TestBot implements Bot {

    public List<String> getMessages() {
        return messages;
    }

    private final List<String> messages;

    public TestBot() {
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.addLast(message);
    }
}
