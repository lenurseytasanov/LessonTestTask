package example.container;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerTest {

    private Container container;

    @Before
    public void init() {
        container = new Container();
    }

    /**
     * Изначально контейнер должен быть пустым
     */
    @Test
    public void emptyContainerTest() {
        assertEquals(0, container.size());
    }

    /**
     * Тест на добавление двух разных элементов
     */
    @Test
    public void addTest() {
        Item item1 = new Item(1);
        Item item2 = new Item(2);

        assertTrue(container.add(item1));
        assertTrue(container.add(item2));
        assertEquals(2, container.size());
        assertTrue(container.contains(item1));
        assertTrue(container.contains(item2));
    }

    /**
     * Тест на удаление существующего элемента
     */
    @Test
    public void deleteTest() {
        Item item = new Item(1);
        container.add(item);

        assertTrue(container.remove(item));
        assertEquals(0, container.size());
    }

    /**
     * Тест на удаление не существующего элемента
     */
    @Test
    public void deleteAbsentItemTest() {
        Item item = new Item(1);

        assertFalse(container.remove(item));
        assertEquals(0, container.size());
    }
}
