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
	
	<div class="container">
	<div class="d-flex justify-content-center text-center horsContainerTitre">
	<!-- javascript to show "Commerçant" and "Association" in both asso and commercant views + Add style to make it as the title -->
	<c:choose>
	<c:when test="${sessionScope.type == 2}">
		<div class="col-md-6 user1" id="user1${msg.sendByAsso}">
			<c:out value="Commerçant ${sessionScope.talkingToName}"/>
		</div>
		<div class="col-md-6 user2" id="user2${msg.sendByAsso}">
			Association
		</div>
	</c:when>
	<c:when test="${sessionScope.type == 3}">
		<div class="col-md-6 user1" id="user1${msg.sendByAsso}">
			Association
		</div>
		<div class="col-md-6 user2" id="user2${msg.sendByAsso}">
			Moi
		</div>
	</c:when>
	</c:choose>

	</div>
	</div>
	
	<div class="container containerStyle">
		<div class="col-md-12 scrollbar" id="noScroll">
		
		<c:forEach var="msg" items="${sessionScope.listMsg}">
			<div class="row">
				
				<div class="col-md-6 other1">
				<c:choose>
					<c:when test="${msg.sendByAsso && sessionScope.type == 3}">
						<div class="sendByOther">
							<%-- <c:out value="id : ${msg.id}" /> --%>
							<c:out value="${msg.text}" />
							<%-- <c:out value="asso? : ${msg.sendByAsso}" /> --%>
						</div>
					</c:when>


					<c:when test="${!msg.sendByAsso && sessionScope.type == 2}">
						<div class="sendByOther">
							<c:out value="${msg.text}" />
						</div>
					</c:when>
				</c:choose>
				</div>
				
				<div class="col-md-6 user1">
				<c:choose>
					<c:when test="${msg.sendByAsso && sessionScope.type == 2}">
						<div class="sendByUser">
							<c:out value="${msg.text}" />
						</div>
					</c:when>

					<c:when test="${!msg.sendByAsso && sessionScope.type == 3}">
						<div class="sendByUser">
							<c:out value="${msg.text}" />
						</div>
					</c:when>
				</c:choose>
				</div>

			</div>

		</c:forEach>
	</div>
	</div>

	<div class="container containerStyle2">
		<div class="col-md-12">
			<!-- <form onsubmit="sendText()"> -->
			<div class="form-row d-flex justify-content-center text-center">
				<div class="col-md-5">
					<input class="form-control" type="text" id="message"
						placeholder="Entrez votre message" />
				</div>
				<div class="col-md-1">
					<button class="btn btn-primary btn-responsive btnChat2"
						type="button" id="btnSend" onclick="sendText()">Envoyer</button>
				</div>
			</div>
			
			<!-- </form> -->
			<div class="col-md-12 marginTextfield">
				<div class="form-row d-flex justify-content-center text-center">
					<c:choose>
						<c:when test="${sessionScope.type=='3'}">
							<div class="col-md-3 marginTextfield">
								<button type="button"
									class="btn btn-primary btn-responsive btnChat2"
									onclick="sendPreEnteredtext('Commande prête!')">Commande
									prête!</button>
							</div>
							<div class="col-md-3 marginTextfield">
								<button type="button"
									class="btn btn-primary btn-responsive btnChat2"
									onclick="sendPreEnteredtext('Stocks remplis!')">Stocks
									remplis!</button>
							</div>
						</c:when>
						<c:when test="${sessionScope.type=='2'}">
							<div class="col-md-3 marginTextfield">
								<button type="button"
									class="btn btn-primary btn-responsive btnChat2"
									onclick="sendPreEnteredtext('Un membre arrive!')">Un
									membre arrive!</button>
							</div>
							<div class="col-md-3 marginTextfield">
								<button type="button"
									class="btn btn-primary btn-responsive btnChat2"
									onclick="sendPreEnteredtext('Nouvelle commande!')">Nouvelle
									commande!</button>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>

		</div>
	</div>









	<div id="includedFooter"></div>

</body>
<script>downScrollbar()</script>
</html>