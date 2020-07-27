package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {

	private static int flag = 1;
	private static int BLOCKSIZE = 4096;
	private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCKSIZE);
	private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCKSIZE);
	private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",8000);
	
	public static void main(String[] args) throws IOException {
		SocketChannel scketChannel = SocketChannel.open();
		scketChannel.configureBlocking(false);
		
		//打开选择器
		Selector selector = Selector.open();
		//获得连接
		scketChannel.register(selector, SelectionKey.OP_CONNECT);
		scketChannel.connect(serverAddress);
		
		Set<SelectionKey> selectionKeys;
		Iterator<SelectionKey> it;
		SelectionKey selectionKey;
		SocketChannel client;
		String receiveText;
		String sendText;
		int count = 0;
		
		while(true){
			selectionKeys = selector.selectedKeys();
			it = selectionKeys.iterator();
			while(it.hasNext()){
				selectionKey = it.next();
				
				if(selectionKey.isConnectable()){
					System.out.println("客户端已连接中。。。");
					client = (SocketChannel) selectionKey.channel();
					if(client.isConnectionPending()){
						client.finishConnect();
						System.out.println("客户端已连接！");
						sendbuffer.clear();
						sendbuffer.put("hello server".getBytes());
						sendbuffer.flip();
						client.write(sendbuffer);
					}
					client.register(selector, SelectionKey.OP_READ);
				}
				
				if(selectionKey.isReadable()){
					client= (SocketChannel) selectionKey.channel();
					receivebuffer.clear();
					count = client.read(receivebuffer);
					if(count>0){
						receiveText = new String(receivebuffer.array(),0,count);//转换成字符串
						System.out.println("客户端接收到服务端数据："+receiveText);
						client.register(selector, SelectionKey.OP_WRITE);
					}
				}
				
				if(selectionKey.isWritable()){
					sendbuffer.clear();
					client = (SocketChannel) selectionKey.channel();
					sendText = "Msg send to Server..."+flag++;
					sendbuffer.put(sendText.getBytes());
					sendbuffer.flip();
					client.write(sendbuffer);
					System.out.println("客户端发送数据给服务端："+sendText);
					client.register(selector, SelectionKey.OP_READ);
				}
				
			}
			//清除，每次循环进来会有不同的值
			selectionKeys.clear();
		}
		
	}
}















