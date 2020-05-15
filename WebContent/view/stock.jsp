<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<link rel="stylesheet" type="text/css" href="../public/css/stockStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
	<script src="../public/js/header.js"></script>
	<script src="../public/js/stock.js"></script>
</head>

<body onload="onload()">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden=true>
	<div id="includedHeader"></div>
	<button>Bas de page</button> <button>Choose filter</button> <button>Empty all</button>
	<div id="articlesDiv"></div>
	<div id="addArticlDiv">
		<div class="form-group row">
			<label for="mode" class="col-form-label">Ajouter/Modifier un article : </label>
			<label class="switch"> 
				<input type="checkbox" id="formCheck" onchange="changeForm()" checked>
				<span class="slider round"></span>
			</label>
		</div>
		<form id="formAdd" action="../StockForm" class="form-container" name="">
			<input type="text" placeholder="Name" name="name" required class="field">					
			<input type="text" placeholder="Description" name="description" class="field">
			<input type="number" placeholder="Real price" name="real_price" required class="field" step="0.01">
			<input type="number" placeholder="Selling price" name="selling_price" required class="field" step="0.01">
  			<input type="number" name="stock" min="0" max="99" required>
			<input type="submit" name="act" value="Ajouter">
		</form>
		<div id="divFormModif"></div>
	</div>
</body>
