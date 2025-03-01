<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>New Order</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="registrationForm" method="POST" action="workroom">
                <fieldset>
                    <input type="hidden" name="command" value="create_replacement_part"/>
                    <h2>Creation of a new spare part</h2>
                    <hr>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Part name</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" name="partName" value="${part.name}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Cost</label>
                        <div class="col-lg-8">
                            <input type="number" min="1" class="form-control" name="cost" min="1" value="${part.cost}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Quantity in stock</label>
                        <div class="col-lg-8">
                            <input type="number" min="1" class="form-control" name="inStock" min="1" value="${part.inStock}">
                        </div>
                    </div>

                    <br/>
                    <h5 class="text-danger text-center">${errorPartCreationMessage}</h5>

                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary center-block">Create</button>
                        </div>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>