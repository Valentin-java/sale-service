package ru.tradesolution.salesservice.rest.controller.handle.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tradesolution.salesservice.rest.exception.ExternalEANServerErrorException;
import ru.tradesolution.salesservice.rest.exception.ProductProcessingException;

@ControllerAdvice
public class ProductErrorHandler {

    @ExceptionHandler(ProductProcessingException.class)
    ResponseEntity<String> handle(ProductProcessingException e) {
        return handleResponseStatusException(e);
    }

    @ExceptionHandler(ExternalEANServerErrorException.class)
    ResponseEntity<String> handle(ExternalEANServerErrorException e) {
        return handleResponseStatusException(e);
    }

    private <T extends RuntimeException> ResponseEntity<String> handleResponseStatusException(T e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
