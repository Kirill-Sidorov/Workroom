<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Edit order</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="orderEditorForm" method="POST" action="workroom">
                <fieldset>
                    <input type="hidden" name="command" value="edit_order"/>
                    <input type="hidden" name="orderId" value="${order.id}"/>
                    <h2>Edit order</h2>
                    <hr>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Name of the thing</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" name="thingName" value="${order.thingName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Description</label>
                        <div class="col-lg-8">
                            <textarea class="form-control" rows="7" name="description"
                                      placeholder="Description of the breakdown"><c:out
                                    value="${order.description}"/></textarea>
                        </div>
                    </div>

                    <br/>
                    <h5 class="text-danger text-center">${errorOrderEditMessage}</h5>

                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary center-block">
                                <c:if test="${user.userType == 'CUSTOMER'}">
                                    Edit
                                </c:if>
                                <c:if test="${user.userType == 'MODERATOR'}">
                                    Accept order
                                </c:if>
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