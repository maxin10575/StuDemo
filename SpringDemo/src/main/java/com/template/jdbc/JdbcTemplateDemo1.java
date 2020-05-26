package com.template.c3p0.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.List;

public class JdbcTemplateDemo1 {
	@Test
	public void add() {
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
		dataSource.setUsername("sa");
		dataSource.setPassword("11111");
		//创建jdbcTemplate对象，设置数据源
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//调用jdbcTemplate对象里面的方法实现操作
		//创建sql语句
		int count = jdbcTemplate.queryForObject("select count(1) from admin_info",Integer.class); 
		System.out.println("count=="+count);
//		int rows = jdbcTemplate.update("update admin_info set adminId = '3'");
//		System.out.println(rows); //返回受影响的行数
	}
	
	
	//jdbc实现代码  返回对象
	@Test
	public void testJdbc() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//加载驱动
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//创建链接
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1434;DatabaseName=study","sa","11111");
			//编写sql语句
			String sql = "select *  from admin_info where adminid=?";
			//预编译sql
			psmt = conn.prepareStatement(sql);
			//设置参数值
			psmt.setString(1,"3");
			//执行sql
			rs = psmt.executeQuery();
			//遍历结果集
			while(rs.next()){
				//得到返回结果值
				String adminid = rs.getString("adminid");
				String admincode = rs.getString("admincode");
				Admin admin = new Admin();
				admin.setAdminCode(admincode);
				admin.setAdminId(adminid);
				System.out.println(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//返回集合
	@SuppressWarnings("resource")
	@Test
	public void addList() throws PropertyVetoException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("dataSource");
		//设置连接池
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		/*dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
		dataSource.setUser("sa");
		dataSource.setPassword("11111");*/
		//设置数据库信息
			/*	DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
				dataSource.setUsername("sa");
				dataSource.setPassword("11111");*/
				//创建jdbcTemplate对象，设置数据源
//				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select *  from admin_info";
		
		List<Admin> list = jdbcTemplate.query(sql,new RowMapper<Admin>(){
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				//1 从结果集里面吧数据得到
				String adminid = rs.getString("adminid");
				String admincode = rs.getString("admincode");
				//2把得到数据封装到对象里面
				Admin admin = new Admin();
				admin.setAdminCode(admincode);
				admin.setAdminId(adminid);
				return admin;
			}
		});
		System.out.println("list==="+list);
	}

	@Test
	public void addDX() {
		//设置数据库信息
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
				dataSource.setUsername("sa");
				dataSource.setPassword("11111");
				//创建jdbcTemplate对象，设置数据源
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select *  from admin_info where adminid=?";
		//调用jdbcTemplate的方法实现
		//第二个参数是接口ROwMapper,需要自己写类实现接口，值做数据封装
		//Admin admin = jdbcTemplate.queryForObject(sql,new MyRowMapper(),'3');
		//内部类方式
		Admin admin = jdbcTemplate.queryForObject(sql,new RowMapper<Admin>(){
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				//1 从结果集里面吧数据得到
				String adminid = rs.getString("adminid");
				String admincode = rs.getString("admincode");
				//2把得到数据封装到对象里面
				Admin admin = new Admin();
				admin.setAdminCode(admincode);
				admin.setAdminId(adminid);
				return admin;
			}
		},"3");
		System.out.println("admin==="+admin);
	}
}

//写@Override报错 无法运行，只有写内部类
class MyRowMapper implements RowMapper<Admin> {
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        //1 从结果集里面吧数据得到
        String adminid = rs.getString("adminid");
        String admincode = rs.getString("admincode");
        //2把得到数据封装到对象里面
        Admin admin = new Admin();
        admin.setAdminCode(admincode);
        admin.setAdminId(adminid);
        System.out.println("admin111==="+admin);
        return admin;
    }
}








