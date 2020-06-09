package cn.mikyan.controller.MyAnnotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD }) // 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效

public @interface LoginRequired {
    
}