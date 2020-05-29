<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<link rel="stylesheet" type="text/css" href="../public/css/scoreStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
<script src="../public/js/header.js"></script>

<title>Camelito Score</title>
</head>


<body onload="includeHeaderAndCheckUser()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden=true>
	<div id="includedHeader"></div>

	<div class="container">
	<div class="row">
		<div class="col-md-12" id="yourScore">
			<h2>La course aux Camelicoins</h2>
			<div class="col-md-12 headerTitre">Bienvenue sur cette course,
				truc, c'est ici que tu vas pouvoir venir avec ta fidèle monture pour
				voir les scores de chacun afin de constater à quel point tu
				sauvegardes la planète !</div>


			<%@include file="scoreCamel.jsp"%>

			<%@include file="scoreTable.jsp"%>

			<%@include file="scoreFiltre.jsp"%>
		</div>
	</div>
	</div>
	<div id="includedFooter"></div>
</body>