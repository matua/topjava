package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

public class Meal implements Comparable<Meal> {
    private UUID uuid;
    private LocalDateTime dateTime;

    private String description;

    private int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.uuid = UUID.randomUUID();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal() {
        this.uuid = UUID.randomUUID();
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

    public UUID getUuid() {
        return uuid;
    }

    public Meal setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (calories != meal.calories) return false;
        if (!Objects.equals(uuid, meal.uuid)) return false;
        if (!Objects.equals(dateTime, meal.dateTime)) return false;
        return Objects.equals(description, meal.description);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + calories;
        return result;
    }

    @Override
    public int compareTo(Meal o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
