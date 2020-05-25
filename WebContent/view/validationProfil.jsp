<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"crossorigin="anonymous">
	
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
<scriptsrc="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="../public/css/validationProfilStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
<title>Camelito - Validation des profil</title>
<script src="../public/js/header.js"></script>
<script src="../public/js/validation.js"></script>

</head>


<body onload="includeHeaderAndCheckUser()">
	<div id="includedHeader"></div>
	<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>

	<div class="container">
		<label for="mode" class="col-form-label">Client/Association :
		</label>

		<c:choose>
			<c:when test="${sessionScope.surLesValides=='true'}">
				<input type="checkbox" class="toogle" data-toggle="toggle"
					data-on="false" data-off="Validés" data-onstyle="warning"
					data-offstyle="warning" id="checkbox" onchange="unvalidate()">
			</c:when>
			<c:otherwise>
				<input type="checkbox" class="toogle" data-toggle="toggle"
					data-on="false" data-off="Validés" data-onstyle="warning"
					data-offstyle="warning" id="checkbox" onchange="unvalidate()"
					checked>
			</c:otherwise>

		</c:choose>





		<c:choose>
			<c:when test="${sessionScope.surLesValides=='true'}">
				<div id="aValider" hidden>

					<c:forEach var="user" items="${sessionScope.listUsers}">
						<p>
							<u>Pseudo </u>:
							<c:out value="${user.pseudo}" />
							<u>Mail </u>:
							<c:out value="${user.mail}" />
							<u>Type </u>:
							<c:out value="${user.type}" />
						</p>
						<input class="btn btn-warning" type="submit"
							value="Valider le profil"
							onclick="validateProfil(${user.id},false,true)" />
						<input class="btn btn-warning" type="submit"
							value="Changer type et valider profil"
							onclick="validateProfil(${user.id},true,true)" />

					</c:forEach>
				</div>

				<div id="valide">

					<c:forEach var="user" items="${sessionScope.listValideUsers}">
						<p>
							<u>Pseudo </u>:
							<c:out value="${user.pseudo}" />
							<u>Mail </u>:
							<c:out value="${user.mail}" />
							<u>Type </u>:
							<c:out value="${user.type}" />
						</p>

						<input class="btn btn-warning" type="submit"
							value="Rendre invalide le profil"
							onclick="validateProfil(${user.id},false,false)" />

					</c:forEach>


				</div>

			</c:when>

			<c:otherwise>

				<div id="aValider">

					<c:forEach var="user" items="${sessionScope.listUsers}">
						<p>
							<u>Pseudo </u>:
							<c:out value="${user.pseudo}" />
							<u>Mail </u>:
							<c:out value="${user.mail}" />
							<u>Type </u>:
							<c:out value="${user.type}" />
						</p>
						<input class="btn btn-warning" type="submit"
							value="Valider le profil"
							onclick="validateProfil(${user.id},false,true)" />
						<input class="btn btn-warning" type="submit"
							value="Changer type et valider profil"
							onclick="validateProfil(${user.id},true,true)" />

					</c:forEach>
				</div>

				<div id="valide" hidden>

					<c:forEach var="user" items="${sessionScope.listValideUsers}">
						<p>
							<u>Pseudo </u>:
							<c:out value="${user.pseudo}" />
							<u>Mail </u>:
							<c:out value="${user.mail}" />
							<u>Type </u>:
							<c:out value="${user.type}" />
						</p>

						<input class="btn btn-warning" type="submit"
							value="Rendre invalide le profil"
							onclick="validateProfil(${user.id},false,false)" />

					</c:forEach>


				</div>

			</c:otherwise>

		</c:choose>

	</div>
</body>
</html>