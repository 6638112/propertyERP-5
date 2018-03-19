package com.cnfantasia.server.ms.propertycard.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.revenue.task.ISynTask;

/**
 * 小区平均账单金额定时计算任务
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2016年09月09日       yanghua             1.0             1.0 Version
 */
@Repository
public class GroupBuildingBillAvgSynTask implements ISynTask{
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IGroupBuildingService groupBuildingService;
	
	@Resource
	private IPayBillService payBillService;
	
	@Override
	public Integer execTask() {
		int size = 0;
		logger.debug("GroupBuildingBillAvgSynTask start:" + DateUtils.getCurrentDate());
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("sys0AddTimeStart", null);//目前数据量偏少，暂时不适用
		paraMap.put("sys0AddTimeEnd", null);//目前数据量偏少，暂时不适用
		
		int length = 100;
		int begin = 0;
		
		//避免极端情况下大量数据一次性处理，此处只会循环每次处理100条，在某次少于50(或者少于100)条即已经处理完成。
		while(true) {
			paraMap.put("_begin", begin);
			paraMap.put("_length", length);
			
			List<Map<String, Object>> bgAvgList = payBillService.getGroupbuildingAvgAmount(paraMap);
			
			if(bgAvgList != null && bgAvgList.size() > 0) {
				groupBuildingService.updateGroupBuildingBillAvgBacth(bgAvgList);
			}
			
			begin += length;
			
			if(bgAvgList.size() < 100) {
				break;
			}
		}
		
		logger.debug("GroupBuildingBillAvgSynTask end:" + DateUtils.getCurrentDate() + ";size:" + size);
		return size;
	}

}
