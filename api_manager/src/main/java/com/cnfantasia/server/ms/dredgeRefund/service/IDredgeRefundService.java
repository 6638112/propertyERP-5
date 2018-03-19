package com.cnfantasia.server.ms.dredgeRefund.service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.dredgeRefund.entity.DredgeRefundEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: IDredgeRefundService.
 * @Date: 2017-10-12 14:18
 * @Auther: kangduo
 * @Description: ()
 */
public interface IDredgeRefundService {

    Integer addDredgeRefund(DredgeRefundEntity entity);

    List<DredgeRefundEntity> getDredgeRefundList(DredgeRefundEntity entity, PageModel pageModel);

    Integer getDredgeRefundCount(DredgeRefundEntity entity);

    Integer audit(BigInteger refundId, String result, String auditReason);

    DredgeRefundEntity getDredgeRefundDetail(BigInteger refundId);
}
