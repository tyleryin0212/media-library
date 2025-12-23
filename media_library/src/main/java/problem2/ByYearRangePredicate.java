package problem2;

public class ByYearRangePredicate implements ItemPredicate {
    private final Integer fromYear; // inclusive, nullable
    private final Integer toYear;   // inclusive, nullable

    public ByYearRangePredicate(Integer fromYear, Integer toYear) {
        if (fromYear != null && fromYear < 0) {
            throw new IllegalArgumentException("fromYear cannot be negative");
        }
        if (toYear != null && toYear < 0) {
            throw new IllegalArgumentException("toYear cannot be negative");
        }
        if (fromYear != null && toYear != null && fromYear > toYear) {
            throw new IllegalArgumentException("fromYear cannot be greater than toYear");
        }
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    @Override
    public boolean matchesBook(Book b) {
        return matchesYear(b.getYear());
    }

    @Override
    public boolean matchesMusic(Music m) {
        return matchesYear(m.getYear());
    }

    private boolean matchesYear(int year) {
        if (fromYear != null && year < fromYear) return false;
        if (toYear != null && year > toYear) return false;
        return true;
    }
}
