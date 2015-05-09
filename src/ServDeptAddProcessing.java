/**
 * Servlet implementation class ServDeptAddProcessing
 * @author Zhichao Cao (zc77@drexel.edu)
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ServDeptAddProcessing")
public class ServDeptAddProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private ResourceBundle bundle;
	private DBSwitch dbswitch;
	private OpDepartments opdept;
//	private SearchEngine se;
	
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServDeptAddProcessing() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bundle = ResourceBundle.getBundle("OraBundle");
		dbswitch = new DBSwitch();
		message = dbswitch.openDBConnection(bundle.getString("dbUser"), bundle.getString("dbPass"), bundle.getString("dbSID"), 
										 bundle.getString("dbHost"), Integer.parseInt(bundle.getString("dbPort")));
		conn = dbswitch.get_conn();
		opdept = new OpDepartments(conn);
//		se = new SearchEngine(conn);
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
		Departments dept = new Departments();
		String name = request.getParameter("tbName");
		double bud = Double.parseDouble(request.getParameter("tbBudget"));
		dept.setName(name);
		dept.setBudget(bud);
		if (!message.equalsIgnoreCase("servus")) {	
			PrintWriter out = response.getWriter();
			out.println("<h1>Oracle connection failed " + message + "</h1>");
		} 
		else {
			opdept.addDept(dept);
			response.sendRedirect("/~zc77/servlet/ServDeptList");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
