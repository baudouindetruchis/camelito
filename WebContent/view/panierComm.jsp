<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="../public/css/panierStyle.css">


<body>

	<h2>Mes commandes </h2>
	<c:forEach var="comm" items="${sessionScope.commandeList}">
		<!-- Bouton -->
		<a href="" class="btn btn-default btn-rounded mb-4 btnComm" data-toggle="modal" 
		data-target="#modalPopupComm${comm.id}" onClick="btnCommClick(${comm.id})">N°<c:out value="${comm.id}" /></a>

		<!-- Form utilisé comme popup -->
		<div class="modal fade" id="modalPopupComm${comm.id}" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content noborder">
					<div class="modal-header text-center headpopup">
						<h4 class="modal-title w-100 font-weight-bold">
							Votre commande N°
							<c:out value="${comm.id}"/>
						</h4>
						<!-- Bouton pour fermer le popup -->
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="text-center totalpopup">
						<h5>Total : <c:out value="${comm.priceTotal}"/>€</h5>
					</div>

					<!-- Contenu du popup -->
					<div class="modal-body mx-3">
						<div class="md-form mb-5 contentpopup">
							<label class="magasinpopup">Monoprix : 6.0€ </label>
							<br>
							<label>1 P'tit lu : 3.0€ </label>
							<br>
							<label>1 Pasta box : 3.0€ </label>
						</div>
						<div class="md-form mb-5 contentpopup">
							<label class="magasinpopup">MacDo : 2.0€ </label>
							<br>
							<label>2 Big mac : 2.0€ </label>
						</div>

						<div class="md-form mb-5 contentpopup">
							<label class="magasinpopup">Franprix : 3.0€ </label>
							<br>
							<label>3 Arizona : 3.0€ </label>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:forEach>


</body>