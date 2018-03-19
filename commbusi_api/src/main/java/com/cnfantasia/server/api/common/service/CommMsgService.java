package com.cnfantasia.server.api.common.service;

import java.util.List;

import com.cnfantasia.server.api.common.dao.CommMsgDao;
import com.cnfantasia.server.api.common.entity.CommMsg;


public class CommMsgService {
	
	private CommMsgDao commMsgDao;
	
	public int insertCommMsg(CommMsg commMsg) {
		return commMsgDao.insertCommMsg(commMsg);
	}
	
	public int insertCommMsgBatch(List<CommMsg> commMsgList) {
		return commMsgDao.insertCommMsgBatch(commMsgList);
	}

	public void setCommMsgDao(CommMsgDao commMsgDao) {
		this.commMsgDao = commMsgDao;
	}

}
