package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    Author author;


    @BeforeEach
    void setUp() {

        author = new Author(new Name("John", "Doe"));
    }

    @Test
    void getName() {
        assertEquals("John Doe", author.getName());
    }
}