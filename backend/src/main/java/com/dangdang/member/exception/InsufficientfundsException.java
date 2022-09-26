package com.dangdang.member.exception;

public class InsufficientfundsException extends Exception{
    public InsufficientfundsException() {
        super("잔액이 부족합니다.");
    }
}
