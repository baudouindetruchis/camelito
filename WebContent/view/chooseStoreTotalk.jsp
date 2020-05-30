<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

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
		value="${sessionScope.type}" hidden=true>

	<div class="container">
		<div class="col-md-12 text-center listMag">
			<div class="row">

				<c:forEach var="store" items="${sessionScope.listStores}">
					<div class="col-md-4">
						<button type="button" class="btn btn-primary btn-responsive btnChat"
							onclick="chooseStore('${store}')">
							<c:out value="${store}" />
						</button>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<div id="includedFooter"></div>
</body>
</html>