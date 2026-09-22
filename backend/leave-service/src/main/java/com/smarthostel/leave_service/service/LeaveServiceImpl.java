package com.smarthostel.leave_service.service;

import com.smarthostel.leave_service.entity.Leave;
import com.smarthostel.leave_service.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    @Override
    public Leave createLeave(Leave leave) {

        if (leave.getFromDate().isAfter(leave.getToDate())) {
            throw new RuntimeException(
                    "From date cannot be after to date"
            );
        }

        if (leaveRepository
                .existsByStudentIdAndFromDateAndToDate(
                        leave.getStudentId(),
                        leave.getFromDate(),
                        leave.getToDate())) {

            throw new RuntimeException(
                    "Leave already exists for student "
                            + leave.getStudentId()
                            + " for the given dates"
            );
        }

        if (leave.getStatus() == null ||
                leave.getStatus().isBlank()) {

            leave.setStatus("PENDING");
        }

        return leaveRepository.save(leave);
    }

    @Override
    public Leave getLeaveById(Long id) {

        return leaveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave not found with id: " + id
                        ));
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public List<Leave> getLeavesByStudentId(
            Long studentId) {

        return leaveRepository.findByStudentId(studentId);
    }

    @Override
    public List<Leave> getLeavesByStudentAndStatus(
            Long studentId,
            String status) {

        return leaveRepository
                .findByStudentIdAndStatus(
                        studentId,
                        status
                );
    }

    @Override
    public List<Leave> getLeavesByStatus(
            String status) {

        return leaveRepository.findByStatus(status);
    }

    @Override
    public List<Leave> getLeavesByDateRange(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(
                    "Start date cannot be after end date"
            );
        }

        return leaveRepository.findByFromDateBetween(
                startDate,
                endDate
        );
    }

    @Override
    public Leave updateLeave(
            Long id,
            Leave leave) {

        Leave existingLeave = getLeaveById(id);

        if (leave.getFromDate().isAfter(leave.getToDate())) {
            throw new RuntimeException(
                    "From date cannot be after to date"
            );
        }

        boolean datesChanged =
                !existingLeave.getStudentId()
                        .equals(leave.getStudentId())
                || !existingLeave.getFromDate()
                        .equals(leave.getFromDate())
                || !existingLeave.getToDate()
                        .equals(leave.getToDate());

        if (datesChanged &&
                leaveRepository
                        .existsByStudentIdAndFromDateAndToDate(
                                leave.getStudentId(),
                                leave.getFromDate(),
                                leave.getToDate())) {

            throw new RuntimeException(
                    "Leave already exists for student "
                            + leave.getStudentId()
                            + " for the given dates"
            );
        }

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