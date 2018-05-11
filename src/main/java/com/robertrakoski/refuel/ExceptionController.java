package com.robertrakoski.refuel;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionController {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleException(Exception exception) {
		return new ResponseEntity<>(formErrorMessage(exception), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception) {
		return new ResponseEntity<>(formErrorMessage(exception), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value = EntityExistsException.class)
	public ResponseEntity<ErrorMessage> handleEntityExistsException(EntityExistsException exception) {
		return new ResponseEntity<>(formErrorMessage(exception), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception) {
		return new ResponseEntity<>(formErrorMessage(exception), HttpStatus.NOT_FOUND);
	}
	
	private ErrorMessage formErrorMessage(Exception exception) {
		return new ErrorMessage(exception.getMessage(), exception.getClass().getName());
	}
	
	static class ErrorMessage {
		private String message;
		private String errorClass;

		ErrorMessage(String message, String errorClass) {
			this.message = message;
			this.errorClass = errorClass;
		}

		public String getMessage() {
			return message;
		}

		public String getErrorClass() {
			return errorClass;
		}
	}
}