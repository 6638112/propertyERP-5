package com.cnfantasia.server.api.access.service;

import java.util.List;

import com.cnfantasia.server.api.access.entity.ThirdFeeInfoEntity;

public interface IQryFeeService {

	/**
	 * 查询临停车费用
	 * 
	 * @param carNum
	 * @param thirdFeeInfoList
	 */
	public void qryTempFee(String carNum, List<ThirdFeeInfoEntity> thirdFeeInfoList);
}
