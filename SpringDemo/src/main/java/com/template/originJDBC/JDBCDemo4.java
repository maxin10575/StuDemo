package com.template.originJDBC;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


/**
 * �������Ԫ����
 * @author mx
 *
 */
public class JDBCDemo4 {
		public static void main(String[] args) {
			Connection conn = null;
			try {
				conn=DBUtil.getConnection();
				Statement state = conn.createStatement();
				String sql = "SELECT * FROM emp";
				ResultSet rs = state.executeQuery(sql);
				/*
				 * ��ȡ���Ԫ����
				 */
				ResultSetMetaData rsmd = rs.getMetaData();
				/*
				 * �鿴��������ֶ�����
				 */
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					/*
					 * �鿴ָ���ֶ�����
					 */
					String colname = rsmd.getColumnName(i);
					System.out.println(colname);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null) {
					DBUtil.closeConnection(conn);
				}
			}
		}
}
