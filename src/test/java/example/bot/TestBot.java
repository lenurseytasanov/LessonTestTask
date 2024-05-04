package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация бота для теста. Сохраняет отправляемые сообщения.
 */
public class TestBot implements Bot {

    /**
     * Возвращает сохраненные сообщения
     * @return Список сообщений
     */
    public List<String> getMessages() {
        return messages;
    }

    private final List<String> messages;

    /**
     * Создает бота с пустым списком сообщений
     */
    public TestBot() {
        messages = new ArrayList<>();
    }

    /**
     * Записывает сообщение в начало списка
     * @param chatId идентификатор чата
     * @param message текст сообщения
     */
    @Override
    public void sendMessage(Long chatId, String message) {
        messages.addFirst(message);
    }
}
