package com.smarthostel.mess_service.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessResponse {

    private Long id;
    private String messName;
    private LocalDate mealDate;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String specialMenu;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}