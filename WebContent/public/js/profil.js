function goToEdit() {
	var value = document.getElementById("promo");
	
		document.getElementById("btnSaveChange").hidden = false;
		document.getElementById("btnGoToEdit").hidden = true;
		document.getElementById("newPseudo").hidden = false;
		document.getElementById("newEmail").hidden = false;
		document.getElementById("Mdp").hidden = false;
		if(value!=null){
			document.getElementById("newPromo").hidden = false;
		}

}
 

 function saveChange() {
	var value = document.getElementById("promo");
	 
	document.getElementById("btnGoToEdit").hidden = true;
	document.getElementById("newPseudo").hidden = true;
	document.getElementById("newEmail").hidden = true;
	document.getElementById("Mdp").hidden = true;
	if(value!=null){
		document.getElementById("newPromo").hidden = true;
	}
		

}