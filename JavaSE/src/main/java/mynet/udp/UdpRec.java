package mynet.udp;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@RestController
public class UdpRec {
	public static void main(String[] args) {
		try {
			DatagramSocket datagramSocket = new DatagramSocket(10001);
			while (true) {
				byte[]	buf = new byte[1024];
				DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length);
				datagramSocket.receive(datagramPacket);
				String ip = datagramPacket.getAddress().getHostAddress();
				String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				System.out.println(ip+":"+data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


