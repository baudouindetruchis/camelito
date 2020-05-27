<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	


<link rel="stylesheet" type="text/css"
	href="../public/css/magasinListStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Les Commandes</title>
<script src="../public/js/asso.js"></script>
<script src="../public/js/header.js"></script>

</head>
<body onload="includeHeaderAndCheckUser()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden>
	<div id="includedHeader"></div>

	<div class="container">
		<div class="row">
			<c:forEach var="listCommand"
				items="${sessionScope.listArticlesByUser}">

				<div id="comm${listCommand.idAndName}" class="col-md-3 mobileMargin">
					<div class="listbox">
						<div>
							<c:out value="${listCommand.user_name}" />
						</div>
						<div>
							<c:out value="${listCommand.price}" />
							€
						</div>
						<c:choose>
							<c:when test="${listCommand.ready=='lacommandeestprete'}">

								<p>La commande est prête</p>
							</c:when>
							<c:when test="${listCommand.ready=='lacommandenestpasprete'}">
								<p>La commande n'est pas prête</p>
							</c:when>
						</c:choose>
					</div>

					<!-- Form  -->
					<div for="${listCommand.idAndName}"
						href="#${listCommand.idAndName}" data-toggle="collapse"
						class="divSousTitre">
						<p class="pSousTitre" for="articles">Articles commandés</p>
						<i class="fa fa-angle-double-down" aria-hidden="true"></i>
					</div>

					<!-- Contenu -->
					<div id="${listCommand.idAndName}"
						class="collapse col-md-12 contentToggle ">

						<ul class="list-group list-group-flush paddingTopBot">

							<!-- Chaque liste d'articles -->
							<c:forEach var="art" items="${listCommand.listArticles}">
								<div class="form-control-plaintext" id="name">
									<li class="list-group-item d-flex justify-content-between align-items-center listContent">
										<c:out value="${art.name}" /> <span
										class="badge badge-primary badge-pill"><c:out
												value="${art.quantity}" /></span>
									</li>
								</div>
								
							</c:forEach>
							
						</ul>
						
						<c:choose>
							<c:when test="${listCommand.ready=='lacommandeestprete'}">

								<div id="closeOrder"
									class="closeOrderBtn position-relative stretched-link"
									onclick="finishOrder('comm${listCommand.idAndName}', '${listCommand.user_name}', '${listCommand.id}')">
									<i class="fa fa-times-circle-o fa-2x" aria-hidden="true"></i>
								</div>
								
							</c:when>
						</c:choose>
						
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="includedFooter"></div>
</body>
</html>