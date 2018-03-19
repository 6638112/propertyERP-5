package com.cnfantasia.server.api.ebuy.web;

import java.io.IOException;
import java.math.BigInteger;
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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.ebuy.domain.BuyCar;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntityFamilyGroup;
import com.cnfantasia.server.api.ebuy.entity.EbuyFamilyFavor;
import com.cnfantasia.server.api.ebuy.entity.EbuyFightGroups;
import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;
import com.cnfantasia.server.api.ebuy.entity.EbuyHomeProd;
import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLst;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdForLstSales;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductHasTEbuyAuthEntity_EbuyAuth;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.EbuySalesPromotion;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.ebuy.service.EbuyIdentifyInfoService;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.ebuy.service.IEbuyFlowRechargeService;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.ebuy.service.IEbuyService;
import com.cnfantasia.server.api.ebuyorder.util.RechargePackageUtils;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.operation.service.IOperationService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.advertiseClickRecord.entity.AdvertiseClickRecord;
import com.cnfantasia.server.domainbase.advertiseClickRecord.service.IAdvertiseClickRecordBaseService;
import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.entity.DeliveryOrderExpressTrace;
import com.cnfantasia.server.domainbase.deliveryOrderExpressTrace.service.IDeliveryOrderExpressTraceBaseService;
import com.cnfantasia.server.domainbase.ebuyAdvertise.service.IEbuyAdvertiseBaseService;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;
import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 此接口有用到mybatis二级缓存，缓存的刷新策略根据不同的接口不同的策略
 * 暂时没有进行相关清除缓存处理，而是自动间隔时间刷新，待后续补充完整
 * @author yewj
 */
@Controller
@RequestMapping("/ebuyNew")
public class EbuyNewController extends BaseController{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IEbuyNewService ebuyNewService;
	
	private IEbuyService ebuyService;
	
	private IOperationService operationService;
	
	private IEbuyAdvertiseService advertiseService;
	
	private ICommonRoomService commonRoomService;
	
	private ICommonUserService commonUserService;
	
	private ICommonDeviceService commonDeviceService;
	
	private IEbuyFlowRechargeService ebuyFlowRechargeService;
	
	private EbuyIdentifyInfoService ebuyIdentifyInfoService;
	
	private IAddressOperationService addressOperationService;

	@Resource
	private IAdvertiseClickRecordBaseService advertiseClickRecordBaseService;
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private ISysParamManager sysParamManager;

	@Resource
	private ICompanyService companyService;

	@Resource
	private IDeliveryOrderExpressTraceBaseService deliveryOrderExpressTraceBaseService;

	@Resource
	private IEbuyProductShelfBaseService ebuyProductShelfBaseService;

	@Resource
	private IHomeMessageService homeMessageService;
	
	@Resource
	private IEbuyAdvertiseBaseService ebuyAdvertiseBaseService;
	@Resource
	private IUserBaseService userBaseService;
	
	private static Map<BigInteger, BigInteger> weakMapCache = new WeakHashMap<BigInteger, BigInteger>(1000);
	
	private static String RECHARGE_UUID;
	
	private static String RECHARGE_PHONE_URL;
	

	private BigInteger getUserGroupId(boolean isGetCache) {
		BigInteger groupBuildId = weakMapCache.get(UserContext.getOperIdBigInteger());
		if(groupBuildId == null || !isGetCache) {
			groupBuildId = commonRoomService.getGroupBuildingIdByUserIdForEbuy(UserContext.getOperIdBigInteger());
			weakMapCache.put(UserContext.getOperIdBigInteger(), groupBuildId);
		}
		return groupBuildId;
	}
	
	
	/**
	 * 查询首页广告栏列表（首页弹框，超市banner，LA在用，连LA一起改的时候这个再去掉 注释于2016.12.26）
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryAds.json")
	@ResponseBody
	public JsonResponse qryAds(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		String code = ParamUtils.getString(request, "code", "EBUY_AD");
		String city = ParamUtils.getString(request, "city", null);
		Integer version = HeaderManager.getVersionNum();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> codeList = new ArrayList<String>();
		codeList.add(code);
		paramMap.put("codeList", codeList);
		paramMap.put("version", version);
		
		List<BigInteger> codeIdList = null;
		BigInteger gbId = getUserGroupId(false);
		BigInteger cityId = null;
		if(!StringUtils.isEmpty(city)) {
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("city", city);
			cityId = ebuyNewService.selectCityIdByName(paramMap2);
		}
		if(cityId != null) {
			//轻应用广告栏传过来的是城市名称
			codeIdList = addressOperationService.getCodeIdList(null, null, cityId, null, null);
		} else {
			if(gbId == null) {
				gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
			}
			codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);
		}
		paramMap.put("codeIdList", codeIdList);
		List<EbuyAdvertise> adList = null;
		List<EbuyAdvertise> ads = null;
		if (code.equals("MAIN_BUSINESS_AD")) {// 首页弹框广告
			EbuyAdvertise advertise = advertiseService.getMainPromoteAdvertise(paramMap);
			if (advertise != null) {
				advertise.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, advertise.getPicName(), advertise.getUpdTime()));
				if(advertise.getType() == 1 && version != null) {
					if(advertise.getLinkUrl().contains("?")) {
						advertise.setLinkUrl(advertise.getLinkUrl() + "&version=" + version);
					} else {
						advertise.setLinkUrl(advertise.getLinkUrl() + "?version=" + version);
					}
				}
				adList = new ArrayList<EbuyAdvertise>();
				Integer frequency = advertise.getFrequency() == null ? 1 : advertise.getFrequency();
				if (frequency.compareTo(2) == 0) {
					AdvertiseClickRecord record = new AdvertiseClickRecord();
					record.setAdvertiseId(BigInteger.valueOf(advertise.getId()));
					record.setUserId(UserContext.getOperIdMustExistBigInteger());
					int count = advertiseClickRecordBaseService.getAdvertiseClickRecordCount(MapConverter.toMap(record));
					if (count <= 0) {
						adList.add(advertise);
					}
				} else {
					adList.add(advertise);
				}
			}
			ads = adList;
		} else {// banner
			adList = advertiseService.getEbuyAdvertiseList(paramMap);
			List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchants= advertiseService.queryFightGroupProductZiti();
			int fightGroupProductZitiNum = (ebuyFightSupplyMerchants!=null)?ebuyFightSupplyMerchants.size():0;
			List<BigInteger> adIds = advertiseService.queryPromoteProductAdIds();
			if(adList != null) {
				ads = new ArrayList<EbuyAdvertise>();
				for(EbuyAdvertise ad : adList) {
					ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, ad.getPicName(), ad.getUpdTime()));
					if(ad.getType() == 1 && version != null) {
						if(ad.getLinkUrl().contains("?")) {
							ad.setLinkUrl(ad.getLinkUrl() + "&version=" + version);
						} else {
							ad.setLinkUrl(ad.getLinkUrl() + "?version=" + version);
						}
					}
					{
						// 没有自提点，不显示轻应用拼购banner；生产上没有专题banner
						if("LA_EBUY".equals(ad.getCode()) &&
								ad.getLinkUrl()!=null && ad.getLinkUrl().contains("../laGroupPurchase/ziTiDianList.do")){
							if(fightGroupProductZitiNum>0){// 有自提点， add
								ads.add(ad);
							}
						} else if(("EBUY_AD".equals(ad.getCode()) &&
								ad.getLinkUrl()!=null && ad.getLinkUrl().contains("ebuyNew/themeProductAd.html")) ||
								("LA_EBUY".equals(ad.getCode()) &&
										ad.getLinkUrl()!=null && ad.getLinkUrl().contains("../product/themeProductAd.do"))){
							// app专题banner、轻应用专题banner
							if(adIds!=null){
								for(BigInteger adId:adIds){
									if((ad.getId()!=null) && (adId.toString().equals(ad.getId().toString()))){
										ads.add(ad);
										break;
									}
								}
							}
						} else {
							ads.add(ad);
						}
					}
				}
			}
		}
		jsonResponse.putData("list", ads);
		return jsonResponse;
	}

	
	/**
	 * 查询搜索框文字
	 * @param request
	 * @return
	 */
	@RequestMapping("/qrySearchWord.json")
	@ResponseBody
	public JsonResponse qrySearchWord(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		String code = ParamUtils.getString(request, "code", "EBUY_SEARCH_WORD");
		OperationConstantData operationConstantData = operationService.getOperationSignal(code);
		
		if(operationConstantData != null) {
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("code", operationConstantData.getCode());
			resMap.put("word", operationConstantData.getContent());
			jsonResponse.setDataValue(resMap);
		}
		
		return jsonResponse;
	}
	
	/**
	 * 超市首页的各分类及商品列表接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryHomeProdList.json")
	@ResponseBody
	public JsonResponse qryHomeProdList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		int prodCount = ParamUtils.getInt(request, "prodCount", 7);
		//拿当前用户所在的小区ID，未添加门牌则会拿到-1
		BigInteger groupBuildId = getUserGroupId(false);
		int sex = UserContext.getSex();
		
		List<EbuyHomeProd> ebuyHomeProdList = ebuyNewService.getEbuyHomeProdList(EbuyHomeProd.HOME_TYPE_COMMON, groupBuildId, prodCount, sex);
		jsonResponse.putData("list", ebuyHomeProdList);
		return jsonResponse;
	}
	
	/**
	 * 超市首页的各分类商品列表更多接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryHomeProdMoreList.json")
	@ResponseBody
	public JsonResponse qryHomeProdMoreList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		Long homeTypeId = ParamUtils.getLong(request, "homeTypeId", null);
		String searchKey = ParamUtils.getString(request, "searchKey", null);
		
		//拿当前用户所在的小区ID，未添加门牌则会拿到-1
		BigInteger groupBuildId = getUserGroupId(false);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("homeTypeId", homeTypeId);
		paramMap.put("searchKey", searchKey);
		paramMap.put("sex", UserContext.getSex());
		if(groupBuildId != null && groupBuildId.longValue() != -1L) {
			paramMap.put("groupBuildId", groupBuildId);
		} else {
			paramMap.put("supplyType", 1);//未登录只显示自营商品
		}

		PageModel pageModel = ControllerUtils.getPageModel(request);
		List<EbuyProdForLst> prodList = ebuyNewService.getEbuyHomeProdMoreList(paramMap, pageModel);
		return ControllerUtils.addPageInfo(jsonResponse, prodList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 超市首页的粮票列表接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryHomePacketList.json")
	@ResponseBody
	public JsonResponse qryHomePacketList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		int prodCount = ParamUtils.getInt(request, "prodCount", 8);
		//拿当前用户所在的小区ID，未添加门牌则会拿到-1
		BigInteger groupBuildId = getUserGroupId(false);
		int sex = UserContext.getSex();
		
		EbuyHomeProd ebuyHomeProd = ebuyNewService.getEbuyHomeProd(EbuyHomeProd.HOME_TYPE_PACKET, groupBuildId, prodCount, sex);
		jsonResponse.setDataValue(ebuyHomeProd);
		return jsonResponse;
	}
	
	@RequestMapping("/qryFamilyFavorList.json")
	@ResponseBody
	public JsonResponse qryFamilyFavorList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		List<UserSimpleEntity> familyUserList = commonUserService.getFamilyMembersWithoutSelf(userId, false);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger groupBuildId = getUserGroupId(false);
		paramMap.put("groupBuildId", groupBuildId);
		
		if(familyUserList != null && familyUserList.size() > 0) {
			paramMap.put("familyUserList", familyUserList);
			List<EbuyFamilyFavor> familyFavorList = ebuyNewService.getEbuyFamilyFavorList(paramMap);
			jsonResponse.putData("list", familyFavorList);
		} else {
			jsonResponse.putData("list", new ArrayList<EbuyFamilyFavor>());
		}
		
		return jsonResponse;
	}
	
	/**
	 * 此接口用于家人喜欢测试用，上生产时可以删除此接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryFamilyFavorListTest.json")
	@ResponseBody
	public JsonResponse qryFamilyFavorListTest(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		BigInteger userId = BigInteger.valueOf(50004L);
		
		List<UserSimpleEntity> familyUserList = commonUserService.getFamilyMembersWithoutSelf(userId, false);
		if(familyUserList != null && familyUserList.size() > 0) {
			paramMap.put("familyUserList", familyUserList);
			List<EbuyFamilyFavor> familyFavorList = ebuyNewService.getEbuyFamilyFavorList(paramMap);
			jsonResponse.putData("list", familyFavorList);
		} else {
			jsonResponse.putData("list", new ArrayList<EbuyFamilyFavor>());
		}
		
		return jsonResponse;
	}

	
	@RequestMapping("/add2BuyCarBatch.json")
	@ResponseBody
	public JsonResponse add2BuyCarBatch(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		List<BuyCar> buyCarList = JSON.parseArray(request.getParameter("productList"), BuyCar.class);
		UserIdType userIdType = commonDeviceService.getUserIdType();
		
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity = null;
		String notEnoughMsg = "";
		for(BuyCar buyCar : buyCarList) {
			try {
				ebuyBuyCarEntity = ebuyService.add2BuyCar(userIdType.getUserId(), 
						userIdType.getUserType(), buyCar.getProductId(), buyCar.getNum(), EbuyDict.PointType.EBUY_PRODUCT, EbuyDict.WlAppType.Jfq, buyCar.getProductSpecId());
			} catch(BusinessRuntimeException e) {
//				throw e;
				notEnoughMsg = "部分商品库存不足导致部分商品添加购物车失败！";
			}
		}
		
		if(ebuyBuyCarEntity != null) {
			jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		} else {
			jsonResponse.putData("productCount", 0);
		}
		if(!notEnoughMsg.equals("")) {
			jsonResponse.putData("notEnoughMsg", notEnoughMsg);
			logger.debug(JSON.toJSONString(jsonResponse));
		}
		
		return jsonResponse;
	}
	
	/**
	 * 拿物业专供和楼下的分类
	 * @param request
	 * @return
	 */
	@RequestMapping("/getStoreProdTypes.json")
	@ResponseBody
	public JsonResponse getStoreProdTypes(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		
		Integer appType = ParamUtils.getInteger(request, "appType", 1); //1是APP， 2为文旅；默认是APP调用
		Long storeId = ParamUtils.getLong(request, "storeId", null);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appType", appType);
		
		if(storeId == null) {
			BigInteger groupBuildId = getUserGroupId(false);
			paramMap.put("groupBuildId", groupBuildId);
			paramMap.put("supplyType", 3);
		} else {
			paramMap.put("storeId", storeId);
			paramMap.put("supplyType", 2);
		}
		
		List<EbuyProdType> prodTypeList = ebuyNewService.getStoreProdTypes(paramMap);
		jsonResponse.putData("list", prodTypeList);
		return jsonResponse;
	}
	
	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchHomeProdList.json")
	@ResponseBody
	public JsonResponse searchHomeProdList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		Long homeTypeId = ParamUtils.getLong(request, "homeTypeId", null);
		String searchKey = ParamUtils.getString(request, "searchKey", null);
		BigInteger groupBuildId = getUserGroupId(false);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("homeTypeId", homeTypeId);
		paramMap.put("searchKey", searchKey);
		paramMap.put("groupBuildId", groupBuildId);
		if(groupBuildId == null || groupBuildId.longValue() == -1) {
			paramMap.put("supplyType", 1);
		}
		
		PageModel pageModel = ControllerUtils.getPageModel(request);
		
		List<EbuyProdForLst> prodList = ebuyNewService.searchHomeProdListByPage(paramMap, pageModel);
		return ControllerUtils.addPageInfo(jsonResponse, prodList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/mobileQuery.json")
	@ResponseBody
	public Object mobileQuery(String mobileNo, String orderType) throws IOException {
		if(RECHARGE_UUID == null) {
			RECHARGE_UUID = CnfantasiaCommbusiUtil.getSysParaValue("rechargeUuid");
			RECHARGE_PHONE_URL = CnfantasiaCommbusiUtil.getSysParaValue("rechargePhoneUrl");
		}
		
//		String jsonStr = RechargePackageUtils.getRechargePackageJson();
//		Double price = RechargePackageUtils.getRechargePackage("YD10");
		
//		if(RechargePackageUtils.getRechargePackageJson() == null) {
			HttpUtil httpUtil = new HttpUtil();
			httpUtil.addParameter("mobileNo", mobileNo);
			httpUtil.addParameter("uuid", RECHARGE_UUID);
			if(!StringUtils.isEmpty(orderType)) {
				httpUtil.addParameter("orderType", orderType); //orderType=3为查询话费充值套餐;为空表示流量套餐
			}
			String jsonStr = httpUtil.post(RECHARGE_PHONE_URL, 10000, "UTF-8");
			
			RechargePackageUtils.setRechargePackageToCache(jsonStr, mobileNo);
//		}
		return jsonStr;
		
////		try {
//			HttpUtil httpUtil = new HttpUtil();
//			httpUtil.addParameter("mobileNo", mobileNo);
//			httpUtil.addParameter("uuid", RECHARGE_UUID);
//			String jsonStr = httpUtil.post(RECHARGE_PHONE_URL, 5000, "UTF-8");
//			
//			Map<String, RechargePackage> rechargePackageMap = RechargePackageUtils.getRechargePackageList(jsonStr);
//			
//			RedisCacheHandler.set(orderId.toString(), orderId.toString(), 60);
//			
//			return jsonStr;
////		} catch (Exception e) {
////			String test = "{\"code\":\"00\",\"msg\":\"OK\",\"data\":{\"count\":6,\"data\":[{\"vol\":10,\"price\":3,\"packageName\":\"移动全国流量卡10M\",\"operatorCode\":\"YD\",\"packageId\":\"YD10\",\"salePrice\":2.4,\"oprPackageId\":\"10\",\"activePeriod\":365,\"isCombo\":\"0\"},{\"vol\":30,\"price\":5,\"operatorCode\":\"YD\",\"packageId\":\"YD30\",\"salePrice\":5.0,\"oprPackageId\":\"11\",\"activePeriod\":365,\"isCombo\":\"0\"},{\"vol\":70,\"price\":10,\"operatorCode\":\"YD\",\"packageId\":\"YD70\",\"salePrice\":10.0,\"oprPackageId\":\"12\",\"activePeriod\":365,\"isCombo\":\"0\"},{\"vol\":150,\"price\":20,\"operatorCode\":\"YD\",\"packageId\":\"YD150\",\"salePrice\":20.0,\"oprPackageId\":\"13\",\"activePeriod\":365,\"isCombo\":\"0\"},{\"vol\":500,\"price\":30,\"operatorCode\":\"YD\",\"packageId\":\"YD500\",\"salePrice\":30.0,\"oprPackageId\":\"18\",\"activePeriod\":365,\"isCombo\":\"0\"},{\"vol\":1024,\"price\":50,\"operatorCode\":\"YD\",\"packageId\":\"YD1024\",\"salePrice\":50.0,\"oprPackageId\":\"19\",\"activePeriod\":365,\"isCombo\":\"0\"}]}}";
////			return test;
////		}
	}
	
	@RequestMapping("/returnStatus.json")
	@ResponseBody
	public Object returnStatus(HttpServletRequest request) {
		String jsonStr = "";
		String retStr = "";
		try {
			logger.debug("returnStatus-begin:");
			jsonStr = ParamUtils.getRequestBodyJson(request);
			logger.info("returnStatus-jsonStr:" + jsonStr);
			JSONObject retJson = new JSONObject(jsonStr);
			EbuyFlowRecharge flowRecharge = new EbuyFlowRecharge();
			
			String orderId = retJson.getJSONObject("MSGBODY").getJSONObject("CONTENT").getString("EXTORDER");
			flowRecharge.setOrderId(Long.valueOf(orderId.trim()));
			
			if("00".equals(retJson.getJSONObject("MSGBODY").getJSONObject("CONTENT").getString("CODE"))) {
				flowRecharge.setStatus(3);
			} else {
				flowRecharge.setStatus(4);
			}
			flowRecharge.setCallBackResult(jsonStr);
			ebuyFlowRechargeService.updateFlowRecharge(flowRecharge);
			if(flowRecharge.getStatus() == 3) {
				ebuyFlowRechargeService.updateFlowRechargeOrder(flowRecharge);
			}
			
			retStr = getRespForRecharge("00", "ok").toString();
		} catch (JSONException e) {
			logger.error("returnStatus error:" + jsonStr);
			logger.error(e.getMessage(), e);
			return getRespForRecharge("99", e.getMessage().length() > 15 ? e.getMessage().substring(0, 15) : e.getMessage());
		}
		return retStr;
	}
	
	/**
	 * 确认收货
	 */
	@RequestMapping("/confirmReceived.json")
	@ResponseBody
	public JsonResponse confirmReceived(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger deliveryOrderId = new BigInteger(request.getParameter("deliveryOrderId"));
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));

		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		//2.交互
		ebuyNewService.confirmReceived(userId, orderId, deliveryOrderId);

		//更新运单主的消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.EBUY_STORE_ALERT);
		message.setResouceId(deliveryOrderId);
		homeMessageService.generateCommonMsg(message);

		//3.结果处理
		return jsonResponse;
	}

	private JSONObject getRespForRecharge(String rcode, String rmsg) {
		String dateStr = DateUtils.getCurrentDateStr("yyyyMMddHHmmssSS");
		String APP_ID = CnfantasiaCommbusiUtil.getSysParaValue("rechargeAppId");
		String SECERT_KEY = CnfantasiaCommbusiUtil.getSysParaValue("rechargeSecertKey");
		String secertkey = dateStr + dateStr + APP_ID + SECERT_KEY;
		Map<String, Object> respMap = new HashMap<String, Object>();
		Map<String, Object> header = new HashMap<String, Object>();
		respMap.put("HEADER", header);
		header.put("VERSION", "V1.0");
		header.put("TIMESTAMP", dateStr);
		header.put("SEQNO", dateStr);
		header.put("APPID", APP_ID);
		header.put("SECERTKEY", Md5Util.MD5(secertkey));
		
		Map<String, Object> msgbody = new HashMap<String, Object>();
		Map<String, Object> resp = new HashMap<String, Object>();
		respMap.put("MSGBODY", msgbody);
		msgbody.put("RESP", resp);
		
		resp.put("RCODE", rcode);
		resp.put("RMSG", rmsg);
		
//		RedisCacheHandler.set(orderId.toString(), orderId.toString(), 60);
		
		JSONObject respJson = new JSONObject(respMap);
		return respJson;
	}
	
	/**
	 * 是否显示新用户专享
	 */
	@RequestMapping("/isNewUserType.json")
	@ResponseBody
	public JsonResponse isNewUserType(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String city = ParamUtils.getString(request, "city", null);
//		float lat = ParamUtils.getFloat(request, "lat", 0); //纬度值
//		float lng = ParamUtils.getFloat(request, "lng", 0); //经度值
		if(StringUtils.isEmpty(city)) {
			jsonResponse.putData("isNewUser", 0);
			return jsonResponse;
		}
		BigInteger userId = UserContext.getOperIdBigInteger();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("city", city);
		paramMap.put("userId", userId);
		EbuySalesPromotion salesPromotion = ebuyNewService.getNewUserType(paramMap);
		if(salesPromotion == null) {
			jsonResponse.putData("isNewUser", 0);
//			jsonResponse.putData("canBuyNum", 0);
		} else {
			jsonResponse.putData("isNewUser", 1);
			jsonResponse.putData("name", salesPromotion.getName());
			jsonResponse.putData("canBuyNum", salesPromotion.getCanBuyNum());
		}

		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 海淘商品拿身份证信息
	 */
	@RequestMapping("/getIdentifyInfo.json")
	@ResponseBody
	public JsonResponse getIdentifyInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		if(userId == null) {
			userId = UserContext.getOperIdMustExistBigInteger();
		} else if(UserContext.getOperIdBigInteger() != null) {
			userId = UserContext.getOperIdBigInteger();
		}
		String idName = ParamUtils.getString(request, "idName", null);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("idName", idName);
		EbuyIdentifyInfo idInfo = ebuyIdentifyInfoService.getIdentifyInfo(paramMap);
		if(idInfo != null) {
			jsonResponse.putData("idInfo", idInfo);
		}
//		jsonResponse.putData("buyNotice", "跨境商品同地址每月限购一次");
		jsonResponse.putData("buyNotice", CnfantasiaCommbusiUtil.getSysParaValue("buyNotice"));

		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 删除身份证信息
	 */
	@RequestMapping("/delIdentifyInfo.json")
	@ResponseBody
	public JsonResponse delIdentifyInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		if(userId == null) {
			userId = UserContext.getOperIdMustExistBigInteger();
		} else if(UserContext.getOperIdBigInteger() != null) {
			userId = UserContext.getOperIdBigInteger();
		}
		String idName = ParamUtils.getString(request, "idName", null);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("idName", idName);
		ebuyIdentifyInfoService.delIdentifyInfo(paramMap);
		
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/getTest.json")
	@ResponseBody
	public Object getTest() throws JSONException {
		String test = "{\"MSGBODY\":{\"CONTENT\":{\"SIGN\":\"47119F4324F82D2245A0E7EFAF794F86\",\"ORDERTYPE\":\"1\",\"EXTORDER\":\"51209\",\"PACKAGEID\":\"10\",\"USER\":\"13723460250\"}},\"HEADER\":{\"SEQNO\":\"51209\",\"VERSION\":\"V1.0\",\"SECERTKEY\":\"972ACF487CE145192D23C27EECF457E4\",\"APPID\":\"caish\",\"TIMESTAMP\":\"20150804100514395\"}}";
		JSONObject testJ = new JSONObject(test);
		JSONObject t2 = HttpUtil.post("http://192.168.5.93:8080/API/ebuyNew/returnStatus.json", testJ);
		return t2.toString();
	}
	
	@RequestMapping("/getTest2.json")
	@ResponseBody
	public Object getTest2(HttpServletRequest request) throws JSONException, IOException {
		logger.info("getTest2-info:" + request.getRemoteAddr() + "," + request.getRemoteHost() + "," + request.getRemotePort() + "," + request.getRequestURI());
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addHeader("test2", "test2");
		httpUtil.addParameter("curTime", DateUtils.getCurrentDateStr("yyyyMMddHHmmssSS"));
		String retStr = httpUtil.get("http://202.170.133.211:80/API/getTest2.json", 5000);
		logger.info("getTest2-retStr:" + retStr);
		return request.getRemoteAddr();
	}
	
	@RequestMapping("/cleanCache.json")
	@ResponseBody
	public Object cleanCache(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		ebuyNewService.cleanCache();
		return jsonResponse;
	}
	
	public void setOperationService(IOperationService operationService) {
		this.operationService = operationService;
	}

	public void setEbuyNewService(IEbuyNewService ebuyNewService) {
		this.ebuyNewService = ebuyNewService;
	}

	public void setAdvertiseService(IEbuyAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	public void setCommonUserService(ICommonUserService commonUserService) {
		this.commonUserService = commonUserService;
	}

	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}

	public void setEbuyService(IEbuyService ebuyService) {
		this.ebuyService = ebuyService;
	}

	public void setEbuyFlowRechargeService(
			IEbuyFlowRechargeService ebuyFlowRechargeService) {
		this.ebuyFlowRechargeService = ebuyFlowRechargeService;
	}

	public void setEbuyIdentifyInfoService(
			EbuyIdentifyInfoService ebuyIdentifyInfoService) {
		this.ebuyIdentifyInfoService = ebuyIdentifyInfoService;
	}
	
	/**
	 * 超市商品详情html页面
	 * @return
	 */
	@RequestMapping("/newEbuyproductDetail.html")
	public String newEbuyproductDetail(HttpServletRequest request,ModelMap model){
		//搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//获取登录用户信息
		BigInteger userId = UserContext.getOperIdBigInteger();//可以为空
		Long proCarNum = ParamUtils.getLong(request, "proCarNum", null);
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		
		EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(ebuyService.getProductById(userId,productId,pointType,parseWlAppType(request)), null);
		if(ebuyProductInfo==null){
			return "/ebuy/noItem";
		}
		EbuyProductShelf shelf = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(ebuyProductInfo.getId());
		List<EbuyProductParameters> productParametersList = ebuyService.getProductParameters(shelf.gettEbuyProductId());
		String themeAdDesc = ebuyNewService.getEbuyThemeDescByShelfId(shelf.getId(), 1);
		//配送名称
		String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
		//认证信息
		List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
		//运费信息
		Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
		//产品图片列表
		List<EbuyProductPic>  ebuyProductPicList = ebuyProductInfo.doEntity().getEbuyProductPicList();
		List<String> picList = new ArrayList<String>();
		if(ebuyProductPicList!=null && ebuyProductPicList.size()>0){
			for (int i = ebuyProductPicList.size() - 1; i >= 0; i--) {
				EbuyProductPic productPic = ebuyProductPicList.get(i);
				picList.add(productPic.getUrlMini());
			}
		}else{
			picList.add(ebuyProductInfo.getPicBaseMini());
		}
		//产品介绍图片列表
		List<EbuyProductIntroducePic>  ebuyProductIntroducePicList = ebuyProductInfo.doEntity().getEbuyProductIntroducePicList();
//		List<String> picList1 = new ArrayList<String>();
		//产品规格列表
		List<EbuyProductSpec> ebuyProductSpecList = ebuyProductInfo.doEntity().getEbuyProductSpecList();
		//供应商名称
		String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
		//客服电话
		String phone = companyService.getCompanyServiceInfo().getTel();
		//结果处理
		if(ebuyProductInfo.getFilmTicketNum() !=null && ebuyProductInfo.getFilmTicketNum() >= 1 && ebuyProductInfo.getFilmTicketNum() < 100){
			model.addAttribute("filmStatus", 1);
			model.addAttribute("filmPayUrl", sysParamManager.getSysParaValue(SysParamKey.FILE_PAY_URL));
			model.addAttribute("filmLookUrl", sysParamManager.getSysParaValue(SysParamKey.FILE_LOOK_URL));
		}
		model.addAttribute("ebuyProductInfo", ebuyProductInfo);
		model.addAttribute("themeAdDesc", themeAdDesc);
		Collections.reverse(productParametersList);
		model.addAttribute("productParametersList", productParametersList);
		model.addAttribute("picserverUrl", getrefundPicUrl());
		model.addAttribute("defaultDeliveryName", defaultDeliveryName);
		model.addAttribute("ebuyProductHasTEbuyAuthEntityList", ebuyProductHasTEbuyAuthEntityList);
		model.addAttribute("defaultDeliveryFee", defaultDeliveryFee);
		model.addAttribute("ebuyProductPicList", picList);
		model.addAttribute("ebuyProductIntroducePicList", ebuyProductIntroducePicList);
		model.addAttribute("ebuyProductSpecList", ebuyProductSpecList);
		model.addAttribute("supplyMerchantName", supplyMerchantName);
		model.addAttribute("phone", phone);
		model.addAttribute("proCarNum", proCarNum);

        String server = CnfantasiaCommbusiUtil.getSysParaValue("laUrl");
        model.addAttribute("shareUrl", server + "/product/productDetail.do?ptId=" + productId);
        String pic = ebuyProductInfo.getPicBase();
        if (pic != null) {
            pic = pic.substring(0, pic.lastIndexOf(".")) + "/72" + pic.substring(pic.lastIndexOf("."), pic.length());
            model.addAttribute("shareImg", OmsSysParamManager.getImageServerUrl(PathConstants.Product_Image_Dir)
                    + PathConstants.Product_Image_Dir + pic);
        }
        return "/ebuy/itemDetails";
	}
	
	@RequestMapping("/itemList.html")
	public String itemList(HttpServletRequest request,ModelMap model){
		
		return "/ebuy/itemList";
	}

	@RequestMapping("themeProductAd.html")
	public ModelAndView jumpToThemeProductAd(BigInteger advId) {
		ModelAndView mav = new ModelAndView("/ebuy/themeProductAd");
		com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise ebuyAdvertise = ebuyAdvertiseBaseService.getEbuyAdvertiseBySeqId(advId);
		List<EbuyProdForLstSales> products = ebuyNewService.selectAdvertiseThemeProduct(advId);
		mav.addObject("products", products);
		mav.addObject("ebuyAdvertise", ebuyAdvertise);
		return mav;
	}

	@RequestMapping("themeProductAd.json")
	@ResponseBody
	public JsonResponse getThemeProductAd(BigInteger advId) {
		JsonResponse json = new JsonResponse();
		List<EbuyProdForLstSales> products = ebuyNewService.selectAdvertiseThemeProduct(advId);
		json.putData("products", products);
		return json;
	}

	/**
	 * APP超市请求楼下店和拼购列表(从348已不用)
	 * @return
     */
	@RequestMapping(value = "qryHomeStoreAndFightGroups.json")
	@ResponseBody
	@Deprecated
	public JsonResponse qryHomeStoreAndFightGroups() {
		JsonResponse json = new JsonResponse();
		//查楼下店
		BigInteger groupBuildId = getUserGroupId(false);
		if (groupBuildId == null || groupBuildId.compareTo(new BigInteger("-1")) == 0) {
			json.putData("homeStoreTitle", "解放区加盟店");
			json.putData("storeInfo", null);

			json.putData("homeFightGroupsTitle", "解放区特荐");
			json.putData("fightGroupsListLink", null);
			json.putData("fightGroupsInfo", null);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("groupBuildId", groupBuildId);
			paramMap.put("orderType", "1");
			PageModel pageModel = new PageModel(0, 1);
			List<EbuyStore> storeList = ebuyNewService.getEbuyStoreList(paramMap, pageModel);
			EbuyStore ebuyStore = null;
			if (!DataUtil.isEmpty(storeList)) {
				ebuyStore = storeList.get(0);
			}
			json.putData("homeStoreTitle", "解放区加盟店");
			json.putData("storeInfo", ebuyStore);

			//查拼购
			EbuyFightGroups hotProduct = ebuyNewService.getHotFightProductSelling(groupBuildId);
			List<EbuyFightGroups> fightProductsNotBegin = ebuyNewService.getFightProducts("0", groupBuildId, pageModel);
			pageModel.setLength(4 - fightProductsNotBegin.size());
			List<EbuyFightGroups> fightProductsBegin = ebuyNewService.getFightProducts("1", groupBuildId, pageModel);

			List<EbuyFightGroups> fightGroupsInfo = new ArrayList<EbuyFightGroups>();
			if (hotProduct != null) {
				hotProduct.setFightStatus(4);
				fightGroupsInfo.add(hotProduct);
			}
			//进行中的要排除热销
			for (int i = 0; i < fightProductsBegin.size(); i++) {
				if (hotProduct == null
						|| hotProduct.getProductId().compareTo(fightProductsBegin.get(i).getProductId()) != 0) {
					fightGroupsInfo.add(fightProductsBegin.get(i));
					if (fightGroupsInfo.size() >= 3 - fightProductsNotBegin.size()) {
						break;
					}
				}
			}
			if (!DataUtil.isEmpty(fightProductsNotBegin)) {
				fightGroupsInfo.add(fightProductsNotBegin.get(0));
			}
			json.putData("homeFightGroupsTitle", "解放区特荐");
			String fightGroupsListLink = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.Curr_Server_BaseUrl)
					+ "ebuyNew/fightGroupsList.html";
			json.putData("fightGroupsListLink", fightGroupsListLink);
			json.putData("fightGroupsInfo", fightGroupsInfo);
		}

		return json;
	}

	/**
	 * 拼购列表
	 * @return
	 */
	@RequestMapping(value = "/fightGroupsList.html")
	public ModelAndView fightGroupsList() {
		ModelAndView mav = new ModelAndView("/ebuy/ebuyFight/groupBuyingList");
		//查拼购
		BigInteger groupBuildId = getUserGroupId(false);
		List<EbuyFightGroups> fightProductsNotBegin = ebuyNewService.getFightProducts("0", groupBuildId, null);
		List<EbuyFightGroups> fightProductsBegin = ebuyNewService.getFightProducts("1", groupBuildId, null);
		mav.addObject("proList", fightProductsBegin);
		mav.addObject("preSellList", fightProductsNotBegin);
		return  mav;
	}

	@RequestMapping(value = "/qryDeliveryOrderExpress.json")
	@ResponseBody
	public JsonResponse getDeliveryOrderExpress(BigInteger deliveryOrderId){
		DeliveryOrderExpressTrace trace = new DeliveryOrderExpressTrace();
		trace.setDeliveryOrderId(deliveryOrderId);
		List<DeliveryOrderExpressTrace> traceList = null;
		if (deliveryOrderId != null) {
			traceList = deliveryOrderExpressTraceBaseService.getDeliveryOrderExpressTraceByCondition(MapConverter.toMap(trace));
		}
		JsonResponse json = new JsonResponse();
		json.putData("list", traceList);
		return json;
	}
	@RequestMapping(value = "/clickAd.json")
	@ResponseBody
	public JsonResponse clickAd(BigInteger advId, Integer positionType, String advTitle, String advLink){
		BigInteger userId = UserContext.getOperIdBigInteger();
		AdvertiseClickRecord record = new AdvertiseClickRecord();
		record.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_advertise_click_record));
		record.setAdvertiseId(advId);
		record.setUserId(userId);
		record.setPositionType(positionType);
		record.setAdvTitle(advTitle);
		record.setAdvLink(advLink);
		if (userId != null) {
			User nowUser = userBaseService.getUserBySeqId(userId);
			record.setRoomId(nowUser.getDefaultRoomId());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		record.setClickDate(sdf.format(new Date()));
		advertiseClickRecordBaseService.insertAdvertiseClickRecord(record);
		return new JsonResponse();
	}

	/**
	 * 解析请求的商品类别，电商商品还是积分商品
	 * @param request
	 * @return
	 */
	protected Integer parsePointType(HttpServletRequest request){
		Integer pointType = EbuyDict.PointType.EBUY_PRODUCT;//默认电商商品
//		String pointTypeStr = request.getParameter("pointType");
//		if(!StringUtils.isEmpty(pointTypeStr)){
//			pointType = Integer.parseInt(pointTypeStr);
//		}
		return pointType;
	}
	
	protected Long parseWlAppType(HttpServletRequest request){
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Wl_Light_App)==0){
			return EbuyDict.WlAppType.Wl_Light_App;
		}else if(subChannelId!=null&&subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0){//解放区微信轻应用
			return EbuyDict.WlAppType.Jfq_Light_App;
		}
		return EbuyDict.WlAppType.Jfq;
	}
	
	//获取图片地址
	private String getrefundPicUrl() {
		String basePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
		/*FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
		String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();*/
		String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
		return imageServerUrl+basePath;
	}


	public void setAddressOperationService(IAddressOperationService addressOperationService) {
		this.addressOperationService = addressOperationService;
	}

}
