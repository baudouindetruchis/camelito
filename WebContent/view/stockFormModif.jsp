<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
</script>

<body>
		<form id="formModif" action="../StockForm" class="form-container" name="" hidden=true>
			<select id="modId" name="idArticle" required>
				<option disabled selected> -- select an option -- </option>
	  			<c:forEach var="artc" items="${sessionScope.stockList}">
			      <option value="${artc.id}">${artc.id} - ${artc.name}</option>
			    </c:forEach>
			</select>				
			<input id="modDescription" type="text" placeholder="Description" name="description" class="field">
			<input id="modRealPrice" type="number" placeholder="Real price" name="real_price" required class="field" step="0.01">
			<input id="modSellingPrice" type="number" placeholder="Selling price" name="selling_price" required class="field" step="0.01">
  			<input type="number" id="modStock" name="stock" min="0" max="99" required>
			<input type="submit" name="act" value="Modifer">
			<input type="submit" name="act" value="Supprimer">
		</form>		
</body>
