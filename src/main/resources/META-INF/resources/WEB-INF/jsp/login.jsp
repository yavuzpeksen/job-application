<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <title>Kod Gemisi</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${contextPath}/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${contextPath}/css/blue.css">
  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="../../index2.html"><b>Job Application</a>
      </div><!-- /.login-logo -->
      
      <div class="login-box-body ${error != null ? 'has-error' : ''}"">
        <p class="login-box-msg">Login Page</p>
        <form action="<c:url value='/j_spring_security_check' />" method="post" name='loginForm' > 
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Username" name='username' id="username">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" id="password" name='password'>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div>
          	<c:out value = "${error}"/>
          </div>
          <div class="row">
          
            <div class="col-xs-8">
	            <div class="checkbox">
	                <label>
	                  <input type="checkbox" name="remember-me">Remember me
	                </label>
	              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
            </div><!-- /.col -->
          </div>
          <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
        </form>
        
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 3.2 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>    <!-- iCheck -->
	    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>
  </body>
</html>