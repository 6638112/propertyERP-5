package com.cnfantasia.server.ms.payCarFee.service;

import java.util.Map;

import com.cnfantasia.server.ms.payCarFee.dao.IPayCarFeeDao;

/**
 * 停车缴费Service实现类
 *
 * @author Liyl
 * @version 1.0 2016年3月16日 上午5:31:09
 */
public class PayCarFeeService implements IPayCarFeeService {
	
	private IPayCarFeeDao payCarFeeDao;
	
	public void setPayCarFeeDao(IPayCarFeeDao payCarFeeDao) {
		this.payCarFeeDao = payCarFeeDao;
	}
	
	/**
	 * 查询车牌号是否已登记
	 * @param name 姓名
	 * @param carNo 车牌号
	 * @return
	 */
	@Override
	public boolean isCarNoRegistered(String name, String carNo){
		return payCarFeeDao.isCarNoRegistered(name, carNo);
	}
	
	/**
	 * 根据name和carNo查询userId、carId、groupBuildingId
	 * @param name
	 * @param carNo
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserInfoByCarNOAndName(String name, String carNo) {
		return payCarFeeDao.queryUserInfoByCarNOAndName(name, carNo);
	}
}
