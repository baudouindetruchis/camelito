<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Camelito - Shopping</title>

<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../public/css/shopping.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">

<script> 
   
    
</script>
<script src="../public/js/header.js"></script>
<script src="../public/js/shopping.js"></script>
</head>

<body onload="onload()">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden=true>
	<div id="includedHeader"></div>
	<div class="container">
		<h2>Produits disponibles</h2>
		<div id="includArt" class="row">

		</div>
	</div>
</body>
</html>