package com.smarthostel.attendance_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceResponse {

    private Long id;

    private Long studentId;

    private LocalDate attendanceDate;

    private String status;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}