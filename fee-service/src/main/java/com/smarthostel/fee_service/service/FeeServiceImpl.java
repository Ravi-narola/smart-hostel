package com.smarthostel.fee_service.service;

import com.smarthostel.fee_service.entity.Fee;
import com.smarthostel.fee_service.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    @Override
    public Fee createFee(Fee fee) {

        validateFee(fee);

        calculateFeeAmounts(fee);

        return feeRepository.save(fee);
    }

    @Override
    public Fee getFeeById(Long id) {

        return feeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Fee not found with id: " + id
                        ));
    }

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @Override
    public List<Fee> getFeesByStudentId(
            Long studentId) {

        return feeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Fee> getFeesByStudentAndStatus(
            Long studentId,
            String status) {

        return feeRepository.findByStudentIdAndStatus(
                studentId,
                status
        );
    }

    @Override
    public List<Fee> getFeesByStatus(
            String status) {

        return feeRepository.findByStatus(status);
    }

    @Override
    public List<Fee> getFeesByType(
            String feeType) {

        return feeRepository.findByFeeType(feeType);
    }

    @Override
    public List<Fee> getFeesByDueDateRange(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(
                    "Start date cannot be after end date"
            );
        }

        return feeRepository.findByDueDateBetween(
                startDate,
                endDate
        );
    }

    @Override
    public List<Fee> getFeesByStudentAndDueDateRange(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(
                    "Start date cannot be after end date"
            );
        }

        return feeRepository
                .findByStudentIdAndDueDateBetween(
                        studentId,
                        startDate,
                        endDate
                );
    }

    @Override
    public Fee updateFee(
            Long id,
            Fee fee) {

        Fee existingFee = getFeeById(id);

        validateFee(fee);

        existingFee.setStudentId(fee.getStudentId());
        existingFee.setAmount(fee.getAmount());
        existingFee.setPaidAmount(fee.getPaidAmount());
        existingFee.setFeeType(fee.getFeeType());
        existingFee.setDueDate(fee.getDueDate());
        existingFee.setPaymentDate(fee.getPaymentDate());
        existingFee.setStatus(fee.getStatus());
        existingFee.setPaymentMethod(fee.getPaymentMethod());
        existingFee.setTransactionId(fee.getTransactionId());
        existingFee.setRemarks(fee.getRemarks());

        calculateFeeAmounts(existingFee);

        return feeRepository.save(existingFee);
    }

    @Override
    public void deleteFee(Long id) {

        Fee fee = getFeeById(id);

        feeRepository.delete(fee);
    }

    private void validateFee(Fee fee) {

        if (fee.getAmount() == null ||
                fee.getAmount().compareTo(BigDecimal.ZERO) < 0) {

            throw new RuntimeException(
                    "Amount cannot be negative"
            );
        }

        if (fee.getPaidAmount() == null) {
            fee.setPaidAmount(BigDecimal.ZERO);
        }

        if (fee.getPaidAmount()
                .compareTo(fee.getAmount()) > 0) {

            throw new RuntimeException(
                    "Paid amount cannot be greater than amount"
            );
        }

        if (fee.getDueDate() == null) {
            throw new RuntimeException(
                    "Due date is required"
            );
        }
    }

    private void calculateFeeAmounts(Fee fee) {

        BigDecimal amount = fee.getAmount();

        BigDecimal paidAmount =
                fee.getPaidAmount() != null
                        ? fee.getPaidAmount()
                        : BigDecimal.ZERO;

        BigDecimal pendingAmount =
                amount.subtract(paidAmount);

        fee.setPaidAmount(paidAmount);
        fee.setPendingAmount(pendingAmount);

        if (pendingAmount.compareTo(BigDecimal.ZERO) == 0) {

            fee.setStatus("PAID");

            if (fee.getPaymentDate() == null) {
                fee.setPaymentDate(LocalDate.now());
            }

        } else if (paidAmount.compareTo(BigDecimal.ZERO) > 0) {

            fee.setStatus("PARTIAL");

        } else {

            fee.setStatus("PENDING");
        }
    }
}