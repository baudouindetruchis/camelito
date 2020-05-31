
function sendText(){
	var txt = document.getElementById("message").value;
	
	var arr = ["connard", "putain","merde", "salop", "pute", "prout", "attardé", "rien a foutre", "chier", "encul"];
	var vulgar = false;
	for(let i=0; i<=9;i++){
		if(txt.includes(arr [i]) ){
			vulgar=true;
		}
	}
	if(!txt.replace(/\s/g, '').length){
		alert("On ne peut pas envoyer de message vide");
	}else if(vulgar===true){
		alert("Quelle vulgarité! C'est pas correct!");
	}else if(txt.includes("aime Camelito")){
		alert("Nous aussi on t'aime");
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

function downScrollbar() {
	$('#noScroll').scrollTop($('#noScroll')[0].scrollHeight);
	console.log($('#noScroll')[0].scrollHeight);
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