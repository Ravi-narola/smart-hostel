package com.smarthostel.student_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
    private LocalDate dateOfBirth;
    private String course;
    private Integer semester;
    private LocalDate enrollmentDate;
    private String address;
    private String city;
    private String state;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}