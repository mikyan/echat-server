package cn.mikyan.enums;


/**
 *  忽略或通过好友请求的枚举
 */
public enum FriendRequestOperatorEnum {
    IGNORE(0, "忽略"),
	PASS(1, "通过");
	
	public final Integer type;
	public final String msg;
	
	FriendRequestOperatorEnum(Integer type, String msg){
		this.type = type;
		this.msg = msg;
	}
	
	public Integer getType() {
		return type;
	}  
	
	public static String getMsgByType(Integer type) {
		for (FriendRequestOperatorEnum operType : FriendRequestOperatorEnum.values()) {
			if (operType.getType() == type) {
				return operType.msg;
			}
		}
		return null;
	}
}