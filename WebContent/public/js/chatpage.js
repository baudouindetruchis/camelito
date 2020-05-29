function sendPreEnteredtext(txt) {
	console.log(txt);
	$.ajax({
		  url: "../ChatForm",
		  type: "get", //send it through get method
		  data: { 
			  txt:txt
			  },
		  success: function(response) {
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
	
}