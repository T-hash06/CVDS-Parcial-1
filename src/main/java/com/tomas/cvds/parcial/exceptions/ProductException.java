package com.tomas.cvds.parcial.exceptions;

/**
 * Custom exception class for product-related exceptions.
 */
public class ProductException extends AppException {
    /**
     * Constructs a new ProductException with the specified detail message and status code.
     *
     * @param message the detail message.
     * @param statusCode the HTTP status code.
     */
    public ProductException(String message, Integer statusCode) {
        super(message, statusCode);
    }

    /**
     * Exception indicating a product conflict (e.g., product already exists).
     */
    public static class ProductConflictException extends ProductException {
        /**
         * Constructs a new ProductConflictException with a default message and status code 409.
         */
        public ProductConflictException() {
            super("Product already exists", 409);
        }
    }

    /**
     * Exception indicating that a product was not found.
     */
    public static class ProductNotFoundException extends ProductException {
        /**
         * Constructs a new ProductNotFoundException with a default message and status code 404.
         */
        public ProductNotFoundException() {
            super("Product not found", 404);
        }
    }

    /**
     * Exception indicating that the product stock cannot be negative.
     */
    public static class ProductStockNegativeException extends ProductException {
        /**
         * Constructs a new ProductStockNegativeException with a default message and status code 400.
         */
        public ProductStockNegativeException() {
            super("Product stock cannot be negative", 400);
        }
    }
}
