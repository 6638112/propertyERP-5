package com.cnfantasia.server.api.dredgeAddress.dao;

import com.cnfantasia.server.api.dredgeAddress.entity.DredgeAddressEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: IDredgeAddressDao.
 * @Date: 2017-06-27 10:55
 * @Auther: kangduo
 * @Description: ()
 */
public interface IDredgeAddressDao {
    List<DredgeAddressEntity> getDredgeAddressList(BigInteger userId);

    Integer updDredgeAddress(DredgeAddressEntity entity);
}
