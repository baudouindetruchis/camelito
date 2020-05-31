function onload(){ 
    	$("#articlesDiv").load("stockArticles.jsp");  
    	$("#divFormModif").load("stockFormModif.jsp"); 
    	includeHeaderAndCheckUser();
    }

function reloadTable(){ 
    	$("#articlesDiv").load("stockArticles.jsp");  
    }


function orderSql(){
	var cBox = document.getElementById("trierId");
	var sqlOrder = cBox.value;
	$.ajax({
		  url: "../StockClick",
		  type: "get", //send it through get method
		  data: { 
			  sqlOrder:sqlOrder
			  },
		  success: function(response) {
			$("#articlesDiv").load("stockArticles.jsp");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}

function emptyStock(){
	$.ajax({
		  url: "../StockClick",
		  type: "get", //send it through get method
		  success: function(response) {
			$("#articlesDiv").load("stockArticles.jsp");
	    	$("#divFormModif").load("stockFormModif.jsp");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}
function stockUpdate(id){
	var inputStock = document.getElementById("stock-"+id);
	var newStock = inputStock.value;
	$.ajax({
		  url: "../StockClick",
		  type: "get", //send it through get method
		  data: { 
		    id:id, 
		    newStock: newStock
		  },
		  success: function(response) {
			$("#articlesDiv").load("stockArticles.jsp");
	    	$("#divFormModif").load("stockFormModif.jsp");
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
}
    
    function changeForm() {
      var checkBox = document.getElementById("formCheck");
      var formAdd = document.getElementById("formAdd");
      var formModif = document.getElementById("formModif");
      if (checkBox.checked == true){
    	  formAdd.hidden=false;
    	  formModif.hidden=true;
      } else {
    	  formModif.hidden=false;
    	  formAdd.hidden=true;
      }
    }
    
    function fillForm(id, name, description, real_price, selling_price, stock, img){
    	var modId           = document.getElementById("modId");
    	var modDescription  = document.getElementById("modDescription");
    	var modRealPrice    = document.getElementById("modRealPrice");
    	var modSellingPrice = document.getElementById("modSellingPrice");
    	var modStock        = document.getElementById("modStock");
    	var modImg        = document.getElementById("modImg");
    	modId.value = id;
    	modDescription.value = description;
    	modRealPrice.value = real_price;
    	modSellingPrice.value = selling_price;
        modStock.value = stock;
        modImg.value = img;

        var checkBox = document.getElementById("formCheck");
        checkBox.checked = false;
        changeForm();
    }