package cn.mikyan.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.RepaintManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.mikyan.enums.FriendRequestOperatorEnum;
import cn.mikyan.enums.SearchFriendsStatusEnum;
import cn.mikyan.pojo.Users;
import cn.mikyan.pojo.bo.UsersBO;
import cn.mikyan.pojo.vo.MyFriendsVO;
import cn.mikyan.pojo.vo.UsersVO;
import cn.mikyan.service.UserService;
import cn.mikyan.utils.FastDFSClient;
import cn.mikyan.utils.FileUtils;
import cn.mikyan.utils.JsonUtils;
import cn.mikyan.controller.pojo.ResponseJSON;
import cn.mikyan.utils.MD5Utils;
import cn.mikyan.controller.MyAnnotation.*;
import org.n3r.idworker.Sid;

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
    
    @Autowired
	private UserService userService;
	
	@Autowired
	private FastDFSClient fastDFSClient;

	@Autowired
	private Sid sid;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
	 * @Description: 用户注册/登录
	 */
	@PostMapping("/registOrLogin")
	public ResponseJSON registOrLogin(HttpServletResponse response,@RequestBody Users user) throws Exception {
		// 0. 判断用户名和密码不能为空
		if (StringUtils.isBlank(user.getUsername()) 
				|| StringUtils.isBlank(user.getPassword())) {
			return ResponseJSON.errorMsg("用户名或密码不能为空...");
		}
		
		// 1. 判断用户名是否存在，如果存在就登录，如果不存在则注册
		boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
		Users userResult = null;
		UsersVO userVO = new UsersVO();
		if (usernameIsExist) {
			// 1.1 登录
			userResult = userService.queryUserForLogin(user.getUsername(), 
									MD5Utils.getMD5Str(user.getPassword()));
			if (userResult == null) {
				return ResponseJSON.errorMsg("用户名或密码不正确..."); 
			}
			
			
		} else {
			String token=sid.nextShort();
			stringRedisTemplate.opsForValue().set(user.getUsername(),token);
			// 1.2 注册
			user.setNickName(user.getUsername());
			user.setFaceimage("");
			user.setFaceimageBig("");
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			userResult = userService.saveUser(user);
		}
		//待添加在redis里添加的代码
		boolean hasKey = stringRedisTemplate.hasKey(user.getUsername());
		String token="";
		if(!hasKey){
			token=sid.nextShort();
			stringRedisTemplate.opsForValue().set(user.getUsername(),token);
		}else{
			token=stringRedisTemplate.opsForValue().get(user.getUsername());
		}

		//保存token
		Cookie cookie = new Cookie("token",token);
		cookie.setPath("/");
		response.addCookie(cookie);

		//保存username
		cookie = new Cookie("userName",user.getUsername());
		cookie.setPath("/");
		response.addCookie(cookie);

		//保存userid
		cookie = new Cookie("userId",user.getId());
		cookie.setPath("/");
		response.addCookie(cookie);

		BeanUtils.copyProperties(userResult, userVO);
		return ResponseJSON.ok(userVO);

	}
	
	/**
	 * @Description: 上传用户头像
	 */
	
	
	
	/**
	 * 同base64传输效率也太低了，您给这发邮件哪
	 * 改成找到的用二进制的方法传输
	 * 头像没关系，聊天记录就必须得用那个了
	 * @param userBO
	 * @return
	 * @throws Exception
	 */
	@LoginRequired
	@PostMapping("/uploadFaceBase64")
	public ResponseJSON uploadFaceBase64(HttpServletRequest request,@RequestBody UsersBO userBO) throws Exception {
		// 获取前端传过来的base64字符串, 然后转换为文件对象再上传
		String base64Data = userBO.getFaceData();
		String userFacePath = "D:\\image\\" + userBO.getUserId() + "userface64.png";
		FileUtils.base64ToFile(userFacePath, base64Data);
		
		// 上传文件到fastdfs
		MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
		String url = fastDFSClient.uploadBase64(faceFile);
		System.out.println(url);
		
//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"
		
		// 获取缩略图的url
		String thump = "_80x80.";
		String arr[] = url.split("\\.");
		String thumpImgUrl = arr[0] + thump + arr[1];
		
		// 更新用户头像
		Users user = new Users();
		user.setId(userBO.getUserId());
		user.setFaceimage(thumpImgUrl);
		user.setFaceimageBig(url);
		
		Users result = userService.updateUserInfo(user);
		
		return ResponseJSON.ok(result);
	}


	/**
	 * @Description: 设置用户昵称
	 */
	@LoginRequired
	@PostMapping("/setNickname")
	public ResponseJSON setNickname(HttpServletRequest request,@RequestBody UsersBO userBO) throws Exception {
        
        Users user = new Users();
		user.setId(userBO.getUserId());
		user.setNickName(userBO.getNickname());
		
		Users result = userService.updateUserInfo(user);
		
		return ResponseJSON.ok(result);
	}
	
	/**
	 * @Description: 搜索好友接口, 根据账号做匹配查询而不是模糊查询
	 */
	@PostMapping("/search")
	public ResponseJSON searchUser(String myUserId, String friendUsername)
			throws Exception {
        
		// 0. 判断 myUserId friendUsername 不能为空
		if (StringUtils.isBlank(myUserId) 
				|| StringUtils.isBlank(friendUsername)) {
			return ResponseJSON.errorMsg("");
		}
		
		// 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
		// 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
		// 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
		Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
		if (status == SearchFriendsStatusEnum.SUCCESS.status) {
			Users user = userService.queryUserInfoByUsername(friendUsername);
			UsersVO userVO = new UsersVO();
			BeanUtils.copyProperties(user, userVO);
			return ResponseJSON.ok(userVO);
		} else {
			String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
			return ResponseJSON.errorMsg(errorMsg);
		}
		
	}
	
	
	/**
	 * @Description: 发送添加好友的请求
	 */
	@LoginRequired
	@PostMapping("/addFriendRequest")
	public ResponseJSON addFriendRequest(HttpServletRequest request,String myUserId, String friendUsername)
			throws Exception {
		
		// 0. 判断 myUserId friendUsername 不能为空
		if (StringUtils.isBlank(myUserId) 
				|| StringUtils.isBlank(friendUsername)) {
			return ResponseJSON.errorMsg("");
		}
		
		// 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
		// 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
		// 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
		Integer status = userService.preconditionSearchFriends(myUserId, friendUsername);
		if (status == SearchFriendsStatusEnum.SUCCESS.status) {
			userService.sendFriendRequest(myUserId, friendUsername);
		} else {
			String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
			return ResponseJSON.errorMsg(errorMsg);
		}
		
		return ResponseJSON.ok();
	}
	
	/**
	 * @Description: 查询添加好友的请求
	 */
	@LoginRequired
	@PostMapping("/queryFriendRequests")
	public ResponseJSON queryFriendRequests(HttpServletRequest request,String userId) {
		
		// 0. 判断不能为空
		if (StringUtils.isBlank(userId)) {
			return ResponseJSON.errorMsg("");
		}
		
		// 1. 查询用户接受到的朋友申请
		return ResponseJSON.ok(userService.queryFriendRequestList(userId));
	}
	
	
	/**
	 * @Description: 接受方 通过或者忽略朋友请求
	 */
	@LoginRequired
	@PostMapping("/operatorFriendRequest")
	public ResponseJSON operatorFriendRequest(HttpServletRequest request,String acceptUserId, String sendUserId,
												Integer operType) {
		
		// 0. acceptUserId sendUserId operType 判断不能为空
		if (StringUtils.isBlank(acceptUserId) 
				|| StringUtils.isBlank(sendUserId) 
				|| operType == null) {
			return ResponseJSON.errorMsg("");
		}
		
		// 1. 如果operType 没有对应的枚举值，则直接抛出空错误信息
		if (StringUtils.isBlank(FriendRequestOperatorEnum.getMsgByType(operType))) {
			return ResponseJSON.errorMsg("");
		}
		
		if (operType == FriendRequestOperatorEnum.IGNORE.type) {
			// 2. 判断如果忽略好友请求，则直接删除好友请求的数据库表记录
			userService.deleteFriendRequest(sendUserId, acceptUserId);
		} else if (operType == FriendRequestOperatorEnum.PASS.type) {
			// 3. 判断如果是通过好友请求，则互相增加好友记录到数据库对应的表
			//	   然后删除好友请求的数据库表记录
			userService.passFriendRequest(sendUserId, acceptUserId);
		}
		
		// 4. 数据库查询好友列表
		List<MyFriendsVO> myFirends = userService.queryMyFriends(acceptUserId);
		
		return ResponseJSON.ok(myFirends);
	}
	
	/**
	 * @Description: 查询我的好友列表
	 */
	@LoginRequired
	@PostMapping("/myFriends")
	public ResponseJSON myFriends(HttpServletRequest request,String userId) {
		// 0. userId 判断不能为空
		if (StringUtils.isBlank(userId)) {
			return ResponseJSON.errorMsg("");
		}
		
		// 1. 数据库查询好友列表
		List<MyFriendsVO> myFirends = userService.queryMyFriends(userId);
		System.out.println(JsonUtils.objectToJson(myFirends));
		return ResponseJSON.ok(myFirends);
	}
	
	/**
	 * 
	 * @Description: 用户手机端获取未签收的消息列表
	 */
	@LoginRequired
	@PostMapping("/getUnReadMsgList")
	public ResponseJSON getUnReadMsgList(HttpServletRequest request,String acceptUserId) {
		// 0. userId 判断不能为空
		if (StringUtils.isBlank(acceptUserId)) {
			return ResponseJSON.errorMsg("");
		}
		
		// 查询列表
		List<cn.mikyan.pojo.UsersChatMsg> unreadMsgList = userService.getUnReadMsgList(acceptUserId);
		
		return ResponseJSON.ok(unreadMsgList);
	}
}