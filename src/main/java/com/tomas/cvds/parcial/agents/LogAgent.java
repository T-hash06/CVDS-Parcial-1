package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.models.ProductModel;

/**
 * LogAgent is responsible for logging product information updates.
 * It subscribes to the ProductMemoryDatabase to receive notifications.
 */
public class LogAgent extends Agent<ProductModel> {

    /**
     * Constructor for LogAgent.
     * It subscribes to the ProductMemoryDatabase to receive product updates.
     */
    public LogAgent() {
        ProductMemoryDatabase database = ProductMemoryDatabase.getInstance();
        database.addSubscriber(this);

        System.out.println("LogAgent created");
    }

    /**
     * Notifies the LogAgent with the updated product data.
     * Logs the product name and available stock.
     *
     * @param data the updated product data
     */
    @Override
    public void notify(ProductModel data) {
        this.log("Product: " + data.getName() + " -> " + data.getStock() + " available units");
    }
}
