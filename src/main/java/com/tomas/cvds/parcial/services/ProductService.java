package com.tomas.cvds.parcial.services;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ArrayList<ProductModel> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    public Void createProduct (ProductModel product) throws ProductException.ProductConflictException {
        this.productRepository.createProduct(product);
        return null;
    }
}
