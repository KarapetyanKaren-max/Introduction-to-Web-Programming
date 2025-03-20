package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        return storageService.getSearchableItems().stream()
                .filter(item -> item.getName().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}