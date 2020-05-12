<%@ page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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

	    


<%
//int id_session = Integer.parseInt(request.getParameter("userId"));

String driverName = "org.postgresql.Driver";
String connectionUrl = "jdbc:postgresql://127.0.0.1:5432/";
String dbName = "camelitoLocal";
String userId = "postgres";
String password = "123";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>


	<div id="includedHeader"></div>
	<div id="title">
		<h1 style="font-size: 2vw">Course au camelicoins</h1>
	</div>

	<div id="scoreTab">
	<table class="table table-striped">
	  <thead>
	    <tr>
<th scope="col" style="width: 5%"> Prénom </th>
<th scope="col" style="width: 10%"> Nom </th>
<th scope="col" style="width: 5%"> Score </th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>



</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM details WHERE score IS NOT NULL ORDER BY score";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">


<td><%=resultSet.getString("last_name") %></td>
<td><%=resultSet.getString("first_name") %></td>
<td><%=resultSet.getString("score") %></td>

</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>


<tr>

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
		
		
<%  
//int id_session = Integer.parseInt(request.getParameter("userId"));
int id_session = 1;
String sql_solo ="SELECT score FROM details WHERE (id_user = 1) AND (score IS NOT NULL)";
resultSet = null;
resultSet = statement.executeQuery(sql_solo);
while(resultSet.next()){
%>


		<p>


<td><%=resultSet.getString("score") %></td>

		</p>
		
<% 
}

%>	
	 
		
		
	</div>
	<div id="race">
		<img class="camel" alt="previous" src="../public/images/chammeau.png">
		<img class="camel" alt="you" src="../public/images/chammeau.png">
		<img class="camel" alt="next" src="../public/images/chammeau.png">
	</div>
	
	
</body>
