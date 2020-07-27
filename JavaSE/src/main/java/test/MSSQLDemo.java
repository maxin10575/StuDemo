package test;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MSSQLDemo
{
    // MSSQL连接驱动
    private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // 连接字符串
    private static final String DB_URL = "jdbc:sqlserver://DESKTOP-5PRPIEN;databaseName=EpointBid_TP7";
    // 连接用户名
    private static final String USER = "sa";
    // 连接密码
    private static final String PASSWORD = "11111";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt1 = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("打开数据库连接...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
            stmt1 = conn.createStatement();
            String sql = "select name from sysobjects where xtype='u' order BY Name";
            rs = stmt.executeQuery(sql);
            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                String tableName = rs.getString("name");
                list.add(tableName);
            }
            FileWriter out = new FileWriter("D:\\数据重新推送脚本1.txt", true);
            for(String name : list) {
                String str = "Select Name FROM SysColumns Where id=Object_Id('" + name + "') and name like 'exc_uploadTimeJDPT_%'";
                ResultSet rs1 = stmt1.executeQuery(str);
                while (rs1.next()) {
                	String  a =  rs1.getString("Name");
                	/*String [] arrs =null;
                	if(a.contains("me")){
                	arrs = a.split("me");
                	}
                	if(a.contains("ME")){
                		arrs = a.split("ME");
                	}
                	String field = arrs[0] + "MEJDPT" +arrs[1];*/
                    out.write("update "+ name +" set " + a + " ='2001-01-01 00:00:00.000'");
                    out.write("\r\n");
                    System.out.println("表" + name + "写入完毕！");
                }
            }
            System.out.println("完成！");
            out.flush();
            out.close();
            rs.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
