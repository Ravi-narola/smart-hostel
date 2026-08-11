package com.smarthostel.leave_service.mapper;

import com.smarthostel.leave_service.dto.LeaveRequest;
import com.smarthostel.leave_service.dto.LeaveResponse;
import com.smarthostel.leave_service.entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public Leave toEntity(LeaveRequest request) {
        return Leave.builder()
                .studentId(request.getStudentId())
                .fromDate(request.getFromDate())
                .toDate(request.getToDate())
                .leaveType(request.getLeaveType())
                .reason(request.getReason())
                .status(request.getStatus())
                .remarks(request.getRemarks())
                .build();
    }

    public LeaveResponse toResponse(Leave leave) {
        return LeaveResponse.builder()
                .id(leave.getId())
                .studentId(leave.getStudentId())
                .fromDate(leave.getFromDate())
                .toDate(leave.getToDate())
                .leaveType(leave.getLeaveType())
                .reason(leave.getReason())
                .status(leave.getStatus())
                .remarks(leave.getRemarks())
                .createdAt(leave.getCreatedAt())
                .updatedAt(leave.getUpdatedAt())
                .build();
    }

    public void updateEntity(Leave leave, LeaveRequest request) {
        leave.setStudentId(request.getStudentId());
        leave.setFromDate(request.getFromDate());
        leave.setToDate(request.getToDate());
        leave.setLeaveType(request.getLeaveType());
        leave.setReason(request.getReason());
        leave.setStatus(request.getStatus());
        leave.setRemarks(request.getRemarks());
    }
}