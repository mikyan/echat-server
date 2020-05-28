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


@RestController
@RequestMapping("u")
public class UserController {
    
}