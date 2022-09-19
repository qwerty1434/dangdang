package com.dangdang.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception{
    @Getter
    HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String msg) {
        super(msg);
    }
}
