package com.smarthostel.fee_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal paidAmount;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pendingAmount;

    @NotNull
    @Column(nullable = false, length = 30)
    private String feeType;

    @NotNull
    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate paymentDate;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(length = 100)
    private String paymentMethod;

    @Column(length = 100)
    private String transactionId;

    @Column(length = 500)
    private String remarks;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (paidAmount == null) {
            paidAmount = BigDecimal.ZERO;
        }

        if (pendingAmount == null && amount != null) {
            pendingAmount = amount.subtract(paidAmount);
        }

        if (status == null) {
            status = "PENDING";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();

        if (amount != null && paidAmount != null) {
            pendingAmount = amount.subtract(paidAmount);
        }
    }
}