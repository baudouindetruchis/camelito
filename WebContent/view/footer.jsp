<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html>
<head>
<title>Footer</title>
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="../public/css/footerStyle.css">

</head>
<body>
<jsp:useBean id="date" class="java.util.Date" />
	<!-- Site footer -->
	<footer class="site-footer sticky-bottom">
		<div class="container footerContainer">
			<div class="row">
				<div class="col-sm-12 col-md-6">
					<h6>À propos</h6>
					<p class="text-justify">
						Camelito est un service élaboré par des étudiants pour des
						étudiants. Toujours dans l'optique de gâcher un minimum possible,
						Camelito vous permet d'acheter des produits invendus (Toujours
						commestible et sans danger tant que l'on évite <b>les excès</b>) à
						petit prix: Bon pour la santé, bon pour la planète !
					</p>
				</div>

				<div class="col-xs-6 col-md-3 text-center">
					<h6>Catégories</h6>
					<ul class="footer-links" style="line-height: 30px;">
						<li><a href="profil.jsp">Profil</a></li>
						<c:choose>
							<c:when test="${sessionScope.status==false}">

							</c:when>
							<c:when test="${sessionScope.type=='1'||sessionScope.type=='4'}">
								<li><a href="../ShoppingLoad">Shopping</a></li>
								<li><a href="../PanierLoad">Panier</a></li>
								<li><a href="score.jsp">Classement</a></li>
							</c:when>
							<c:when test="${sessionScope.type=='2' }">
								<li><a href="../ClientListLoadForm">Clients</a></li>
								<li><a href="../MagasinListLoadForm">Magasins</a></li>
								<li><a href="../ValidationLoadForm">Utilisateurs</a></li>
							</c:when>
							<c:when test="${sessionScope.type=='3'}">
								<li><a href="../StockLoad">Stock</a></li>
								<li><a href="../MessageLoad">Messagerie</a></li>
								<li><a href="../MyCmdLoadFrom">Ma commande</a></li>
							</c:when>
						</c:choose>
						<li><a href="<%=request.getContextPath()%>/Deconnexion">Quitter</a></li>
					</ul>
				</div>

				<div class="col-xs-6 col-md-3 text-center">
					<h6>Nous contacter</h6>
					<table class="table footer-links">
						<tbody>
							<tr>
								<td style="vertical-align: middle;"><i class="fa fa-map-marker contactIcon"></i></td>
								<td><div><a href="#"> 10 Rue de Vanves, 92130 Issy-les-Moulineaux</a></div></td>
							</tr>
							<tr>
								<td style="vertical-align: middle;"><i class="fa fa-phone contactIcon"></i></td>
								<td><div><a href="#"> 01 49 54 52 43</a></div></td>
							</tr>
							<tr>
								<td style="vertical-align: middle;"><i class="fa fa-envelope-o contactIcon"></i></td>
								<td><div><a href="#"> camelito-contact@isep.fr</a></div></td>
							</tr>
						</tbody>
					</table>
					<!-- <ul class="footer-links">
						<li><i class="fa fa-map-marker contactIcon"></i><a href="#"> 10 Rue de Vanves, 92130 Issy-les-Moulineaux</a></li>
						
						<li><br><i class="fa fa-phone contactIcon"></i><a href="#"> 01 49 54 52 43</a></li>
						
						<li><br><i class="fa fa-envelope-o contactIcon"></i><a href="#"> camelito-contact@isep.fr</a></li>
					</ul> -->
				</div>
			</div>
			<hr>
		</div>
		<div class="container footerContainer">
			<div class="row">
				<div class="col-md-8 col-sm-6 col-xs-12">
					<p class="copyright-text">
						Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> All Rights Reserved by <a href="#">the Camelito Team</a>.
					</p>
				</div>

				<div class="col-md-4 col-sm-6 col-xs-12">
					<ul class="social-icons">
						<li><a class="facebook" href="#"><i
								class="fa fa-facebook"></i></a></li>
						<li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
						<li><a class="dribbble" href="#"><i
								class="fa fa-dribbble"></i></a></li>
						<li><a class="linkedin" href="#"><i
								class="fa fa-linkedin"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>