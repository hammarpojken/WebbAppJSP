

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("password");
		String prof = request.getParameter("Staff");
		String prof2 = request.getParameter("Patient");
		String page = "login.jsp";
		
		LoginDao dao = new LoginDao();
		
		if(prof != null) {
			if(dao.check(prof, uname, pass))
				page = "index.jsp";
		}
		if (prof2 !=null) {
			if(dao.check(prof2, uname, pass)) {
				page = "index.jsp";
			}
		}
		
			response.sendRedirect(page);
		
			
		
		
//		try {
//			ResultSet rs;
//			if(prof != null)
//			 rs = dbhandler.getUser(prof, uname, pass);
//			rs = dbhandler.getUser(prof2, uname, pass);
//			
//			if(rs.) && pass.equals("123")) {
//				System.out.print(prof);
//				response.sendRedirect("index.jsp");
//			}
//			else {
//				response.sendRedirect("login.jsp");
//			}
//			
//		}catch (Exception e) {
//			
//		}
//		
//		
	}

}
