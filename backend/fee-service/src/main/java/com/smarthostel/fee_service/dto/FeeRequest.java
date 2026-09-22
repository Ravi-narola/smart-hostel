package com.smarthostel.fee_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeRequest {

    @NotNull
    private Long studentId;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @DecimalMin(value = "0.0")
    private BigDecimal paidAmount;

    @NotNull
    private String feeType;

    @NotNull
    private LocalDate dueDate;

    private LocalDate paymentDate;

    private String status;

    private String paymentMethod;

    private String transactionId;

    private String remarks;
}