package com.cnfantasia.server.api.dredgeAddress.dao;

import com.cnfantasia.server.api.dredgeAddress.entity.DredgeAddressEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DredgeAddressDao.
 * @Date: 2017-06-27 10:55
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeAddressDao extends AbstractBaseDao implements IDredgeAddressDao {

    @Override
    public List<DredgeAddressEntity> getDredgeAddressList(BigInteger userId) {
        Map<String, Object> para = new HashMap<>();
        para.put("userId", userId);
        return sqlSession.selectList("dredgeAddress.getDredgeAddressList", para);
    }

    @Override
    public Integer updDredgeAddress(DredgeAddressEntity entity) {
        return sqlSession.update("dredgeAddress.updDredgeAddress", entity);
    }
}
