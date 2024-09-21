package com.tomas.cvds.parcial.repositories;

import com.tomas.cvds.parcial.models.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository {

    ArrayList<ProductModel> getAllProducts();
}
