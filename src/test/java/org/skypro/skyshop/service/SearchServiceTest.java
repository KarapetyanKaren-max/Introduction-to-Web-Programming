package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchWhenStorageIsEmpty() {
        String pattern = "milk";
        when(storageService.getSearchableItems()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search(pattern);

        assertTrue(results.isEmpty(), "Expected no results when storage is empty");
        verify(storageService).getSearchableItems();
    }

    @Test
    public void testSearchWhenNoMatchingItems() {
        String pattern = "milk";
        Product product = new Product("Bread", "Fresh bread", 30);
        when(storageService.getSearchableItems()).thenReturn(List.of(product));

        Collection<SearchResult> results = searchService.search(pattern);

        assertTrue(results.isEmpty(), "Expected no results when there are no matching items");
        verify(storageService).getSearchableItems();
    }

    @Test
    public void testSearchWhenMatchingItemExists() {
        String pattern = "milk";
        Product product = new Product("Milk", "Fresh cow milk", 50);

        when(storageService.getSearchableItems()).thenReturn(List.of(product));

        Collection<SearchResult> results = searchService.search(pattern);

        assertEquals(1, results.size(), "Expected one result when there is a matching item");

        SearchResult result = results.iterator().next();

        assertEquals(product.getId().toString(), result.getId());
        assertEquals(product.getName(), result.getName());

        verify(storageService).getSearchableItems();
    }
}
