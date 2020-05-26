package mynet.chat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �����ҿͻ���
 *
 * @author maxin
 */

public class Client{
/*
		 * java.net.Socket
		 * ����TCPЭ��ͨѶ�������ڿͻ��ˡ�
		 * Socket��Ҫ����
		 * 1�������˽�������
		 * 2��ͨ��Socket��ȡһ�����������������Զ�˼����
		 * 		�������ݽ������������ͨѶ����
		 */
	/**
	 *���췽���� ��ʼ���ͻ���
	 */
	private String name;
	private Socket socket;
		public Client() throws UnknownHostException, IOException{
			/**
			 * ��ʵ����Socket��ʱ����Ҫ������������
			 * 1������˵�IP��ַ
			 * 2������˵Ķ˿ں�
			 * ͨ��IP��ַ�������ӵ���������ڼ������
			 * ͨ���˿ڿ����ҵ������ڷ���˼�����ϵķ����
			 * Ӧ�ó���
			 * ���ң�ʵ����Socket�Ĺ��̾��ǽ������ӵ�
			 * ���̣������������û����Ӧ���׳��쳣��
			 */
			System.out.println("�������ӷ����.....");
			socket = new Socket("172.17.16.147",8088);  //localhost 127.17.16.147
			System.out.println("�����������");			}
		/**
		 * �ͻ��˿�ʼ�����ķ���
		 */
		 public void start(){
			 	try{
			 		/*
			 		 * ������һ���ǳ�
			 		 */
			 		//���ڻ�ȡ�û�����
			 		Scanner scanner = new Scanner(System.in);
			 	
			 		while(true){
			 			System.out.println("�������ǳƣ�");
			 			name = scanner.nextLine();
			 			if(name.length()==0){
			 				System.out.println("����������һ���ַ�");
			 				continue;
			 			}
			 			break;
			 		}
			 
			 		//����������ȡ�������Ϣ���߳�
			 		ServerHandler handler = new ServerHandler();
			 		Thread t = new Thread(handler);
			 		t.start();
			 		/*
			 		 * Socket �ṩ������
			 		 * OutputStream  getOutputStream()
			 		 * �÷������Ի�ȡһ���������ͨ����
			 		 * �����д�������ݶ��ᷢ�͸�Զ�˼������
			 		 */
			 		OutputStream  out = socket.getOutputStream();
			 		OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
			 		PrintWriter pw = new PrintWriter(osw,true);
			 		//���ڻ�ȡ�û�����
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
				 System.out.println("�ͻ�������ʧ�ܣ�");
			 }
		 }
		 /**
		  * ���̵߳�������ѭ����ȡ����˷��͹�����ÿһ����Ϣ
		  * ��������ͻ��˵Ŀ���̨��
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
					 	 * �Ƚ��ǳƵ������͸������
					 	 */
					 	pw.println(name);
					 	System.out.println("��ӭ�㣺"+name+",��ʼ����ɣ�");
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