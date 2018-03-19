package com.cnfantasia.server.ms.advertise.service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.communityAds.dao.ICommunityAdsBaseDao;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.domainbase.ebuyAdvertise.service.IEbuyAdvertiseBaseService;
import com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.entity.EbuyAdvertiseHasPromoteShelfProduct;
import com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.service.IEbuyAdvertiseHasPromoteShelfProductBaseService;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.dao.IOperationHomeSupplyTypeBaseDao;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;
import com.cnfantasia.server.domainbase.operationSaHasEbSupply.entity.OperationSaHasEbSupply;
import com.cnfantasia.server.domainbase.operationSaHasEbSupply.service.IOperationSaHasEbSupplyBaseService;
import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;
import com.cnfantasia.server.domainbase.operationServiceArea.service.IOperationServiceAreaBaseService;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;
import com.cnfantasia.server.ms.advertise.dao.IAdvertiseForOmsDao;
import com.cnfantasia.server.ms.advertise.entity.AdvQryParam;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;
import com.cnfantasia.server.ms.advertise.entity.EbuyAdvertiseDto;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class AdvertiseForOmsService implements IAdvertiseForOmsService{
    private IAdvertiseForOmsDao advertiseForOmsDao;
    private IEbuyAdvertiseBaseService ebuyAdvertiseBaseService;
    private IEbuyAdvertiseHasPromoteShelfProductBaseService ebuyAdvertiseHasPromoteShelfProductBaseService;
    private IOperationSaHasEbSupplyBaseService operationSaHasEbSupplyBaseService;
    private IOperationServiceAreaBaseService operationServiceAreaBaseService;
    private IUuidManager uuidManager;
    private IOperationHomeSupplyTypeBaseDao operationHomeSupplyTypeBaseDao;
    private ICommunityAdsBaseDao communityAdsBaseDao;
    private ISysParamManager sysParamManager;
    
    public void setAdvertiseForOmsDao(IAdvertiseForOmsDao advertiseForOmsDao) {
        this.advertiseForOmsDao = advertiseForOmsDao;
    }
    public void setEbuyAdvertiseBaseService(IEbuyAdvertiseBaseService ebuyAdvertiseBaseService) {
        this.ebuyAdvertiseBaseService = ebuyAdvertiseBaseService;
    }
    public void setOperationSaHasEbSupplyBaseService(IOperationSaHasEbSupplyBaseService operationSaHasEbSupplyBaseService) {
        this.operationSaHasEbSupplyBaseService = operationSaHasEbSupplyBaseService;
    }
    public void setOperationServiceAreaBaseService(IOperationServiceAreaBaseService operationServiceAreaBaseService) {
        this.operationServiceAreaBaseService = operationServiceAreaBaseService;
    }
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }
    public void setEbuyAdvertiseHasPromoteShelfProductBaseService(IEbuyAdvertiseHasPromoteShelfProductBaseService ebuyAdvertiseHasPromoteShelfProductBaseService) {
        this.ebuyAdvertiseHasPromoteShelfProductBaseService = ebuyAdvertiseHasPromoteShelfProductBaseService;
    }
    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    @Override
    public List<Map<String, Object>> getShelfProductForAdv(String qryStr, BigInteger supplyMerchantId, Integer appType, PageModel pageModel) {
        return advertiseForOmsDao.getShelfProductForAdv(qryStr, supplyMerchantId, appType, pageModel);
    }

    @Override
    public List<EbuyAdvertiseDto> getThemeProductAdvList(AdvQryParam param, PageModel pageModel) {
        return advertiseForOmsDao.getThemeProductAdvList(param, pageModel);
    }

    @Override
    public Long getThemeProductAdvListCount(AdvQryParam param) {
        return advertiseForOmsDao.getThemeProductAdvListCount(param);
    }

    @Override
    public List<EbuyAdvertiseDto> getAlertAdvList(AdvQryParam param, PageModel pageModel) {
        return advertiseForOmsDao.getAlertAdvList(param, pageModel);
    }

    @Override
    public Long getAlertAdvListCount(AdvQryParam param) {
        return advertiseForOmsDao.getAlertAdvListCount(param);
    }

    @Override
    public List<Map<String, Object>> getThemeProductAdvProductList(BigInteger advId) {
        return advertiseForOmsDao.getThemeProductAdvProductList(advId);
    }

    @Override
    public List<Map<String, Object>> getAdvAreaByAdvId(BigInteger advId, Integer advType) {
        return advertiseForOmsDao.getAdvAreaByAdvId(advId, advType);
    }
    
    public void setOperationHomeSupplyTypeBaseDao(IOperationHomeSupplyTypeBaseDao operationHomeSupplyTypeBaseDao) {
		this.operationHomeSupplyTypeBaseDao = operationHomeSupplyTypeBaseDao;
	}
    
	public void setCommunityAdsBaseDao(ICommunityAdsBaseDao communityAdsBaseDao) {
		this.communityAdsBaseDao = communityAdsBaseDao;
	}
	@Override
    @Transactional
    public void addThemeProductAdv(AdvertiseDto dto) {
        //处理广告表
        EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
        ebuyAdvertise.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise));
        ebuyAdvertise.setType(1);
        if (dto.getAdvType() != null && dto.getAdvType().compareTo(1) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)
                    + "ebuyNew/themeProductAd.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("EBUY_AD");
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(4) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)
                    + "ebuyNew/themeProductAd.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("MAIN_BUSINESS_AD");
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(3) == 0) {
            ebuyAdvertise.setLinkUrl("../product/themeProductAd.do?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("LA_EBUY");
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(5) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.LA_BASE_URL) +
                    "themeAdv/productList.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("EBUY_THEME");
            ebuyAdvertise.setVersion(512L);
        } else {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.LA_BASE_URL) +
                    "themeAdv/productList.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("DREDGE_THEME");
            ebuyAdvertise.setVersion(512L);
        }
        ebuyAdvertiseBaseService.insertEbuyAdvertise(ebuyAdvertise);
        //处理商品
        dealShelfProducts(dto, ebuyAdvertise.getId(), dto.getAdvType());
        //处理用户范围
        int areaType = dto.getAreaType() == null ? 1 : dto.getAreaType();
        List<BigInteger> cityIds = dto.getCityIds() == null ? null : new ArrayList<BigInteger>(dto.getCityIds());
        List<BigInteger> gbIds = dto.getGbIds() == null ? null :new ArrayList<BigInteger>(dto.getGbIds());
        dealServiceArea(areaType,ebuyAdvertise.getId(), cityIds,null, gbIds, AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
    }

    @Override
    @Transactional
    public void updThemeProductAdv(AdvertiseDto dto) {
        //处理广告
        EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
        if (dto.getAdvType() != null && dto.getAdvType().compareTo(1) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)
                    + "ebuyNew/themeProductAd.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("EBUY_AD");
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(4) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)
                    + "ebuyNew/themeProductAd.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("MAIN_BUSINESS_AD");
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(3) == 0){
            ebuyAdvertise.setCode("LA_EBUY");
            ebuyAdvertise.setLinkUrl("../product/themeProductAd.do?advId=" + ebuyAdvertise.getId());
        } else if (dto.getAdvType() != null && dto.getAdvType().compareTo(5) == 0) {
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.LA_BASE_URL) +
                    "themeAdv/productList.html?advId=" + ebuyAdvertise.getId());
            ebuyAdvertise.setCode("EBUY_THEME");
            ebuyAdvertise.setVersion(512L);
        } else {
            ebuyAdvertise.setVersion(512L);
            ebuyAdvertise.setCode("DREDGE_THEME");
            ebuyAdvertise.setLinkUrl(ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.LA_BASE_URL) +
                    "themeAdv/productList.html?advId=" + ebuyAdvertise.getId());
        }
        ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
        //处理商品
        advertiseForOmsDao.deletePromoteShelfProductsByAdvId(ebuyAdvertise.getId());
        dealShelfProducts(dto, ebuyAdvertise.getId(), dto.getAdvType());
        //处理用户范围
        advertiseForOmsDao.deleteSaEbSupplyByAdvId(ebuyAdvertise.getId(), AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
        int areaType = dto.getAreaType() == null ? 1 : dto.getAreaType();
        List<BigInteger> cityIds = dto.getCityIds() == null ? null : new ArrayList<BigInteger>(dto.getCityIds());
        List<BigInteger> gbIds = dto.getGbIds() == null ? null :new ArrayList<BigInteger>(dto.getGbIds());
        dealServiceArea(areaType,ebuyAdvertise.getId(), cityIds,null, gbIds, AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
    }

    private void dealShelfProducts(AdvertiseDto dto, BigInteger advId, Integer advType) {
        if (dto.getShelfIds() != null) {
            List<BigInteger> shelfIds = new ArrayList<BigInteger>(dto.getShelfIds());
            if (!DataUtil.isEmpty(shelfIds)) {
                List<EbuyAdvertiseHasPromoteShelfProduct> hasProducts = new ArrayList<EbuyAdvertiseHasPromoteShelfProduct>(shelfIds.size());
                List<BigInteger> hasProductIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise_has_promote_shelf_product, shelfIds.size());
                EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct = null;
                for (int i = 0; i < shelfIds.size(); i++) {
                    ebuyAdvertiseHasPromoteShelfProduct = new EbuyAdvertiseHasPromoteShelfProduct();
                    ebuyAdvertiseHasPromoteShelfProduct.setId(hasProductIds.get(i));
                    ebuyAdvertiseHasPromoteShelfProduct.settEbuyAdvertiseFId(advId);
                    if (advType == 6) {
                        ebuyAdvertiseHasPromoteShelfProduct.settDredgeProductFId(shelfIds.get(i));
                    } else {
                        ebuyAdvertiseHasPromoteShelfProduct.settEbuyProductShelfFId(shelfIds.get(i));
                    }
                    hasProducts.add(ebuyAdvertiseHasPromoteShelfProduct);
                }
                ebuyAdvertiseHasPromoteShelfProductBaseService.insertEbuyAdvertiseHasPromoteShelfProductBatch(hasProducts);
            }
        }
    }
    
    /**
     * <p>广告范围处理</p>
     * <p>
     * 首页弹窗、到家：ebSupplyType=4<br>
     * 街坊ebSupplyType=2<br>
     * 拦腰ebSupplyType=3
     * </p>
     * 
     * @param areaType
     * @param advId
     * @param cityIds
     * @param gbIds
     * @param ebSupplyType
     */
    @Override
    public void dealServiceArea(int areaType, BigInteger advId, List<BigInteger> cityIds,List<BigInteger> blockIds, List<BigInteger> gbIds, Integer ebSupplyType) {
        List<OperationSaHasEbSupply> ebSupplies = new ArrayList<OperationSaHasEbSupply>();
        OperationSaHasEbSupply ebSupply = null;
        OperationServiceArea serviceArea = null;
        if (areaType == 1) { //全国范围
            BigInteger zero = BigInteger.ZERO;
            serviceArea = new OperationServiceArea();
            serviceArea.settAddressCountryFId(new BigInteger("-1"));
            serviceArea.settAddressProvinceFId(zero);
            serviceArea.settAddressCityFId(zero);
            serviceArea.settAddressBlockFId(zero);
            serviceArea.settAddressBlockSelfFId(zero);
            serviceArea.settGroupBuildingFId(zero);
            serviceArea.setLevel(1);
            List<OperationServiceArea> areas = operationServiceAreaBaseService.getOperationServiceAreaByCondition(MapConverter.toMap(serviceArea));
            if (DataUtil.isEmpty(areas)) {
                serviceArea.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_service_area));
                serviceArea.settAddressBlockSelfFId(new BigInteger("0"));
                operationServiceAreaBaseService.insertOperationServiceArea(serviceArea);
            } else {
                serviceArea = areas.get(0);
            }
            ebSupply = new OperationSaHasEbSupply();
            ebSupply.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_sa_has_eb_supply));
            ebSupply.setEbSupplyId(advId);
            ebSupply.setRelation(1);
            ebSupply.setType(ebSupplyType);
            ebSupply.setSaId(serviceArea.getId());
            ebSupplies.add(ebSupply);
        } else if (areaType == 2 && cityIds != null) { //城市范围
            List<BigInteger> saSupplyIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_sa_has_eb_supply, cityIds.size());
            for (int i = 0; i < cityIds.size(); i++) {
                BigInteger cityId = cityIds.get(i);
                AddressIdEntity addressId = advertiseForOmsDao.getAddressIds(null, null, cityId, null, null);
                if (addressId != null) {
                    BigInteger zero = new BigInteger("0");
                    String addrUnique = addressId.getCountryId().toString() + "." + addressId.getProvinceId()
                            + "." + addressId.getCityId() + ".0.0.0";
                    serviceArea = new OperationServiceArea();
                    serviceArea.settAddressCountryFId(addressId.getCountryId());
                    serviceArea.settAddressProvinceFId(addressId.getProvinceId());
                    serviceArea.settAddressCityFId(addressId.getCityId());
                    serviceArea.settAddressBlockFId(zero);
                    serviceArea.settAddressBlockSelfFId(zero);
                    serviceArea.settGroupBuildingFId(zero);
                    serviceArea.setLevel(3);
                    List<OperationServiceArea> areas = operationServiceAreaBaseService.getOperationServiceAreaByCondition(MapConverter.toMap(serviceArea));
                    if (DataUtil.isEmpty(areas)) {
                        serviceArea.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_service_area));
                        serviceArea.setAddressUnique(addrUnique);
                        operationServiceAreaBaseService.insertOperationServiceArea(serviceArea);
                    } else {
                        serviceArea = areas.get(0);
                    }
                    ebSupply = new OperationSaHasEbSupply();
                    ebSupply.setId(saSupplyIds.get(i));
                    ebSupply.setEbSupplyId(advId);
                    ebSupply.setRelation(1);
                    ebSupply.setType(ebSupplyType);
                    ebSupply.setSaId(serviceArea.getId());
                    ebSupplies.add(ebSupply);
                }
            }
        } else if (areaType == 3 && gbIds != null) { //小区范围
            List<BigInteger> saSupplyIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_sa_has_eb_supply, gbIds.size());
            for (int i = 0; i < gbIds.size(); i++) {
                BigInteger gbId = gbIds.get(i);
                AddressIdEntity addressId = advertiseForOmsDao.getAddressIds(null, null, null, null, gbId);
                if (addressId != null) {
                    String addrUnique = addressId.getCountryId().toString() + "." + addressId.getProvinceId()
                            + "." + addressId.getCityId() + "." + addressId.getBlockId() + ".0." + addressId.getGbId();
                    serviceArea = new OperationServiceArea();
                    serviceArea.settAddressCountryFId(addressId.getCountryId());
                    serviceArea.settAddressProvinceFId(addressId.getProvinceId());
                    serviceArea.settAddressCityFId(addressId.getCityId());
                    serviceArea.settAddressBlockFId(addressId.getBlockId());
                    serviceArea.settAddressBlockSelfFId(new BigInteger("0"));
                    serviceArea.settGroupBuildingFId(addressId.getGbId());
                    serviceArea.setLevel(5);
                    List<OperationServiceArea> areas = operationServiceAreaBaseService.getOperationServiceAreaByCondition(MapConverter.toMap(serviceArea));
                    if (DataUtil.isEmpty(areas)) {
                        serviceArea.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_service_area));
                        serviceArea.setAddressUnique(addrUnique);
                        operationServiceAreaBaseService.insertOperationServiceArea(serviceArea);
                    } else {
                        serviceArea = areas.get(0);
                    }
                    ebSupply = new OperationSaHasEbSupply();
                    ebSupply.setId(saSupplyIds.get(i));
                    ebSupply.setEbSupplyId(advId);
                    ebSupply.setRelation(1);
                    ebSupply.setType(ebSupplyType);
                    ebSupply.setSaId(serviceArea.getId());
                    ebSupplies.add(ebSupply);
                }
            }
        }else if (areaType == 4 && blockIds != null) { //行政区（县）范围
    	List<BigInteger> saSupplyIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_sa_has_eb_supply, blockIds.size());
    	for (int i = 0; i < blockIds.size(); i++) {
    		BigInteger blockId = blockIds.get(i);
    		AddressIdEntity addressId = advertiseForOmsDao.getAddressIds(null, null, null, blockId, null);
    		if (addressId != null) {
    			String addrUnique = addressId.getCountryId().toString() + "." + addressId.getProvinceId()
    			+ "." + addressId.getCityId() + "." + addressId.getBlockId() + ".0." + addressId.getGbId();
    			serviceArea = new OperationServiceArea();
    			serviceArea.settAddressCountryFId(addressId.getCountryId());
    			serviceArea.settAddressProvinceFId(addressId.getProvinceId());
    			serviceArea.settAddressCityFId(addressId.getCityId());
    			serviceArea.settAddressBlockFId(addressId.getBlockId());
    			serviceArea.settAddressBlockSelfFId(BigInteger.ZERO);
    			serviceArea.settGroupBuildingFId(BigInteger.ZERO);
    			serviceArea.setLevel(4);
    			List<OperationServiceArea> areas = operationServiceAreaBaseService.getOperationServiceAreaByCondition(MapConverter.toMap(serviceArea));
    			if (DataUtil.isEmpty(areas)) {
    				serviceArea.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_service_area));
    				serviceArea.setAddressUnique(addrUnique);
    				operationServiceAreaBaseService.insertOperationServiceArea(serviceArea);
    			} else {
    				serviceArea = areas.get(0);
    			}
    			ebSupply = new OperationSaHasEbSupply();
    			ebSupply.setId(saSupplyIds.get(i));
    			ebSupply.setEbSupplyId(advId);
    			ebSupply.setRelation(1);
    			ebSupply.setType(ebSupplyType);
    			ebSupply.setSaId(serviceArea.getId());
    			ebSupplies.add(ebSupply);
    		}
    	}
    }
        if (ebSupplies.size() > 0) {
        	{
        		//先刪除之前的配置 added by wenfq 2017-06-23
        		OperationSaHasEbSupply ebSupplyQry = new OperationSaHasEbSupply();
        		ebSupplyQry.setType(ebSupplies.get(0).getType());
        		ebSupplyQry.setRelation(ebSupplies.get(0).getRelation());
        		ebSupplyQry.setEbSupplyId(ebSupplies.get(0).getEbSupplyId());
        		List<OperationSaHasEbSupply> list = operationSaHasEbSupplyBaseService.getOperationSaHasEbSupplyByCondition(MapConverter.toMap(ebSupplyQry));
        		List<BigInteger> idList = new ArrayList<BigInteger>();
        		for(int i = 0; i < list.size(); i++)
        			idList.add(list.get(i).getId());
        		if(idList.size()>0)
        			operationSaHasEbSupplyBaseService.deleteOperationSaHasEbSupplyLogicBatch(idList);
        	}
        	
            operationSaHasEbSupplyBaseService.insertOperationSaHasEbSupplyBatch(ebSupplies);
        }
    }

    /**
     * <p>发广告</p>
     * <p><strong>t_ebuy_advertise</strong> f_type：<br>
     * 1：“链接”；<br>
     * 2：“产品APP”；<br>
     * 3：“手机充值”；<br>
     * 4：“不跳”；<br>
     * 5：“到家服务”；<br>
     * 6：“一元Go”
     * </p>
     * 
     * @param dto
     */
    @Override
    @Transactional
    public void addAlertAdv(AdvertiseDto dto) {
    	BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
    	String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    	BigInteger id = null;
    	String advType = String.valueOf(dto.getAdvType());
    	Integer ebSupplyType = null;
    	if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType) || AdvConstant.AdvType.Dao_Jia.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
    		//处理广告表
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            id = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise);
            ebuyAdvertise.setId(id);
            if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(2) == 0){
        		dealAppPageAddress(dto.getEbuyAdvertise(), dto.getAlertAppPageType());
            } else if(AdvConstant.AdvType.Dao_Jia.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(5) == 0){
            	ebuyAdvertise.setLinkUrl(dto.getAlertAppPageType().toString());// “id”：select f_id from t_community_supply_type where f_parent_id=-1 and f_sys0_del_state=0
            }
            if(AdvConstant.AdvType.Dao_Jia.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(4) == 0){
            	ebuyAdvertise.setLinkUrl("#");
            }
            if (AdvConstant.AdvType.CAR_FEE.equals(advType)) {
                ebuyAdvertise.setCode("CAR_FEE_AD");
            }
            ebuyAdvertise.setSys0AddUser(userId);
            ebuyAdvertise.setSys0AddTime(now);
            ebuyAdvertise.setSys0DelState(0);
            ebuyAdvertiseBaseService.insertEbuyAdvertise(ebuyAdvertise);
    	} else if(AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Lan_Yao;
    		
    		OperationHomeSupplyType ohst = new OperationHomeSupplyType();
    		id = uuidManager.getNextUuidBigInteger(SEQConstants.t_operation_home_supply_type);
    		ohst.setId(id);
    		ohst.setName(dto.getEbuyAdvertise().getTittle());
    		if (dto.getEbuyAdvertise().getType().compareTo(2) == 0) {
    			dealAppPageAddressForLanYao(ohst, dto.getAlertAppPageType());
    		}
    		ohst.setLinkUrl(dto.getEbuyAdvertise().getLinkUrl());
    		ohst.setParentId(BigInteger.valueOf(701L));
    		ohst.setDataLevel(2);
    		if(AdvConstant.JumpType.App.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(3);
    		} else if(AdvConstant.JumpType.H5.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(2);
    		} else if(AdvConstant.JumpType.No_Jump.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(1);
    			ohst.setLinkUrl("#");
    			ohst.setCode(null);
    		}
    		ohst.setOrder(id.longValue());
    		ohst.setHomePlace(2);
    		ohst.setLastUpdTime(now);
    		ohst.setVersion(dto.getEbuyAdvertise().getVersion());
    		ohst.setMaxVersion(dto.getEbuyAdvertise().getMaxVersion());
    		ohst.setIsHot(2);
    		ohst.setIconName(dto.getEbuyAdvertise().getPicName());
    		ohst.setStartTime(dto.getEbuyAdvertise().getStartTime());
    		ohst.setEndTime(dto.getEbuyAdvertise().getEndTime());
    		ohst.setSys0AddUser(userId);
    		ohst.setSys0AddTime(now);
    		ohst.setSys0DelState(0);
    		
    		operationHomeSupplyTypeBaseDao.insertOperationHomeSupplyType(ohst);
    	} else if(AdvConstant.AdvType.Jie_Fang.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Jie_Fang;
    		
    		CommunityAds ca = new CommunityAds();
    		id = uuidManager.getNextUuidBigInteger(SEQConstants.t_community_ads);
    		ca.setId(id);
    		ca.setIcon(dto.getEbuyAdvertise().getPicName());
    		if(dto.getEbuyAdvertise().getType().compareTo(4) == 0){
    			ca.setDetailUrl(null);
    		} else {
    			ca.setDetailUrl(dto.getEbuyAdvertise().getLinkUrl());
    		}
    		ca.setOrder(id.longValue());
    		ca.setStartTime(dto.getEbuyAdvertise().getStartTime());
    		ca.setEndTime(dto.getEbuyAdvertise().getEndTime());
    		ca.setName(dto.getEbuyAdvertise().getTittle());
    		ca.setStatus(1);
    		ca.setMinVersion(dto.getEbuyAdvertise().getVersion());
    		ca.setMaxVersion(dto.getEbuyAdvertise().getMaxVersion());
    		
    		ca.setSys0AddUser(userId);
    		ca.setSys0AddTime(now);
    		ca.setSys0DelState(0);
    		communityAdsBaseDao.insertCommunityAds(ca);
        } else if (AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            //处理广告表
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            id = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise);
            ebuyAdvertise.setId(id);
            String title = dto.getFirstLine() + ";" + dto.getSecondLine() + ";" + dto.getThirdLine()+";"+ebuyAdvertise.getTittle();
            ebuyAdvertise.setTittle(title);
            BigInteger dredgeProductId = dto.getDredgeProductId();
            String laBaseUrl = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL);
            ebuyAdvertise.setCode("DREDGE_ACTIVITY_AD");
            if(dto.getEbuyAdvertise().getType().compareTo(5) == 0){
            	ebuyAdvertise.setLinkUrl(laBaseUrl + "dredge/productDetail.html?productId=" + dredgeProductId);
            }
            ebuyAdvertiseBaseService.insertEbuyAdvertise(ebuyAdvertise);
        } else if (AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equalsIgnoreCase(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            //处理广告表
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            id = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise);
            ebuyAdvertise.setId(id);
            ebuyAdvertise.setCode("WX_STORE_ORDER_PUSH_AD");
            ebuyAdvertiseBaseService.insertEbuyAdvertise(ebuyAdvertise);
        } else if (AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            //处理广告表
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            id = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_advertise);
            ebuyAdvertise.setId(id);
            ebuyAdvertise.setCode("EXPERIENCE_STORE_AD");
            if(dto.getEbuyAdvertise().getType().compareTo(5) == 0){
                ebuyAdvertise.setLinkUrl(dto.getStoreShelfId().toString());
            }
            ebuyAdvertiseBaseService.insertEbuyAdvertise(ebuyAdvertise);
        }

        //处理用户范围
        int areaType = dto.getAreaType() == null ? 1 : dto.getAreaType();
        List<BigInteger> cityIds = dto.getCityIds() == null ? null : new ArrayList<BigInteger>(dto.getCityIds());
        List<BigInteger> gbIds = dto.getGbIds() == null ? null :new ArrayList<BigInteger>(dto.getGbIds());
        dealServiceArea(areaType, id, cityIds, dto.getBlockId(), gbIds, ebSupplyType);
    }

    @Override
    @Transactional
    public void updAlertAdv(AdvertiseDto dto) {
    	BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
    	String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    	BigInteger id = dto.getEbuyAdvertise().getId();
    	String advType = String.valueOf(dto.getAdvType());
    	Integer ebSupplyType = null;
    	if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType) || AdvConstant.AdvType.Dao_Jia.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
    		//处理广告
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(2) == 0){
        		dealAppPageAddress(dto.getEbuyAdvertise(), dto.getAlertAppPageType());
            } else if(AdvConstant.AdvType.Dao_Jia.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(5) == 0){
            	ebuyAdvertise.setLinkUrl(dto.getAlertAppPageType().toString());// “id”：select f_id from t_community_supply_type where f_parent_id=-1 and f_sys0_del_state=0
            }
            if(AdvConstant.AdvType.Dao_Jia.equals(advType) && dto.getEbuyAdvertise().getType().compareTo(4) == 0){
            	ebuyAdvertise.setLinkUrl("#");
            }
            ebuyAdvertise.setSys0UpdUser(userId);
            ebuyAdvertise.setSys0UpdTime(now);
            ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
    	} else if(AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Lan_Yao;
    		
    		OperationHomeSupplyType ohst = new OperationHomeSupplyType();
    		ohst.setId(id);
    		ohst.setName(dto.getEbuyAdvertise().getTittle());
    		if (dto.getEbuyAdvertise().getType().compareTo(2) == 0) {
    			dealAppPageAddressForLanYao(ohst, dto.getAlertAppPageType());
    		}
    		ohst.setParentId(BigInteger.valueOf(701L));
    		ohst.setDataLevel(2);
    		ohst.setLinkUrl(dto.getEbuyAdvertise().getLinkUrl());
    		if(AdvConstant.JumpType.App.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(3);
    		} else if(AdvConstant.JumpType.H5.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(2);
    		} else if(AdvConstant.JumpType.No_Jump.equals(dto.getEbuyAdvertise().getType())){
    			ohst.setDataType(1);
    			ohst.setLinkUrl("#");
    			ohst.setCode(" ");
    		}
    		ohst.setVersion(dto.getEbuyAdvertise().getVersion());
    		ohst.setMaxVersion(dto.getEbuyAdvertise().getMaxVersion());
    		ohst.setIconName(dto.getEbuyAdvertise().getPicName());
    		ohst.setStartTime(dto.getEbuyAdvertise().getStartTime());
    		ohst.setEndTime(dto.getEbuyAdvertise().getEndTime());
    		ohst.setSys0UpdUser(userId);
    		ohst.setSys0UpdTime(now);
    		
    		operationHomeSupplyTypeBaseDao.updateOperationHomeSupplyType(ohst);
    	} else if(AdvConstant.AdvType.Jie_Fang.equals(advType)){
    		ebSupplyType = AdvConstant.EbSupplyType.Jie_Fang;
    		
    		CommunityAds ca = new CommunityAds();
    		ca.setId(id);
    		ca.setIcon(dto.getEbuyAdvertise().getPicName());
    		if(dto.getEbuyAdvertise().getType().compareTo(4) == 0){
    			ca.setDetailUrl(" ");
    		} else {
    			ca.setDetailUrl(dto.getEbuyAdvertise().getLinkUrl());
    		}
    		ca.setStartTime(dto.getEbuyAdvertise().getStartTime());
    		ca.setEndTime(dto.getEbuyAdvertise().getEndTime());
    		ca.setName(dto.getEbuyAdvertise().getTittle());
    		ca.setStatus(1);
    		ca.setMinVersion(dto.getEbuyAdvertise().getVersion());
    		ca.setMaxVersion(dto.getEbuyAdvertise().getMaxVersion());
    		
    		ca.setSys0UpdUser(userId);
    		ca.setSys0UpdTime(now);
    		communityAdsBaseDao.updateCommunityAds(ca);
    	} else if (AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            //处理广告表
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            String title = dto.getFirstLine() + ";" + dto.getSecondLine() + ";" + dto.getThirdLine()+";"+ebuyAdvertise.getTittle();
            ebuyAdvertise.setTittle(title);
            ebuyAdvertise.setType(null);
            ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
        } else if (AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equals(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
        } else if (AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)) {
            ebSupplyType = AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
            EbuyAdvertise ebuyAdvertise = dto.getEbuyAdvertise();
            ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
        }

        //处理用户范围
        advertiseForOmsDao.deleteSaEbSupplyByAdvId(id, ebSupplyType);
        int areaType = dto.getAreaType() == null ? 1 : dto.getAreaType();
        List<BigInteger> cityIds = dto.getCityIds() == null ? null : new ArrayList<BigInteger>(dto.getCityIds());
        List<BigInteger> gbIds = dto.getGbIds() == null ? null :new ArrayList<BigInteger>(dto.getGbIds());
        dealServiceArea(areaType, id, cityIds, dto.getBlockId(), gbIds, ebSupplyType);
    }

    /**
     * 处理app内跳转的页面参数
     * @param ebuyAdvertise
     * @param appPageType
     */
    private void dealAppPageAddress(EbuyAdvertise ebuyAdvertise, int appPageType) {
        switch (appPageType) {
            case 1 ://物业报修
                ebuyAdvertise.setIosAddr("wuyebaoxiu");
                ebuyAdvertise.setAndroidAddr("wuyebaoxiu");
                break;
            case 2 ://物业公告
                ebuyAdvertise.setIosAddr("wuyegonggao");
                ebuyAdvertise.setAndroidAddr("wuyegonggao");
                break;
            case 3 ://智能停车
                ebuyAdvertise.setIosAddr("zhinengtingche");
                ebuyAdvertise.setAndroidAddr("zhinengtingche");
                break;
            case 4 ://上门服务
                ebuyAdvertise.setIosAddr("shangmenfuwu");
                ebuyAdvertise.setAndroidAddr("shangmenfuwu");
                break;
            case 5 ://店铺列表
                ebuyAdvertise.setIosAddr("dianpuliebiao");
                ebuyAdvertise.setAndroidAddr("dianpuliebiao");
                break;
            case 6 ://超市首页
                ebuyAdvertise.setIosAddr("chaoshishouye");
                ebuyAdvertise.setAndroidAddr("chaoshishouye");
                break;
            case 7 ://物业缴费
                ebuyAdvertise.setIosAddr("wuyejiaofei");
                ebuyAdvertise.setAndroidAddr("wuyejiaofei");
                break;
        }
    }

    private void dealAppPageAddressForLanYao(OperationHomeSupplyType ohst, int appPageType) {
        switch (appPageType) {
            case 1://维修
                ohst.setCode("superRepair");
                break;
            case 2://物业缴费
                ohst.setCode("wuyejiaofei");
                break;
        }
    }
    
    @Override
    public List<GroupBuildingSimpleEntity> getGbs(){
    	return advertiseForOmsDao.getGbs();
    }
    
    public static void dealRealTitle(EbuyAdvertise ebuyAdvertise) {
		if(StringUtils.isNotBlank(ebuyAdvertise.getTittle())) {
			String[] titles = ebuyAdvertise.getTittle().split(";");
			if(titles!=null && titles.length==4) {
				ebuyAdvertise.setTittle(titles[3]);
			}
		}
	}
}
