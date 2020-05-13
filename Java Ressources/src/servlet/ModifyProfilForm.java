package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JavaFunction.ModifyProfilFunctions;
import obj.User;

/**
 * Servlet implementation class modifyProfilForm
 */
@WebServlet("/ModifyProfilForm")
public class ModifyProfilForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProfilForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("newEmail");	
		String pseudo = request.getParameter("newPseudo");
		String promotion =request.getParameter("newPromo");
		int promotionInt = 0;
		
		
		//connect to the bdd
				String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
				String user = "postgres";
				String psw = "123";
				String page = "./view/profil.jsp";

				HttpSession session = request.getSession();
				try (Connection con = DriverManager.getConnection(url, user, psw)) {
					int id = (int) session.getAttribute("id");
					
					PreparedStatement editPseudo = con.prepareStatement("UPDATE public.users SET user_name = '"+ pseudo +"' WHERE id = '"+id+"'" );
					PreparedStatement editMail = con.prepareStatement("UPDATE public.users SET mail = '"+ email +"' WHERE id = '"+id+"'" );																	
					PreparedStatement verifPseudoUnicity = con.prepareStatement("SELECT * FROM public.users WHERE user_name ='"+ pseudo+ "'");
					
					if(!pseudo.isEmpty()&& !(pseudo== null)) {
						ResultSet rsPseudo = verifPseudoUnicity.executeQuery();
						if(!rsPseudo.next()) {
							User obj = (User) session.getAttribute("user");
							editPseudo.execute();
							obj.setPseudo(pseudo);
						}
					}

					if(ModifyProfilFunctions.verifyEmail( email)) {
						editMail.execute();	
						User obj = (User) session.getAttribute("user");
						obj.setMail(email);
					}
					
					if((int) session.getAttribute("type")==1) {
						if(!promotion.isEmpty()&& !(promotion== null)) {
							try {
								promotionInt = Integer.parseInt(promotion);
								PreparedStatement editPromo = con.prepareStatement("UPDATE public.details SET promotion = '"+ promotionInt +"'  WHERE id_user = '"+id+"'");
																	
								 if(ModifyProfilFunctions.verifyPromo(promotionInt) || (promotionInt == 0)) {
									User obj = (User) session.getAttribute("user");
									editPromo.execute();
									obj.setPromotion(promotionInt);	
									session.setAttribute("promo", promotionInt);
								}
								
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}
									
				
					
					String pwd = request.getParameter("oldPassword");
					String newPwd = request.getParameter("newPassword");
					String secondPwd = request.getParameter("secondPassword");
					
					if(!pwd.isEmpty()&& !(pwd== null)) {
						
						PreparedStatement pswCheck = con.prepareStatement("SELECT password FROM public.users WHERE id = '"+id+"'");
						
			        	ResultSet rsPsw = pswCheck.executeQuery();
			        	Boolean rightPasswrd = false;
			        	rsPsw.next();
			        	String hashed = rsPsw.getString("password");
			        	rightPasswrd = BCrypt.checkPassword(pwd,  hashed);
			        	
			        	if(rightPasswrd && (newPwd.equals(secondPwd))) {
			        		
			        		newPwd = BCrypt.hashPassword(newPwd);
			        		PreparedStatement editPsw= con.prepareStatement("UPDATE public.users SET password=  '"+ newPwd +"' WHERE id = '"+id+"'" );
			        		editPsw.execute();	
			        	}
					}
					
		        			
				} catch (SQLException e) {
					System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(page);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	}
