package ru.javawebinar.topjava.model;

import java.util.ArrayList;
import java.util.List;

public class MealDao {
    private List<Meal> meals = new ArrayList<>();

    public MealDao(List<Meal> meals) {
        this.meals = meals;
    }

    public void addMeal(Meal meal) {

    }
}
