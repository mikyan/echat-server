package cn.mikyan.controller;


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
// import cn.mikyan.utils.IMoocJSONResult;
// import cn.mikyan.utils.MD5Utils;
import cn.mikyan.controller.MyAnnotation.*;

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
}