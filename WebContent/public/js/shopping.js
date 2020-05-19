function btnActClick(id, act) { 
    	$.ajax({
    		  url: "../ShoppingClick",
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