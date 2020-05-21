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
						<h1>Changer de monture</h1>
						<p id="intro">Le choix de votre monture est une étape importante pour devenir un utilisateur experimenté de Camelito.
						<br>Chaque chameau est unique en son genre, ce qui vous permet de trouver votre nouveau meilleur ami sans difficulté <br>
						N'hésitez pas à cliquer sur la photo des différentes montures pour voir leur description </p>
									
							<br><input type="radio"
								id="pic" name="pic" value="../public/images/Camel4.png"
								required="required">
							<div data-toggle="collapse" data-target="#Camel4"><img class="editPic img-responsive"
								src="../public/images/Camel4.png" alt="Camel4" width="25%" id="chooseImg"
								height="100"/></div>
  								<div id="Camel4" class="collapse">
    								<p id="intro"><b>Augustus</b> est le chameau de compagnie parfait : il restera toujours à vos côtés.<br> 
													Ses passions : manger et dormir. Son tempérament calme et posé le rend de très bonne compagnie.<br> Attention cependant,
								 					il peut avoir une réaction exagérée lorsque quelqu'un lui dérobe ses doux mets.</p>
  								</div>
	
							<br><input type="radio"
								id="pic" name="pic" value="../public/images/Camel2.png"
								required="required">
							<div data-toggle="collapse" data-target="#Camel2"><img class="editPic img-responsive"
									src="../public/images/Camel2.png" alt="Camel2" width="25%" id="Philibert"
									height="100"/></div>
  								<div id="Camel2" class="collapse">
    								<p id="intro"><b>Philibert</b> est le petit jeunot de notre troupe. Il est énergique et petit blagueur.<br>
										Il fera tout ce qui est possible afin de vous rendre heureux. <br>Son peulage doux et soyeux fait de lui la parfaite peluche 
										pour toutes siestes. <br>Attention cependant son jeune âge et son manque d'experience peuvent le rendre maladroit.</p>
								</div>
							
							
							<br><input type="radio"
								id="pic" name="pic" value="../public/images/Camel3.png"
								required="required">
							<div data-toggle="collapse" data-target="#Camel3"><img class="editPic img-responsive"
									src="../public/images/Camel3.png" alt="Camel3" width="25%" id="chooseImg"
									height="100"/></div>
  								<div id="Camel3" class="collapse">
    								<p id="intro"><b>Marthe </b>est la doyenne du groupe.<br> Ses 57 ans d'expérience dans l'aide aux bipèdes lui confèrent une assurance indégniable.<br> 
										C'est la chamelle à avoir si vous voulez connaître le bons plans de Camelito. <br>C'est l'amie qu'il faut si vous aimez
										les soirées au coin de la cheminée accompagnées de tisane et de jeu de cartes.</p>
							</div>
		
							
							
							<br><input type="radio"
								id="pic" name="pic" value="../public/images/Camel5.png"
								required="required">
							<div data-toggle="collapse" data-target="#Camel5"><img class="editPic img-responsive"
									src="../public/images/Camel5.png" alt="Camel5" width="25%" id="chooseImg"
									height="100"/></div>
  								<div id="Camel5" class="collapse">
    								<p id="intro"><b>Roseline </b>est la sportive du groupe.<br>
    								 Du haut de ses 20km/h en vitesse de pointe vous ne serez jamais en retard. <br>
    								 Elle fait très attention à ce qu'elle achète : tout doit être bon pour son organisme. Avec elle adieu les <i>Mcdo'</i>!
    								 <br>Infatiguable, elle peut cependant vite le devenir si vous ne lui faites pas faire une balade dans la journée
    								
    								</p></div>
							
							
							<br><input type="radio"
								id="pic" name="pic" value="../public/images/Camel.png"
								required="required">
							<div data-toggle="collapse" data-target="#Camel"><img class="editPic img-responsive"
									src="../public/images/Camel.png" alt="Camel" width="25%" id="chooseImg"
									height="100"/></div>
  								<div id="Camel" class="collapse">
    								<p id="intro"><b>Ferdinand </b> est le bon vivant du groupe. <br>
    								Fan incontesté de Dionisos plus connu sous le nom de Bacchus chez nos amis les romains, <br>
    								'dinand ne manque pas une occasion pour passer une bonne soirée. Ses mots d'ordre sont boisson et gateaux apéro	
    								<br>Attention cependant à ne pas oublier que les chameaux peuvent boire jusqu'à 132 litres en 10 minutes!</p></div>
							
						
						
						<input class="btn-primary btn-responsive" type="submit" id="ChangePic"
						  value="Valider mon choix"
						 />
						 
					</form>
				</div>







</body>

</html>