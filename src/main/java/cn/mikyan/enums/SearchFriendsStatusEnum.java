package cn.mikyan.enums;


/**
 * 添加好友的状态
 */
public enum SearchFriendsStatusEnum {
    SUCCESS(0, "OK"),
	NOT_EXIST(1, "无此用户"),	
	NOT_YOURSELF(2, "不能添加自己"),			
	ALREADY_FRIENDS(3, "不能重复添加好友");
	
	public final Integer status;
	public final String msg;
	
	SearchFriendsStatusEnum(Integer status, String msg){
		this.status = status;
		this.msg = msg;
	}
	
	public Integer getStatus() {
		return status;
	}  
	
	public static String getMsgByKey(Integer status) {
		for (SearchFriendsStatusEnum type : SearchFriendsStatusEnum.values()) {
			if (type.getStatus() == status) {
				return type.msg;
			}
		}
		return null;
	}
}