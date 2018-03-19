package com.cnfantasia.server.carYhsMsg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.carYhsMsg.entity.CarYhsMsgSendParam;
import com.cnfantasia.server.domainbase.carYhsMsg.dao.CarYhsMsgBaseDao;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 临停车缴费消息发送
 * 
 * @author liyulin
 * @version 1.0 2016年8月16日 下午3:28:11
 */
public class CarYhsMsgDao extends CarYhsMsgBaseDao implements ICarYhsMsgDao {
	
	/**
	 * 查询待发送的临停车缴费消息
	 * 
	 * @param gbIds 小区id
	 * @return
	 */
	public List<CarYhsMsg> selectCarYhsMsgForSending(List<Object> gbIds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbIds", gbIds);
		return sqlSession.selectList("carYhsMsg.selectCarYhsMsgForSending", paramMap);
	}

	/**
	 * 更新消息发送状态
	 * 
	 * @param carYhsMsgSendParam
	 * @return
	 */
	public boolean updateSendStatus(CarYhsMsgSendParam carYhsMsgSendParam) {
		return sqlSession.update("carYhsMsg.updateSendStatus", carYhsMsgSendParam) > 0;
	}

}
