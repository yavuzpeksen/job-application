<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
  <table class="table table-bordered">
    <thead>
      <tr>
        <th>Job Title</th>
        <th>Job Description</th>
        <th>Number of people</th>
        <th>Last Application Date</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${jobSet}" var="item" varStatus="st">
	    	<tr>
	    		<td>${item.title}</td>
	    		<td>${item.description}</td>
	    		<td>${item.hiringPersonNumber}</td>
	    		<td>${item.lastApplicationDate}</td>
	    		<td style="text-align:center;"><button type="button" class="btn-danger">Sil</button></td>
	    	</tr>
    	</c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>
