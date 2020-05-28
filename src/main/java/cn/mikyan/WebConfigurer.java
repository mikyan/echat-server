package cn.mikyan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.mikyan.controller.AuthorityInterceptor;

/**
 * 和springmvc的webmvc拦截配置一样
 * 
 * @author BIANP
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        // registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(AuthorityInterceptor()).addPathPatterns("/**");
	 }
	 
	//  @Bean
	//  public LoginInterceptor LoginInterceptor() {
	// 	 return new LoginInterceptor();
	//  }
	 
	 @Bean
	 public AuthorityInterceptor AuthorityInterceptor() {
		 return new AuthorityInterceptor();
	 }
}