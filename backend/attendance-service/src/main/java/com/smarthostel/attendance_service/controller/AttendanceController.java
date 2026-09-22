package com.smarthostel.attendance_service.controller;

import com.smarthostel.attendance_service.dto.AttendanceRequest;
import com.smarthostel.attendance_service.dto.AttendanceResponse;
import com.smarthostel.attendance_service.entity.Attendance;
import com.smarthostel.attendance_service.mapper.AttendanceMapper;
import com.smarthostel.attendance_service.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttendanceMapper attendanceMapper;

    @PostMapping
    public ResponseEntity<AttendanceResponse> createAttendance(
            @Valid @RequestBody AttendanceRequest request) {

        Attendance attendance =
                attendanceMapper.toEntity(request);

        Attendance saved =
                attendanceService.createAttendance(
                        attendance
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(attendanceMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceMapper.toResponse(
                        attendanceService
                                .getAttendanceById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>>
    getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance()
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponse>>
    getByStudentId(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByStudentId(studentId)
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceResponse>>
    getByDate(
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByDate(date)
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}/range")
    public ResponseEntity<List<AttendanceResponse>>
    getByStudentAndDateRange(
            @PathVariable Long studentId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByStudentAndDateRange(
                                studentId,
                                startDate,
                                endDate
                        )
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse>
    updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRequest request) {

        Attendance attendance =
                attendanceMapper.toEntity(request);

        Attendance updated =
                attendanceService.updateAttendance(
                        id,
                        attendance
                );

        return ResponseEntity.ok(
                attendanceMapper.toResponse(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }
}