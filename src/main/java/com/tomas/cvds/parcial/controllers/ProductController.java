package com.tomas.cvds.parcial.controllers;

import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ArrayList<ProductModel>> getAllProducts() {

        try {
            ArrayList<ProductModel> products = productService.getAllProducts();

            return ResponseEntity.ok(products);
        } catch (Exception exception) {

            System.out.println(exception.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
