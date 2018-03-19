package com.cnfantasia.server.api.common.dao;

import java.util.List;

import com.cnfantasia.server.api.common.entity.CommMsg;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

public class CommMsgDao extends AbstractBaseDao{
	
	public int insertCommMsg(CommMsg commMsg) {
		return sqlSession.insert("commMsg.insertCommMsg", commMsg);
	}
	
	public int insertCommMsgBatch(List<CommMsg> commMsgList) {
		return sqlSession.insert("commMsg.insertCommMsgBatch", commMsgList);
	}
	

}
