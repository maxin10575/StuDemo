package course.entity;



public class SubscribedInfo {
	private String salaryid;
	private String courseid;
	private String starttime;
	private String endtime;
	private String note;
	private int actualhours;
	public String getSalaryid() {
		return salaryid;
	}
	public void setSalaryid(String salaryid) {
		this.salaryid = salaryid;
	}
	public String getCourseid() {
		return courseid;
	}
	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getActualhours() {
		return actualhours;
	}
	public void setActualhours(int actualhours) {
		this.actualhours = actualhours;
	}
	
}
