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
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
<link rel="stylesheet" type="text/css"
	href="../public/css/magasinListStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Les Commandes</title>
<script src="../public/js/header.js"></script>
<script src="../public/js/asso.js"></script>

</head>
<body onload="includeHeaderAndCheckUser()">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>
<div id="includedHeader"></div>

<div class="container">
   <div class="row">
   <c:forEach var="cmd" items="${sessionScope.listStoresCommands}">
      <div class="col-md-3 mobileMargin" id="comm${store.store_name}">
<c:choose>
						<c:when test="${cmd.ready=='lacommandenestpasprete'}">
		<div class="listbox">
			<div>
			<c:out value="${cmd.store_name}" />
			</div>
			<div>
			<c:out value="${cmd.price}" />
				€
				
			</div>
			
		</div>

					<!-- Form  -->
					<div for="${cmd.store_name}" href="#${cmd.store_name}" data-toggle="collapse" class="divSousTitre">
						<p class="pSousTitre" for="articles">Articles commandés</p>
						<i class="fa fa-angle-double-down" aria-hidden="true"></i>
					</div>
					
					<!-- Contenu -->
					<div id="${cmd.store_name}" class="collapse col-md-12 contentToggle ">
						<ul class="list-group list-group-flush paddingTopBot">

							<!-- Chaque liste d'articles -->
							<c:forEach var="art" items="${cmd.listArticles}">
								<div class="form-control-plaintext" id="name">
									<li class="list-group-item d-flex justify-content-between align-items-center listContent">
										<c:out value="${art.name}" /> <span class="badge badge-primary badge-pill"><c:out value="${art.quantity}" /></span>
									</li> 
								</div>
							</c:forEach>
							</ul>				
							
							
							<div id="closeCommand" class="closeOrderBtn position-relative stretched-link" onclick="recupCommande('comm${cmd.store_name}', '${cmd.store_name}', '${cmd.id}')">
						<i class="fa fa-times-circle-o fa-2x" aria-hidden="true"></i>
					</div>
					
					</div>	
					</c:when>
						
					</c:choose>
       
      </div>
      </c:forEach>
   </div>
   


</div>
</body>
</html>