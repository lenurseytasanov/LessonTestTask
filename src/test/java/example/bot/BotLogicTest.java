package example.bot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BotLogicTest {

    private TestBot bot;

    private User user;

    private BotLogic botLogic;

    @Before
    public void init() {
        bot = new TestBot();
        user = new User(0L);
        botLogic = new BotLogic(bot);
    }

    /**
     * Тест на команду /test. Проверяется реакция бота на правильный и неправильный ответы.
     */
    @Test
    public void testCommandTest() {
        botLogic.processCommand(user, "/test");
        assertEquals("Вычислите степень: 10^2", bot.getMessages().getFirst());

        botLogic.processCommand(user, "100");
        assertEquals("Правильный ответ!", bot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().getFirst());

        botLogic.processCommand(user, "0");
        assertEquals("Вы ошиблись, верный ответ: 6", bot.getMessages().get(1));
        assertEquals("Тест завершен", bot.getMessages().getFirst());
    }

    /**
     * Тест на команду /notify. Проверяется, что напоминание срабатывает через указанное время.
     * Также проверяется работа при неправильном формате записи числа секунд.
     */
    @Test
    public void notifyCommandTest() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания", bot.getMessages().getFirst());

        String notificationText = "notification";
        botLogic.processCommand(user, notificationText);
        assertEquals("Через сколько секунд напомнить?", bot.getMessages().getFirst());

        botLogic.processCommand(user, "not_a_number");
        assertEquals("Пожалуйста, введите целое число", bot.getMessages().getFirst());

        botLogic.processCommand(user, "1");
        assertEquals("Напоминание установлено", bot.getMessages().getFirst());

        Thread.sleep(1010);
        assertEquals("Сработало напоминание: '%s'".formatted(notificationText), bot.getMessages().getFirst());
    }

    /**
     * Тест на команду /repeat с одним нерешенным вопросом. Проверятся реакция при отсутсвии
     * нерешенных вопросов.
     */
    @Test
    public void repeatCommandTest() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "-1");
        botLogic.processCommand(user, "6");

        botLogic.processCommand(user, "/repeat");
        assertEquals("Вычислите степень: 10^2", bot.getMessages().getFirst());
        botLogic.processCommand(user, "100");

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения", bot.getMessages().getFirst());
    }
}
