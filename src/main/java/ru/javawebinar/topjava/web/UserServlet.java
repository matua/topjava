package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        if (user != null) {
            SecurityUtil.setAuthUserId(Integer.parseInt(user));
        }
        log.info("Setting user {}", user);
        response.sendRedirect("meals");
    }
}