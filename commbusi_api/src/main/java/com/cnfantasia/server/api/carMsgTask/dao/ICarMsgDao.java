package com.cnfantasia.server.api.carMsgTask.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.carMsgTask.entity.CarSendMsgEntity;
import com.cnfantasia.server.api.carMsgTask.entity.CarSendPushEntity;

public interface ICarMsgDao {

	/**
	 * 查询短信参数信息
	 * 
	 * @return
	 */
	public List<CarSendMsgEntity> queryCarMsgInfo();
	
	/**
	 * 查询推送参数信息
	 * 
	 * @return
	 */
	public List<CarSendPushEntity> queryCarPushInfo();

	public Integer qryTimeoutInMonthCount(BigInteger userId);
}
