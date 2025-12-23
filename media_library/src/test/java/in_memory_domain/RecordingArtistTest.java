package in_memory_domain;

import com.tyleryin.medialibrary.in_memory_domain.Name;
import com.tyleryin.medialibrary.in_memory_domain.RecordingArtist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingArtistTest {
    RecordingArtist recordingArtist;

    @BeforeEach
    void setUp() {
        recordingArtist = new RecordingArtist(new Name("John", "Doe"));
    }

    @Test
    void getName() {
        assertEquals("John Doe", recordingArtist.getName());
    }
}