<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Kod Gemisi</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
	    <h2 align="center" >Homepage</h2>
	    <br>
	    <div style="width: 60%; margin:0 auto;">
		    <div style="height:40px;">
		    	<a href="accesspoint/logout" style="float:right; height:20px; width:100px; border:1px solid black; text-align:center;">Logout</a>
		    </div>
		    <c:choose>
			    <c:when test="${isAdmin}">
			    	<p style="">Welcome <c:out value = "${username}"/>, you are logged in as HR Manager. <c:out value="${pageMessage}"></c:out></p>
			    </c:when>
		    </c:choose> 
		    <br>
		    <c:choose>
		    	<c:when test="${isAdmin}"> 
			    	<c:choose>
			    		<c:when test="${jobListingId > 0}">	
			    		<table class="table table-bordered">
							<tbody>
							    
							        			
				    		<form action="admin/getJobListing" method="POST">
								<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
								<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
				    		
				    			<tr>
				    				<td>Click the button to the list page of your job posts</td>
									<td align="center">
										<input type = "submit" class="btn-primary" style="display:inline-block;"value = "List" />
									</td>
								</tr>
							</form>
			    		<!--  <a href="/getJobListing?id=<c:out value="${jobListingId}"/>" >Git</a> -->
		
				    		<form action="admin/deleteJobListing" method="POST">
								<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
								<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
								
								<tr>
									<td>Click the button to delete all your job posts</td>	    		
						    		<td align="center">
										<button id="deleteBtn" type = "submit" style="display:inline-block;" class="btn-danger">Delete</button>
									</td>
								</tr>
							</form>
							</tbody>
						</table>
				    		<!--  <a href="/deleteJobListing">Sil</a> -->
			    		
			    		</c:when>
			    		<c:otherwise>
				    		<form id = "createJobListingForm">
								<input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
								<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
								
								<div style="width:60%; display:inline-block;">
									<div style="width:80%; margin:0 auto;">
						    			<p style="float:left;">Click the button to create job listing </p>
						    		</div>
					    		</div>
					    		<button id="submitBtn" type = "submit" class="btn-primary">Create</button>
					    		
					    	</form>
				    	<!--  <a href="/createJobListing">Olustur.</a> -->
				    	</c:otherwise>
			    	</c:choose>
		    	</c:when>
		    </c:choose>
	    </div>

    </div>
    <c:choose>
    	<c:when test="${!isAdmin}">

		    		<div class="container">
		    		
					    <c:choose>
						    <c:when test="${!isAdmin}">
						    	<p style="">Welcome <c:out value = "${username}"/>, you are logged in as Applicant candidate</p>
						    </c:when>
					    </c:choose>
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
								    		<td><a href="/getJobApplyPage?postid=${item.id}">Link</a></td>
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
				  </c:when>
		    	</c:choose>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#submitBtn").click(function(){
		event.preventDefault();
		$.ajax({
		    type: "GET",
		    url: "admin/createJobListing",
		    data: $('#createJobListingForm').serialize(),
		    dataType: "json",
		    success: function(data) {
		    	if(data.status == 1){
		    		alert("Job list successfully created");
		    		location.reload(); 		
		    	}
		    },
		   error: function() {
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
		    url: "admin/deleteJobListing",
		    data: data,
		    dataType: "json",
		    success: function(data) {
		    	if(data.status == 1){
		    		alert("Job list successfully deleted")
		    		location.reload(); 		
		    	}
		    },
		   error: function() {
		       alert("There is an error");
		   }			
		});
	});

});
</script>
</body>
</html>