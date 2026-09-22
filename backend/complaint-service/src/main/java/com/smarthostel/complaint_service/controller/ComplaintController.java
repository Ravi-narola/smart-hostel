package com.smarthostel.complaint_service.controller;

import com.smarthostel.complaint_service.dto.ComplaintRequest;
import com.smarthostel.complaint_service.dto.ComplaintResponse;
import com.smarthostel.complaint_service.entity.Complaint;
import com.smarthostel.complaint_service.mapper.ComplaintMapper;
import com.smarthostel.complaint_service.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;
    private final ComplaintMapper complaintMapper;

    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody ComplaintRequest request) {

        Complaint complaint =
                complaintMapper.toEntity(request);

        Complaint savedComplaint =
                complaintService.createComplaint(
                        complaint
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        complaintMapper.toResponse(
                                savedComplaint
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                complaintMapper.toResponse(
                        complaintService.getComplaintById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponse>>
    getAllComplaints() {

        return ResponseEntity.ok(
                complaintService.getAllComplaints()
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByStudentId(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByStudentId(studentId)
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByStatus(status)
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByCategory(category)
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByPriority(
            @PathVariable String priority) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByPriority(priority)
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/assigned/{assignedTo}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByAssignedTo(
            @PathVariable String assignedTo) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByAssignedTo(assignedTo)
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}/status/{status}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByStudentAndStatus(
            @PathVariable Long studentId,
            @PathVariable String status) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByStudentAndStatus(
                                studentId,
                                status
                        )
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}/category/{category}")
    public ResponseEntity<List<ComplaintResponse>>
    getComplaintsByStudentAndCategory(
            @PathVariable Long studentId,
            @PathVariable String category) {

        return ResponseEntity.ok(
                complaintService
                        .getComplaintsByStudentAndCategory(
                                studentId,
                                category
                        )
                        .stream()
                        .map(complaintMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponse>
    updateComplaint(
            @PathVariable Long id,
            @Valid @RequestBody ComplaintRequest request) {

        Complaint complaint =
                complaintMapper.toEntity(request);

        Complaint updatedComplaint =
                complaintService.updateComplaint(
                        id,
                        complaint
                );

        return ResponseEntity.ok(
                complaintMapper.toResponse(
                        updatedComplaint
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(
            @PathVariable Long id) {

        complaintService.deleteComplaint(id);

        return ResponseEntity.noContent().build();
    }
}