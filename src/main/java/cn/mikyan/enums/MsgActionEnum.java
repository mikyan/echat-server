package cn.mikyan.enums;


/**
 * 
 * @Description: 发送消息的动作 枚举
 */

public enum MsgActionEnum {
    
	CONNECT(1, "第一次(或重连)初始化连接"),
	CHAT(2, "聊天消息"),	
	SIGNED(3, "消息签收"),
	KEEPALIVE(4, "客户端保持心跳"),
    PULL_FRIEND(5, "拉取好友"),
	Friend_Request(6,"发送好友请求"),
	CHAT_IMAGE(7,"消息是图片");
	
	public final Integer type;
	public final String content;
	
	MsgActionEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}  
}


