package com.mx.action;

public class HelloAction {
	//(1)每次访问action时候，默认执行名称execute方法
	//配置action访问路径
	public String execute(){
		return "ok";
	}
}
