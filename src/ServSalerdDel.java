/**
 * Servlet implementation class ServSalerdDel
 * @author Zhichao Cao (zc77@drexel.edu)
 */

import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ServSalerdDel")
public class ServSalerdDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private ResourceBundle bundle;
	private DBSwitch dbswitch;
	private OpSalesRecords opsr;
//	private SearchEngine se;
	
	private String message;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServSalerdDel() {
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
		opsr = new OpSalesRecords(conn);
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
		int param = Integer.parseInt(request.getParameter("id"));
		opsr.delSalerd(param);
		response.sendRedirect("/~zc77/servlet/ServSalerdList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
