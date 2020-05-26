package mynet.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class Send implements Runnable {
	private DatagramSocket ds;
    Send (DatagramSocket ds) {
		this.ds = ds;
	}
	@Override
	public void run() {
		try{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line=bufferedReader.readLine())!=null) {
			if ("stop".equals(line)) {
				break;
			}
			byte[]	buf = line.getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length,InetAddress.getByName("127.0.0.255"),10002);
			ds.send(dp);
		}
		ds.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}

class Rec implements Runnable {
	private DatagramSocket dSocket;
	Rec (DatagramSocket dSocket) {
		this.dSocket = dSocket;
	}
	@Override
	public void run() {
		try {
			while (true) {
				byte[]	buf = new byte[1024];
				DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length);
				dSocket.receive(datagramPacket);
				String ip = datagramPacket.getAddress().getHostAddress();
				String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				System.out.println(ip+":"+data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class UdpRecAndSend{
	public static void main(String[] args) throws SocketException {
		DatagramSocket sendSocket = new DatagramSocket();
		DatagramSocket recSocket = new DatagramSocket(10002);
		new Thread(new Rec(recSocket)).start();
		new Thread(new Send(sendSocket)).start();
	}
}

