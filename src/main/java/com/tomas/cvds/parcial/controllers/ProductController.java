package com.tomas.cvds.parcial.controllers;

import com.tomas.cvds.parcial.exceptions.AppException;
import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

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
}
