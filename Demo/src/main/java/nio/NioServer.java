package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
	private int BLOCKSIZE = 4096;
	private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCKSIZE);
	private ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCKSIZE);
	private Selector selector;
	private int flag = 1;
	
	public NioServer(int port) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//�Ƿ�����
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		//��IP�Ͷ˿�
		serverSocket.bind(new InetSocketAddress(port));
		//��ѡ����
		selector = Selector.open();
		//ע��
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server start->"+port);
	}
	
	//����
	public void lister() throws IOException{
		while(true){
			//����¼��б�
			selector.select();
			System.out.println("222");
			Set<SelectionKey> selectionKeys= selector.selectedKeys();
			Iterator<SelectionKey> it = selectionKeys.iterator();
			while(it.hasNext()){
				SelectionKey  selectionkey = it.next();
				it.remove();
				//ҵ���߼�
				handleKey(selectionkey);
			}
		}
	}
	//
	public void handleKey(SelectionKey  selectionkey) throws IOException{
		//
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String reciveText;
		String sendText;
		int count = 0 ;
		
		if(selectionkey.isAcceptable()){//�ɽ��յ��¼�
			//ǿ��ת��
			server = (ServerSocketChannel) selectionkey.channel();
			//accept���տͻ������ӣ���ȡ�ͻ���SocketChannel
			client = server.accept();
			//������
			client.configureBlocking(false);
			//���Ӻ�֮�󣬿�ʼ׼�������ݣ�ע����¼�
			client.register(selector,SelectionKey.OP_READ);
			
		}else if (selectionkey.isReadable()){ // ��
			//��ȡ�ͻ��ˣ���ÿͻ���channel
			client = (SocketChannel)selectionkey.channel();
			count = client.read(receivebuffer);
			if(count>0){
				//���յ��ͻ�������
				reciveText = new String(receivebuffer.array(),0,count);
				System.out.println("����˽��յ��ͻ��˵���Ϣ��"+reciveText);
				//�����ͻ������ݺ�ע��д�¼����Ա㷢����Ӧ����
				client.register(selector,SelectionKey.OP_WRITE);
			}
			
		}else if(selectionkey.isWritable()){ //д
			sendbuffer.clear();
			client = (SocketChannel) selectionkey.channel();
			//���͵�����
			sendText = "msg send to client" + flag++;
			//�ѷ��͵�����д��������
			sendbuffer.put(sendText.getBytes());
			sendbuffer.flip();
			//���ͻ���������
			client.write(sendbuffer);
			System.out.println("����˷������ݸ��ͻ��ˣ�"+ sendText);
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 8000;
		NioServer server = new NioServer(port);
		server.lister();
	}
	
	
}













