package com.template.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	//��Ӳ���
	public void add(){
		System.out.println("====="+jdbcTemplate);
	}
	
		
	
}
