package com.smarthostel.fee_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeResponse {

    private Long id;
    private Long studentId;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal pendingAmount;
    private String feeType;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private String status;
    private String paymentMethod;
    private String transactionId;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}