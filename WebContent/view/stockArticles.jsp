<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

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
	      <th scope="col">Description</th>
	      <th scope="col">Prix intial</th>
	      <th scope="col">Prix de vente</th>
	      <th scope="col">Quant.</th>
	      <th scope="col">  </th>
	      <th scope="col">  </th>
	      <th scope="col">  </th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="artc" items="${sessionScope.stockList}">
		  <tr>
		  	<td><c:out value="${artc.name}"/></td>
		  	<td><c:out value="${artc.description}"/></td>
		  	<td><c:out value="${artc.real_price}"/></td>
		  	<td><c:out value="${artc.selling_price}"/></td>
		  	<td><c:out value="${artc.quantity}"/></td>
	        <td><button onClick="btnTabClick(${artc.id}, 'less')">-1</button></td>
	        <td><button onClick="btnTabClick(${artc.id}, 'more')">+1</button></td>
	        <td><button onClick="btnTabClick(${artc.id}, 'supp')">Supp</button></td>
	   	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
		
</body>
