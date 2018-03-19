package com.cnfantasia.server.ms.user.service;

import java.math.BigInteger;

import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.IOmsUserBaseService;
/**
 * 描述:() 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserService extends IOmsUserBaseService{
	/**
	 * 根据用户编号查询用户详情
	 * @param userId
	 * @return
	 */
	public UserEntity getUserById(BigInteger userId);
	
	
	/**
	 * 更改个人信息
	 * @return
	 */
	public UserEntity updPersonInfo(OmsUser omsUser);
}
