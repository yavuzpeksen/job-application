<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Application</title>
</head>
<body>
	    
    Anasayfaya hosgeldin <c:out value = "${username}"/>,
    <br><br>
    <c:choose>
    	<c:when test="${isAdmin}">
    	
 
	    	<c:choose>
	    		<c:when test="${jobListingId > 0}">
	    			
	    		<form action="/getJobListing" method="POST">
					<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
	    		
	    		Is ilan olusturma sayfaniza gitmek icin Tiklayin. 
	    		
					<input type = "submit" value = "Git" />
					
				</form>
	    		<!--  <a href="/getJobListing?id=<c:out value="${jobListingId}"/>" >Git</a> -->
	    		<br>
	    		<form action="/deleteJobListing" method="POST">
					<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
					
	    		Is ilan listenizi silmek icin 'Sil' linkine tiklayin. 
	    		
					<input type = "submit" value = "Sil" />
					
				</form>
	    		
	    		<!--  <a href="/deleteJobListing">Sil</a> -->
	    		
	    		</c:when>
	    		<c:otherwise>
		    		<form action="/createJobListing" method="GET">
						<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						
			    	Is ilan listeleme sayfasi olusturmak icin Tiklayin. 
			    	
			    	<input type = "submit" value ="Olustur" />
			    	</form>
		    	<!--  <a href="/createJobListing">Olustur.</a> -->
		    	</c:otherwise>
	    	</c:choose>
    	</c:when>
    	
    	<c:otherwise>
    	Butun is ilanlari listeleniyor.
    	</c:otherwise>
    </c:choose>
    <br>
    <a href="accesspoint/logout">Cikis</a>   

</body>
</html>