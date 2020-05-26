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
		//是否阻塞
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		//绑定IP和端口
		serverSocket.bind(new InetSocketAddress(port));
		//打开选择器
		selector = Selector.open();
		//注册
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server start->"+port);
	}
	
	//监听
	public void lister() throws IOException{
		while(true){
			//获得事件列表
			selector.select();
			System.out.println("222");
			Set<SelectionKey> selectionKeys= selector.selectedKeys();
			Iterator<SelectionKey> it = selectionKeys.iterator();
			while(it.hasNext()){
				SelectionKey  selectionkey = it.next();
				it.remove();
				//业务逻辑
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
		
		if(selectionkey.isAcceptable()){//可接收的事件
			//强制转换
			server = (ServerSocketChannel) selectionkey.channel();
			//accept接收客户端连接，获取客户端SocketChannel
			client = server.accept();
			//非阻塞
			client.configureBlocking(false);
			//连接好之后，开始准备读数据，注册读事件
			client.register(selector,SelectionKey.OP_READ);
			
		}else if (selectionkey.isReadable()){ // 读
			//读取客户端，获得客户端channel
			client = (SocketChannel)selectionkey.channel();
			count = client.read(receivebuffer);
			if(count>0){
				//接收到客户端数据
				reciveText = new String(receivebuffer.array(),0,count);
				System.out.println("服务端接收到客户端的信息："+reciveText);
				//读到客户端数据后，注册写事件，以便发送响应数据
				client.register(selector,SelectionKey.OP_WRITE);
			}
			
		}else if(selectionkey.isWritable()){ //写
			sendbuffer.clear();
			client = (SocketChannel) selectionkey.channel();
			//发送的数据
			sendText = "msg send to client" + flag++;
			//把发送的数据写到缓冲区
			sendbuffer.put(sendText.getBytes());
			sendbuffer.flip();
			//发送缓冲区数据
			client.write(sendbuffer);
			System.out.println("服务端发送数据给客户端："+ sendText);
		}
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int port = 8000;
		NioServer server = new NioServer(port);
		server.lister();
	}
	
	
}













