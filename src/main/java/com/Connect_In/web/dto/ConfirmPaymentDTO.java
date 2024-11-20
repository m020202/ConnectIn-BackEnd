package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ConfirmPaymentDTO {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class ConfirmPaymentRequestDTO {
        private String orderId;
        private String amount;
        private String paymentKey;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    public static class ConfirmPaymentResponseDTO {
        private String paymentKey;
        private String status;
        private String orderId;
        private String method;
        private String amount;
    }
}
