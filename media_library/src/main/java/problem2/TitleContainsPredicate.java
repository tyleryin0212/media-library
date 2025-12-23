package problem2;

import java.util.Locale;
import java.util.Objects;

public final class TitleContainsPredicate implements ItemPredicate {
    private final String needle;

    public TitleContainsPredicate(String keyword) {
        Objects.requireNonNull(keyword, "Keyword cannot be null");
        if (keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword cannot be blank");
        }
        this.needle = keyword.toLowerCase(Locale.ROOT);
    }

    @Override
    public boolean matchesBook(Book b) {
        return titleMatches(b);
    }

    @Override
    public boolean matchesMusic(Music m) {
        return titleMatches(m);
    }

    private boolean titleMatches(Item item) {
        return item.getTitle().toLowerCase(Locale.ROOT).contains(needle);
    }
}
