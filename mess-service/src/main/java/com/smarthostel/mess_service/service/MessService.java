package com.smarthostel.mess_service.service;

import com.smarthostel.mess_service.entity.Mess;

import java.time.LocalDate;
import java.util.List;

public interface MessService {

    Mess createMess(Mess mess);

    Mess getMessById(Long id);

    Mess getMessByDate(LocalDate mealDate);

    List<Mess> getAllMess();

    List<Mess> getMessByStatus(String status);

    List<Mess> getMessBetweenDates(
            LocalDate startDate,
            LocalDate endDate
    );

    Mess updateMess(Long id, Mess mess);

    void deleteMess(Long id);
}