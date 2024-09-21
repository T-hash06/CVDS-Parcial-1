package com.tomas.cvds.parcial.database;


import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductMemoryDatabase implements ProductRepository, Subscriptable<Integer> {

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
        notifySubscribers(stock);

        return null;
    }

    @Override
    public void notifySubscribers(Integer data) {
        for (Notifiable<Integer> subscriber : this.subscribers) {
            subscriber.notify(data);
        }
    }

    @Override
    public void addSubscriber(Notifiable<Integer> subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Notifiable<Integer> subscriber) {
        this.subscribers.remove(subscriber);
    }
}
