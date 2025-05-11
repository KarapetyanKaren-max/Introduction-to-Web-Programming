package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

import java.util.UUID;

public class Product {
    // Поля класса
    private final UUID id; // Уникальный идентификатор продукта
    private final String name; // Имя продукта
    private String description; // Описание продукта
    private double price; // Цена продукта

    // Конструктор класса
    public Product(String name, String description, double price) {
        this.id = UUID.randomUUID(); // Генерируем уникальный ID для каждого продукта
        this.name = name; // Устанавливаем имя продукта
        this.description = description; // Устанавливаем описание продукта
        this.price = price; // Устанавливаем цену продукта
    }

    // Метод для получения уникального идентификатора продукта
    public UUID getId() {
        return id;
    }

    // Метод для получения имени продукта
    public String getName() {
        return name;
    }

    // Метод для получения описания продукта
    public String getDescription() {
        return description;
    }

    // Метод для установки описания продукта (если нужно изменить)
    public void setDescription(String description) {
        this.description = description;
    }

    // Метод для получения цены продукта
    public double getPrice() {
        return price;
    }

    // Метод для установки цены продукта (если нужно изменить)
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