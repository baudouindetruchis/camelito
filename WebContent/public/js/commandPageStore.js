function btnValidate(id) {
	var btn = document.getElementById("btnStyle"+id);
	var btnBack = window.getComputedStyle(btn, null).getPropertyValue("background-color");
	
	if (btnBack == "rgb(128, 128, 128)") {
		document.getElementById("btnQuestion"+id).hidden = true;
		document.getElementById("btnStyle"+id).style.background = "#1e7e34";
//		console.log("1");
	}
	
	if (btnBack == "rgb(30, 126, 52)") {
		document.getElementById("btnQuestion"+id).hidden = false;
		document.getElementById("btnStyle"+id).style.background = "gray";
//		console.log("2");
	}	
	console.log("btnStyle"+id);
}
