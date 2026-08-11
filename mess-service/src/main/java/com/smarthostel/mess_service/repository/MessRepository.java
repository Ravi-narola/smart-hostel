package com.smarthostel.mess_service.repository;

import com.smarthostel.mess_service.entity.Mess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessRepository extends JpaRepository<Mess, Long> {

    Optional<Mess> findByMealDate(LocalDate mealDate);

    List<Mess> findByStatus(String status);

    List<Mess> findByMealDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    boolean existsByMealDate(LocalDate mealDate);
}