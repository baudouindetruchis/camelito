function includeHeaderAndCheckUser()  {
	var session = document.getElementById("checkSession").value;
	$("#includedHeader").load("header_sample.jsp"); 
	$("#includedFooter").load("footer.jsp"); 
	if(session==''){
		window.location.href = "index.jsp";
	}
}


