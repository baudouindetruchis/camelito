<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">


<meta charset="UTF-8">
<%@page contentType="text/html; charset=UTF-8"%>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<body>
<c:forEach var="artc" items="${sessionScope.articleList}">

		<div class="col-md-3">
			<div class="product-top">
				<img class="object-fit_contain" src="${artc.img}">
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
									onClick="btnActClick(${artc.id}, 'less')" title="Less">
									<i class="fa fa-minus-circle" aria-hidden="true"></i>
								</button></td>
							<td><label for="" class="col-form-label"><c:out value="${artc.quantity}" />/<c:out value="${artc.stock}" /></label></td>
							<td><button type="button" class="btn btn-secondary" onClick="btnActClick(${artc.id}, 'more')" title="Add">
									<i class="fa fa-plus-circle" aria-hidden="true"></i>
								</button></td>
						</tr>
					</table>
				</div>
				<div class="overlay2">
				<div class="desc col-md-12">
						<c:out value="${artc.description}" />
					</div>
					<div class="sav col-md-12">
						Vous économiserez :
						<c:out value="${artc.saving}" />€/u
					</div>
				</div>
			</div>
			<div class="product-bottom text-center">
				<h5>
					<c:out value="${artc.magasin}" />
				</h5>
				<h3>
					<c:out value="${artc.name}" />
					(
					<c:out value="${artc.quantity}" />
					)
				</h3>
				<h5 class="prix">
					<c:out value="${artc.selling_price}" />
					€
				</h5>
			</div>
		</div>
	</c:forEach>


</body>
