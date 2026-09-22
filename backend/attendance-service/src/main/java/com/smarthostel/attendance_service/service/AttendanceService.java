package com.smarthostel.attendance_service.service;

import com.smarthostel.attendance_service.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {

    Attendance createAttendance(Attendance attendance);

    Attendance getAttendanceById(Long id);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByStudentId(Long studentId);

    List<Attendance> getAttendanceByDate(LocalDate date);

    List<Attendance> getAttendanceByStudentAndDateRange(
            Long studentId,
            LocalDate startDate,
            LocalDate endDate
    );

    Attendance updateAttendance(
            Long id,
            Attendance attendance
    );

    void deleteAttendance(Long id);
}