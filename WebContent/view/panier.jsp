<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>



<link rel="stylesheet" type="text/css" href="../public/css/panierStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
	<script src="../public/js/header.js"></script>
	<script src="../public/js/panier.js"></script>
</head>

<body onload="onload()">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>
	<div id="includedHeader"></div>
	<div id="ordersDiv"></div>
	<div id="cartDiv"></div>	
	<div id="fin">
		<button onClick="btnActClick('ann')">Annuler</button>
		<button onClick="btnActClick('pay')">Payer</button>
	</div>
	
</body>
