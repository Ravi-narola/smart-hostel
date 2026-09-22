package com.smarthostel.student_service.service;

import com.smarthostel.student_service.entity.Student;
import com.smarthostel.student_service.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {

        if (studentRepository.existsByStudentId(student.getStudentId())) {
            throw new RuntimeException(
                    "Student ID already exists: " + student.getStudentId());
        }

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException(
                    "Email already exists: " + student.getEmail());
        }

        if (student.getStatus() == null || student.getStatus().isBlank()) {
            student.setStatus("ACTIVE");
        }

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        if (!existingStudent.getStudentId()
                .equals(student.getStudentId())
                && studentRepository.existsByStudentId(
                        student.getStudentId())) {

            throw new RuntimeException(
                    "Student ID already exists: "
                            + student.getStudentId());
        }

        if (!existingStudent.getEmail()
                .equalsIgnoreCase(student.getEmail())
                && studentRepository.existsByEmail(
                        student.getEmail())) {

            throw new RuntimeException(
                    "Email already exists: "
                            + student.getEmail());
        }

        existingStudent.setStudentId(student.getStudentId());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMobileNumber(student.getMobileNumber());
        existingStudent.setGender(student.getGender());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setSemester(student.getSemester());
        existingStudent.setEnrollmentDate(student.getEnrollmentDate());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setCity(student.getCity());
        existingStudent.setState(student.getState());
        existingStudent.setStatus(student.getStatus());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}