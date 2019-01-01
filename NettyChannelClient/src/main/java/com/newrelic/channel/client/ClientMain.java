package com.newrelic.channel.client;

import com.newrelic.channel.client.utils.ClientHandler;
import com.newrelic.channel.client.utils.RequestDataEncoder;
import com.newrelic.channel.client.utils.ResponseDataDecoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMain {

	public static void main(String[] args) throws Exception {
		
		String host = "localhost";
		int port = 8080;
		
		// TODO Auto-generated method stub
		if (args.length == 2) {
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		
		ClientMain main = new ClientMain();
		main.run(host, port);
	}
	
	
	public void run(String host, int port) throws Exception{
		
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		 
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
  
                @Override
                public void initChannel(SocketChannel ch) 
                  throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(), 
                      new ResponseDataDecoder(), new ClientHandler());
                }
            });
 
            ChannelFuture f = b.connect(host, port).sync();
 
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
		
		
		
	}

}
