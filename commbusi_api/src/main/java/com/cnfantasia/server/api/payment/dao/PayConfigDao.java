package com.cnfantasia.server.api.payment.dao;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;


/**
 * 支付配置
 * @author shiyl
 *
 */
public class PayConfigDao extends AbstractBaseDao implements IPayConfigDao{

	@Override
	public Double selectPayConfigHbPercent(Integer orderType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderType", orderType);
		return sqlSession.selectOne("payConfig.select_PayConfig_HbPercent",tmpMap);
	}

}
