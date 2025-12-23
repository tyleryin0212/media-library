package in_memory_domain;

import com.tyleryin.medialibrary.in_memory_domain.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    Name name;

    @BeforeEach
    void setUp() {
        name = new Name("John", "Doe");
    }

    @Test
    void getFirstName() {
        assertEquals("John", name.getFirstName());
    }

    @Test
    void setFirstName() {
        name.setFirstName("David");
        assertEquals("David", name.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Doe", name.getLastName());
    }

    @Test
    void setLastName() {
        name.setLastName("Lee");
        assertEquals("Lee", name.getLastName());
    }

    @Test
    void getFullName() {
        assertEquals("John Doe", name.getFullName());
    }
}