package com.smarthostel.mess_service.mapper;

import com.smarthostel.mess_service.dto.MessRequest;
import com.smarthostel.mess_service.dto.MessResponse;
import com.smarthostel.mess_service.entity.Mess;
import org.springframework.stereotype.Component;

@Component
public class MessMapper {

    public Mess toEntity(MessRequest request) {
        return Mess.builder()
                .messName(request.getMessName())
                .mealDate(request.getMealDate())
                .breakfast(request.getBreakfast())
                .lunch(request.getLunch())
                .dinner(request.getDinner())
                .specialMenu(request.getSpecialMenu())
                .status(request.getStatus())
                .build();
    }

    public MessResponse toResponse(Mess mess) {
        return MessResponse.builder()
                .id(mess.getId())
                .messName(mess.getMessName())
                .mealDate(mess.getMealDate())
                .breakfast(mess.getBreakfast())
                .lunch(mess.getLunch())
                .dinner(mess.getDinner())
                .specialMenu(mess.getSpecialMenu())
                .status(mess.getStatus())
                .createdAt(mess.getCreatedAt())
                .updatedAt(mess.getUpdatedAt())
                .build();
    }

    public void updateEntity(Mess mess, MessRequest request) {
        mess.setMessName(request.getMessName());
        mess.setMealDate(request.getMealDate());
        mess.setBreakfast(request.getBreakfast());
        mess.setLunch(request.getLunch());
        mess.setDinner(request.getDinner());
        mess.setSpecialMenu(request.getSpecialMenu());
        mess.setStatus(request.getStatus());
    }
}