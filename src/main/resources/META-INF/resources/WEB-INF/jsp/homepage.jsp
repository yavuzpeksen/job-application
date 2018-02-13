<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Application</title>
</head>
<body>
	
	<h1>Kullanici giris sayfasi</h1>   
    
    Anasayfaya hosgeldin <c:out value = "${username}"/>
    
    <a href="accesspoint/logout">
    
    <br><br><b>Cikis</b></a>   

</body>
</html>