package com.smarthostel.student_service.controller;

import com.smarthostel.student_service.dto.StudentRequest;
import com.smarthostel.student_service.dto.StudentResponse;
import com.smarthostel.student_service.entity.Student;
import com.smarthostel.student_service.mapper.StudentMapper;
import com.smarthostel.student_service.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(
            @Valid @RequestBody StudentRequest request) {

        Student student = studentMapper.toEntity(request);

        Student savedStudent =
                studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentMapper.toResponse(savedStudent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable Long id) {

        Student student =
                studentService.getStudentById(id);

        return ResponseEntity.ok(
                studentMapper.toResponse(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {

        List<StudentResponse> students =
                studentService.getAllStudents()
                        .stream()
                        .map(studentMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest request) {

        Student student =
                studentMapper.toEntity(request);

        Student updatedStudent =
                studentService.updateStudent(id, student);

        return ResponseEntity.ok(
                studentMapper.toResponse(updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}