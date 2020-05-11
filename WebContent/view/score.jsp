<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="fr">

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

 <script> 
    $(function(){
    	$("#includedHeader").load("header_sample.jsp"); 
    });
</script> 

<link rel="stylesheet" type="text/css" href="../public/css/scoreStyle.css">
<link rel="stylesheet" type="text/css" href="../public/css/pageStyle.css">

<head>
	<meta http-equiv="Content-Type" content="test/html; charset=UTF-8">
	<title>Camelito Score</title>
</head>

<body>
	<div id="includedHeader"></div>
	<div id="title">
		<h1 style="font-size: 2vw">Course au camelicoins</h1>
		
	
		
	</div>
	<div id="scoreTab">
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col" style="width: 5%">Classement</th>
	      <th scope="col" style="width: 10%">Pseudo</th>
	      <th scope="col" style="width: 5%">Score</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	    
	<FORM NAME="form1" ACTION="collecte.jsp" METHOD="POST">    
<%

int current = 1;
if(request.getParameter("hidden") != null) {
    current = Integer.parseInt(request.getParameter("hidden"));
}

//time to connect to bdd
String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
String user = "postgres";
String psw = "123";


	Connection con = DriverManager.getConnection(url, user, psw);
	Statement st;
	ResultSet rst;
	
	
	st = con.createStatement();
	rst=st.executeQuery("SELECT * FROM score");
	
	
    Statement statement = con.createStatement(
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY);
	
	
	while(rst.next()) {
		System.out.print(rst.getInt("id")+"\t");
		System.out.print(rst.getInt("point")+"\t");
		System.out.println();
	}
	
	
    ResultSet resultset = 
            statement.executeQuery("SELECT * FROM score"); 

        if(current < 1){
            current = 1;
        }

        resultset.last();
        int rows = resultset.getRow();
        if(current <= rows){
            resultset.absolute(current);
        } 
				

%>
            <TABLE BORDER="1">
                <TR>
                    <TH>id</TH>
                    <TH>score</TH>

                </TR>
                <TR>
                    <TD> <%= resultset.getString(1) %> </TD>
                    <TD> <%= resultset.getString(2) %> </TD>
                </TR>
            </TABLE>
            <BR>
            <INPUT TYPE="HIDDEN" NAME="hidden" VALUE="<%= current %>">
            <INPUT TYPE="BUTTON" VALUE="Suivant" ONCLICK="moveNext()">
            <INPUT TYPE="BUTTON" VALUE="Précédent" ONCLICK="movePrevious()">
        </FORM>




		  <form action="../Score">

      	  <c:set var = "usr" scope = "session" value = "${sessionScope.getScore}"/>
      	  
	      <th scope="row">I</th>
	      <td>BobB</td>
	      <td>742</td>
	    </tr>
	    <tr>
	      <th scope="row">II</th>
	      <td>Valerie</td>
	      <td>600</td>
	    </tr>
	    <tr>
	      <th scope="row">III</th>
	      <td>BarnaB</td>
	      <td>555</td>
	    </tr>
	  </tbody>
	</table>
	</div>
	<div id="yourScore">
		<h1 style="font-size: 2vw">Ton score : </h1>
		<p>442</p>
	</div>
	<div id="race">
		<img class="camel" alt="previous" src="../public/images/chammeau.png">
		<img class="camel" alt="you" src="../public/images/chammeau.png">
		<img class="camel" alt="next" src="../public/images/chammeau.png">
	</div>
	
        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function moveNext()
            {
                var counter = 0
                counter = parseInt(document.form1.hidden.value) + 1
                document.form1.hidden.value = counter
                form1.submit()
            }    
            function movePrevious()
            {
                var counter = 0
                counter = parseInt(document.form1.hidden.value) - 1
                document.form1.hidden.value = counter
                form1.submit()
            }    
            // --> 
        </SCRIPT>
	
</body>
