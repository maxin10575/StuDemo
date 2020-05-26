package swt;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 远程执行linux的shell script
 */
public class RemoteExecuteCommand {
	private static Connection conn;
	private static Connection conn1;
	private String ip;
	private String userName;
	private String userPwd;

	public RemoteExecuteCommand(String ip, String userName, String userPwd) {
		this.ip = ip;
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public RemoteExecuteCommand() {

	}

	/**
	 * 备份主机认证
	 * 
	 * @author mx
	 * @return 登录成功返回Connection，否则返回null
	 */
	public static Connection login(String ip, String userName, String userPwd,String port ) {
		boolean flg = false;
		try {
			int portin = Integer.valueOf(port);
			conn = new Connection(ip,portin);
			conn.connect();// 连接
			flg = conn.authenticateWithPassword(userName, userPwd);
			if (flg) {
				System.out.println("备份主机认证成功！");
				return conn;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 目标主机认证
	 * 
	 * @author mx
	 * @return 登录成功返回true，否则返回false
	 */
	public static Connection login1(String ip, String userName, String userPwd,String port) {
		boolean flg = false;
		try {
			int portin1 = Integer.valueOf(port);
			conn1 = new Connection(ip,portin1);
			conn1.connect();// 连接
			flg = conn1.authenticateWithPassword(userName, userPwd);
			if (flg) {
				System.out.println("目标主机认证成功！");
				return conn;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析脚本执行返回的结果集
	 * 
	 * @author mx
	 * @param in
	 *            输入流对象
	 * @param charset
	 *            编码
	 * @return 以纯文本的格式返回
	 */
	public static String processStdout(InputStream in, String charset) {
		InputStream stdout = new StreamGobbler(in);
//		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
			String console = br.readLine();
			if(console == null){
				br.close();
				return "pathistrue";
			}else{
				br.close();
				return "pathisfalse";
			}
			/*while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}*/
//		catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String executeLinuxCmd(String cmd) {
        Runtime run = Runtime.getRuntime();
        try { 
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
             System.out.println("[check] now size \n"+bs.readLine());     
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1;) {
            	out.append(new String(b, 0, n));
            }
            in.close();
            // process.waitFor();
            process.destroy();
            return out.toString();
        } catch (IOException e) {
        	 e.printStackTrace();
        	 return "pathisfalse";
           
        }
    }
	

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		RemoteExecuteCommand.conn = conn;
	}

	public static Connection getConn1() {
		return conn1;
	}

	public static void setConn1(Connection conn1) {
		RemoteExecuteCommand.conn1 = conn1;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
