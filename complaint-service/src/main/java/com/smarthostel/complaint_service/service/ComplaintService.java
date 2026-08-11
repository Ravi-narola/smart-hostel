package com.smarthostel.complaint_service.service;

import com.smarthostel.complaint_service.entity.Complaint;

import java.util.List;

public interface ComplaintService {

    Complaint createComplaint(Complaint complaint);

    Complaint getComplaintById(Long id);

    List<Complaint> getAllComplaints();

    List<Complaint> getComplaintsByStudent(Long studentId);

    List<Complaint> getComplaintsByStatus(String status);

    List<Complaint> getComplaintsByCategory(String category);

    List<Complaint> getComplaintsByPriority(String priority);

    List<Complaint> getComplaintsByAssignedTo(String assignedTo);

    Complaint updateComplaint(Long id, Complaint complaint);

    void deleteComplaint(Long id);
}