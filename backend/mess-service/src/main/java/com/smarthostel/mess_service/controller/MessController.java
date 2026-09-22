package com.smarthostel.mess_service.controller;

import com.smarthostel.mess_service.dto.MessRequest;
import com.smarthostel.mess_service.dto.MessResponse;
import com.smarthostel.mess_service.entity.Mess;
import com.smarthostel.mess_service.mapper.MessMapper;
import com.smarthostel.mess_service.service.MessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mess")
@RequiredArgsConstructor
public class MessController {

    private final MessService messService;
    private final MessMapper messMapper;

    @PostMapping
    public ResponseEntity<MessResponse> createMess(
            @Valid @RequestBody MessRequest request) {

        Mess mess = messMapper.toEntity(request);
        Mess savedMess = messService.createMess(mess);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messMapper.toResponse(savedMess));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessResponse> getMessById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                messMapper.toResponse(
                        messService.getMessById(id)));
    }

    @GetMapping("/date/{mealDate}")
    public ResponseEntity<MessResponse> getMessByDate(
            @PathVariable LocalDate mealDate) {

        return ResponseEntity.ok(
                messMapper.toResponse(
                        messService.getMessByDate(mealDate)));
    }

    @GetMapping
    public ResponseEntity<List<MessResponse>> getAllMess() {

        return ResponseEntity.ok(
                messService.getAllMess()
                        .stream()
                        .map(messMapper::toResponse)
                        .toList());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MessResponse>> getByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                messService.getMessByStatus(status)
                        .stream()
                        .map(messMapper::toResponse)
                        .toList());
    }

    @GetMapping("/between")
    public ResponseEntity<List<MessResponse>> getBetweenDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return ResponseEntity.ok(
                messService
                        .getMessBetweenDates(startDate, endDate)
                        .stream()
                        .map(messMapper::toResponse)
                        .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessResponse> updateMess(
            @PathVariable Long id,
            @Valid @RequestBody MessRequest request) {

        Mess mess = messMapper.toEntity(request);
        Mess updatedMess = messService.updateMess(id, mess);

        return ResponseEntity.ok(
                messMapper.toResponse(updatedMess));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMess(
            @PathVariable Long id) {

        messService.deleteMess(id);

        return ResponseEntity.noContent().build();
    }
}