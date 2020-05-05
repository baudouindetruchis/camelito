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

<link rel="stylesheet" type="text/css" href="../public/css/panierStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<title>Camelito Panier</title>
</head>

<body>
	<div id="includedHeader"></div>
	<div id="ordersDiv">
		<h1 style="font-size: 2vw">Mes commandes :</h1>
		<button>N�2504</button>
	</div>
	<div id="cartDiv">
	<h1 style="font-size: 2vw">Mon panier :</h1>
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Article</th>
	      <th scope="col">Commer�ant</th>
	      <th scope="col">Quant.</th>
	      <th scope="col">Prix</th>
	      <th scope="col">  </th>
	      <th scope="col">  </th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Pasta box</td>
	      <td>Monop</td>
	      <td>1</td>
	      <td>3�</td>
	      <td><button>Supp</button></td>
	      <td><button>Modif</button></td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Pizza chervre</td>
	      <td>Monop</td>
	      <td>2</td>
	      <td>4�</td>
	      <td><button>Supp</button></td>
	      <td><button>Modif</button></td>
	    </tr>
	    <tr>
	      <th></th>
	      <td></td>
	      <td></td>
	      <td>Total</td>
	      <td>7�</td>
	      <td></td>
	      <td></td>
	    </tr>
	  </tbody>
	</table>
	
	<div id="fin">
		<button>Annuler</button>
		<button>Payer</button>
	</div>
	</div>
	
</body>