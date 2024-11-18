package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PaymentErrorResponse {
    private Integer code;
    private String message;
}
