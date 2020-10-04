<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo" scope="request"/>
    <title>AddMeal</title>
    <style type="text/css">
        #mealsTable td.red {
            color: red;
            border-color: black
        }

        #mealsTable td.green {
            color: green;
            border-color: black
        }
    </style>
</head>
<body>
<H1>Meals</H1>
<p><a href="addMeal">Add Meal</a></p>
<section>
    <table border="1" cellpadding="8" cellspacing="0" id="mealsTable">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
        <c:forEach var="meal" items="${meals}">
            <tr>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                               var="parsedDate" type="date"/>
                <fmt:formatDate var="formattedDate" pattern="dd MMM yyyy HH:mm" value="${parsedDate}"/>
                <c:choose>
                    <c:when test="${meal.excess}">
                        <c:set var="text_color" value="red"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="text_color" value="green"/>
                    </c:otherwise>
                </c:choose>
                <td class="<c:out value = "${text_color}"/>">${formattedDate}</td>
                <td class="<c:out value = "${text_color}"/>">${meal.description}</td>
                <td class="<c:out value = "${text_color}"/>">${meal.calories}</td>
                <td><a href="meals?uuid=${meal.uuid}&action=delete"><img src="img/delete.png" alt="delete"></a></td>
                <td><a href="meals?uuid=${meal.uuid}&action=edit"><img src="img/pencil.png" alt="edit"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>