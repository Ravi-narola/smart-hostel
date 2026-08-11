package com.smarthostel.leave_service.repository;

import com.smarthostel.leave_service.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByStudentId(Long studentId);

    List<Leave> findByStudentIdAndStatus(
            Long studentId,
            String status
    );

    List<Leave> findByStatus(String status);

    List<Leave> findByLeaveType(String leaveType);

    List<Leave> findByFromDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    boolean existsByStudentIdAndFromDateAndToDate(
            Long studentId,
            LocalDate fromDate,
            LocalDate toDate
    );
}