package com.cnfantasia.server.api.roomValidate.dao;

import java.math.BigInteger;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domainbase.roomValidate.dao.RoomValidateBaseDao;

public class RoomValidateDao extends RoomValidateBaseDao implements
		IRoomValidateDao {
	
	/**
	 * 查询用户已经提交的记录数
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public int selectRecordNum(BigInteger userId) {
		return sqlSession.selectOne("roomValidate.select_record_num", userId);
	}

}
