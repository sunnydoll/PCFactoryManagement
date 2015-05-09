/**
 * 
 * Class of Authorizations
 * 
 * @author Zhichao Cao (zc77@drexel.edu)
 * 
 */
public class Authorizations {
	public int aid;
	public String name;
	public int isdel;
	public int empnum;
	
	public Authorizations(){}
	
	public Authorizations(int id, String name) {
		this.aid = id;
		this.name = name;
		isdel = 0;
	}
	
	public Authorizations(int id, String name, int isdel) {
		this.aid = id;
		this.name = name;
		this.isdel = isdel;
	}

	public int getAid() {
		return aid;
	}

	public String getName() {
		return name;
	}

	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
	    return aid + ": " + name;
	}
	
	public int getEmpnum() {
		return empnum;
	}

	public void setEmpnum(int empnum) {
		this.empnum = empnum;
	}

	public String toHTML() {
		  return "<tr><td>" + name + "</td><td><a href='/~zc77/servlet/ServAuthView?id=" + aid + "'>View</a> <a href='/~zc77/servlet/ServAuthMod?id=" + aid + "'>Modify</a> <a href='/~zc77/servlet/ServAuthDel?id=" + aid + "'>Delete</a></td></tr>";
	}
}
