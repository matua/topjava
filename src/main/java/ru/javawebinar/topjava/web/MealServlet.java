package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/meal.jsp";
    private static final String LIST_MEALS = "/WEB-INF/jsp/meals.jsp";
    private MealDaoMemoryImpl dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = new MealDaoMemoryImpl(MealsUtil.getMeals());
    }

    private List<MealTo> getFilteredMeals() {
        return MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(request.getParameter("DateTime")));
        meal.setDescription(request.getParameter("Description"));
        meal.setCalories(Integer.parseInt(request.getParameter("Calories")));

        String mealUuid = request.getParameter("mealUuid");
        if (mealUuid == null || mealUuid.isEmpty()) {
            dao.add(meal);
        } else {
            meal.setUuid(UUID.fromString(mealUuid));
            dao.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        request.setAttribute("meals", getFilteredMeals());
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                UUID mealUuid = UUID.fromString(request.getParameter("mealUuid"));
                dao.delete(mealUuid);
                request.setAttribute("meals", getFilteredMeals());
                response.sendRedirect(request.getContextPath() + "/meals");
            } else if (action.equalsIgnoreCase("edit")) {
                forward = INSERT_OR_EDIT;
                UUID mealUuid = UUID.fromString(request.getParameter("mealUuid"));
                Meal meal = dao.getById(mealUuid);
                request.setAttribute("meal", meal);
                forward(request, response, forward);
            } else if (action.equalsIgnoreCase("insert"))
                forward(request, response, INSERT_OR_EDIT);
        } else {
            forward = LIST_MEALS;
            request.setAttribute("meals", getFilteredMeals());
            forward(request, response, forward);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String forward) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
