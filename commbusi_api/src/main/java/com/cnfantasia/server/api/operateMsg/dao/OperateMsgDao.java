package com.cnfantasia.server.api.operateMsg.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

public class OperateMsgDao extends AbstractBaseDao{

	public List<MessageToBeSend> qryMessageToBeSendListForPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("operateMsg.qryMessageToBeSendList",paramMap);
	}

	public int qryMessageToBeSendListForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "operateMsg.qryMessageToBeSendList", paramMap);
	}

	public List<OperateConfigRange> qrySendRangeByMsgId(BigInteger id) {
		return sqlSession.selectList("operateMsg.qrySendRangeByMsgId",id);
	}

	public List<String> qryUserMobileByMsgToBeSendId(BigInteger id) {
		return sqlSession.selectList("operateMsg.qryUserMobileByMsgToBeSendId",id);
	}

	public List<CommUserDataEntity> qryUserIdByMsgToBeSendId(BigInteger id) {
		return sqlSession.selectList("operateMsg.qryUserIdByMsgToBeSendId",id);
	}
	/**
	 * 查询所有待发送的运营消息
	 * @return
	 */
	public List<MessageToBeSend> qryMessageToBeSendList4Task() {
		return sqlSession.selectList("operateMsg.qryMessageToBeSendList4Task");
	}

}
