/**
 * Servlet implementation class ServProdModProcessing
 * @author Zhichao Cao (zc77@drexel.edu)
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ServProdModProcessing")
public class ServProdModProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private ResourceBundle bundle;
	private DBSwitch dbswitch;
	private OpProducts opprod;
	private OpComponents opcomp;
	private OpEmployees opemp;
	//private SearchEngine se;

	private String message;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServProdModProcessing() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		bundle = ResourceBundle.getBundle("OraBundle");
		dbswitch = new DBSwitch();
		message = dbswitch.openDBConnection(bundle.getString("dbUser"),
				bundle.getString("dbPass"), bundle.getString("dbSID"),
				bundle.getString("dbHost"),
				Integer.parseInt(bundle.getString("dbPort")));
		conn = dbswitch.get_conn();
		opprod = new OpProducts(conn);
		opcomp = new OpComponents(conn);
		opemp = new OpEmployees(conn);
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
		String prod = request.getParameter("tbName");
		int num = Integer.parseInt(request.getParameter("tbNum"));
		String ssn = request.getParameter("Employees");
		int param = Integer.parseInt(request.getParameter("id"));
		Products p = new Products();
		p.setName(prod);
		p.setStock(num);
		p.setSsn(ssn);
		if (!message.equalsIgnoreCase("servus")) {	
			PrintWriter out = response.getWriter();
			out.println("<h1>Oracle connection failed " + message + "</h1>");
		} 
		else {
			opprod.delProdMake(param);
			opprod.modProd(p, param);
			ArrayList catelist = opcomp.getCateList();
			for(int i = 0; i < catelist.size(); i++) {
				Category cate = (Category) catelist.get(i);
				String catename = cate.getName();
				int cid = 0;
				cid = Integer.parseInt(request.getParameter(catename));
				if(cid > 0) {
					opprod.addProdMake(cid, param);
				}
			}
			response.sendRedirect("/~zc77/servlet/ServProdList");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
