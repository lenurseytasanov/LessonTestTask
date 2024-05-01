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
     * Тест на команду /test
     */
    @Test
    public void testCommandTest() {
        // Arrange
        String command = "/test";
        String expected = "Вычислите степень: 10^2";

        // Act
        botLogic.processCommand(user, command);
        String message = bot.getMessages().getFirst();

        // Assert
        assertEquals(State.TEST, user.getState());
        assertEquals(expected, message);
    }

    /**
     * Тест на команду /notify
     */
    @Test
    public void notifyCommandTest() {
        // Arrange
        String command = "/notify";
        String expected = "Введите текст напоминания";

        // Act
        botLogic.processCommand(user, command);
        String message = bot.getMessages().getFirst();

        // Assert
        assertEquals(State.SET_NOTIFY_TEXT, user.getState());
        assertEquals(expected, message);
    }

    /**
     * Тест на команду /repeat с пустым списком не решенных вопросов
     */
    @Test
    public void repeatCommandEmptyListTest() {
        // Arrange
        String command = "/repeat";
        String expected = "Нет вопросов для повторения";

        // Act
        botLogic.processCommand(user, command);
        String message = bot.getMessages().getFirst();

        // Assert
        assertEquals(expected, message);
    }

    /**
     * Тест на команду /repeat с одним не решенным вопросом
     */
    @Test
    public void repeatCommandTest() {
        // Arrange
        String command = "/repeat";
        String questionText = "question";
        Question question = new Question(questionText, "answer");
        user.addWrongAnswerQuestion(question);

        // Act
        botLogic.processCommand(user, command);
        String message = bot.getMessages().getFirst();

        // Assert
        assertEquals(State.REPEAT, user.getState());
        assertEquals(questionText, message);
    }
}
