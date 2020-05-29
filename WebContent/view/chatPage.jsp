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
				<div class = "msg">
				
				<c:forEach var="msg" items="${sessionScope.listMsg}">
		<div class= "oneMsg">
		<c:choose>
			<c:when test="${msg.sendByAsso && sessionScope.type == 2}">
			<div class ="sendByUser">
				<c:out value="id : ${msg.id}" />
				<c:out value="txt : ${msg.text}" />
				<c:out value="asso? : ${msg.sendByAsso}" />
			</div>
			</c:when>
			
			<c:when test="${msg.sendByAsso && sessionScope.type == 3}">
			<div class ="sendByOther">
				<c:out value="id : ${msg.id}" />
				<c:out value="txt : ${msg.text}" />
				<c:out value="asso? : ${msg.sendByAsso}" />
			</div>
			</c:when>
			
			<c:when test="${!msg.sendByAsso && sessionScope.type == 3}">
			<div class ="sendByUser">
				<c:out value="id : ${msg.id}" />
				<c:out value="txt : ${msg.text}" />
				<c:out value="asso? : ${msg.sendByAsso}" />
			</div>
			</c:when>
			<c:when test="${!msg.sendByAsso && sessionScope.type == 2}">
			<div class ="sendByOther">
				<c:out value="id : ${msg.id}" />
				<c:out value="txt : ${msg.text}" />
				<c:out value="asso? : ${msg.sendByAsso}" />
			</div>
			</c:when>
			
		</c:choose>
			
		</div>
		
	</c:forEach>
				</div>	
					
					
	<div class = "btn">
	
	<input class="form-control" type="text" id="message"
						placeholder="Enter message" />
	<button type="button" class="btnn" onclick="sendText()">Envoyer</button>
			
		<c:choose>
			<c:when test="${sessionScope.type=='3'}">
				<button type="button" class="btnn" onclick="sendPreEnteredtext('Commande prête!')">Commande prête!</button>
				<button type="button" class="btnn" onclick="sendPreEnteredtext('Stocks remplis!')">Stocks remplis!</button>
			</c:when>
			<c:when test="${sessionScope.type=='2'}">
				<button type="button" class="btnn" onclick="sendPreEnteredtext('Un membre arrive!')">Un membre arrive!</button>
				<button type="button" class="btnn" onclick="sendPreEnteredtext('Nouvelle commande!')">Nouvelle commande!</button>
			</c:when>
		</c:choose>
	
	</div>				
					
					
								
	<div class="container">
		
		</div>

	
	
	

<div id="includedFooter"></div>

</body>

</html>