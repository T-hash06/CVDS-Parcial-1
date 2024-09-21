package com.tomas.cvds.parcial.database;


import com.tomas.cvds.parcial.models.ProductModel;
import com.tomas.cvds.parcial.repositories.ProductRepository;

import java.util.ArrayList;

public class ProductMemoryDatabase implements ProductRepository {

    private static ProductMemoryDatabase instance;
    private final ArrayList<ProductModel> products;

    private ProductMemoryDatabase() {
        this.products = new ArrayList<>();
    }

    public static ProductMemoryDatabase getInstance() {
        if (instance == null) {
            ProductMemoryDatabase.instance = new ProductMemoryDatabase();
        }

        return ProductMemoryDatabase.instance;
    }

    @Override
    public ArrayList<ProductModel> getAllProducts() {
        return this.products;
    }
}
