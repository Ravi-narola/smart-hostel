package com.smarthostel.leave_service.service;

import com.smarthostel.leave_service.entity.Leave;

import java.util.List;

public interface LeaveService {

    Leave createLeave(Leave leave);

    Leave getLeaveById(Long id);

    List<Leave> getAllLeaves();

    List<Leave> getLeavesByStudent(Long studentId);

    List<Leave> getLeavesByStatus(String status);

    List<Leave> getLeavesByType(String leaveType);

    Leave updateLeave(Long id, Leave leave);

    void deleteLeave(Long id);
}