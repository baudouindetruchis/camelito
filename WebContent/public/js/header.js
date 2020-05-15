function includeHeaderAndCheckUser()  {
	var session = document.getElementById("checkSession").value;
	$("#includedContent").load("header_sample.jsp"); 
	if(session==''){
		window.location.href = "index.jsp";
	}
	
}


