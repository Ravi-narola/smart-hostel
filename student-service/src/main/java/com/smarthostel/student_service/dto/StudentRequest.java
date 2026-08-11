package com.smarthostel.student_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {

    @NotBlank
    private String studentId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
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
}