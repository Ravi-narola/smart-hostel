package com.smarthostel.complaint_service.repository;

import com.smarthostel.complaint_service.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudentId(Long studentId);

    List<Complaint> findByStudentIdAndStatus(
            Long studentId,
            String status
    );

    List<Complaint> findByStatus(String status);

    List<Complaint> findByCategory(String category);

    List<Complaint> findByPriority(String priority);

    List<Complaint> findByAssignedTo(String assignedTo);
}