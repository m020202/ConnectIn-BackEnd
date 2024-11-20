package com.Connect_In.web.controller;

import com.Connect_In.web.apiPayload.ApiResponse;
import com.Connect_In.web.dto.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/payments/")
public class TossController {
    private final String API_URL = "https://api.tosspayments.com/v1/payments/confirm";
    private final String SECRET_KEY = "test_sk_DnyRpQWGrNanRMA2mRjgVKwv1M9E";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/saveAmount")
    public ResponseEntity<?> tempSave(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {
        session.setAttribute(saveAmountRequest.getOrderId(), saveAmountRequest.getAmount());
        return ResponseEntity.ok("Payment temp save successful");
    }

    @PostMapping("/verifyAmount")
    public ResponseEntity<?> verifyAmount(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {

        String amount = (String) session.getAttribute(saveAmountRequest.getOrderId());
        // 결제 전의 금액과 결제 후의 금액이 같은지 검증
        if(amount == null || !amount.equals(saveAmountRequest.getAmount()))
            return ResponseEntity.badRequest().body(PaymentErrorResponse.builder().code(400).message("결제 금액 정보가 유효하지 않습니다.").build());

        // 검증에 사용했던 세션은 삭제
        session.removeAttribute(saveAmountRequest.getOrderId());

        return ResponseEntity.ok("Payment is valid");
    }

    @PostMapping("/confirm")
    public ApiResponse<ConfirmPaymentDTO.ConfirmPaymentResponseDTO> confirmPayment(@RequestBody ConfirmPaymentDTO.ConfirmPaymentRequestDTO confirmPaymentRequest) throws Exception {

        // requestConfirm(): toss payments에 결제 승인 요청
        HttpResponse<String> response = requestConfirm(confirmPaymentRequest); // 토스에게 결제 승인 요청

        if (response.statusCode() == 200) {
            return ApiResponse.onSuccess(objectMapper.readValue(response.body(), ConfirmPaymentDTO.ConfirmPaymentResponseDTO.class));
        } else {
            throw new Exception("결제 승인 실패: " + response.body());
        }
    }

    public HttpResponse<String> requestConfirm(ConfirmPaymentDTO.ConfirmPaymentRequestDTO confirmPaymentRequest) throws Exception {
        String tossOrderId = confirmPaymentRequest.getOrderId();
        String amount = confirmPaymentRequest.getAmount();
        String paymentKey = confirmPaymentRequest.getPaymentKey();

        // 승인 요청에 사용할 JSON 객체를 만듭니다.
        JsonNode requestObj = objectMapper.createObjectNode()
                .put("orderId", tossOrderId)
                .put("amount", amount)
                .put("paymentKey", paymentKey);

        // ObjectMapper를 사용하여 JSON 객체를 문자열로 변환
        String requestBody = objectMapper.writeValueAsString(requestObj);

        // 결제 승인 API를 호출
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Basic " + encodeBase64(SECRET_KEY))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    // Base64 인코딩 유틸리티
    private String encodeBase64(String value) {
        return java.util.Base64.getEncoder().encodeToString(value.getBytes());
    }

}
