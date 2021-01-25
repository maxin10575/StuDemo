package com.template.originJDBC;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


/**
 * �������ڹ������ݿ�����
 * @author adminitartor
 *
 */
public class DBUtil {
	//���ݿ����ӳ�
	private static BasicDataSource ds;
	
	static{
		//��ʼ����̬����
		//1���������ļ�
		/*
		 * java.util.Properties
		 * ������ȡ.properties�ļ�������������
		 * ÿһ�����ݣ�Ȼ����key-value����ʽ����
		 * �ڵ�ǰʵ���С�
		 */
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
			String className = prop.getProperty("classname");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			int maxActive = Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait = Integer.parseInt(prop.getProperty("maxwait"));
			//��ʼ�����ӳ�
			ds = new BasicDataSource();
			//��JDBC������������Ҫ����Ϣ���õ����ӳ���
			
			//Class.forName(...)
			ds.setDriverClassName(className);
			
			//DriverManager.getConnection(...)
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//�������ӳ����������
			ds.setMaxActive(maxActive);
			//�������ȴ�ʱ��
			ds.setMaxWait(maxWait);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//2���������ļ���ʼ��
		
		
		
	}
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public static Connection getConnection() 
							  throws Exception{
		/*
		 * ���ӳ��ṩ�ķ���:
		 * Connection getConnection()
		 * �÷������Է���һ�����ӳ��п������ӡ�
		 * ����һ�����������������ӳ����п�������
		 * ����ʹ��ʱ�����̷��أ�����ǰ���ӳ�û��
		 * ��������ʱ�����������������ʱ���ɴ���
		 * ���ӳ�ʱͨ��setMaxWait���õ�ʱ��Ϊ׼
		 * �ڵȴ��ڼ����п����������������أ���
		 * �������ȴ�ʱ����û�п�������ʱ���÷�
		 * �����׳���ʱ�쳣��
		 */
		return ds.getConnection();
	}
	/**
	 * �رո���������
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			conn.setAutoCommit(true);
			/*
			 * ����������ͨ�����ӳػ�ȡ�ģ���ô����
			 * ������ӵ�close���������������ݿ�Ͽ�
			 * �����ˣ��������ǽ������ӻ������ӳء�
			 */
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = DBUtil.getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




