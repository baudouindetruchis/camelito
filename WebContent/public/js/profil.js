function includeOtherPart()  {
	$("#includeReussite").load("mesReussites.jsp");
	$("#includeStore").load("monMagasin.jsp");
	includeHeader();
}


function editStore(){
	
	var value = document.getElementById("btnSaveChangeStore").hidden;
	if(value == true){
		document.getElementById("btnSaveChangeStore").hidden = false;
		document.getElementById("btnEditStore").hidden = true;
		document.getElementById("newName").hidden = false;
		document.getElementById("newAddress").hidden = false;
	}else{
		document.getElementById("btnSaveChangeStore").hidden = true;
		document.getElementById("btnEditStore").hidden = false;
		document.getElementById("newName").hidden = true;
		document.getElementById("newAddress").hidden = true;
	}
	
}


function goToEdit() {
	var value = document.getElementById("promo");
	
		document.getElementById("btnSaveChange").hidden = false;
		document.getElementById("btnGoToEdit").hidden = true;
		document.getElementById("newPseudo").hidden = false;
		document.getElementById("newEmail").hidden = false;
		document.getElementById("Mdp").hidden = false;
		if(value!=null){
			document.getElementById("newPromo").hidden = false;
			document.getElementById("info").hidden = false;
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
		document.getElementById("info").hidden = true;
	}
		

}