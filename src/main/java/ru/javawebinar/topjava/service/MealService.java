package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(Integer id, int userId) {
        repository.delete(id, userId);
    }

    public Meal get(Integer id, int userId) {
        return repository.get(id, userId);
    }

    public List<Meal> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId) {
        return new ArrayList<>(repository.getFilteredByDateAll(startDate, endDate, startTime, endTime, userId));

    }

    public void update(Meal meal, int userId) {
        repository.save(meal, userId);
    }
}