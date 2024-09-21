package com.tomas.cvds.parcial.services;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * ProductService class
 * Defines the services for the ProductModel
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    /**
     * Retrieves a product by its name.
     *
     * @param productName the name of the product to retrieve
     * @return the product with the specified name
     * @throws ProductException.ProductNotFoundException if the product is not found
     */
    public ProductModel getProductByName(String productName) throws ProductException.ProductNotFoundException {
        ProductModel productModel = this.productRepository.getProductByName(productName);

        if (productModel == null) {
            throw new ProductException.ProductNotFoundException();
        }

        return productModel;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    public ArrayList<ProductModel> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    /**
     * Creates a new product.
     *
     * @param product the product to create
     * @return null
     * @throws ProductException.ProductConflictException if there is a conflict creating the product
     */
    public Void createProduct(ProductModel product) throws ProductException.ProductConflictException {
        this.productRepository.createProduct(product);
        return null;
    }

    /**
     * Updates the stock of a product.
     *
     * @param name  the name of the product to update
     * @param stock the new stock value
     * @return null
     * @throws ProductException.ProductNotFoundException      if the product is not found
     * @throws ProductException.ProductStockNegativeException if the stock value is negative
     */
    public Void updateProductStock(String name, Integer stock) throws ProductException.ProductNotFoundException, ProductException.ProductStockNegativeException {
        if (stock < 0) {
            throw new ProductException.ProductStockNegativeException();
        }

        this.productRepository.updateProductStock(name, stock);

        return null;
    }

    /**
     * Deletes all products.
     *
     * @return null
     */
    public Void deleteAllProducts() {
        this.productRepository.deleteAllProducts();
        return null;
    }
}
