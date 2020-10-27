package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.javawebinar.topjava.model.Meal.*;

@NamedQuery(name = GET, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
@NamedQuery(name = DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
@NamedQuery(name = ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
@NamedQuery(name = GET_BETWEEN_HALF_OPEN, query = "SELECT m FROM Meal m " +
        "WHERE m.user.id=:userId AND m.dateTime >= :startDateTime AND m.dateTime < :endDateTime ORDER BY m.dateTime DESC")
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity {
    public static final String GET = "Meal.get";
    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED = "Meal.getAll";
    public static final String GET_BETWEEN_HALF_OPEN = "Meal.getBetweenHalfOpen";

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Range(min = 10, max = 10000)
    @Column(name = "calories", nullable = false)
    private int calories;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
