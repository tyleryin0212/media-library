package com.tyleryin.medialibrary.in_memory_domain;

import java.util.Objects;

/**
 * Is this item a book written by this author?
 */
public final class ByAuthorPredicate implements ItemPredicate {
    private final Author author;

    public ByAuthorPredicate(Author author) {
        this.author = Objects.requireNonNull(author, "Author cannot be null");
    }

    @Override
    public boolean matchesBook(Book b) {
        return b.getAuthor().equals(author);
    }

    @Override
    public boolean matchesMusic(Music m) {
        return false;
    }
}
