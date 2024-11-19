package br.com.guilherme.cadpessoa.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		Map<String, String> beanValidationErrors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage =  error.getDefaultMessage();
			beanValidationErrors.put(fieldName, errorMessage);
		});
		
		
		Map<String, Object> errors = new HashMap<>();		
		errors.put("timestamp", Instant.now());
		errors.put("status", HttpStatus.BAD_REQUEST);
		errors.put("path", request.getRequestURI());		
		errors.put("beanValidationErrors", beanValidationErrors);
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CadPessoaUserException.class)
	public ResponseEntity<?> handleException(CadPessoaUserException e, HttpServletRequest request) {
		
		Map<String, Object> errors = new HashMap<>();
		errors.put("timestamp", Instant.now());
		errors.put("status", HttpStatus.BAD_REQUEST);
		errors.put("message", e.getMessage());
		errors.put("path", request.getRequestURI());
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
