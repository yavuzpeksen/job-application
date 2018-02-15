<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
	<title>Kod Gemisi</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Job List</h2>
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
        <th></th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${jobSet}" var="item" varStatus="st">
	    	<tr>
	    		<td>${item.title}</td>
	    		<td>${item.description}</td>
	    		<td>${item.hiringPersonNumber}</td>
	    		<td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.lastApplicationDate}" /></td>
	    		<td><a href="/jobDetailPage">Link</a></td>
	    		
	    		<td style="text-align:center;"><button data-postid = "${item.id}"type="button" class="btn-danger delete">Delete</button></td>
	    	</tr>
    	</c:forEach>
    </tbody>
  </table>
  
    </c:when>
    <c:otherwise>
    <h4> You do not have any job post.</h4>
    </c:otherwise>
  	</c:choose>
  <br>
  
  <h2>Add Job Post</h2>
  
  <div style="background:#e4e4e4; height:220px; padding-top:20px;">
  <form id="createJobPostForm">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Job Title</label>
      <input type="text" class="form-control" name="title">
    </div>
    <div class="form-group col-md-6">
     <label for="inputAddress">Number of person to hire</label>
     <input type="number" class="form-control" name="numOfPerson">
    </div>
  </div>
  <div class="form-row">
	<div class="form-group col-md-6">
	<label for="inputDescription">Job Description</label>
	<input type="text" class="form-control" name="description">
	</div>
	<div class="form-group col-md-6">
	<label for="inputDate">Select date</label>
	<input type="date" class="form-control" name="lastDate">
	</div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-10">
    </div>
    <div class="form-group col-md-2">
		<button id="submitBtn" style="float: right;" type="submit" class="btn btn-primary">Create</button>
    </div>
  </div>
  <input type="hidden" name="id" value="<c:out value="${jobListingId}"/>" /> 
  <input type="hidden" name="${_csrf.parameterName}"
  value="${_csrf.token}" />
  </form>
  </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	$( ".delete" ).each(function(index) {
	    $(this).on("click", function(){
	        var postId = $(this).data('postid');
	        
			event.preventDefault();
			var data = {
				postid: postId,
				${_csrf.parameterName}:'<c:out value="${_csrf.token}"/>'
			}
			$.ajax({
			    type: "POST",
			    url: "/deleteJobPost",
			    data: data,
			    dataType: "json",
			    success: function(data) {
			    	if(data.status == 1){
			    		alert('Successfully deleted');
			    		location.reload();
			    	}else{
			    		alert('Error');
			    	}
			    },
			   error: function() {
			        //$("#commentList").append($("#name").val() + "<br/>" + $("#body").val());
			       alert("There was an error submitting comment");
			   }
				
			});
	        
	    });
	});
	
	$("#submitBtn").click(function(){
		event.preventDefault();
		$.ajax({
		    type: "POST",
		    url: "/createJobPost",
		    data: $('#createJobPostForm').serialize(),
		    dataType: "json",
		    success: function(data) {
		    	if(data.status == 1){
		    		alert('Successfully added');
		    		location.reload();
		    	}else{
		    		alert('Error');
		    	}
		    },
		   error: function() {
		        //$("#commentList").append($("#name").val() + "<br/>" + $("#body").val());
		       alert("There was an error submitting comment");
		   }
			
		});
	});

});
</script>

</body>
</html>
