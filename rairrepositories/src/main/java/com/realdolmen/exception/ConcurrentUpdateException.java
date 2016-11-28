package com.realdolmen.exception;

public class ConcurrentUpdateException extends Exception {
    public ConcurrentUpdateException(String msg){
        super(msg);
    }
}