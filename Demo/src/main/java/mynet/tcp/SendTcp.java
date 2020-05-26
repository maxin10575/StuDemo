package mynet.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendTcp {
	public static void main(String[] args) {
		try{
			StringBuffer stringBuffer = new StringBuffer();
			StringBuilder stringBuilder = new StringBuilder();
			Socket  socket = new Socket("127.0.0.1",10003);
			//��������
			OutputStream outputStream  = socket.getOutputStream();
			outputStream.write("����haha111��@".getBytes());
			
			//��������
			InputStream in = socket.getInputStream();
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			System.out.println(new String(buf,0,len));
			
			socket.close();
			
		}catch(Exception e){
			
		}
	}

}