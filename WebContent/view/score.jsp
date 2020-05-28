<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
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
</script> 

<link rel="stylesheet" type="text/css" href="../public/css/scoreStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<title>Camelito Score</title>
</head>


<body>
	<div id="includedHeader"></div>
	<div class="container">

		<form action="../Score">



			<div id="title">
				<h1 style="font-size: 2vw">Course au camelicoins</h1>
			</div>

			<div id="scoreTab">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col" style="width: 5%">Prénom</th>
							<th scope="col" style="width: 10%">Nom</th>
							<th scope="col" style="width: 5%">Score</th>
						</tr>
					</thead>
					<tbody>


						<jsp:useBean id="score" scope="request" class="servlet.Score">
						</jsp:useBean>

						<%@include file="score2.jsp"%>

					</tbody>

				</table>

				<div id="yourScore">
					<h1 style="font-size: 2vw">Vôtre score :</h1>
				</div>

				<%@include file="score3.jsp"%>

				Score de la personne suivante :
				<%=userSui%>

				Score de la personne précédente :
				<%=userPre%>

				<div id="race">
					<IMG class="style_image" alt="previous"
						src="../public/images/chammeau.png; margin:0 10px 0 <%=userSui%>px;">
					<center>
						<IMG class="style_image" alt="you"
							src="../public/images/chammeau.png">
					</center>
					<IMG class="style_image" alt="next"
						src="../public/images/chammeau.png; margin:0 10px 0 <%=userPre%>px;">
				</div>
		</form>
	</div>
</body>