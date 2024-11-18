package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ConfirmPaymentResponse {
    private String orderId;
    private String message;
}
