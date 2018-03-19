package com.cnfantasia.server.api.propertyAccountInfo.dao;

import com.cnfantasia.server.api.propertyAccountInfo.entity.PropertyAccountInfoEntity;
import com.cnfantasia.server.domainbase.propertyAccountInfo.dao.PropertyAccountInfoBaseDao;

/**
 * 代扣卡账户
 * 
 * @author liyulin
 * @version 1.0 2016年11月4日 下午2:07:56
 */
public class PropertyAccountInfoDao extends PropertyAccountInfoBaseDao
		implements IPropertyAccountInfoDao {

	/**
	 * 更新代扣卡账户（加锁校验）
	 * 
	 * @param propertyAccountInfo
	 * @return
	 */
	@Override
	public boolean updatePropertyAccountInfoWithLock(
			PropertyAccountInfoEntity propertyAccountInfo) {
		return sqlSession.update(
				"propertyAccountInfo.update_propertyAccountInfo_withLock",
				propertyAccountInfo) > 0;
	}

}