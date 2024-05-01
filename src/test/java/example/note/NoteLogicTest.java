package example.note;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        String add_command = "/add " + message;
        String notes_command = "/notes";

        String added = noteLogic.handleMessage(add_command);
        String notes = noteLogic.handleMessage(notes_command);

        assertEquals("Note added!", added);
        assertTrue(notes.startsWith("Your notes:"));
        assertTrue(notes.contains(message));
    }

    /**
     * Тест на редактирование записи
     */
    @Test
    public void editMessageTest() {
        String message = "message";
        String new_message = "edited";
        String add_command = "/add " + message;
        String edit_command = "/edit 1" + new_message;
        String notes_command = "/notes";

        noteLogic.handleMessage(add_command);
        String edit_message = noteLogic.handleMessage(edit_command);
        String edited_notes = noteLogic.handleMessage(notes_command);

        assertEquals("Note edited!", edit_message);
        assertFalse(edited_notes.contains(message));
        assertTrue(edited_notes.contains(new_message));
    }

    /**
     * Тест на удаление записи
     */
    @Test
    public void deleteMessageTest() {
        String message = "message";
        String add_command = "/add " + message;
        String delete_command = "/del 1";
        String notes_command = "/notes";

        noteLogic.handleMessage(add_command);
        String delete_message = noteLogic.handleMessage(delete_command);
        String edited_notes = noteLogic.handleMessage(notes_command);

        assertEquals("Note deleted!", delete_message);
        assertFalse(edited_notes.contains(message));
    }

    /**
     * Тест на неизвестную команду
     */
    @Test
    public void unknownCommandMessageTest() {
        String command = "/fffff";

        String message = noteLogic.handleMessage(command);

        assertEquals("Unknown command", message);
    }
}
