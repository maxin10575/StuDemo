package mynet.chat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 聊天室客户端
 *
 * @author maxin
 */

public class Client{
/*
		 * java.net.Socket
		 * 基于TCP协议通讯，运行在客户端。
		 * Socket主要负责：
		 * 1：与服务端建立连接
		 * 2：通过Socket获取一个输入流与输出流与远端计算机
		 * 		进行数据交换以完成网络通讯需求。
		 */
	/**
	 *构造方法， 初始化客户端
	 */
	private String name;
	private Socket socket;
		public Client() throws UnknownHostException, IOException{
			/**
			 * 在实例化Socket的时候，需要传入两个参数
			 * 1：服务端的IP地址
			 * 2：服务端的端口号
			 * 通过IP地址可以连接到服务端所在计算机，
			 * 通过端口可以找到运行在服务端计算机上的服务端
			 * 应用程序。
			 * 而且，实例化Socket的过程就是建立连接的
			 * 过程，所以若服务端没有响应会抛出异常。
			 */
			System.out.println("正在连接服务端.....");
			socket = new Socket("172.17.16.147",8088);  //localhost 127.17.16.147
			System.out.println("服务端已连接");			}
		/**
		 * 客户端开始工作的方法
		 */
		 public void start(){
			 	try{
			 		/*
			 		 * 先输入一个昵称
			 		 */
			 		//用于获取用户输入
			 		Scanner scanner = new Scanner(System.in);
			 	
			 		while(true){
			 			System.out.println("请输入昵称：");
			 			name = scanner.nextLine();
			 			if(name.length()==0){
			 				System.out.println("请至少输入一个字符");
			 				continue;
			 			}
			 			break;
			 		}
			 
			 		//启动用来读取服务端消息的线程
			 		ServerHandler handler = new ServerHandler();
			 		Thread t = new Thread(handler);
			 		t.start();
			 		/*
			 		 * Socket 提供方法：
			 		 * OutputStream  getOutputStream()
			 		 * 该方法可以获取一个输出流，通过该
			 		 * 输出流写出的数据都会发送给远端计算机。
			 		 */
			 		OutputStream  out = socket.getOutputStream();
			 		OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
			 		PrintWriter pw = new PrintWriter(osw,true);
			 		//用于获取用户输入
			 		pw.println(name);
			 		 scanner = new Scanner(System.in); 	
			 while(true){
					String message = scanner.nextLine();
			 			pw.println(message);
			 	}
			 	}catch (Exception e) {
			 			e.printStackTrace();
				}
		 }
		 public static void main(String[] args){
			 try{
				 Client client = new Client();
				 client.start();
			 }catch(Exception e){
				 e.printStackTrace();
				 System.out.println("客户端启动失败！");
			 }
		 }
		 /**
		  * 该线程的作用是循环读取服务端发送过来的每一条信息
		  * 并输出到客户端的控制台上
		  */
		 class ServerHandler implements Runnable {
			 public void run(){
				 try {
					 	InputStream in = socket.getInputStream();
					 	InputStreamReader isr = new InputStreamReader(in,"utf-8");
					 	BufferedReader br = new BufferedReader(isr);
					 	OutputStream  name= socket.getOutputStream();
					 	OutputStreamWriter osw = new OutputStreamWriter(name,"utf-8");
					 	PrintWriter pw = new PrintWriter(osw,true);
					 	/*
					 	 * 先将昵称单独发送给服务端
					 	 */
					 	pw.println(name);
					 	System.out.println("欢迎你："+name+",开始聊天吧！");
					 	String message =null;
					 	while((message=br.readLine())!=null){
					 		System.out.println(message);
					 	}
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		 }
}