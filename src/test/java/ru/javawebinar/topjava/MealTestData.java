package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ;
    public static final int NOT_FOUND = 10;

    public static final Meal meal = new Meal(LocalDateTime.now(), "Some food", 500);

    public static final List<Meal> meals = MealsUtil.meals;

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "New food", 10);
    }

    public static Meal getUpdated() {
        Meal updated = meal;
        updated.setDescription("Updated Food Description");
        updated.setDateTime(LocalDateTime.of(2020, Month.JUNE, 28, 11, 0));
        return updated;
    }

    public static List<Meal> getFilteredMeals() {
        Meal meal1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                "Завтрак", 500);
        meal1.setId(100_000);
        Meal meal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0),
                "Обед", 1000);
        meal2.setId(100_001);
        Meal meal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0),
                "Ужин", 500);
        meal3.setId(100_002);
        Meal meal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0),
                "Еда на граничное значение", 100);
        meal4.setId(100_003);
        return new ArrayList<Meal>() {{
            add(meal1);
            add(meal2);
            add(meal3);
            add(meal4);
        }};
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}