//package ru.tradesolution.salesservice.rest.controller.handle.error;
//
//@ControllerAdvice
//public class ProductErrorHandler {
//    @ExceptionHandler(ResponseStatusException.class)
//    ResponseEntity<String> handle(ResponseStatusException e) {
//        return handleResponseStatusException(e);
//    }
//
//    private ResponseEntity<String> handleResponseStatusException(ResponseStatusException e) {
//        return ResponseEntity.status(e.getStatus()).body(e.getReason());
//    }
//}
