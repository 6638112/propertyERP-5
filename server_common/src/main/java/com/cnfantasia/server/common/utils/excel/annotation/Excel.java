/**
 * Copyright (C) 2015 Shenzhen virgo network technology Co., Ltd. All rights reserved. 
 *
 * @Date:2015年10月20日 上午11:14:22
 *
 * @Version V1.0
 */
package com.cnfantasia.server.common.utils.excel.annotation;

import java.lang.annotation.*;


/**
 * @ClassName:Excel
 * @Date:2016年08月01日 上午10:04:22
 * @Description:(自定义注解 excel导入导出)
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
	public String title() default "";
}
