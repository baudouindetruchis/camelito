function validateProfil(id,changeType){

	$.ajax({
		  url: "../ValidationForm",
		  type: "get", //send it through get method
		  data: { 
		    id:id,
		    changeType:changeType
		  },
		  success: function(response) {
			window.location.replace("validationProfil.jsp");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}