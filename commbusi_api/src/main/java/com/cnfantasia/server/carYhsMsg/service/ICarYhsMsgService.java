package com.cnfantasia.server.carYhsMsg.service;

import java.util.List;

import com.cnfantasia.server.carYhsMsg.entity.CarYhsMsgSendParam;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;
import com.cnfantasia.server.domainbase.carYhsMsg.service.ICarYhsMsgBaseService;

/**
 * 临停车缴费消息发送
 * 
 * @author liyulin
 * @version 1.0 2016年8月16日 下午3:28:11
 */
public interface ICarYhsMsgService extends ICarYhsMsgBaseService{

	/**
	 * 查询待发送的临停车缴费消息
	 * 
	 * @param gbIds 小区id
	 * @return
	 */
	public List<CarYhsMsg> selectCarYhsMsgForSending(List<Object> gbIds);
	
	/**
	 * 更新消息发送状态
	 * 
	 * @param carYhsMsgSendParam
	 * @return
	 */
	public boolean updateSendStatus(CarYhsMsgSendParam carYhsMsgSendParam);
}
