package cn.mikyan.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.mikyan.controller.MyAnnotation.*;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
       if (!(handler instanceof HandlerMethod)) {
           return true;
       }
       // ①:START 方法注解级拦截器
       HandlerMethod handlerMethod = (HandlerMethod) handler;
       Method method = handlerMethod.getMethod();
       // 判断接口是否需要登录
       LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
       // 有 @LoginRequired 注解，需要认证
       if (methodAnnotation != null) {

        // TODO Auto-generated method stub
           // 这写你拦截需要干的事儿，比如取缓存，SESSION，权限判断等
           System.out.println("====================================");
           return true;
       }
       return true;
   }
}