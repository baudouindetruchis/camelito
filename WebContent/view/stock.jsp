<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

 <script> 
    $(function(){
    	$("#includedHeader").load("header_sample.jsp"); 
    	$("#articlesDiv").load("stockArticles.jsp"); 
    });
</script> 

<link rel="stylesheet" type="text/css" href="../public/css/stockStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
</head>

<body>
	<div id="includedHeader"></div>
	<div id="articlesDiv"></div>
	<div id="addArticlDiv">
		<form action="../StockForm" class="form-container" name="">
			<h5>Ajouter un article :</h5><br>
			<input type="name" placeholder="Name" name="name" required class="field">					
			<input type="description" placeholder="Description" name="description" required class="field">
			<input type="real_price" placeholder="Real price" name="real_price" required class="field">
			<input type="selling_price" placeholder="Selling price" name="selling_price" required class="field">	
		</form>
	</div>
</body>
