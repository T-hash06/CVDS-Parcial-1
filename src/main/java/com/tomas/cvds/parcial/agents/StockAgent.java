package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.models.ProductModel;

/**
 * StockAgent is responsible for monitoring the stock levels of products.
 * It subscribes to the ProductMemoryDatabase and logs alerts when stock levels are low.
 */
public class StockAgent extends Agent<ProductModel> {

    /**
     * Constructs a StockAgent and subscribes it to the ProductMemoryDatabase.
     */
    public StockAgent() {
        ProductMemoryDatabase database = ProductMemoryDatabase.getInstance();
        database.addSubscriber(this);

        System.out.println("StockAgent created");
    }

    /**
     * Notifies the StockAgent with the given ProductModel data.
     * Logs an alert if the stock level is below the threshold.
     *
     * @param data the ProductModel data to be processed
     */
    @Override
    public void notify(ProductModel data) {
        if (data.getStock() < 5) {
            this.log("Alert!!!! Product: " + data.getName() + " has too low stock, there are only " + data.getStock() + " units left");
        }
    }
}
