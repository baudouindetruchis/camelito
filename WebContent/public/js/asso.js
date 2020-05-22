function finishOrder(idComm, user_name, id) {
	document.getElementById(idComm).hidden = true;
	
	$.ajax({
		  url: "../ClientListDelete",
		  type: "get", //send it through get method
		  data: { 
			  user_name:user_name,
			  id:id
			  },
		  success: function(response) {
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
	
}
