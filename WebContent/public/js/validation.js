function validateProfil(id,changeType, toValidate){
	$.ajax({
		  url: "../ValidationForm",
		  type: "get", //send it through get method
		  data: { 
		    id:id,
		    changeType:changeType,
		    toValidate:toValidate
		  },
		  success: function(response) {
			window.location.replace("validationProfil.jsp");
				
			
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}

function unvalidate(){
	var toValide =  document.getElementById("checkbox").checked;
	if(document.getElementById("checkbox").checked){
		if(document.getElementById("aValider")!=null){
			document.getElementById("aValider").hidden = false;
		}
		if(document.getElementById("valide")!=null){
			document.getElementById("valide").hidden = true;
		}
		
		
	}else{
		if(document.getElementById("aValider")!=null){
			document.getElementById("aValider").hidden = true;
		}
		if(document.getElementById("valide")!=null){
			document.getElementById("valide").hidden = false;
		}
		
	}
	
}

function typeMembre(type){
	if(type=='1'){
		document.write("Client");
	}
	if(type=='2'){
		document.write("Association");
	}
	if(type=='3'){
		document.write("Commerçant");
	}
}


