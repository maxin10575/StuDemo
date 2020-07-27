package aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;


public class AioServer {

	public AioServer(int port) throws Exception{
		final AsynchronousServerSocketChannel listen = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
		listen.accept(null,new CompletionHandler<AsynchronousSocketChannel,Void>(){
		
			@Override
			public void completed(AsynchronousSocketChannel ch, Void vi) {
				listen.accept(null,this);
				try {
					handler(ch);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				//ʧ��ʱ��ķ���
				System.out.println("�첽IOʧ��");
			}});
	}
	
	private void handler(AsynchronousSocketChannel ch) throws Exception {
		ByteBuffer bytebuffer = ByteBuffer.allocate(32);
		ch.read(bytebuffer).get();
		bytebuffer.flip();
		System.out.println("����˽��գ�===="+bytebuffer.get());
		
	}
	
	public static void main(String[] args) throws Exception {
		int port = 7080;
		AioServer server = new AioServer(port);
		System.out.println("����˼����˿ڣ�"+port);
		Thread.sleep(1000000);
	}
	
	
}









