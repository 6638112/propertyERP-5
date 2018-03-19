package com.cnfantasia.server.carYhsMsg.service;

import java.util.List;

import com.cnfantasia.server.carYhsMsg.dao.ICarYhsMsgDao;
import com.cnfantasia.server.carYhsMsg.entity.CarYhsMsgSendParam;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;
import com.cnfantasia.server.domainbase.carYhsMsg.service.CarYhsMsgBaseService;

public class CarYhsMsgService extends CarYhsMsgBaseService implements
		ICarYhsMsgService {
	private ICarYhsMsgDao carYhsMsgDao;

	public void setCarYhsMsgDao(ICarYhsMsgDao carYhsMsgDao) {
		this.carYhsMsgDao = carYhsMsgDao;
	}
	
	/**
	 * 查询待发送的临停车缴费消息
	 * 
	 * @param gbIds 小区id
	 * @return
	 */
	public List<CarYhsMsg> selectCarYhsMsgForSending(List<Object> gbIds) {
		return carYhsMsgDao.selectCarYhsMsgForSending(gbIds);
	}

	/**
	 * 更新消息发送状态
	 * 
	 * @param carYhsMsgSendParam
	 * @return
	 */
	public boolean updateSendStatus(CarYhsMsgSendParam carYhsMsgSendParam) {
		return carYhsMsgDao.updateSendStatus(carYhsMsgSendParam);
	}
}
