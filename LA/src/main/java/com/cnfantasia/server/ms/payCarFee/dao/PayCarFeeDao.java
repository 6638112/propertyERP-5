package com.cnfantasia.server.ms.payCarFee.dao;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
/**
 * 停车缴费DAO实现类
 * 
 * @author Liyl
 * @version 1.0 2016年3月16日 上午5:29:33
 */
public class PayCarFeeDao extends AbstractBaseDao implements IPayCarFeeDao {

	/**
	 * 查询车牌号是否已登记
	 * 
	 * @param name 姓名
	 * @param carNo 车牌号
	 * @return
	 */
	@Override
	public boolean isCarNoRegistered(String name, String carNo) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("name", name);
		paramMap.put("carNo", carNo);
		final Integer count = sqlSession.selectOne("access.select_CarNo_Name_Count", paramMap);
		return null != count && 0 < count;
	}
	
	/**
	 * 根据name和carNo查询userId、carId、groupBuildingId
	 * @param name
	 * @param carNo
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserInfoByCarNOAndName(String name, String carNo){
		final Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("name", name);
		paramMap.put("carNo", carNo);
		final Map<String, Object> userInfo = sqlSession.selectOne("access.select_UserInfo_By_CarNo", paramMap);
		return userInfo;
	}
}
