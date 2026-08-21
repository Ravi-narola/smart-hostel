package com.smarthostel.fee_service.mapper;

import com.smarthostel.fee_service.dto.FeeRequest;
import com.smarthostel.fee_service.dto.FeeResponse;
import com.smarthostel.fee_service.entity.Fee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeeMapper {

    public Fee toEntity(FeeRequest request) {

        BigDecimal paidAmount =
                request.getPaidAmount() != null
                        ? request.getPaidAmount()
                        : BigDecimal.ZERO;

        BigDecimal pendingAmount =
                request.getAmount().subtract(paidAmount);

        if (pendingAmount.compareTo(BigDecimal.ZERO) < 0) {
            pendingAmount = BigDecimal.ZERO;
        }

        return Fee.builder()
                .studentId(request.getStudentId())
                .amount(request.getAmount())
                .paidAmount(paidAmount)
                .pendingAmount(pendingAmount)
                .feeType(request.getFeeType())
                .dueDate(request.getDueDate())
                .paymentDate(request.getPaymentDate())
                .status(request.getStatus())
                .paymentMethod(request.getPaymentMethod())
                .transactionId(request.getTransactionId())
                .remarks(request.getRemarks())
                .build();
    }

    public FeeResponse toResponse(Fee fee) {

        return FeeResponse.builder()
                .id(fee.getId())
                .studentId(fee.getStudentId())
                .amount(fee.getAmount())
                .paidAmount(fee.getPaidAmount())
                .pendingAmount(fee.getPendingAmount())
                .feeType(fee.getFeeType())
                .dueDate(fee.getDueDate())
                .paymentDate(fee.getPaymentDate())
                .status(fee.getStatus())
                .paymentMethod(fee.getPaymentMethod())
                .transactionId(fee.getTransactionId())
                .remarks(fee.getRemarks())
                .createdAt(fee.getCreatedAt())
                .updatedAt(fee.getUpdatedAt())
                .build();
    }

    public void updateEntity(
            Fee fee,
            FeeRequest request) {

        BigDecimal paidAmount =
                request.getPaidAmount() != null
                        ? request.getPaidAmount()
                        : BigDecimal.ZERO;

        fee.setStudentId(request.getStudentId());
        fee.setAmount(request.getAmount());
        fee.setPaidAmount(paidAmount);
        fee.setFeeType(request.getFeeType());
        fee.setDueDate(request.getDueDate());
        fee.setPaymentDate(request.getPaymentDate());
        fee.setStatus(request.getStatus());
        fee.setPaymentMethod(request.getPaymentMethod());
        fee.setTransactionId(request.getTransactionId());
        fee.setRemarks(request.getRemarks());

        BigDecimal pendingAmount =
                request.getAmount().subtract(paidAmount);

        if (pendingAmount.compareTo(BigDecimal.ZERO) < 0) {
            pendingAmount = BigDecimal.ZERO;
        }

        fee.setPendingAmount(pendingAmount);
    }
}