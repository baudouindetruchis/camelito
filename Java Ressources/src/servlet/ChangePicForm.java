package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import obj.User;

/**
 * Servlet implementation class ChangePicForm
 */
@WebServlet("/ChangePicForm")
public class ChangePicForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePicForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:postgresql://127.0.0.1:5432/camelitoLocal";
		String user = "postgres";
		String psw = "123";
		
		
		String pic = request.getParameter("pic");
		HttpSession session = request.getSession();
		try (Connection con = DriverManager.getConnection(url, user, psw)) {
			int id = (int) session.getAttribute("id");
			PreparedStatement editPic = con.prepareStatement("UPDATE public.details SET profil_pic = '"+ pic +"' WHERE id_user = '"+id+"'" );
			editPic.execute();
			User usr = (User) session.getAttribute("user");
			usr.setProfilPic(pic);
		
		
		}catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("./view/profil.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
