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

        boolean added1 = container.add(item1);
        boolean added2 = container.add(item2);

        assertEquals(2, container.size());
        assertTrue(container.contains(item1));
        assertTrue(container.contains(item2));
        assertTrue(added1);
        assertTrue(added2);
    }

    /**
     * Тест на удаление существующего элемента
     */
    @Test
    public void deleteTest() {
        Item item = new Item(1);

        boolean added = container.add(item);
        boolean removed = container.remove(item);

        assertEquals(0, container.size());
        assertTrue(added);
        assertTrue(removed);
    }

    /**
     * Тест на удаление не существующего элемента
     */
    @Test
    public void deleteAbsentItemTest() {
        Item item = new Item(1);

        boolean removed = container.remove(item);

        assertEquals(0, container.size());
        assertFalse(removed);
    }

    /**
     * Тест на добавление двух одинаковых элементов
     */
    @Test
    public void addExistingItemTest() {
        Item item1 = new Item(1);
        Item item2 = new Item(1);

        boolean added1 = container.add(item1);
        boolean added2 = container.add(item2);

        assertEquals(1, container.size());
        assertTrue(added1);
        assertFalse(added2);
        assertTrue(container.contains(item1));
        assertTrue(container.contains(item2));
    }
}
