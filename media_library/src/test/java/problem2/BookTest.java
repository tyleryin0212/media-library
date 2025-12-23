package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Author orwell;
    private Book book;

    @BeforeEach
    public void setUp() {

        orwell = new Author(new Name("George", "Orwell"));
        book = new Book(orwell, "1984", 1949);

    }

    @Test
    public void testBookConstructorAndGetters() {
        assertEquals("1984", book.getTitle());
        assertEquals(1949, book.getYear());
        assertEquals(orwell, book.getCreator());
    }

}