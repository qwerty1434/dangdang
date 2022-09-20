package com.dangdang.advice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DuplicateException extends Exception{
    @Getter
    HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

    public DuplicateException(String msg) {
        super(msg);
    }
}