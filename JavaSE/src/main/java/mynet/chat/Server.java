package mynet.chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室服务端
 * @author maixn
 *
 */
public class Server {
	/**
	 * java.net.ServerSocket
	 * 运行在服务端的Socket  主要作用：
	 * 1:向操作系统申请端口号
	 * 		客户端就是通过这个端口与服务端应用程序建立连接的
	 * 2:监听服务端口，一旦一个客户端通过该端口发起连接  ServerSocket
	 * 		会自动创建一个Socket与客户端进行连接并完成后续交互
	 */
	private ServerSocket server;
	/*
	 * 该集合存放所有客户端的输出流
	 */
	private List<PrintWriter> allOut;
	/*
	 * 建立构造方法 用来初始化服务端
	 */
	public Server() throws IOException{
		/*
		 * 初始化ServerSocket并向操作系统申请端口号  该端口号不能与其他使用TCP
		 * 协议的应用程序申请的端口号相同，否则抛出异常
		 */
		server = new ServerSocket(8088);
		
		/*
		 * 初始化集合
		 */
		allOut = new ArrayList<PrintWriter>();
	}
	/**
	 * 
	 * 向集合中添加输出流
	 * @param out
	 */
	private synchronized void addOut(PrintWriter out){
		allOut.add(out);
	}
	/**
	 * 从共享集合中将给定的输出流删除
	 *
	 * @param out
	 */
	private synchronized void removeOut(PrintWriter out){
		allOut.remove(out);
	}
	/**
	 * 
	 * 将消息转发给所有的客户端(广播消息)
	 * @param message
	 */
	private synchronized void sendMessage(String message){
		for(PrintWriter out : allOut){
			out.println(message);
		}
	}
	
	/*
	 * 服务端开始工作的方法
	 */
	public void start(){
		try{
			/*
			 * ServerSocket提供了方法；
			 * Socket accept()
			 * 该方法是一个阻塞方法，作用是监听申请的服务端口，直到一个客户端通过
			 * 该端口建立连接为止，然后该方法会创建并返回一个Socket，服务端可以
			 * 通过该Socket与建立连接的客户端进行交互
			 */
			while(true){
				System.out.println("等待客户端连接...");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了！");
				//创建线程用来处理该客户端的交互工作
				ClientHandler handler = new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		try{
			Server server = new Server();
			server.start();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("服务端启动失败！");
		}
	}
	/**
	 * (内部类)
	 * 该线程用来与指定的客户端进行交互的
	 * @author maxin
	 */
	class ClientHandler implements Runnable{
		//当前线程用来处理客户端的Socket
		private Socket socket;
		//客户端的IP地址信息
		private String host;
		//用户的昵称
		private String nickName;
		public ClientHandler(Socket socket){
			this.socket = socket;
			/*
			 * 通过Socket获取远端计算机地址信息
			 */
			InetAddress address = socket.getInetAddress();
			//将远端计算机IP的字符串的形式返回
			host = address.getHostAddress();
		}
		public void run(){
			PrintWriter pw = null;
			try{
				
				/*
				 * 通过Socket获取输出流 以便可以将消息发送给客户端
				 */
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
				pw = new PrintWriter(osw,true);
				
				//将该客户端的输出流存入共享集合中
				addOut(pw);
				
				
				
				/*
				 * InputStrean getInputStream()
				 * 获取输入流 通过该流读取的内容就是远程计算机发送的信息
				 */
				InputStream in = socket.getInputStream();		
				InputStreamReader isr = new InputStreamReader(in,"UTF-8");	
				BufferedReader br = new BufferedReader(isr);
				
				/*
				 * 首先读取昵称
				 */
				nickName = br.readLine();
				
				//广播该用户上线
				sendMessage(nickName+"上线了！当前人数为:"+allOut.size());
				String message = null;
					/*
					 * 当使用br.readline()读取远程计算机发送过来的消息时，远程计算机
					 * (在这里就是客户端)的操作系统不同，当其断开连接时，br.readline方法
					 * 会抛出异常
					 * 当linux的客户端断开连接时，br,readline方法会返回null
					 */
				while((message = br.readLine())!=null){		
//					System.out.println(host+"说:"+message);
//					pw.println(host+"说:  "+message);
					//将该客户端发送的内容转发给所有的客户端
					sendMessage(nickName+"说:"+message);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
//				处理客户端断开连接以后的操作
				/*
				 * 将该客户端的输出流从共享集合中删除
				 */
				removeOut(pw);
				//广播该用户下线了
				sendMessage(nickName+"下线了！当前在线人数为:"+allOut.size());
				
//				System.out.println(host+"下线了");
				/*
				 * 将该客户端的Socket 关闭以释放资源
				 */
				try{
					socket.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}




















