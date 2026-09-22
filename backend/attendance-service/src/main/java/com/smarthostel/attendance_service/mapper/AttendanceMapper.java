package com.smarthostel.attendance_service.mapper;

import com.smarthostel.attendance_service.dto.AttendanceRequest;
import com.smarthostel.attendance_service.dto.AttendanceResponse;
import com.smarthostel.attendance_service.entity.Attendance;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public Attendance toEntity(AttendanceRequest request) {

        return Attendance.builder()
                .studentId(request.getStudentId())
                .attendanceDate(request.getAttendanceDate())
                .status(request.getStatus())
                .remarks(request.getRemarks())
                .build();
    }

    public AttendanceResponse toResponse(Attendance attendance) {

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentId(attendance.getStudentId())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus())
                .remarks(attendance.getRemarks())
                .createdAt(attendance.getCreatedAt())
                .updatedAt(attendance.getUpdatedAt())
                .build();
    }

    public void updateEntity(
            Attendance attendance,
            AttendanceRequest request) {

        attendance.setStudentId(request.getStudentId());
        attendance.setAttendanceDate(
                request.getAttendanceDate()
        );
        attendance.setStatus(request.getStatus());
        attendance.setRemarks(request.getRemarks());
    }
}