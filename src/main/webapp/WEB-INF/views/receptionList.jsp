<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Reception List</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="static/app_css.css" rel="stylesheet">
</head>

<body>

<a href="logout" class="btn btn-primary">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
    <c:forEach items="${users}" var="user">
    <c:if test="${user.name != 'admin'}">
        <c:if test="${user.name != 'reception'}">
        <br>
        <div class="container event-list">


                <div>
                    <h3>${user.name}</h3>
                    <h3>${user.email}</h3>
                </div>


            <table class="table table-condensed">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>dateIN</th>
                        <th>dateOUT</th>
                        <th>room</th>
                        <th>service</th>
                    </tr>
                </thead>
                <tbody>
                        <c:forEach items="${user.bookingSet}" var="booking">
                            <tr class="danger">
                                <td>${booking.id}</td>
                                <td>${booking.dateIn}</td>
                                <td>${booking.dateOut}</td>
                                <td>${booking.room.nameOfRoom}</td>
                                <td>
                                    <c:forEach items="${booking.guestServiceList}" var="guestService">
                                        ${guestService.nameService}<br>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
      </c:if>
</c:if>
    </c:forEach>
</body>

</html>