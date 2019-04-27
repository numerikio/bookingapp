<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <a href="<c:url value='/login' />" class="btn btn-warning custom-width">log in</a>
        <sec:authorize access="hasRole('USER')">
        <a href="<c:url value='bookingPage' />" class="btn btn-warning custom-width">Booking Page
            <c:choose>
               <c:when test="${coint == '0'}">
               </c:when>
               <c:otherwise>
                  <span class="badge">${coint}</span>
               </c:otherwise>
            </c:choose>
        </a>
        <a href="logout" class="btn btn-primary">${loggedinuser} <i class="glyphicon glyphicon-log-out"></i></a>
    </sec:authorize>
    </div>
    <br>
<div class="container">
    <form action="find">
    <div>
        <%@include file="calendar.jsp" %>
    </div>
        <br>
        <c:forEach items="${categories}" var="categor">
            <input type="radio" name="categor" id="${categor}" value="${categor}">${categor}<br>
        </c:forEach>
        <br>
        <div>
        <button type="submit" class="btn-lg btn-primary"><i class="glyphicon glyphicon-search"></i></button>
        </div>
        <br>
    </form>
</div>
<div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th width="10%">name</th>
                <th width="70%">description</th>
                <th width="10%">category</th>
                <th width="10%">cost</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach items="${rooms}" var="room">
                <tr class="success">
                    <td vertical-align="middle">${room.nameOfRoom}</td>
                    <td>${room.description}</td>
                    <td>${room.category}</td>
                    <td>${room.cost}</td>
                    <c:if test="${found}">
                    <td><c:choose>
                        <c:when test="${days == '0'|| empty days}">
                            <b>${room.cost}</b>
                        </c:when>
                        <c:otherwise>
                           <b>${room.cost*days}</b>
                        </c:otherwise>
                    </c:choose></td>
                    <sec:authorize access="hasRole('USER')">
                        <td><button type="button" class="btn btn-success" onclick="tt(${room.id})">buy now</button></td>
                    </sec:authorize>
                    </c:if>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    </div>

    <sec:authorize access="hasRole('ADMIN')">
        <script language="javascript" type="text/javascript">
            window.location = window.location.href = 'userslist';
        </script>
    </sec:authorize>

    <sec:authorize access="hasRole('RECEPTION')">
        <script language="javascript" type="text/javascript">
            window.location = window.location.href = 'reception';
        </script>
    </sec:authorize>

    <script>
    document.getElementById('date').value = '${dateString}';
        function tt(roomId) {
            document.location.href = 'bookingRoom?date=' + document.getElementById('date').value + '&roomId=' + roomId;
        }
        function setAll() {
          var c = document.getElementById('All');
          if (!c.checked) c.checked = true;
        }
        setAll();
    </script>
</body>
</html>