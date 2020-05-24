
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<head>
<title>Header sample</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="../public/css/header_sample.css">

</head>
<body>
<hr/>


	<h2 class="success">Mes réussites</h2>

	<div class="form-group row">
		<img class="img-responsive" src="../public/images/pieces.png"
			alt="Pile de piece" width="15%" height="17%" />
		<p class="col-form-label">10€ d'économisés</p>
	</div>
	<div class="form-group row">
		<img class="img-responsive" src="../public/images/podium.png"
			alt="Podium" width="15%" height="17%" />
		<p class="col-form-label">Concours en groupe</p>
	</div>
	<div class="form-group row">
		<img class="img-responsive" src="../public/images/medaille.png"
			alt="medaille" width="10%" height="10%" />
		<p class="col-form-label" id="name">
			<c:out value="${usr.score}" />
			Points cumulés
		</p>
	</div>


	<div class="form-group row">
		<label for="succes" class="col-form-label">Succès : </label>

	</div>
	<div class="col-sm-7">
		<c:forEach var="success" items="${sessionScope.succesList}">

			<p text" class="form-control-plaintext" id="success">
				<c:out value="${success}" />
			</p>
		</c:forEach>


	</div>



</body>
</html>