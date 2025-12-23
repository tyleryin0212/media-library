package com.tyleryin.medialibrary.service;

import com.tyleryin.medialibrary.DTO.CreateItemRequest;
import com.tyleryin.medialibrary.DTO.ItemResponse;

import java.util.List;

public interface ItemService {
    List<String> listItemTitles();
    ItemResponse createItem(CreateItemRequest req);
}
