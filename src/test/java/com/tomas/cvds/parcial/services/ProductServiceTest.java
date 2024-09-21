package com.tomas.cvds.parcial.services;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ProductService
 */
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @AfterEach
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    /**
     * Should create a valid product
     * Test if a valid product is created successfully
     * @throws ProductException.ProductConflictException
     */
    @Test
    public void shouldCreateAValidProduct() throws ProductException.ProductConflictException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        assertTrue(productService.getAllProducts().contains(model));
    }

    /**
     * Should get all products
     * Test if all products are returned successfully from the repository
     * @throws ProductException.ProductConflictException
     */
    @Test
    public void shouldUpdateProductStock() throws ProductException.ProductConflictException, ProductException.ProductNotFoundException, ProductException.ProductStockNegativeException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        ProductModel createdProduct = productService.getProductByName("Product");

        assertEquals(10, (int) createdProduct.getStock());
        productService.updateProductStock("Product", 4);

        ProductModel updatedProduct = productService.getProductByName("Product");
        assertEquals(4, (int) updatedProduct.getStock());
    }

    /**
     * Should delete all products
     * Test if all products are deleted successfully from the repository
     * @throws ProductException.ProductConflictException
     */
    @Test
    public void shouldDeleteAllProducts() throws ProductException.ProductConflictException {
        ProductModel model1 = new ProductModel();
        ProductModel model2 = new ProductModel();

        model1.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        model2.setName("Product2").setPrice(1000).setCategory("Category").setStock(10);

        productService.createProduct(model1);
        productService.createProduct(model2);

        assertTrue(productService.getAllProducts().contains(model1));
        assertTrue(productService.getAllProducts().contains(model2));
        productService.deleteAllProducts();
        assertTrue(productService.getAllProducts().isEmpty());
    }

    /**
     * Should get a product by its name
     * Test if a product is returned successfully by its name
     * @throws ProductException.ProductConflictException
     * @throws ProductException.ProductNotFoundException
     */
    @Test
    public void shouldFindAProductByName() throws ProductException.ProductConflictException, ProductException.ProductNotFoundException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        ProductModel createdProduct = productService.getProductByName("Product");

        assertEquals(model, createdProduct);
    }

    /**
     * Should throw an error if the product name already exists
     * Test if an error is thrown when trying to create a product with an existing name
     * @throws ProductException.ProductConflictException
     */
    @Test
    public void shouldErrorIfProductNameAlreadyExists() throws ProductException.ProductConflictException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        try {
            productService.createProduct(model);
        } catch (ProductException.ProductConflictException e) {
            assertEquals("Product already exists", e.getMessage());
        }
    }

    /**
     * Should error if product does not exist
     * Test if an error is thrown when trying to get a product that does not exist
     */
    @Test
    public void shouldErrorIfProductDoesNotExistOnGetProduct() {
        try {
            productService.getProductByName("Product");
        } catch (ProductException.ProductNotFoundException e) {
            assertEquals("Product not found", e.getMessage());
        }
    }

    /**
     * Should error if product does not exist when an update is attempted
     * Test if an error is thrown when trying to update a product that does not exist
     */
    @Test
    public void shouldErrorIfProductDoesNotExistOnUpdateProduct() {
        try {
            productService.updateProductStock("Product", 10);
        } catch (ProductException.ProductNotFoundException | ProductException.ProductStockNegativeException e) {
            assertEquals("Product not found", e.getMessage());
        }
    }

    /**
     * Should error if product stock is negative
     * Test if an error is thrown when trying to update a product with a negative stock
     * @throws ProductException.ProductConflictException
     */
    @Test
    public void shouldErrorIfProductStockIsNegative() throws ProductException.ProductConflictException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        try {
            productService.updateProductStock("Product", -1);
        } catch (ProductException.ProductNotFoundException | ProductException.ProductStockNegativeException e) {
            assertEquals("Product stock cannot be negative", e.getMessage());
        }
    }
}
