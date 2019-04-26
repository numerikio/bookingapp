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


 <a href="<c:url value='/' />" class="btn btn-warning custom-width">main page</a>
 <div class="container">
    <table class="table table-condensed">
        <thead>
            <tr>
                <th>booking ID</th>
                <th>date IN</th>
                <th>date OUT</th>
                <th>room name</th>
                <th>category</th>
                <th>total</th>
                <th>service<th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${bookedList}" var="booked">
                <tr class="danger">
                    <td>${booked.id}</td>
                    <td>${booked.dateIn}</td>
                    <td>${booked.dateOut}</td>
                    <td>${booked.room.nameOfRoom}</td>
                    <td>${booked.room.category}</td>
                    <td>${booked.totalPrice}</td>
                    <td><a href="<c:url value='servPage?booked=${booked.id}' />" class="btn btn-success custom-width">serv</a></td>
                    <td><c:forEach items="${booked.guestServiceList}" var="guestService">
                    ${guestService.nameService}<br>
                    </c:forEach></td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    </div>
</body>
</html>