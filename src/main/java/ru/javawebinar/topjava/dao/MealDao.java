package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.UUID;

public interface MealDao {
    Meal getById(UUID uuid);

    void add(Meal meal);

    void delete(UUID mealUuid);

    void update(Meal meal);

    List<Meal> getAll();
}
