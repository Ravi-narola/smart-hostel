package com.smarthostel.fee_service.service;

import com.smarthostel.fee_service.entity.Fee;

import java.util.List;

public interface FeeService {

    Fee createFee(Fee fee);

    Fee getFeeById(Long id);

    List<Fee> getAllFees();

    List<Fee> getFeesByStudent(Long studentId);

    List<Fee> getFeesByStudentAndStatus(Long studentId, String status);

    List<Fee> getFeesByStatus(String status);

    List<Fee> getFeesByType(String feeType);

    Fee updateFee(Long id, Fee fee);

    void deleteFee(Long id);
}