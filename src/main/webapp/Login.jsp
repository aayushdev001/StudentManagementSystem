<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Management Application - Login</title>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
            style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Student Management App </a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list"
                    class="nav-link">Go to all students</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="login" method="post">
                    <caption>
                        <h2>Login</h2>
                    </caption>

                    <fieldset class="form-group">
                        <label>Email</label> 
                        <input type="email" class="form-control"
                            name="email" required="required" autocomplete="off">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> 
                        <input type="password" class="form-control"
                            name="password" required="required" autocomplete="off">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Login</button>
                </form>

                <c:if test="${message != null}">
                    <div class="alert alert-danger mt-3">
                        <c:out value="${message}" />
                    </div>
                </c:if>

                <div class="mt-3">
                    <a href="<%=request.getContextPath()%>/register" class="btn btn-primary">Register</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
