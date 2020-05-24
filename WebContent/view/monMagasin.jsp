
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

	
<script src="../public/js/profil.js"></script>

</head>

<body>
<hr/>
	<div class="container">
		<div class="col-md-12">
			
			<h2 class="success">Mon magasin</h2>
			<form action="../ModifyMagasinForm">
				<c:set var="usr" scope="session" value="${sessionScope.user}" />
				
				<div class="form-row text-center">
						<div class="form-group col-md-1"></div></div>

				<div class="form-row text-center">
					<div class="form-group col-md-6">
						<label for="nomStore" class="col-form-label">Nom </label>
						<input type="text" class="form-control-plaintext text-center"
								name="storeName" id="storeName" value="${usr.storeName}"
								disabled>
						<input class="form-control text-center" type="text" name="newName"
							id="newName" placeholder="Nom du magasin" hidden />
					</div>

					<div class="form-group col-md-6">
						<label for="address" class="col-form-label">Adresse </label> <input
							type="text" class="form-control-plaintext text-center" id="address"
							value="${usr.address}" disabled> <input
							class="form-control text-center" type="text" name="newAddress"
							id="newAddress" placeholder="Nouvelle adresse" hidden />
					</div>
				</div>

				<div class="form-row text-center">
					<div class="form-group col-md-12">
						<input class="btn-primary btn-responsive btnModifier"
							type="submit" id="btnSaveChangeStore" value="Save Changes"
							onclick="btnSaveChangeStore()" hidden />
					</div>
					<div class="form-group col-md-12">
						<input class="btn-primary btn-responsive btnModifier"
							type="button" value="Modifier" id="btnEditStore"
							onclick="editStore()" />
					</div>
				</div>

			</form>


		</div>
	</div>
</body>
</html>