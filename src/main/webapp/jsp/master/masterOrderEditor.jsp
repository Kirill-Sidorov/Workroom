<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Order</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<script>
    function setInputEnabled(id) {
        const checkBox = document.getElementById(id);
        const inputNumber = document.getElementById(id + "Input");
        const row = document.getElementById(id + "Row");
        if (checkBox.checked === true) {
            inputNumber.disabled = false;
            row.className = "success";
            inputNumber.value = 1;
        } else {
            inputNumber.disabled = true;
            row.className = "";
            inputNumber.value = 0;
        }
    }
</script>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="registrationForm" method="POST" action="workroom">
                <fieldset>
                    <input type="hidden" name="command" value="edit_order_master"/>
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
                        <label class="col-lg-3 control-label">Client</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" value="${order.customerName}" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Description</label>
                        <div class="col-lg-9">
                            <textarea class="form-control" rows="7" readonly><c:out value="${order.description}"/></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Order status</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" value="${order.orderStatus.name}" readonly>
                        </div>
                    </div>

                    <hr>

                    <div class="form-group has-warning">
                        <label class="col-lg-3 control-label">Cost work</label>
                        <div class="col-lg-9">
                            <input type="number" min="0" class="form-control" name="costWork" value="${order.costWork}">
                        </div>
                    </div>
                    <h5 class="text-danger text-center">${errorEditMessage}</h5>
                    <hr>

                    <h3>Spare parts table</h3>
                    <c:if test="${order.orderStatus=='REPAIR_PROCESS'}">
                        <h4 class="text-danger">The list of parts has already been formed</h4>
                    </c:if>
                    <div class="bs-component">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>Choose</th>
                                <th>№</th>
                                <th>Part name</th>
                                <th>Cost</th>
                                <th>Required quantity</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="part" items="${replacementParts}" varStatus="status">
                                <tr id="id${status.index}Row"
                                    <c:if test="${part.numberParts!=0}">class="success"</c:if>>
                                    <td>
                                        <input type="checkbox" name="selectedParts"
                                               <c:if test="${order.orderStatus=='REPAIR_PROCESS'}">disabled</c:if>
                                               value="${part.partId}" id="id${status.index}"
                                               onclick="setInputEnabled(id)"
                                               <c:if test="${part.numberParts!=0}">checked</c:if>>
                                    </td>
                                    <td><c:out value="${status.count}"/></td>
                                    <td><c:out value="${part.partName}"/></td>
                                    <td><c:out value="${part.cost}"/></td>
                                    <td>
                                        <input type="number" name="numberParts" id="id${status.index}Input"
                                               <c:if test="${part.numberParts==0}">disabled</c:if>
                                               <c:if test="${order.orderStatus=='REPAIR_PROCESS'}">disabled</c:if>
                                               value="${part.numberParts}" min="1" class="form-control">
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <hr>

                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary center-block">Save</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>