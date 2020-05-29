<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->

<body>
	<h3>Le score</h3>
	
	<c:set var="pUsr" value="${sessionScope.prevParticipant}" />
	<c:set var="cUsr" value="${sessionScope.currParticipant}" />
	<c:set var="sUsr" value="${sessionScope.succParticipant}" />
	
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
	     <tr style="${part.pseudo == cUsr.pseudo ? 'background-color:#b67823' : ''}">
		  	<td><c:out value="${part.place}"/></td>
		  	<td><c:out value="${part.pseudo}"/></td>
		  	<td><c:out value="${part.score}"/></td>
	   	  </tr>
	  </c:forEach>
		  <tr>
		  	<td>...</td>
		  	<td>...</td>
		  	<td>...</td>
	   	  </tr>
		  <tr>
		  	<td><c:out value="${sUsr.place}"></c:out></td>
		  	<td><c:out value="${sUsr.pseudo}"></c:out></td>
		  	<td><c:out value="${sUsr.score}"></c:out></td>
	   	  </tr>	
		  <tr style="background-color:#b67823">
		  	<td><c:out value="${cUsr.place}"></c:out></td>
		  	<td><c:out value="${cUsr.pseudo}"></c:out></td>
		  	<td><c:out value="${cUsr.score}"></c:out></td>
	   	  </tr>	
		  <tr>
		  	<td><c:out value="${pUsr.place}"></c:out></td>
		  	<td><c:out value="${pUsr.pseudo}"></c:out></td>
		  	<td><c:out value="${pUsr.score}"></c:out></td>
	   	  </tr>	   	  
	  </tbody>
	</table>
		
</body>
