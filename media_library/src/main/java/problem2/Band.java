package problem2;

import java.util.List;
import java.util.Objects;


/**
 * The {@code Band} class represents a musical group consisting of one or more
 * {@link RecordingArtist} members. A band can serve as the creator of a {@link Music} item
 * in the library catalog.
 * <p>
 * Each band has a unique name and maintains a collection of its members.
 * This class implements the {@link Creator} interface, allowing it to be treated
 * polymorphically alongside {@link RecordingArtist} and {@link Author}.
 *
 */
public class Band implements Creator {

    private final String name;
    private final List<RecordingArtist> members;

    public Band(String name, List<RecordingArtist> members) {
        if (name == null) {
            throw new IllegalArgumentException("band name cannot be null");
        }
        if (members == null) {
            throw new IllegalArgumentException("band members cannot be null");
        }
        this.name = name;
        this.members = members;
    }


    public List<RecordingArtist> getMembers() {
        return members;
    }
    @Override
    public String getName() {
        return name;
    }

    public boolean contains(RecordingArtist artist) {
        return members.contains(artist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Band other)) return false;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
