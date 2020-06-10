package cn.mikyan.netty.method;

import cn.mikyan.netty.method.binarymethod.BinaryMethod;
import cn.mikyan.netty.method.textmethod.TextMethod;

public abstract class AbstractMethodFactory {

    public abstract TextMethod getTextMethod(Integer actionInteger);
    public abstract BinaryMethod getBianaryMethod(Integer actionInteger);
}