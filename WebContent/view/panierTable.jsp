<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<body>
	<h1 style="font-size: 2vw">Mon panier :</h1>
	<table id='myTable' class="table table-striped">
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
	        <td><button onClick="btnTabClick(${artc.id}, 'less')">-1</button></td>
	        <td><button onClick="btnTabClick(${artc.id}, 'more')">+1</button></td>
	        <td><button onClick="btnTabClick(${artc.id}, 'supp')">Supp</button></td>
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
		
</body>
