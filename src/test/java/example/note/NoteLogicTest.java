package example.note;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteLogicTest {

    private NoteLogic noteLogic;

    @Before
    public void init() {
        noteLogic = new NoteLogic();
    }

    /**
     * Тест на добавление записи
     */
    @Test
    public void addMessageTest() {
        String message = "message";

        String added = noteLogic.handleMessage("/add " + message);
        assertEquals("Note added!", added);

        String notes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: " + message, notes);
    }

    /**
     * Тест на редактирование записи
     */
    @Test
    public void editMessageTest() {
        String message = "message";
        String new_message = "edited";
        noteLogic.handleMessage("/add " + message);

        String edit_message = noteLogic.handleMessage("/edit 1" + new_message);
        assertEquals("Note edited!", edit_message);

        String edited_notes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: " + edit_message, edited_notes);
    }

    /**
     * Тест на удаление записи
     */
    @Test
    public void deleteMessageTest() {
        String message = "message";
        noteLogic.handleMessage("/add " + message);

        String delete_message = noteLogic.handleMessage("/del 1");
        assertEquals("Note deleted!", delete_message);

        String edited_notes = noteLogic.handleMessage("/notes");
        assertEquals("Your notes: ", edited_notes);
    }

    /**
     * Тест на неизвестную команду
     */
    @Test
    public void unknownCommandMessageTest() {

        String message = noteLogic.handleMessage("/fffff");

        assertEquals("Unknown command", message);
    }
}
