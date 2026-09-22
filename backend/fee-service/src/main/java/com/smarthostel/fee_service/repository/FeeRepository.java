package com.smarthostel.fee_service.repository;

import com.smarthostel.fee_service.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByStudentId(Long studentId);

    List<Fee> findByStudentIdAndStatus(
            Long studentId,
            String status
    );

    List<Fee> findByStatus(String status);

    List<Fee> findByFeeType(String feeType);

    List<Fee> findByDueDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<Fee> findByStudentIdAndDueDateBetween(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate
    );
}