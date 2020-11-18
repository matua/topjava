package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

/*
Generally, I prefer to only use @RequestMapping at the class level to specify the base
path. I use the more specific @GetMapping, @PostMapping, and so on, on each of
the handler methods. (Spring in Action)
*/
@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {
    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping()
    public String showAllMeals(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("delete")
    public String delete(@RequestParam(name = "id") Integer id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "mealForm";
    }

    @GetMapping("update")
    public String update(
            Model model,
            @RequestParam Integer id
    ) {
        model.addAttribute("meal", super.get(id));
        return "mealForm";
    }

    @GetMapping("filter")
    public String filter(
            Model model,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String startTime,
            @RequestParam String endTime
    ) {
        model.addAttribute("meals", super.getBetween(parseLocalDate(startDate), parseLocalTime(startTime), parseLocalDate(endDate), parseLocalTime(endTime)));
        return "meals";
    }

    @PostMapping()
    public String processCreateUpdateForm(
            @RequestParam String dateTime,
            @RequestParam String description,
            @RequestParam Integer calories,
            @RequestParam(name = "id", required = false) Integer id
    ) {
        Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                calories);

        if (id == null) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
        return "redirect:/meals";
    }
}