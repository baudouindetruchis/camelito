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
    	$.ajax({
    		  url: "../PanierClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    act: act
    		  },
    		  success: function(response) {
    		    	$("#cartDiv").load("panierTable.jsp"); 
    		    	$("#ordersDiv").load("panierComm.jsp");
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }