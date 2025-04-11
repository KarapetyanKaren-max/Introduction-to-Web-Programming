package org.skypro.skyshop.model.basket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    public void addProduct(UUID id) {
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}