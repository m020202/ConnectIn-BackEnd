//package com.Connect_In.domain;
//
//import com.Connect_In.domain.enums.TossPaymentMethod;
//import com.Connect_In.domain.enums.TossPaymentStatus;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//public class TossPayment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    String paymentId;
//
//
//    @Column(nullable = false, unique = true)
//    String tossPaymentKey;
//
//    // 토스내부에서 관리하는 별도의 orderId가 존재함
//    @Column(nullable = false)
//    String tossOrderId;
//
//    Long totalAmount;
//
//    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
//    TossPaymentMethod tossPaymentMethod;
//
//    @Enumerated(value = EnumType.STRING)
//    @Column(nullable = false)
//    TossPaymentStatus tossPaymentStatus;
//
//    @Column(nullable = false)
//    LocalDateTime requestedAt;
//
//    LocalDateTime approvedAt;
//}
