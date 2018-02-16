<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Kod Gemisi</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	    
    <h2>Welcome to the homepage <c:out value = "${username}"/>, </h2>
    <br><br>
    <c:choose>
    	<c:when test="${isAdmin}"> 
	    	<c:choose>
	    		<c:when test="${jobListingId > 0}">
	    			
	    		<form action="/getJobListing" method="POST">
					<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
	    		
	    			<p style="float:left;">Click the button to the list page of your job posts </p> 
					<input type = "submit" class="btn-primary" value = "List" />
					
				</form>
				<br>
	    		<!--  <a href="/getJobListing?id=<c:out value="${jobListingId}"/>" >Git</a> -->

	    		<form action="/deleteJobListing" method="POST">
					<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
					
	    			<p style="float:left;"> Click the button to delete all your job posts </p>
	    		
					<button id="deleteBtn" type = "submit" class="btn-danger">Delete</button>
					
				</form>
	    		<!--  <a href="/deleteJobListing">Sil</a> -->
	    		
	    		</c:when>
	    		<c:otherwise>
		    		<form id = "createJobListingForm">
						<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
						<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
						
			    		<p style="float:left;">Click the button to create job listing </p>
			    		
			    		<button id="submitBtn" type = "submit" class="btn-primary">Create</button>
			    	</form>
		    	<!--  <a href="/createJobListing">Olustur.</a> -->
		    	</c:otherwise>
	    	</c:choose>
    	</c:when>
    	
    	<c:otherwise>
    		<div class="container">
    		<h2>Job Post List</h2>
    		  <c:choose>
	  				<c:when test="${hasJob}">
			    	  	<table class="table table-bordered">
					    <thead>
					      <tr>
					        <th>Job Title</th>
					        <th>Job Description</th>
					        <th>Number of people</th>
					        <th>Last Application Date</th>
					        <th>Details</th>
					      </tr>
					    </thead>
					    <tbody>
					    	<c:forEach items="${jobList}" var="item" varStatus="st">
						    	<tr>
						    		<td>${item.title}</td>
						    		<td>${item.description}</td>
						    		<td>${item.hiringPersonNumber}</td>
						    		<td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.lastApplicationDate}" /></td>
						    		<td><a href="/getJobDetailPage?postid=${item.id}">Link</a></td>
						    	</tr>
					    	</c:forEach>
					    </tbody>
					  </table>
			  		</c:when>
    		<c:otherwise>
			    <h4> There is no job post.</h4>
			</c:otherwise>
			</c:choose>
		  </div>
    	</c:otherwise>
    </c:choose>
    <br>
    <a href="accesspoint/logout">Logout</a>   

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#submitBtn").click(function(){
		event.preventDefault();
		$.ajax({
		    type: "GET",
		    url: "/createJobListing",
		    data: $('#createJobListingForm').serialize(),
		    dataType: "json",
		    success: function(data) {
		    	if(data.status == 1){
		    		alert("Job list successfully created");
		    		location.reload(); 		
		    	}
		    },
		   error: function() {
		        //$("#commentList").append($("#name").val() + "<br/>" + $("#body").val());
		       alert("There is an error");
		   }
			
		});
	});
	
	$("#deleteBtn").click(function(){
		event.preventDefault();
		var data = {
			id: ${jobListingId},
			${_csrf.parameterName}:'<c:out value="${_csrf.token}"/>'
		}
		$.ajax({
		    type: "POST",
		    url: "/deleteJobListing",
		    data: data,
		    dataType: "json",
		    success: function(data) {
		    	if(data.status == 1){
		    		alert("Job list successfully deleted")
		    		location.reload(); 		
		    	}
		    },
		   error: function() {
		        //$("#commentList").append($("#name").val() + "<br/>" + $("#body").val());
		       alert("There is an error");
		   }
			
		});
	});

});
</script>
</body>
</html>