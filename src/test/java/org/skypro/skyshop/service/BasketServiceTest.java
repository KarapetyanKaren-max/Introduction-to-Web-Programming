package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.UserBasket;

import java.util.List;
import java.util.Map;
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
    @DisplayName("Выбрасывает исключение если добавляется не сушествуюший продукт")
    public void testAddNonExistentProductThrowsException() {
        UUID nonExistentProductId = UUID.randomUUID();

        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(nonExistentProductId);
        });

        assertEquals("Product not found", exception.getMessage());

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    public void testAddExistingProductCallsAddProductOnBasket() {

        Product product = new Product("Milk", "Fresh cow milk", 50);

        when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));

        basketService.addProductToBasket(product.getId());

        verify(productBasket).addProduct(product.getId());
    }

    @Test
    public void testGetUserBasketReturnsEmptyWhenBasketIsEmpty() {
        when(productBasket.getItems()).thenReturn(Map.of());

        UserBasket basket = basketService.getUserBasket();

        assertNotNull(basket);
        assertTrue(basket.getItems().isEmpty());
        assertEquals(0, basket.getTotal());

        verify(productBasket,only()).getItems();
    }

    @Test
    public void testGetUserBasketReturnsProductsWhenBasketIsNotEmpty() {
        Product product1 = new Product( "Milk", "Fresh cow milk", 50);
        Product product2 = new Product( "Bread", "Fresh bread", 30);

        when(productBasket.getItems()).thenReturn(Map.of(product1.getId(),1, product2.getId(),1));
        when(storageService.getProductById(product1.getId())).thenReturn(Optional.of(product1));
        when(storageService.getProductById(product2.getId())).thenReturn(Optional.of(product2));
        UserBasket basket = basketService.getUserBasket();

        assertNotNull(basket);
        assertEquals(2, basket.getItems().size());
        assertEquals(product1.getPrice()+product2.getPrice(), basket.getTotal());

        verify(productBasket,only()).getItems();
        verify(storageService,times(2)).getProducts();

    }
}
