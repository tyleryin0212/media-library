package com.tyleryin.medialibrary.service;


import com.tyleryin.medialibrary.DTO.CreateItemRequest;
import com.tyleryin.medialibrary.DTO.ItemResponse;
import com.tyleryin.medialibrary.DTO.ItemType;
import com.tyleryin.medialibrary.DTO.UpdateItemRequest;
import com.tyleryin.medialibrary.in_memory_domain.Author;
import com.tyleryin.medialibrary.in_memory_domain.Book;
import com.tyleryin.medialibrary.in_memory_domain.Item;
import com.tyleryin.medialibrary.in_memory_domain.Music;
import com.tyleryin.medialibrary.in_memory_domain.Name;
import com.tyleryin.medialibrary.in_memory_domain.RecordingArtist;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of {@link ItemService} used for local development/testing.
 * Translates DTOs to domain objects and back.
 */
@Service
public class InMemoryItemService implements ItemService {

    private final Map<UUID, Item> items = new ConcurrentHashMap<>();

    @Override
    public ItemResponse createItem(CreateItemRequest request) {
        Item item = toDomain(request);
        items.put(item.getId(), item);
        return toResponse(item);
    }

    @Override
    public Optional<ItemResponse> getById(UUID id) {
        return Optional.ofNullable(items.get(id)).map(this::toResponse);
    }

    @Override
    public List<ItemResponse> getAll() {
        return items.values().stream().map(this::toResponse).toList();
    }

    @Override
    public boolean deleteById(UUID id) {
        return items.remove(id) != null;
    }

    @Override
    public Optional<ItemResponse> updateById(UUID id, UpdateItemRequest patch) {
        Item updated = items.computeIfPresent(id, (k, oldItem) -> rebuildWithPatch(oldItem, patch));
        return Optional.ofNullable(updated).map(this::toResponse);
    }

    private Item toDomain(CreateItemRequest request) {
        Name name = new Name(request.getFirstName(), request.getLastName());
        return switch (request.getType()) {
            case BOOK -> new Book(new Author(name), request.getTitle(), request.getYear());
            case MUSIC -> new Music(new RecordingArtist(name), request.getTitle(), request.getYear());
        };
    }

    private Item rebuildWithPatch(Item oldItem, UpdateItemRequest patch) {
        String newTitle = (patch.getTitle() != null) ? patch.getTitle() : oldItem.getTitle();
        int newYear = (patch.getYear() != null) ? patch.getYear() : oldItem.getYear();

        if (oldItem instanceof Book book) {
            return new Book(oldItem.getId(), book.getAuthor(), newTitle, newYear);
        }
        return new Music(oldItem.getId(), oldItem.getCreator(), newTitle, newYear);
    }

    private ItemResponse toResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setTitle(item.getTitle());
        response.setYear(item.getYear());

        if (item instanceof Book book) {
            response.setType(ItemType.BOOK);
            response.setFirstName(book.getAuthor().getName());
            response.setLastName("");
        } else if (item instanceof Music music) {
            response.setType(ItemType.MUSIC);
            response.setFirstName(music.getCreator().getName());
            response.setLastName("");
        }

        return response;
    }
}
