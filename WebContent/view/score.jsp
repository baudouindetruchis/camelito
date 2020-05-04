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
	<title>Camelito Score</title>
</head>

<body>
	<div id="includedHeader"></div>
	<div id="title">
		<h1 style="font-size: 2vw">Course au camelicoins</h1>
	</div>
	<div id="scoreTab">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col" style="width: 5%">Classement</th>
	      <th scope="col" style="width: 10%">Pseudo</th>
	      <th scope="col" style="width: 5%">Score</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">I</th>
	      <td>BobB</td>
	      <td>742</td>
	    </tr>
	    <tr>
	      <th scope="row">II</th>
	      <td>Valerie</td>
	      <td>600</td>
	    </tr>
	    <tr>
	      <th scope="row">III</th>
	      <td>BarnaB</td>
	      <td>555</td>
	    </tr>
	  </tbody>
	</table>
	</div>
	<div id="yourScore">
		<h1 style="font-size: 2vw">Ton score : </h1>
		<p>442</p>
	</div>
	<div id="race">
		<img class="camel" alt="previous" src="../public/images/chammeau.png">
		<img class="camel" alt="you" src="../public/images/chammeau.png">
		<img class="camel" alt="next" src="../public/images/chammeau.png">
	</div>
	
</body>
