<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<!-- Bootstrap Toggle -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="../public/css/profilStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Ma commande</title>
<script src="../public/js/header.js"></script>

</head>

<body onload="includeHeaderAndCheckUser()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden>
	<div id="includedHeader"></div>
	<div class="container">



		<c:forEach var="article" items="${sessionScope.CommandePourStore}">

			<label>Ma commande :</label>
			<c:out value="prix total : ${sessionScope.TotalPrice}" />€
			<c:out value="- ${article.quantity} x  " />
			<c:out value="${article.name}" />

			<div>
				<c:out value="	-->${article.selling_price}" />
				€ la pièce
			</div>


		</c:forEach>

	</div>

	<div id="includedFooter"></div>

</body>

</html>