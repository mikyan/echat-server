package cn.mikyan.netty.method.textmethod;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public interface TextMethod {
    
    public  void doOperation(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception;
}