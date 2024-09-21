package com.tomas.cvds.parcial.repositories;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * ProductRepository interface
 * Defines the repository for the ProductModel
 */
@Repository
public interface ProductRepository {
    /**
     * Retrieves a product by its name.
     *
     * @param productName the name of the product to retrieve
     * @return the product with the specified name
     */
    ProductModel getProductByName(String productName);

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    ArrayList<ProductModel> getAllProducts();

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @throws ProductException.ProductConflictException if there is a conflict creating the product
     */
    Void createProduct(ProductModel product) throws ProductException.ProductConflictException;

    /**
     * Updates the stock of a product.
     *
     * @param name  the name of the product to update
     * @param stock the new stock value
     * @throws ProductException.ProductNotFoundException if the product is not found
     */
    Void updateProductStock(String name, Integer stock) throws ProductException.ProductNotFoundException;

    /**
     * Deletes all products.
     */
    Void deleteAllProducts();
}
