package problem2;

import java.util.Objects;

/**
 * The {@code Author} class represents an individual who has written a book.
 * <p>
 * Each author has a {@link Name}, which includes their first and last names.
 * This class implements the {@link Creator} interface so that it can serve
 * as the creator of a {@link Book} in the library catalog.
 *
 */
public class Author implements Creator {

    /** The name of the author, including first and last names. */
    private final Name name;

    /**
     * Constructs a new {@code Author} with the specified name.
     *
     * @param name the {@link Name} of the author
     */
    public Author(Name name) {
        if  (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.name = name;
    }

    /**
     * Returns the full name of the author (first name + last name).
     *
     * @return the author's full name as a {@code String}
     */
    @Override
    public String getName() {
        return name.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getName(), author.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
