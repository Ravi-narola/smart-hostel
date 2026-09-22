package com.smarthostel.mess_service.service;

import com.smarthostel.mess_service.entity.Mess;
import com.smarthostel.mess_service.repository.MessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessServiceImpl implements MessService {

    private final MessRepository messRepository;

    @Override
    public Mess createMess(Mess mess) {

        if (mess.getStatus() == null) {
            mess.setStatus("ACTIVE");
        }

        return messRepository.save(mess);
    }

    @Override
    public Mess getMessById(Long id) {
        return messRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Mess menu not found with id: " + id));
    }

    @Override
    public Mess getMessByDate(LocalDate mealDate) {
        return messRepository.findByMealDate(mealDate)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Mess menu not found for date: " + mealDate));
    }

    @Override
    public List<Mess> getAllMess() {
        return messRepository.findAll();
    }

    @Override
    public List<Mess> getMessByStatus(String status) {
        return messRepository.findByStatus(status);
    }

    @Override
    public List<Mess> getMessBetweenDates(
            LocalDate startDate,
            LocalDate endDate) {

        return messRepository.findByMealDateBetween(
                startDate,
                endDate
        );
    }

    @Override
    public Mess updateMess(Long id, Mess mess) {

        Mess existingMess = getMessById(id);

        existingMess.setMessName(mess.getMessName());
        existingMess.setMealDate(mess.getMealDate());
        existingMess.setBreakfast(mess.getBreakfast());
        existingMess.setLunch(mess.getLunch());
        existingMess.setDinner(mess.getDinner());
        existingMess.setSpecialMenu(mess.getSpecialMenu());
        existingMess.setStatus(mess.getStatus());

        return messRepository.save(existingMess);
    }

    @Override
    public void deleteMess(Long id) {
        Mess mess = getMessById(id);
        messRepository.delete(mess);
    }
}