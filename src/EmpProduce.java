/**
 * 
 * Class of Products produced by Employees
 * 
 * @author Zhichao Cao (zc77@drexel.edu)
 * 
 */
public class EmpProduce {
	public String ssn;
	public String emp;
	public String auth;
	public int pid;
	public String product;
	public int stock;
	
	public EmpProduce(){}
	
	public EmpProduce(String emp, String product) {
		this.emp = emp;
		this.product = product;
	}

	public String getEmp() {
		return emp;
	}

	public String getProduct() {
		return product;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	public String toString() {
	    return "Employee: " + emp + ", product: " + product;
	}
	
	public String getSsn() {
		return ssn;
	}

	public String getAuth() {
		return auth;
	}

	public int getPid() {
		return pid;
	}

	public int getStock() {
		return stock;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String toHTMLFromProd() {
		return "<tr><td>" + ssn + "</td><td>" + emp + "</td><td>" + auth + "</td><td><a href='/~zc77/servlet/ServEmpView?id=" + ssn + "'>View</a> <a href='/~zc77/servlet/ServEmpDel?id=" + ssn + "'>Delete</a></td></tr>";
	}
	
	public String toHTMLFromEmp() {
		return "<tr><td>" + pid + "</td><td>" + product + "</td><td>" + stock + "</td><td><a href='/~zc77/servlet/ServProdView?id=" + pid + "'>View</a> <a href='/~zc77/servlet/ServProdDel?id=" + pid + "'>Delete</a></td></tr>";
	}
}
