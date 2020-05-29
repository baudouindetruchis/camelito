<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->

<body>

<div class="form-group">
<div class="form-row">
<div class="col-md-12">
<%-- 
	<h4>previous : </h4>
	<c:set var="pUsr" value="${sessionScope.prevParticipant}" />
	<c:out value="${pUsr.pseudo}"></c:out>
	<c:out value="${pUsr.score}"></c:out>
	<h4>you : </h4>
	<c:set var="cUsr" value="${sessionScope.currParticipant}" />
	<c:out value="${cUsr.pseudo}"></c:out>
	<c:out value="${cUsr.score}"></c:out>
	<h4>successor : </h4>
	<c:set var="sUsr" value="${sessionScope.succParticipant}" />
	<c:out value="${sUsr.pseudo}"></c:out>
	<c:out value="${sUsr.score}"></c:out>
 --%>
	<!-- calcul des marge pour placer le chameau au centre -->
	<c:set var="pUsr" value="${sessionScope.prevParticipant}" />
	<c:set var="cUsr" value="${sessionScope.currParticipant}" />
	<c:set var="sUsr" value="${sessionScope.succParticipant}" />
	
	<c:set var="coeff" value="${70/(sUsr.score - pUsr.score)}" /><!-- modifier le 70 pour changer la taille prise par les chameaux -->
	<c:set var="margeG" value="${coeff * (cUsr.score - pUsr.score)}" />
	<c:set var="margeD" value="${coeff * (sUsr.score - cUsr.score)}" />
	
	
	<div class="d-flex justify-content-center text-center" id="scorerace">
		<div style="width: 65px;"><c:out value="${pUsr.score}"/></div>
		<div style="width: 65px; margin-left:${margeG}%; margin-right:${margeD}%;"><c:out value="${cUsr.score}"/></div> 
		<div style="width: 65px;"><c:out value="${sUsr.score}"/></div>
	</div>
	<div class="d-flex justify-content-center text-center" id="userrace">
		<div style="width: 65px;"><c:out value="${pUsr.pseudo}"/></div>
		<div style="width: 65px; margin-left:${margeG}%; margin-right:${margeD}%;"><c:out value="${cUsr.pseudo}"/></div> 
		<div style="width: 65px;"><c:out value="${sUsr.pseudo}"/></div>
	</div>
	<div class="d-flex justify-content-center" id="race">
		<IMG class="style_image" alt="prev" src="../public/images/chammeau.png">
		<IMG class="style_image" alt="you" src="../public/images/chammeau.png" style="margin-left:${margeG}%; margin-right:${margeD}%;"> <!-- le style place le chameau a la bonne distance des precedent et successeur -->
		<IMG class="style_image" alt="next" src="../public/images/chammeau.png">
	</div>

</div>
</div>
</div>
	
</body>
