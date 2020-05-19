<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<head>
<title>Camelito - Login</title>
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
	href="../public/css/pageStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/login.css">

<link rel="icon" href="../public/images/logo_noback.png">
<script src="../public/js/login.js"></script>
</head>

<body onload="includeOtherPartOnLoad()">
		<div class="row">
			<div class="col-sm-4">

				<form action="../ConnexionForm">
					<c:set var="connectionMsg" scope="session"
						value="${sessionScope.connectionMsg}" />

					<fieldset class="ref">
						<a href="index.jsp"> <img class="img-responsive"
							src="../public/images/logo_camelito_marron.png"
							alt="Logo Camelito" width="120" height="120" />
						</a>
						<h1>Camelito</h1>
					</fieldset>

					<input class="form-control" type="text" name="userName"
						placeholder="User Name" required="required" /></br> <input
						class="form-control" id="password" type="password" name="password"
						placeholder="Mot de passe" required="required" /></br> <a
						style="color: red"><c:out value="${connectionMsg}" /></a><br>
					<div class="form-check">

						<input type="checkbox" class="form-check-input" id="pswVisible"
							name="pswVisible" onclick="showPassword()"> <label
							class="labelito" for="exampleCheck1">Afficher le
							mot de passe</label>

					</div>
					<input class="btn btn-basic btn-block" type="submit"
						value="Se connecter" />
					<fieldset class="ref">
						<a href="#" style="color: black" onclick="openForm()">S'inscrire</a><br>
						<a href="#" style="color: black">Mot de passe oublié</a><br>
						<a href="#" style="color: black" onclick="includeFAQ()">FAQ</a>
					</fieldset>
				</form>

				<div class="form-popup" id="myForm">
					<form action="../InscriptionForm" class="form-container"
						name="inscription">
						<img src="../public/images/times.png" onclick="closeForm()"
							alt="close" class="close_button">
						<h1>Inscription</h1>
						<br> <br> <a style="color: red"><c:out
								value="${sessionScope.inscrPseudoMsg}" /></a><br> <input
							type="pseudo" placeholder="Pseudo" name="pseudo"
							required="required" class="field"> <input type="lastname"
							placeholder="Nom" name="lastname" required="required"
							class="field"> <input type="firstname"
							placeholder="Prenom" name="firstname" required="required"
							class="field"> <input type="email"
							placeholder="Adresse email" name="email" required="required"
							class="field"> <label for="year">Promotion :</label> <select
							type="number" id="year" name="year" required="required"
							class="field">
							<option>Choisir</option>
							<option value="3">A1</option>
							<option value="4">A2</option>
							<option value="5">A3</option>
							<option value="1">P1</option>
							<option value="2">P2</option>
							<option value="1">I1</option>
							<option value="2">I2</option>
							<option value="0">Autre</option>
						</select> <input id="passwordInsc1" type="password"
							placeholder="Mot de passe" name="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Le mot de passe doit contenir au moins un chiffre, une majuscule et une minuscule, il doit aussi avoir au moins 8 caractères"
							required="required" class="field"> 
							<input
							id="passwordInsc2" type="password"
							placeholder="Vérification du mot de passe" name="secondPassword"
							required="required" class="field"> <br> <a
							style="color: red"><c:out value="${sessionScope.inscrPswMsg}" /></a><br>
						<div class="form-check2">
							<input type="checkbox" class="form-check-input" id="pswVisible2"
								name="pswVisible2" onclick="showPasswordInscr()"> <label
								class="form-check-label" for="exampleCheck1">Afficher
								les mots de passes</label>
						</div>
						<br>
						<div class="radio_but">
							<input type="radio" id="client" name="categorie" value="Client"
								required="required"> Client <input type="radio"
								id="commercant" name="categorie" value="Commercant"
								required="required">Commerçant <input type="radio"
								id="asso" name="categorie" value="Asso" required="required">Association
						</div>
						<br>
						<button type="submit" class="btnn">Valider</button>
					</form>
				</div>
			</div>


			<div id="includeCamelitoInfo" class="col-sm-4"></div>
			<div id="includeCamelitoPrez" class="col-sm-4"></div>
			<div id="includeFAQ" class="col-sm-8"></div>

		</div>

</body>

</html>
