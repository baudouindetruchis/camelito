function After10(place) {
	var ligne = document.getElementById("after "+place);
	var place11 = document.getElementById("after2 "+place)
	
	if (place > '10') {
		ligne.hidden = false;
	}
	if (place == '10') {
		place11.hidden = false;
	}
	else {
		
	}
}