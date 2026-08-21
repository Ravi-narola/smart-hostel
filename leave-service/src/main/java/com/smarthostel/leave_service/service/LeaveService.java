package com.smarthostel.leave_service.service;

import com.smarthostel.leave_service.entity.Leave;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {

    Leave createLeave(Leave leave);

    Leave getLeaveById(Long id);

    List<Leave> getAllLeaves();

    List<Leave> getLeavesByStudentId(Long studentId);

    List<Leave> getLeavesByStudentAndStatus(
            Long studentId,
            String status
    );

    List<Leave> getLeavesByStatus(String status);

    List<Leave> getLeavesByDateRange(
            LocalDate startDate,
            LocalDate endDate
    );

    Leave updateLeave(Long id, Leave leave);

    void deleteLeave(Long id);
}