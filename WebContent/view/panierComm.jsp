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
						<!-- Pour chaque store -->
						<c:forEach var="subComm" items="${comm.commandTotal}">
						<div class="md-form mb-5 contentpopup">
							<label class="magasinpopup"> <c:out value="${subComm.storeName}"/> :  <c:out value="${subComm.priceStore}"/>€ </label>
							<!-- Pour chaque article -->
							<c:forEach var="art" items="${subComm.commandToStore}">
								<br>
								<label>
									(<c:out value="${art.quantity}"/>)
									<c:out value="${art.name}"/> : 
									<c:out value="${art.selling_price}"/>€
								</label>
							</c:forEach>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>


</body>