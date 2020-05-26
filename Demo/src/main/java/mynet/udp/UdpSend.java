package mynet.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSend {
	
	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			while ((line=bufferedReader.readLine())!=null) {
				if ("stop".equals(line)) {
					break;
				}
				byte[]	buf = line.getBytes();
				DatagramPacket dPacket = new DatagramPacket(buf, buf.length,InetAddress.getByName("127.0.0.1"),10001);
				datagramSocket.send(dPacket);
			}
			datagramSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
