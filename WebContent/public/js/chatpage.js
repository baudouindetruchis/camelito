
function sendText(){
	var txt = document.getElementById("message").value;
	if(!txt.replace(/\s/g, '').length){
		alert("On ne peut pas envoyer de message vide");
	}else{
		sendPreEnteredtext(txt);
	}
}

function chooseStore(talkingTo) {
	
	$.ajax({
		  url: "../ChatForm",
		  type: "get", //send it through get method
		  data: { 
			  talkingTo:talkingTo
			  },
		  success: function(response) {
			  window.location.replace("../loadChats");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
	
}


function sendPreEnteredtext(txt) {
	
	$.ajax({
		  url: "../ChatForm",
		  type: "get", //send it through get method
		  data: { 
			  txt:txt
			  },
		  success: function(response) {
			  window.location.replace("../loadChats");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
	
}