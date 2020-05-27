<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="obj.User"%>
<body>

<%int userPre = 0; %>
<%int userSui = 0; %>

<%  
HttpSession session2 = request.getSession(false);
User user = (User) session2.getAttribute("user");
user.getId();
int personneConnected = user.getId();

String sql_solo ="SELECT score FROM details WHERE (id_user = '"+ personneConnected +"') AND (score IS NOT NULL)";
resultSet = null;
resultSet = statement.executeQuery(sql_solo);
while(resultSet.next()){
%>

		<p>
<td> <%=resultSet.getString("score") %></td>
		</p>	
<% 
}
%>	


<% 
int personneConnectedSuivant = personneConnected - 1;
sql_solo ="SELECT score FROM details WHERE (id_user = '"+ personneConnectedSuivant +"') AND (score IS NOT NULL)";
resultSet = null;
resultSet = statement.executeQuery(sql_solo);
while(resultSet.next()){
%>
		<p>
<td> <%userPre = Integer.parseInt(resultSet.getString("score")); %></td>
		</p>	
<% 
}
%>


<% 
int personneConnectedPre = personneConnected + 1;
sql_solo ="SELECT score FROM details WHERE (id_user = '"+ personneConnectedPre +"') AND (score IS NOT NULL)";
resultSet = null;
resultSet = statement.executeQuery(sql_solo);
while(resultSet.next()){
%>
		<p>
<td> <%userSui = Integer.parseInt(resultSet.getString("score")); %></td>
		</p>	
<% 
}
%>

	
</body>
