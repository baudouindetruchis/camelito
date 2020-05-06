<!DOCTYPE html>
<html lang="fr">
<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8" %>

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/profilStyle.css">

<script> 
    $(function(){
      	$("#includedContent").load("header_sample.jsp"); 
    });
    </script>
<head>

<head>
	<title>Camelito - Modifier son profil</title>
	<script src="jquery-3.4.1.min.js"></script>
	<link rel="import" href="header_sample.jsp">
</head>

<body>
<div id="includedContent"></div>

<div class="container">
  <div class="row">
    <div class="col-sm-6">
    <h2>Mon profil</h2>
      <form>
      
        <div  class="edit form-group row">
	    <label for="name" class="col-form-label">Nom : </label>
	    <div class="col-sm-7">
	      <input type="text"  class="editable form-control-plaintext" id="lastname" value='<%=session.getAttribute("lName")%>'>
	    </div>
	  </div>
  
	  <div class="edit form-group row">
	    <label for="surname" class="col-form-label">Prénom : </label>
	    <div class="col-sm-7">
	      <input type="text"  class="editable form-control-plaintext" id="firstname" value='<%=session.getAttribute("fName")%>'>
	    </div>
	  </div>
  
	  <div class="edit form-group row">
	    <label for="pseudo" class="col-form-label">Pseudo : </label>
	    <div class="col-sm-7">
	      <input type="text"  class="editable form-control-plaintext" id="pseudo" value='<%=session.getAttribute("userName")%>'>
	    </div>
	  </div>
	  
	  <div class="edit form-group row">
	    <label for="mail" class="col-form-label">Adresse mail : </label>
	    <div class="col-sm-7">
	      <input type="email" class="editable form-control-plaintext" id="mail" value='<%=session.getAttribute("mail")%>'>
	    </div>
	  </div>
  
    <div class="form-group row">
    	<label for="mode" class="noedit col-form-label">Mode client : </label>
   		<label class="switch">
  		<input type="checkbox" id="mode">
  		<span class="slider round"></span>
		</label>
  	</div>
  
  	<input class="btn btn-basic" type="submit"  value="Valider" />
	
    </form>
    </div>
      
    <div  class="col-sm-6">	
    <h2 class="success">Mes réussites</h2>
	<fieldset>
	<img  class="profil img-responsive" src="../public/images/Add_User.png" alt="Add User" width="25%" height="120"/>
	</fieldset> 
	<!-- <p class="success">Réussite : </p> -->
	<div class="form-group row">
    <img class="img-responsive"  src="../public/images/pieces.png" alt="Pile de piece" width="15%" height="17%"/>
    <p class="col-form-label">10€ d'économisés</p>
  	</div>
	<div class="form-group row">
    <img class="img-responsive"  src="../public/images/podium.png" alt="Podium" width="15%" height="17%"/>
    <p class="col-form-label">Concours en groupe</p>
  	</div>
  	<div class="form-group row">
    <img class="img-responsive"  src="../public/images/medaille.png" alt="medaille" width="10%" height="10%"/>
    <p class="col-form-label">400 points cumulés</p>
  	</div>
	
	
	
   
  </div>
</div>
</div>
    
    
</body>

</html>