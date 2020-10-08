<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo" scope="request"/>

    <title>Meals</title>
    <style type="text/css">
        tr.red {
            color: red;
            border-color: black
        }

        tr.green {
            color: green;
            border-color: black
        }
    </style>
</head>
<body>
<H1>Meals</H1>
<h3><a href="index.html">Home</a></h3>
<p><a href="meals?action=insert">Add Meal</a>
</p>
<table border="1" cellpadding="8" cellspacing="0" id="mealsTable">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr class="${meal.excess ? 'red' : 'green'}">
            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                           var="parsedDate" type="date" parseLocale="ru_RU"/>
            <fmt:formatDate var="formattedDate" pattern="yyyy-MM-dd HH:mm" value="${parsedDate}"/>
            <td>${formattedDate}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?mealId=${meal.id}&action=delete"><img src="<c:url value="/img/delete.png"/>"
                                                                     width="21" height="21" alt="delete"></a></td>
            <td><a href="meals?mealId=${meal.id}&action=edit"><img src="<c:url value="/img/edit.png"/>" width="21"
                                                                   height="21" alt="edit"></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>