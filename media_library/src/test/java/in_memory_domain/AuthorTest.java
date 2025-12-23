package in_memory_domain;

import com.tyleryin.medialibrary.in_memory_domain.Author;
import com.tyleryin.medialibrary.in_memory_domain.Name;
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