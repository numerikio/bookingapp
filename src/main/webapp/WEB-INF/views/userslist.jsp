<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link rel="shortcut icon" href="favicon.ico?ver=1" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>
    <br>
    <div class="container">
        <div class="panel panel-default">
            <sec:authorize access="hasRole('ADMIN')">
                <div class="row">
                    <div class="col-sm-6">
                        <a href="<c:url value='/newuser' />" class="btn btn-warning custom-width">new user</a>
                    </div>
                    <div class="col-sm-6 text-right">
                        <a href="<c:url value='/logout' />" class="btn btn-warning custom-width">logout</a>
                    </div>
                </div>
            </sec:authorize>

            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>name</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.name}</td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td><a href="<c:url value='/edit-user-${user.id}' />" class="btn btn-success custom-width">edit</a></td>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td><a href="<c:url value='/delete-user-${user.id}' />" class="btn btn-danger custom-width">delete</a></td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>
