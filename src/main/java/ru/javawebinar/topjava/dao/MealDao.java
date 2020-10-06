package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal getById(int uuid);

    Meal add(Meal meal);

    void delete(int mealUuid);

    Meal update(Meal meal);

    List<Meal> getAll();
}
