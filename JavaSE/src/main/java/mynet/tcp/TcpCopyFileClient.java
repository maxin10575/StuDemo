package mynet.tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpCopyFileClient {
	public static void main(String[] args) throws Exception {
		try{
		Socket s = new Socket("127.0.0.1", 10027);
		
		BufferedReader bufr = new BufferedReader(new FileReader("resource/a.txt"));

		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		String line = null;
		while ( (line = bufr.readLine())!=null ){
				out.println(line);
		}
		s.shutdownOutput();//�رտͻ��˵���������൱�ڸ����м���һ�����-1
		
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = bufIn.readLine();
		System.out.println(str);
		
		bufr.close();
		s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
