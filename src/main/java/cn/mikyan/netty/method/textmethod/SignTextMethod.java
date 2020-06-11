package cn.mikyan.netty.method.textmethod;

import cn.mikyan.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import cn.mikyan.netty.pojo.DataContent;
import cn.mikyan.service.UserService;
import cn.mikyan.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SignTextMethod implements TextMethod {

	@Override
	public void doOperation(ChannelHandlerContext ctx, TextWebSocketFrame msg) {

		// 获取客户端传输过来的消息
		String content = msg.text();

		Channel currentChannel = ctx.channel();

		// 1. 获取客户端发来的消息
		DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
		Integer action = dataContent.getAction();

		// 2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]

		UserService userService = (UserService) SpringUtil.getBean("userServiceImpL");
		// 扩展字段在signed类型的消息中，代表需要去签收的消息id，逗号间隔
		String msgIdsStr = dataContent.getExtand();
		String msgIds[] = msgIdsStr.split(",");

		List<String> msgIdList = new ArrayList<>();
		for (String mid : msgIds) {
			if (StringUtils.isNotBlank(mid)) {
				msgIdList.add(mid);
			}
		}

		System.out.println(msgIdList.toString());

		if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
			// 批量签收
			userService.updateMsgSigned(msgIdList);
		}
	}

}