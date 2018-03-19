package com.cnfantasia.server.api.carMsgTask.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.carMsgTask.entity.CarSendMsgEntity;
import com.cnfantasia.server.api.carMsgTask.entity.CarSendPushEntity;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

public class CarMsgDao extends AbstractBaseDao implements ICarMsgDao {
	
	@Override
	public List<CarSendMsgEntity> queryCarMsgInfo() {
		return sqlSession.selectList("carMsgTask.select_carMsgInfo");
	}

	@Override
	public List<CarSendPushEntity> queryCarPushInfo() {
		return sqlSession.selectList("carMsgTask.select_carPushInfo");
	}

	@Override
	public Integer qryTimeoutInMonthCount(BigInteger userId) {
		return sqlSession.selectOne("carMsgTask.qryTimeoutInMonthCount");
	}
}
