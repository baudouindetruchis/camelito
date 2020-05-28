<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" media="screen">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="../public/css/commandPageStoreStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Ma commande</title>
<script src="../public/js/header.js"></script>
<script src="../public/js/commandPageStore.js"></script>
</head>

<body onload="includeHeaderAndCheckUser()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden>
	<div id="includedHeader"></div>
	<div class="container">
		<div class="col-md-12 articleList">
		
			<div class="articleTitre">
			<h2>Ma commande</h2>
			</div>
			<c:forEach var="article" items="${sessionScope.CommandePourStore}">

				<div class="col-md-12 article text-center">
					<div class="form-row">
						<div class="col-md-4"
							style="padding-right: 20px; padding-left: 20px;">
							<img class="object-fit_contain" src="${article.img}">
						</div>

						<div class="col-md-6 cardLike">
							<h4>
								<c:out value="${article.quantity}" />
								<c:out value="${article.name}" />
							</h4>
							<div class="col-md-12 cardContent">
								<label>Prix unitaire :</label>
								<c:out value="${article.selling_price}" />
								€/u
							</div>
							<label class="active btnStyle" id="btnStyle${article.id}" onclick="btnValidate('${article.id}')"> 
							<input type="checkbox" autocomplete="off" checked hidden=True>
								<i class="fa fa-check" aria-hidden="true"></i>
								<i class="fa fa-question" id="btnQuestion${article.id}" aria-hidden="true"></i>
							</label>
						</div>
					</div>
				</div>

			</c:forEach>

			<div class="col-md-12 text-center totalPrice">
				<h3><label>Prix total :</label>
				<c:out value="${sessionScope.TotalPrice}" />
				€</h3>
			</div>

		</div>
	</div>

	<div id="includedFooter"></div>

</body>

</html>