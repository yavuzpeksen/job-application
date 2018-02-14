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
	    		
	    		<td style="text-align:center;"><button type="button" class="btn-danger">Delete</button></td>
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
  <form>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Job Title</label>
      <input type="text" class="form-control" id="title">
    </div>
    <div class="form-group col-md-6">
     <label for="inputAddress">Number of person to hire</label>
     <input type="number" class="form-control" id="HiringPersonNumber">
    </div>
  </div>
  <div class="form-row">
	<div class="form-group col-md-6">
	<label for="inputDescription">Job Description</label>
	<input type="text" class="form-control" id="description">
	</div>
	<div class="form-group col-md-6">
	<label for="inputDate">Select date</label>
	<input type="date" class="form-control" id="lastDate">
	</div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-10">
    </div>
    <div class="form-group col-md-2">
		<button style="float: right;" type="submit" class="btn btn-primary">Create</button>
    </div>
  </div>
    
  </form>
  </div>

</div>

</body>
</html>