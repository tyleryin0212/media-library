package com.tyleryin.medialibrary.service;


import com.tyleryin.medialibrary.DTO.CreateItemRequest;
import com.tyleryin.medialibrary.DTO.ItemResponse;
import com.tyleryin.medialibrary.in_memory_domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * decides how to create items, translates DTO to domain
 */
@Service
public class InMemoryItemService implements ItemService {
    private final Catalog catalog;

    public InMemoryItemService(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public List<String> listItemTitles() {
        //return titles of all items for now
        return catalog.getAllItems().stream()
                .map(Item::getTitle)
                .toList();
    }

    @Override
    public ItemResponse createItem(CreateItemRequest req) {
        if (req == null) throw new IllegalArgumentException("Request cannot be null");
        if (req.getType() == null) throw new IllegalArgumentException("type cannot be null");
        if (req.getTitle() == null) throw new IllegalArgumentException("title cannot be null");
        if (req.getFirstName() == null) throw new IllegalArgumentException("firstName cannot be null");
        if (req.getLastName() == null) throw new IllegalArgumentException("lastName cannot be null");

        // Build the domain Name from first/last
        Name name = new Name(req.getFirstName(), req.getLastName());

        Item item;
        String type = req.getType();

        if (type.equalsIgnoreCase("BOOK")) {
            Author author = new Author(name);
            item = new Book(author, req.getTitle(), req.getYear());
        } else if (type.equalsIgnoreCase("MUSIC")) {
            RecordingArtist artist = new RecordingArtist(name);
            item = new Music(artist, req.getTitle(), req.getYear());
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }

        catalog.addItem(item);

        // Map domain -> response DTO
        ItemResponse res = new ItemResponse();
        res.setId(item.getId());
        res.setType(item instanceof Book ? "BOOK" : "MUSIC");
        res.setTitle(item.getTitle());
        res.setYear(item.getYear());

        // Return first/last from the request (safe + consistent with your domain)
        res.setFirstName(req.getFirstName());
        res.setLastName(req.getLastName());

        return res;
    }
}
