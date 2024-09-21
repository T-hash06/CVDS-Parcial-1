package com.tomas.cvds.parcial.exceptions;

public class ProductException extends AppException {
    public ProductException(String message, Integer statusCode) {
        super(message, statusCode);
    }

    public static class ProductConflictException extends ProductException {
        public ProductConflictException() {
            super("Product already exists", 409);
        }
    }

    public static class ProductNotFoundException extends ProductException {
        public ProductNotFoundException() {
            super("Product not found", 404);
        }
    }

    public static class ProductStockNegativeException extends ProductException {
        public ProductStockNegativeException() {
            super("Product stock cannot be negative", 400);
        }
    }
}
