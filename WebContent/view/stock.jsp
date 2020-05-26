<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Bootstrap Toggle -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/stockStyle.css">

<head>
	<title>Camelito - Stock</title>
	<script src="../public/js/header.js"></script>
	<script src="../public/js/stock.js"></script>
</head>

<body onload="onload()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden=true>
	<div id="includedHeader"></div>

	<div class="container">	
		<h2>Mon stock</h2>

	<div class="form-row">
		<div class="form-group col-md-3">
			<label for="formCheck" class="col-form-label">Ajouter/Modifier un article : </label> 
			<input type="checkbox" data-toggle="toggle" data-on="Ajouter" data-off="Modifier" data-onstyle="warning" data-offstyle="warning" id="formCheck" onchange="changeForm()" checked>			
		</div>
			<div class="form-group col-md-9"></div>
		</div>
	
	<div class=formStyle>
		<form id="formAdd" action="../StockForm" class="form-container" name="">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="productName">Nom du produit</label> 
						<input type="text" class="form-control" id="productName" placeholder="Nom du produit" name="name" required class="field">
					</div>
					<div class="form-group col-md-6">
						<label for="productDescription">Description</label> 
						<input type="text" class="form-control" id="productDescription" placeholder="Description" name="description" class="field">
					</div>
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-5">
						<label for="realPrice">Prix réel</label> 
						<input type="number" class="form-control" id="realPrice" placeholder="Prix réel" name="real_price" required class="field" step="0.01">
					</div>
					<div class="form-group col-md-5">
						<label for="sellingPrice">Prix de vente</label> 
						<input type="number" class="form-control" id="sellingPrice" placeholder="Prix de vente" name="selling_price" required class="field" step="0.01">
					</div>
					<div class="form-group col-md-2">
						<label for="numberProduct">Quantité</label> 
						<input type="number" class="form-control" id="numberProduct" placeholder="0" name="stock" min="0" max="99" required>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="pic">Image</label> 
						<input type="text" class="form-control" id="pic" placeholder="Lien hypertext de l'image" name="pic">
					</div>
				</div>
				


				<div class="form-row" style="text-align: center">
					<div class="form-group col-md-12">
						<input type="submit" class="btn btn-primary btnAjout" name="act" value="Ajouter">
					</div>
				</div>
		</form>
	</div>
	
	<div id="divFormModif"></div>
		
		<div id="articlesDiv"></div>
		<div id="addArticlDiv"></div>
	</div>
	<div id="includedFooter"></div>
</body>