package com.tyleryin.medialibrary.in_memory_domain;

public interface ItemPredicate {
    boolean matchesBook(Book b);
    boolean matchesMusic(Music m);
}
