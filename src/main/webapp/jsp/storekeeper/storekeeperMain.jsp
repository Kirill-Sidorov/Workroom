<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Storekeeper</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-9">
            <h1>Welcome, storekeeper ${user.firstName}!</h1>
        </div>
        <div class="col-md-3">
            <a href="workroom?command=logout" class="btn btn-default pull-right">Logout</a>
        </div>
    </div>
    <hr>
    <a href="workroom?command=show_replacement_part_creation_page" class="btn btn-primary btn-lg">Add new part</a>
    <h3>Spare parts:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Part name</th>
                <th>Cost</th>
                <th>Quantity in stock</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="part" items="${replacementParts}" varStatus="status">
                <tr <c:if test="${part.inStock <= 1}">class="danger"</c:if>>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${part.name}"/></td>
                    <td><c:out value="${part.cost}"/></td>
                    <td><c:out value="${part.inStock}"/></td>
                    <td>
                        <a href="workroom?command=show_replacement_part_editor_page&id=${part.id}" title="Edit">
                            <img src="img/edit.png">
                        </a>
                        <a href="workroom?command=delete_replacement_part&id=${part.id}" title="Delete">
                            <img src="img/delete.png">
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <h3>Orders awaiting spare parts:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Thing name</th>
                <th>Master</th>
                <th>Order status</th>
                <th>Cost of work</th>
                <th>Check availability of spare parts</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderWaitParts}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${order.thingName}"/></td>
                    <td><c:out value="${order.masterName}"/></td>
                    <td><c:out value="${order.orderStatus.name}"/></td>
                    <td><c:out value="${order.costWork}"/></td>
                    <td>
                        <a href="workroom?command=show_order_parts_page&id=${order.id}" title="Check availability of spare parts">
                            <img src="img/search.png">
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