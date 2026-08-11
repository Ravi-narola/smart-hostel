package com.smarthostel.student_service.mapper;

import com.smarthostel.student_service.dto.StudentRequest;
import com.smarthostel.student_service.dto.StudentResponse;
import com.smarthostel.student_service.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .studentId(request.getStudentId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobileNumber(request.getMobileNumber())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .course(request.getCourse())
                .semester(request.getSemester())
                .enrollmentDate(request.getEnrollmentDate())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .status(request.getStatus())
                .build();
    }

    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .studentId(student.getStudentId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .mobileNumber(student.getMobileNumber())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .course(student.getCourse())
                .semester(student.getSemester())
                .enrollmentDate(student.getEnrollmentDate())
                .address(student.getAddress())
                .city(student.getCity())
                .state(student.getState())
                .status(student.getStatus())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }

    public void updateEntity(Student student, StudentRequest request) {
        student.setStudentId(request.getStudentId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setMobileNumber(request.getMobileNumber());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setCourse(request.getCourse());
        student.setSemester(request.getSemester());
        student.setEnrollmentDate(request.getEnrollmentDate());
        student.setAddress(request.getAddress());
        student.setCity(request.getCity());
        student.setState(request.getState());
        student.setStatus(request.getStatus());
    }
}