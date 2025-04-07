package org.skypro.skyshop.model.article;


import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return "title";
    }

    @Override
    public String getContentType() {
        return "Article";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}