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
        productService.updateProductStock("Product", 20);

        ProductModel updatedProduct = productService.getProductByName("Product");
        assertEquals(20, (int) updatedProduct.getStock());
    }
}
