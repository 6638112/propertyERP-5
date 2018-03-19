package com.cnfantasia.server.ms.user.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domainbase.omsUser.dao.OmsUserBaseDao;
import com.cnfantasia.server.ms.user.entity.UserEntity;
/**
 * 描述:() 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserDao extends OmsUserBaseDao implements IUserDao{

	@Override
	public UserEntity selectUserById(BigInteger userId) {
		return selectUserById(userId);
	}

}
