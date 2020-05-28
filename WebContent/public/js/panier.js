function onload(){ 
    	$("#cartDiv").load("panierTable.jsp"); 
    	$("#ordersDiv").load("panierComm.jsp");
    	includeHeaderAndCheckUser();
    }
   
    
    function btnTabClick(id, act) {
    	$.ajax({
    		  url: "../PanierClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    id:id , 
    		    act: act
    		  },
    		  success: function(response) {
    		    $("#cartDiv").load("panierTable.jsp");
    		    if(!(response.trim()==="")){
    		    	var elemTxt = document.getElementById("txtResponse");
        		    elemTxt.innerHTML = response;
        			$("#modalMsg").modal();
    		    }
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }
    function btnCommClick(id) { 
    	$.ajax({
    		  url: "../PanierClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    id:id , 
    		    act: 'comm'
    		  },
    		  success: function(response) {
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }
    function btnActClick(act) { 
	    var elemTxt = document.getElementById("txtResponse");
	    elemTxt.innerHTML = "Please wait...";
    	$.ajax({
    		  url: "../PanierClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    act: act
    		  },
    		  success: function(response) {
    		    	$("#cartDiv").load("panierTable.jsp"); 
    		    	$("#ordersDiv").load("panierComm.jsp");
        		    var elemTxt = document.getElementById("txtResponse");
        		    elemTxt.innerHTML = response;
        			$("#modalMsg").modal();
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }