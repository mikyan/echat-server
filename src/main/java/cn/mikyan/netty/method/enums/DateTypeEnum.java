package cn.mikyan.netty.method.enums;

public enum DateTypeEnum {
    Text_Type(1,"文字类型"),
    Binary_Type(2,"二进制类型");
    
    
	public final Integer type;
	public final String content;
	
	DateTypeEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}  
}