function goToEdit() {
	var value = '@Request.RequestContext.HttpContext.Session["type"]';
	var x = document.getElementById("pseudo").disabled;
	if(x){
		document.getElementById("pseudo").disabled=false;
		document.getElementById("mail").disabled=false;
		document.getElementById("btnSaveChange").hidden = false;
		document.getElementById("btnGoToEdit").hidden = true;
		document.getElementById("newPseudo").hidden = false;
		document.getElementById("newEmail").hidden = false;
		document.getElementById("Mdp").hidden = false;
		if(value=='1'){
			document.getElementById("newPromo").hidden = false;
			document.getElementById("promo").disabled=false;
		}
	}else{
		document.getElementById("pseudo").disabled=true;
		document.getElementById("mail").disabled=true;
		document.getElementById("btnSaveChange").hidden = true;
		document.getElementById("newPseudo").hidden = true;
		document.getElementById("newEmail").hidden = true;
		document.getElementById("Mdp").hidden = true;
		if(value=='1'){
			document.getElementById("promo").disabled=true;
			document.getElementById("newPromo").hidden = true;
		}
		
	}
}
 

 function saveChange() {

	var x = document.getElementById("pseudo").disabled;
	if(x){
		document.getElementById("pseudo").disabled=false;
		document.getElementById("mail").disabled=false;
		document.getElementById("btnGoToEdit").hidden = true;
		document.getElementById("newPseudo").hidden = true;
		document.getElementById("newEmail").hidden = true;
		document.getElementById("Mdp").hidden = true;
		if(value=='1'){
			document.getElementById("promo").disabled=false;
			document.getElementById("newPromo").hidden = true;
		}
		
	}else{
		document.getElementById("pseudo").disabled=true;
		document.getElementById("mail").disabled=true;
		document.getElementById("btnGoToEdit").hidden = false;
		document.getElementById("btnSaveChange").hidden = true;
		document.getElementById("newPseudo").hidden = false;
		document.getElementById("newEmail").hidden = false;
		document.getElementById("Mdp").hidden = false;
		if(value=='1'){
			document.getElementById("promo").disabled=true;
			document.getElementById("newPromo").hidden = false;
		}
	}	
}