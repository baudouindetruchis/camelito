<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<!-- Bootstrap Toggle -->
<link href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>
	
	
	<!-- Font Awesome: Icons -->
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="../public/css/chat.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">


<head>
<title>Camelito - Messagerie</title>
<script src="../public/js/header.js"></script>
<script src="../public/js/chatpage.js"></script>

</head>

<body onload="includeHeaderAndCheckUser()">

	<div id="includedHeader"></div>
	<input id="checkSession" type="text" name="checkSession"
					value="${sessionScope.type}" hidden>
					
					
					
	<div class = "btn">
	
	<c:forEach var="store" items="${sessionScope.listStores}">
		
		<button type="button" class="btnn" onclick="chooseStore('${store}')"><c:out value="${store}" /> </button>
	</c:forEach>
			
	
	</div>				
					
					
								
	<div class="container">
		
		</div>

	
	
	

<div id="includedFooter"></div>

</body>

</html>