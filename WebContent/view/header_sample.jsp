<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Header sample</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="../public/css/header_sample.css">

</head>
<body>
	<nav class="navbar navbar-expand-md bg-light sticky-top">
	
		<button class="navbar-toggler" data-toggle="collapse" data-target="#collapse_target">
			<span class="navbar-toggler-icon"></span>
		</button>
		
		<div class="collapse navbar-collapse" id="collapse_target">
		
		<div class="mainicon" href="/"><img class="logo" src="../public/images/logo_camelito_marron.png"><span class="align-middle"> Camelito </span>
		</div>

		
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="profil.jsp">Profil</a>
			</li>
			
			<c:choose>
			    <c:when test="${session.getAttribute('type')=='1'}">
			        type1
			    </c:when>  
			    <c:when test="${session.getAttribute('type')=='2'}">
			        type1
			    </c:when>    
			    <c:otherwise>
			        other
			    </c:otherwise>
			</c:choose>
			<li class="nav-item">
				<a class="nav-link" href="shopping.jsp">Shopping</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="panier.jsp">Panier</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="score.jsp">Classement</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="contact.jsp">Contact</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/Deconnexion">Quitter</a>
			</li>
		</ul>
		</div>
		
		
	</nav>
	
	

</body>
</html>