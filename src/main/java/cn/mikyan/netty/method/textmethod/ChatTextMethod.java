package cn.mikyan.netty.method.textmethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.mikyan.SpringUtil;
import cn.mikyan.enums.MsgActionEnum;
import cn.mikyan.netty.ChatHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import cn.mikyan.netty.UserChannelRel;
import cn.mikyan.netty.pojo.ChatMsg;
import cn.mikyan.netty.pojo.DataContent;
import cn.mikyan.service.UserService;
import cn.mikyan.utils.JsonUtils;


public class ChatTextMethod implements TextMethod {


	@Override
	public void doOperation(ChannelHandlerContext ctx, TextWebSocketFrame msg) {

		// 获取客户端传输过来的消息
		String content = msg.text();

		Channel currentChannel = ctx.channel();

		// 1. 获取客户端发来的消息
		DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
		Integer action = dataContent.getAction();

		// 2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
		ChatMsg chatMsg = dataContent.getChatMsg();
		String msgText = chatMsg.getMsg();
		String receiverId = chatMsg.getReceiverId();
		String senderId = chatMsg.getSenderId();

		// 保存消息到数据库，并且标记为 未签收
		chatMsg.setType(MsgActionEnum.CHAT.type);
		UserService userService = (UserService) SpringUtil.getBean("userServiceImpL");
		String msgId = userService.saveMsg(chatMsg);
		chatMsg.setMsgId(msgId);
		
		//System.out.println(JsonUtils.objectToJson(chatMsg));
		DataContent dataContentMsg = new DataContent();
		dataContentMsg.setChatMsg(chatMsg);
		dataContentMsg.setAction(MsgActionEnum.CHAT.type);
		// 发送消息
		// 从全局用户Channel关系中获取接受方的channel
		Channel receiverChannel = UserChannelRel.get(receiverId);
		if (receiverChannel == null) {
			// TODO channel为空代表用户离线，推送消息（JPush，个推，小米推送）
		} else {
			// 当receiverChannel不为空的时候，从ChannelGroup去查找对应的channel是否存在
			Channel findChannel = ChatHandler.users.find(receiverChannel.id());
			if (findChannel != null) {
				// 用户在线
				receiverChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(dataContentMsg)));
			} else {
				// 用户离线 TODO 推送消息
				/**
				 * 这里有很明显的问题，在用户id和channel对应的map里找到了channel，但是在channelgroup里没有找到
				 * 那么应该是因为用户离线了，但是并没有清除原来的channel，做两个改进
				 * 一、在用户离线，清楚channelGroup里channel的同时，清楚userchannelrel里的channel
				 * 二、在这个地方添加清楚userchannelrel内channel的代码
				 */
			}
		}

	}

}