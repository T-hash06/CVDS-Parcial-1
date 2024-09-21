package com.tomas.cvds.parcial.repositories;

import com.tomas.cvds.parcial.exceptions.ProductException;
import com.tomas.cvds.parcial.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository {

    ArrayList<ProductModel> getAllProducts();

    Void createProduct(ProductModel product) throws ProductException.ProductConflictException;

    Void updateProductStock(String name, Integer stock) throws ProductException.ProductNotFoundException;
}
