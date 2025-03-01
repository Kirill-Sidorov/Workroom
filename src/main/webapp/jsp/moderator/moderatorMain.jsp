<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Moderator</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <h1>Welcome, moderator ${user.firstName}!</h1>
        </div>
        <div class="col-md-3">
            <a href="workroom?command=logout" class="btn btn-default pull-right">Logout</a>
        </div>
    </div>

    <h3>New orders pending verification:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Thing name</th>
                <th>Description</th>
                <th>Client</th>
                <th>Change</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${pendingOrders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><textarea class="form-control" rows="5" readonly><c:out value="${order.description}"/></textarea></td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td>
                        <a href="workroom?command=show_order_editor_page&id=${order.id}" title="Edit order"><img
                                src="img/edit.png"></a>
                        <a href="workroom?command=check_order&id=${order.id}" title="Accept order"><img
                                src="img/accept.png"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>

    <h3>Orders to remove:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Thing name</th>
                <th>Client</th>
                <th>Master</th>
                <th>Order status</th>
                <th>Change</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${canceledOrders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td><c:out value="${order.masterName}"/></td>
                    <td><c:out value="${order.orderStatus.name}"/></td>
                    <td>
                        <a href="workroom?command=delete_order&id=${order.id}" title="Delete order">
                            <img src="img/delete.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>

    <h3>Blocking users:</h3>
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
                <th>Change</th>
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
                        <c:if test="${user.userType!='ADMIN'&&user.userType!='MODERATOR'}">
                            <a href="workroom?command=change_user_status&id=${user.id}&status=${user.status}" title="Block/Unblock">
                                <img src="img/lock.png">
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>