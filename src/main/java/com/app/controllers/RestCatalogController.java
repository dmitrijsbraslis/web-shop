package com.app.controllers;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCatalogController {

    @Autowired
    private CatalogService catalogService;

    @CrossOrigin
    @GetMapping("/getItems")
    public List<CatalogItem> getItems() {
        return catalogService.getCatalogItems();
    }

    @CrossOrigin
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return catalogService.getCategories();
    }

    @CrossOrigin
    @GetMapping("/subcategories/{id}")
    public List<Category> getSubCategories(@PathVariable(value = "id") int id) {
        return catalogService.getSubCategories(id);
    }
}
