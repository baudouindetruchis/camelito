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



<link rel="stylesheet" type="text/css" href="../public/css/panierStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
	<script src="../public/js/header.js"></script>
	<script src="../public/js/panier.js"></script>
</head>

<body onload="onload()">
	<input id="checkSession" type="text" name="checkSession"
		value="${sessionScope.type}" hidden=true>
	<div id="includedHeader"></div>

	<div class="container">
		<div id="ordersDiv"></div>
		<div id="cartDiv"></div>
		<div id="fin">
			<button class="btn btn-default btn-rounded mb-4 btnFinAnnuler"
				data-toggle="modal" data-target="#modalConfirmAnnuler">Annuler</button>
			<button class="btn btn-default btn-rounded mb-4 btnFinPayer"
				data-toggle="modal" data-target="#modalConfirmPayer"
				onClick="btnActClick('pay')">Payer</button>
		</div>

		<!-- Popup pour le bouton Annuler -->
		<div class="modal fade" id="modalConfirmAnnuler" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content noborder">
					<div class="modal-header text-center headpopup">
						<h4 class="messageAnnulerPayer">Voulez-vous vraiment annuler
							votre commande ? (Tous vos articles vont être supprimés de votre
							panier)</h4>

						<!-- Bouton pour fermer le popup -->
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<!-- Contenu du popup -->
					<div class="modal-body mx-3">
						<div class="md-form mb-5 popupConfirm">
							<button type="button" data-dismiss="modal" aria-label="Close" class="btn btn-default btn-rounded mb-4 btnNon">Non</button>
							<button type="button" data-dismiss="modal" aria-label="Close" class="btn btn-default btn-rounded mb-4 btnOui" onClick="btnActClick('ann')">Oui</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalConfirmPayer" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content noborder">
					<div class="modal-header text-center headpopupPayer">
						<h4 class="messageAnnulerPayer">Vous allez automatiquement
							être redirigé vers une plateforme de paiement, veuillez
							patienter...</h4>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="includedFooter"></div>
</body>