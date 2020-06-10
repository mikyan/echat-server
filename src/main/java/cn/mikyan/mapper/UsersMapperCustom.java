package cn.mikyan.mapper;

import java.util.List;

import cn.mikyan.pojo.Users;
import cn.mikyan.pojo.vo.FriendRequestVO;
import cn.mikyan.pojo.vo.MyFriendsVO;
import cn.mikyan.utils.MyMapper;

public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}