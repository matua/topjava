package ru.javawebinar.topjava.repository.inmemory;

import com.sun.istack.internal.NotNull;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    private Boolean validUser(Meal meal) {
        if (meal != null) {
            return SecurityUtil.authUserId() == meal.getUserId();
        }
        return false;
    }

    @Override
    public Meal save(Meal meal) {
        if (validUser(meal)) {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // handle case: update, but not present in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        if (validUser(repository.get(id))) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id) {
        if (validUser(repository.get(id))) {
            return repository.get(id);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().stream()
                .filter(this::validUser)
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime()))
                .collect(Collectors.toList());
    }
}

