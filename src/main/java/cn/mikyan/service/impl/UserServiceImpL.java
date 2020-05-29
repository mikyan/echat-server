package cn.mikyan.service.impl;

import java.util.List;

import cn.mikyan.pojo.Users;
import cn.mikyan.pojo.UsersChatMsg;
import cn.mikyan.pojo.vo.FriendRequestVO;
import cn.mikyan.pojo.vo.MyFriendsVO;
import cn.mikyan.service.UserService;

public class UserServiceImpL implements UserService {

    @Override
    public boolean queryUsernameIsExist(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Users queryUserForLogin(String username, String pwd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users saveUser(Users user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users updateUserInfo(Users user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer preconditionSearchFriends(String myUserId, String friendUsername) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users queryUserInfoByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendFriendRequest(String myUserId, String friendUsername) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteFriendRequest(String sendUserId, String acceptUserId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void passFriendRequest(String sendUserId, String acceptUserId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<MyFriendsVO> queryMyFriends(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String saveMsg(UsersChatMsg chatMsg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateMsgSigned(List<String> msgIdList) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UsersChatMsg> getUnReadMsgList(String acceptUserId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}