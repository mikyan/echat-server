package cn.mikyan.netty.method;

import cn.mikyan.enums.MsgActionEnum;
import cn.mikyan.netty.method.binarymethod.BinaryMethod;
import cn.mikyan.netty.method.textmethod.ConnectTextMethod;
import cn.mikyan.netty.method.textmethod.TextMethod;

public class TextMethodFactory extends AbstractMethodFactory {

    @Override
    public TextMethod getTextMethod(Integer actionInteger) {
        if(actionInteger==MsgActionEnum.CONNECT.type){
            return new ConnectTextMethod();
        }
        return null;
    }

    @Override
    public BinaryMethod getBianaryMethod(Integer actionInteger) {
        //Don't do anything
        return null;
    }
    
}