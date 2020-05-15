<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<body>

	<h1 style="font-size: 2vw">Mes commandes :</h1>
	<c:forEach var="comm" items="${sessionScope.commandeList}">
		<!-- Bouton -->
		<a href="" class="btn btn-default btn-rounded mb-4 btnComm"
			data-toggle="modal" data-target="#modalRegisterForm"
			onClick="btnCommClick(${comm})">N°<c:out value="${comm}" /></a>

		<!-- Form utilisé comme popup -->
		<div class="modal fade" id="modalRegisterForm" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">
							Votre commande N°
							<c:out value="${comm}" />
						</h4>
						<!-- Bouton pour fermer le popup -->
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>

						<!-- Contenu du popup -->
					</div>
					<div class="modal-body mx-3">
						<div class="md-form mb-5">
							<label>Surement des p'tits lu</label>
						</div>
						<div class="md-form mb-5">
							<label>C'est pas des tagliatelles ça ?</label>
						</div>

						<div class="md-form mb-4">
							<label>Nouilles instantanées du Sichuan</label>
						</div>

					</div>
				</div>
			</div>
		</div>
	</c:forEach>


</body>