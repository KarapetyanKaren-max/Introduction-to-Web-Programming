package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productStorage = new HashMap<>();
    private final Map<UUID, Article> articleStorage = new HashMap<>();

    public StorageService() {
        populateData();
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    public void populateData() {
        productStorage.put(UUID.randomUUID(), new Product("Молоко", "Свежая коровье молоко", 50));
        productStorage.put(UUID.randomUUID(), new Product("Хрен", "Растение с острым вкусом", 15));
        productStorage.put(UUID.randomUUID(), new Product("Масло", "Сливочное натуральный продукт", 250));
        productStorage.put(UUID.randomUUID(), new Product("Торт", "С нежным вкусом", 565));

        Article article1 = new Article("Статья 1", "Очень полезное свежее коровье молоко");
        Article article2 = new Article("Статья 2", "для чего же нужен Хрен");
        Article article3 = new Article("Статья 3", "Откуда берется масло");
        Article article4 = new Article("Статья 4", "Вреден ли торт здоровью");

        articleStorage.put(article1.getId(), article1);
        articleStorage.put(article2.getId(), article2);
        articleStorage.put(article3.getId(), article3);
        articleStorage.put(article4.getId(), article4);
    }

    public List<Searchable> getSearchableItems() {
        List<Searchable> searchableItems = new ArrayList<>();
        searchableItems.addAll(productStorage.values());
        searchableItems.addAll(articleStorage.values());
        return searchableItems;
    }

    public Map<UUID, Product> getProducts() {
        return productStorage;
    }

    public Map<UUID, Article> getArticles() {
        return articleStorage;
    }
}