package com.smarthostel.leave_service.controller;

import com.smarthostel.leave_service.dto.LeaveRequest;
import com.smarthostel.leave_service.dto.LeaveResponse;
import com.smarthostel.leave_service.entity.Leave;
import com.smarthostel.leave_service.mapper.LeaveMapper;
import com.smarthostel.leave_service.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;
    private final LeaveMapper leaveMapper;

    @PostMapping
    public ResponseEntity<LeaveResponse> createLeave(
            @Valid @RequestBody LeaveRequest request) {

        Leave leave = leaveMapper.toEntity(request);
        Leave savedLeave = leaveService.createLeave(leave);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(leaveMapper.toResponse(savedLeave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponse> getLeaveById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                leaveMapper.toResponse(
                        leaveService.getLeaveById(id)));
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponse>> getAllLeaves() {

        return ResponseEntity.ok(
                leaveService.getAllLeaves()
                        .stream()
                        .map(leaveMapper::toResponse)
                        .toList());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<LeaveResponse>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                leaveService.getLeavesByStudent(studentId)
                        .stream()
                        .map(leaveMapper::toResponse)
                        .toList());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveResponse>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                leaveService.getLeavesByStatus(status)
                        .stream()
                        .map(leaveMapper::toResponse)
                        .toList());
    }

    @GetMapping("/type/{leaveType}")
    public ResponseEntity<List<LeaveResponse>> getByType(
            @PathVariable String leaveType) {

        return ResponseEntity.ok(
                leaveService.getLeavesByType(leaveType)
                        .stream()
                        .map(leaveMapper::toResponse)
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponse> updateLeave(
            @PathVariable Long id,
            @Valid @RequestBody LeaveRequest request) {

        Leave leave = leaveMapper.toEntity(request);
        Leave updatedLeave = leaveService.updateLeave(id, leave);

        return ResponseEntity.ok(
                leaveMapper.toResponse(updatedLeave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(
            @PathVariable Long id) {

        leaveService.deleteLeave(id);

        return ResponseEntity.noContent().build();
    }
}