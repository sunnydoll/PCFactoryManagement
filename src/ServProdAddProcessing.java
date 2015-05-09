/**
 * Servlet implementation class ServProdAddProcessing, processing of adding a new Product
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

//@WebServlet("/ServProdAddProcessing")
public class ServProdAddProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private ResourceBundle bundle;
	private DBSwitch dbswitch;
	private OpProducts opprod;
	private OpComponents opcomp;
	//private SearchEngine se;

	private String message;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServProdAddProcessing() {
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
		Products p = new Products();
		p.setName(prod);
		p.setStock(num);
		p.setIsdel(0);
		if (!message.equalsIgnoreCase("servus")) {	
			PrintWriter out = response.getWriter();
			out.println("<h1>Oracle connection failed " + message + "</h1>");
		} 
		else {
			int pid = opprod.addProd(p, ssn);
			ArrayList catelist = opcomp.getCateList();
			for(int i = 0; i < catelist.size(); i++) {
				Category cate = (Category) catelist.get(i);
				String catename = cate.getName();
				int cid = 0;
				cid = Integer.parseInt(request.getParameter(catename));
				if(cid > 0) {
					opprod.addProdMake(cid, pid);
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
