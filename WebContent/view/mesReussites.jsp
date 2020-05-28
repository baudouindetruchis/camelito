
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<head>
<title>Header sample</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../public/css/header_sample.css">

</head>
<body>
<hr class="hrmargin" />

	<div class="container">
		<h2 class="success">Mes réussites</h2>

		<div class="col-md-12 reussitesCont">
			<div class="form-row reussitesRow">
				<div class="col-md-6 reussitesCol">
					<div class="col-md-12">
					<img class="img-responsive reussitesImg" src="../public/images/pieces.png"
						alt="Pile de piece" /></div>
					<label class="col-form-label reussitesLbl">10€ d'économisé</label>
				</div>

				<div class="col-md-6 reussitesCol">
				<div class="col-md-12">
					<img class="img-responsive reussitesImg" src="../public/images/medaille.png"
						alt="medaille" /></div>
					<label class="col-form-label  reussitesLbl" id="name">
						<c:out value="${usr.score}" />
						Points cumulés
					</label>
				</div>
			</div>
		</div>


		<%-- <div class="form-group row">
			<label for="succes" class="col-form-label">Succès : </label>
		</div>
		<div class="col-sm-7">
			<c:forEach var="success" items="${sessionScope.succesList}">
				<p class="form-control-plaintext" id="success">
					<c:out value="${success}" />
				</p>
			</c:forEach>


		</div> --%>

		<div class="col-md-12 text-center">
			<h4 class="col-form-label">Débloquez tous les succès !</h4>

			<div class="form-row" style="text-align: -webkit-center;">
				<c:forEach var="aSuccess" items="${sessionScope.successList}">
				
					<div class="col-md-4">
						<div class="card text-center cardSizing">
							<div class="card-body">

								<c:choose>
									<c:when test="${aSuccess.isDone}">
										<!-- Si le success est validé -->
										<a style="font-size: 50px;"> ${aSuccess.pic}</a>
									</c:when>
									<c:otherwise>
										<!-- Si le success n'a pas encore été realisé -->
										<a style="opacity: 0.5; font-size: 50px;"> ${aSuccess.pic}</a>
									</c:otherwise>
								</c:choose>
								<div class="form-row centerContent">
									<label>${aSuccess.name}</label>
									<%-- <c:out value="${aSuccess.type}" />
							<c:out value="${aSuccess.isBest}" /> --%>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


			<h4 class="col-form-label">Vos meilleurs succès</h4>

			<div class="form-row" style="text-align: -webkit-center;">
				<c:forEach var="aSuccess" items="${sessionScope.successList}">

					<c:choose>
						<c:when test="${aSuccess.isBest}">
							<div class="col-md-4">
								<div class="card text-center cardSizing">
									<div class="card-body">
										<!-- Si le success est le meilleur de sa categorie -->
										<a style="font-size: 50px;"> ${aSuccess.pic}</a>
										<div class="form-row centerContent">
											<label>${aSuccess.name}</label>
											<%-- <c:out value="${aSuccess.type}" /> --%>
										</div>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>

				</c:forEach>
			</div>
			<!-- wei wei wei -->
			<div class="form-group row">
				<label for="succes" class="col-form-label">Selectionne un
					succes : </label>
			</div>
			<div class="col-sm-7">
				<c:forEach var="aSuccess" items="${sessionScope.successList}">
					<c:choose>
						<c:when test="${aSuccess.isBest}">
							<!-- prend les meilleur succes -->
							<c:choose>
								<c:when test="${aSuccess.type == 'saving'}">
									<!-- de la categorie perso -->
									<c:out value="${aSuccess.name}" />
									<img src="${aSuccess.pic}" style="height: 20px; width: 20px;" />
									<c:out value="${aSuccess.type}" />
									<br>
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>

	</div>

</body>
</html>