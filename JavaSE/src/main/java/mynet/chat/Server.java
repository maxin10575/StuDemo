package mynet.chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * �����ҷ����
 * @author maixn
 *
 */
public class Server {
	/**
	 * java.net.ServerSocket
	 * �����ڷ���˵�Socket  ��Ҫ���ã�
	 * 1:�����ϵͳ����˿ں�
	 * 		�ͻ��˾���ͨ������˿�������Ӧ�ó��������ӵ�
	 * 2:��������˿ڣ�һ��һ���ͻ���ͨ���ö˿ڷ�������  ServerSocket
	 * 		���Զ�����һ��Socket��ͻ��˽������Ӳ���ɺ�������
	 */
	private ServerSocket server;
	/*
	 * �ü��ϴ�����пͻ��˵������
	 */
	private List<PrintWriter> allOut;
	/*
	 * �������췽�� ������ʼ�������
	 */
	public Server() throws IOException{
		/*
		 * ��ʼ��ServerSocket�������ϵͳ����˿ں�  �ö˿ںŲ���������ʹ��TCP
		 * Э���Ӧ�ó�������Ķ˿ں���ͬ�������׳��쳣
		 */
		server = new ServerSocket(8088);
		
		/*
		 * ��ʼ������
		 */
		allOut = new ArrayList<PrintWriter>();
	}
	/**
	 * 
	 * �򼯺�����������
	 * @param out
	 */
	private synchronized void addOut(PrintWriter out){
		allOut.add(out);
	}
	/**
	 * �ӹ������н������������ɾ��
	 *
	 * @param out
	 */
	private synchronized void removeOut(PrintWriter out){
		allOut.remove(out);
	}
	/**
	 * 
	 * ����Ϣת�������еĿͻ���(�㲥��Ϣ)
	 * @param message
	 */
	private synchronized void sendMessage(String message){
		for(PrintWriter out : allOut){
			out.println(message);
		}
	}
	
	/*
	 * ����˿�ʼ�����ķ���
	 */
	public void start(){
		try{
			/*
			 * ServerSocket�ṩ�˷�����
			 * Socket accept()
			 * �÷�����һ�����������������Ǽ�������ķ���˿ڣ�ֱ��һ���ͻ���ͨ��
			 * �ö˿ڽ�������Ϊֹ��Ȼ��÷����ᴴ��������һ��Socket������˿���
			 * ͨ����Socket�뽨�����ӵĿͻ��˽��н���
			 */
			while(true){
				System.out.println("�ȴ��ͻ�������...");
				Socket socket = server.accept();
				System.out.println("һ���ͻ��������ˣ�");
				//�����߳���������ÿͻ��˵Ľ�������
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
			System.out.println("���������ʧ�ܣ�");
		}
	}
	/**
	 * (�ڲ���)
	 * ���߳�������ָ���Ŀͻ��˽��н�����
	 * @author maxin
	 */
	class ClientHandler implements Runnable{
		//��ǰ�߳���������ͻ��˵�Socket
		private Socket socket;
		//�ͻ��˵�IP��ַ��Ϣ
		private String host;
		//�û����ǳ�
		private String nickName;
		public ClientHandler(Socket socket){
			this.socket = socket;
			/*
			 * ͨ��Socket��ȡԶ�˼������ַ��Ϣ
			 */
			InetAddress address = socket.getInetAddress();
			//��Զ�˼����IP���ַ�������ʽ����
			host = address.getHostAddress();
		}
		public void run(){
			PrintWriter pw = null;
			try{
				
				/*
				 * ͨ��Socket��ȡ����� �Ա���Խ���Ϣ���͸��ͻ���
				 */
				OutputStream out = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
				pw = new PrintWriter(osw,true);
				
				//���ÿͻ��˵���������빲������
				addOut(pw);
				
				
				
				/*
				 * InputStrean getInputStream()
				 * ��ȡ������ ͨ��������ȡ�����ݾ���Զ�̼�������͵���Ϣ
				 */
				InputStream in = socket.getInputStream();		
				InputStreamReader isr = new InputStreamReader(in,"UTF-8");	
				BufferedReader br = new BufferedReader(isr);
				
				/*
				 * ���ȶ�ȡ�ǳ�
				 */
				nickName = br.readLine();
				
				//�㲥���û�����
				sendMessage(nickName+"�����ˣ���ǰ����Ϊ:"+allOut.size());
				String message = null;
					/*
					 * ��ʹ��br.readline()��ȡԶ�̼�������͹�������Ϣʱ��Զ�̼����
					 * (��������ǿͻ���)�Ĳ���ϵͳ��ͬ������Ͽ�����ʱ��br.readline����
					 * ���׳��쳣
					 * ��linux�Ŀͻ��˶Ͽ�����ʱ��br,readline�����᷵��null
					 */
				while((message = br.readLine())!=null){		
//					System.out.println(host+"˵:"+message);
//					pw.println(host+"˵:  "+message);
					//���ÿͻ��˷��͵�����ת�������еĿͻ���
					sendMessage(nickName+"˵:"+message);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
//				����ͻ��˶Ͽ������Ժ�Ĳ���
				/*
				 * ���ÿͻ��˵�������ӹ�������ɾ��
				 */
				removeOut(pw);
				//�㲥���û�������
				sendMessage(nickName+"�����ˣ���ǰ��������Ϊ:"+allOut.size());
				
//				System.out.println(host+"������");
				/*
				 * ���ÿͻ��˵�Socket �ر����ͷ���Դ
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




















