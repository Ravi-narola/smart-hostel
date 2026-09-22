package com.smarthostel.mess_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessRequest {

    @NotBlank
    private String messName;

    @NotNull
    private LocalDate mealDate;

    private String breakfast;
    private String lunch;
    private String dinner;
    private String specialMenu;
    private String status;
}