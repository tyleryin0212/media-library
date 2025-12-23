package problem2;

public interface ItemPredicate {
    boolean matchesBook(Book b);
    boolean matchesMusic(Music m);
}
