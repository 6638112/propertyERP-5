package com.cnfantasia.server.api.payment.serviceUntran;

import com.cnfantasia.server.api.payment.dao.IPayConfigDao;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;


/**
 * 支付配置服务类
 * @author shiyl
 *
 */
public class PayConfigService implements IPayConfigService{
	
	private IPayConfigDao payConfigDao;
	public void setPayConfigDao(IPayConfigDao payConfigDao) {
		this.payConfigDao = payConfigDao;
	}


	@Override
	public Double getPayConfigHbPercent(Integer orderType) {
		if(orderType==null){
			throw new BusinessRuntimeException("payConfigService.getPayConfigHbPercent.typeNull");
		}
		Double percent = payConfigDao.selectPayConfigHbPercent(orderType);
		if(percent==null||percent<0||percent>1){
			throw new BusinessRuntimeException("payConfigService.getPayConfigHbPercent.resultError");
		}
		return percent;
	}
	

}
