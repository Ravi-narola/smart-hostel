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

        Attendance attendance = attendanceMapper.toEntity(request);
        Attendance savedAttendance =
                attendanceService.createAttendance(attendance);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(attendanceMapper.toResponse(savedAttendance));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(
            @PathVariable Long id) {

        Attendance attendance =
                attendanceService.getAttendanceById(id);

        return ResponseEntity.ok(
                attendanceMapper.toResponse(attendance));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> getAllAttendance() {

        List<AttendanceResponse> response =
                attendanceService.getAllAttendance()
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponse>> getByStudent(
            @PathVariable Long studentId) {

        List<AttendanceResponse> response =
                attendanceService.getAttendanceByStudent(studentId)
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceResponse>> getByDate(
            @PathVariable LocalDate date) {

        List<AttendanceResponse> response =
                attendanceService.getAttendanceByDate(date)
                        .stream()
                        .map(attendanceMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRequest request) {

        Attendance attendance =
                attendanceMapper.toEntity(request);

        Attendance updatedAttendance =
                attendanceService.updateAttendance(id, attendance);

        return ResponseEntity.ok(
                attendanceMapper.toResponse(updatedAttendance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.noContent().build();
    }
}