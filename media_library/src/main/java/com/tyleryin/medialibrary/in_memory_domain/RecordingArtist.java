package com.tyleryin.medialibrary.in_memory_domain;

import java.util.Objects;

/**
 * The {@code RecordingArtist} class represents an individual who performs or produces music.
 * <p>
 * Each recording artist has a {@link Name}, which includes their first and last names.
 * This class implements the {@link Creator} interface so that a recording artist can serve
 * as the creator of a {@link Music} item in the library catalog.
 * <p>
 * A {@code RecordingArtist} may also be a member of a {@link Band}.
 *
 */
public class RecordingArtist implements Creator {

    /** The name of the recording artist, including first and last names. */
    private final Name name;

    /**
     * Constructs a new {@code RecordingArtist} with the specified name.
     *
     * @param name the {@link Name} of the recording artist
     */
    public RecordingArtist(Name name) {
        if  (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.name = name;
    }

    /**
     * Returns the full name of this recording artist (first name + last name).
     *
     * @return the artist's full name as a {@code String}
     */
    @Override
    public String getName() {
        return name.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecordingArtist that = (RecordingArtist) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
