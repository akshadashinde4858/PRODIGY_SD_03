package com.prodigy.contactmanagementsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ExpiredJwtException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
