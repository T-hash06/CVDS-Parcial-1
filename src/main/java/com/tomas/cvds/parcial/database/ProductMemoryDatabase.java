package com.tomas.cvds.parcial.database;


import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductMemoryDatabase implements ProductRepository, Subscriptable<ProductModel> {

    private static ProductMemoryDatabase instance;
    private final ArrayList<ProductModel> products;
    private final ArrayList<Notifiable> subscribers;

    private ProductMemoryDatabase() {
        this.products = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public static ProductMemoryDatabase getInstance() {
        if (instance == null) {
            ProductMemoryDatabase.instance = new ProductMemoryDatabase();
        }

        return ProductMemoryDatabase.instance;
    }

    public ProductModel getProductByName(String productName) {
        return this.products.stream().findFirst().filter(productModel -> productModel.getName().equalsIgnoreCase(productName)).orElse(null);
    }

    @Override
    public ArrayList<ProductModel> getAllProducts() {
        return this.products;
    }

    @Override
    public Void createProduct(ProductModel product) throws ProductException.ProductConflictException {
        if (this.products.stream().findFirst().filter(productModel -> productModel.getName().equalsIgnoreCase(product.getName())).isPresent()) {
            throw new ProductException.ProductConflictException();
        }

        this.products.add(product);

        return null;
    }

    @Override
    public Void updateProductStock(String name, Integer stock) throws ProductException.ProductNotFoundException {
        ProductModel product = this.products.stream().findFirst().filter(productModel -> productModel.getName().equalsIgnoreCase(name)).orElse(null);

        if (product == null) {
            throw new ProductException.ProductNotFoundException();
        }

        product.setStock(stock);
        notifySubscribers(product);

        return null;
    }

    @Override
    public Void deleteAllProducts() {
        this.products.clear();
        return null;
    }

    @Override
    public void notifySubscribers(ProductModel data) {
        for (Notifiable<ProductModel> subscriber : this.subscribers) {
            subscriber.notify(data);
        }
    }

    @Override
    public void addSubscriber(Notifiable<ProductModel> subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Notifiable<ProductModel> subscriber) {
        this.subscribers.remove(subscriber);
    }
}
