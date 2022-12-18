package com.ajurczyk.shop.common;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final ErrorCode errorCode = ErrorCode.BAD_REQUEST;
    private final String details;

    public BadRequestException(String details) {
	    super(ErrorCode.BAD_REQUEST.getMessage());
        this.details = details;
    }
}
