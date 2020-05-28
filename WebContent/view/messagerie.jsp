<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>messagerie</title>
    <link rel="stylesheet" type="text/css" href="../public/css/messagerie.css">
    <script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
	<link rel="stylesheet" 
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous">
	
	</script>

    <script src="../public/js/header.js"></script>
</head>
<body onload="includeHeaderAndCheckUser()">
<div id=container>
	<input id="checkSession" type="text" name="checkSession" value ="${sessionScope.type}" hidden>

	<div id="includedHeader"></div>
    <div class="talk_con">
        <div class="talk_show" id="words">
            <div class="atalk"><span id="asay">A:Bonjour</span></div>
       		<div class="btalk"><span id="bsay">B:Bonjour</span></div>   
        </div>
        
        <div class="talk_short_input">
        	<input type="button" value="Stock remplir" class="stock_rempli" id="stockrempli">
        	<input type="button" value="Commande prête" class="commande_prete" id="commandeprete">
        </div>
        
        <div class="talk_input">
            
            <input type="text" class="talk_word" id="talkwords">
            <input type="button" value="envoyer" class="talk_sub" id="talksub">
        </div>
    </div>
    </div>
    <div id="includedFooter"></div>

</body>
<script type="text/javascript">

        window.onload = function(){
            var Words = document.getElementById("words");
            var Who = document.getElementById("who");
            var TalkWords = document.getElementById("talkwords");
            var TalkSub = document.getElementById("talksub");
            var TalkSubStockRempli = document.getElementById("stockrempli");
            var TalkSubCommandePrete = document.getElementById("commandeprete");
            var websocket = null;
        	
        	//Déterminez si le navigateur actuel prend en charge WebSocket  
        	if('WebSocket' in window){
			websocket = new WebSocket("ws://localhost:8282/camelito-master/websocket");//creer la connection (format de l'adresse: ws//localhost:XXXX/nom du projet/websocket)
        	}  
        	else{  
            	alert('Votre navigateur ne prend pas en charge websocket！')  
        	}  
              
        	//Méthode de rappel pour une erreur de connexion  
        	websocket.onerror = function(){  
            	setMessageInnerHTML("error");  
        	};
              
        	//quand le connexion réussie  
        	websocket.onopen = function(event){  
            	alert("connection réussit, bienvenue!"+" "+"user");  
        	}  
              
        	//le message reçu 
        	websocket.onmessage = function(event){  
            	setMessageInnerHTML(event.data);  
        	}  
              
        	//Surveillez l'événement de fermeture de la fenêtre, lorsque la fenêtre est fermée, fermez activement la connexion Websocket。  
        	window.onbeforeunload = function(){  
            	websocket.close();  
        	}  
              
        	//afficher le message 
        	function setMessageInnerHTML(innerHTML){
        		var str = "";
                str = '<div class="atalk"><span>' + innerHTML +'</span></div>' ;
                Words.innerHTML = Words.innerHTML + str; 
        	}  
              
        	//fermer la connection 
        	function closeWebSocket(){  
            	websocket.close();  
        	}  
        	
        	//envoyer le message
        	TalkSub.onclick = function(){
                if(TalkWords.value == ""){
	                 // show alert window when input box is empty
                    alert("Veuillez entrer du texte");
                    return;
                }else{
                	var str = "";
                    str = '<div class="btalk"><span>' + TalkWords.value +'</span></div>' ;
                    Words.innerHTML = Words.innerHTML + str;
                	websocket.send(TalkWords.value);
                	
                	//Après l'envoi du message, la boîte d'envoi du message est automatiquement effacée
                	document.getElementById('text').value="";  	
                }            
        	}  

            //when user clicked "Stock rempli" button, system send a short sentence automatically
            TalkSubStockRempli.onclick = function(){
            	str = '<div class="btalk"><span>Stock rempli</span></div>' ;
                Words.innerHTML = Words.innerHTML + str;
            }
            
          	//when user clicked "Commande prete" button
            TalkSubCommandePrete.onclick = function(){
            	str = '<div class="btalk"><span>Command prête</span></div>' ;
                Words.innerHTML = Words.innerHTML + str;
            }
        }
    </script>
</html>
