package com.cnfantasia.server.ms.ebuy.web;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnfantasia.server.common.utils.DataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.pub.util.WeChatConfig;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.ms.ebuy.dto.FightgroupProductEntity;
import com.cnfantasia.wl.wechat.WeChatConstant;
import com.cnfantasia.wl.wechat.model.SnsUserinfo;
import com.cnfantasia.wl.wechat.util.JsapiTicketGetter;
import com.cnfantasia.wl.wechat.util.Sign;
import com.cnfantasia.wl.wechat.web.LoginHelper;

@Controller
@RequestMapping("/laGroupPurchase")
public class GroupPurchaseLaController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IHttpClient simpleHttpClient;
	
	/**
	 * 自提点商品
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/productList.do")
	public String qrygroupPurchaseList(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ClientProtocolException, IOException, ParseException {
		/*String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
		if (!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) {//判断此用户是否是未关注或登录的用户 
			logger.info("the currentUrl url is: " + currentUrl);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",currentUrl);
			logger.info("wechatRedirectUrl is: " +  wechatRedirectUrl);
			return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
		}
		
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);*/
		
        String ziTiId=request.getParameter("ziTiId");
		if(ziTiId!=null && !ziTiId.equals("")){
			Map<String, Object> paramap = new HashMap<String, Object>();
			paramap.put("ziTiId", ziTiId);
			JsonResponse ProductResponse = simpleHttpClient.submitSimple("/groupPurchase/laProductList.html", paramap, LoginHelper.prepareReqHeader(request));
			String productObjectList = ((JSONObject)ProductResponse.getDataValue()).getString("fightProductList");
			if(productObjectList == null || productObjectList.equals("[]")){
				return "/ebuyFight/noItem";
			}
	    	//商品列表
			List<FightgroupProductEntity> fightProductList  = JSON.parseArray(productObjectList+"",FightgroupProductEntity.class);
			if(fightProductList!=null && fightProductList.size()==1){
				return "redirect:/laGroupPurchase/laProductDetail.do?productId="+fightProductList.get(0).getFightProductId();
			}
//			request.getSession().setAttribute("ziTiId", ziTiId);
			List<FightgroupProductEntity> proList = new ArrayList<FightgroupProductEntity>();
			List<FightgroupProductEntity> preSellList = new ArrayList<FightgroupProductEntity>();
			Date now = new Date();
			for (FightgroupProductEntity fightgroupProductEntity : fightProductList) {
				boolean isPre = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fightgroupProductEntity.getStartDate()).getTime() > now.getTime();
				if (isPre) {
					preSellList.add(fightgroupProductEntity);
				} else {
					proList.add(fightgroupProductEntity);
				}
			}
			if (!DataUtil.isEmpty(preSellList)) {
				Collections.reverse(preSellList);
			}
			model.addAttribute("proList", proList);
			model.addAttribute("preSellList", preSellList);
	    	model.addAttribute("dataValue", ProductResponse.getDataValue());
		}
    	return "/ebuyFight/groupBuyingList";
	}
	
	@RequestMapping("/ziTiDianList.do")
	public String ZiTiDianList(HttpServletRequest request,ModelMap model){
		String ziTiId = (String)request.getSession().getAttribute("ziTiId");
		if(ziTiId!=null && !ziTiId.equals("")){
			return "redirect:/laGroupPurchase/productList.do?ziTiId="+ziTiId;
		}else{
			JsonResponse zitiDianResponse = simpleHttpClient.submitSimple("/groupPurchase/ziTiDianList.html", null, LoginHelper.prepareReqHeader(request));
			String zitiDianStr = ((JSONObject)zitiDianResponse.getDataValue()).getString("zitiList");
			List<EbuyFightSupplyMerchant> zitiList = JSON.parseArray(zitiDianStr+"",EbuyFightSupplyMerchant.class);
			model.addAttribute("zitiList", zitiList);
			return "/ebuyFight/groupBuyingAddressList";
		}
	}
	
	/**
	 * 轻应用拼购商品详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/laProductDetail.do")
	public String qryLaGroupPurchaseDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ClientProtocolException, IOException{
//		String currentUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
		String currentUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "laGroupPurchase/laProductDetail.do" + "?" + request.getQueryString();;
		currentUrl = currentUrl.replace("code", "codeTest"); //由于二次转发，code参数会造成不登录，后台报异常。快速这样先解决。
//		String currentUrl = request.getRequestURL().toString();
		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		
		if ((!LoginHelper.isLoginUser(request) && request.getParameter("code") == null) || isLogin == null) {//判断此用户是否是未关注或登录的用户 
			request.getSession().setAttribute("isLogin", true);
			
			logger.info("the currentUrl url is: " + currentUrl);
			String wechatRedirectUrl = WeChatConstant.OAuth2_URL.replace("APPID", WeChatConfig.APPID).replace("REDIRECT_URI",currentUrl);
			logger.info("wechatRedirectUrl is: " +  wechatRedirectUrl);
			return "redirect:" + wechatRedirectUrl; //重定向到微信授权页
		}
		
		
		SnsUserinfo user = LoginHelper.getSnsUserInfo(request, response);
		LoginHelper.loginAPI(simpleHttpClient, request, user);
		
		Map<String, Object> paramap = new HashMap<String, Object>();
		BigInteger productId= ParamUtils.getBigInteger(request, "productId", null);
		paramap.put("productId", productId);
		JsonResponse ProductDetail = simpleHttpClient.submitSimple("/groupPurchase/laProductDetail.html", paramap, LoginHelper.prepareReqHeader(request));
		String phone = ((JSONObject)ProductDetail.getDataValue()).getString("phone");
		String picserverUrl = ((JSONObject)ProductDetail.getDataValue()).getString("picserverUrl");
		String fightProduct = ((JSONObject)ProductDetail.getDataValue()).getString("fightProduct");
		FightgroupProductEntity fightProductJson = JSON.parseObject(fightProduct, FightgroupProductEntity.class);
		model.addAttribute("phone", phone);
		model.addAttribute("picserverUrl", picserverUrl);
		model.addAttribute("endTime", fightProductJson.getExpireDate());
		model.addAttribute("fightProduct", fightProductJson);
		model.addAttribute("picList",fightProductJson.getTitlePics());

		//分享给朋友或朋友圈, 需要签名
		request.setAttribute("signInfo", Sign.sign(JsapiTicketGetter.getJsapiTicket(), currentUrl));
		if(fightProductJson.getFightStatus() == 1 || fightProductJson.getFightStatus() == 1){
			return "/ebuyFight/groupBuying";
		} else if (fightProductJson.getFightStatus() == 0) {
			return "/ebuyFight/groupBuyingPresell";
		} else {
			return "/ebuyFight/groupBuyingEnd";
		}
	}
	
	/**
	 * 轻应用图文详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/qryLaProIntroduce.do")
	public String qryLaProIntroduce(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		BigInteger productId= ParamUtils.getBigInteger(request, "productId", null);
		paramMap.put("productId", productId);
		JsonResponse ProductDetail = simpleHttpClient.submitSimple("/groupPurchase/qryLaProIntroduce.html", 
				paramMap, LoginHelper.prepareReqHeader(request));
		model.addAttribute("dataValue", ProductDetail.getDataValue());
//		String picList = ((JSONObject)ProductDetail.getDataValue()).getString("picList");
//		String parameteList = ((JSONObject)ProductDetail.getDataValue()).getString("parameteList");
//		String picserverUrl = ((JSONObject)ProductDetail.getDataValue()).getString("picserverUrl");
//		List<EbuyProductParameters> parameters = null;
//		List<EbuyProductIntroducePic> piclist = null;
//		if(parameteList!=null && !parameteList.equals("[]") && !parameteList.equals("")){
//			parameters = JSON.parseArray(parameteList+"",EbuyProductParameters.class);
//		}
//		if(picList!=null && !picList.equals("[]") && !picList.equals("")){
//			piclist = JSON.parseArray(picList+"",EbuyProductIntroducePic.class);
//		}
//		model.addAttribute("picList", piclist);
//		model.addAttribute("parameteList", parameters);
//		model.addAttribute("picserverUrl", picserverUrl);
		return "/ebuyFight/groupBuyingDetails";
	}

	
//	@RequestMapping("/testlogin.html")
//	@ResponseBody
//	public String testLogin(HttpServletRequest request,ModelMap model){
//		LoginHelper.login(request, simpleHttpClient);
//		return "登录成功";
//	}
}
