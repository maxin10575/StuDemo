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
		//�������ݿ���Ϣ
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
		dataSource.setUsername("sa");
		dataSource.setPassword("11111");
		//����jdbcTemplate������������Դ
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//����jdbcTemplate��������ķ���ʵ�ֲ���
		//����sql���
		int count = jdbcTemplate.queryForObject("select count(1) from admin_info",Integer.class); 
		System.out.println("count=="+count);
//		int rows = jdbcTemplate.update("update admin_info set adminId = '3'");
//		System.out.println(rows); //������Ӱ�������
	}
	
	
	//jdbcʵ�ִ���  ���ض���
	@Test
	public void testJdbc() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//��������
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//��������
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1434;DatabaseName=study","sa","11111");
			//��дsql���
			String sql = "select *  from admin_info where adminid=?";
			//Ԥ����sql
			psmt = conn.prepareStatement(sql);
			//���ò���ֵ
			psmt.setString(1,"3");
			//ִ��sql
			rs = psmt.executeQuery();
			//���������
			while(rs.next()){
				//�õ����ؽ��ֵ
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
	
	//���ؼ���
	@SuppressWarnings("resource")
	@Test
	public void addList() throws PropertyVetoException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("dataSource");
		//�������ӳ�
//		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		/*dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
		dataSource.setUser("sa");
		dataSource.setPassword("11111");*/
		//�������ݿ���Ϣ
			/*	DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
				dataSource.setUsername("sa");
				dataSource.setPassword("11111");*/
				//����jdbcTemplate������������Դ
//				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select *  from admin_info";
		
		List<Admin> list = jdbcTemplate.query(sql,new RowMapper<Admin>(){
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				//1 �ӽ������������ݵõ�
				String adminid = rs.getString("adminid");
				String admincode = rs.getString("admincode");
				//2�ѵõ����ݷ�װ����������
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
		//�������ݿ���Ϣ
				DriverManagerDataSource dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dataSource.setUrl("jdbc:sqlserver://localhost:1434;DatabaseName=study");
				dataSource.setUsername("sa");
				dataSource.setPassword("11111");
				//����jdbcTemplate������������Դ
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select *  from admin_info where adminid=?";
		//����jdbcTemplate�ķ���ʵ��
		//�ڶ��������ǽӿ�ROwMapper,��Ҫ�Լ�д��ʵ�ֽӿڣ�ֵ�����ݷ�װ
		//Admin admin = jdbcTemplate.queryForObject(sql,new MyRowMapper(),'3');
		//�ڲ��෽ʽ
		Admin admin = jdbcTemplate.queryForObject(sql,new RowMapper<Admin>(){
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				//1 �ӽ������������ݵõ�
				String adminid = rs.getString("adminid");
				String admincode = rs.getString("admincode");
				//2�ѵõ����ݷ�װ����������
				Admin admin = new Admin();
				admin.setAdminCode(admincode);
				admin.setAdminId(adminid);
				return admin;
			}
		},"3");
		System.out.println("admin==="+admin);
	}
}

//д@Override���� �޷����У�ֻ��д�ڲ���
class MyRowMapper implements RowMapper<Admin> {
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        //1 �ӽ������������ݵõ�
        String adminid = rs.getString("adminid");
        String admincode = rs.getString("admincode");
        //2�ѵõ����ݷ�װ����������
        Admin admin = new Admin();
        admin.setAdminCode(admincode);
        admin.setAdminId(adminid);
        System.out.println("admin111==="+admin);
        return admin;
    }
}








