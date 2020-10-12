package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {
    public static final String MEAL_NOT_FOUND_OR_UNAUTHORIZED_USER = "Meal not found or unauthorized user";
    private final MealRepository repository;

    public MealService(@Qualifier("inMemoryMealRepository") MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        if (authorizedUserAndNotNull(id, userId)) {
            repository.delete(id);
        } else {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_UNAUTHORIZED_USER);
        }
    }

    public Meal get(int id, int userId) {
        if (authorizedUserAndNotNull(id, userId)) {
            return repository.get(id);
        } else {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_UNAUTHORIZED_USER);
        }
    }

    public List<Meal> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId) {
        return repository.getFilteredByDateAll(startDate, endDate, startTime, endTime).stream()
                .filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toList());

    }

    public void update(Meal meal, int userId) {
        if (authorizedUserAndNotNull(meal.getId(), userId)) {
            repository.save(meal, userId);
        } else {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_UNAUTHORIZED_USER);
        }
    }

    private boolean authorizedUserAndNotNull(int id, int userId) {
        return repository.get(id).getUserId() == userId || repository.get(id) == null;
    }
}