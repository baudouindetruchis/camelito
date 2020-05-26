<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<!-- Bootstrap Toggle -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="../public/css/profilStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Profil</title>
<script src="../public/js/profil.js"></script>
<script src="../public/js/header.js"></script>

</head>

<body onload="includeOtherPart(); includeIntroMonture('${sessionScope.user.profilPic}')">


	<div id="includedHeader"></div>

	<div class="container">
		<div>
			<div class="col-md-12">
				<input id="checkSession" type="text" name="checkSession"
					value="${sessionScope.type}" hidden>
				<h2>Mon profil</h2>
				<form action="../ModifyProfilForm" class="form-container">

					<c:set var="usr" scope="session" value="${sessionScope.user}" />
					<div class="form-group headProfil">
						<div class="form-row">
							<div class="col-md-12 text-center">
								<h5>Bienvenue sur votre profil,</h5>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12 text-center">
								<h5>
									<c:out value="${usr.first_name}" />
									<c:out value="${usr.last_name}" />
								</h5>
							</div>
						</div>
						<div class="form-row">
							<div class="col-md-12 text-center">
								<fieldset>
									<img
										class="profil img-responsive btn btn-default btn-rounded btnComm"
										data-toggle="modal" data-target="#myForm"
										<c:set var="user" scope="session" value="${sessionScope.user}" />
										src="${usr.profilPic}" alt="Add User" width="25%" height="120" />
								</fieldset>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-12 text-center">
								<label for="imgProfil" class="col-form-label">Votre monture</label>
								<div id="introMonture">
								N'hésite pas à <b>cliquer sur l'image</b> pour sélectionner la monture de tes rêves ! 
								</div>
							</div>
						</div>
					</div>


					<div class="form-row text-center">
						<div class="form-group col-md-1"></div>
					
						<div class="form-group col-md-5">
							<label for="pseudo" class="col-form-label">Pseudo </label>
							<input type="text" class="form-control-plaintext text-center" name="pseudo"
								id="pseudo" value="${usr.pseudo}" disabled>
							<input class="form-control text-center" type="text" name="newPseudo"
							id="newPseudo" placeholder="newPseudo" hidden />
						</div>
						
						<div class="col-md-5">
							<label for="mail" class="col-form-label">Adresse mail </label>
							<input type="email" class="form-control-plaintext text-center" id="mail" value="${usr.mail}" disabled>
							<input class="form-control text-center" type="text" name="newEmail" id="newEmail" placeholder="newEmail" hidden />
						</div>
						
						<div class="form-group col-md-1"></div>
						
					</div>

					<div class="form-row text-center">
					
						<c:choose>
							<c:when test="${sessionScope.type=='2'|| sessionScope.type=='4'}">

								<div class="form-group col-md-2"></div>

								<div class="form-group col-md-3">
									<label for="mode" class="col-form-label switchLabel">Client/Association
									</label>
									<c:choose>
										<c:when test="${sessionScope.type=='4'}">
											<input type="checkbox" class="toogle text-center"
												data-toggle="toggle" data-on="Client" data-off="Association"
												data-onstyle="warning" data-offstyle="warning" id="mode"
												onchange="changeType()" checked>
										</c:when>
										<c:otherwise>
											<input type="checkbox" class="toogle" data-toggle="toggle"
												data-on="Client" data-off="Association"
												data-onstyle="warning" data-offstyle="warning" id="mode"
												onchange="changeType()">

										</c:otherwise>
									</c:choose>
								</div>
							</c:when>
						</c:choose>
						
						<div class="form-group col-md-2"></div>

						<c:choose>
							<c:when
								test="${sessionScope.type=='1' || sessionScope.type=='2' || sessionScope.type=='4'}">
								<div class="form-group col-md-3 text-center">
									<c:choose>
										<c:when test="${sessionScope.promo=='0'}">
											<label for="Promo" class="col-form-label labelMargin">Promotion </label>
											<input type="text" class="form-control text-center"
												id="promo" value="Pas concerné" disabled>
											<input class="form-control text-center" type="number"
												name="newPromo" id="newPromo" value="${usr.promotion}"
												hidden />
										</c:when>

										<c:otherwise>
											<label for="Promo" class="col-form-label labelMargin">Promotion </label>
											<input type="text" class="form-control-plaintext text-center"
												id="promo" value="${usr.promotion}" disabled>
											<input class="form-control text-center" type="number" name="newPromo"
												id="newPromo"
												value="${usr.promotion}" hidden />
										</c:otherwise>
									</c:choose>
								</div>
							</c:when>
						</c:choose>

					</div>


					<div class="form-group row" id="Mdp" hidden>
						<label for="oldPassword" class="col-form-label labelMargin">Ancien
							mot de passe: </label> <input class="form-control" type="password"
							placeholder="Ancien Mot de passe" name="oldPassword"> <label
							for="newPassword" class="col-form-label labelMargin">Nouveau
							mot de passe: </label> <input class="form-control" type="password"
							placeholder="Nouveau mot de passe" name="newPassword"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							title="Le mot de passe doit contenir au moins un chiffre et une majuscule et minuscule, il doit avoir au moins 8 caractères">
						<label for="secondPassword" class="col-form-label labelMargin">Nouveau
							mot de passe: </label> <input class="form-control" type="password"
							placeholder="Vérification du mot de passe" name="secondPassword">
					</div>

					<div class="form-row text-center">
						<div class="form-group col-md-12">
							<input class="btn btn-primary btn-responsive btnModifier"
								type="submit" id="btnSaveChange" value="Save Changes"
								onclick="saveChange()" hidden />
						</div>
						<div class="form-group col-md-12 marginBottom">
							<input class="btn btn-primary btn-responsive btnModifier"
								type="button" value="Modifier" id="btnGoToEdit"
								onclick="goToEdit()" />
						</div>
					</div>

				</form>
			</div>

		</div>

	</div>
	
	<c:choose>
				<c:when test="${sessionScope.type=='3'}">
					<div id="includeStore" class="col-md-12"></div>
				</c:when>

				<c:when test="${sessionScope.type=='1'} ">
					<div id="includeReussite" class="col-md-12"></div>
				</c:when>

				<%-- <c:when test="${sessionScope.type=='2' }">
					<div id="includeReussite" class="col-md-12" hidden></div>
				</c:when> --%>
				<c:when test="${sessionScope.type=='4' }">
					<div id="includeReussite" class="col-md-12"></div>
				</c:when>
			</c:choose>

	<!-- Form utilisé comme popup -->
	<div class="modal fade" id="myForm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content noborder">
				<div class="modal-header text-center headpopup">
					<h4 class="modal-title w-100 font-weight-bold titrepopup">Changer de monture</h4>
					<!-- Bouton pour fermer le popup -->
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<!-- Intro du titre -->
				<div class="text-center totalpopup">
					<h5 id="intro">
						Le choix de votre monture est une étape importante pour devenir un
						utilisateur experimenté de Camelito. Chaque chameau est
						unique en son genre, ce qui vous permet de trouver votre nouveau
						meilleur ami sans difficulté.
					</h5>
				</div>

				<!-- Contenu du popup -->
				<div class="modal-body mx-3">
					<form action="../ChangePicForm" class="form" name="inscription">
						<div class="md-form">
							<div class="contentpopup">
								<div class="row" style="margin-bottom: 15px;">
									<div class="col-md-5 inputGroup">
										<input type="radio" class="checkboxAvatar" id="pic4" name="pic"  value="../public/images/Camel4.png" required="required">
										<label for="pic4"> Augustus </label>
									</div>
									<div class="col-md-4 text-center">
										<img class="editPic img-responsive center-block"
											src="../public/images/Camel4.png" alt="Camel4" width="25%"
											id="chooseImg" height="100" />
									</div>
									<div class="col-md-3"></div>
								</div>
								<div>
									<div id="intro">
										<div id="introAugustus">	
										<b>Augustus</b> est le chameau de compagnie parfait: il
										restera toujours à vos côtés.<br> Ses passions: <b>manger
										et dormir</b>. Son tempérament calme et posé le rend de très bonne
										compagnie. Attention cependant, il peut avoir une
										réaction exagérée lorsque quelqu'un lui dérobe ses doux mets.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="md-form">
							<div class="contentpopup">
							
							<div class="row" style="margin-bottom: 15px;">
									<div class="col-md-5 inputGroup">
										<input type="radio" class="checkboxAvatar" id="pic2" name="pic"  value="../public/images/Camel2.png" required="required">
										<label for="pic2"> Philibert </label>
									</div>
									<div class="col-md-4 text-center">
										<img class="editPic img-responsive center-block"
											src="../public/images/Camel2.png" alt="Camel2" width="25%"
											id="chooseImg" height="100" />
									</div>
									<div class="col-md-3"></div>
								</div>
								<div>
									<div id="intro">
										<div id="introPhilibert">
										<b>Philibert</b> est le petit jeunot de notre troupe. <br>Il est
										<b>énergique et petit blagueur</b>. Il fera tout ce qui est
										possible afin de vous rendre heureux. Son peulage
										doux et soyeux fait de lui la parfaite peluche pour toutes
										siestes. Attention cependant son jeune âge et son
										manque d'experience peuvent le rendre maladroit.
										</div>
									</div>
								</div>
						
							</div>
						</div>
						<div class="md-form">
							<div class="contentpopup">
							
							<div class="row" style="margin-bottom: 15px;">
									<div class="col-md-5 inputGroup">
										<input type="radio" class="checkboxAvatar" id="pic3" name="pic"  value="../public/images/Camel3.png" required="required">
										<label for="pic3"> Marthe </label>
									</div>
									<div class="col-md-4 text-center">
										<img class="editPic img-responsive center-block"
											src="../public/images/Camel3.png" alt="Camel3" width="25%"
											id="chooseImg" height="100" />
									</div>
									<div class="col-md-3"></div>
								</div>
								<div>
									<div id="intro">
										<div id="introMarthe">
										<b>Marthe </b>est la doyenne du groupe.<br> Ses 57 ans
										d'expérience dans l'aide aux bipèdes lui confèrent une
										<b>assurance indégniable</b>. C'est la chamelle à avoir si
										vous voulez connaître le bons plans de Camelito. C'est
										l'amie qu'il faut si vous aimez les soirées au coin de la
										cheminée accompagnées de tisane et de jeu de cartes.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="md-form">
							<div class="contentpopup">
							
							<div class="row" style="margin-bottom: 15px;">
									<div class="col-md-5 inputGroup">
										<input type="radio" class="checkboxAvatar" id="pic5" name="pic"  value="../public/images/Camel5.png" required="required">
										<label for="pic5"> Roseline </label>
									</div>
									<div class="col-md-4 text-center">
										<img class="editPic img-responsive center-block"
											src="../public/images/Camel5.png" alt="Camel5" width="25%"
											id="chooseImg" height="100" />
									</div>
									<div class="col-md-3"></div>
								</div>
								<div>
									<div id="intro">
										<div id="introRoseline">
										<b>Roseline </b>est la sportive du groupe.<br> Du haut de
										ses 20km/h en vitesse de pointe vous ne serez jamais en
										retard. Elle fait très attention à ce qu'elle achète
										: tout doit être bon pour son organisme. Avec elle adieu les <i>Mcdo</i>!
										<b>Infatiguable</b>, elle peut cependant vite devenir
										fatigante si vous ne lui faites pas faire une balade dans la
										journée.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="md-form">
							<div class="contentpopup">
							
							<div class="row" style="margin-bottom: 15px;">
									<div class="col-md-5 inputGroup">
										<input type="radio" class="checkboxAvatar" id="pic1" name="pic"  value="../public/images/Camel.png" required="required">
										<label for="pic1"> Ferdinand </label>
									</div>
									<div class="col-md-4 text-center">
										<img class="editPic img-responsive center-block"
											src="../public/images/Camel.png" alt="Camel" width="25%"
											id="chooseImg" height="100" />
									</div>
									<div class="col-md-3"></div>
								</div>
								<div>
									<div id="intro">
										<div id="introFerdinand">
										<b>Ferdinand </b> est le bon vivant du groupe. <br> Fan
										incontesté de Dionysos plus connu sous le nom de Bacchus chez
										nos amis les romains, 'dinand ne manque pas une
										occasion pour <b>passer une bonne soirée</b>. Ses mots d'ordre sont
										boisson et gateaux apéro Attention cependant à ne pas
										oublier que les chameaux peuvent boire jusqu'à 132 litres en
										10 minutes!
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="text-center">
							<input class="btn-primary btn-responsive btn-md" type="submit"
								id="ChangePic" value="Valider" />
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

<div id="includedFooter"></div>

</body>

</html>