<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->


<body onload="myFunction()">

	<h3>Pour espionner ses potes</h3>

	<div class="col-md-12">
		<div class="col-md-12 text-center">
			<input type="text" id="myInput" class="inputField" onkeyup="myFunction()"
				placeholder="Recherche.." title="Type in a pseudo">
		</div>


		<table id='filterTable'
			class="table table-striped scoreTablePrincipale">
			<thead>
				<tr>
					<th scope="col">Classement</th>
					<th scope="col">Pseudo</th>
					<th scope="col">Score</th>
					<th scope="col">Titre secret</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="part" items="${sessionScope.fullParticipantsList}">
					<tr>
						<td><c:out value="${part.place}" /></td>
						<td><c:out value="${part.pseudo}" /></td>
						<td><c:out value="${part.score}" /></td>
						<td id="titreSecret ${part.pseudo}${part.favSucces}"><c:out value="${part.favSucces}"/></td>
					</tr>
					<script>titreSuccess('${part.pseudo}','${part.favSucces}')</script>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>