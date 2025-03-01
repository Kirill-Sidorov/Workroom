<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Registration</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="editUserForm" method="POST" action="workroom">
                <fieldset>
                    <div class="text-center">
                        <h2>Edit user</h2>
                    </div>
                    <input type="hidden" name="command" value="update_user"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">Name</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" value="${user.firstName}" name="firstName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Surname</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" value="${user.lastName}" name="lastName">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Phone</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" value="${user.phone}" name="phoneNumber"
                                   placeholder="8-xxx-xxx-xx-xx">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Login</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" value="${user.login}" name="login">
                        </div>
                    </div>

                    <br/>
                    <h5 class="text-danger text-center">${errorRegistrationMessage}</h5>

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