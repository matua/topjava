<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Add/Update Meal</title>
</head>
<body>
<H1>Add/Update Meal</H1>

<form method="POST" action='meals' name="frmAddMeal">
    <p>Meal UUID : ${meal.uuid}</p><br/>
    <input name="mealUuid" type="hidden" value="${meal.uuid}">
    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm"
                   var="parsedDate"/>
    <fmt:formatDate var="formattedDate" pattern="yyyy-MM-dd'T'HH:mm" value="${parsedDate}"/>

    <p>DateTime : <label>
        <input type="datetime-local" name="DateTime" value="${formattedDate}"/>
    </label></p> <br/>
    <p>Description : <input
            type="text" name="Description"
            value="<c:out value="${meal.description}" />"/></p> <br/>
    <p>Calories : <input
            type="number" min="0" max="10000" name="Calories"
            value="<c:out value="${meal.calories}" />"/></p> <br/>

    <input type="submit" value="Submit"/>
    <%--    <p></p><a href="meals?action=all">Cancel</a></p>--%>

    <input type="button" name="cancel" value="cancel" onClick="window.location.href='meals';"/>
</form>
</body>
</html>