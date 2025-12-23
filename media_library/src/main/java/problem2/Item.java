package problem2;

import java.util.Objects;
import java.util.UUID;

/**
 * The {@code Item} class represents a single entry in the library's catalog,
 * such as a book or a piece of music. Each item has a creator (author, band, or recording artist),
 * a title, and the year it was published or released.
 * <p>
 * This is an abstract class that serves as a base for specific item types:
 * <ul>
 *   <li>{@link Book} — represents a book written by an {@link Author}</li>
 *   <li>{@link Music} — represents a musical work by a {@link RecordingArtist} or a {@link Band}</li>
 * </ul>
 * <p>
 * Subclasses must call the constructor of {@code Item} using {@code super(creator, title, year)}.
 */
public abstract class Item {

    /** stable identifier for REST/DB usage.*/
    private final UUID id;

    /** The creator of the item — can be an Author, RecordingArtist, or Band. */
    private final Creator creator;

    /** The title of the item. */
    private final String title;

    /** The year the item was published or released. */
    private final int year;

    /**
     * Convenience constructor: generates a new id.
     */
    protected Item(Creator creator, String title, int year) {
        this(UUID.randomUUID(), creator, title, year);
    }

    /**
     * Constructs a new {@code Item} with the specified creator, title, and year.
     *
     * @param creator the creator of the item (e.g., author, recording artist, or band)
     * @param title   the title of the item
     * @param year    the year the item was published or released
     */
    protected Item(UUID id, Creator creator, String title, int year) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (creator == null) {
            throw new IllegalArgumentException("Creator cannot be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        }
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.year = year;
    }

    public UUID getId() {
        return id;
    }

    /**
     * Returns the creator of this item.
     *
     * @return the {@link Creator} of this item
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     * Returns the title of this item.
     *
     * @return the title of the item as a {@code String}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the year this item was published or released.
     *
     * @return the publication or release year as an {@code int}
     */
    public int getYear() {
        return year;
    }

    public abstract boolean matches(ItemPredicate predicate);

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item other)) return false;
        return id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "%s{id=%s, title='%s', year=%d, creator=%s}"
                .formatted(getClass().getSimpleName(), id, title, year, creator);
    }
}
