package cn.mikyan.netty.method.textmethod;

import cn.mikyan.SpringUtil;
import cn.mikyan.netty.ChatHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import cn.mikyan.netty.UserChannelRel;
import cn.mikyan.netty.pojo.ChatMsg;
import cn.mikyan.netty.pojo.DataContent;
import cn.mikyan.service.UserService;
import cn.mikyan.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class HeartBeatTextMethod implements TextMethod {

	@Override
	public void doOperation(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
		// 获取客户端传输过来的消息
		String content = msg.text();

		Channel currentChannel = ctx.channel();

		// 1. 获取客户端发来的消息
		DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
		Integer action = dataContent.getAction();

		//  2.4  心跳类型的消息
		System.out.println("收到来自channel为[" + currentChannel + "]的心跳包...");

	}

}