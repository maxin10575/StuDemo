package mynet.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpDemoServer {
	public static void main(String[] args) {
		try{
			ServerSocket ss = new ServerSocket(10005);
			Socket s = ss.accept();
			
			String ip = s.getInetAddress().getHostAddress();
			System.out.println("ip==="+ip);
			
			//��ȡsocket��������
			BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//����д��socket�������͸��ͻ���
//			BufferedWriter bufOut = new BufferedWriter( new OutputStreamWriter(s.getOutputStream()) );
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			
			String line = null ;
			while( (line=bufIn.readLine()) != null ){
				System.out.println("�ͻ�������==="+line);
//				bufOut.write(line.toUpperCase());
//				bufOut.newLine();
//				bufOut.flush();
				out.println(line.toUpperCase());
			}
			s.close();
			ss.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
