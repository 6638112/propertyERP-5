package com.cnfantasia.server.ms.payRedEnvelope.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * 代扣卡转粮票一览表
 * 
 * @author liyulin
 * @version 1.0 2016年11月7日 下午2:04:39
 */
public interface IPayRedEnvelopeDao {
	
	/**
	 * 查询代扣卡转粮票记录
	 * 
	 * @param param
	 * @return
	 */
	List<PayRedEnvelope> selectPcToEnvelopeHistory(Map<String, Object> param);
	
	/**
	 * 查询代扣卡转粮票记录条数
	 * 
	 * @param param
	 * @return
	 */
	int selectPcToEnvelopeHistoryCount(Map<String, Object> param);
	
	/**
	 * 查询代扣卡转粮票总金额
	 * 
	 * @param param
	 * @return
	 */
	Long selectPcToEnvelopeTotalMoney(Map<String, Object> param);
}
