package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNonExistentProductThrowsException() {
        UUID nonExistentProductId = UUID.randomUUID();

        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            basketService.addProduct(nonExistentProductId);
        });

        assertEquals("Product not found", exception.getMessage());

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    public void testAddExistingProductCallsAddProductOnBasket() {
        UUID existingProductId = UUID.randomUUID();

        Product product = new Product(existingProductId, "Milk", "Fresh cow milk", 50);

        when(storageService.getProductById(existingProductId)).thenReturn(Optional.of(product));

        basketService.addProduct(existingProductId);

        verify(productBasket).addProduct(product);
    }

    @Test
    public void testGetUserBasketReturnsEmptyWhenBasketIsEmpty() {
        when(productBasket.getProducts()).thenReturn(List.of()); // Добавлено

        Basket basket = basketService.getUserBasket();

        assertTrue(basket.isEmpty(), "Expected empty basket");
        verify(productBasket).getProducts();
    }

    @Test
    public void testGetUserBasketReturnsProductsWhenBasketIsNotEmpty() {
        Product product1 = new Product(UUID.randomUUID(), "Milk", "Fresh cow milk", 50);
        Product product2 = new Product(UUID.randomUUID(), "Bread", "Fresh bread", 30);

        when(productBasket.getProducts()).thenReturn(List.of(product1, product2));

        Basket basket = basketService.getUserBasket();

        assertFalse(basket.isEmpty(), "Expected non-empty basket");
        assertEquals(2, basket.size());
    }
}
