package com.cnfantasia.server.ms.advertise.dao;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.ms.advertise.entity.AdvQryParam;
import com.cnfantasia.server.ms.advertise.entity.EbuyAdvertiseDto;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

public class advertiseForOmsDao extends AbstractBaseDao implements IAdvertiseForOmsDao {
    @Override
    public List<Map<String, Object>> getShelfProductForAdv(String qryStr, BigInteger supplyMerchantId, Integer appType, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("qryStr", qryStr);
        param.put("appType", appType);
        param.put("supplyMerchantId", supplyMerchantId);
        if (pageModel != null) {
            param.put("begin", pageModel.getBegin());
            param.put("length", pageModel.getLength());
        }
        return sqlSession.selectList("advertiseForOms.getShelfProductForAdv", param);
    }

    @Override
    public List<EbuyAdvertiseDto> getThemeProductAdvList(AdvQryParam param, PageModel pageModel) {
        Map<String, Object> paramMap = MapConverter.toMap(param);
        if (paramMap != null && pageModel != null) {
            paramMap.put("begin", pageModel.getBegin());
            paramMap.put("length", pageModel.getLength());
        }
        return sqlSession.selectList("advertiseForOms.getThemeProductAdvList", paramMap);
    }

    @Override
    public Long getThemeProductAdvListCount(AdvQryParam param) {
        return sqlSession.selectOne("advertiseForOms.getThemeProductAdvListCount", MapConverter.toMap(param));
    }

    @Override
    public List<EbuyAdvertiseDto> getAlertAdvList(AdvQryParam param, PageModel pageModel) {
        Map<String, Object> paramMap = MapConverter.toMap(param);
        if (paramMap != null && pageModel != null) {
            paramMap.put("begin", pageModel.getBegin());
            paramMap.put("length", pageModel.getLength());
        }
        return sqlSession.selectList("advertiseForOms.getAlertAdvList", paramMap);
    }

    @Override
    public Long getAlertAdvListCount(AdvQryParam param) {
        return sqlSession.selectOne("advertiseForOms.getAlertAdvListCount", MapConverter.toMap(param));
    }

    @Override
    public List<Map<String, Object>> getThemeProductAdvProductList(BigInteger advId) {
        return sqlSession.selectList("advertiseForOms.getThemeProductAdvProductList", advId);
    }

    @Override
    public List<Map<String, Object>> getAdvAreaByAdvId(BigInteger advId, Integer advType) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("advId", advId);
    	param.put("advType", advType);
        return sqlSession.selectList("advertiseForOms.getAdvAreaByAdvId", param);
    }

    @Override
    public AddressIdEntity getAddressIds(BigInteger countryId, BigInteger provinceId, BigInteger cityId, BigInteger blockId, BigInteger gbId) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (gbId != null) {
            param.put("gbId", gbId);
        } else if (blockId != null) {
            param.put("blockId", blockId);
        } else if (cityId != null) {
            param.put("cityId", cityId);
        } else if (provinceId != null) {
            param.put("provinceId", provinceId);
        } else if (countryId != null) {
            param.put("countryId", countryId);
        }
        return sqlSession.selectOne("advertiseForOms.getAddressIds", param);
    }

    @Override
    public Integer deletePromoteShelfProductsByAdvId(BigInteger advId) {
        return sqlSession.delete("advertiseForOms.deletePromoteShelfProductsByAdvId", advId);
    }

    @Override
    public Integer deleteSaEbSupplyByAdvId(BigInteger advId, Integer advType) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("advId", advId);
    	param.put("advType", advType);
        return sqlSession.delete("advertiseForOms.deleteSaEbSupplyByAdvId", param);
    }
    
    @Override
    public List<GroupBuildingSimpleEntity> getGbs(){
    	return sqlSession.selectList("advertiseForOms.select_gbs", null);
    }
}
