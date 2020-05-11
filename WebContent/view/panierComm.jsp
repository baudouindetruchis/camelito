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
	
		<h1 style="font-size: 2vw">Mes commandes :</h1>
		<c:forEach var="comm" items="${sessionScope.commandeList}">
			<button onClick="btnCommClick(${comm})">N°<c:out value="${comm}"/></button>
	  	</c:forEach>
		
</body>
