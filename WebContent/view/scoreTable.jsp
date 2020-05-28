<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<body>
	<h2>Course aux camelicoins </h2>
	<table id='myTable' class="table table-striped" style="width: 50%">
	  <thead>
	    <tr>
	      <th scope="col">Classement</th>
	      <th scope="col">Pseudo</th>
	      <th scope="col">Score</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="part" items="${sessionScope.participantsList}">
		  <tr>
		  	<td><c:out value="${part.place}"/></td>
		  	<td><c:out value="${part.pseudo}"/></td>
		  	<td><c:out value="${part.score}"/></td>
	   	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
		
</body>
