package mynet.tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpRec {
    public static void main(String[] args) {
        try {
            //����
            ServerSocket ss = new ServerSocket(10003);
            Socket s = ss.accept();

            String ip = s.getInetAddress().getHostAddress();
            System.out.println("ip===" + ip);

            //��������
            InputStream in = s.getInputStream();
			/*InputStreamReader inputStreamReader= new InputStreamReader(in);
			int a ;
			while( (a=inputStreamReader.read())!= -1 ){
				System.out.println((char)a);
			}*/
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            System.out.println(new String(buf, 0, len));

            //��������
            OutputStream out = s.getOutputStream();
            Thread.sleep(4000);
            out.write("����ֵ".getBytes());


            s.close();//�رտͻ���
            ss.close();//�رշ����
        } catch (Exception e) {
            // TODO: handle exception
        } finally {

        }

    }
}
