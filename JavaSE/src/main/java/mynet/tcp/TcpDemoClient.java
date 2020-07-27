package mynet.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpDemoClient {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 10005);
			//��ȡ������������
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			
			//������д�뵽socket����������͸������
			//BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//ʹ��PrintWriter
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			
			//socket��ȡ������ȡ����˷��صĴ�д��Ϣ
			BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String line = null;
			while ( (line = bufr.readLine())!=null ){
				if("over".equals(line)){
					break;
				}
				/*bufOut.write(line);
				bufOut.newLine();
				bufOut.flush();*/
				out.println(line);
				
				String str = bufIn.readLine();
				System.out.println("����˷��أ�"+str);
			}
			bufr.close();
			//����-1�������Ҳ��ֹͣ
			s.close();
			
		} catch (Exception e) {
		}
		
		
	}

}
