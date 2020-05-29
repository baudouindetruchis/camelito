<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Header sample</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../public/css/header_sample.css">


</head>
<body>
	<div class="col-md-12 nopadding fixed-top">
		<nav class="navbar navbar-expand-md sticky-top">

			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#collapse_target"> 
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="collapse_target">

				<div class="mainicon" href="/">
					<img class="logo" src="../public/images/logo_camelito_marron.png"><span
						class="align-middle"> Camelito </span>
				</div>
			
				
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="profil.jsp">Profil</a>
					</li>
					<c:choose>
						<c:when test="${sessionScope.status==false}">
						
						</c:when>
						<c:when test="${sessionScope.type=='1'||sessionScope.type=='4'}">
							<li class="nav-item"><a class="nav-link" href="../ShoppingLoad">Shopping</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="../PanierLoad">Panier</a></li>
							<li class="nav-item"><a class="nav-link" href="../Score">Classement</a>
							</li>
						</c:when>
						<c:when test="${sessionScope.type=='2' }">
							<li class="nav-item"><a class="nav-link" href="../ClientListLoadForm">Clients</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="../MagasinListLoadForm">Magasins</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="../ValidationLoadForm">Utilisateurs</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="../ChatLoadAssoForm">Messagerie</a>
							</li>
						</c:when>
						<c:when test="${sessionScope.type=='3'}">
							<li class="nav-item"><a class="nav-link" href="../StockLoad">Stock</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="../loadChats">Messagerie</a>
							</li>
							<li class="nav-item"><a class="nav-link" href="../MyCmdLoadFrom">Ma commande</a>
							</li>
						</c:when>
					</c:choose>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/Deconnexion">Quitter</a></li>
				</ul>
			</div>


		</nav>
	</div>



</body>
</html>