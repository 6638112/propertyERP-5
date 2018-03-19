package com.cnfantasia.server.ms.payCarFee.dao;

import java.math.BigInteger;
import java.util.Map;

/**
 * 停车缴费DAO接口
 *
 * @author Liyl
 * @version 1.0 2016年3月16日 上午5:29:07
 */
public interface IPayCarFeeDao {
	
	/**
	 * 查询车牌号是否已登记
	 * @param name 姓名
	 * @param carNo 车牌号
	 * @return
	 */
	public boolean isCarNoRegistered(String name, String carNo);
	
	/**
	 * 根据name和carNo查询userId、carId、groupBuildingId
	 * @param name
	 * @param carNo
	 * @return
	 */
	public Map<String, Object> queryUserInfoByCarNOAndName(String name, String carNo);
}
