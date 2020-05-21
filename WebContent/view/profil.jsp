<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<!-- Bootstrap Toggle -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="../public/css/profilStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Profil</title>
<script src="../public/js/profil.js"></script>
<script src="../public/js/header.js"></script>

</head>

<body onload="includeOtherPart()">


	<div id="includedHeader"></div>

	<div class="container">
		<div class="row">
			<div class="col-sm-6">
			<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>
				<h2>Mon profil</h2>
				<form action="../ModifyProfilForm" >

					<c:set var="usr" scope="session" value="${sessionScope.user}" />

				<fieldset>
					<img class="profil img-responsive"
					<c:set var="user" scope="session" value="${sessionScope.user}" />
						src="${usr.profilPic}" alt="Add User" width="25%"
						height="120"/>
						
						<a href="#" onclick="openForm() "><img class="edit img-responsive"
						src="../public/images/edit.png" alt="edit" width="5%"
						height="20"/></a>
				</fieldset>
				
				
					<div class="form-group row">
						<label for="name" class="col-form-label">Nom : </label>
						<div class="col-sm-7">
							<p class="form-control-plaintext" id="name">
								<c:out value="${usr.last_name}" />
							</p>
						</div>
					</div>

					<div class="form-group row">
						<label for="surname" class="col-form-label">Prénom : </label>
						<div class="col-sm-7">
							<p class="form-control-plaintext" id="surname">
								<c:out value="${usr.first_name}" />
							</p>
						</div>
					</div>

					<div class="form-group row">
						<label for="pseudo" class="col-form-label">Pseudo : </label>
						<div class="col-sm-7">
							<input type="text" class="form-control-plaintext" name="pseudo"
								id="pseudo" value="${usr.pseudo}" disabled>
						</div>
						<input class="form-control" type="text" name="newPseudo"
							id="newPseudo" placeholder="newPseudo" hidden /></br>
						<!-- old:   class="form-control-plaintext" -->
					</div>


					<div class="form-group row">
						<label for="mail" class="col-form-label">Adresse mail : </label>
						<div class="col-sm-7 edit">
							<input type="email" class="form-control-plaintext" id="mail"
								value="${usr.mail}" disabled>
						</div>
						<input class="form-control" type="text" name="newEmail"
							id="newEmail" placeholder="newEmail" hidden /></br>
						<!-- old:   class="form-control-plaintext" -->
					</div>
					<c:choose>

						<c:when test="${sessionScope.type=='2'|| sessionScope.type=='4'}">
							<div class="form-group row">
		
								<label for="mode" class="col-form-label">Client/Association : </label> 
								<c:choose>
									<c:when test="${sessionScope.type=='4'}">
										<input type="checkbox" class="toogle" data-toggle="toggle" data-on="Client" data-off="Association" data-onstyle="warning" data-offstyle="warning" id="mode" onchange="changeType()" checked>			
									</c:when>
									<c:otherwise>
									<input type="checkbox" class="toogle" data-toggle="toggle" data-on="Client" data-off="Association" data-onstyle="warning" data-offstyle="warning" id="mode" onchange="changeType()">			
									
									</c:otherwise>
								</c:choose>
								
							</div>
						</c:when>
					</c:choose>
					<c:choose>
							<c:when test="${sessionScope.type=='1' || sessionScope.type=='2' || sessionScope.type=='4'}">
							<c:choose>
								<c:when test="${sessionScope.promo=='0'}">
									<div class="form-group row">
										<label for="Promo" class="col-form-label">Promotion :
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" id="promo"
												value="Pas concerné" disabled>
										</div>
									</div>
								</c:when>

								<c:otherwise>
									<div class="form-group row">
										<label for="Promo" class="col-form-label">Promotion :
										</label>
										<div class="col-sm-7">
											<input type="text" class="form-control-plaintext" id="promo"
												value="${usr.promotion}" disabled>
										</div>
										

									</div>

								</c:otherwise>								
							</c:choose>

						</c:when>
					</c:choose>
					
					
					<div>
					<input class="form-control" type="number" name="newPromo" id="newPromo"placeholder="Année de diplôme, 0 si non concerné" hidden /></br>
							
					</div>
					
					<div class="form-group row" id="Mdp" hidden>
						<label for="oldPassword" class="col-form-label">Ancien mot
							de passe : </label> <input class="form-control" type="password"
							placeholder="Ancien Mot de passe" name="oldPassword"> <label
							for="newPassword" class="col-form-label">Nouveau mot de
							passe : </label> <input class="form-control" type="password"
							placeholder="Nouveau mot de passe" name="newPassword"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Le mot de passe doit contenir au moins un chiffre et une majuscule et minuscule, il doit avoir au moins 8 caractères">
						<label for="secondPassword" class="col-form-label">Nouveau
							mot de passe : </label> <input class="form-control" type="password"
							placeholder="Vérification du mot de passe" name="secondPassword">
					</div>

					<input class="btn-primary btn-responsive" type="submit"
						id="btnSaveChange" value="Save Changes" onclick="saveChange()"
						hidden />
					<input class="btn-primary btn-responsive" type="button"
						value="Modifier" id="btnGoToEdit" onclick="goToEdit()" />

				</form>
			</div>

			<c:choose> 
				<c:when test="${sessionScope.type=='3'}">
					<div id="includeStore" class="col-sm-6"></div>
				</c:when>
				
				<c:when test="${sessionScope.type=='1'} ">
					<div id="includeReussite" class="col-sm-6"></div>
				</c:when>
				
				<c:when test="${sessionScope.type=='2' }">
					<div id="includeReussite" class="col-sm-6" hidden></div>
				</c:when>
				<c:when test="${sessionScope.type=='4' }">
					<div id="includeReussite" class="col-sm-6" ></div>
				</c:when>
			</c:choose>
				

			</div>
			
			</div>


			<div class="form-popup" id="myForm">
					<form action="../ChangePicForm" class="form-container"
						name="inscription">
						<img src="../public/images/times.png" onclick="closeForm()"
							alt="close" class="close_button">
						<h1>Changer son avatar</h1>
						
						<div>
						<label for="chooseImg" class="col-form-label">Avatars disponibles : </label> 
						
						
						<div class="radio_but">
							<div>
							<input type="radio" id="sansImage" name="pic" value="../public/images/Add_User.png"
								required="required">								
								<img class="profil img-responsive"
									src="../public/images/Add_User.png" alt="Add User" width="25%" id="chooseImg"
									height="120"/><br>
									</div>
							<div>
								<input type="radio"
									id="Camel4" name="pic" value="../public/images/Camel4.png"
									required="required">
									<img class="profil img-responsive"
										src="../public/images/Camel4.png" alt="Camel4" width="25%" id="chooseImg"
										height="120"/><br>
							</div>
							
							
							<div>
								<input type="radio"
								id="Camel2" name="pic" value="../public/images/Camel2.png"
								required="required">
								<img class="profil img-responsive"
									src="../public/images/Camel2.png" alt="Camel2" width="25%" id="chooseImg"
									height="120"/><br>
							</div>
							
							
							<div>
								<input type="radio"
								id="Camel4" name="pic" value="../public/images/Camel3.png"
								required="required">
								<img class="profil img-responsive"
									src="../public/images/Camel3.png" alt="Camel4" width="25%" id="chooseImg"
									height="120"/><br>
							</div>
							
							<div>
								<input type="radio"
								id="Camel5" name="pic" value="../public/images/Camel5.png"
								required="required">
								<img class="profil img-responsive"
									src="../public/images/Camel5.png" alt="Camel5" width="25%" id="chooseImg"
									height="120"/><br>
							</div>
							
							<div>
								<input type="radio"
								id="Camel" name="pic" value="../public/images/Camel.png"
								required="required">
								<img class="profil img-responsive"
									src="../public/images/Camel.png" alt="Camel" width="25%" id="chooseImg"
									height="120"/><br>
							</div>
							
							
						</div>
						</div><br>
						<input class="btn-primary btn-responsive" type="submit" id="ChangePic"
						  value="Valider mon choix"
						 />
						 
					</form>
				</div>







</body>

</html>