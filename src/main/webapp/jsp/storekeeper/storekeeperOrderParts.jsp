<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Order</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="registrationForm" method="POST" action="workroom">
                <fieldset>
                    <input type="hidden" name="command" value="confirm_replacement_parts"/>
                    <input type="hidden" name="orderId" value="${order.id}"/>
                    <h2>Order</h2>
                    <hr>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Thing name</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" value="${order.thingName}" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Master</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" value="${order.masterName}" readonly>
                        </div>
                    </div>

                    <hr>

                    <h3>Spare parts table</h3>
                    <div class="bs-component">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>№</th>
                                <th>Part name</th>
                                <th>Required quantity</th>
                                <th>Quantity in stock</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="part" items="${replacementParts}" varStatus="status">
                                <tr
                                        <c:choose>
                                            <c:when test="${part.numberParts <= part.inStock}">
                                                class="success"
                                            </c:when>
                                            <c:otherwise>
                                                class="danger"
                                            </c:otherwise>
                                        </c:choose>>
                                    <td><c:out value="${status.count}"/></td>
                                    <td><c:out value="${part.partName}"/></td>
                                    <td><c:out value="${part.numberParts}"/></td>
                                    <td><c:out value="${part.inStock}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <hr>

                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary center-block"
                                    <c:if test="${isAllowedConfirm}">disabled</c:if>>Confirm availability of spare parts
                            </button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>