<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Admin</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <h1>Welcome, administrator ${user.firstName}!</h1>
        </div>
        <div class="col-md-3">
            <a href="workroom?command=logout" class="btn btn-default pull-right">Logout</a>
        </div>
    </div>
    <a href="workroom?command=show_registration_page" class="btn btn-primary btn-lg">User registration</a>
    <hr>
    <h3>Users:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Login</th>
                <th>Group</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Authorized</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${unlockedUsers}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.userType}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.status}"/></td>
                    <td><c:out value="${user.authorized}"/></td>
                    <td>
                        <a href="workroom?command=show_user_editor_page&id=${user.id}" title="Edit">
                            <img src="img/edit.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <h3>Blocked users:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Login</th>
                <th>Group</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Authorized</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${lockedUsers}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.userType}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.status}"/></td>
                    <td><c:out value="${user.authorized}"/></td>
                    <td>
                        <a href="workroom?command=show_user_editor_page&id=${user.id}" title="Edit">
                            <img src="img/edit.png">
                        </a>
                        <a href="workroom?command=delete_user&id=${user.id}" title="Delete">
                            <img src="img/delete.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>