package cn.mikyan.netty.method;

import cn.mikyan.enums.MsgActionEnum;
import cn.mikyan.netty.method.binarymethod.*;
import cn.mikyan.netty.method.textmethod.*;

public class TextMethodFactory extends AbstractMethodFactory {

    @Override
    public TextMethod getTextMethod(Integer actionInteger) {
        if(actionInteger==MsgActionEnum.CONNECT.type){
            return new ConnectTextMethod();
        }else if(actionInteger==MsgActionEnum.CHAT.type){
            return new ChatTextMethod();
        }else if(actionInteger==MsgActionEnum.SIGNED.type){
            return new SignTextMethod();
        }else if(actionInteger==MsgActionEnum.KEEPALIVE.type){
            return new HeartBeatTextMethod();
        }
        else if(actionInteger==MsgActionEnum.PULL_FRIEND.type){
            return null;
        }
        else if(actionInteger==MsgActionEnum.Friend_Request.type){
            return null;
        }
        else if(actionInteger==MsgActionEnum.CHAT_IMAGE.type){
            return new ImageChatTextMethod();
        }
        return null;
    }

    // CONNECT(1, "第一次(或重连)初始化连接"),
	// CHAT(2, "聊天消息"),	
	// SIGNED(3, "消息签收"),
	// KEEPALIVE(4, "客户端保持心跳"),
    // PULL_FRIEND(5, "拉取好友"),
	// Friend_Request(6,"发送好友请求"),
	// CHAT_IMAGE(7,"消息是图片");
    @Override
    public BinaryMethod getBianaryMethod(Integer actionInteger) {
        //Don't do anything
        return null;
    }
    
}