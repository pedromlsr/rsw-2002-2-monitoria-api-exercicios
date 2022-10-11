package org.serratec.cliente.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> errors = new ArrayList<>();
		
		for (FieldError error : ex.getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		
		ErrorResponse errorResponse = new ErrorResponse(status.value(),
				"Confira o preenchimento dos campos inseridos.", LocalDateTime.now(), errors);
		
		return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		
		List<String> errors = new ArrayList<>();
		
		errors.add(ex.getLocalizedMessage());
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		
		ErrorResponse error = new ErrorResponse(httpStatus.value(),
				"Registro não encontrado.", LocalDateTime.now(), errors);
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
