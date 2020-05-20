<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.0/css/bootstrap.min.css" rel="stylesheet">
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.0/js/bootstrap.min.js">
</script>

<style type="text/css">
   .listbox
{
    margin-right: 1px;
    margin-left: 1px;
    margin-top: 10px;
    padding: 10px;
    background-color:#eee;
    border: 1px solid #ddd;
}

</style>
<script src="../public/js/header.js"></script>
</head>
<body onload="includeHeaderAndCheckUser()">
<div id="includedHeader"></div>
<div class="container">
<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>
   <div class="row">
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Alice 4€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Bob 24€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Camille 14€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Etienne 4€</p>
         </div>
      </div>
   </div>
   <div class="row">
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>David 3€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Fred 4€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Barnabé 4€</p>
         </div>
      </div>
      <div class="col-xs-6 col-sm-3">
         <div class="listbox">
            <p>Irene 4€</p>
         </div>
      </div>
</div>
</div>
</body>
</html>