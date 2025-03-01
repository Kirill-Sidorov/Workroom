<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<style>
    <%@include file="/style/main.css" %>
</style>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 well">
            <form class="form-horizontal" name="loginForm" method="POST" action="workroom">
                <fieldset>
                    <div class="text-center">
                        <h2>Login</h2>
                    </div>
                    <input type="hidden" name="command" value="login"/>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Login</label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control" name="login" value="${loginInput}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-2 control-label">Password</label>
                        <div class="col-lg-8">
                            <input type="password" class="form-control" name="password">
                        </div>
                    </div>
                    <br/>
                    <h5 class="text-danger text-center">${errorLoginMessage}</h5>
                    <div class="form-group">
                        <div class="col-lg-8 col-lg-offset-2">
                            <button type="submit" class="btn btn-primary center-block">Sign In</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>