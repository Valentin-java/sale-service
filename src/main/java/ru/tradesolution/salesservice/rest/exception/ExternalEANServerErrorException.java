package ru.tradesolution.salesservice.rest.exception;

public class ExternalEANServerErrorException extends RuntimeException {

    public ExternalEANServerErrorException(String message) {
        super(message);
    }
}
