package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MusicTest {

    private RecordingArtist chris;
    private Music soloSong;

    private Band coldplay;
    private RecordingArtist jonny;
    private Music bandSong;

    @BeforeEach
    public void setUp() {
        // Solo artist
        chris = new RecordingArtist(new Name("Chris", "Martin"));
        soloSong = new Music(chris, "Clocks", 2002);

        // Band with members

        jonny = new RecordingArtist(new Name("Jonny", "Buckland"));
        List<RecordingArtist> artists = new ArrayList<>();
        artists.add(chris);
        artists.add(jonny);
        coldplay = new Band("Coldplay", artists);

        bandSong = new Music(coldplay, "Yellow", 2000);
    }

    @Test
    public void testSoloArtistConstructorAndGetters() {
        assertEquals("Clocks", soloSong.getTitle());
        assertEquals(2002, soloSong.getYear());
        assertEquals(chris, soloSong.getCreator());
    }

    @Test
    public void testBandConstructorAndGetters() {
        assertEquals("Yellow", bandSong.getTitle());
        assertEquals(2000, bandSong.getYear());
        assertEquals(coldplay, bandSong.getCreator());

        // Check that band members include both artists
        assertTrue(coldplay.getMembers().contains(chris));
        assertTrue(coldplay.getMembers().contains(jonny));
    }
}