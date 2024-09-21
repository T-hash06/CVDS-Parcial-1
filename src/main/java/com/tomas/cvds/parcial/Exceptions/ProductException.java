package com.tomas.cvds.parcial.Exceptions;

public class ProductException extends AppException {
    public ProductException(String message, Integer statusCode) {
        super(message, statusCode);
    }

    public static class ProductConflictException extends ProductException {
        public ProductConflictException() {
            super("Product already exists", 409);
        }
    }
}
