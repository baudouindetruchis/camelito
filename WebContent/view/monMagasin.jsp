
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Header sample</title>
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
<link rel="stylesheet" type="text/css"
	href="../public/css/header_sample.css">
	
	<script src="../public/js/profil.js"></script>

</head>

<h2 class="success">Mon magasin</h2>
				
				<form action="../ModifyMagasinForm">

					<c:set var="usr" scope="session" value="${sessionScope.user}" />


					<div class="form-group row">
						<label for="nomStore" class="col-form-label">Nom : </label>
						<div class="col-sm-7">
							<input type="text" class="form-control-plaintext" name="storeName"
								id="storeName" value="${usr.storeName}" disabled>
						</div>
						<input class="form-control" type="text" name="newName"
							id="newName" placeholder="Nom du magasin" hidden /></br>
					</div>


					<div class="form-group row">
						<label for="address" class="col-form-label">Adresse  : </label>
						<div class="col-sm-7 edit">
							<input type="text" class="form-control-plaintext" id="address"
								value="${usr.address}" disabled>
						</div>
						<input class="form-control" type="text" name="newAddress"
							id="newAddress" placeholder="Nouvelle adresse" hidden /></br>
					</div>
				
					
					

					<input class="btn-primary btn-responsive" type="submit"
						id="btnSaveChangeStore" value="Save Changes" onclick="btnSaveChangeStore()"
						hidden /> <input class="btn-primary btn-responsive" type="button"
						value="Modifier" id="btnEditStore" onclick="editStore()" />

				</form>
	  					
	  					

</body>
</html>