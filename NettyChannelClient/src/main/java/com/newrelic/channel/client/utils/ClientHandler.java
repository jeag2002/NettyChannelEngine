package com.newrelic.channel.client.utils;

import com.newrelic.channel.client.beans.RequestData;
import com.newrelic.channel.client.beans.ResponseData;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
   
	public void channelActive(ChannelHandlerContext ctx) 
      throws Exception {
  
        RequestData msg = new RequestData();
        msg.setIntValue(123);
        msg.setStringValue(
          "all work and no play makes jack a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }
 
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) 
      throws Exception {
        System.out.println((ResponseData)msg);
        ctx.close();
    }
	
}
