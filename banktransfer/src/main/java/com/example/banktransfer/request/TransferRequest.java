package com.example.banktransfer.request;

public record TransferRequest(String sendId, String reiceiveId, Long amount) {
}
