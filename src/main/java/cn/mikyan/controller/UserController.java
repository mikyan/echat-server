package cn.mikyan.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import cn.mikyan.enums.OperatorFriendRequestTypeEnum;
// import cn.mikyan.enums.SearchFriendsStatusEnum;
import cn.mikyan.pojo.Users;
import cn.mikyan.pojo.bo.UsersBO;
import cn.mikyan.pojo.vo.MyFriendsVO;
import cn.mikyan.pojo.vo.UsersVO;
import cn.mikyan.service.UserService;
// import cn.mikyan.utils.FastDFSClient;
// import cn.mikyan.utils.FileUtils;
// import cn.mikyan.utils.ResponseJSON;
// import cn.mikyan.utils.MD5Utils;
import cn.mikyan.controller.MyAnnotation.*;
import cn.mikyan.controller.pojo.*;
@RestController
@RequestMapping("v1/u")

public class UserController {
    

    /**
     * 
     * @param user
     * @return
     * @throws Exception
     * 没啥用，就是试下自己写的登录拦截器
     * 这是个统一登录的验证，只需要在需要登录的接口前
     * 加上他就行了
     * 这是一个基于注解驱动的拦截过滤器模式
     */
    @LoginRequired
    @PostMapping("/test")
	public String test(@RequestBody Users user) throws Exception {
        return null;
    }
    
    // @Autowired
	private UserService userService;
	
	// @Autowired
	// private FastDFSClient fastDFSClient;

	/**
	 * @Description: 用户注册/登录
	 */
	@PostMapping("/registOrLogin")
	public ResponseJSON registOrLogin(HttpServletResponse response,@RequestBody Users user) throws Exception {
		
		// TODO 
		return null;
	}
	
	/**
	 * @Description: 上传用户头像
	 */
	
	/**
	 * 同base64传输效率也太低了，您给这发邮件哪
	 * 改成找到的用二进制的方法传输
	 * 头像没关系，聊天记录就必须得用那个了
     * 还是用base64
	 * @param userBO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFaceBase64")
	public ResponseJSON uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception {
		// TODO
		return null;
	}
	
	/**
	 * @Description: 设置用户昵称
	 */
	@PostMapping("/setNickname")
	public ResponseJSON setNickname(@RequestBody UsersBO userBO) throws Exception {
        
        // TODO
        return null;
	}
	
	/**
	 * @Description: 搜索好友接口, 根据账号做匹配查询而不是模糊查询
	 */
	@PostMapping("/search")
	public ResponseJSON searchUser(String myUserId, String friendUsername)
			throws Exception {
        // TODO
        
		return null;
		
	}
	
	
	/**
	 * @Description: 发送添加好友的请求
	 */
	@PostMapping("/addFriendRequest")
	public ResponseJSON addFriendRequest(String myUserId, String friendUsername)
			throws Exception {
		
		// TODO 
		return null;
	}
	
	/**
	 * @Description: 查询添加好友的请求
	 */
	@PostMapping("/queryFriendRequests")
	public ResponseJSON queryFriendRequests(String userId) {
		
		// TODO 
		return null;
	}
	
	
	/**
	 * @Description: 接受方 通过或者忽略朋友请求
	 */
	@PostMapping("/operatorFriendRequest")
	public ResponseJSON operatorFriendRequest(String acceptUserId, String sendUserId,
												Integer operType) {
		
		// TODO 
		return null;
	}
	
	/**
	 * @Description: 查询我的好友列表
	 */
	@PostMapping("/myFriends")
	public ResponseJSON myFriends(String userId) {
		// TODO 
		return null;
	}
	
	/**
	 * 
	 * @Description: 用户手机端获取未签收的消息列表
	 */
	@PostMapping("/getUnReadMsgList")
	public ResponseJSON getUnReadMsgList(String acceptUserId) {
		// TODO 
		return null;
	}
}