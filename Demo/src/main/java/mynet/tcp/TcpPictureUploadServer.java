package mynet.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpPictureUploadServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(10007);
		while (true) {
			Socket s = ss.accept();
//			new Thread(new tcp.PicThread(s)).start();
		}
		// �رշ����
		// ss.close();
	}
}

// ����
class PicThread implements Runnable {
    private Socket s;

    PicThread(Socket s) {
        this.s = s;
    }

    public void run() {
        int count = 0;
        String ip = s.getInetAddress().getHostAddress();
    try{
        System.out.println(ip + "......connetted");
        InputStream in = s.getInputStream();

        File file = new File(ip +"("+ (count)+")"+".jpg");
        while(file.exists()){
            file =new File(ip +"("+ (count++)+")"+".jpg");
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        OutputStream out = s.getOutputStream();
        out.write("�ϴ��ɹ�".getBytes());
        fos.close();
        // �رտͻ���
        s.close();

    }catch(Exception e){
        e.printStackTrace();
    }
}
}
