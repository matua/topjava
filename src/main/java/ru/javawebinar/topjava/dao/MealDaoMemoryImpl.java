package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.LinkedList;
import java.util.List;

public class MealDaoMemoryImpl implements MealDao {
    private final List<Meal> meals;

    public MealDaoMemoryImpl(List<Meal> meals) {
        this.meals = new LinkedList<>(meals);
    }

    @Override
    public Meal getById(int mealId) {
        return meals.stream()
                .filter(meal -> meal.getId() == mealId)
                .findAny()
                .orElse(null);
    }

    @Override
    public Meal add(Meal meal) {
        if (getById(meal.getId()) == null) {
            meals.add(meal);
        }
        return meal;
    }

    @Override
    public void delete(int mealId) {
        meals.removeIf(meal -> meal.getId() == mealId);
    }

    @Override
    public Meal update(Meal meal) {
        for (int i = 0; i < meals.size(); i++) {
            Meal m = meals.get(i);
            if (m.getId() == meal.getId()) {
                meals.set(i, meal);
                return meals.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }
}
