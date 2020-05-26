package mynet.tcp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpCopyFileServer {

	public static void main(String[] args) throws Exception {
		try {
		ServerSocket ss = new ServerSocket(10027);
		
		Socket s = ss.accept();
		//
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+" ������");
		
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		PrintWriter  out = new PrintWriter(new FileWriter("b.txt"),true);
		
		String line = null;
		
		while( (line=bufIn.readLine()) != null){
			out.println(line);
		}
		
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
		pw.println("�ϴ��ɹ�");
		
		s.close();
		ss.close();
		bufIn.close();
		out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
