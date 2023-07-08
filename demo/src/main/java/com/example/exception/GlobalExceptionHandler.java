package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> getMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		FieldError fieldError = ex.getBindingResult().getFieldError();
		String field = fieldError.getField();
		String defaultMessage = fieldError.getDefaultMessage();
		String errorMessage = field + " : " + defaultMessage;
		return new ResponseEntity<String>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> getDuplicateException(DuplicateUserException ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
