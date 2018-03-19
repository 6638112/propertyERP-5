package com.cnfantasia.server.ms.dredgeRefund.dao;

import com.cnfantasia.server.ms.dredgeRefund.entity.DredgeRefundEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IDredgeRefundDao.
 * @Date: 2017-10-12 14:18
 * @Auther: kangduo
 * @Description: ()
 */
public interface IDredgeRefundDao {

    List<DredgeRefundEntity> getDredgeRefundList(Map<String, Object> param);

    Integer getDredgeRefundCount(DredgeRefundEntity entity);

    DredgeRefundEntity getDredgeRefundDetail(BigInteger refundId);

}
