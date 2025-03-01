<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Customer</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1>Welcome, ${user.firstName}!</h1>
        </div>
        <div class="col-md-6">
            <a href="workroom?command=logout" class="btn btn-default pull-right">Logout</a>
        </div>
    </div>
    <hr>
    <a href="workroom?command=show_order_creation_page" class="btn btn-primary btn-lg">Create a new order</a>
    <hr>
    <h3>Orders:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name of the thing</th>
                <th>Client</th>
                <th>Master</th>
                <th>Order status</th>
                <th>Full cost</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td><c:out value="${order.masterName}"/></td>
                    <td><c:out value="${order.orderStatus.name}"/></td>
                    <td><a href="workroom?command=show_full_cost_page&id=${order.id}" title="Full cost">
                            <img src="img/cost.png">
                        </a>
                    </td>
                    <td>
                        <a href="workroom?command=show_order_editor_page&id=${order.id}" title="Edit order">
                            <img src="img/edit.png">
                        </a>
                        <c:if test="${order.orderStatus!='REPAIR_PROCESS'&&order.orderStatus!='COMPLETED'}">
                            <a href="workroom?command=cancel_order&id=${order.id}" title="Cancel order">
                                <img src="img/cancel.png">
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
</div>
</body>
</html>