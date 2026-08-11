package com.smarthostel.attendance_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private LocalDate attendanceDate;

    @NotNull
    private String status;

    private String remarks;
}