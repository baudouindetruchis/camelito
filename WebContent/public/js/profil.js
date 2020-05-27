function includeOtherPart()  {
	$("#includeReussite").load("mesReussites.jsp");
	$("#includeStore").load("monMagasin.jsp");

	includeHeaderAndCheckUser();
	
}


function modifyError(error) {
	if(error===''){
		
	}else{
		alert(error);
	}
 
}


function includeIntroMonture(profilPic) {
	if(profilPic==='../public/images/Camel4.png'){
		$("#introMonture").load("profil.jsp #introAugustus");
	}
	if(profilPic==='../public/images/Camel2.png'){
		$("#introMonture").load("profil.jsp #introPhilibert");
	}
	if(profilPic==='../public/images/Camel3.png'){
		$("#introMonture").load("profil.jsp #introMarthe");
	}
	if(profilPic==='../public/images/Camel5.png'){
		$("#introMonture").load("profil.jsp #introRoseline");
	}
	if(profilPic==='../public/images/Camel.png'){
		$("#introMonture").load("profil.jsp #introFerdinand");
	}
}


function changePic() {
	 var checkBox = document.getElementById("mode");
		$.ajax({
			  url: "../ChangeTypeForm",
			  type: "get", //send it through get method
			  data: {   
			  },
			  success: function(response) {
				  includeHeaderAndCheckUser();
				  if (checkBox.checked == true){
					  includeReussite.hidden=false;
			      } else {
			    	  includeReussite.hidden=true;
			      }
				  
				  
				  
			  },
			  error: function(xhr) {
			    //Do Something to handle error
			  }
			});
}

//function openForm() {
//  document.getElementById("myForm").style.display = "block";
//}
//
//function closeForm() {
//  document.getElementById("myForm").style.display = "none";
//}


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
			document.getElementById("promo").hidden = true;
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
 
 function changeType() {
	 var checkBox = document.getElementById("mode");
		$.ajax({
			  url: "../ChangeTypeForm",
			  type: "get", //send it through get method
			  data: {   
			  },
			  success: function(response) {
				  includeHeaderAndCheckUser();
				  if (checkBox.checked == true){
					  includeReussite.hidden=false;
			      } else {
			    	  includeReussite.hidden=true;
			      }
				  
				  
				  
			  },
			  error: function(xhr) {
			    //Do Something to handle error
			  }
			});
 }