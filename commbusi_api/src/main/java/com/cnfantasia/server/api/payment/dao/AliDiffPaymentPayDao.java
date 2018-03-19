package com.cnfantasia.server.api.payment.dao;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;

import java.math.BigInteger;

/**
 * @className: AliDiffPaymentPayDao
 * @date: 2017-12-07 15:35
 * @author: yanghua
 * @description:(支付宝：多少户支付)
 */
public class AliDiffPaymentPayDao extends AbstractBaseDao {

    /**
     * 获取默认的支付宝信息（自己平台）
     * @param orderId
     * @param userId
     * @param gbId
     * @return
     */
    public PropertyCompanyThirdPayCfg getDefaultAliPayInfo(BigInteger gbId) {
        return sqlSession.selectOne("payConfig.getDefaultAliPayInfo", gbId);
    }

    public BigInteger getCarnumGbId(BigInteger orderId) {
        return sqlSession.selectOne("payConfig.getCarnumGbId", orderId);
    }
}
