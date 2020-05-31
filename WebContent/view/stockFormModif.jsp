<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<link rel="stylesheet" type="text/css" href="../public/css/stockStyle.css">


<body>
	<div class=formStyle>
		<form id="formModif" action="../StockForm" class="form-container" name="" hidden=true>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="modId">Produit sélectionné</label> 
						<select id="modId" class="form-control" name="idArticle" required>
						<c:forEach var="artc" items="${sessionScope.stockList}">
							<option value="${artc.id}">${artc.id}- ${artc.name}</option>
						</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="modDescription">Description</label> 
						<input type="text" class="form-control" id="modDescription" placeholder="Description" name="description" class="field">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-5">
						<label for="modRealPrice">Prix réel</label> 
						<input type="number" class="form-control" id="modRealPrice" placeholder="Prix réel" name="real_price" required class="field" step="0.01">
					</div>
					<div class="form-group col-md-5">
						<label for="modSellingPrice">Prix de vente</label> 
						<input type="number" class="form-control" id="modSellingPrice" placeholder="Prix de vente" name="selling_price" required class="field" step="0.01">
					</div>
					<div class="form-group col-md-2">
						<label for="modStock">Quantité</label> 
						<input type="number" class="form-control" id="modStock" placeholder="0" name="stock" min="0" max="99" required>
					</div>
				</div>
				<div class="form-row">
						<label for="pic">Image</label> 
						<input type="text" class="form-control" id="modImg" placeholder="Lien hypertext de l'image" name="pic">
					</div>

				<div class="form-row" style="text-align: center">
					<div class="form-group col-md-12">
						<input type="submit" class="btn btn-primary btnAjout" name="act" value="Supprimer" formnovalidate>
						<input type="submit" class="btn btn-primary btnAjout" name="act" value="Modifier">
				</div>
				</div>
		</form>
	</div>
		
</body>
