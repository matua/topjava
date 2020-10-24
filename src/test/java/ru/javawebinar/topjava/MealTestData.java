package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ;

    public static final Meal meal1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal meal5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal meal6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal meal7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    public static final Meal meal2_1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 12, 9, 0), "Завтрак2", 50);
    public static final Meal meal2_2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 12, 12, 0), "Обед2", 100);
    public static final Meal meal2_3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 12, 11, 0), "Ужин2", 50);
    public static final Meal meal2_4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 13, 0, 0), "Еда на граничное значение2", 10);
    public static final Meal meal2_5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 11, 0), "Завтрак2", 100);
    public static final Meal meal2_6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 15, 0), "Обед2", 50);
    public static final Meal meal2_7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 9, 0), "Ужин2", 41);

    public static final LocalDateTime duplicateDateTime = meal1.getDateTime();

    public final static List<Meal> userOneMeals = Arrays.asList(meal7, meal6, meal5, meal4, meal3, meal2, meal1);

    public static final List<Meal> filteredMealJanuary30 = Arrays.asList(meal3, meal2, meal1);
    public static final List<Meal> filteredMealJanuary31 = Arrays.asList(meal7, meal6, meal5, meal4);

    public static final Meal mealToUpdate = new Meal(LocalDateTime.of(2020, Month.JUNE, 28, 11, 0),
            "Happy Birthday Meal", 10000);

    static {
        meal1.setId(MEAL_ID);
        meal2.setId(MEAL_ID + 1);
        meal3.setId(MEAL_ID + 2);
        meal4.setId(MEAL_ID + 3);
        meal5.setId(MEAL_ID + 4);
        meal6.setId(MEAL_ID + 5);
        meal7.setId(MEAL_ID + 6);
        meal2_1.setId(MEAL_ID + 7);
        meal2_2.setId(MEAL_ID + 8);
        meal2_3.setId(MEAL_ID + 9);
        meal2_4.setId(MEAL_ID + 10);
        meal2_5.setId(MEAL_ID + 11);
        meal2_6.setId(MEAL_ID + 12);
        meal2_7.setId(MEAL_ID + 13);
        mealToUpdate.setId(MEAL_ID);
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "New food", 10);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        //usingFieldByFieldElementComparator()
    }
}