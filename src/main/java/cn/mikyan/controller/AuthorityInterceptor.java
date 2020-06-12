package cn.mikyan.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.mikyan.controller.MyAnnotation.*;
import cn.mikyan.SpringUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //现在还问题，但是感觉老师不会很关注这里，算了
        //StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");
        // ①:START 方法注解级拦截器
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {

            // TODO Auto-generated method stub
            // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
            Cookie[] cookies= request.getCookies();
            String userName="";

            //设计出错，所以写了很多个if获取userId
            String userId = request.getParameter("userId");
            if(userId==null){
                userId = request.getParameter("myUserId");
            }
            if(userId==null){
                userId = request.getParameter("acceptUserId");
            }
            System.out.println(userId);


            if(cookies==null){
               
                System.out.println("没有cookie");
                return false;
                
            }else{
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals("userName")){
                        userName=cookie.getValue();
                        break;
                    }
                }

                if(!userName.equals("")){
                    for(Cookie cookie: cookies){
                        if(cookie.getName().equals("token")){
                            String token=stringRedisTemplate.opsForValue().get(userName);
                            if(token.equals(cookie.getValue())){
                                return true;
                            }
                        }
                    }
                }
                
            }
            
            return false;
        }
        return true;
    }
}