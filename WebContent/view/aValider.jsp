<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="../public/css/faqStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
					
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>
</head>

<body>


<div>
	<c:forEach var="user" items="${sessionScope.listUsers}">
		
			<p><u>Pseudo </u>:  <c:out value="${user.pseudo}" /> 
			<u>Mail </u>:  <c:out value="${user.mail}" /> 
			<u>Type </u>:  <c:out value="${user.type}" /> </p>
			
			<input class="btn btn-warning" type="submit"value="Valider le profil" onclick="validateProfil(${user.id},false)" />
			<input class="btn btn-warning" type="submit"value="Changer type et valider profil" onclick="validateProfil(${user.id},true)" />

		</c:forEach>

</div>					
									
						

</body>
</html>
