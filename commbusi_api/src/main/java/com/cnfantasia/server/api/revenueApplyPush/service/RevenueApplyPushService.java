package com.cnfantasia.server.api.revenueApplyPush.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.revenueApplyPush.bean.ResultDetail;
import com.cnfantasia.server.api.revenueApplyPush.bean.ResultInfo;
import com.cnfantasia.server.api.revenueApplyPush.dao.RevenueApplyPushDao;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyEntity;

public class RevenueApplyPushService {
    @Resource
    private RevenueApplyPushDao revenueApplyPushDao;
    
	public int mergeRevenueApply() {
		return revenueApplyPushDao.mergeRevenueApply();
	}
    /**
	 * 查询待推送的提款单
	 * @param paramMap
	 * @return
	 */
	public List<RevenueApplyEntity> selectRevenueApplyList4Push(HashMap<String, Object> paramMap ) {
		return revenueApplyPushDao.selectRevenueApplyList4Push(paramMap);
	}
	
	/**
	 * Push后增加推送记录
	 * @param resultInfo
	 * @param raList
	 */
	public void addPushRecordAfterPush(ResultInfo resultInfo, String srcBillNumber){
		if ("0000".equals(resultInfo.getCode())) {//成功
			for(ResultDetail resultDetail : resultInfo.getDetailList()){
				List<String> applyNoList = Arrays.asList(resultDetail.getSrcCode().split(";"));
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("applyNoList", applyNoList);
				paramMap.put("easCode", resultDetail.getEasCode());
				revenueApplyPushDao.updateRevenueApplyAfterPushSuccess(paramMap);
				
				paramMap.put("pushResponse", JSON.toJSONString(resultInfo));
				revenueApplyPushDao.insertPushRecordAfterSuccess(paramMap);
			}
		} else {//失败
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("srcBillNumber", srcBillNumber);
			paramMap.put("pushResponse", JSON.toJSONString(resultInfo));
			revenueApplyPushDao.insertPushRecordAfterFail(paramMap);
		}
	}

	public int updateRevenueApplyAfterEASPayed(String easCode, String srcCode, String payAmount) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("easCode", easCode);
		paramMap.put("srcCode", srcCode);
		paramMap.put("payAmount", payAmount);
		return revenueApplyPushDao.updateRevenueApplyAfterEASPayed(paramMap);
	}
	
}
