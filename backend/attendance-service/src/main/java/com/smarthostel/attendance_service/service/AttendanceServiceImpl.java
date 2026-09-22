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
    public Attendance createAttendance(
            Attendance attendance) {

        if (attendanceRepository
                .existsByStudentIdAndAttendanceDate(
                        attendance.getStudentId(),
                        attendance.getAttendanceDate())) {

            throw new RuntimeException(
                    "Attendance already exists for student "
                            + attendance.getStudentId()
                            + " on "
                            + attendance.getAttendanceDate()
            );
        }

        if (attendance.getStatus() == null ||
                attendance.getStatus().isBlank()) {

            attendance.setStatus("PRESENT");
        }

        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {

        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found with id: "
                                        + id
                        ));
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByStudentId(
            Long studentId) {

        return attendanceRepository
                .findByStudentId(studentId);
    }

    @Override
    public List<Attendance> getAttendanceByDate(
            LocalDate date) {

        return attendanceRepository
                .findByAttendanceDate(date);
    }

    @Override
    public List<Attendance>
    getAttendanceByStudentAndDateRange(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate) {

        return attendanceRepository
                .findByStudentIdAndAttendanceDateBetween(
                        studentId,
                        startDate,
                        endDate
                );
    }

    @Override
    public Attendance updateAttendance(
            Long id,
            Attendance attendance) {

        Attendance existing =
                getAttendanceById(id);

        if ((!existing.getStudentId()
                .equals(attendance.getStudentId())
                ||
                !existing.getAttendanceDate()
                        .equals(attendance.getAttendanceDate()))
                &&
                attendanceRepository
                        .existsByStudentIdAndAttendanceDate(
                                attendance.getStudentId(),
                                attendance.getAttendanceDate()
                        )) {

            throw new RuntimeException(
                    "Attendance already exists for student "
                            + attendance.getStudentId()
                            + " on "
                            + attendance.getAttendanceDate()
            );
        }

        existing.setStudentId(
                attendance.getStudentId()
        );

        existing.setAttendanceDate(
                attendance.getAttendanceDate()
        );

        existing.setStatus(
                attendance.getStatus()
        );

        existing.setRemarks(
                attendance.getRemarks()
        );

        return attendanceRepository.save(existing);
    }

    @Override
    public void deleteAttendance(Long id) {

        Attendance attendance =
                getAttendanceById(id);

        attendanceRepository.delete(attendance);
    }
}