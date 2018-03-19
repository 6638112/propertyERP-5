package com.cnfantasia.server.api.access.task;

import java.util.List;
import com.cnfantasia.server.api.access.entity.UserCarpushMsg;
import com.cnfantasia.server.api.access.service.IAccessService;

/**
 * 每天十二点推送停车费到期提醒
 * 定时将到期信息插入消息表中
 * @author ligs
 *
 */
public class CarMsgPushTask {
	private IAccessService accessService;
	public void setAccessService(IAccessService accessService) {
		this.accessService = accessService;
	}
	public void pushMsg(){
		excuteTask();
	}
	//查询到期车牌，消息提醒插入消息表
	private void excuteTask(){
		//用户到期车牌信息
		List<UserCarpushMsg> carmsgList = accessService.qrycarexpirePushMsg();
		if(carmsgList==null || carmsgList.size()<1){
			return;
		}
		accessService.pushTouserCarBill(carmsgList);
	}
}
