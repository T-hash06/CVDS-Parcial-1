package com.tomas.cvds.parcial.database;


import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;

import java.util.ArrayList;

/**
 * Singleton class that implements the ProductRepository and Subscriptable interfaces.
 * Manages an in-memory database of products and notifies subscribers of changes.
 */
public class ProductMemoryDatabase implements ProductRepository, Subscriptable<ProductModel> {

    private static ProductMemoryDatabase instance;
    private final ArrayList<ProductModel> products;
    private final ArrayList<Notifiable> subscribers;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the products and subscribers lists.
     */
    private ProductMemoryDatabase() {
        this.products = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the ProductMemoryDatabase.
     *
     * @return the singleton instance
     */
    public static ProductMemoryDatabase getInstance() {
        if (instance == null) {
            ProductMemoryDatabase.instance = new ProductMemoryDatabase();
        }

        return ProductMemoryDatabase.instance;
    }

    /**
     * Retrieves a product by its name.
     *
     * @param productName the name of the product
     * @return the product with the specified name, or null if not found
     */
    public ProductModel getProductByName(String productName) {
        return this.products.stream().findFirst().filter(productModel -> productModel.getName().equalsIgnoreCase(productName)).orElse(null);
    }

    /**
     * Retrieves all products in the database.
     *
     * @return a list of all products
     */
    @Override
    public ArrayList<ProductModel> getAllProducts() {
        return this.products;
    }

    /**
     * Creates a new product in the database.
     *
     * @param product the product to be created
     * @return null
     * @throws ProductException.ProductConflictException if a product with the same name already exists
     */
    @Override
    public Void createProduct(ProductModel product) throws ProductException.ProductConflictException {
        if (this.products.stream().findFirst().filter(productModel -> productModel.getName().equalsIgnoreCase(product.getName())).isPresent()) {
            throw new ProductException.ProductConflictException();
        }

        this.products.add(product);

        return null;
    }

    /**
     * Updates the stock of a product.
     *
     * @param name  the name of the product
     * @param stock the new stock value
     * @return null
     * @throws ProductException.ProductNotFoundException if the product is not found
     */
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

    /**
     * Deletes all products in the database.
     *
     * @return null
     */
    @Override
    public Void deleteAllProducts() {
        this.products.clear();
        return null;
    }

    /**
     * Notifies all subscribers of a change to a product.
     *
     * @param data the product that has changed
     */
    @Override
    public void notifySubscribers(ProductModel data) {
        for (Notifiable<ProductModel> subscriber : this.subscribers) {
            subscriber.notify(data);
        }
    }

    /**
     * Adds a subscriber to the list of subscribers.
     *
     * @param subscriber the subscriber to be added
     */
    @Override
    public void addSubscriber(Notifiable<ProductModel> subscriber) {
        this.subscribers.add(subscriber);
    }

    /**
     * Removes a subscriber from the list of subscribers.
     *
     * @param subscriber the subscriber to be removed
     */
    @Override
    public void removeSubscriber(Notifiable<ProductModel> subscriber) {
        this.subscribers.remove(subscriber);
    }
}
