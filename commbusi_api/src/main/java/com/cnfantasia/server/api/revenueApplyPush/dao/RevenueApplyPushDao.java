package com.cnfantasia.server.api.revenueApplyPush.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyEntity;

public class RevenueApplyPushDao extends AbstractBaseDao {

	/**
	 * 查询待推送的提款单
	 * @param paramMap
	 * @return
	 */
	public List<RevenueApplyEntity> selectRevenueApplyList4Push(HashMap<String, Object> paramMap ) {
		return sqlSession.selectList("revenuePush.selectRevenueApplyList4Push", paramMap);
	}

	
	public int updateRevenueApplyAfterPushSuccess(Map<String, Object> paramMap) {
		return sqlSession.update("revenuePush.updateRevenueApplyAfterPushSuccess", paramMap);
	}

	/**
	 * 推送成功后插入推送记录
	 * @param paramMap 
	 * @return
	 */
	public int insertPushRecordAfterSuccess(Map<String, Object> paramMap) {
		return sqlSession.insert("revenuePush.insertPushRecordAfterSuccess", paramMap);
	}
	
	/**
	 * 推送失败后插入推送记录
	 * @param paramMap
	 * @return
	 */
	public int insertPushRecordAfterFail(Map<String, Object> paramMap) {
		return sqlSession.insert("revenuePush.insertPushRecordAfterFail", paramMap);
	}

	/**
	 * EAS付款成功后，更新提款单数据及状态
	 * @param paramMap
	 * @return
	 */
	public int updateRevenueApplyAfterEASPayed(Map<String, Object> paramMap) {
		int updCount1 = sqlSession.update("revenuePush.updateRevenueApplyAfterEASPayed_1", paramMap);
		int updCount2 = sqlSession.update("revenuePush.updateRevenueApplyAfterEASPayed_2", paramMap);
		int updCount3 = sqlSession.update("revenuePush.updateRevenueApplyAfterEASPayed_3", paramMap);
		int updCount4 = sqlSession.update("revenuePush.updateRevenueApplyTkStatus", paramMap);
		return updCount1 + updCount2 + updCount3 + updCount4;
	}
	public int mergeRevenueApply() {
		int insertCount = sqlSession.insert("revenuePush.mergeRevenueApply");
		int updCount = sqlSession.update("revenuePush.updateMergeRelation");
		return insertCount + updCount;
	}
}
