<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<%@page contentType="text/html; charset=UTF-8"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("filterTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
      if (filter==="") {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>

<body onload="myFunction()">

<h2>Pour espionner ses potes</h2>

<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a pseudo">

	
	<table id='filterTable' class="table table-striped" style="width: 50%">
	  <tbody>
	  <c:forEach var="part" items="${sessionScope.fullParticipantsList}">
	     <tr>
		  	<td><c:out value="${part.place}"/></td>
		  	<td><c:out value="${part.pseudo}"/></td>
		  	<td><c:out value="${part.score}"/></td>
	   	  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	
</body>

