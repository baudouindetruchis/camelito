<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous">
</script>

 <script> 
    $(function(){
    	$("#includedHeader").load("header_sample.jsp"); 
    });
    function btnCommClick(i) {
    	console.log("comm"+i);
    }
    function btnLessClick(i) {
    	console.log("less"+i);
    }
    function btnMoreClick(i) {
    	console.log("less"+i);
    }
    function btnSuppClick(i) {
    	console.log("less"+i);
    }
</script> 

<link rel="stylesheet" type="text/css" href="../public/css/panierStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
</head>

<body>
	<div id="includedHeader"></div>
	<div id="ordersDiv">
		<h1 style="font-size: 2vw">Mes commandes :</h1>
		<c:forEach var="comm" items="${sessionScope.commandeList}">
			<a class="nav-link" href="../PanierClick?id=${comm}&act=comm">N°<c:out value="${comm}"/></a>
	  	</c:forEach>
	</div>
	<div id="cartDiv">
	<h1 style="font-size: 2vw">Mon panier :</h1>
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">Article</th>
	      <th scope="col">Commercant</th>
	      <th scope="col">Quant.</th>
	      <th scope="col">Prix</th>
	      <th scope="col">  </th>
	      <th scope="col">  </th>
	      <th scope="col">  </th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="artc" items="${sessionScope.panierList}">
		  <tr>
		  	<td><c:out value="${artc.name}"/></td>
		  	<td><c:out value="${artc.magasin}"/></td>
		  	<td><c:out value="${artc.quantity}"/></td>
		  	<td><c:out value="${artc.selling_price}"/></td>
	        <td><a class="nav-link" href="../PanierClick?id=${artc.id}&act=less">-1</a></td>
	        <td><a class="nav-link" href="../PanierClick?id=${artc.id}&act=more">+1</a></td>
	        <td><a class="nav-link" href="../PanierClick?id=${artc.id}&act=supp">Supp</a></td>
	   	  </tr>
	  </c:forEach>
	    <tr>
	      <td></td>
	      <td></td>
	      <td>Total</td>
		  	<td><c:out value="${sessionScope.total_price}"/>€</td>
	      <td></td>
	      <td></td>
	    </tr>
	  </tbody>
	</table>
	
	<div id="fin">
		<button>Annuler</button>
		<button>Payer</button>
	</div>
	</div>
	
</body>
