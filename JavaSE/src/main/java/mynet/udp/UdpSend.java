package mynet.udp;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSend {
	private static final Logger logger = LoggerFactory.getLogger(UdpRec.class);
	public final static int  c = 0;
	private int a = 1;
	private String  b = "aaa";
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

	@GetMapping("/user/{id}")
	public String findUserById(@PathVariable Integer id) {
		if (id != null && id < 1) {
			return "a";
			// throw new IllegalArgumentException("id < 1");
		} else {
			return "b";
		}
	}
	
	
	
	
}
