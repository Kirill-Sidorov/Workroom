<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Full cost</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <h3>Repair: ${order.thingName}</h3>
    <h3>Master: ${order.masterName}</h3>
    <h3>Cost of work: ${order.costWork}</h3>
    <hr>
    <h3>Cost of spare parts:</h3>
    <div class="bs-component">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name</th>
                <th>Number</th>
                <th>Cost</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="part" items="${parts}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><c:out value="${part.partName}"/></td>
                    <td><c:out value="${part.numberParts}"/></td>
                    <td><c:out value="${part.cost}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <hr>
    <h3>Full cost: ${fullCost}</h3>
</div>
</body>
</html>