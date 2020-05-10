<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8" %>

<head>
	<title>Camelito - Login</title>
	<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="../public/css/login.css">
	
	
	<link rel="icon" href="../public/images/logo_noback.png">
	<script src="../public/js/login.js"></script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<form action="../ConnexionForm">

					<fieldset class="ref">
						<img class="img-responsive" src="../public/images/logo_camelito_marron.png" alt="Logo Camelito" width="120" height="120" />
						<h1>Camelito</h1>
					</fieldset>

					<input class="form-control" type="text" name="userName" placeholder="User Name" required/></br>
					<input class="form-control" id="password" type="password" name="password" placeholder="Mot de passe" required /></br>	
					<input type="checkbox" id="pswVisible" name="pswVisible" onclick="showPassword()"> Afficher le mot de passe
					
					<input class="btn btn-basic btn-block" type="submit" value="Se connecter" />
					<fieldset class="ref">
						<a href="#" style="color:black" onclick="openForm()">S'inscrire</a><br>
						<a href="#" style="color:black">Mot de passe oublié </a>
						
					</fieldset>
				</form>

				<div class="form-popup" id="myForm">
					<form action="../InscriptionForm" class="form-container" name="inscription">
						<img src="https://lh3.googleusercontent.com/proxy/rEyvARXpQlApk13rYq6iBXF0xNFC3R3sxiwCsRpE6FV8d7HavcdGTboyb_6SBK-kWJSC7t86dLUWFUJmlINrGmNopeJpmbokzw1LOM5CLwayIITKAg" onclick="closeForm()" alt="close" class="close_button">
						<h1>Inscription</h1>
						<br>

						<input type="pseudo" placeholder="Pseudo" name="pseudo" required class="field">
						<input type="lastname" placeholder="Nom" name="lastname" required class="field">
						<input type="firstname" placeholder="Prenom" name="firstname" required class="field">
						<input type="email" placeholder="Adresse email" name="email" required class="field">
	
						<label for="year">Promotion : </label>
							<select type="number" id="year" name="year" required class="field">
								<option > Choisir</option>					
								<option value=3>A1</option>
								<option value=4>A2</option>
								<option value=5>A3</option>
								<option value=1>P1</option>
								<option value=2>P2</option>
								<option value=1>I1</option>
								<option value=2>I2</option>
								<option value=0>Autre</option>
							</select>
					
						<input  id="passwordInsc" type="password" placeholder="Mot de passe" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" 
						title="Le mot de passe doit contenir au moins un chiffre et une majuscule et minuscule, il doit avoir au moins 8 caractères"required class="field">
						<input id="passwordInsc" type="password" placeholder="Vérification du mot de passe" name="secondPassword" required class="field">
						<input type="checkbox" id="pswVisible2" name="pswVisible2" onclick="showPasswordInscr()"> Afficher les mots de passe
						<br>
						<div class="radio_but">
							<input type="radio" id="client" name="categorie" value="Client"required> Client
							<input type="radio" id="commercant" name="categorie" value="Commercant"required> Commerçant
							<input type="radio" id="asso" name="categorie" value="Asso"required> Association
						</div>
						<br>
						<button type="submit" class="btnn">Valider</button>
					</form>
				</div>

			</div>

			<div class="center_div">
				<img src="../public/images/ecommerce.jpg" class="animated_img">
				<div class="behind_center">
					<h6>Ils nous font confiance</h6>
					<br>
					<img class="marque" src="https://www.somocergroup.com/wp-content/uploads/2017/11/monoprix.png">
					<img class="marque" src="https://www.bastienfiguie.com/wp-content/uploads/2015/05/FRANPRIX-LOGO-VERTICAL_detoure_HD.png">
					<img class="marque" src="https://www.vectorsland.com/imgd/l25301-boulangerie-leveneur-logo-60344.png">
					<br>
					<br>
					<h6>Intéressé par nos services ?</h6>
					<br>
					<img src="https://i0.wp.com/www.ucaubenas.com/wp-content/uploads/email-logo.png?fit=256%2C256" class="icon"> camelito.illico@presto.fr <br>
					<img src="https://i.ya-webdesign.com/images/telephone-icon-png-6.png" class="icon"> 06 77 88 77 88 <br> <br>
					<img src="https://groupe-reussite.fr/wp-content/uploads/2020/04/logo_isep.png" alt="Isep" class="isep"> 10 Rue de Vanves, 92130 <br> Issy-les-Moulineaux

				</div>
			</div>

			<div class="right_div">
				<img src="../public/images/hands.jpg" class="animated_img">
				<div class="behind_right">
					<h6>Notre équipe</h6>
					<img class="equipe" src="../public/images/equipe.png">
				</div>
			</div>

		</div>
	</div>
	

	
</body>

</html>