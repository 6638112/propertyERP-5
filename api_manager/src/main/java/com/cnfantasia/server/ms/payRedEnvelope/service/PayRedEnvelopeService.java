package com.cnfantasia.server.ms.payRedEnvelope.service;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.ms.payRedEnvelope.dao.IPayRedEnvelopeDao;

/**
 * 代扣卡转粮票一览表
 * 
 * @author liyulin
 * @version 1.0 2016年11月7日 下午2:04:39
 */
public class PayRedEnvelopeService implements IPayRedEnvelopeService {
	private IPayRedEnvelopeDao payRedEnvelopeDao;
	
	public void setPayRedEnvelopeDao(IPayRedEnvelopeDao payRedEnvelopeDao) {
		this.payRedEnvelopeDao = payRedEnvelopeDao;
	}

	/**
	 * 查询代扣卡转粮票记录
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public List<PayRedEnvelope> selectPcToEnvelopeHistory(Map<String, Object> param) {
		return payRedEnvelopeDao.selectPcToEnvelopeHistory(param);
	}

	/**
	 * 查询代扣卡转粮票记录条数
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public int selectPcToEnvelopeHistoryCount(Map<String, Object> param) {
		return payRedEnvelopeDao.selectPcToEnvelopeHistoryCount(param);
	}

	/**
	 * 查询代扣卡转粮票总金额
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public Long selectPcToEnvelopeTotalMoney(Map<String, Object> param) {
		return payRedEnvelopeDao.selectPcToEnvelopeTotalMoney(param);
	}

}
