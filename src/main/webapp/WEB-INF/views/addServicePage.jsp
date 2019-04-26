<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <a href="<c:url value='/' />" class="btn btn-warning custom-width">home</a>
    <form action="addServ">
        <c:forEach items="${guestServices}" var="guestService">
            <input type="checkbox" name="param" value="${guestService.nameService}">${guestService.nameService} ${guestService.cost} ${guestService.regularity}    <b>total:
            <c:if test="${guestService.regularity eq 'all'}">
                ${guestService.cost*days}
       </c:if>
            <c:if test="${guestService.regularity eq 'one'}">
           ${guestService.cost}
           </c:if></b><Br>
        </c:forEach>
        <input type="hidden" name="booked" value="${booked}">
        <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-ok"></i></button>

    </form>
</body>

</html>