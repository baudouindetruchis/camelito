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

<link rel="stylesheet" type="text/css"
	href="../public/css/magasinListStyle.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Les Commandes</title>
<script src="../public/js/header.js"></script>

</head>
<body onload="includeHeaderAndCheckUser()">
<div id="includedHeader"></div>
<div class="container">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>
   <div class="row">
   <c:forEach var="store" items="${sessionScope.listCommands}">
      <div class="col-xs-6 col-sm-3">
      
         <div class="listbox">
            <c:out value="${store.storeName}" /> <br>
            <c:out value="${store.priceStore}" />
         </div>
         
         <label for="articles"><u>Articles commandés : </u></label>
         <c:forEach var="art" items="${store.commandToStore}">
			<p class="form-control-plaintext" id="name">
				<c:out value="${art.quantity}" /> <c:out value="${art.name}" />
			</p>
		</c:forEach>
       
      </div>
      </c:forEach>
   </div>
   


</div>
</body>
</html>