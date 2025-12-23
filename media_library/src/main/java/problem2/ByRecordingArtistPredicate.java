package problem2;

import java.util.Objects;

/**
 * Matches only Music items created by the given artist:
 * Is this music created by this artist or their band?
 */

public final class ByRecordingArtistPredicate implements ItemPredicate {
    private final RecordingArtist artist;

    public ByRecordingArtistPredicate(RecordingArtist artist) {
        this.artist = Objects.requireNonNull(artist, "RecordingArtist cannot be null");
    }

    @Override
    public boolean matchesBook(Book b) {
        return false;
    }

    @Override
    public boolean matchesMusic(Music m) {
        Creator creator = m.getCreator();

        if (creator instanceof RecordingArtist ra) {
            return ra.equals(artist);
        }
        if (creator instanceof Band band) {
            return band.contains(artist);
        }
        return false;
    }
}
