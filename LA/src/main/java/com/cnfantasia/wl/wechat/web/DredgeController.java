package com.cnfantasia.wl.wechat.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommDataUpgradeService;
import com.cnfantasia.server.api.dredge.entity.AddDredgeBillParamter;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.session.SessionValueManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.addressCity.service.AddressCityBaseService;
import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;
import com.cnfantasia.server.domainbase.supportBank.service.ISupportBankBaseService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.UserInfoGetter;
import com.wxap.util.MD5Util;
import com.wxap.util.Sha1Util;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dredge")
public class DredgeController extends BaseController {

	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IHttpClient simpleHttpClient;
	
	@Resource
	private IAddressProvinceBaseService addressProvinceBaseService;
	
	@Resource
	private DredgeService dredgeService;
	
	/**
	 * 个人中心
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/personalCenter.do")
	public ModelAndView personalCenter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}
		
//		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
//		LoginHelper.loginAPI(simpleHttpClient, request, user);
		
//		LoginHelper.login(request, simpleHttpClient).toString();
//		Regist3rdResponse regist3rdResponse = (Regist3rdResponse) request.getSession().getAttribute("regist3rdResponse");
		
		request.setAttribute("regist3rdResponse", LoginHelper.getRegist3rdResponseFromSession(request));
		
		/* 改为红点提醒了, 所以注释以下代码
		EbuyOrder ebuyOrder  = new EbuyOrder();
		ebuyOrder.setBuyerId(userId);
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.EBuy_Product);
		ebuyOrder.setSubChannel(HeaderConstant.SubChannelId.Jfq_Light_App+"");
		ebuyOrder.setSys0DelState(0);
		request.setAttribute("ebuyOrderCount", ebuyOrderBaseService.getEbuyOrderCount(MapConverter.convertBean(ebuyOrder)));//订单数量
		
		 * DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.settUserFId(userId);
		dredgeBill.setSys0DelState(0);
		request.setAttribute("dredgeBillCount", dredgeBillBaseService.getDredgeBillCount(MapConverter.convertBean(dredgeBill)));//疏通单数量 
		 */		
		
		//查询红点
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String [] modelCodeList = {RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_CONFIRMED, RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_SETPAYAMOUNT};
			paramMap.put("modelCodeList", JSON.toJSON(modelCodeList));
			JsonResponse jsonResponse = simpleHttpClient.submitSimple("/redpoint/qryRedpointInfoMulti.json", paramMap, LoginHelper.prepareReqHeader(request));
			JSONArray array = ((JSONObject)jsonResponse.getDataValue()).getJSONArray("list");
			for (int i = 0; array != null && i < array.size(); i++) {
				//是否点击，false未点，true已点
				boolean clickStatus = ((JSONObject)array.get(i)).getBoolean("clickStatus");
				request.setAttribute("clickStatus", clickStatus ? 1 : 0);
				if(!clickStatus)
					break;
			}
		}

		return new ModelAndView("/dredge/personalCenter");
	}
	
	/**
	 * 点击红点
	 * @param request
	 * @return
	 */
	@RequestMapping("/clickRedpointInfo.json")
	@ResponseBody
	public JsonResponse clickRedpointInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数

		//2.交互
		String [] modelCodeList = {RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_CONFIRMED, RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_SETPAYAMOUNT};
		for(int i = 0; i <modelCodeList.length; i++){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("modelCode", modelCodeList[i]);
			jsonResponse = simpleHttpClient.submitSimple("/redpoint/clickRedpointInfo.json", paramMap, LoginHelper.prepareReqHeader(request));
		}		
		
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 获取维修服务的首页类别
	 * @param request
	 * @return
	 */
	@RequestMapping("/listWeiXiuHomeType.do")
	public ModelAndView listWeiXiuHomeType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginHelper.shareSessionKey(request, response, simpleHttpClient);
		
		//LoginHelper.login(request, simpleHttpClient);
		
		/*OperationHomeSupplyType ost = new OperationHomeSupplyType();
		ost.setCode("weixiu");
		ost.setSys0DelState(0);

		ISysParamManager sysParamManager =(ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH);
		IFileServerService fileServerService = (IFileServerService) CnfantasiaCommbusiUtil.getBeanManager("fileServerService");
		
		List<OperationHomeSupplyType> ostList = operationHomeSupplyTypeBaseService.getOperationHomeSupplyTypeByConditionDim(MapConverter.convertBean(ost));
		for(OperationHomeSupplyType ost2: ostList){
			ost2.setIconName(fileServerService.getAccessUrl(iconBasePath + ost2.getIconName(), ost2));
		}
		request.setAttribute("resList", ostList);*/
		
		IAddressProvinceBaseService addressProvinceBaseService = (IAddressProvinceBaseService) CnfantasiaCommbusiUtil.getBeanManager("addressProvinceBaseService");
		request.setAttribute("pcbList", addressProvinceBaseService.getAddressProvinceByCondition(null));
		
		return new ModelAndView("/dredge/maintainIndex");
	}



	/**
	 * 获取疏通服务类型
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDredgeTypeList.json")
	public JsonResponse getDredgeTypeList(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/getDredgeTypeList.json", CommonController.getParameterMap(request));
		return jsonResponse;
	}

	@RequestMapping(value = "/productDetail.html", method = RequestMethod.GET)
	public String dredgeProductDetailPage(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String subChannelId = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		String version = HeaderManager.getVersion();
		String sessionKey = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY);
		logger.debug("listWeiXiuHomeType.do-sessionKey:" + sessionKey + ", version:" + version + ",subChannelId:" + subChannelId + ", sessionId:" +  request.getSession().getId());
		
		if(subChannelId == null && version == null) {
			subChannelId = (String) request.getSession().getAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		}
		String phone = ParamUtils.getString(request,"phone", null);
		if(!StringUtils.isEmpty(phone)) {
			SessionValueManager.putRecommendUserPhone(request);
		}
		
		if (!"1".equals(subChannelId) && !"2".equals(subChannelId)) {//1和2是iOS和android渠道，其它就认为是轻应用过来的
			String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/productDetail.html" + "?" + request.getQueryString();
			currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
			Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

			if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
				request.getSession().setAttribute("isLogin", true);
				String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
				return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
			}
			logger.debug("listWeiXiuHomeType.do-weixin");
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
			setIsSubscribe(request, response);
		}
		if(sessionKey != null) {
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, version);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, sessionKey);
			request.getSession().setAttribute(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, subChannelId);
		}

		//记录用户openId的请求商品记录
		BigInteger dredgeProductId = ParamUtils.getBigInteger(request, "productId", null);
		if (dredgeProductId != null) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			if (user.getOpenid() != null) {
				RedisCacheHandler.set(RedisCachePrefix.Dredge_Product_View_OpenId + user.getOpenid(), dredgeProductId.toString(), 3 * 60);
			}
		}

		return "/dredge/productDetail";
	}
	
	/**
	 * 添加疏通服务工单
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/addDredgeBill.do")
	@ResponseBody
	public JsonResponse addDredgeBill(HttpServletRequest request, HttpServletResponse response) {
		logger.info("addDredgeBill.do-----------");
		logger.info("param is:" + CommonController.getParameterMap(request));
		JsonResponse jsonResponse = new JsonResponse();
		AddDredgeBillParamter addDredgeBillParamter = new AddDredgeBillParamter();
		try{
			//0.搜集房间相关的 参数
			//BigInteger groupBuildingId = ParamUtils.getBigInteger(request, "groupBuildingId", null);
//			String groupBuildingName = ParamUtils.getString(request, "groupBuildingName", null);
			Integer gbSignStatus = ParamUtils.getInteger(request, "gbSignStatus", null);
			
//			String buildingName = request.getParameter("buildingName");
//			String unitName = request.getParameter("unitName");
//			String roomNum = request.getParameter("roomNum");
			
			String dredgeAddress = request.getParameter("dredgeAddress");

			if (dredgeAddress == null) {
				String cityName = "";
				if (gbSignStatus == null) {//用户添加的小区
					jsonResponse = simpleHttpClient.submitSimple("/room/addGroupBuildingAndRoom.json", CommonController.getParameterMap(request),
							LoginHelper.prepareReqHeader(request));
					if (!jsonResponse.getStatus().equals("0000")) {
						return jsonResponse;
					}
					cityName = ((JSONObject)jsonResponse.getDataValue()).getString("city");//行政区要先带出来
					String totalAddress = ((JSONObject)jsonResponse.getDataValue()).getString("totalAddress");//完整地址
					dredgeAddress = totalAddress.substring(totalAddress.indexOf(cityName));
				} else if (gbSignStatus == 1) {//已经签约小区
					jsonResponse = simpleHttpClient.submitSimple("/room/addVirtualRoomOnly.json", CommonController.getParameterMap(request),
							LoginHelper.prepareReqHeader(request));
					if (!jsonResponse.getStatus().equals("0000")) {
						return jsonResponse;
					}
					cityName = ((JSONObject)jsonResponse.getDataValue()).getString("city");//行政区要先带出来
					String totalAddress = ((JSONObject)jsonResponse.getDataValue()).getString("totalAddress");//完整地址
					dredgeAddress = totalAddress.substring(totalAddress.indexOf(cityName));
				} else if (gbSignStatus == 0) {//未签约小区 
					jsonResponse = simpleHttpClient.submitSimple("/room/addBuildingAndRoom.json", CommonController.getParameterMap(request),
							LoginHelper.prepareReqHeader(request));
					if (!jsonResponse.getStatus().equals("0000")) {
						return jsonResponse;
					}
					cityName = ((JSONObject)jsonResponse.getDataValue()).getString("city");//行政区要先带出来
					String totalAddress = ((JSONObject)jsonResponse.getDataValue()).getString("totalAddress");//完整地址
					dredgeAddress = totalAddress.substring(totalAddress.indexOf(cityName));
				}
				
				SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
				//因为增加了房间，所以需要重新登录以更新Session中的Regist3rdResponse信息
				request.getSession().setAttribute("regist3rdResponse",null);//清空后，才能强制登录API，否则取的是缓存里的数据，默认房间还是刚自动分配的房间
				LoginHelper.loginAPI(simpleHttpClient, request, user);
			}
			addDredgeBillParamter.setDredgeAddress(dredgeAddress);
			
			//1.搜集参数
			addDredgeBillParamter.setDredgeTypeId(ParamUtils.getBigInteger(request, "dredgeTypeId", null));
			addDredgeBillParamter.setSubTypeId(ParamUtils.getBigInteger(request, "subTypeId", null));
	
			addDredgeBillParamter.setDredgeContent(request.getParameter("dredgeContent"));
			addDredgeBillParamter.setExpectDate(ParamUtils.getDate(request, "expectDate", null, "yyyy-MM-dd HH:mm").getTime());
			addDredgeBillParamter.setTel(request.getParameter("tel"));
			addDredgeBillParamter.setReferrerMobile(request.getParameter("referrerMobile"));
			addDredgeBillParamter.setBlockId(ParamUtils.getBigInteger(request, "addressBlockId", null));
			List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
			addDredgeBillParamter.setPicList(picList);

			List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
			addDredgeBillParamter.setProductIdQtyList(productIdQtyList);
			addDredgeBillParamter.setLinkName(request.getParameter("linkName"));
			List<DredgeProductSpecEntity> specList = JSON.parseArray(request.getParameter("specList"), DredgeProductSpecEntity.class);
			addDredgeBillParamter.setProductSpecList(specList);
			addDredgeBillParamter.setDredgeProductId(new BigInteger(request.getParameter("dredgeProductId")));

			Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
			if(regist3rdResponse == null || regist3rdResponse.getUserId() == null) {
				SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
				LoginHelper.loginAPI(simpleHttpClient, request, user);
				//LoginHelper.login(request, simpleHttpClient);
				regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
				regist3rdResponse.getDefaultRoomInfo().getBlock();
			}
			
			//用户Id
			BigInteger userId = new BigInteger(regist3rdResponse.getUserId());
			addDredgeBillParamter.setUserId(userId);
			BigInteger roomId = new BigInteger(regist3rdResponse.getDefaultRoomId());
			addDredgeBillParamter.setRoomId(roomId);
	
			//2.交互 */
			if(addDredgeBillParamter.getBlockId()==null){
				addDredgeBillParamter.setBlockId(dredgeService.qryBlockId_by_roomId(roomId));
			}
			addDredgeBillParamter.setSubmitChannel(2);

			Map<String, Object> resMap = dredgeService.addDredgeBill(addDredgeBillParamter);
			jsonResponse.putDataAll(resMap);
			//3.结果处理
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			jsonResponse.setStatus("0002");
			jsonResponse.setMessage("提交订单失败！");
		}
		return jsonResponse;
	}

	@RequestMapping("/viewSelfBuyProductList.do")
	public ModelAndView viewSelfBuyProductList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
		String operType = ParamUtils.getString(request, "operType", "view");
		String billType = ParamUtils.getString(request, "billType");
		boolean canEdit = ParamUtils.getBooleanNotNull(request, "canEdit", true);

		if(dredgeBillId == null) {
			throw new ValidateRuntimeException("dredgeBillId can't be null");
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dredgeBillId", dredgeBillId);
		paramMap.put("canEdit", canEdit);
		JsonResponse json = simpleHttpClient.submitSimple("/dredge/viewSelfBuyProductList.json", paramMap, LoginHelper.prepareReqHeader(request));
		mav.addObject("dataValue", json.getDataValue());
		mav.addObject("billType", billType);
		if ("view".equals(operType)) {
			mav.setViewName("/dredge/maintain_itemHasSelected");
		} else {
			mav.setViewName("/dredge/maintain_itemEdit");
		}
		return mav;
	}
	
	/**
	 * 查询我的预约单
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMyDredgeBillList.json")
	@ResponseBody
	public JsonResponse queryMyDredgeBillList(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		//1.搜集参数
		int type = ParamUtils.getInt(request, "type", 1);  //type=1已预约，type=2已结束
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);

		//2.交互

		//用户Id
		//LoginHelper.login(request, simpleHttpClient);
		
//		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
//		String login3rdRes = LoginHelper.loginAPI(simpleHttpClient, request, user);
//		Regist3rdResponse regist3rdResponse = JSON.parseObject(login3rdRes, Regist3rdResponse.class);
		
		Integer page = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE));
		Integer pageNum = Integer.parseInt(request.getParameter(PageModel.PageKey.PAGE_NUM));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("fromLA", true);
		params.put(PageModel.PageKey.PAGE, page);
		params.put(PageModel.PageKey.PAGE_NUM, pageNum);
		
		//3.结果处理
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/qryMyDredgeBillList.json", params, LoginHelper.prepareReqHeader(request));
		return jsonResponse;
	}
	
	/**
	 * 查询预约单详情
	 * @param request
	 * @return
	 */
	
	@Deprecated //以后访问用qryDredgeBillDetail.do接口，因为只有do接口才会被拦截
	@RequestMapping("/qryDredgeBillDetail.json")
	public ModelAndView qryDredgeBillDetail(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String id = request.getParameter("id");
		String billType = request.getParameter("billType");

		//2.交互
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("billType", billType);
		jsonResponse = simpleHttpClient.submitSimple("/dredge/qryDredgeBillOrPropertyRepairDetail.json", params);
		
		//3.结果处理
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/myAppointmentDetails");
	}
	
	/**
	 * 查询预约单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryDredgeBillDetail.do")
	public ModelAndView qryDredgeBillDetailByDo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String id = request.getParameter("id");
		String billType = request.getParameter("billType");
		
		//2.交互
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("billType", billType);
		jsonResponse = simpleHttpClient.submitSimple("/dredge/qryDredgeBillOrPropertyRepairDetail.json", params);
		jsonResponse.putData("cpList", dredgeService.getCommentsPointList());
		
		//3.结果处理
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/myAppointmentDetails");
	}
	
	
	/**
	 * 取消预约单
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelDredge.json")
	@ResponseBody
	public JsonResponse cancelDredge(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		String cancelDesc = request.getParameter("cancelDesc");

		//2.交互
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("cancelDesc", cancelDesc);
		
		jsonResponse = simpleHttpClient.submitSimple("/dredge/cancelDredge.json", params, LoginHelper.prepareReqHeader(request));

		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 确认缴疏通服务费
	 */
	@RequestMapping("/qryOpenDredgeService.html")
	@ResponseBody
	public JsonResponse qryOpenDredgeService_by_cstId_and_cityName(HttpServletRequest request) {
		
		String cityName = request.getParameter("cityName");
		
		//2.交互
		if(cityName ==null){
			throw new ValidateRuntimeException("cityName can't be null. ");
		}
		
		AddressCity city = new AddressCity();
		city.setName(cityName);
		
		AddressCityBaseService addressCityBaseService = (AddressCityBaseService)CnfantasiaCommbusiUtil.getBeanManager("addressCityBaseService");
		List<AddressCity> cityList = addressCityBaseService.getAddressCityByConditionDim(MapConverter.convertBean(city));
		if (cityList.size() == 0) {
			throw new ValidateRuntimeException("can't find city by name " + cityName);
		} else if (cityList.size() > 1) {
			throw new ValidateRuntimeException("find more than one city by name " + cityName);
		}
		
		request.getSession().setAttribute("cityId", cityList.get(0).getId());
		request.getSession().setAttribute("cityName", cityList.get(0).getName());
		
		JsonResponse jsonResponse = new JsonResponse();
		int isOpenDredgeService = dredgeService.qryOpenDredgeService_by_cstId_and_cityName(CommonController.getParameterMap(request));
		
		jsonResponse.putData("isOpenDredgeService", isOpenDredgeService);
		return jsonResponse;
	}
	/**
	 * 确认缴疏通服务费
	 */
	@RequestMapping("/confirmPayDredgeBill.json")
	@ResponseBody
	public JsonResponse confirmPayBill(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger dredgeBillId = new BigInteger(request.getParameter("dredgeBillId"));
		//BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		Long hbAmount = null;
		if (!StringUtils.isEmpty(request.getParameter("hbAmount"))) {
			Double hbAmountDouble = null;
			hbAmountDouble = Double.parseDouble(request.getParameter("hbAmount"));//粮票金额
			hbAmount = PriceUtil.multiply100(hbAmountDouble);
		}
		Set<BigInteger> couponIdSet = null;
		if (!StringUtils.isEmpty(request.getParameter("couponIdList"))) {//优惠的Id列表
			List<BigInteger> couponIdList = JSON.parseArray(request.getParameter("couponIdList"), BigInteger.class);
			couponIdSet = new HashSet<BigInteger>();
			couponIdSet.addAll(couponIdList);
		}

		//2.交互
		Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
		EbuyOrder order = dredgeService.confirmPayBill(dredgeBillId, hbAmount, couponIdSet, subChannelId);

		//3.结果处理
		jsonResponse.putData("orderId", order.getId()); //订单ID
		jsonResponse.putData("orderShouldPayAmount", order.getAmount()); //订单ID
		return jsonResponse;
	}
	
	
	/**
	 * 预提交订单
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping("/submitOrderMulti.do")
	@ResponseBody
	public String submitOrderMulti(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建参数队列    
		Map<String, Object> params = new HashMap<String, Object>();
		//先提交订单
		String orderId = request.getParameter("orderId");

		if (request.getParameter("shouldPayAmt") != null) {// "用户需要支付现金额"
			double shouldPayAmt = Double.parseDouble(request.getParameter("shouldPayAmt"));
			if (shouldPayAmt < 0.000001) {// "用户不需要支付现金"
				params.clear();
				params.put("orderId", orderId);
				params.put("message", "user do't use cash to pay");
				return JSON.toJSONString(params);
			}
		}
	
		params.clear();
		params.put("orderId", orderId);
		SnsUserinfo snsUser = LoginHelper.getSnsUserInfo(request, response);
		params.put("openid", snsUser.getOpenid());

		//预支付
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/weiXinPayJieFangQuLightApp/prePayRequest.json", params,
				LoginHelper.prepareReqHeader(request));
		logger.info("prepay's jsonResponse is: " + jsonResponse.getDataValue().toString());

		//正式支付所需参数
		JSONObject jsonObject = JSON.parseObject(jsonResponse.getDataValue().toString());
		SortedMap<String, Object> signParams = new TreeMap<String, Object>();
		signParams.put("appId", WeChatConfig.APPID);
		signParams.put("nonceStr", Sha1Util.getNonceStr());
		signParams.put("timeStamp", Sha1Util.getTimeStamp());
		signParams.put("package", "prepay_id=" + jsonObject.get("prepay_id"));
		signParams.put("signType", "MD5");
		String sign = MD5Util.createMD5Sign("UTF-8", signParams);
		signParams.put("paySign", sign);
		
		signParams.put("orderId", orderId);//添加订单号（冗余信息）
		logger.info("weixin pay signParams is: " + signParams);
		return JSON.toJSONString(signParams);
	}
	
	/**
	 * 提交订单页面
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/maintainYuyue.do")
	public ModelAndView maintainYuyue(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<AddressProvince> pcbList = addressProvinceBaseService.getAddressProvinceByCondition(null);
		request.setAttribute("pcbList", pcbList);
		
		Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);
		
		if(request.getSession().getAttribute("cityName") == null) {//如果会话中没有城市，设为默认城市为深圳市
			request.getSession().setAttribute("cityId", 233);
			request.getSession().setAttribute("cityName", "深圳市");
		}
		
		if (regist3rdResponse.getDefaultRoomInfo() != null) {//有门牌
			String city = regist3rdResponse.getDefaultRoomInfo().getCity();
			String totalAddress = regist3rdResponse.getDefaultRoomInfo().getTotalAddress();
			int cityIndex = totalAddress.indexOf(city);
			String dredgeAddress = totalAddress.substring(cityIndex + city.length());
			if (regist3rdResponse.getDefaultRoomInfo().getCity().contains(request.getSession().getAttribute("cityName").toString())) {
				request.setAttribute("dredgeAddress", dredgeAddress);
				return new ModelAndView("/dredge/maintainYuyue_noRoom");
			} else {//不同城市 
				request.setAttribute("dredgeAddress", dredgeAddress);
				return new ModelAndView("/dredge/maintainYuyue_noRoom");
			}
		} else {//无门牌
			return new ModelAndView("/dredge/maintainYuyue_noRoom");
		}
	}
	
	/**
	 * 绑定手机
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/bindPhone.json")
	@ResponseBody
	public JsonResponse bindPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mobile = request.getParameter("mobile");
		if(StringUtils.isEmpty(mobile)){
			throw new ValidateRuntimeException("mobile can't be null. ");
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		
		String userId = LoginHelper.getRegist3rdResponseFromSession(request).getUserId();
		
		ICommDataUpgradeService commDataUpgradeService = (ICommDataUpgradeService) CnfantasiaCommbusiUtil.getBeanManager("commDataUpgradeService");
		commDataUpgradeService.executeBindActionForPhone(new BigInteger(userId), mobile);
		return jsonResponse;
	}
	
	@Resource
	ISupportBankBaseService supportBankBaseService;
	
	
	/**
	 * 绑定手机号
	 * @param request
	 * @return
	 */
	@RequestMapping("/bindMobilePhone.do")
	public ModelAndView bindMobilePhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		return new ModelAndView("/dredge/bindingPhoneNum.jsp");
	}
	/**
	 * 绑定银行卡
	 * @param request
	 * @return
	 */
	@RequestMapping("/bindingBankCard.do")
	public ModelAndView bindingBankCard(HttpServletRequest request) {
		List<AddressProvince> pcbList = addressProvinceBaseService.getAddressProvinceByCondition(null);
		request.setAttribute("pcbList", pcbList);
		
		List<SupportBank> supportBankList = supportBankBaseService.getSupportBankByCondition(null);
		request.setAttribute("bankList", supportBankList);
		
		return new ModelAndView("/dredge/bindingBankCard");
	}
	
	/**
	 * 推荐人-查询我的账户
	 */
	@RequestMapping("/qryMyAccount.do")
	public ModelAndView qryMyAccount_recommend(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/recommend/qryMyAccount.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/recommendAwardList");
	}
	
	/**
	 * 推荐人-查询我的账户
	 */
	@RequestMapping("/qryBankCardList.do")
	public ModelAndView qryBankCardList_recommend(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/master/qryBankCardList.json", null, LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/bankCard");
	}
	
	/**
	 * 推荐人-申请提款, 默认提现所有可提现的工单
	 */
	@RequestMapping("/applyWithdraw.do")
	public ModelAndView applyWithdraw_recommend(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/recommend/applyWithdraw.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/cashOutDetails");
	}
	
	/**
	 * 推荐人-申请列表 
	 */
	@RequestMapping("/viewWithdrawList.do")
	public ModelAndView viewWithdrawList_recommend(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/recommend/viewWithdrawList.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/cashOutRecordList");
	}
	
	/**
	 * 推荐人-申请详情 
	 */
	@RequestMapping("/viewWithdrawDetail.do")
	public ModelAndView viewWithdrawDetail_recommend(HttpServletRequest request) {
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/recommend/viewWithdrawDetail.json", getParameterMap(request), LoginHelper.prepareReqHeader(request));
		request.setAttribute("dataValue", jsonResponse.getDataValue());
		return new ModelAndView("/dredge/cashOutDetails");
	}
	
	/**
	 * 我的推荐 
	 */
	@RequestMapping("/myAppointment.do")
	public ModelAndView toMyAppointment(HttpServletRequest request) {
		return new ModelAndView("/dredge/myAppointment");
	}
	
	/**
	 * 我的粮票
	 */
	@RequestMapping("/myRedEnvelope.do")
	public ModelAndView toMyRedEnvelope(HttpServletRequest request) {
		return new ModelAndView("/tickets/index");
	}

	/**
	 * 跳添加维修单页面
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/maintainTypelist.do")
	public ModelAndView toMaintainTypelist(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		String parentTypeId = ParamUtils.getString(request, "parentTypeId", null);
		String city = ParamUtils.getString(request, "city", null);
		
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}
		
		setIsSubscribe(request, response);
		ModelAndView modelAndView = new ModelAndView("/dredge/maintainTypelist");
		modelAndView.addObject("myNumber", number);
		modelAndView.addObject("myPassword", password);
		modelAndView.addObject("parentTypeId", parentTypeId);
		modelAndView.addObject("city", city);
		return modelAndView;
	}
	
	/**
	 * 跳添加维修单页面
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/getTypelist.do")
	@ResponseBody
	public JsonResponse getMaintainTypelist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}*/
		return simpleHttpClient.submitSimple("/dredge/getDredgeTypeListNew.json", CommonController.getParameterMap(request));
	}
	
	/**
	 * 获取商品列表
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping("/getProductList.do")
	@ResponseBody
	public JsonResponse getProductList(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException {
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}
		
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/qryDredgeProductList.json", CommonController.getParameterMap(request));
		
		return jsonResponse;
	}
	
	/**
	 * 设置用户是否已经关注公众号
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private void setIsSubscribe(HttpServletRequest request, HttpServletResponse response) throws IOException, ClientProtocolException {
		//用户是否订阅
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		if (user.getOpenid() != null) {
			int subscribe = JSON.parseObject(UserInfoGetter.getUserInfo(user.getOpenid())).getInteger("subscribe");
			request.setAttribute("subscribe", subscribe);
		} else {
			request.setAttribute("subscribe", 0);
		}
	}
}
