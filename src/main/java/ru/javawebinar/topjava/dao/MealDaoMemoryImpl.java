package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MealDaoMemoryImpl implements MealDao {
    private static final AtomicInteger idCounter = new AtomicInteger(7);
    private final Map<Integer, Meal> meals;

    public MealDaoMemoryImpl(List<Meal> mealList) {
        this.meals = new ConcurrentHashMap<>();
        IntStream.range(0, mealList.size()).forEach(i -> meals.put(mealList.get(i).getId(), mealList.get(i)));
    }

    @Override
    public Meal getById(int mealId) {
        for (Map.Entry<Integer, Meal> entry : meals.entrySet()) {
            if (entry.getKey() == mealId) {
                return entry.getValue();
            }
        }
        return null;
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
        meals.forEach((id, m) -> {
            if (m.getId() == meal.getId()) {
                meals.put(meal.getId(), meal);
            }
        });
        return meal;
    }

    @Override
    public Map<Integer, Meal> getAll() {
        return new ConcurrentHashMap<>(meals);
    }
}