package mynet.tcp;

import java.io.*;
import java.net.Socket;

public class TcpPictureUploadClient {
	public static void main(String[] args) {

		try {
			
			if(args.length!=1){
				System.out.println("��ѡ��ͼƬ");
				return;
			}
			File file = new File(args[0]);
			if(!(file.exists()) && file.isFile()){
				System.out.println("�ļ�������");
				return;
			}
			if(!file.getName().endsWith(".png")){
				System.out.println("�ļ���ʽ������");
				return;
			}
			if(file.length() > 1024*1024*5){
				System.out.println("�ļ�̫��");
				return;
			}
			
			Socket s = new Socket("127.0.0.1", 10007);
//			FileInputStream fis = new FileInputStream("resource/1.png");
			FileInputStream fis = new FileInputStream(file);

			OutputStream out = s.getOutputStream();

			byte[] buf = new byte[1024];

			int len = 0;

			while ((len = fis.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			//�������
			s.shutdownOutput();
			InputStream in = s.getInputStream();
			byte[] bufIn = new byte[1024];
			int num = in.read(bufIn);
			System.out.println(new String(bufIn, 0, num));
			fis.close();
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
