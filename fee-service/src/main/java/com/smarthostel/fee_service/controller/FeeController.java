package com.smarthostel.fee_service.controller;

import com.smarthostel.fee_service.dto.FeeRequest;
import com.smarthostel.fee_service.dto.FeeResponse;
import com.smarthostel.fee_service.entity.Fee;
import com.smarthostel.fee_service.mapper.FeeMapper;
import com.smarthostel.fee_service.service.FeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;
    private final FeeMapper feeMapper;

    @PostMapping
    public ResponseEntity<FeeResponse> createFee(
            @Valid @RequestBody FeeRequest request) {

        Fee fee = feeMapper.toEntity(request);

        Fee savedFee = feeService.createFee(fee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feeMapper.toResponse(savedFee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeResponse> getFeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeMapper.toResponse(
                        feeService.getFeeById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<FeeResponse>>
    getAllFees() {

        return ResponseEntity.ok(
                feeService.getAllFees()
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FeeResponse>>
    getFeesByStudentId(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByStudentId(studentId)
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}/status/{status}")
    public ResponseEntity<List<FeeResponse>>
    getFeesByStudentAndStatus(
            @PathVariable Long studentId,
            @PathVariable String status) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByStudentAndStatus(
                                studentId,
                                status
                        )
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<FeeResponse>>
    getFeesByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByStatus(status)
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/type/{feeType}")
    public ResponseEntity<List<FeeResponse>>
    getFeesByType(
            @PathVariable String feeType) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByType(feeType)
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<FeeResponse>>
    getFeesByDueDateRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByDueDateRange(
                                startDate,
                                endDate
                        )
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/student/{studentId}/date-range")
    public ResponseEntity<List<FeeResponse>>
    getFeesByStudentAndDueDateRange(
            @PathVariable Long studentId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {

        return ResponseEntity.ok(
                feeService
                        .getFeesByStudentAndDueDateRange(
                                studentId,
                                startDate,
                                endDate
                        )
                        .stream()
                        .map(feeMapper::toResponse)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeResponse> updateFee(
            @PathVariable Long id,
            @Valid @RequestBody FeeRequest request) {

        Fee fee = feeMapper.toEntity(request);

        Fee updatedFee =
                feeService.updateFee(id, fee);

        return ResponseEntity.ok(
                feeMapper.toResponse(updatedFee)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFee(
            @PathVariable Long id) {

        feeService.deleteFee(id);

        return ResponseEntity.noContent().build();
    }
}