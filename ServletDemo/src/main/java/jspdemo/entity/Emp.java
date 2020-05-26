package jspdemo.entity;

import java.io.Serializable;

public class Emp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 741014501009908421L;
	private Integer empno;
	private String ename;
	private String job;
	private Double sal;
		
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}

	
	
	
}
