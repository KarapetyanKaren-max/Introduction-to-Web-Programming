package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

import java.util.UUID;

public class Product {

    private final UUID id;
    private final String name;
    private String description;
    private double price;


    public Product(String name, String description, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}