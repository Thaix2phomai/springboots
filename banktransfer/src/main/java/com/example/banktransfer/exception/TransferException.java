package com.example.banktransfer.exception;

public class TransferException extends RuntimeException{
    public TransferException(String message){
        super(message);
    }
}
