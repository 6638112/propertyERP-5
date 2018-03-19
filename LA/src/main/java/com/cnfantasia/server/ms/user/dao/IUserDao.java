package com.cnfantasia.server.ms.user.dao;

import java.math.BigInteger;

import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.domainbase.omsUser.dao.IOmsUserBaseDao;
/**
 * 描述:() 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserDao extends IOmsUserBaseDao{
	/**
	 * 根据用户编号查询用户详情
	 * @param userId
	 * @return
	 */
	public UserEntity selectUserById(BigInteger userId);
}
