<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="../public/css/validationProfilStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
<title>Camelito - Validation des profil</title>
<script src="../public/js/header.js"></script>
<script src="../public/js/validation.js"></script>

</head>


<body onload="includeHeaderAndCheckUser()">
	<div id="includedHeader"></div>

	<div class="container">
		<div class="col-md-12"
			style="background-color: bisque; padding-bottom: 10px;">
			<input id="checkSession" type="text" name="checkSession"
				value="${sessionScope.type}" hidden>

			<h2>Validation des profils</h2>

			<div class="text-center">
				<label for="mode" class="col-form-label text-center">Changer
					de mode (Comptes À valider - Validés) </label>
			</div>

			<div class="text-center">
				<c:choose>
					<c:when test="${sessionScope.surLesValides=='true'}">
						<input type="checkbox" class="toogle" data-toggle="toggle"
							data-on="À valider" data-off="Validés" data-onstyle="warning"
							data-offstyle="warning" id="checkbox" onchange="unvalidate()">
					</c:when>
					<c:otherwise>
						<input type="checkbox" class="toogle" data-toggle="toggle"
							data-on="À valider" data-off="Validés" data-onstyle="warning"
							data-offstyle="warning" id="checkbox" onchange="unvalidate()"
							checked>
					</c:otherwise>

				</c:choose>
			</div>
		</div>

		<div class="col-md-12"
			style="margin-top: 15px; background-color: bisque; padding-bottom: 15px; margin-bottom: 15px;">
			<c:choose>
				<c:when test="${sessionScope.surLesValides=='true'}">
					<div id="aValider" hidden>

						<div class="form-row">
							<c:forEach var="user" items="${sessionScope.listUsers}">
								<div class="col-md-4 paddingClean">
									<div class="card text-center">
										<div class="card-body">
											<h5 class="card-title">
												<label>${user.pseudo}</label>
											</h5>
											<div class="col-md-12">
												<label>${user.mail}</label>
											</div>
											<label id="leTypeMembre"><script>typeMembre('${user.type}')</script></label>
											<div class="form-row">
												<div class="col-md-5 btn btn-success"
													onclick="validateProfil(${user.id},false,true)">Valider
													le compte</div>
												<div class="col-md-5 btn btn-success"
													onclick="validateProfil(${user.id},true,true)">Valider
													en type Client</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

					<div id="valide">

						<div class="form-row">

							<c:forEach var="user" items="${sessionScope.listValideUsers}">
								<div class="col-md-4 paddingClean">
									<div class="card text-center">
										<div class="card-body">
											<h5 class="card-title">
												<label>${user.pseudo}</label>
											</h5>
											<div class="col-md-12">
												<label>${user.mail}</label>
											</div>
											<label id="leTypeMembre"><script>typeMembre('${user.type}')</script></label>
											<div class="form-row">
												<div class="col-md-12 btn btn-danger"
													onclick="validateProfil(${user.id},false,false)">Invalider
													le compte</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

				</c:when>

				<c:otherwise>

					<div id="aValider">

						<div class="form-row ">

							<c:forEach var="user" items="${sessionScope.listUsers}">
								<div class="col-md-4 paddingClean">
									<div class="card text-center">
										<div class="card-body">
											<h5 class="card-title">
												<label>${user.pseudo}</label>
											</h5>
											<div class="col-md-12">
												<label>${user.mail}</label>
											</div>
											<label id="leTypeMembre"><script>typeMembre('${user.type}')</script></label>
											<div class="form-row">
												<div class="col-md-5 btn btn-success"
													onclick="validateProfil(${user.id},false,true)">Valider
													le compte</div>
												<div class="col-md-5 btn btn-success"
													onclick="validateProfil(${user.id},true,true)">Valider
													en type Client</div>
											</div>
										</div>
									</div>
									</div>
							</c:forEach>

						</div>
					</div>

					<div id="valide" hidden>

						<div class="form-row ">

							<c:forEach var="user" items="${sessionScope.listValideUsers}">
								<div class="col-md-4 paddingClean">
									<div class="card text-center">
										<div class="card-body">
											<h5 class="card-title">
												<label>${user.pseudo}</label>
											</h5>
											<div class="col-md-12">
												<label>${user.mail}</label>
											</div>
											<label id="leTypeMembre"><script>typeMembre('${user.type}')</script></label>
											<div class="form-row">
												<div class="col-md-12 btn btn-danger"
													onclick="validateProfil(${user.id},false,false)">Invalider
													le compte</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>


					</div>

				</c:otherwise>

			</c:choose>
		</div>

	</div>
	<div id="includedFooter"></div>
</body>
</html>