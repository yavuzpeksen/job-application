<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
        <p class="login-box-msg">Reset Password</p>
        	 
        	<c:choose>
        		<c:when test="${isFormAllowed == false}">
        			<div class="alert alert-danger" align="center">
        				Token is not valid or it is expired
        			</div>
        		</c:when>
        		<c:otherwise>
        		
        		<c:if test="${resetStatus != null}">
	        		
		        	<div class="alert ${resetStatus == true ? 'alert-success' : 'alert-danger'}" align="center"><c:out value="${resetResultMessage}"></c:out>
		        	</div>
	
	        	</c:if>
        		
        		<form:form  method="POST" modelAttribute="resetForm" class="form-signin needs-validation was-validated"> 
			       			        
			        <spring:bind path="password">
			            <div class="form-group has-feedback ${status.error ? 'has-error' : ''}">
			                <form:input type="password" path="password" class="form-control" placeholder="Password" required="required"></form:input>
			                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
			                <form:errors path="password"></form:errors>
			            </div>
			        </spring:bind>
			        <spring:bind path="passwordConfirm">
			            <div class="form-group ${status.error ? 'has-error' : ''}">
			                <form:input type="password" path="passwordConfirm" class="form-control"
			                            placeholder="Confirm your password" required="required"></form:input>
			                <form:errors path="passwordConfirm"></form:errors>
			            </div>
			        </spring:bind>
			          <div>
			          	<c:out value = "${error}"/>
			          </div>
			          
			          <div class="row" align="center">
			          	<button type="submit" class="btn btn-primary btn-flat">Submit</button>   
			          	<!-- style="background-color:#00b941;"  -->           	
			          </div>
			          <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
			        </form:form>
        		
        		</c:otherwise>
        	</c:choose>
        
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 3.2 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Bootstrap 3.3.4 -->
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>
  </body>
</html>