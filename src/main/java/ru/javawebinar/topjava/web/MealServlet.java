package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;

public class MealServlet extends HttpServlet {
    public static final int CALORIES_PER_DAY = 2000;
    private static final Logger logger = LoggerFactory.getLogger(MealServlet.class);
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/WEB-INF/jsp/meal.jsp";
    private static final String LIST_MEALS = "/WEB-INF/jsp/meals.jsp";
    private MealDao dao;

    @Override
    public void init() {
        dao = new MealDaoMemoryImpl(MealsUtil.meals);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(request.getParameter("DateTime")))
                .setDescription(request.getParameter("Description"))
                .setCalories(Integer.parseInt(request.getParameter("Calories")));

        String mealId = request.getParameter("mealId");
        int mealIdParsed = Integer.parseInt(mealId);

        if (mealIdParsed == 0) {
            dao.add(meal);
        } else {
            meal.setId(mealIdParsed);
            dao.update(meal);
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        response.sendRedirect(request.getContextPath() + "/meals");
        logger.debug("Redirection to meals");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    int mealId = Integer.parseInt(request.getParameter("mealId"));
                    dao.delete(mealId);
                    request.setAttribute("meals", getMealsTo());
                    response.sendRedirect(request.getContextPath() + "/meals");
                    logger.debug("Redirection to meals");
                    break;
                case "edit":
                    forward = INSERT_OR_EDIT;
                    mealId = Integer.parseInt(request.getParameter("mealId"));
                    Meal meal = dao.getById(mealId);
                    request.setAttribute("meal", meal);
                    forward(request, response, forward);
                    logger.debug("Forwarding to meal.jsp");
                    break;
                case "insert":
                    LocalDateTime now = LocalDateTime.now();
                    request.setAttribute("now", now);
                    forward(request, response, INSERT_OR_EDIT);
                    logger.debug("Forwarding to meal.jsp");
                default:
                    response.sendRedirect(request.getContextPath() + "/meals");
                    logger.debug("Redirection to meals");
            }
        } else {
            forward = LIST_MEALS;
            request.setAttribute("meals", getMealsTo());
            forward(request, response, forward);
            logger.debug("Forwarding to meals.jsp");
        }
    }

    private List<MealTo> getMealsTo() {
        return MealsUtil.getMealsTo(dao.getAll(), CALORIES_PER_DAY);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String forward) throws
            ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}