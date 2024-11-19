package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Payment {
    private String paymentKey;
    private String status;
    private String orderId;
    private String method;
    private String amount;
}
