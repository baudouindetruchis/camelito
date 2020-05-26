<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>webScocket chat room</title>
	<script type="text/javascript">  
      var websocket = null;  
      //Determine whether the current browser supports WebSocket  
      if('WebSocket' in window){  
          websocket = new WebSocket("ws://localhost:8181/chatDemo/websocket");  
      }  
      else{  
          alert('your browser does not support websocket！')  
      }  
            
      //when connection fail 
      websocket.onerror = function(){  
          setMessageInnerHTML("error");  
      };  
            
      //when connection success
      websocket.onopen = function(event){  
          alert("link success, welcome!");  
      }  
            
      //received message
      websocket.onmessage = function(event){  
          setMessageInnerHTML(event.data);  
      }  
            
      //close connection button
      websocket.onclose = function(){  
          var username=document.getElementById('username').value;  
          setMessageInnerHTML(getNowFormatDate()+" "+username+" offline");
      }  
            
      /*Monitor the window closing event. 
      When the window closes, actively close the websocket connection to prevent the window from closing before the connection is disconnected. */
      window.onbeforeunload = function(){  
          websocket.close();  
      }  
            
      //Show message on webpage
      function setMessageInnerHTML(innerHTML){  
          document.getElementById('message').innerHTML += innerHTML + '';  
      }
      </script>
</head>
<body>         
         <div class="container">  
         <center><h1>welcome to the chat </h1></center>         
           <div id="message"></div>  
             nickname： <input id="username" type="text"/>  
             message： <input id="text" type="text" style="width:300px"/>  
        <button onclick="send()">send</button>  
        <button onclick="closeWebSocket()">quit</button>          
        </div>           
</body>
</html>