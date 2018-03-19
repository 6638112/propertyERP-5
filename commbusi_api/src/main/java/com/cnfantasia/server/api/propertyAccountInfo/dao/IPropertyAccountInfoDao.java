package com.cnfantasia.server.api.propertyAccountInfo.dao;

import com.cnfantasia.server.api.propertyAccountInfo.entity.PropertyAccountInfoEntity;
import com.cnfantasia.server.domainbase.propertyAccountInfo.dao.IPropertyAccountInfoBaseDao;

/**
 * 代扣卡账户
 * 
 * @author liyulin
 * @version 1.0 2016年11月4日 下午2:07:56
 */
public interface IPropertyAccountInfoDao extends IPropertyAccountInfoBaseDao{

	/**
	 * 更新代扣卡账户（加锁校验）
	 * 
	 * @param propertyAccountInfo
	 * @return
	 */
	public boolean updatePropertyAccountInfoWithLock(PropertyAccountInfoEntity propertyAccountInfo);
	
}
