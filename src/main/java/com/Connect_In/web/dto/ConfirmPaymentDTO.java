package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ConfirmPaymentDTO {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConfirmPaymentRequestDTO {
        private String orderId;
        private Integer amount;
        private String paymentKey;
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConfirmPaymentResponseDTO {
        private String status;
        private String orderName;
        private String orderId;
        private Integer totalAmount;
        private String approvedAt;
    }
}
