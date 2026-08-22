package com.smarthostel.complaint_service.mapper;

import com.smarthostel.complaint_service.dto.ComplaintRequest;
import com.smarthostel.complaint_service.dto.ComplaintResponse;
import com.smarthostel.complaint_service.entity.Complaint;
import org.springframework.stereotype.Component;

@Component
public class ComplaintMapper {

    public Complaint toEntity(ComplaintRequest request) {

        return Complaint.builder()
                .studentId(request.getStudentId())
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .priority(request.getPriority())
                .status(request.getStatus())
                .assignedTo(request.getAssignedTo())
                .resolution(request.getResolution())
                .build();
    }

    public ComplaintResponse toResponse(Complaint complaint) {

        return ComplaintResponse.builder()
                .id(complaint.getId())
                .studentId(complaint.getStudentId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .category(complaint.getCategory())
                .priority(complaint.getPriority())
                .status(complaint.getStatus())
                .assignedTo(complaint.getAssignedTo())
                .resolution(complaint.getResolution())
                .createdAt(complaint.getCreatedAt())
                .updatedAt(complaint.getUpdatedAt())
                .resolvedAt(complaint.getResolvedAt())
                .build();
    }

    public void updateEntity(
            Complaint complaint,
            ComplaintRequest request) {

        complaint.setStudentId(request.getStudentId());
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setPriority(request.getPriority());
        complaint.setStatus(request.getStatus());
        complaint.setAssignedTo(request.getAssignedTo());
        complaint.setResolution(request.getResolution());
    }
}