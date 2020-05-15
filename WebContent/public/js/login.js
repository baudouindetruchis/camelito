function includeOtherPartOnLoad()  {
	$("#includeCamelitoInfo").load("presentationCamelito.jsp");
}

function includeFAQ()  {
	document.getElementById("includeCamelitoInfo").hidden = true;
	$("#includeFAQ").load("faq.jsp");
}

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function showPassword() {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}


function showPasswordInscr() {
	  var x = document.getElementById("passwordInsc1");
	  var y = document.getElementById("passwordInsc2");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	  if (y.type === "password") {
	    y.type = "text";
	  } else {
	    y.type = "password";
	  }
	}







