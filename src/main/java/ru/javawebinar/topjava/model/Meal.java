package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.id = idCounter.incrementAndGet();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal() {
        this.id = idCounter.incrementAndGet();

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Meal setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Meal setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCalories() {
        return calories;
    }

    public Meal setCalories(int calories) {
        this.calories = calories;
        return this;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public int getId() {
        return id;
    }

    public Meal setId(int id) {
        this.id = id;
        return this;
    }
}
