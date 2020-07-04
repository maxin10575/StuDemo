package course.dao;


import course.entity.CourseInfo;
import course.entity.SubscribedInfo;
import course.utils.FlowNumber;
import course.utils.TestDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScbDao {
	
	//删除选中的行   （选课记录）
		public int scbDelete(String ids) {
			Connection con = null;
			PreparedStatement pst = null;
			int i = 0;
			String sql = "delete from subscribedinfo where salaryid in('" + ids + "')";
		
			try {
				con =  TestDB.getConn();
				pst = con.prepareStatement(sql);
				i = pst.executeUpdate();
				return i;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return 0;
		}
	//add()保存 选课记录
		public int saveScb(SubscribedInfo s) {
				 Connection conn = null;
				 PreparedStatement ps = null;
				 String courseid = s.getCourseid();
				 //自动生成id
				 String salaryid = FlowNumber.subfetchFlowNumber();
				 String sql = "insert into subscribedinfo(courseid,starttime,endtime,note,salaryid,actualhours) values(?,?,?,?,?,?)";
				 try {
					conn = TestDB.getConn();				
					ps = conn.prepareStatement(sql);
					String starttime = s.getStarttime().replace("T", " ");
					String endtime = s.getEndtime().replace("T"," ");
					ps.setString(1, s.getCourseid());			
					ps.setString(2, starttime);
					ps.setString(3,endtime);		
					ps.setString(4,s.getNote());
					ps.setString(5, salaryid);
					//计算实际学时
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date start = sdf.parse(starttime);
					Date end = sdf.parse(endtime);
					long hours = end.getTime() - start.getTime();
					int actualhours = (int)hours/(1000*60*60);				
					ps.setInt(6, actualhours);
					int i = ps.executeUpdate();
					update(actualhours,courseid);
					return i ;
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
						try {
							conn.close();
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						} 
					}
			 return 0;		
		}
		
		//更新第一张表1
			public void update(int actualhours,String courseid) {		
				   Connection conn=null;
				   PreparedStatement ps=null;
				   String sql="select * from courseinfo where id=?";
				   try{
					   conn=TestDB.getConn();
					   ps=conn.prepareStatement(sql);

					   ps.setString(1, courseid);
					   ResultSet rs=ps.executeQuery();
					   while(rs.next()){				  				  		
						int hotlevel= rs.getInt("hotlevel");
						int selectedcount = rs.getInt("selectedcount");
						updateCourseInfo(hotlevel,selectedcount,actualhours,courseid);
					   }
			
				   }catch(Exception e){
					   e.printStackTrace();
				   }finally{
					   if(conn!=null){
						   try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					   }
				   }		
			}
			//更新第一张表2
			 public int updateCourseInfo(int hotlevel,int selectedcount,int actualhours,String courseid){ 
				 	int updatehotlevel = hotlevel + actualhours * 5 ;
				 	int updateselectedvount = selectedcount+1;
				    Connection conn = null;
					PreparedStatement ps = null;
					String sql = "update courseinfo set hotlevel=?,selectedcount=?  where id=? ";		
							try {
								conn = TestDB.getConn();					
								ps = conn.prepareStatement(sql);
								ps.setInt(1, updatehotlevel);
								ps.setInt(2, updateselectedvount);
								ps.setString(3, courseid);
								int i = ps.executeUpdate();
								return i;
							} catch (Exception e) {
								e.printStackTrace();
							}finally{
								try {
									conn.close();
									ps.close();
								} catch (SQLException e) {
									e.printStackTrace();
								}					
							}
				 return 0;
			 }
	//展示选课记录页面
		public List listScbAll() {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List list = new ArrayList();	
					try {
						conn = TestDB.getConn();
						String sql = "select * from subscribedinfo s,courseinfo c where s.courseid= c.id  order by salaryid" ;
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()){
							SubscribedInfo s = new SubscribedInfo();
							s.setSalaryid(rs.getString("salaryid"));
							s.setCourseid(rs.getString("coursename"));
							s.setNote(rs.getString("note"));
							s.setStarttime(rs.getString("starttime"));
							s.setEndtime(rs.getString("endtime"));
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date starttime = sdf.parse(rs.getString("starttime"));
							Date endtime = sdf.parse(rs.getString("endtime"));
							long actualtime = endtime.getTime() - starttime.getTime();
							int hours = (int)actualtime/(1000*60*60);
							s.setActualhours(hours);
							list.add(s);
						}
						return list;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							conn.close();
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}					
					}
			return null;
		}


		//选课页面 搜索
		public List scbfindByCourseName(String key) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List list = new ArrayList();	
					try {
						conn = TestDB.getConn();
						String sql = "select * from subscribedinfo s,CourseInfo c where s.courseid=c.id and c.coursename=?" ;
						ps = conn.prepareStatement(sql);
						ps.setString(1,key);
						rs = ps.executeQuery();
						while(rs.next()){
							SubscribedInfo s = new SubscribedInfo();
							s.setSalaryid(rs.getString("salaryid"));
							s.setCourseid(rs.getString("coursename"));
							s.setNote(rs.getString("note"));
							s.setStarttime(rs.getString("starttime"));
							s.setEndtime(rs.getString("endtime"));
							s.setActualhours(rs.getInt("actualhours"));
							list.add(s);
						}
						return list;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							conn.close();
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}					
					}
			return null;
		}
		//选课页面 搜索
		public List findByactualhours(String key) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List list = new ArrayList();	
					try {
						conn = TestDB.getConn();
						String sql = "select * from subscribedinfo s,courseinfo c where s.courseid=c.id and actualhours=? " ;
						ps = conn.prepareStatement(sql);
						ps.setObject(1,Integer.parseInt(key));
						rs = ps.executeQuery();
						while(rs.next()){
							SubscribedInfo s = new SubscribedInfo();
							s.setSalaryid(rs.getString("salaryid"));
							s.setCourseid(rs.getString("coursename"));
							s.setNote(rs.getString("note"));
							s.setStarttime(rs.getString("starttime"));
							s.setEndtime(rs.getString("endtime"));
							s.setActualhours(rs.getInt("actualhours"));
							list.add(s);
						}
						return list;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							conn.close();
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}					
					}
			return null;
		}
		//下拉选实现展示预选课程名
		public List findByCourseName() {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List list = new ArrayList();	
					try {
						conn = TestDB.getConn();
						String sql = "select id,coursename from courseinfo ";
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()){
							CourseInfo c = new CourseInfo();
							c.setId(rs.getString("id"));
							c.setCoursename(rs.getString("coursename"));	
							list.add(c);
						}
						
						return list;
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							conn.close();
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}					
					}
			return null;
		}
		
	}

