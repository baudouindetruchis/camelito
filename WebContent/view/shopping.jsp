<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Camelito - Shopping</title>

<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../public/css/shopping.css">
<link rel="stylesheet" type="text/css"
	href="../public/css/pageStyle.css">

<script> 
    $(function(){
    	$("#includedHeader").load("header_sample.jsp"); 
    });
    function btnActClick(id, act) { 
    	$.ajax({
    		  url: "../ShoppingClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    id:id , 
    		    act: act
    		  },
    		  success: function(response) {
    		    	$("#cartDiv").load("panierTable.jsp");
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }
</script>

</head>
<body>
	<div id="includedHeader"></div>
	<div class="container">
		<h2>Produits disponibles</h2>
		<div class="row">

			<c:forEach var="artc" items="${sessionScope.articleList}">
				<div class="col-md-3">
					<div class="product-top">
						<img src="../public/images/Dandan-Mian.jpg">
						<div class="overlay">
							<!-- Get the icons from "fontawesome.com/icons" -->
							<div class="favorite">
								<button type="button" class="btn btn-secondary"
									title="Add to favorite">
									<i class="fa fa-heart"></i>
								</button>
							</div>

							<table class="quantity">
								<tr>
									<td><button type="button" class="btn btn-secondary"
											onClick="btnActClick(${artc.id}, 'more')" title="Add">
											<i class="fa fa-plus-circle" aria-hidden="true"></i>
										</button></td>
									<td><label for="" class="col-form-label">X</label></td>
									<td><button type="button" class="btn btn-secondary"
											onClick="btnActClick(${artc.id}, 'less')" title="Less">
											<i class="fa fa-minus-circle" aria-hidden="true"></i>
										</button></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="product-bottom text-center">
						<h5>
							<c:out value="${artc.magasin}" />
						</h5>
						<h3>
							<c:out value="${artc.name}" />
						</h3>
						<h5 class="prix">
							<c:out value="${artc.selling_price}" />
							€
						</h5>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
</body>
</html>