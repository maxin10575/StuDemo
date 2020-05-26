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
		
		//��ѡ����
		Selector selector = Selector.open();
		//�������
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
					System.out.println("�ͻ����������С�����");
					client = (SocketChannel) selectionKey.channel();
					if(client.isConnectionPending()){
						client.finishConnect();
						System.out.println("�ͻ��������ӣ�");
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
						receiveText = new String(receivebuffer.array(),0,count);//ת�����ַ���
						System.out.println("�ͻ��˽��յ���������ݣ�"+receiveText);
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
					System.out.println("�ͻ��˷������ݸ�����ˣ�"+sendText);
					client.register(selector, SelectionKey.OP_READ);
				}
				
			}
			//�����ÿ��ѭ���������в�ͬ��ֵ
			selectionKeys.clear();
		}
		
	}
}















