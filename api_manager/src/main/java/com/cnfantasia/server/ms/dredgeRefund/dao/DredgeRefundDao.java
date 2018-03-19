package com.cnfantasia.server.ms.dredgeRefund.dao;

import com.cnfantasia.server.ms.dredgeRefund.entity.DredgeRefundEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DredgeRefundDao.
 * @Date: 2017-10-12 14:18
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeRefundDao extends AbstractBaseDao implements IDredgeRefundDao {
    @Override
    public List<DredgeRefundEntity> getDredgeRefundList(Map<String, Object> param) {
        return sqlSession.selectList("dredgeRefundForOms.getDredgeRefundList", param);
    }

    @Override
    public Integer getDredgeRefundCount(DredgeRefundEntity entity) {
        return sqlSession.selectOne("dredgeRefundForOms.getDredgeRefundCount", entity);
    }

    @Override
    public DredgeRefundEntity getDredgeRefundDetail(BigInteger refundId) {
        Map<String, Object> param = new HashMap<>();
        param.put("refundId", refundId);
        return sqlSession.selectOne("dredgeRefundForOms.getDredgeRefundDetail", param);
    }
}
