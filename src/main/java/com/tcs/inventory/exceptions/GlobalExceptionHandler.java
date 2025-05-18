package com.tcs.inventory.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	   @ExceptionHandler(PurchaseOrderNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handlePurchaseOrderNotFoundException(PurchaseOrderNotFoundException ex) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.NOT_FOUND.value(),
	            "Purchase Order Not Found",
	            ex.getMessage(),
	            LocalDateTime.now()
	        );
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidOperationException.class)
	    public ResponseEntity<ErrorResponse> handleInvalidOperationException(InvalidOperationException ex) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            "Invalid Operation",
	            ex.getMessage(),
	            LocalDateTime.now()
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(InsufficientInventoryException.class)
	    public ResponseEntity<ErrorResponse> handleInsufficientInventoryException(InsufficientInventoryException ex) {
	        ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            "Insufficient Inventory",
	            ex.getMessage(),
	            LocalDateTime.now()
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
//        ErrorResponse error = new ErrorResponse(
//            HttpStatus.NOT_FOUND.value(),
//            ex.getMessage()
//        );
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
}
