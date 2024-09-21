package com.tomas.cvds.parcial;

import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfiguration class
 * Defines the configuration of the application
 */
@Configuration
public class AppConfiguration {


    /**
     * productRepository method
     * It enables the dependency injection of the ProductRepository interface
     * @return ProductRepository
     */
    @Bean
    public ProductRepository productRepository() {
        return ProductMemoryDatabase.getInstance();
    }
}
