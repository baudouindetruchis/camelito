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
    <style type="text/css">
    	body{
		background-color: #F0F0F0;
		background-image: linear-gradient(#FFFFFF, #FFFFFF, #FFFFFF, #FFCC99);
		}
        .talk_con{
            width:600px;
            height:500px;
            border:1px solid #666;
            margin:50px auto 0;
            background:#f9f9f9;
            border-radius: 8px;
        }
        .talk_show{
            width:580px;
            height:380px;
            border:1px solid #666;
            background:#fff;
            margin:10px auto 0;
            overflow:auto;
        }
        .talk_short_input{
        	width:580px;
            margin:10px auto 0;
        }
        .talk_input{
            width:580px;
            margin:10px auto 0;
        }
        .whotalk{
            width:80px;
            height:30px;
            float:left;
            outline:none;
        }
        .talk_word{
            width:420px;
            height:26px;
            padding:0px;
            float:left;
            margin-left:0px;
            outline:none;
            text-indent:10px;
        }        
        .talk_sub{
        	border-radius: 8px;
            width:80px;
            height:30px;
            margin-left:10px;
            background-image: linear-gradient(#FFCC99, #FFFFFF, #FFCC99);
        }
        .atalk{
           margin:10px; 
        }
        .atalk span{
            display:inline-block;
            background:#0181cc;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
        .btalk{
            margin:10px;
            text-align:right;
        }
        .btalk span{
            display:inline-block;
            background:#ef8201;
            border-radius:10px;
            color:#fff;
            padding:5px 10px;
        }
        .stock_rempli{
        border-radius: 12px;
        width: 45%;
        height: 40px;
        background-image: linear-gradient(#FFCC99, #FFFFFF, #FFCC99);
        }
        .commande_prete{
        border-radius: 12px;
        width: 45%;
        height: 40px;
        background-image: linear-gradient(#FFCC99, #FFFFFF, #FFCC99);
        }
	</style>
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

            /*function getNowFormatDate() {  
            	var myDate = new Date();  
            	myDate.getYear();        //获取当前年份(2位)  
            	myDate.getFullYear();    //获取完整的年份(4位,1970-????)  
            	myDate.getMonth();       //获取当前月份(0-11,0代表1月)  
            	myDate.getDate();        //获取当前日(1-31)  
            	myDate.getDay();         //获取当前星期X(0-6,0代表星期天)  
            	myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)  
            	myDate.getHours();       //获取当前小时数(0-23)  
            	myDate.getMinutes();     //获取当前分钟数(0-59)  
            	myDate.getSeconds();     //获取当前秒数(0-59)  
            	myDate.getMilliseconds();    //获取当前毫秒数(0-999)  
            	myDate.toLocaleDateString();     //获取当前日期  
            	var mytime=myDate.toLocaleTimeString();     //获取当前时间  
           		return myDate.toLocaleString( );        //获取日期与时间  
          	}*/
        }
    </script>
</html>
