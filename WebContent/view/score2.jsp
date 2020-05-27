<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="obj.User"%>
<body>
<%
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
	
</body>
