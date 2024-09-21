package com.tomas.cvds.parcial;

import com.tomas.cvds.parcial.database.ProductMemoryDatabase;
import com.tomas.cvds.parcial.repositories.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return ProductMemoryDatabase.getInstance();
    }
}
