package com.smarthostel.attendance_service.service;

import com.smarthostel.attendance_service.entity.Attendance;
import com.smarthostel.attendance_service.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found with id: " + id));
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    @Override
    public Attendance updateAttendance(
            Long id,
            Attendance attendance) {

        Attendance existingAttendance = getAttendanceById(id);

        existingAttendance.setStudentId(attendance.getStudentId());
        existingAttendance.setAttendanceDate(
                attendance.getAttendanceDate());
        existingAttendance.setStatus(attendance.getStatus());
        existingAttendance.setRemarks(attendance.getRemarks());

        return attendanceRepository.save(existingAttendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = getAttendanceById(id);
        attendanceRepository.delete(attendance);
    }
}