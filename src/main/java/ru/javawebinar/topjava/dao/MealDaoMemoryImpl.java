package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMemoryImpl implements MealDao {
    private static final AtomicInteger idCounter = new AtomicInteger(7);
    private final Map<Integer, Meal> meals;

    public MealDaoMemoryImpl(List<Meal> mealList) {
        this.meals = new ConcurrentHashMap<>();
        mealList.forEach(this::add);
    }

    @Override
    public Meal getById(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public Meal add(Meal meal) {
        int id = idCounter.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
        return meal;
    }

    @Override
    public void delete(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public Meal update(Meal meal) {
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}