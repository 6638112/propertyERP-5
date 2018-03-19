package com.cnfantasia.server.ms.user.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.ms.user.dao.IUserDao;
import com.cnfantasia.server.ms.user.entity.UserEntity;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;
import com.cnfantasia.server.domainbase.omsUser.service.OmsUserBaseService;
/**
 * 描述:() 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserService extends OmsUserBaseService implements IUserService{
	private IUserDao userDao;
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserEntity getUserById(BigInteger userId) {
		return userDao.selectUserById(userId);
	}

	@Override
	public UserEntity updPersonInfo(OmsUser omsUser) {
		int updRes = userDao.updateOmsUser(omsUser);
		if(updRes<=0){
			throw new BusinessRuntimeException("UserService.updPersonInfo.failed");
		}
		return userDao.selectUserById(omsUser.getId());
	}
}
