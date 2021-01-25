package course.dao;


import course.entity.CourseInfo;
import course.utils.FlowNumber;
import course.utils.TestDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CourseDao {
	
	//aad方法 保存
	 public int saveCourse(CourseInfo c){
		   Connection conn=null;
		   String id = FlowNumber.fetchFlowNumber();
		   String sql="insert into courseInfo(coursename,starttime,suitable,type,totalhours,hotlevel,selectedcount,id,note)"
		   		+ " values(?,?,?,?,?,?,?,?,?)";
		   try{
		    conn= TestDB.getConn();
		    PreparedStatement ps=conn.prepareStatement(sql);
		    ps.setString(1, c.getCoursename());
			ps.setString(2, c.getStarttime());
			ps.setInt(3, c.getSuitable());
			ps.setInt(4, c.getType());
			ps.setInt(5, c.getTotalhours());
			ps.setInt(6, c.getHotlevel());
			ps.setObject(7, c.getSelectedcount());
			ps.setString(8, id);
			ps.setString(9,c.getNote());
			System.out.println("suitable="+c.getSuitable());
			System.out.println("c.getTotalhours()==="+c.getTotalhours());
		    int i=ps.executeUpdate();
		    return i;
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
		   return 0;
	   }
	 //显示所有记录(修改此处-关联utils内的FlowNumber)
	public List<CourseInfo> listAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CourseInfo> list = new ArrayList<CourseInfo>();	
				try {
					conn = TestDB.getConn();
					String sql = "select * from courseinfo order by id";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while(rs.next()){
						CourseInfo c = new CourseInfo();
						
						c.setId(rs.getString("id"));
						c.setCoursename(rs.getString("coursename"));
						c.setStarttime(rs.getString("starttime"));
						c.setSuitable(rs.getInt("suitable"));
						c.setType(rs.getInt("type"));
						c.setTotalhours(rs.getInt("totalhours"));
						c.setHotlevel(rs.getInt("hotlevel"));
						c.setSelectedcount(rs.getInt("selectedcount"));
						c.setNote(rs.getString("note"));
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
//删除选中的行
	public int delete(String ids) {
		Connection con = null;
		PreparedStatement pst = null;
		int i = 0;
		String sql = "delete from courseinfo where id in('" + ids + "')";
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
//编辑
	public int edit(CourseInfo c) {
		System.out.println("c==========="+c.getTotalhours());
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update courseinfo set coursename=?,type=?,suitable=?,starttime=?,totalhours=?,hotlevel=?,selectedcount=?,note=?  where id=? ";		
				try {
					conn = TestDB.getConn();					
					ps = conn.prepareStatement(sql);
					ps.setString(1, c.getCoursename());
					ps.setInt(2, c.getType());
					ps.setInt(3, c.getSuitable());
					ps.setString(4, c.getStarttime());
					ps.setInt(5, c.getTotalhours());
					ps.setInt(6, c.getHotlevel());
					ps.setInt(7,c.getSelectedcount());
					ps.setString(8, c.getNote());
					ps.setString(9, c.getId());				
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
//通过id查询 
	public CourseInfo queryById(String id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from courseinfo where id=?";
		try {
			con = TestDB.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			rs.next();
			CourseInfo c = new CourseInfo();
			c.setId(rs.getString("id"));
			c.setCoursename(rs.getString("coursename"));
			c.setStarttime(rs.getString("starttime"));
			c.setSuitable(rs.getInt("suitable"));
			c.setType(rs.getInt("type"));
			c.setTotalhours(rs.getInt("totalhours"));
			c.setHotlevel(rs.getInt("hotlevel"));
			c.setSelectedcount(rs.getInt("selectedcount"));
			c.setNote(rs.getString("note"));
			return c;
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
		return null;
	}
//通过课程名称查询
	public List findByCoursename(String key) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();			
		String sql = "select * from courseinfo where coursename=?";
		try {
			con = TestDB.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, key);
			rs = pst.executeQuery();
			
			while(rs.next()){	
			CourseInfo c = new CourseInfo();	
			c.setId(rs.getString("id"));
			c.setCoursename(rs.getString("coursename"));
			c.setStarttime(rs.getString("starttime"));
			c.setSuitable(rs.getInt("suitable"));
			c.setType(rs.getInt("type"));
			list.add(c);
			return list;
			}
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
		return null;
	}

	//通过type查询
	   public List findByType(String type){
		   List list=new ArrayList();
		   Connection conn=null;
		   PreparedStatement ps=null;
		   String sql="select * from courseinfo where type=?";
		   try{
			   conn=TestDB.getConn();
			   ps=conn.prepareStatement(sql);
			   //把"1" 转换为 int型
			   ps.setInt(1, Integer.parseInt(type));
			   ResultSet rs=ps.executeQuery();
			   while(rs.next()){
				   CourseInfo c=new CourseInfo();
				   c.setId(rs.getString("id"));
				   c.setCoursename(rs.getString("coursename"));
				   c.setStarttime(rs.getString("starttime"));
				   c.setSuitable(rs.getInt("suitable"));
				   c.setType(rs.getInt("type"));
				   c.setTotalhours(rs.getInt("totalhours"));
				   c.setHotlevel(rs.getInt("hotlevel"));
				   c.setSelectedcount(rs.getInt("selectedcount"));
				   list.add(c);
			   }
			   return list;
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
		   return null;
	   }
}
