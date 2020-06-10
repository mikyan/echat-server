package cn.mikyan.enums;

/**
 * @Description: 消息签收状态的枚举
 */
public enum MsgSignFlagEnum {
    
	UNSIGN(false, "未签收"),
	SIGNED(true, "已签收");	
	
	public final Boolean type;
	public final String content;
	
	MsgSignFlagEnum(Boolean type, String content){
		this.type = type;
		this.content = content;
	}
	
	public Boolean getType() {
		return type;
	}  
}