package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    public static final String MEAL_NOT_FOUND_OR_BELONGS_TO_A_DIFFERENT_USER = "Meal not found or belongs to a different user";
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<Meal> getAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAll");
        return new ArrayList<>(service.getAll(startDate, endDate, startTime, endTime, authUserId()));
    }

    public List<MealTo> getAllTos(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        log.info("getAllTos");
        return MealsUtil.getTos(getAll(startDate, endDate, startTime, endTime), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        Meal meal = service.get(id, authUserId());
        if (meal == null) {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_BELONGS_TO_A_DIFFERENT_USER);
        }
        return meal;
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkMeal(id);
        service.delete(id, authUserId());
    }

    private void checkMeal(int id) {
        Meal meal = service.get(id, authUserId());
        if (meal == null) {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_BELONGS_TO_A_DIFFERENT_USER);
        }
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        Meal mealToCheck = service.get(meal.getId(), authUserId());
        if (mealToCheck == null) {
            throw new NotFoundException(MEAL_NOT_FOUND_OR_BELONGS_TO_A_DIFFERENT_USER);
        }
        service.update(meal, authUserId());
    }
}