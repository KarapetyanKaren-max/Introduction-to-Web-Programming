package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProduct(UUID productId) {
        products.put(productId, products.getOrDefault(productId, 0) + 1);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}