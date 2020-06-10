package cn.mikyan.netty.method;

import cn.mikyan.netty.method.enums.DateTypeEnum;

public class FactoryProducer {
    public static AbstractMethodFactory getFactory(Integer type){
        if(type==DateTypeEnum.Text_Type.type){
           return new TextMethodFactory();
        } else if(type==DateTypeEnum.Binary_Type.type){
           return new BinaryMethodFactory();
        }
        return null;
     }
}