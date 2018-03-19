package com.cnfantasia.server.business.pub.RepeatReqValidation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: RepeatReqValidate
 * @Date: 2017-01-03 9:55
 * @Auther: kangduo
 * @Description: (重复提交校验注解，用于请求接口，加注解表示校验)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatReqValidate {
    //算做重复提交的最大秒数
    int expireSecond() default 5;
}
