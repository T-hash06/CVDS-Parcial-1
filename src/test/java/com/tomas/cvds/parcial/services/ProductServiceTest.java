package com.tomas.cvds.parcial.services;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @AfterEach
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }

    @Test
    public void shouldCreateAValidProduct() throws ProductException.ProductConflictException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        assertTrue(productService.getAllProducts().contains(model));
    }

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

    @Test
    public void shouldFindAProductByName() throws ProductException.ProductConflictException, ProductException.ProductNotFoundException {
        ProductModel model = new ProductModel();

        model.setName("Product").setPrice(1000).setCategory("Category").setStock(10);
        productService.createProduct(model);

        ProductModel createdProduct = productService.getProductByName("Product");

        assertEquals(model, createdProduct);
    }

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

    @Test
    public void shouldErrorIfProductDoesNotExistOnGetProduct() {
        try {
            productService.getProductByName("Product");
        } catch (ProductException.ProductNotFoundException e) {
            assertEquals("Product not found", e.getMessage());
        }
    }

    @Test
    public void shouldErrorIfProductDoesNotExistOnUpdateProduct() {
        try {
            productService.updateProductStock("Product", 10);
        } catch (ProductException.ProductNotFoundException | ProductException.ProductStockNegativeException e) {
            assertEquals("Product not found", e.getMessage());
        }
    }

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
