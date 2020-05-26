package com.template.originJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

/** Statement只适合执行静态SQL语句，因为执行动态 SQL语句有两个缺点:
		*  * 1:由于含有动态信息，那么就需要先拼接SQL，这就可能出现SQL注入攻击的问题。
		*  * 2:大部分情况下，拼接SQL时，语义已经定好，拼接 的内容无非就是一些数据，那么当大批量执行这样
		*  * 含有动态值的SQL时，数据库每当接受到Statement 发送的SQL语句时，只要语句中的内容有区别，就会
		*  * 当做一条全新的SQL语句去执行。数据库执行SQL时 会首先解析SQL语句并生成一个执行计划(开销大)， 那么批量执行这样内容有些微变化的SQL时会为每一
		*  * 个SQL生成一个执行计划，对数据库是负担。
		*  *
		*  * java.sql.PreparedStatement 该接口是Statement的子接口。设计目的是为了
		*  * 执行动态SQL语句。这样的SQL称为预编译SQL， 这种SQL语句会将动态信息以"?"代替，先进行占位。 然后将该SQL发送给数据库生成执行计划。然后当
		*  * 需要执行该SQL时，只需要将?需要的实际数据再次 传递给数据库即可。 1:由于先将SQL语句发送给数据库，并生成了执行
		*  * 计划(语义已经确定)，就不存在拼接SQL导致 改变SQL语义(SQL注入攻击)的问题了。 2:由于执行计划已经生成，当大批量执行SQL时，
		*  * 每次只需要将?表示的实际值传入，那么数据库 会重用执行计划，这就减少了服务器的压力。*/

public class JDBCDemo1 {
		public static void main(String[] args) {
			Connection conn = null;
			try {
					conn =DBUtil.getConnection();
					/*
					 * 在一个事务中插入1000条数据可以减少数据库
					 * 对表的实际写操作次数,提高效率.
					 */
					conn.setAutoCommit(false);
					String sql = "INSERT INTO userinfo_mx "
								+"(id,username,password,email,nickname,account) "
								+"VALUES "
								+"(?,?,'123456',?,?,5000)";
					/*
					 * 使用ps可以使者1000条SQL使用同一个执行计划
					 * 从而提高SQL执行效率
					 */
					PreparedStatement ps = conn.prepareStatement(sql);
					long start = System.currentTimeMillis();
					for (int i = 20000; i < 30000; i++) {
						ps.setInt(1, i);
						ps.setString(2,"test"+i);
						ps.setString(3, "test"+i+"@qq.com");
						ps.setString(4, "nick"+i);
						/*
						 * 执行executeUpdate方法,会将?对应的一组数据发送给
						 * 数据库服务端
						 * 那么调用10000次该方法就会发送10000次
						 * 提高了网络调用次数会降低网络传输效率.
						 */
						//ps.executeUpdate();
						//添加到本地缓存中(添加到批中)
						ps.addBatch();
					}
					//执行批操作(将缓存的内容一次性发送给数据库执行)
				int[]arr=	ps.executeBatch();	
					conn.commit();
					long end = System.currentTimeMillis();
					System.out.println("插入完毕,耗时:"+(end-start));
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn!= null){
					DBUtil.closeConnection(conn);
				}
			}
		}
}
