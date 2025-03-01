<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Master</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <h1>Welcome, master ${user.firstName}!</h1>
        </div>
        <div class="col-md-3">
            <a href="workroom?command=logout" class="btn btn-default pull-right">Logout</a>
        </div>
    </div>
    <h3>Available orders:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name of the thing</th>
                <th>Description</th>
                <th>Client</th>
                <th>Accept an order</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${availableOrders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td>
                        <textarea class="form-control" rows="5" readonly><c:out value="${order.description}"/></textarea>
                    </td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td>
                        <a href="workroom?command=take_order&id=${order.id}" title="Accept an order">
                            <img src="img/accept.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <h3>Unfinished orders:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name of the thing</th>
                <th>Client</th>
                <th>Order status</th>
                <th>Cost of work</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${uncompletedOrders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td><c:out value="${order.orderStatus.name}"/></td>
                    <td><c:out value="${order.costWork}"/></td>
                    <td>
                        <a href="workroom?command=show_master_order_editor_page&id=${order.id}" title="Edit order">
                            <img src="img/edit.png">
                        </a>
                        <c:if test="${order.orderStatus=='REPAIR_PROCESS'}">
                            <a href="workroom?command=confirm_order_execution&id=${order.id}"
                               title="Complete the order">
                                <img src="img/accept.png">
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <h3>Completed orders:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name of the thing</th>
                <th>Client</th>
                <th>Order status</th>
                <th>Cost of work</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${completedOrders}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><c:out value="${order.customerName}"/></td>
                    <td><c:out value="${order.orderStatus.name}"/></td>
                    <td><c:out value="${order.costWork}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>