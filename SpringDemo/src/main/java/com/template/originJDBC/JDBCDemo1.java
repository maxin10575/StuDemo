package com.template.originJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

/** Statementֻ�ʺ�ִ�о�̬SQL��䣬��Ϊִ�ж�̬ SQL���������ȱ��:
		*  * 1:���ں��ж�̬��Ϣ����ô����Ҫ��ƴ��SQL����Ϳ��ܳ���SQLע�빥�������⡣
		*  * 2:�󲿷�����£�ƴ��SQLʱ�������Ѿ����ã�ƴ�� �������޷Ǿ���һЩ���ݣ���ô��������ִ������
		*  * ���ж�ֵ̬��SQLʱ�����ݿ�ÿ�����ܵ�Statement ���͵�SQL���ʱ��ֻҪ����е����������𣬾ͻ�
		*  * ����һ��ȫ�µ�SQL���ȥִ�С����ݿ�ִ��SQLʱ �����Ƚ���SQL��䲢����һ��ִ�мƻ�(������)�� ��ô����ִ������������Щ΢�仯��SQLʱ��Ϊÿһ
		*  * ��SQL����һ��ִ�мƻ��������ݿ��Ǹ�����
		*  *
		*  * java.sql.PreparedStatement �ýӿ���Statement���ӽӿڡ����Ŀ����Ϊ��
		*  * ִ�ж�̬SQL��䡣������SQL��ΪԤ����SQL�� ����SQL���Ὣ��̬��Ϣ��"?"���棬�Ƚ���ռλ�� Ȼ�󽫸�SQL���͸����ݿ�����ִ�мƻ���Ȼ��
		*  * ��Ҫִ�и�SQLʱ��ֻ��Ҫ��?��Ҫ��ʵ�������ٴ� ���ݸ����ݿ⼴�ɡ� 1:�����Ƚ�SQL��䷢�͸����ݿ⣬��������ִ��
		*  * �ƻ�(�����Ѿ�ȷ��)���Ͳ�����ƴ��SQL���� �ı�SQL����(SQLע�빥��)�������ˡ� 2:����ִ�мƻ��Ѿ����ɣ���������ִ��SQLʱ��
		*  * ÿ��ֻ��Ҫ��?��ʾ��ʵ��ֵ���룬��ô���ݿ� ������ִ�мƻ�����ͼ����˷�������ѹ����*/

public class JDBCDemo1 {
		public static void main(String[] args) {
			Connection conn = null;
			try {
					conn =DBUtil.getConnection();
					/*
					 * ��һ�������в���1000�����ݿ��Լ������ݿ�
					 * �Ա��ʵ��д��������,���Ч��.
					 */
					conn.setAutoCommit(false);
					String sql = "INSERT INTO userinfo_mx "
								+"(id,username,password,email,nickname,account) "
								+"VALUES "
								+"(?,?,'123456',?,?,5000)";
					/*
					 * ʹ��ps����ʹ��1000��SQLʹ��ͬһ��ִ�мƻ�
					 * �Ӷ����SQLִ��Ч��
					 */
					PreparedStatement ps = conn.prepareStatement(sql);
					long start = System.currentTimeMillis();
					for (int i = 20000; i < 30000; i++) {
						ps.setInt(1, i);
						ps.setString(2,"test"+i);
						ps.setString(3, "test"+i+"@qq.com");
						ps.setString(4, "nick"+i);
						/*
						 * ִ��executeUpdate����,�Ὣ?��Ӧ��һ�����ݷ��͸�
						 * ���ݿ�����
						 * ��ô����10000�θ÷����ͻᷢ��10000��
						 * �����������ô����ή�����紫��Ч��.
						 */
						//ps.executeUpdate();
						//��ӵ����ػ�����(��ӵ�����)
						ps.addBatch();
					}
					//ִ��������(�����������һ���Է��͸����ݿ�ִ��)
				int[]arr=	ps.executeBatch();	
					conn.commit();
					long end = System.currentTimeMillis();
					System.out.println("�������,��ʱ:"+(end-start));
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!= null){
					DBUtil.closeConnection(conn);
				}
			}
		}
}
