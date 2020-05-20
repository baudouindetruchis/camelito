
function onload(){ 
    	$("#includArt").load("shoppingArticles.jsp");
    	includeHeaderAndCheckUser();
    }
   
function btnActClick(id, act) { 
    	$.ajax({
    		  url: "../ShoppingClick",
    		  type: "get", //send it through get method
    		  data: { 
    		    id:id , 
    		    act: act
    		  },
    		  success: function(response) {
    		    	$("#includArt").load("shoppingArticles.jsp");
    		  },
    		  error: function(xhr) {
    		    //Do Something to handle error
    		  }
    		});
    }