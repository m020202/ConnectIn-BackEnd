package com.Connect_In.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveAmountRequest {
    private String orderId;
    private Integer amount;
}
