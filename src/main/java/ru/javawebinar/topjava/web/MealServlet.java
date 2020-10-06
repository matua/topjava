package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealDao;
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

public class MealServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/meal.jsp";
    private static final String LIST_MEALS = "/WEB-INF/jsp/meals.jsp";
    private MealDao dao;

    @Override
    public void init() {
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
        String mealId = request.getParameter("mealId");

        if (dao.getById(Integer.parseInt(mealId)) == null) {
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
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
            switch (action) {
                case "delete":
                    int mealId = Integer.parseInt(request.getParameter("mealId"));
                    dao.delete(mealId);
                    request.setAttribute("meals", getFilteredMeals());
                    response.sendRedirect(request.getContextPath() + "/meals");
                    break;
                case "edit":
                    forward = INSERT_OR_EDIT;
                    mealId = Integer.parseInt(request.getParameter("mealId"));
                    Meal meal = dao.getById(mealId);
                    request.setAttribute("meal", meal);
                    forward(request, response, forward);
                    break;
                default:
                    LocalDateTime now = LocalDateTime.now();
                    request.setAttribute("now", now);
                    request.setAttribute("insert", "insert");
                    forward(request, response, INSERT_OR_EDIT);
            }
        } else {
            forward = LIST_MEALS;
            request.setAttribute("meals", getFilteredMeals());
            forward(request, response, forward);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String forward) throws
            ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}