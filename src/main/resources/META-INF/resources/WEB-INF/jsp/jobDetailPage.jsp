<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
	<title>Kod Gemisi</title>
</head>
<body>
	<h1>Job</h1>
  	<c:choose>
  		<c:when test="${job != null}">
  		There is a job post.
  		</c:when>
     	<c:otherwise>
    	<h4> The selected job post is not available at the moment..</h4>
     	</c:otherwise>
  	</c:choose>
</body>
</html>