<!DOCTYPE html>
<html>
<head>
<title>Mon Shopping</title>

<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../public/css/shopping.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<script> 
    $(function(){
    	$("#includedHeader").load("header_sample.jsp"); 
    });
</script> 

<!-- THOUGHTS ABOUT THIS DESIGN -->
<!-- Some things are not well designed, such as the h2 with "Produits disponibles" 
that uses absolute positioning (which means it will not be shown at the same position
depending on the resolution of the screen / browser for the user), and also we need to find a way to adapt the images 
to this format, the simplest way would be to process images before using to make them all the
same size.
Is this design easy to interact with for the database / back ?
 -->
</head>
<body>
	<div id="includedHeader"></div>
	<div class="container">
	<h2>Produits disponibles</h2>
	<div class="row">
	
	<!-- First item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Second item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Third item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Forth item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Second item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Third item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<!-- Forth item -->
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>	
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	<div class="col-md-3">
	<div class="product-top">
	<img src="../public/images/Dandan-Mian.jpg">
		<div class="overlay">
		<!-- Get the icons from "fontawesome.com/icons" -->
		<button type="button" class="btn btn-secondary" title="Add to favorite"><i class="fa fa-heart"></i></button>
		<button type="button" class="btn btn-secondary" title="Add to cart"><i class="fa fa-shopping-cart"></i></button>
		</div>
	</div>
	
		<div class="product-bottom text-center">
		<h5>Monoprix</h5>
		<h3>Nouilles du Sichuan instantanées</h3>
		<h5 class="prix">3€50</h5>
		</div>
	</div>
	
	</div>
	</div>
</body>
</html>