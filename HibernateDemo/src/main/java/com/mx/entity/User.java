package com.mx.entity;

public class User {

	private Integer myid;
	private String myname;
	private String mypwd;
	private String myaddr;

	public Integer getMyid() {
		return myid;
	}

	public void setMyid(Integer myid) {
		this.myid = myid;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getMypwd() {
		return mypwd;
	}

	public void setMypwd(String mypwd) {
		this.mypwd = mypwd;
	}

	public String getMyaddr() {
		return myaddr;
	}

	public void setMyaddr(String myaddr) {
		this.myaddr = myaddr;
	}

	@Override
	public String toString() {
		return "User{" +
				"myid=" + myid +
				", myname='" + myname + '\'' +
				", mypwd='" + mypwd + '\'' +
				", myaddr='" + myaddr + '\'' +
				'}';
	}
}
