package com.smarthostel.complaint_service.service;

import com.smarthostel.complaint_service.entity.Complaint;
import com.smarthostel.complaint_service.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {

        if (complaint.getPriority() == null ||
                complaint.getPriority().isBlank()) {

            complaint.setPriority("MEDIUM");
        }

        if (complaint.getStatus() == null ||
                complaint.getStatus().isBlank()) {

            complaint.setStatus("OPEN");
        }

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {

        return complaintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Complaint not found with id: " + id
                        ));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsByStudentId(
            Long studentId) {

        return complaintRepository.findByStudentId(studentId);
    }

    @Override
    public List<Complaint> getComplaintsByStatus(
            String status) {

        return complaintRepository.findByStatus(status);
    }

    @Override
    public List<Complaint> getComplaintsByCategory(
            String category) {

        return complaintRepository.findByCategory(category);
    }

    @Override
    public List<Complaint> getComplaintsByPriority(
            String priority) {

        return complaintRepository.findByPriority(priority);
    }

    @Override
    public List<Complaint> getComplaintsByAssignedTo(
            String assignedTo) {

        return complaintRepository.findByAssignedTo(assignedTo);
    }

    @Override
    public List<Complaint> getComplaintsByStudentAndStatus(
            Long studentId,
            String status) {

        return complaintRepository
                .findByStudentIdAndStatus(
                        studentId,
                        status
                );
    }

    @Override
    public List<Complaint> getComplaintsByStudentAndCategory(
            Long studentId,
            String category) {

        return complaintRepository
                .findByStudentIdAndCategory(
                        studentId,
                        category
                );
    }

    @Override
    public Complaint updateComplaint(
            Long id,
            Complaint complaint) {

        Complaint existingComplaint =
                getComplaintById(id);

        existingComplaint.setStudentId(
                complaint.getStudentId()
        );

        existingComplaint.setTitle(
                complaint.getTitle()
        );

        existingComplaint.setDescription(
                complaint.getDescription()
        );

        existingComplaint.setCategory(
                complaint.getCategory()
        );

        existingComplaint.setPriority(
                complaint.getPriority()
        );

        existingComplaint.setStatus(
                complaint.getStatus()
        );

        existingComplaint.setAssignedTo(
                complaint.getAssignedTo()
        );

        existingComplaint.setResolution(
                complaint.getResolution()
        );

        return complaintRepository.save(
                existingComplaint
        );
    }

    @Override
    public void deleteComplaint(Long id) {

        Complaint complaint =
                getComplaintById(id);

        complaintRepository.delete(complaint);
    }
}