package com.cnfantasia.server.api.ebuy.web;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.entity.EbuyFightGroups;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLst;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLstSales;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.flashDealActivity.entity.FlashDealActivityDetailNewEntity;
import com.cnfantasia.server.api.flashDealActivity.service.IFlashDealActivityService;
import com.cnfantasia.server.api.limitBuy.contant.LimitBuyDict;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;

/**
 * @ClassName: EbuyControllerVersion2
 * @Date: 2016-12-22 16:32
 * @Auther: kangduo
 * @Description: (从348开始用的ebuyController)
 */
@Controller
@RequestMapping("/ebuyV2")
public class EbuyControllerVersion2 extends BaseController {

    @Resource
    private IEbuyNewService ebuyNewService;

    @Resource
    private ICommonRoomService commonRoomService;

    @Resource
    private IAddressOperationService addressOperationService;

    @Resource
    private IEbuyAdvertiseService ebuyAdvertiseService;

    @Resource
    private IFlashDealActivityService flashDealActivityService;

    @Resource
    private ILimitBuyService limitBuyService;

    @Resource
    private ISysParamManager sysParamManager;

    private static Map<BigInteger, BigInteger> weakMapCache = new WeakHashMap<BigInteger, BigInteger>(1000);
    private final Object lock = new Object();
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/checkOrderPrdtBeforePay.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse checkOrderPrdtBeforePay(BigInteger orderId) {
    	JsonResponse jsonResponse = new JsonResponse();
    	
		String checkResult = ebuyNewService.checkOrderPrdtBeforePay(orderId);
		jsonResponse.setStatus("通过校验".equals(checkResult) ? CommConstants.ResponseStatus.SUCCESS : CommConstants.ResponseStatus.VALIDATE_ERR);
		jsonResponse.setMessage("通过校验".equals(checkResult) ? "通过校验" : checkResult);
		
		return jsonResponse;
    }
    
    @RequestMapping(value = "/qryEbuyAdsList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryEbuyAdsList() {
        JsonResponse json = new JsonResponse();

        //根据小区进行查的
        BigInteger gbId = getUserGroupId(false);
        Integer version = HeaderManager.getVersionNum();
        if(gbId == null) {
            gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
        }
        gbId = HeaderManager.getGbId() == null ? gbId : HeaderManager.getGbId();
        List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);

        //banner
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<String> codeList = new ArrayList<String>();
        codeList.add("EBUY_AD");
        codeList.add("ACTIVITY_ENTRANCE");
        codeList.add("LIMITBUY_ALERT");
        paramMap.put("codeList", codeList);
        paramMap.put("version", version);
        paramMap.put("codeIdList", codeIdList);
        List<EbuyAdvertise> adList = ebuyAdvertiseService.getEbuyAdvertiseList(paramMap);
        List<EbuyAdvertise> adsShow = null;
        String operId = UserContext.getOperId();
        adsShow = new ArrayList<EbuyAdvertise>();
        if (adList != null && !adList.isEmpty()) {
            List<BigInteger> adIds = ebuyAdvertiseService.queryPromoteProductAdIds();
            PageModel pageModel = new PageModel(0, 1);
            for (EbuyAdvertise ad : adList) {
                ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, ad.getPicName(), ad.getUpdTime()));
                if (ad.getType() == 1 && version != null) {
                    if (ad.getLinkUrl().contains("?")) {
                        ad.setLinkUrl(ad.getLinkUrl() + "&version=" + version);
                    } else {
                        ad.setLinkUrl(ad.getLinkUrl() + "?version=" + version);
                    }
                }
                if (ad.getLinkUrl() != null && ad.getLinkUrl().contains("ebuyNew/themeProductAd.html")) {
                    // app专题banner
                    if (adIds != null) {
                        for (BigInteger adId : adIds) {
                            if ((ad.getId() != null) && (adId.toString().equals(ad.getId().toString()))) {
                                // 处理【推广商品广告列表】处理仅仅只有一个商品时
                                dealOnlyOneProduct(ad);
                                adsShow.add(ad);
                                break;
                            }
                        }
                    }
                } else if (ad.getLinkUrl() != null && ad.getLinkUrl().contains("flashDealActivity/activityList.html")) {
                    //幸运购没有显示预告，picName是两个图，linkUrl是短链接
                    List<FlashDealActivityDetailNewEntity> activityList = flashDealActivityService.getActivityList(operId == null ? null : new BigInteger(operId), null, gbId, pageModel);
                    String[] split = ad.getPicName().split(";");
                    ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[0], null));
                    if (DataUtil.isEmpty(activityList)) {
                        ad.setLinkUrl(ad.getLinkUrl() + (ad.getLinkUrl().contains("?") ? "&" : "?")
                                + "pic=" + CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[1], null));
                    }
                    adsShow.add(ad);
                } else if (ad.getLinkUrl() != null && ad.getLinkUrl().contains("ebuyNew/fightGroupsList.html")) {
                    //拼购没有显示预告，picName是两个图，linkUrl是短链接
                    List<EbuyFightGroups> fightProductsBegin = ebuyNewService.getFightProducts("1", gbId, pageModel);
                    String[] split = ad.getPicName().split(";");
                    ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[0], null));
                    if (DataUtil.isEmpty(fightProductsBegin)) {
                        ad.setLinkUrl(ad.getLinkUrl() + (ad.getLinkUrl().contains("?") ? "&" : "?")
                                + "pic=" + CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, split[1], null));
                    }
                    adsShow.add(ad);
                } else {
                    adsShow.add(ad);
                }
            }
        } else {
            codeList.clear();
            codeList.add("EBUY_AD_DEFAULT");
            adList = ebuyAdvertiseService.getEbuyAdvertiseList(paramMap);
            for (EbuyAdvertise ad : adList) {
                ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, ad.getPicName(), ad.getUpdTime()));
            }
            adsShow.addAll(adList);
        }

        if (operId == null && gbId == null) {
            json.putData("banner", adsShow);
            json.putData("activity", null);
            json.putData("homeStore", null);
            return json;
        }

        //限时购的店
        List<EbuyStore> limitBuyStoreQry = limitBuyService.getLimitBuyListStore(gbId, 1, LimitBuyDict.PositionType.IN_PUB_STORE, null, null);
        List<EbuyStore> limitBuyStore = new ArrayList<EbuyStore>(limitBuyStoreQry.size());
        for (EbuyStore ebuyStore : limitBuyStoreQry) {
            //移除没限时购商品的店
            List<LimitBuyInfo> limitBuyList = ebuyStore.getLimitBuyList();
            if (limitBuyList != null && limitBuyList.size() > 0) {
                if (limitBuyList.size() > 15) {
                    limitBuyList = limitBuyList.subList(0, 15);
                    ebuyStore.setLimitBuyList(limitBuyList);
                }
                for (LimitBuyInfo limitBuyInfo : limitBuyList) {
                    String desc = ebuyNewService.getEbuyThemeDescByShelfId(limitBuyInfo.getShelfId(), 1);
                    limitBuyInfo.setInAnniversary(desc != null);
                }
                limitBuyStore.add(ebuyStore);
            }
        }
        Map<String, Object> activityMap = new HashMap<String, Object>();
        activityMap.put("title", "限时促销");
        activityMap.put("list", limitBuyStore);

        //homeStore，要排除限时购的店
        Map<String, Object> homeStoreMap = null;
        paramMap.clear();
        if (gbId != null && gbId.compareTo(new BigInteger("-1")) != 0) {
            paramMap.put("groupBuildId", gbId);
            paramMap.put("orderType", "1");
            PageModel pageModel = new PageModel(0, 100);
            List<EbuyStore> storeList = ebuyNewService.getEbuyStoreList(paramMap, pageModel);
            if (!DataUtil.isEmpty(storeList)) {
                //移除促销店
                List<EbuyStore> resStoreList = new ArrayList<>(storeList.size());
                int maxShow = 1;
                for (EbuyStore ebuyStore : storeList) {
                    if (maxShow == 0) {
                        break;
                    }
                    boolean hasThis = false;
                    for (EbuyStore limitStore : limitBuyStore) {
                        if (limitStore.getId().compareTo(ebuyStore.getId()) == 0) {
                            hasThis = true;
                            break;
                        }
                    }
                    if (!hasThis) {
                        maxShow--;
                        resStoreList.add(ebuyStore);
                    }
                }
                for (EbuyStore ebuyStore : resStoreList) {
                    List<EbuyProdForLst> ebuyProdForLstList = ebuyStore.getEbuyProdForLstList();
                    if (!DataUtil.isEmpty(ebuyProdForLstList)) {
                        for (EbuyProdForLst ebuyProdForLst : ebuyProdForLstList) {
                            String desc = ebuyNewService.getEbuyThemeDescByShelfId(BigInteger.valueOf(ebuyProdForLst.getId()), 1);
                            ebuyProdForLst.setInAnniversary(desc != null);
                        }
                    }
                }
                homeStoreMap = new HashMap<String, Object>();
                homeStoreMap.put("title", "社区好店");
                Collections.sort(resStoreList);
                homeStoreMap.put("list", resStoreList);
            }
        }

        json.putData("banner", adsShow);
        json.putData("activity", activityMap);
        json.putData("homeStore", homeStoreMap);
        return json;
    }
    
    /**
     * 处理【推广商品广告列表】处理仅仅只有一个商品时
     * @param ad
     * @return
     */
    public EbuyAdvertise dealOnlyOneProduct(EbuyAdvertise ad){
    	if(ad!=null && "EBUY_AD".equals(ad.getCode()) && ad.getType()!=null && ad.getType()==1){
			List<EbuyProdForLstSales> products = ebuyNewService.selectAdvertiseThemeProduct(BigInteger.valueOf(ad.getId()));
			if(products!=null && products.size()==1){
				ad.setType(2);
				ad.setLinkUrl(String.valueOf(products.get(0).getId()));
			}
    	}
    	return ad;
    }

    /**
     * 拿自营的分类
     * @param request
     * @return
     */
    @RequestMapping(value = "/qryProductTypes.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryProductTypes(HttpServletRequest request){
        JsonResponse jsonResponse = new JsonResponse();
        Integer appType = ParamUtils.getInteger(request, "appType", 1); //1是APP， 2为文旅；默认是APP调用
        Integer opType = ParamUtils.getInteger(request, "opType", 0); //活动类型，如新用户专享为1，默认没有活动
        BigInteger groupBuildId = getUserGroupId(false);
        groupBuildId = HeaderManager.getGbId() == null ? groupBuildId : HeaderManager.getGbId();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("appType", appType);
        paramMap.put("groupBuildId", groupBuildId);
        paramMap.put("version", HeaderManager.getVersionNum());
        paramMap.put("opType", opType);

        if(groupBuildId == null || groupBuildId.longValue() == -1) {
            paramMap.put("supplyType", 1);
        } else {
            paramMap.put("supplyTypes", new Integer[]{1, 4});
        }

        List<EbuyProdType> prodTypeList = ebuyNewService.getStoreProdTypes(paramMap);
        List<LimitBuyInfo> limitBuyInfoList = limitBuyService.
                getLimitBuyListByGbId(groupBuildId, 1, LimitBuyDict.PositionType.IN_PUB_STORE, new PageModel(0, 1));
        //1分钟缓存会把限时促销也缓存了
        if (!DataUtil.isEmpty(limitBuyInfoList) && prodTypeList.get(0).getId() != -1) {
            EbuyProdType type = new EbuyProdType();
            type.setId(EbuyDict.EbuyProdTypeSpec.LIMIT_BUY);
            type.setTypeName("限时促销");
            prodTypeList.add(0, type);
        }
        jsonResponse.putData("list", prodTypeList);
        return jsonResponse;
    }

    /**
     * 查体验店的商品列表及搜索
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/qryProdList4ExperienceStore.json")
    @ResponseBody
    public JsonResponse qryProdList4ExperienceStore(HttpServletRequest request) throws ParseException {
        Integer appType = ParamUtils.getInteger(request, "appType", 1); //1是APP， 2为文旅；默认是APP调用
        Long storeId = ParamUtils.getLong(request, "storeId", null);
        Long productTypeId = ParamUtils.getLong(request, "productTypeId", null);
        Integer supplyType = ParamUtils.getInteger(request, "supplyType", null);
        String searchKey = ParamUtils.getString(request, "searchKey", null);
        String orderBy = ParamUtils.getString(request, "orderBy", null);
        BigInteger groupBuildId = getUserGroupId(false);
        Integer opType = ParamUtils.getInteger(request, "opType", 0); //活动类型，如新用户专享为1，默认没有活动

        PageModel pageModel = ControllerUtils.getPageModel(request);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("appType", appType);
        paramMap.put("storeId", storeId);
        paramMap.put("productTypeId", productTypeId);
//        paramMap.put("groupBuildId", groupBuildId);
        paramMap.put("opType", opType);

        if(supplyType == null) {//supplyType=null时是首页的搜索
            if(groupBuildId == null || groupBuildId.longValue() == -1) {
                paramMap.put("supplyType", 1); //未登录时，如果供应商类型为空，搜索自营的。登录则搜索所有的
            }
        } else if(supplyType == 1) {//supplyType不为空时,是非首页搜索
            if(groupBuildId == null || groupBuildId.longValue() == -1) {
                paramMap.put("supplyType", 1);
            } else {
                paramMap.put("supplyTypes", new Integer[]{1, 4});
            }
        } else {
            paramMap.put("supplyType", supplyType);
        }

        JsonResponse json = new JsonResponse();
        paramMap.put("searchKey", searchKey);
        paramMap.put("orderBy", orderBy);
        List<EbuyProdForLstSales> prodList = ebuyNewService.getProdList(paramMap, pageModel);
        json.putData("productList", prodList);
        json.putData("hasNext", !pageModel.isLast);

        return json;
    }

    /**
     * 查商品列表及搜索
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/qryProdList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryProdList(HttpServletRequest request) throws ParseException {
        Integer appType = ParamUtils.getInteger(request, "appType", 1); //1是APP， 2为文旅；默认是APP调用
        Long storeId = ParamUtils.getLong(request, "storeId", null);
        Long productTypeId = ParamUtils.getLong(request, "productTypeId", null);
        Integer supplyType = ParamUtils.getInteger(request, "supplyType", null);
        String searchKey = ParamUtils.getString(request, "searchKey", null);
        String orderBy = ParamUtils.getString(request, "orderBy", null);
        BigInteger groupBuildId = getUserGroupId(false);
        groupBuildId = HeaderManager.getGbId() == null ? groupBuildId : HeaderManager.getGbId();
        Integer opType = ParamUtils.getInteger(request, "opType", 0); //活动类型，如新用户专享为1，默认没有活动

        //统计当天浏览量
        if (storeId != null) {
            synchronized (lock) {
                String key = RedisCachePrefix.storeViewCountToday + sdfDate.format(new Date()) + storeId;
                Integer viewToday = RedisCacheHandler.get(key) == null ? 0 : (Integer.valueOf(RedisCacheHandler.get(key)));
                String value = null;
                SecureRandom random = new SecureRandom();
                if (viewToday == 0) {
                    value = String.valueOf(viewToday + Math.abs(random.nextInt()) % 11 + 1);;
                } else if (viewToday < 50) {
                    value = String.valueOf(viewToday + Math.abs(random.nextInt()) % 4 + 1);
                } else if (viewToday < 100) {
                    value = String.valueOf(viewToday + Math.abs(random.nextInt()) % 3 + 1);
                } else if (viewToday < 200) {
                    value = String.valueOf(viewToday + Math.abs(random.nextInt()) % 2 + 1);
                } else {
                    value = String.valueOf(viewToday + 1);
                }
                int expire = 60 * 60 * 24;
                RedisCacheHandler.set(key, value, expire);
            }
        }

        JsonResponse json = new JsonResponse();
        PageModel pageModel = ControllerUtils.getPageModel(request);
        if (productTypeId != null && productTypeId == -1L) {
            List<LimitBuyInfo> limitBuyList = limitBuyService
                    .getLimitBuyListByGbId(groupBuildId, 1, LimitBuyDict.PositionType.IN_PUB_STORE, pageModel);
            if (!DataUtil.isEmpty(limitBuyList)) {
                for (LimitBuyInfo limitBuyInfo : limitBuyList) {
                    String desc = ebuyNewService.getEbuyThemeDescByShelfId(limitBuyInfo.getShelfId(), 1);
                    limitBuyInfo.setInAnniversary(desc != null);
                }
            }
            json.putData("limitBuyList", limitBuyList);
            json.putData("hasNext", limitBuyList.size() == pageModel.getLength());
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("appType", appType);
            paramMap.put("storeId", storeId);
            paramMap.put("productTypeId", productTypeId);
            paramMap.put("groupBuildId", groupBuildId);
            paramMap.put("opType", opType);

            if(supplyType == null) {//supplyType=null时是首页的搜索
                if(groupBuildId == null || groupBuildId.longValue() == -1) {
                    paramMap.put("supplyType", 1); //未登录时，如果供应商类型为空，搜索自营的。登录则搜索所有的
                }
            } else if(supplyType == 1) {//supplyType不为空时,是非首页搜索
                if(groupBuildId == null || groupBuildId.longValue() == -1) {
                    paramMap.put("supplyType", 1);
                } else {
                    paramMap.put("supplyTypes", new Integer[]{1, 4});
                }
            } else {
                paramMap.put("supplyType", supplyType);
            }

            paramMap.put("searchKey", searchKey);
            paramMap.put("orderBy", orderBy);
            List<EbuyProdForLstSales> prodList = ebuyNewService.getProdList(paramMap, pageModel);
            if (!DataUtil.isEmpty(prodList)) {
                for (EbuyProdForLstSales ebuyProdForLstSales : prodList) {
                    String desc = ebuyNewService.getEbuyThemeDescByShelfId(BigInteger.valueOf(ebuyProdForLstSales.getId()), 1);
                    ebuyProdForLstSales.setInAnniversary(desc != null);
                }
            }
            json.putData("productList", prodList);
            json.putData("hasNext", !pageModel.isLast);

            //如果是查楼下店商品，则查出限时购部分
            if (storeId != null) {
                String repeat = sysParamManager.getSysParaValue(SysParamKey.Repeat_LimitBuy_Product_InStore);
                json.putData("isRepeatLimit", "Y".equals(repeat));
                //未开始的也查出来
                List<LimitBuyInfo> limitBuyList = limitBuyService.getLimitBuyListByMerchant(BigInteger.valueOf(storeId), 1, null, null);
                if (!DataUtil.isEmpty(limitBuyList)) {
                    for (LimitBuyInfo limitBuyInfo : limitBuyList) {
                        String desc = ebuyNewService.getEbuyThemeDescByShelfId(limitBuyInfo.getShelfId(), 1);
                        limitBuyInfo.setInAnniversary(desc != null);
                    }
                }
                json.putData("limitBuyList", limitBuyList);
            }
        }

        return json;
    }

    /**
     * 查楼下店列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/qryStoreList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryStoreList(HttpServletRequest request){
        JsonResponse jsonResponse = new JsonResponse();
        String searchKey = ParamUtils.getString(request, "searchKey", null);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchKey", searchKey);
        BigInteger groupBuildId = getUserGroupId(false);
        groupBuildId = HeaderManager.getGbId() == null ? groupBuildId : HeaderManager.getGbId();
        paramMap.put("groupBuildId", groupBuildId);

        PageModel pageModel = ControllerUtils.getPageModel(request);
        List<EbuyStore> storeList = ebuyNewService.getEbuyStoreList(paramMap, pageModel);

        return ControllerUtils.addPageInfo(jsonResponse, storeList, pageModel.isLast, pageModel.count);
    }

    @RequestMapping(value = "/qryStoreInfo.json")
    @ResponseBody
    public JsonResponse qryStoreInfo(BigInteger supplyMerchantId) {
        JsonResponse json = new JsonResponse();
        EbuyStore storeInfo = ebuyNewService.getEbuyStoreInfo(supplyMerchantId);
        json.setDataValue(storeInfo);
        return json;
    }

    private BigInteger getUserGroupId(boolean isGetCache) {
        BigInteger groupBuildId = weakMapCache.get(UserContext.getOperIdBigInteger());
        if(groupBuildId == null || !isGetCache) {
            groupBuildId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(UserContext.getOperIdBigInteger());
            weakMapCache.put(UserContext.getOperIdBigInteger(), groupBuildId);
        }
        return groupBuildId;
    }

}
