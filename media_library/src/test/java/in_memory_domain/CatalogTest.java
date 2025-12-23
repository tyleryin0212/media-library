package in_memory_domain;

import com.tyleryin.medialibrary.in_memory_domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogTest {

    private Catalog catalog;
    private Author orwell;
    private Book book1;
    private RecordingArtist chris;
    private Band coldplay;
    private Music song1;
    private Music song2;

    @BeforeEach
    public void setUp() {
        catalog = new Catalog();

        // Create an author and book
        orwell = new Author(new Name("George", "Orwell"));
        book1 = new Book(orwell, "1984", 1949);

        // Create a recording artist and music
        chris = new RecordingArtist(new Name("Chris", "Martin"));
        List<RecordingArtist> artists = new ArrayList<>();
        artists.add(chris);
        coldplay = new Band("Coldplay", artists);


        song1 = new Music(chris, "Clocks", 2002);
        song2 = new Music(coldplay, "Yellow", 2000);

        // Add items to catalog
        catalog.addItem(book1);
        catalog.addItem(song1);
        catalog.addItem(song2);
    }

    @Test
    public void testAddItem() {
        Book newBook = new Book(orwell, "Animal Farm", 1945);
        catalog.addItem(newBook);

        List<Item> results = catalog.search("Animal");
        assertEquals(1, results.size());
        assertTrue(results.contains(newBook));
    }

    @Test
    public void testRemoveItem() {
        boolean removed = catalog.removeItem(book1);
        assertTrue(removed);

        List<Item> results = catalog.search("1984");
        assertEquals(0, results.size());

        // Try removing again → should return false
        assertFalse(catalog.removeItem(book1));
    }

    @Test
    public void testSearchByTitle() {
        List<Item> results = catalog.search("clocks");
        assertEquals(1, results.size());
        assertTrue(results.contains(song1));

        results = catalog.search("Yellow");
        assertEquals(1, results.size());
        assertTrue(results.contains(song2));

        results = catalog.search("Nonexistent");
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByAuthor() {
        List<Item> results = catalog.search(orwell);
        assertEquals(1, results.size());
        assertTrue(results.contains(book1));

        Author fakeAuthor = new Author(new Name("Fake", "Author"));
        results = catalog.search(fakeAuthor);
        assertEquals(0, results.size());
    }

    @Test
    public void testSearchByRecordingArtist() {
        // Artist as solo creator
        List<Item> results = catalog.search(chris);
        assertEquals(2, results.size()); // song1 (solo) + song2 (band)
        assertTrue(results.contains(song1));
        assertTrue(results.contains(song2));

        RecordingArtist otherArtist = new RecordingArtist(new Name("Someone", "Else"));
        results = catalog.search(otherArtist);
        assertEquals(0, results.size());
    }
}