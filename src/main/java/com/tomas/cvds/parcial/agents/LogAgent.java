package com.tomas.cvds.parcial.agents;

import com.tomas.cvds.parcial.database.Notifiable;
import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.models.ProductModel;

public class LogAgent extends Agent<ProductModel> {

    public LogAgent() {
        ProductMemoryDatabase database = ProductMemoryDatabase.getInstance();
        database.addSubscriber(this);

        System.out.println("LogAgent created");
    }

    @Override
    public void notify(ProductModel data) {
        this.log("Product: " + data.getName() + " -> " + data.getStock() + " available units");
    }
}
