package com.template.originJDBC;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


/**
 * 结果集的元数据
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
				 * 获取结果元数据
				 */
				ResultSetMetaData rsmd = rs.getMetaData();
				/*
				 * 查看结果集的字段数量
				 */
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					/*
					 * 查看指定字段名字
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
