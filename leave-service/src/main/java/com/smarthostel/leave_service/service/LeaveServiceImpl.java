package com.smarthostel.leave_service.service;

import com.smarthostel.leave_service.entity.Leave;
import com.smarthostel.leave_service.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    @Override
    public Leave createLeave(Leave leave) {

        if (leave.getStatus() == null) {
            leave.setStatus("PENDING");
        }

        return leaveRepository.save(leave);
    }

    @Override
    public Leave getLeaveById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with id: " + id));
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public List<Leave> getLeavesByStudent(Long studentId) {
        return leaveRepository.findByStudentId(studentId);
    }

    @Override
    public List<Leave> getLeavesByStatus(String status) {
        return leaveRepository.findByStatus(status);
    }

    @Override
    public List<Leave> getLeavesByType(String leaveType) {
        return leaveRepository.findByLeaveType(leaveType);
    }

    @Override
    public Leave updateLeave(Long id, Leave leave) {

        Leave existingLeave = getLeaveById(id);

        existingLeave.setStudentId(leave.getStudentId());
        existingLeave.setFromDate(leave.getFromDate());
        existingLeave.setToDate(leave.getToDate());
        existingLeave.setLeaveType(leave.getLeaveType());
        existingLeave.setReason(leave.getReason());
        existingLeave.setStatus(leave.getStatus());
        existingLeave.setRemarks(leave.getRemarks());

        return leaveRepository.save(existingLeave);
    }

    @Override
    public void deleteLeave(Long id) {
        Leave leave = getLeaveById(id);
        leaveRepository.delete(leave);
    }
}