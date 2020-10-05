package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MealDaoMemoryImpl implements MealDao {
    private final List<Meal> meals;

    public MealDaoMemoryImpl(List<Meal> meals) {
        this.meals = new ArrayList<>(meals);
    }

    @Override
    public Meal getById(UUID mealUuid) {
        return meals.stream()
                .filter(meal -> meal.getUuid().equals(mealUuid))
                .findAny()
                .orElse(null);
    }

    @Override
    public void add(Meal meal) {
        if (getById(meal.getUuid()) == null) {
            meals.add(meal);
        }
    }

    @Override
    public void delete(UUID mealUuid) {
        meals.removeIf(meal -> meal.getUuid().equals(mealUuid));
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getUuid());
        add(meal);
    }

    @Override
    public List<Meal> getAll() {
        Collections.sort(meals);
        return meals;
    }
}
