function After10(place,id) {
	var ligne1 = document.getElementById("after "+place);
	var ligne2 = document.getElementById("after "+id+place);
	var ligne3 = document.getElementById("after "+place+id);
	var place11 = document.getElementById("after2 "+place)
	
	if (place >= '13'){
		ligne1.hidden = false;
		ligne2.hidden = false;
		ligne3.hidden = false;
		place11.hidden = false;
	}
	if (place == '12'){
		ligne2.hidden = false;
		ligne3.hidden = false;
		place11.hidden = false;
	}
	if (place == '11') {
		ligne3.hidden = false;
		place11.hidden = false;
	}
	if (place == '10') {
		place11.hidden = false;
	}
	else {
		ligne1.hidden = true;
		ligne2.hidden = true;
		ligne3.hidden = true;
		place11.hidden = true;
	}
}

function titreSuccess(idPseudo,SuccessName) {
	var txt = document.getElementById("titreSecret "+idPseudo+SuccessName);
	
	if(SuccessName=='Score supérieur à 150') {
		txt.innerHTML = 'La légende du game';
		console.log(SuccessName)
	}
	if(SuccessName=='Pas moins de 50€ économisé !') {
		txt.innerHTML = 'Le roi des thunes';
	}
	if(SuccessName=='Plus de 10 commandes passées !') {
		txt.innerHTML = 'Fou de Uber Eats';
	}
}

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