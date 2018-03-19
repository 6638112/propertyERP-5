package com.cnfantasia.server.api.dredgeAddress.service;

import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.dredgeAddress.dao.IDredgeAddressDao;
import com.cnfantasia.server.api.dredgeAddress.entity.DredgeAddressEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeAddress.entity.DredgeAddress;
import com.cnfantasia.server.domainbase.dredgeAddress.service.IDredgeAddressBaseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DredgeAddressService.
 * @Date: 2017-06-27 10:55
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeAddressService implements IDredgeAddressService {

    private IDredgeAddressDao dredgeAddressDao;
    private IUuidManager uuidManager;
    private IDredgeAddressBaseService dredgeAddressBaseService;
    private DredgeService dredgeService;
    private IAddressOperationService addressOperationService;

    public void setDredgeAddressDao(IDredgeAddressDao dredgeAddressDao) {
        this.dredgeAddressDao = dredgeAddressDao;
    }
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }
    public void setDredgeAddressBaseService(IDredgeAddressBaseService dredgeAddressBaseService) {
        this.dredgeAddressBaseService = dredgeAddressBaseService;
    }
    public void setDredgeService(DredgeService dredgeService) {
        this.dredgeService = dredgeService;
    }
    public void setAddressOperationService(IAddressOperationService addressOperationService) {
        this.addressOperationService = addressOperationService;
    }

    @Override
    public List<DredgeAddressEntity> getDredgeAddressList(BigInteger userId, BigInteger dredgeProductId) {
        List<DredgeAddressEntity> dredgeAddressList = dredgeAddressDao.getDredgeAddressList(userId);
        if (!DataUtil.isEmpty(dredgeAddressList)) {
            List<BigInteger> codeIdList;
            boolean inDredgeProductArea = false;
            for (DredgeAddressEntity entity : dredgeAddressList) {
                codeIdList = addressOperationService.getCodeIdList(null, null, null, entity.getBlockId(), entity.getGbId());
                inDredgeProductArea = dredgeService.isInDredgeProductArea(codeIdList, dredgeProductId);
                entity.setValid(inDredgeProductArea);
            }
        }
        return dredgeAddressList;
    }

    @Override
    public BigInteger addDredgeAddress(DredgeAddressEntity entity) {
        DredgeAddress address = new DredgeAddress();
        address.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_address));
        address.settUserFId(UserContext.getOperIdBigInteger());
        address.setAddress(entity.getAddress());
        address.setBlockId(entity.getBlockId());
        address.setGbId(entity.getGbId());
        dredgeAddressBaseService.insertDredgeAddress(address);
        return address.getId();
    }

    @Override
    public Integer updDredgeAddress(DredgeAddressEntity entity) {
        return dredgeAddressDao.updDredgeAddress(entity);
    }

}
