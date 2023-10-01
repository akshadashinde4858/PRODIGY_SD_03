package com.prodigy.contactmanagementsystem.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prodigy.contactmanagementsystem.exception.ContactNotFoundException;
import com.prodigy.contactmanagementsystem.exception.UserNotFoundException;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	


	@ExceptionHandler(value = { ExpiredJwtException.class })
	protected ResponseEntity<Object> jwtTokenExpired(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Session Expired. Please login again.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { ContactNotFoundException.class })
	protected ResponseEntity<Object> contactNotFoundExceptionHandler(RuntimeException ex, WebRequest request) {	
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	
	

	@ExceptionHandler(value = { UserNotFoundException.class })
	protected ResponseEntity<Object> userNotFoundExceptionHandler(RuntimeException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}