package com.prodigy.contactmanagementsystem.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ContactNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
