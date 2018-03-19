package com.cnfantasia.server.api.performPro;

import org.junit.Test;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.performPro.service.IPerformProService;

/**
 * 测试设备处理
* Filename:    TestPerformProService.java
* @version:    1.0.0
* Create at:   2015年6月23日 上午8:53:21
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年6月23日       shiyl             1.0             1.0 Version
 */
public class TestPerformProService extends BaseTest{
	
	@Test
	public void test001(){
		IPerformProService performProService = ctx.getBean("performProService", IPerformProService.class);
		String deviceId = "ac:f7:f3:a5:63:94,0";
		Long deviceType = 1L;
		Object res = performProService.getIsNewUser(deviceId, deviceType);
		System.out.println(res);
	}
}
