/**
 * 
 * An implementation of navigation menu based on the role of user.
 *  
 * @author Zhichao Cao (zc77@drexel.edu)
 *
 */

import java.io.PrintWriter;

public class NaviMenu {
	private String auth;
	private String dept;
	private String ssn;
	
	public NaviMenu(){}
	
	public NaviMenu(String auth, String dept, String ssn) {
		this.auth = auth;
		this.dept = dept;
		this.ssn = ssn;
	}
	
	public String printMenu() {
		String strMenu = "<html><head><link href='/~zc77/css/style.css' rel='stylesheet' type='text/css' /></head>";		
		strMenu += "<body style='text-align: center'>";
		strMenu += "<div class='nav'><div class='navinner'><ul class='navlist'><li><a href='/~zc77/servlet/ServIndex'>Homepage</a></li>";
		try {
			if(dept.startsWith("General") || dept.startsWith("Executive")) {
				strMenu += "<li><a href='/~zc77/servlet/ServAuthList'>Titles</a></li> <li><a href='/~zc77/servlet/ServEmpList'>Employees</a></li> <li><a href='/~zc77/servlet/ServDeptList'>Departments</a></li> <li><a href='/~zc77/servlet/ServCompList'>Components</a></li> <li><a href='/~zc77/servlet/ServProdList'>Products</a></li> <li><a href='/~zc77/servlet/ServDealerList'>Dealers</a> </li> <li><a href='/~zc77/servlet/ServSalerdList'>SalesRecords</a></li> <li><a href='/~zc77/servlet/ServStat'>Statistics</a></li>";
			}
			else {
				if(dept.startsWith("Human")) {
					strMenu += "<li><a href='/~zc77/servlet/ServAuthList'>Titles</a></li>";
					strMenu += "<li><a href='/~zc77/servlet/ServEmpList'>Employees</a></li>";
					strMenu += "<li><a href='/~zc77/servlet/ServDeptList'>Departments</a></li>";
				}
				else {
					strMenu += "<li style='color: white'>Titles</li>";
					strMenu += "<li style='color: white'>Employees<li>";
					strMenu += "<li style='color: white'>Departments</li>";
				}
				if(dept.startsWith("Purchasing")) {
					strMenu += "<li><a href='/~zc77/servlet/ServCompList'>Components</a></li>";
				}
				else {
					strMenu += "<li style='color: white'>Components</li>";
				}
				if(dept.startsWith("Production")) {
					strMenu += "<li><a href='/~zc77/servlet/ServProdList'>Products</a></li>";
				}
				else {
					strMenu += "<li style='color: white'>Products</li>";
				}
				if(dept.startsWith("Contract")) {
					strMenu += "<li><a href='/~zc77/servlet/ServDealerList'>Dealers</a></li>";
					strMenu	+= "<li><a href='/~zc77/servlet/ServSalerdList'>SalesRecords</a></li>";
				}
				else {
					strMenu += "<li style='color: white'>Dealers</li>";
					strMenu += "<li style='color: white'>SalesRecords</li>";
				}
				if(dept.startsWith("Finance") || dept.startsWith("Accounting")) {
					strMenu += "<li><a href='/~zc77/servlet/ServStat'>Statistics</a></li>";
				}
				else {
					strMenu += "<li style='color: white'>Statistics</li>";
				}
			}
		}
		catch(Exception e) {
//			strMenu += "<li><a href='/~zc77/servlet/ServAuthList'>Titles</a></li> <li><a href='/~zc77/servlet/ServEmpList'>Employees</a></li> <li><a href='/~zc77/servlet/ServDeptList'>Departments</a></li> <li><a href='/~zc77/servlet/ServCompList'>Components</a></li> <li><a href='/~zc77/servlet/ServProdList'>Products</a></li> <li><a href='/~zc77/servlet/ServDealerList'>Dealers</a> </li> <li><a href='/~zc77/servlet/ServSalerdList'>SalesRecords</a></li> <li><a href='/~zc77/servlet/ServStat'>Statistics</a></li>";
		}
		strMenu += "<li><a href='/~zc77/servlet/ServEmpView?id=" + ssn + "'>Information</a></li>";
		strMenu += "<li><a href='/~zc77/servlet/ServExit'>Logout</a>";
		strMenu += "</ul></div></div>";
		return strMenu;
	}
	
//	public String[] analyzeRole(String verify) {
//		String[] role = null;
//		if(dept.startsWith("Human")) {
//			role[0] = "human";
//		}
//		return role;
//	}

	public String getAuth() {
		return auth;
	}

	public String getDept() {
		return dept;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
}
