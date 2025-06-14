package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.UserBasket;
import org.skypro.skyshop.model.search.BasketItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isPresent()) {
            productBasket.addProduct(id);
        } else {
            throw new NoSuchProductException("Продукт с ID " + id + " не найден");
        }
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> items = productBasket.getItems();
        List<BasketItem> basketItems = items.entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElse(null), entry.getValue()))
                .collect(Collectors.toList());

        double total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}