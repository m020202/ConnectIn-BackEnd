package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ConfirmPaymentRequest {
    private String orderId;
    private String amount;

}
