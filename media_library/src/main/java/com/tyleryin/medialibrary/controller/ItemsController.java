package com.tyleryin.medialibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @GetMapping
    public List<String> listItems() {
        return List.of("placeholder-1", "placeholder-2");
    }

}
