package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.models.ProductModel;

public class StockAgent extends Agent<ProductModel> {

    public StockAgent() {
        ProductMemoryDatabase database = ProductMemoryDatabase.getInstance();
        database.addSubscriber(this);

        System.out.println("StockAgent created");
    }

    @Override
    public void notify(ProductModel data) {
        if (data.getStock() < 5) {
            this.log("Alert!!!! Product: " + data.getName() + " has too low stock, there are only " + data.getStock() + " units left");
        }
    }
}
