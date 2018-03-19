package com.cnfantasia.wl.wechat.web;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.entity.Regist3rdResponse;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.session.SessionValueManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressProvince.service.IAddressProvinceBaseService;
import com.cnfantasia.server.domainbase.ebuyLog.entity.EbuyLog;
import com.cnfantasia.server.domainbase.ebuyLog.service.IEbuyLogBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductPic.service.IEbuyProductPicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.ms.notice.dto.DataValue;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.annotation.CheckLogin;
import com.cnfantasia.server.ms.pub.session.SessionManager;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.EbuyProductParametersNew;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.JsapiTicketGetter;
import com.cnfantasia.wl.wechat.util.Sign;
import com.cnfantasia.wl.wechat.util.UserInfoGetter;

@Controller
@RequestMapping("/product")
public class EbuyProductController extends BaseController {

	@Resource
	private IHttpClient simpleHttpClient;

	@Resource
	private IEbuyLogBaseService ebuyLogBaseService;

	@Resource
	private ISysParamManager sysParamManager;

	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private ILimitBuyService limitBuyService;

	@Resource
	private IFileServerService fileServerService;

	@Resource
	private IEbuyProductPicBaseService ebuyProductPicBaseService;
	
	@Resource
	private IEbuyProductShelfBaseService ebuyProductShelfBaseService;
	
	private final Object lock = new Object();
	
	private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");


	/**
	 * 商品首页
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/index.do")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String number = ParamUtils.getString(request, "myNumber", null);
		String password = ParamUtils.getString(request, "myPassword", null);
		if(StringUtils.isEmpty(number) || StringUtils.isEmpty(password)) {
			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);
		} else {
			LoginHelper.login(request, simpleHttpClient);
		}

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryProductTypes.json", null);
		DataValue dataValue = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), DataValue.class);
		List<EbuyProductType> productTypeList = JSONArray.parseArray(dataValue.getList(), EbuyProductType.class);

		request.setAttribute("productTypeList", productTypeList);
		Map<String, Object> params;
		for (int i = 0; i < productTypeList.size(); i++) {
			params = new HashMap<String, Object>();
			params.put("productTypeId", productTypeList.get(i).getId());
			params.put("page", 1);
			params.put("pageNum", 40); //wenfq 蒙蒙说还有10未显示，暂时每类多取40个
			JsonResponse jsonResponseP = simpleHttpClient.submitSimple("/ebuy/qryProductList.json", params);
			dataValue = JSONObject.parseObject(String.valueOf(jsonResponseP.getDataValue()), DataValue.class);
			request.setAttribute("productList" + (i + 1), JSONArray.parseArray(dataValue.getList()));
		}

		// 购物车商品数量
		request.setAttribute("productCount", simpleHttpClient.submitSimple("/ebuy/qryBuyCarProductCount.json", null, LoginHelper.prepareReqHeader(request))
				.getDataValue());

		addEbuyLog(request);

		setIsSubscribe(request, response);

		IAddressProvinceBaseService addressProvinceBaseService = (IAddressProvinceBaseService) CnfantasiaCommbusiUtil.getBeanManager("addressProvinceBaseService");
		request.setAttribute("pcbList", addressProvinceBaseService.getAddressProvinceByCondition(null));

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/index");
		return view;
	}

	/**
	 * 查询解放区体验店
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewStore.do")
	public String qryExperienceStore(HttpServletRequest request, HttpServletResponse response, BigInteger storeId) throws IOException {
		String jfqsource= request.getParameter("jfqsource"); //jfqsource=jfqapp, 表示从app那边请求过来的
		if(StringUtils.isEmpty(jfqsource)) {
			String currentUrl = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "product/viewStore.do" + "?" + request.getQueryString();
			currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
			Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");

			if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
				//统计当天浏览量
				addViewNum(storeId);
		        
				request.getSession().setAttribute("isLogin", true);
				String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
				return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
			}

			SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
			LoginHelper.loginAPI(simpleHttpClient, request, user);

			setIsSubscribe(request, response);

			//记录用户openId的是未关注第一次访问检验店
			logger.info("user openId: " + user.getOpenid());
			logger.info("subscribe: " + request.getAttribute("subscribe"));
			if (user.getOpenid() != null) {
				Regist3rdResponse regist3rdResponse = (Regist3rdResponse) request.getSession().getAttribute("regist3rdResponse");
				RedisCacheHandler.set(RedisCachePrefix.JFQ_Store_View_OpenId + user.getOpenid(), regist3rdResponse.getUserId(), 20);
			}
		} else {
			addViewNum(storeId);
		}

		{//限时促销的
			List<LimitBuyInfo> limitBuyList = limitBuyService.getLimitBuyListByMerchant(storeId, 1, 1, null);
			request.setAttribute("limitBuyList", limitBuyList);
		}

		{//爆款商品
			List<EbuyProduct> epList = limitBuyService.getHotSaleProduct(storeId);
			String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
			for (EbuyProduct ep : epList) {
				BigInteger epId = ebuyProductShelfBaseService.getEbuyProductShelfBySeqId(ep.getId()).gettEbuyProductId();
				EbuyProductPic pic = new EbuyProductPic();
				pic.settEbuyProductFId(epId);

				List<EbuyProductPic> picList = ebuyProductPicBaseService.getEbuyProductPicByCondition(MapConverter.toMap(pic));
				if (picList.size() > 0){
					pic = picList.get(0);
					ep.setPicBaseMini(fileServerService.getAccessUrl(productPicBasePath + pic.getUrlMini(), ep));
					ep.setPicBase(fileServerService.getAccessUrl(productPicBasePath + pic.getUrlBig(), ep));
				}
			}
			request.setAttribute("hotSaleList", epList);
		}

		request.setAttribute("storeId", storeId);
		return "/ebuy/store";
	}

	/**
	 * 楼下店商品列表
	 * @author wenfq 2017-07-10
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryProductList4Store.json")
	@ResponseBody
	public JsonResponse qryProductList4Store(HttpServletRequest request) {
		String detailUrl= "/ebuyV2/qryProdList4ExperienceStore.json";

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse = simpleHttpClient.submitSimple(detailUrl, getParameterMap(request), LoginHelper.prepareReqHeader(request));
		return jsonResponse;
	}
	
	private void addViewNum(BigInteger storeId) {
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
	}

	/**
	 * 商品列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryProductList.do")
	@ResponseBody
	public ModelAndView qryProductList(HttpServletRequest request) {
		String ptId = request.getParameter("ptId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productTypeId", ptId);
		params.put("page", 1);
		params.put("pageNum", 10);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryProductList.json", params);

		ModelAndView view = new ModelAndView();
		view.setViewName("/ebuy/index");
		return view;
	}

	/**
	 * 商品详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/productDetail.do")
	public String productDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//处理LA分享的一些问题
		String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL)
				+ "product/productDetail.do" + "?" + request.getQueryString();
		currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		//request.getSession().setAttribute("isLogin", null);
		if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户
			request.getSession().setAttribute("isLogin", true);

			logger.info("the currentUrl url is: " + currentUrl);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI", URLEncoder.encode(currentUrl, "utf-8"));
			logger.info("wechatRedirectUrl is: " + wechatRedirectUrl);
			return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
		}

		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		if (userId != null) {
			SessionValueManager.putRecommendUserId(request);
		}

		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);

		String ptId = request.getParameter("ptId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", ptId);
		params.put("utm_campaign", ParamUtils.getString(request, "utm_campaign", null));
		params.put("utm_source", ParamUtils.getString(request, "utm_source", null));
		params.put("utm_medium", ParamUtils.getString(request, "utm_medium", null));
		params.put("utm_content", ParamUtils.getString(request, "utm_content", null));

		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryProductDetail.json", params);
		JSONObject product = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()));

		request.setAttribute("product", product);

		jsonResponse = simpleHttpClient.submitSimple("/ebuy/qryProductParameters.json", params);
		DataValue dataValue = JSONObject.parseObject(String.valueOf(jsonResponse.getDataValue()), DataValue.class);
		List<EbuyProductParametersNew> ppList = JSONArray.parseArray(dataValue.getList(), EbuyProductParametersNew.class);
		request.setAttribute("ppList", ppList);

		// 购物车商品数量
		request.setAttribute("productCount", simpleHttpClient.submitSimple("/ebuy/qryBuyCarProductCount.json",
				null, LoginHelper.prepareReqHeader(request)).getDataValue());

		//分享商品用的签名信息
		request.setAttribute("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), currentUrl));
		//分享商品详情用的小图片
		String picBase = product.getString("picBase");
		String miniPicBase = null;
		if (!StringUtils.isEmpty(picBase)) {
			miniPicBase = picBase.substring(0, picBase.lastIndexOf(".")) + "/72" + picBase.substring(picBase.lastIndexOf("."));
		}
		request.setAttribute("miniPicBase", miniPicBase);

		setIsSubscribe(request, response);

		addEbuyLog(request);

		return "/ebuy/itemDetails";
	}

	private boolean addEbuyLog(HttpServletRequest request) {
		try{
			Regist3rdResponse regist3rdResponse = LoginHelper.getRegist3rdResponseFromSession(request);

			EbuyLog ebuyLog = new EbuyLog();
			CnfantasiaCommbusiUtil.newStandard(ebuyLog, SEQConstants.t_ebuy_log);
			ebuyLog.setProductId(ParamUtils.getBigInteger(request, "ptId", null));
			ebuyLog.setUserId(new BigInteger(regist3rdResponse.getUserId()));
			ebuyLog.setUtmCampaingn(ParamUtils.getString(request, "utm_campaign", null));
			ebuyLog.setUtmSource(ParamUtils.getString(request, "utm_source", null));
			ebuyLog.setUtmMedium(ParamUtils.getString(request, "utm_medium", null));
			ebuyLog.setUtmContent(ParamUtils.getString(request, "utm_content", null));
			ebuyLogBaseService.insertEbuyLog(ebuyLog);
			return true;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
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

	@RequestMapping("themeProductAd.do")
	@CheckLogin
	public ModelAndView jumpToThemeProductAd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/ebuy/themeProductAd");
		Map<String, Object> params = new HashMap<String, Object>();
		BigInteger advId = ParamUtils.getBigInteger(request,"advId", null);
		params.put("advId", advId);
		JsonResponse json = simpleHttpClient.submitSimple("/ebuyNew/themeProductAd.json", params);
		mav.addObject("json", json);

		SessionValueManager.putRecommendUserId(request);

		return mav;
	}

	@RequestMapping("/storeSearch.html")
	public String storeSearchPage() {
		return "/ebuy/storeSearch";
	}

}
