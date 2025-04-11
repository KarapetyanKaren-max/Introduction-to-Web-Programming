package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.UserBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final SearchService searchService;
    private final BasketService basketService;

    @Autowired
    public ShopController(SearchService searchService, BasketService basketService) {
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        return searchService.search(pattern);
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductToBasket(id);
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}