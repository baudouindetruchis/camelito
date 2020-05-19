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
	<div class="form-row stockFilter">
		<div class="form-group col-md-4">
			<label for="trierId">Trier vos produits</label>
			<select id="trierId" class="form-control" name="trierId" required>
				<option value="ORDER BY id ASC" selected>Id croissant</option>
				<option value="ORDER BY id DESC" selected>Id decroissant</option>
				<option value="ORDER BY name ASC" selected>Name croissant</option>
				<option value="ORDER BY name DESC" selected>Name decroissant</option>
				<option value="ORDER BY available ASC" selected>Stock croissant</option>
				<option value="ORDER BY available DESC" selected>Stock decroissant</option>
			</select>
		</div>
		<div class="form-group col-md-5"></div>
		<div class="form-group col-md-3 divErase">
			<label></label>
			<input type="submit" class="btn btn-primary btnErase" name="act" value="Vider vos stocks" data-toggle="modal" data-target="#modalConfirmSuppr">
		</div>

		<!-- Popup pour le bouton Tout Supprimer -->
		<div class="modal fade" id="modalConfirmSuppr" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content noborder">
					<div class="modal-header text-center headpopup">
						<h4 class="messageSuppr">Voulez-vous vraiment vider tous vos stocks ?</h4>

						<!-- Bouton pour fermer le popup -->
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<!-- Contenu du popup -->
					<div class="modal-body mx-3">
						<div class="md-form mb-5 popupConfirm">
							<button class="btn btn-default btn-rounded mb-4 btnNon">Non</button>
							<button class="btn btn-default btn-rounded mb-4 btnOui" onclick="emptyStock()">Oui</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>

	<table id='myTable' class="table table-striped stockTable">
	  <thead>
	    <tr>
	      <th scope="col">Id</th>
	      <th scope="col">Article</th>
	      <th scope="col">Description</th>
	      <th scope="col">Prix intial</th>
	      <th scope="col">Prix de vente</th>
	      <th scope="col">Stock</th>
	      <th scope="col">  </th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="artc" items="${sessionScope.stockList}">
		  <tr>
		  	<td><c:out value="${artc.id}"/></td>
		  	<td><c:out value="${artc.name}"/></td>
		  	<td><c:out value="${artc.description}"/></td>
		  	<td><c:out value="${artc.real_price}"/></td>
		  	<td><c:out value="${artc.selling_price}"/></td>
		  	<td><form>
		  		<input id="stock-${artc.id}" type="number"  min="0" max="99" step="1" placeholder="Stock" value="${artc.stock}" onchange="stockUpdate(${artc.id})" class="field form-control">
		  	</form></td>
			<td><button class="btn btn-default btn-rounded mb-4 btnTable" onClick="fillForm(${artc.id}, '${artc.name}', '${artc.description}', ${artc.real_price}, ${artc.selling_price}, ${artc.stock})">Modifier</button></td>
	   	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	
		
</body>
