package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
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

    public void addProduct(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isPresent()) {
            productBasket.addProduct(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> productsInBasket = productBasket.getProducts();

        List<BasketItem> basketItems = productsInBasket.entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElse(null), entry.getValue()))
                .collect(Collectors.toList());

        double total = basketItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return new UserBasket(basketItems, total);
    }
}