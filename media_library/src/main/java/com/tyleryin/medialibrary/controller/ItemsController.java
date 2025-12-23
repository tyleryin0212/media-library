package com.tyleryin.medialibrary.controller;

import com.tyleryin.medialibrary.DTO.CreateItemRequest;
import com.tyleryin.medialibrary.DTO.ItemResponse;
import com.tyleryin.medialibrary.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


/**
 * accepts JSON and returns JSON
 */
@RestController
@RequestMapping("/items")
public class ItemsController {

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<String> listItems() {
        return itemService.listItemTitles();
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(@RequestBody CreateItemRequest req) {
        ItemResponse created =  itemService.createItem(req);
        return ResponseEntity
                .created(URI.create("/items/" + created.getId()))
                .body(created);
    }

}
