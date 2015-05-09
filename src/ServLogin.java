/**
 * 
 * An implementation of login.
 *  
 * @author Zhichao Cao (zc77@drexel.edu)
 *
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ServLogin")
public class ServLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	private ResourceBundle bundle;
	private DBSwitch dbswitch;
	private OpDepartments oplogin;
	
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServLogin() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
//		bundle = ResourceBundle.getBundle("OraBundle");
//		dbswitch = new DBSwitch();
//		message = dbswitch.openDBConnection(bundle.getString("dbUser"), bundle.getString("dbPass"), bundle.getString("dbSID"), 
//										 bundle.getString("dbHost"), Integer.parseInt(bundle.getString("dbPort")));
//		conn = dbswitch.get_conn();
//		oplogin = new OpDepartments(conn);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		dbswitch.closeDBConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bundle = ResourceBundle.getBundle("OraBundle");
		dbswitch = new DBSwitch();
		message = dbswitch.openDBConnection(bundle.getString("dbUser"), bundle.getString("dbPass"), bundle.getString("dbSID"), 
										 bundle.getString("dbHost"), Integer.parseInt(bundle.getString("dbPort")));
		conn = dbswitch.get_conn();
		oplogin = new OpDepartments(conn);
		String username = request.getParameter("tbAccount");
        String password = request.getParameter("tbKey");
        PrintWriter out = response.getWriter();
        Statement st = null;
		try {
			String query = "select * from Employees where name = '" + username + "' and pw = '" + password + "' and isdel = 0";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				String ssn = rs.getString("ssn");
				request.getSession(true).setAttribute("user", username);
				request.getSession(true).setAttribute("ssn", ssn);
				int aid = rs.getInt("aid");
				int did = rs.getInt("did");
				String subquery = "select name from Departments where did = " + did + " and isdel = 0";
				ResultSet rsd = st.executeQuery(subquery);
				if(rsd.next()) {
					String dept = rsd.getString("name");
					request.getSession(true).setAttribute("dept", dept);
				}
				subquery = "select name from Authorizations where aid = " + aid + " and isdel = 0";
				ResultSet rsa = st.executeQuery(subquery);
				if(rsa.next()) {
					String auth = rsa.getString("name");
					request.getSession(true).setAttribute("auth", auth);
				}
				rsd.close();
				rsa.close();
				rs.close();
				st.close();
				request.getRequestDispatcher("/servlet/ServIndex").forward(request, response);
			}
			else {
				rs.close();
				st.close();
				request.getRequestDispatcher("/error.html").forward(request, response);
			}
		}
		catch(SQLException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
