package com.cnfantasia.server.ms.pub.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: CheckLogin.
 * @Date: 2017-09-01 13:45
 * @Auther: kangduo
 * @Description: (有的页面由于分享等原因, 未登录也可以访问, 此注解标记接口要先登录, 未登录会跳授权登录.)
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {
}
