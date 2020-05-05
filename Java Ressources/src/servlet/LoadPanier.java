package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.Article;
import obj.User;

/**
 * Servlet implementation class LoadPanier
 */
@WebServlet("/LoadPanier")
public class LoadPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadPanier() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
//		String page = "./view/index.jsp";
		HttpSession session=request.getSession();

		 try (Connection con = DriverManager.getConnection(url, user, psw))
	        {
	        	PreparedStatement pst = con.prepareStatement("SELECT * FROM public.articles"); //TODO id in list
	        	ResultSet rs = pst.executeQuery();
				
				if(rs==null) {
					/*
					 * Error of connection
					 */
					
				}else {
					List<Article> result = new ArrayList<>();
					Article anArticle; //TODO don't create instance on every loop
		            while (rs.next()) {     
		            	//get value from bdd
		            	int id = rs.getInt("id");
		            	String name = rs.getString("name");
		            	int id_store;
		            	
		            	
		            	String store; //based on id_store
		            	int quantity; 
		            	System.out.println(id+" : "+name);
//		            	String magasin = rs.getString("magasin");
//		            	int type  = rs.getInt("type");
//		            	String password = rs.getString("password");
//		            	boolean status = rs.getBoolean("status");
		            	
		            	//create coresponding article object
		            	Article obj = new Article();
		                obj.setId(id);
		                obj.setName(name);
//		                obj.setPassword(password);
//		                obj.setStatus(status);
//		                obj.setType(type);
//		                obj.setUser_name(user_name);
//						obj.setFirst_name(firstname);
//						obj.setLast_name(lastname);

		                result.add(obj);

		            }
		            
		            //set session attribute
		            session.setAttribute("panierList",result);
		            
		            //load page
//		            page = "./view/panier.jsp";
				}
				
	            
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		

		// load page
		 String  page = "./view/panier.jsp";//TODO
		response.sendRedirect(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
