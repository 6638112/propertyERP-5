package com.cnfantasia.server.api.dredgeAddress.service;

import com.cnfantasia.server.api.dredgeAddress.entity.DredgeAddressEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: IDredgeAddressService.
 * @Date: 2017-06-27 10:54
 * @Auther: kangduo
 * @Description: ()
 */
public interface IDredgeAddressService {
    List<DredgeAddressEntity> getDredgeAddressList(BigInteger userId, BigInteger dredgeProductId);

    BigInteger addDredgeAddress(DredgeAddressEntity entity);

    Integer updDredgeAddress(DredgeAddressEntity entity);
}
