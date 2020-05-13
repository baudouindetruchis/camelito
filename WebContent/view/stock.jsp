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
    
    function changeForm() {
      var checkBox = document.getElementById("formCheck");
      var formAdd = document.getElementById("formAdd");
      var formModif = document.getElementById("formModif");
      if (checkBox.checked == true){
    	  formAdd.hidden=false;
    	  formModif.hidden=true;
      } else {
    	  formModif.hidden=false;
    	  formAdd.hidden=true;
      }
    }
    
    function fillForm(id, name, description, real_price, selling_price, stock){
    	var modId           = document.getElementById("modId");
    	var modName         = document.getElementById("modName");
    	var modDescription  = document.getElementById("modDescription");
    	var modRealPrice    = document.getElementById("modRealPrice");
    	var modSellingPrice = document.getElementById("modSellingPrice");
    	var modStock        = document.getElementById("modStock");
    	modId.value = id;
    	modName.value = name;
    	modDescription.value = description;
    	modRealPrice.value = real_price;
    	modSellingPrice.value = selling_price;
        modStock.value = stock;
        formModif.hidden=false;
  	  	formAdd.hidden=true;
    }
    
</script> 

<link rel="stylesheet" type="text/css" href="../public/css/stockStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
</head>

<body>
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
			<input type="submit" value="Ajouter">
		</form>
		<form id="formModif" action="../StockForm" class="form-container" name="" hidden=true>
			<input id="modId" type="number" placeholder="Id" name="id" required class="field">	
			<input id="modName" type="text" placeholder="Name" name="name" required class="field">					
			<input id="modDescription" type="text" placeholder="Description" name="description" class="field">
			<input id="modRealPrice" type="number" placeholder="Real price" name="real_price" required class="field" step="0.01">
			<input id="modSellingPrice" type="number" placeholder="Selling price" name="selling_price" required class="field" step="0.01">
  			<input type="number" id="modStock" name="stock" min="0" max="99" required>
			<input type="submit" name="modif" value="Modifer">
			<input type="submit" name="supp" value="Supprimer">
		</form>
	</div>
</body>
