package com.cnfantasia.server.ms.payCountInit.task;

import com.cnfantasia.server.commbusi.UserAndDevicePayCount.service.IUserAndDevicePayCountService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.ms.revenue.task.ISynTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 小区平均账单金额定时计算任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2016年09月30日       yanghua             1.0             1.0 Version
 */
@Repository
public class UserAndDevicePayCountInitSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IUserAndDevicePayCountService userAndDevicePayCountService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("UserAndDevicePayCountInitSynTask start:" + DateUtils.getCurrentDate());

		size = userAndDevicePayCountService.updateUserPayCount();
		size += userAndDevicePayCountService.updateDevicePayCount();
		
		logger.debug("UserAndDevicePayCountInitSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}

}
