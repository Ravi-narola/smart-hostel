package com.smarthostel.fee_service.service;

import com.smarthostel.fee_service.entity.Fee;
import com.smarthostel.fee_service.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    @Override
    public Fee createFee(Fee fee) {
        if (fee.getPaidAmount() == null) {
            fee.setPaidAmount(BigDecimal.ZERO);
        }

        if (fee.getAmount() != null) {
            fee.setPendingAmount(
                    fee.getAmount().subtract(fee.getPaidAmount())
            );
        }

        if (fee.getStatus() == null) {
            fee.setStatus("PENDING");
        }

        return feeRepository.save(fee);
    }

    @Override
    public Fee getFeeById(Long id) {
        return feeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Fee not found with id: " + id));
    }

    @Override
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    @Override
    public List<Fee> getFeesByStudent(Long studentId) {
        return feeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Fee> getFeesByStudentAndStatus(
            Long studentId,
            String status) {

        return feeRepository.findByStudentIdAndStatus(studentId, status);
    }

    @Override
    public List<Fee> getFeesByStatus(String status) {
        return feeRepository.findByStatus(status);
    }

    @Override
    public List<Fee> getFeesByType(String feeType) {
        return feeRepository.findByFeeType(feeType);
    }

    @Override
    public Fee updateFee(Long id, Fee fee) {

        Fee existingFee = getFeeById(id);

        existingFee.setStudentId(fee.getStudentId());
        existingFee.setAmount(fee.getAmount());
        existingFee.setPaidAmount(
                fee.getPaidAmount() != null
                        ? fee.getPaidAmount()
                        : BigDecimal.ZERO
        );

        existingFee.setPendingAmount(
                existingFee.getAmount()
                        .subtract(existingFee.getPaidAmount())
        );

        existingFee.setFeeType(fee.getFeeType());
        existingFee.setDueDate(fee.getDueDate());
        existingFee.setPaymentDate(fee.getPaymentDate());
        existingFee.setStatus(fee.getStatus());
        existingFee.setPaymentMethod(fee.getPaymentMethod());
        existingFee.setTransactionId(fee.getTransactionId());
        existingFee.setRemarks(fee.getRemarks());

        return feeRepository.save(existingFee);
    }

    @Override
    public void deleteFee(Long id) {
        Fee fee = getFeeById(id);
        feeRepository.delete(fee);
    }
}