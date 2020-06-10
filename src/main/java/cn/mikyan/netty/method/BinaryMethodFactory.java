package cn.mikyan.netty.method;

import cn.mikyan.netty.method.binarymethod.BinaryMethod;
import cn.mikyan.netty.method.textmethod.TextMethod;

public class BinaryMethodFactory extends AbstractMethodFactory {

    @Override
    public TextMethod getTextMethod(Integer actionInteger) {
        //Don't do anything
        return null;
    }

    @Override
    public BinaryMethod getBianaryMethod(Integer actionInteger) {
        // TODO Auto-generated method stub
        return null;
    }
    
}