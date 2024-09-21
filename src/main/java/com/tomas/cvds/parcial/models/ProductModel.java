package com.tomas.cvds.parcial.models;

public class ProductModel {


    private String name;
    private Integer price;
    private Integer stock;
    private String category;

    public String getName() {
        return name;
    }

    public ProductModel setName(String name) {
        this.name = name;

        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ProductModel setPrice(Integer price) {
        this.price = price;

        return this;
    }

    public Integer getStock() {
        return stock;
    }

    public ProductModel setStock(Integer stock) {
        this.stock = stock;

        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductModel setCategory(String category) {
        this.category = category;

        return this;
    }
}
