  function addFavSuccess(nameSuccess) {
    	$.ajax({
    		  url: "../SuccessForm",
    		  type: "get", //send it through get method
    		  data: { 
    		   
    			  nameSuccess: nameSuccess
    		  },
    		  success: function(response) {
    			  window.location.replace("profil.jsp");
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }



function colorCards(type, value) {
	var cardColor = document.getElementById("card "+type+value);
	if (type == "bienvenu" && value =="0") {
		cardColor.classList.add("border-warning");
	}
	if (type == "perso" && value =="1") {
		cardColor.classList.add("border-warning");
	}
	if (type == "score" && value =="50") {
		cardColor.classList.add("border-danger");
	}
	if (type == "score" && value =="100") {
		cardColor.classList.add("border-danger");
	}
	if (type == "score" && value =="150") {
		cardColor.classList.add("border-danger");
	}
	if (type == "saving" && value =="10") {
		cardColor.classList.add("border-success");
	}
	if (type == "saving" && value =="25") {
		cardColor.classList.add("border-success");
	}
	if (type == "saving" && value =="50") {
		cardColor.classList.add("border-success");
	}
	if (type == "command" && value =="1") {
		cardColor.classList.add("border-primary");
	}
	if (type == "command" && value =="5") {
		cardColor.classList.add("border-primary");
	}
	if (type == "command" && value =="10") {
		cardColor.classList.add("border-primary");
	}
}


function colorCardIcons(type, value) {
	var cardColorIcon = document.getElementById("icon "+type+value);
	if (type == "bienvenu" && value =="0") {
		cardColorIcon.style["color"] = "#ffc107"; 
	}
	if (type == "perso" && value =="1") {
		cardColorIcon.style["color"] = "#ffc107"; 
	}
	if (type == "score" && value =="50") {
		cardColorIcon.style["color"] = "#dc3545"; 
	}
	if (type == "score" && value =="100") {
		cardColorIcon.style["color"] = "#dc3545"; 
	}
	if (type == "score" && value =="150") {
		cardColorIcon.style["color"] = "#dc3545"; 
	}
	if (type == "saving" && value =="10") {
		cardColorIcon.style["color"] = "#28a745"; 
	}
	if (type == "saving" && value =="25") {
		cardColorIcon.style["color"] = "#28a745"; 
	}
	if (type == "saving" && value =="50") {
		cardColorIcon.style["color"] = "#28a745"; 
	}
	if (type == "command" && value =="1") {
		cardColorIcon.style["color"] = "#007bff"; 
	}
	if (type == "command" && value =="5") {
		cardColorIcon.style["color"] = "#007bff"; 
	}
	if (type == "command" && value =="10") {
		cardColorIcon.style["color"] = "#007bff"; 
	}
}

function fillCardImg(type, value) {
	var cardImg = document.getElementById("cardImg "+type+value);
	if (type == "bienvenu" && value =="0") {
		cardImg.setAttribute("src", "../public/images/connection.jpg");
	}
	if (type == "perso" && value =="1") {
		cardImg.setAttribute("src", "../public/images/personnalisation.jpg");
	}
	if (type == "score" && value =="50") {
		cardImg.setAttribute("src", "../public/images/victory.jpg");
	}
	if (type == "score" && value =="100") {
		cardImg.setAttribute("src", "../public/images/victory.jpg");
	}
	if (type == "score" && value =="150") {
		cardImg.setAttribute("src", "../public/images/victory.jpg");
	}
	if (type == "saving" && value =="10") {
		cardImg.setAttribute("src", "../public/images/money.jpg");
	}
	if (type == "saving" && value =="25") {
		cardImg.setAttribute("src", "../public/images/money.jpg");
	}
	if (type == "saving" && value =="50") {
		cardImg.setAttribute("src", "../public/images/money.jpg");
	}
	if (type == "command" && value =="1") {
		cardImg.setAttribute("src", "../public/images/delivery.jpeg");
	}
	if (type == "command" && value =="5") {
		cardImg.setAttribute("src", "../public/images/delivery.jpeg");
	}
	if (type == "command" && value =="10") {
		cardImg.setAttribute("src", "../public/images/delivery.jpeg");
	}
}