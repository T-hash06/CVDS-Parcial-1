package com.tomas.cvds.parcial.controllers;

import com.tomas.cvds.parcial.exceptions.AppException;
import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controller for handling product-related requests.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves a product by its name.
     *
     * @param name the name of the product
     * @return the product details or an error response
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name) {
        try {
            ProductModel product = productService.getProductByName(name);
            return ResponseEntity.ok(product);
        } catch (Exception exception) {
            if (exception instanceof AppException) {
                return ((AppException) exception).getResponse();
            }
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products or an error response
     */
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            ArrayList<ProductModel> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception exception) {
            if (exception instanceof AppException) {
                return ((AppException) exception).getResponse();
            }
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Creates a new product.
     *
     * @param product the product details
     * @return a response indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductModel product) {
        try {
            productService.createProduct(product);
            return ResponseEntity.status(201).body(null);
        } catch (Exception exception) {
            if (exception instanceof AppException) {
                return ((ProductException.ProductConflictException) exception).getResponse();
            }
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Updates the stock of a product.
     *
     * @param name  the name of the product
     * @param stock the new stock value
     * @return a response indicating the result of the operation
     */
    @PatchMapping
    public ResponseEntity<?> updateProductStock(@RequestParam String name, @RequestParam int stock) {
        try {
            productService.updateProductStock(name, stock);
            return ResponseEntity.ok().body(null);
        } catch (Exception exception) {
            if (exception instanceof AppException) {
                return ((AppException) exception).getResponse();
            }
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * Deletes all products.
     *
     * @return a response indicating the result of the operation
     */
    @DeleteMapping
    public ResponseEntity<?> deleteAllProducts() {
        try {
            productService.deleteAllProducts();
            return ResponseEntity.ok().body(null);
        } catch (Exception exception) {
            if (exception instanceof AppException) {
                return ((AppException) exception).getResponse();
            }
            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
