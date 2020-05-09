package cn.mikyan.netty;

import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class WSServer {
	
	/**
	 * 单例模式
	 * @author MIKYAN
	 *
	 */  
	private static class SingletionWSServer{
		static final WSServer INSTANCE = new WSServer();
		
	}
	
	public static WSServer getInstance() {
		
		return SingletionWSServer.INSTANCE;
	}
	
	private EventLoopGroup mainGroup;
	private EventLoopGroup subGroup;
	private ServerBootstrap server;
	private ChannelFuture future;
	
	private WSServer() {
		mainGroup = new NioEventLoopGroup();
		subGroup =new NioEventLoopGroup();
		server=new ServerBootstrap()
				.group(mainGroup,subGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new WSServerInitializer());
		
	}
	
	public void start() {
		this.future=server.bind(8088);
		System.err.println("netty websocket server 启动成功...");
	}
}
