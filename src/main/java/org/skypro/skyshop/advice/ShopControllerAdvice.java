package org.skypro.skyshop.advice;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.error.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        ShopError shopError = new ShopError("PRODUCT_NOT_FOUND", "Запрашиваемый продукт не найден.");

        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}