function includeHeaderAndCheckUser()  {
	var session = document.getElementById("checkSession").value;
	$("#includedHeader").load("header_sample.jsp"); 
	$("#includedFooter").load("footer.jsp"); 
	if(session==''){
		window.location.href = "index.jsp";
	}
	chargeMessagerie();
}

function chargeMessagerie() {
	var footer=false;
	$.ajax({
		  url: "../ChatLoadAssoForm",
		  type: "get", //send it through get method
		  data: { 
			  footer:footer
			  },
		  success: function(response) {
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}


