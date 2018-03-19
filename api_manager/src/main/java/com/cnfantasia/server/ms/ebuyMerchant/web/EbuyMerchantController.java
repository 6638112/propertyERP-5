package com.cnfantasia.server.ms.ebuyMerchant.web;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.*;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.constant.EbuySupplyMerchantConstant.OwnerAuditStatus;
import com.cnfantasia.server.api.ebuy.entity.EbuyProdType;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchantEntity;
import com.cnfantasia.server.api.ebuyorder.entity.DeliveryMethod;
import com.cnfantasia.server.api.ebuyorder.entity.EbuyMerchantBean;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantEbuyProduct;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantOrder;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantProdDetail;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantProdLst;
import com.cnfantasia.server.api.ebuyorder.entity.ShopInfo;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.pub.utils.EbuyMerchantUtil;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.CommonMultiFileUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.UserBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import com.cnfantasia.server.ms.ebuy.service.IEbuyorderService;
import com.cnfantasia.server.ms.ebuyMerchant.entity.McLogonUser;
import com.cnfantasia.server.ms.ebuyProduct.util.ProductImageUtil;
import com.cnfantasia.server.ms.limitBuyActivity.service.LimitBuyActivityService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.SessionManager;

import Decoder.BASE64Decoder;

/**
 * 移动版HTML5 电商商户 Controller
 * 
 * @author yewj
 */
@Controller 
@RequestMapping("/ebuyMerchant")
public class EbuyMerchantController extends BaseController {

	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private EbuyMerchantService ebuyMerchantService;

	@Resource
	private IEbuyorderService ebuyorderService;
	
	@Resource
	private IEbuyProductBaseService ebuyProductBaseService;
	
	@Resource
	private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	
	@Resource
	private IProvinceCityBlockService provinceCityBlockService;
	
	@Resource
	private IGroupBuildingBaseService groupBuildingBaseService;
	
	@Resource
	private IUuidManager uuidManager;

	@Resource
	private IHomeMessageService homeMessageService;
	@Resource
	private ISysParamManager sysParamManager;

	/**
	 * 根据传入的page参数跳转到相应的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPage.html")
	public ModelAndView toPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String page = ParamUtils.getString(request, "page", "login");
		//商品列表
		if(page.equals("itemManage")) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
//			Long supplyId = SessionManager.getCurrentSupplyId(request);
			Long supplyId = getSupplyId(request);
			paramMap.put("storeId", supplyId);
			paramMap.put("status", 0);
			List<EbuyProdType> ptOnShelfList = ebuyMerchantService.getProdTypes(paramMap);
			paramMap.put("status", 1);
			List<EbuyProdType> ptOffShelfList = ebuyMerchantService.getProdTypes(paramMap);
			
			request.setAttribute("ptOnShelfList", ptOnShelfList);
			request.setAttribute("ptOffShelfList", ptOffShelfList);
		}

		modelAndView.setViewName("/ebuyMerchant/" + page);
		return modelAndView;
	}
	
	@RequestMapping("/itemManage.html")
	public ModelAndView itemManage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		McLogonUser mcLogonUser = SessionManager.getMcLogonUser(request);
		if(mcLogonUser == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long supplyId = getSupplyId(request);
		if(supplyId == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
			return modelAndView;
		}
		paramMap.put("storeId", supplyId);
		paramMap.put("status", 0);
		List<EbuyProdType> ptOnShelfList = ebuyMerchantService.getProdTypes(paramMap);
		paramMap.put("status", 1);
		List<EbuyProdType> ptOffShelfList = ebuyMerchantService.getProdTypes(paramMap);
		
		request.setAttribute("ptOnShelfList", ptOnShelfList);
		request.setAttribute("ptOffShelfList", ptOffShelfList);
		modelAndView.setViewName("/ebuyMerchant/itemManage");
		return modelAndView;
	}
	
	@RequestMapping("/myOrder.html")
	public ModelAndView myOrder(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		Long supplyId = getSupplyId(request);
		if(supplyId == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
			return modelAndView;
		}
		
		modelAndView.setViewName("/ebuyMerchant/myOrder");
		return modelAndView;
	}
	
	@RequestMapping("/myOrderSearch.html")
	public ModelAndView myOrderSearch(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		Long supplyId = getSupplyId(request);
		if(supplyId == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
			return modelAndView;
		}
		
		modelAndView.setViewName("/ebuyMerchant/myOrderSearch");
		return modelAndView;
	}
	
	
	@RequestMapping("/setting.html")
	public ModelAndView setting(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Long supplyId = getSupplyId(request);
		if(supplyId == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
			return modelAndView;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", supplyId);
		
		EbuySupplyMerchantEntity merchantSupply = ebuyMerchantService.getMerchantSupply(paramMap);
		request.getSession().setAttribute("merchantSupply", merchantSupply);

		//判断是否有临停车优惠券
		Map<String, Object> carCouponNums = ebuyMerchantService.getCarCouponNums(supplyId);
		if(!DataUtil.isEmpty(carCouponNums) && !DataUtil.isEmpty(carCouponNums.get("num"))) {
			Integer cpNum = Integer.valueOf(carCouponNums.get("num").toString());
			if(cpNum > 0) {
				modelAndView.addObject("openCarCoupon", 1);
			} else {
				modelAndView.addObject("openCarCoupon", 0);
			}
		} else {
			modelAndView.addObject("openCarCoupon", 0);
		}

		modelAndView.setViewName("/ebuyMerchant/setting");
		
		return modelAndView;
	}
	
	private void setSettingToSession(HttpServletRequest request) {
		EbuySupplyMerchantEntity merchantSupply = (EbuySupplyMerchantEntity) request.getSession().getAttribute("merchantSupply");
		if(merchantSupply == null) {
//			Long supplyId = SessionManager.getCurrentSupplyId(request);
			Long supplyId = getSupplyId(request);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("supplyId", supplyId);
			merchantSupply = ebuyMerchantService.getMerchantSupply(paramMap);
			request.getSession().setAttribute("merchantSupply", merchantSupply);
		}
	}
	
	/**
	 * 店主信息修改
	 * @return
	 */
	@RequestMapping("/editShopkeeper.html")
	@ResponseBody
	public JsonResponse editShopkeeper(HttpServletRequest request,ModelMap model,String photoimageedit){
		JsonResponse message = new JsonResponse();
		String filePath= OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Ebuy_Store_Pic ;
		String editPicUrl ="";
		try{
			Long supplyId = getSupplyId(request);
			if(supplyId!=null && !supplyId.equals("")){
				EbuySupplyMerchant merchantSupply = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(new BigInteger(supplyId.toString()));
				String pics = merchantSupply.getOwnerIdPhotoes();
				if(StringUtils.isNotEmpty(photoimageedit)  && StringUtils.isNotEmpty(pics)){
					List<String> photoList = Arrays.asList(photoimageedit.split(","));
					List<String> picList = Arrays.asList(pics.split(";"));
					for(String picUrl :picList){
						if(photoList.contains(picUrl)){
							editPicUrl += picUrl+";";
						}else{
							File fileC = new File(filePath + "/" + picUrl);
							fileC.delete();
						}
					}
				}else{
					if (StringUtils.isNotEmpty(pics)) {
						List<String> picList = Arrays.asList(pics.split(";"));
						for (String pic : picList) {
							File fileC = new File(filePath + "/" + pic);
							fileC.delete();
						}
					}
				}
				String name = ParamUtils.getString(request, "shopkeepername");
				String phoneNum = ParamUtils.getString(request, "phoneNum");
				merchantSupply.setUserName(name);
				merchantSupply.setOwnerPhone(phoneNum);
				String picUrls = uploadBossImage(request, supplyId);
				merchantSupply.setOwnerIdPhotoes(editPicUrl+picUrls);
				merchantSupply.setOwnerAuditStatus(0);
				//二次提交店主审核更新时间，供应商列表以该字段排序
				merchantSupply.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
				ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(merchantSupply);
				message.setStatus("0000");
				message.setMessage("保存成功，即将跳转！");
			}else{
				message.setStatus("0001");
				message.setMessage("保存失败，即将跳转！");
			}
		}catch (BusinessRuntimeException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			Object[] paramArrayOfObject = e.getParamArrayOfObject();
			String msg = MessageSourceUtil.getMessage(e.getErrCode(), paramArrayOfObject);
			message.setMessage(msg);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("修改失败，请联系管理员！");
		} catch(IOException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("修改失败，请联系管理员！");
		}
		return message;
	}
	
	/**
	 * 店主认证
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveShopkeeper.html")
	@ResponseBody
	public Object saveShopkeeper(HttpServletRequest request,ModelMap model){
		JsonResponse message = new JsonResponse();
		try{
			Long supplyId = getSupplyId(request);
			if(supplyId!=null && !supplyId.equals("")){
				EbuySupplyMerchant  merchantSupply = new EbuySupplyMerchant();
				merchantSupply.setId(new BigInteger(supplyId.toString()));
				String name = ParamUtils.getString(request, "shopkeepername");
				String phoneNum = ParamUtils.getString(request, "phoneNum");
				merchantSupply.setUserName(name);
				merchantSupply.setOwnerPhone(phoneNum);
				String picUrl = uploadBossImage(request, supplyId);
				merchantSupply.setOwnerIdPhotoes(picUrl);
				merchantSupply.setOwnerAuditStatus(0);
				ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(merchantSupply);
				message.setStatus("0000");
				message.setMessage("保存成功，即将跳转！");
			}else{
				message.setStatus("0001");
				message.setMessage("保存失败，即将跳转！");
			}
		} catch (BusinessRuntimeException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			Object[] paramArrayOfObject = e.getParamArrayOfObject();
			String msg = MessageSourceUtil.getMessage(e.getErrCode(), paramArrayOfObject);
			message.setMessage(msg);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("保存图片失败，请联系管理员！");
		} catch(IOException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("保存图片失败，请联系管理员！");
		}
		
		return message;
	}
	
	@RequestMapping("/settingChecknew.html")
	public String settingChecknew(HttpServletRequest request,ModelMap model){
//		Long supplyId = SessionManager.getCurrentSupplyId(request);
		Long supplyId = getSupplyId(request);
		EbuySupplyMerchant merchantSupply = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(new BigInteger(supplyId.toString()));
		String pics = merchantSupply.getOwnerIdPhotoes();
		if (StringUtils.isNotEmpty(pics)) {
			List<String> picList = Arrays.asList(pics.split(";"));
			model.addAttribute("picList", picList);
			model.addAttribute("picnum", picList.size());
		}
		model.addAttribute("merchantSupply", merchantSupply);
		return "/ebuyMerchant/settingEdit";
	}
	
	@RequestMapping("/settingCheck.html")
	public ModelAndView settingCheck(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Long supplyId = getSupplyId(request);
		if(supplyId == null) {
			modelAndView.setViewName("/ebuyMerchant/login");
			return modelAndView;
		}
		
		EbuySupplyMerchant merchantSupply = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(new BigInteger(supplyId.toString()));
		if (merchantSupply.getOwnerAuditStatus() != OwnerAuditStatus.WaitAudit) {
			String pics = merchantSupply.getOwnerIdPhotoes();
			if (StringUtils.isNotEmpty(pics)) {
				List<String> picList = Arrays.asList(pics.split(";"));
				request.setAttribute("picList", picList);
			}
			request.setAttribute("merchantSupply", merchantSupply);
			modelAndView.setViewName("/ebuyMerchant/settingCheck");
		} else{
			if (StringUtils.isNotEmpty(merchantSupply.getUserName())) {
				String pics = merchantSupply.getOwnerIdPhotoes();
				if (StringUtils.isNotEmpty(pics)) {
					List<String> picList = Arrays.asList(pics.split(";"));
					request.setAttribute("picList", picList);
				}
				request.setAttribute("merchantSupply", merchantSupply);
				modelAndView.setViewName("/ebuyMerchant/settingCheck");
			}else{
				modelAndView.setViewName("/ebuyMerchant/settingChecknew");	
			}
		}
		return modelAndView;
	}
	
	@RequestMapping("/settingServer.html")
	public ModelAndView settingServer(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		setSettingToSession(request);
		modelAndView.setViewName("/ebuyMerchant/settingServer");
		return modelAndView;
	}
	
	
	@RequestMapping("/settingInfo.html")
	public ModelAndView settingInfo(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		setSettingToSession(request);
		
		EbuySupplyMerchantEntity merchantSupply = (EbuySupplyMerchantEntity) request.getSession().getAttribute("merchantSupply");
		request.setAttribute("pcb",provinceCityBlockService.getProvinceCityBlockByBlockId(merchantSupply.gettAddressBlockFId()));
		
		if(merchantSupply.getStoreAuditStatus()==1){		
			modelAndView.setViewName("/ebuyMerchant/settingInfo");
		}else{
			request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
			modelAndView.setViewName("/ebuyMerchant/shopEdit");
		}
		
		return modelAndView;
	}
	
	/**
	 * 运费设置
	 * @param request
	 * @return
	 */
	@RequestMapping("/editFreight.html")
	public ModelAndView editFreight(HttpServletRequest request) {
		EbuySupplyMerchantEntity merchantSupply = (EbuySupplyMerchantEntity) request.getSession().getAttribute("merchantSupply");
		request.setAttribute("selfGetAddressAuto", merchantSupply.getName() + merchantSupply.getAddress());
		
		request.setAttribute("leastDeliveryAmt", PriceUtil.div100(merchantSupply.getLeastDeliveryAmt()));
		if(merchantSupply.getLeastDeliveryAmt()<0.000001){//如果没有设置起配金额，那就说明运费表里有值
			List<DeliveryMethod> deliveryMethodList = ebuyMerchantService.selectDeliveryMethodByMerchantId(merchantSupply.getId());
			for(DeliveryMethod edm: deliveryMethodList){
				long fee = edm.getFee();
				if (edm.getPrice_amount_end() != null && fee > 0) {
					request.setAttribute("orderAmtPer", PriceUtil.div100(fee));
					request.setAttribute("leastOrderAmt", PriceUtil.div100(edm.getPrice_amount_end()));
				}

				if (edm.getPrice_amount_end() == null && fee > 0) {
					request.setAttribute("orderAmtPerMust", PriceUtil.div100(fee));
				}
				
				if(edm.getType() == EbuyDict.EbuyDeliveryMethodType.SelfGet){
					request.setAttribute("selfGetAddress", edm.getName());
				}
			}
		}
		
		return new ModelAndView("/ebuyMerchant/editFreight");
	}
	
	@RequestMapping("/shopCreate.html")
	public ModelAndView shopCreate(HttpServletRequest request) {
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
		return new ModelAndView("/ebuyMerchant/shopCreate");
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 * @throws JSONException
	 */
	@RequestMapping("/getValidateCode.json")
	@ResponseBody
	public JsonResponse getValidateCode(HttpServletRequest request) throws IOException, JSONException{
		JsonResponse message = new JsonResponse();
		String mobile = ParamUtils.getString(request, "mobile", null);
		String type = ParamUtils.getString(request, "type", "0");
		
		HttpUtil httpUtil = getHttpUtil();
		httpUtil.addParameter("mobile", mobile);
		httpUtil.addParameter("type", type);

		try {
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/getValidateCode.json", 10000, "UTF-8",
					request);
			JSONObject retJson = new JSONObject(retStr);

			if ("0000".equals(retJson.getString("status"))) {
				message.setStatus("0000");
				message.setMessage("获取验证码成功 ");
			}else{
				message.setStatus("0001");
				message.setMessage(retJson.getString("message"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("获取验证码失败，请联系管理员！");
		}
		logger.debug(JSON.toJSONString(message));

		return message;
	}

	/**
	 * 注册-step1: 填入手机号，获取验证码，并提交
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/register1.html")
	public ModelAndView register1(HttpServletRequest request) {
		return new ModelAndView("/ebuyMerchant/registerStep01");
	}

	/**
	 * 注册-提交
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/doRegister.html")
	@ResponseBody
	public Object doRegister(HttpServletRequest request) {
		String mobile = ParamUtils.getString(request, "mobile");
		String verifyCode = ParamUtils.getString(request, "verifyCode");
		String rawPwd = ParamUtils.getString(request, "password");
		
		JsonResponse message = new JsonResponse();
		if (StringUtils.isEmpty(mobile)) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("mobile can't be null");
			return message;
		}		
		if(StringUtils.isEmpty(rawPwd)){
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("pasword cant't be null");
			return message;
		}		
		if(StringUtils.isEmpty(verifyCode)){
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("verifyCode cant't be null");
			return message;	
		}
		
		HttpUtil httpUtil = getHttpUtil();
		httpUtil.addParameter("regType", "0");
		httpUtil.addParameter("verifyCode", verifyCode);
		httpUtil.addParameter("mobile", mobile);
		httpUtil.addParameter("password", rawPwd);
		try {
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/regist.json", 10000, "UTF-8", request);
			JSONObject retJson = new JSONObject(retStr);
			
			if("0000".equals(retJson.getString("status"))) {
				JSONObject dataJson = retJson.getJSONObject("dataValue");
				Long userId = dataJson.getLong("userId");
				Long huaId = dataJson.getLong("huaId");
				int loginType = dataJson.getInt("loginType");
				String sessionKey = dataJson.getString("sessionKey");
				
				McLogonUser logonUser = new McLogonUser();
				logonUser.setUserId(userId);
				logonUser.setHuaId(huaId);
				logonUser.setLoginType(loginType);
				logonUser.setSessionKey(sessionKey);
				
				EbuyMerchantBean merchantBean = ebuyMerchantService.getEbuyMerchantByUserId(userId);
				logonUser.setEbuyMerchantBean(merchantBean);
				
				message.setStatus("0000");
				SessionManager.setMcLogonUser(request, logonUser);
				message.putData("isMerchant", true);
				message.putData("isFirstLogin", true);
				message.setMessage("注册成功");
			} else {
				message.setStatus("0001");
				message.setMessage(retJson.getString("message"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("注册异常，请联系管理员！");
		}
		logger.debug(JSON.toJSONString(message));
		return message;
	}

	/**
	 * 更新店铺信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updShopBusinessInfo.html")
	@ResponseBody
	public JsonResponse saveEditShopBaseInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("更新成功");
		
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		saveShopBaseInfoToSession(request);
		ShopInfo shopInfo = (ShopInfo) request.getSession().getAttribute("shopInfo");
		
		ebuyMerchantService.updateEbuySupplyMerchant(SessionManager.getMcLogonUser(request).getUserId(),id, shopInfo);
		
		return jsonResponse;
	}
	
	/**
	 * 更新运费设置信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEditFreight.html")
	@ResponseBody
	public JsonResponse saveEditFreight(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("更新成功");
		
		BigInteger esmId = new BigInteger(SessionManager.getMcLogonUser(request).getEbuyMerchantBean().getMerchantId().toString());
		
		double leastDeliveryAmt = ParamUtils.getDouble(request, "leastDeliveryAmt", -1);
		double leastOrderAmt = ParamUtils.getDouble(request, "leastOrderAmt", -1);
		double orderAmtPer = ParamUtils.getDouble(request, "orderAmtPer", -1);
		double orderAmtPerMust = ParamUtils.getDouble(request, "orderAmtPerMust", -1);
		String selfGetAddress = ParamUtils.getString(request, "selfGetAddress");
		
		ebuyMerchantService.updateEbuyDeliveryMethod(leastDeliveryAmt, leastOrderAmt, orderAmtPer, orderAmtPerMust,selfGetAddress, esmId);
		
		return jsonResponse;
	}
	
	
	/**
	 * 暂存店铺基本信息,暂存在session中
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveShopBusinessInfo.json")
	@ResponseBody
	public JsonResponse saveShopBaseInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		saveShopBaseInfoToSession(request);

		//return new ModelAndView("/ebuyMerchant/settingFreight"); //再补充运费设置
		
		return jsonResponse;
	}

	private void saveShopBaseInfoToSession(HttpServletRequest request) {
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setShopName(ParamUtils.getString(request, "shopName"));
		shopInfo.setLinkName(ParamUtils.getString(request, "linkName"));
		shopInfo.setLinkPhone(ParamUtils.getString(request, "linkPhone"));
		shopInfo.setBlockId(ParamUtils.getBigInteger(request, "block", null));
		shopInfo.setShopAddress(ParamUtils.getString(request, "shopAddress"));
		shopInfo.setServiceGbIds(ParamUtils.getStrings(request, "groupBuildingId"));

		request.getSession().setAttribute("shopInfo", shopInfo);
		
		shopInfo.setIntroduce(ParamUtils.getString(request, "introduce"));

		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Ebuy_Store_Pic;
//			String filePath = "D:/usr/uploadImages";//本地测试路径

			long startTime = System.currentTimeMillis();
			try {
				String[] storeImagesOld = ParamUtils.getStrings(request, "storeImageOld"); //商户图片--历史图片
				if (storeImagesOld != null) {
					for (int i = 0; i <storeImagesOld.length; i++) {
						 String storeImage = storeImagesOld[i];
						shopInfo.getShopPicList().add(storeImage);
					}
				}
				
				String[] storeImages = ParamUtils.getStrings(request, "storeImage"); //商户图片
				if(storeImages !=null){
					for (String storeImage : storeImages) {
						String shopImage = CommonMultiFileUtil.uploadJSFile(storeImage, OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH),
								PathConstants.Ebuy_Store_Pic, "ebuyShopPic", new BigInteger(SessionManager.getMcLogonUser(request).getUserId() + ""));
						shopInfo.getShopPicList().add(shopImage);
						//File fileStorePic = new File(filePath + "/" + shopImage);
						//小图走CDN或阿里图片服务，因此不再需要小图了
						//generateMiniImageOnly(fileStorePic, PathConstants.Ebuy_Store_Pic);
					}
				}
				
				String[] blImagesOld = ParamUtils.getStrings(request, "blImageOld"); //营业执照图片--历史图片
				if (blImagesOld != null) {
					for (int i = 0; i < blImagesOld.length; i++) {
						String blImage = blImagesOld[i];
						shopInfo.getShopLicenseList().add(blImage);
					}
				}
				
				String[] blImages = ParamUtils.getStrings(request,"blImage");//营业执照
				if(blImages !=null){
					for (String blImage : blImages) {
						String shopBusinessLicense = CommonMultiFileUtil.uploadJSFile(blImage,
								OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH), PathConstants.Ebuy_Store_Pic, "ebuyBusinessLicense",
								new BigInteger(SessionManager.getMcLogonUser(request).getUserId() + ""));
						shopInfo.getShopLicenseList().add(shopBusinessLicense);
						//File fileBL = new File(filePath + "/" + shopBusinessLicense);
						//小图走CDN或阿里图片服务，因此不再需要小图了
						//generateMiniImageOnly(fileBL, PathConstants.Ebuy_Store_Pic);
					}
				}
			} catch (IOException e) {
				logger.info("channelPartner.businessLicense.upload.failure", e);
				e.printStackTrace();
			}
			
			logger.info("Spend time： " + (System.currentTimeMillis() - startTime));
		}
	}

	/**
	 * 保存店铺所有信息
	 */
	@RequestMapping("/saveShopFullInfo.html")
	@ResponseBody
	public synchronized JsonResponse saveShopFullInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		ShopInfo shopInfo = (ShopInfo) request.getSession().getAttribute("shopInfo");

		double leastDeliveryAmt = ParamUtils.getDouble(request, "leastDeliveryAmt", -1);
		double leastOrderAmt = ParamUtils.getDouble(request, "leastOrderAmt", -1);
		double orderAmtPer = ParamUtils.getDouble(request, "orderAmtPer", -1);
		double orderAmtPerMust = ParamUtils.getDouble(request, "orderAmtPerMust", -1);
		String selfGetAddress = ParamUtils.getString(request, "selfGetAddress");

		EbuyMerchantBean ebuyMerchant = ebuyMerchantService.getEbuyMerchantByUserId(SessionManager.getMcLogonUser(request).getUserId());
		if(ebuyMerchant==null) {//不存在就插入
			ebuyMerchantService.insertEbuySupplyMerchant(SessionManager.getMcLogonUser(request).getUserId(), shopInfo, leastDeliveryAmt, leastOrderAmt,
					orderAmtPer, orderAmtPerMust, selfGetAddress);

		} else {//存在就更新
			BigInteger id = ParamUtils.getBigInteger(request, "id", null);
			saveShopBaseInfoToSession(request);
			
			ebuyMerchantService.updateEbuySupplyMerchant(SessionManager.getMcLogonUser(request).getUserId(),id, shopInfo);
		}
		
		//再登录一下
		EbuyMerchantBean merchantBean = ebuyMerchantService.getEbuyMerchantByUserId(SessionManager.getMcLogonUser(request).getUserId());
		SessionManager.getMcLogonUser(request).setEbuyMerchantBean(merchantBean);

		//return new ModelAndView("forward:/ebuyMerchant/setting"); //回到店铺设置界面
		jsonResponse.setMessage("保存店铺信息成功");
		return jsonResponse;
	}


	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.html")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		McLogonUser mcLogonUser = SessionManager.getMcLogonUser(request);
		if(mcLogonUser == null) {
//			String sessionKey = ParamUtils.getString(request, "", null);
			
			modelAndView.setViewName("/ebuyMerchant/login");
		} else {
			Long supplyId = getSupplyId(request);
			
			if (supplyId != null) {//有店铺
				Boolean firstLogin = mcLogonUser.getEbuyMerchantBean().getIsFirstLogin();
				if (firstLogin != null && firstLogin == true) {
					modelAndView.setViewName("/ebuyMerchant/changePassword1");
				} else {
					modelAndView.setViewName("/ebuyMerchant/myOrder");
				}
			} else {//还没创建店铺，提示去开店铺
				modelAndView.setViewName("/ebuyMerchant/registerStep03");
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping("/doLogin.html")
	@ResponseBody
	public Object doLogin(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		String phoneNum = ParamUtils.getString(request, "phoneNum", null);
		String password = ParamUtils.getString(request, "password", null);
		
		HttpUtil httpUtil = getHttpUtil();
		httpUtil.addParameter("number", phoneNum);
//		httpUtil.addParameter("password", new String(Base64.decodeBase64(password)));
		httpUtil.addParameter("password", password);
		httpUtil.addParameter("loginType", "0");
		try {
//			request.getSession().removeAttribute("cookies");
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/doLogin.json", 10000, "UTF-8", request);
			JSONObject retJson = new JSONObject(retStr);
			
			if("0000".equals(retJson.getString("status"))) {
				JSONObject dataJson = retJson.getJSONObject("dataValue");
				Long userId = dataJson.getLong("userId");
				Long huaId = dataJson.getLong("huaId");
				int loginType = dataJson.getInt("loginType");
				String sessionKey = dataJson.getString("sessionKey");
				
				McLogonUser logonUser = new McLogonUser();
				logonUser.setUserId(userId);
				logonUser.setHuaId(huaId);
				logonUser.setLoginType(loginType);
				logonUser.setSessionKey(sessionKey);
				
				EbuyMerchantBean merchantBean = ebuyMerchantService.getEbuyMerchantByUserId(userId);
				logonUser.setEbuyMerchantBean(merchantBean);
				
				message.setStatus("0000");
				if(logonUser.getEbuyMerchantBean() != null) {
					SessionManager.setMcLogonUser(request, logonUser);
					message.putData("isMerchant", true);
					message.putData("isFirstLogin", logonUser.getEbuyMerchantBean().getIsFirstLogin());
					message.setMessage("登录成功");
					
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("number", phoneNum);
					ebuyMerchantService.updateEbuyMerchant(paramMap);
				} else {
					SessionManager.setMcLogonUser(request, logonUser);
					message.putData("isFirstLogin", true);
					message.setMessage("登录成功");
					
					message.putData("isMerchant", false);
					//message.setMessage("此帐户非商户，请联系管理员成为商户！");
				}
			} else {
				message.setStatus("0001");
				message.setMessage(retJson.getString("message"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("登录异常，请联系管理员！");
		}
		logger.debug(JSON.toJSONString(message));
		return message;
	}

	private HttpUtil getHttpUtil() {
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_DEVICE_ID, "ebuy_merchant_login");
		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, HeaderConstant.SubChannelId.EBUY_MERCHANT_APP.toString());
		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, "1.0.0");
		return httpUtil;
	}

	/**
	 * 获取小区列表——按行政区，小区的名称模糊查询
	 */
	@RequestMapping("/getGroupBuildingByBlockIdWithName.json")
	@ResponseBody
	public JsonResponse getGroupBuildingByBlockIdWithName(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();

		BigInteger blockId = ParamUtils.getBigInteger(request, "blockId", null);
		if (blockId == null) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("blockId can't be null");
			return message;
		}

		String gbName = request.getParameter("gbName");
		if (gbName == null) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("gbName  can't be null");
			return message;
		}

		//2.交互
		GroupBuilding qry = new GroupBuilding();
		qry.settBlockFId((blockId));
		qry.setSys0DelState(0);
		qry.setName(gbName);
		qry.setCheckStatus(GroupBuildingDict.CheckStatus.ShenHeTongGuo);
		List<GroupBuilding> gbList = groupBuildingBaseService.getGroupBuildingByConditionDim(MapConverter.toMap(qry));
		qry.setCheckStatus(GroupBuildingDict.CheckStatus.WuXuShenHe);
		gbList.addAll(groupBuildingBaseService.getGroupBuildingByConditionDim(MapConverter.toMap(qry)));

		//3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (GroupBuilding gb : gbList) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", gb.getId());
			tmpMap.put("name", gb.getName());
			tmpMap.put("addressDesc", gb.getAddressDesc());
			resList.add(tmpMap);
		}

		message.setDataValue(resList);
		return message;
	}

	/**
	 * 获取小区列表——分页方式
	 */
	@RequestMapping("/getGroupBuildingPageByBlockId.json")
	@ResponseBody
	public JsonResponse getGroupBuildingPageByBlockId(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();

		BigInteger blockId = ParamUtils.getBigInteger(request, "blockId", null);
		if (blockId == null) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("blockId can't be null");
			return message;
		}
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);

		//2.交互
		GroupBuilding qry = new GroupBuilding();
		qry.settBlockFId((blockId));
		qry.setSys0DelState(0);
		qry.setCheckStatus(GroupBuildingDict.CheckStatus.ShenHeTongGuo);
		List<GroupBuilding> gbList = groupBuildingBaseService.getGroupBuildingByCondition(MapConverter.toMap(qry), pageModel);
		qry.setCheckStatus(GroupBuildingDict.CheckStatus.WuXuShenHe);
		gbList.addAll(groupBuildingBaseService.getGroupBuildingByCondition(MapConverter.toMap(qry), pageModel));

		//3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (GroupBuilding gb : gbList) {
			Map<String, Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("id", gb.getId());
			tmpMap.put("name", gb.getName());
			tmpMap.put("addressDesc", gb.getAddressDesc());
			resList.add(tmpMap);
		}

		return ControllerUtils.addPageInfo(message, resList, pageModel.isLast, pageModel.count);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.html")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
//		request.getSession().removeAttribute("LogonUser");
		request.getSession().invalidate();
		modelAndView.setViewName("/ebuyMerchant/login");
		
		return modelAndView;
	}
	
	/**
	 * 获取验证码<br>
	 * 
	 * type: 0:注册获取;1:忘记密码获取;2手机号登录方式请求验证码;3修改密码或者绑定新手机;4解绑手机
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getValidCode.html")
	@ResponseBody
	public JsonResponse getValidCode(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		String mobile = ParamUtils.getString(request, "phoneNum", null);
		Integer type = ParamUtils.getInteger(request, "type", null);
		if (mobile == null || type == null) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("电话号码不能为空！");
			return message;
		}
		
		if (LoginDict.ValiCodeGetType.REGIST.compareTo(type)==0) {
			User user = new User();
			user.setMobile(mobile);
			user.setSys0DelState(0);
			UserBaseService userBaseService = (UserBaseService) CnfantasiaCommbusiUtil.getBeanManager("userBaseService");
			if (userBaseService.getUserCount(MapConverter.convertBean(user)) > 0) {
				message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				message.setMessage("该手机号已注册！");
				return message;
			}
		}
		
		Date lastGetCodeTm = (Date) request.getSession().getAttribute("getValidCodeTm" + type);
		if(lastGetCodeTm != null) {
			long seconds = (new Date().getTime() - lastGetCodeTm.getTime()) / 1000;
			if(seconds <= 60) {
				message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
				message.setMessage("获取验证码太频繁，1分钟内不能重复获取！");
				return message;
			}
		}

		HttpUtil httpUtil = getHttpUtil();
		if(SessionManager.getMcLogonUser(request) != null) {
			httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, SessionManager.getMcLogonUser(request).getSessionKey());
		}
		httpUtil.addParameter("mobile", mobile);
		httpUtil.addParameter("type", type + "");
		try {
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/getValidateCode.json", 10000, "UTF-8", request);
//			String retStr = httpUtil.post("http://localhost:8081/API/" + "login/getValidateCode.json", 10000, "UTF-8", request);
			message = JSON.parseObject(retStr, JsonResponse.class);
			request.getSession().setAttribute("getValidCodeTm" + type, new Date());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("获取验证码失败，请联系管理员！");
		}

		return message;
	}
	
	/**
	 * 检查验证码是否正确
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/isVarifyCodeCorrect.html")
	@ResponseBody
	public JsonResponse isVarifyCodeCorrect(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		String verifyCode = ParamUtils.getString(request, "validCode", null);
		String number = ParamUtils.getString(request, "phoneNum", null);
		Integer type = ParamUtils.getInteger(request, "type", null);
		if (verifyCode == null || number == null || type == null) {
			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			message.setMessage("verifyCode, mobile and type can't be null");
			return message;
		}

		HttpUtil httpUtil = getHttpUtil();
		if(SessionManager.getMcLogonUser(request) != null) {
			httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, SessionManager.getMcLogonUser(request).getSessionKey());
		}
		
		httpUtil.addParameter("verifyCode", verifyCode);
		httpUtil.addParameter("type", type + "");
		httpUtil.addParameter("backType", "0");
		try {
			String retStr = null;
			//修改密码的走的方式不一样
			if (!type.equals(LoginDict.ValiCodeGetType.EBUY_MERCHANT)) {
				httpUtil.addParameter("number", number);
				request.getSession().setAttribute("number", number);
				retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/doForgetPwd.json", 10000, "UTF-8", request);
			} else {
				httpUtil.addParameter("mobile", number);
				request.getSession().setAttribute("mobile", number);
				retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/isVarifyCodeCorrect.json", 10000, "UTF-8", request);
				//验证结果存至session
				request.getSession().setAttribute("isValicodeCorrect", "0000".equals(JSON.parseObject(retStr, JsonResponse.class).getStatus()));
			}
			message = JSON.parseObject(retStr, JsonResponse.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("获取验证码失败，请联系管理员！");
		}

		return message;
	}
	
//	/**
	//	 * 检查验证码是否正确
//	 * 
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/isVarifyCodeCorrect.html")
//	@ResponseBody
//	public JsonResponse isVarifyCodeCorrect(HttpServletRequest request) {
//		JsonResponse message = new JsonResponse();
//		String verifyCode = ParamUtils.getString(request, "validCode", null);
//		String mobile = ParamUtils.getString(request, "phoneNum", null);
//		Integer type = ParamUtils.getInteger(request, "type", null);
//		if (verifyCode == null || mobile == null || type == null) {
//			message.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
//			message.setMessage("verifyCode, mobile and type can't be null");
//			return message;
//		}
//		
//
//		HttpUtil httpUtil = new HttpUtil();
//		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_DEVICE_ID, "ebuy_merchant_login");
//		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL, HeaderConstant.SubChannelId.EBUY_MERCHANT_APP.toString());
//		httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION, "V1.0.0");
//		if(SessionManager.getMcLogonUser(request) != null) {
//			httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, SessionManager.getMcLogonUser(request).getSessionKey());
//		}
////		httpUtil.addHeader("Cookie", "JSESSIONID=" + request.getSession().getId());
////		request.getCookies();
//		
//		httpUtil.addParameter("verifyCode", verifyCode);
//		httpUtil.addParameter("mobile", mobile);
//		httpUtil.addParameter("type", type + "");
//		try {
//			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/isVarifyCodeCorrect.json", 10000, "UTF-8", request);
////			String retStr = httpUtil.post("http://localhost:8081/API/" + "login/isVarifyCodeCorrect.json", 10000, "UTF-8", request);
//			message = JSON.parseObject(retStr, JsonResponse.class);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			message.setStatus("0002");
	//			message.setMessage("获取验证码失败，请联系管理员！");
//		}
//
//		return message;
//	}
	
	@RequestMapping("/changePassword.html")
	@ResponseBody
	public Object changePassword(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		String password = ParamUtils.getString(request, "password", null);
		String number = (String) request.getSession().getAttribute("number");

		HttpUtil httpUtil = getHttpUtil();
		if(SessionManager.getMcLogonUser(request) != null) {
			httpUtil.addHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SESSIONO_KEY, SessionManager.getMcLogonUser(request).getSessionKey());
		}
		
		httpUtil.addParameter("password", password);
		httpUtil.addParameter("number", number);
		httpUtil.addParameter("backType", "0");
		try {
			String retStr = httpUtil.post(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Last_Api_BaseUrl) + "login/setNewPwd.json", 10000, "UTF-8", request);
			message = JSON.parseObject(retStr, JsonResponse.class);

			try{
				McLogonUser mcUser = SessionManager.getMcLogonUser(request);
				if(mcUser != null) {
					mcUser.getEbuyMerchantBean().setIsFirstLogin(false);
				}
			} catch(Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			message.setMessage("获取验证码失败，请联系管理员！");
		}

		return message;
	}
	
	/**
	 * 订单列表，包括待处理和已处理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOrderList.html")
	@ResponseBody
	public Object getOrderList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		Integer status = ParamUtils.getInteger(request, "status", null);
		String searchKey = ParamUtils.getString(request, "searchKey", null);
		Integer page = ParamUtils.getInteger(request, "page", 1);
//		Long supplyId = SessionManager.getCurrentSupplyId(request);
		Long supplyId = getSupplyId(request);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("supplyId", supplyId);
		paramMap.put("status", status);
		paramMap.put("searchKey", searchKey);
		if(page <= 1) {
			paramMap.put("currentDate", SessionManager.setCurrentDate(request, status));
		} else {
			paramMap.put("currentDate", SessionManager.getCurrentDate(request, status));
		}
		
		PageModel pageModel = ControllerUtils.getPageModel(request);
		List<MerchantOrder> orderList = ebuyMerchantService.getEbuyDeliveryOrderList(paramMap, pageModel);
		JsonResponse retJson = ControllerUtils.addPageInfo(jsonResponse, orderList, pageModel.isLast, pageModel.count);
		logger.debug(JSON.toJSONString(retJson));
		return retJson;
	}
	
	/**
	 * 确认发货
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deliveryOrder.html")
	@ResponseBody
	public Object deliveryOrder(HttpServletRequest request) {
		JsonResponse json = new JsonResponse();
		Long deliverOrderId = ParamUtils.getLong(request, "deliverOrderId", null);
		Long orderId = ParamUtils.getLong(request, "orderId", null);
		
		EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
		ebuyDeliveryOrder.setId(BigInteger.valueOf(deliverOrderId));
		ebuyDeliveryOrder.settEbuyOrderFId(BigInteger.valueOf(orderId));
		ebuyDeliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.DaiShouHuo);
		ebuyDeliveryOrder.setDeliveryTime(DateUtil.formatSecond.get().format(new Date()));
		
		//填写运单号即是发货，此处附近的商家无运单号，只更新订单和配送单状态
		ebuyorderService.saveExpressInfo(ebuyDeliveryOrder);

		//更新运单主的消息
		UserHasHomeMessage message = new UserHasHomeMessage();
		message.setMessageCode(HomeMessageDict.MessageCode.EBUY_STORE_ALERT);
		message.setResouceId(BigInteger.valueOf(deliverOrderId));
		homeMessageService.generateCommonMsg(message);

		return json;
	}
	
	/**
	 * 出售中和仓库中的商品列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryProductList.html")
	@ResponseBody
	public Object getProductList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		Integer status = ParamUtils.getInteger(request, "status", null);
		Long productTypeId = ParamUtils.getLong(request, "productTypeId", null);
		Integer orderBy = ParamUtils.getInteger(request, "orderBy", null);
		String searchKey = ParamUtils.getString(request, "searchKey", null);
//		Integer page = ParamUtils.getInteger(request, "page", 1);
		Long supplyId = SessionManager.getCurrentSupplyId(request);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", status);
		paramMap.put("productTypeId", productTypeId);
		paramMap.put("searchKey", searchKey);
		paramMap.put("supplyId", supplyId);
		paramMap.put("orderBy", orderBy);
		
		PageModel pageModel = ControllerUtils.getPageModel(request);
		List<MerchantProdLst> orderList = ebuyMerchantService.getMerchantProductList(paramMap, pageModel);
		JsonResponse retJson = ControllerUtils.addPageInfo(jsonResponse, orderList, pageModel.isLast, pageModel.count);
		logger.debug(JSON.toJSONString(retJson));
		return retJson;
	}

	/**
	 * 排序置顶
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/upToTop.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse updateSortToTop(HttpServletRequest request, BigInteger prdtId) {
		JsonResponse jsonResponse = new JsonResponse();
        int updCount = ebuyMerchantService.updateSortToTop(prdtId);
        jsonResponse.setStatus(updCount > 0 ? CommConstants.ResponseStatus.SUCCESS : CommConstants.ResponseStatus.BUSINESS_FAILED);
        jsonResponse.setMessage(updCount > 0 ? "置顶成功" : "置顶失败");
		return  jsonResponse;
	}

	/**
	 * 爆款设置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/resetHotSale.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse resetHotSale(HttpServletRequest request, BigInteger prdtId, int isHotSale) {
		JsonResponse jsonResponse = new JsonResponse();
		EbuyProduct ep = ebuyProductBaseService.getEbuyProductBySeqId(prdtId);
		ep.setIsHotSale(isHotSale);
		ebuyProductBaseService.updateEbuyProduct(ep);
		return  jsonResponse;
	}
	
	@RequestMapping("/saveProductPage.html")
	public Object saveProductPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/ebuyMerchant/login");
		
//		Long supplyId = ParamUtils.getLong(request, "supplyId", null);
		Long prodId = ParamUtils.getLong(request, "prodId", null);
		
		//新增商品
		MerchantProdDetail prodDetail = null;
		if(prodId == null) {
			prodDetail = new MerchantProdDetail();
			//新增商品
			modelAndView.setViewName("/ebuyMerchant/itemAddPage");
		} else {//查看或修改商品
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("prodId", prodId);
			prodDetail = ebuyMerchantService.getMerchantProdDetail(paramMap);
			if(prodDetail.getStatusAudit() != null) {
				if (prodDetail.getStatusAudit() == MerchantProdDetail.STATUS_AUDIT_WAIT) {//待审核,即审核中
					modelAndView.setViewName("/ebuyMerchant/itemDetails-auditing");
				} else if (prodDetail.getStatusAudit() == MerchantProdDetail.STATUS_AUDIT_PASS_NOT) {//审核不通过
					modelAndView.setViewName("/ebuyMerchant/itemDetails-auditnot");
				} else if (prodDetail.getStatusAudit() == MerchantProdDetail.STATUS_AUDIT_REPERTORY || prodDetail.getStatus() != 0) {//下架或者草稿
					modelAndView.setViewName("/ebuyMerchant/itemDetails-repertory");
				} else if (prodDetail.getStatusAudit() == MerchantProdDetail.STATUS_AUDIT_PASS) {//审核通过,通过即上架
					modelAndView.setViewName("/ebuyMerchant/itemDetails-onshelf");
				}
			}
		}
		//产品介绍图片列表
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyProductFId", prodId);
		List<EbuyProductIntroducePic>  ebuyProductIntroducePicList = ebuyMerchantService.selectIntroducePics(paramMap);
		Collections.reverse(ebuyProductIntroducePicList);//图文倒序
		List<EbuyProdType> prodTypeList = ebuyMerchantService.getAllProdTypes();
		
		request.setAttribute("introducePicList",ebuyProductIntroducePicList);
		request.setAttribute("prodDetail", prodDetail);
		request.setAttribute("prodTypeList", prodTypeList);
		request.setAttribute("storeStatus", SessionManager.getMcLogonUser(request).getEbuyMerchantBean().getStoreAuditStatus());
		
		return modelAndView;
	}
	
	@RequestMapping("/saveProduct.html")
	@ResponseBody
	public Object saveProduct(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		try {
			BigInteger prodId = ParamUtils.getBigInteger(request, "prodId", null);
			if(prodId == null) {
				MerchantEbuyProduct prod = new MerchantEbuyProduct();
				EbuyMerchantUtil.newStandard(prod, SEQConstants.t_ebuy_product);
				prod.setCreateTime(CnfantasiaCommbusiUtil.getCurrentTime());
				prod.setDownShelfTime(CnfantasiaCommbusiUtil.getCurrentTime());
				
				getProductForReq(request, prod, true);
				List<EbuyProductPic> prodPic = uploadImage(request, prod.getId());
				prod.setProdPic(prodPic);
				if(prodPic.size() > 0) {
					prod.setPicBase(prodPic.get(0).getUrlBig());
					prod.setPicBaseMini(prodPic.get(0).getUrlMini());
				}
				//商品图片介绍
				List<EbuyProductIntroducePic> introduceList = UploadJsImg(prod.getId(),request);
				ebuyMerchantService.insertMerchantProduct(prod,introduceList);
			} else {//查看或修改商品
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("prodId", prodId);
				MerchantEbuyProduct prod = ebuyMerchantService.getMerchantProduct(paramMap);
				
				getProductForReq(request, prod, false);
				List<EbuyProductPic> prodPic = uploadImage(request, prod.getId());
				List<BigInteger> delPicIdList = deleteImage(request, prod.getProdPic());
				prod.setProdPic(prodPic);
				if(prodPic.size() > 0) {
					prod.setPicBase(prodPic.get(0).getUrlBig());
					prod.setPicBaseMini(prodPic.get(0).getUrlMini());
				}
				//商品图文介绍
				getRquestForImage(prodId,request);
				ebuyMerchantService.updateMerchantProduct(prod, delPicIdList);
			}
		} catch (BusinessRuntimeException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0002");
			Object[] paramArrayOfObject = e.getParamArrayOfObject();
			String msg = MessageSourceUtil.getMessage(e.getErrCode(), paramArrayOfObject);
			message.setMessage(msg);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("系统异常，请联系管理员！");
		} catch(IOException e) {
			logger.error(e.getMessage(), e);
			message.setStatus("0003");
			message.setMessage("系统异常，请联系管理员！");
		}
		
		return message;
	}
	
	private void getProductForReq(HttpServletRequest request, MerchantEbuyProduct prod, boolean isNewAdded) {
//		BigInteger prodId = ParamUtils.getBigInteger(request, "prodId", null);
		String prodName = ParamUtils.getString(request, "prodName", null);
		BigInteger productTypeId = ParamUtils.getBigInteger(request, "productTypeId", null);
		Long price = ParamUtils.getLongPrice(request, "price", null);
		Long priceDiscount = ParamUtils.getLongPrice(request, "priceDiscount", null);
		BigInteger leftCount = ParamUtils.getBigInteger(request, "leftCount", null);
		Integer submitStatus = ParamUtils.getInteger(request, "submitStatus", 1);
		String onShelfStatus = ParamUtils.getString(request, "onShelfStatus", "no");
		if(price == null) {
			price = priceDiscount;
		}
		if(priceDiscount == null) {
			throw new BusinessRuntimeException("merchant.prod.price.validerror");
		} else if((price != null && price <= 0) || priceDiscount <= 0) {
			throw new BusinessRuntimeException("merchant.prod.price.validerror");
		}
		if(leftCount ==null) {
			throw new BusinessRuntimeException("merchant.prod.leftCount.validerror");
		} else if(leftCount.intValue() < 0) {
			throw new BusinessRuntimeException("merchant.prod.leftCount.validerror");
		}
		
//		prod.setId(prodId);
		prod.setName(prodName);
		prod.settEbuyProductTypeFId(productTypeId);
		prod.setPrice(price);
		prod.setPriceDiscount(priceDiscount);
		prod.setPurchasePrice(priceDiscount);
		prod.setLeftCount(leftCount);
		if(onShelfStatus.equals("yes")){
		}else{
			prod.setStatus(1);
		}
		if(submitStatus != null && submitStatus == 2) {
//			Integer auditStatus = SessionManager.getMcLogonUser(request).getEbuyMerchantBean().getStoreAuditStatus();
//			if(auditStatus != EbuyMerchantBean.STORE_AUDIT_STATUS_PASS) {
//				throw new ValidateRuntimeException("商户通过审核才能提交出售哟，先放入仓库吧！");
//			}
			prod.setStatusAudit(MerchantProdDetail.STATUS_AUDIT_PASS);
		} else if(submitStatus != null && submitStatus == 4) {
			Integer auditStatus = SessionManager.getMcLogonUser(request).getEbuyMerchantBean().getStoreAuditStatus();
			if(auditStatus != EbuyMerchantBean.STORE_AUDIT_STATUS_PASS) {
				throw new ValidateRuntimeException("商户通过审核才能提交出售哟，先放入仓库吧！");
			}
		}else if(onShelfStatus.equals("yes")){
			
		} else {
			prod.setStatusAudit(MerchantProdDetail.STATUS_AUDIT_REPERTORY);
		}
		
		
		if(prod.gettSupplyMerchantFId() == null) {
//			Long supplyId = SessionManager.getCurrentSupplyId(request);
			Long supplyId = getSupplyId(request);
			prod.settSupplyMerchantFId(BigInteger.valueOf(supplyId));
		}
		
		
		//商品参数：
		String paramKey;
		String paramValue;
		List<EbuyProductParameters> prodParamList = new ArrayList<EbuyProductParameters>();
		for(int i = 1; i <= 10; i ++) {
			paramKey = ParamUtils.getString(request, "paramKey" + i, null);
			paramValue = ParamUtils.getString(request, "paramValue" + i, null);
			if(!StringUtils.isEmpty(paramKey) && !StringUtils.isEmpty(paramValue)) {
				EbuyProductParameters prodParam = new EbuyProductParameters();
				prodParam.settPropName(paramKey);
				prodParam.settPropValue(paramValue);
				prodParam.settEbuyProductFId(prod.getId());
				prodParamList.add(prodParam);
			}
		}
		prod.setProdParams(prodParamList);
		
		EbuyProductShelf prodShelf = new EbuyProductShelf();
		if(isNewAdded){
			// 如果是新增上传到App，则t_ebuy_product_shelf表f_id要从t_ebuy_product中取，且不能相同；如果上传到轻应用，则与当前的t_ebuy_product表f_id相同
			prodShelf.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_product));
		} else {
//			prodShelf.setId(prod.getId());
		}
		prodShelf.settEbuyProductId(prod.getId());
		prodShelf.settEbuyProductTypeId(productTypeId);
		prodShelf.setPrice(prod.getPrice());
		prodShelf.setPriceDiscount(prod.getPriceDiscount());
		if(submitStatus != null && submitStatus == 2) {
			prodShelf.setUpShelfState(0); //上架状态
			prod.setStatus(0);
		}
		prod.setProdShelf(prodShelf);
	}
	
	private List<BigInteger> deleteImage(HttpServletRequest request, List<EbuyProductPic> picList) {
		List<BigInteger> delPicList = new ArrayList<BigInteger>();
		String imageDelete = ParamUtils.getString(request, "imageDelete", "");
		String[] imageDels = imageDelete.split(",");
		for(String imageDel : imageDels) {
			if(StringUtils.isNotEmpty(imageDel)) {
				try {
					Long imagePicId = Long.valueOf(imageDel);
					for(EbuyProductPic pic : picList) {
						if(pic.getId().longValue() == imagePicId) {
							delPicList.add(pic.getId());
							
							String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
							//							String filePath = "D:/usr/uploadImages";//本地测试路径
							File fileC = new File(filePath + "/" + pic.getUrlBig());
							FileUtils.deleteDirs(fileC);
							
							File fileC2 = new File(filePath + "/" + pic.getUrlBig().substring(0, pic.getUrlBig().lastIndexOf(".")));
							FileUtils.deleteDirs(fileC2);
						}
					}
				} catch(Exception e) {
					
				}
			}
		}
		return delPicList;
	}
	private String uploadBossImage(HttpServletRequest request,Long ownerId) throws IllegalStateException, IOException{
		String fileNames = "";
		if (request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			String filePath= OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Ebuy_Store_Pic ;
//			String filePath = "D:/usr/uploadImages";
			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("photoimage");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				int imgIndex=0;
				for (MultipartFile uploadImageFile : uploadImageFiles) {
					if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						String fileName = (ownerId + "_" + System.currentTimeMillis()+imgIndex) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath+"/"+fileName);
						if(!fileC.exists()) {
							fileC.mkdirs();
						}
						uploadImageFile.transferTo(fileC);
						fileNames +=fileName + ";"; 
					}
					imgIndex ++;
				}
			}
		}
		return fileNames;
	}
	private List<EbuyProductPic> uploadImage(HttpServletRequest request, BigInteger prodId) throws IllegalStateException, IOException {
		List<EbuyProductPic> picList = new ArrayList<EbuyProductPic>();
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
//			String filePath = "D:/usr/uploadImages";//本地测试路径

			//商家大图
			//MultipartFile uploadImageFile = multipartRequest.getFiles(name).getFile("imageFile");
			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("imageFile");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				for (int imgIndex = uploadImageFiles.size() - 1; imgIndex >= 0; imgIndex--) {
					MultipartFile uploadImageFile = uploadImageFiles.get(imgIndex);
					if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						String fileNameC = (prodId + "_" + System.currentTimeMillis()+imgIndex) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath + "/" + fileNameC);
						if(!fileC.exists()) {
							fileC.mkdirs();
						}
						
						EbuyProductPic pic = new EbuyProductPic();
						pic.setUrlBig(fileNameC);
						pic.setUrlMini(fileNameC);
						pic.settEbuyProductFId(prodId);
						picList.add(pic);
						uploadImageFile.transferTo(fileC);
						
						// 处理压缩图片
			            ProductImageUtil.generateMiniImage(fileC, PathConstants.Product_Image_Dir);
					}
				}
			}
		}
		return picList;
	}
	
	/**
	 * 只生成小图，不对原图进行处理
	 * 
	 * @param bigImageFile
	 */
	private void generateMiniImageOnly(File bigImageFile, String imageDir) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Market.getGuigeList();
//		String descDirecBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir;
		String descDirecBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + imageDir;
		
		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号
		
		String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		
		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if(!goalFileDir.exists()){
			goalFileDir.mkdirs();
		}
		
		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), null, tmpWidthHeight.getHeight(), 1f, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}
	
	@Resource
	LimitBuyActivityService limitBuyActivityService;
	/**
	 * 下架或者提交出售
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/offShelf.html")
	@ResponseBody
	public Object offShelf(HttpServletRequest request) {
		JsonResponse message = new JsonResponse();
		Long userId = SessionManager.getMcLogonUser(request).getUserId();
		BigInteger prodId = ParamUtils.getBigInteger(request, "prodId", null);
		
		EbuyProduct prod = new EbuyProduct();
		prod.setId(prodId);
		prod.setStatus(1);
		prod.setIsHotSale(EbuyDict.Product_Hot_Sale_Status.Not_Hot_Sale);
		prod.setSys0UpdUser(BigInteger.valueOf(userId));
		EbuyMerchantUtil.updateStandard(prod);
		ebuyProductBaseService.updateEbuyProduct(prod);
		
		List<EbuyProduct> ebuyProducts = new ArrayList<EbuyProduct>();
		ebuyProducts.add(prod);
		limitBuyActivityService.downShelf(ebuyProducts);
		
		return message;
	}
	
	/**
	 * 下架或者提交出售
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/uploadStorePic.html")
	@ResponseBody
	public Object uploadStorePic(HttpServletRequest request) throws IllegalStateException, IOException {
		JsonResponse message = new JsonResponse();
//		Long supplyId = SessionManager.getCurrentSupplyId(request);
		Long supplyId = getSupplyId(request);
		String fileNameC = null;
		
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Ebuy_Store_Pic;
//						String filePath = "D:/usr/uploadImages";//本地测试路径
			File filePathFile = new File(filePath);
			if(!filePathFile.exists()) {
				filePathFile.mkdir();
			}

			//商家大图
			//MultipartFile uploadImageFile = multipartRequest.getFiles(name).getFile("imageFile");
			List<MultipartFile> uploadImageFiles = multipartRequest.getFiles("fileHeadImage");
			if(null!=uploadImageFiles && uploadImageFiles.size()>0){
				for (MultipartFile uploadImageFile : uploadImageFiles) {
					if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
						int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
						fileNameC = (supplyId + "_" + System.currentTimeMillis()) + uploadImageFile.getOriginalFilename().substring(indexOfDot);
						File fileC = new File(filePath + "/" + fileNameC);
						
						uploadImageFile.transferTo(fileC);
						// 处理压缩图片
			            ProductImageUtil.generateMiniImage(fileC, PathConstants.Ebuy_Store_Pic);
						
						break;
					}
				}
			}
		}
		
		if(fileNameC != null) {
			EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
			ebuySupplyMerchant.setId(BigInteger.valueOf(supplyId));
			ebuySupplyMerchant.setStorePic(fileNameC);
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(ebuySupplyMerchant);
			//更新session中图片信息
			if (request.getSession().getAttribute("merchantSupply") != null) {
				((EbuySupplyMerchantEntity) request.getSession().getAttribute("merchantSupply")).setStorePic(fileNameC);
			}
		}
		
		return message;
	}
	
	@RequestMapping("/saveServiceInfo.html")
	@ResponseBody
	public JsonResponse saveServiceInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("更新成功");
		
		String tel = ParamUtils.getString(request, "tel");
		String startTime = ParamUtils.getString(request, "startTime");
		String endTime = ParamUtils.getString(request, "endTime");
		
		if(StringUtils.isEmpty(tel)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("客服电话不能为空");
			return jsonResponse;
		}
				
		if(StringUtils.isEmpty(startTime)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("开始时间不能为空");
			return jsonResponse;
		}
		
		if(StringUtils.isEmpty(endTime)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("结束时间不能为空");
			return jsonResponse;
		}
		
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setId(new BigInteger(SessionManager.getMcLogonUser(request).getEbuyMerchantBean().getMerchantId().toString()));
		ebuySupplyMerchant.setTel(tel);
		ebuySupplyMerchant.setStartTime(startTime);
		ebuySupplyMerchant.setEndTime(endTime);
		
		ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(ebuySupplyMerchant);
		
		return jsonResponse;
	}
	
	private Long getSupplyId(HttpServletRequest request) {
		Long supplyId = SessionManager.getCurrentSupplyId(request);
		if(supplyId == null) {
			McLogonUser logonUser = SessionManager.getMcLogonUser(request);
			if(logonUser != null) {
				EbuyMerchantBean merchantBean = ebuyMerchantService.getEbuyMerchantByUserId(logonUser.getUserId());
				logonUser.setEbuyMerchantBean(merchantBean);
				SessionManager.setMcLogonUser(request, logonUser);
				
				supplyId = SessionManager.getCurrentSupplyId(request);
			}
		}
		return supplyId;
	}

	public void setEbuyMerchantService(EbuyMerchantService ebuyMerchantService) {
		this.ebuyMerchantService = ebuyMerchantService;
	}
	
	/**
	 * 处理前端js压缩图片上传
	 * @return
	 */
	private List<EbuyProductIntroducePic> UploadJsImg(BigInteger productId,HttpServletRequest request){
		//商品介绍图片
		String newUpLoad0 = ParamUtils.getString(request, "newUpLoad0", null);
		String newUpLoad1 = ParamUtils.getString(request, "newUpLoad1", null);
		String newUpLoad2 = ParamUtils.getString(request, "newUpLoad2", null);
		String newUpLoad3 = ParamUtils.getString(request, "newUpLoad3", null);
		String newUpLoad4 = ParamUtils.getString(request, "newUpLoad4", null);
		String newUpLoad5 = ParamUtils.getString(request, "newUpLoad5", null);
		String newUpLoad6 = ParamUtils.getString(request, "newUpLoad6", null);
		String newUpLoad7 = ParamUtils.getString(request, "newUpLoad7", null);
		String newUpLoad8 = ParamUtils.getString(request, "newUpLoad8", null);
		String newUpLoad9 = ParamUtils.getString(request, "newUpLoad9", null);
		//商品描述
		String descText0 = ParamUtils.getString(request, "descText0", null);
		String descText1 = ParamUtils.getString(request, "descText1", null);
		String descText2 = ParamUtils.getString(request, "descText2", null);
		String descText3 = ParamUtils.getString(request, "descText3", null);
		String descText4 = ParamUtils.getString(request, "descText4", null);
		String descText5 = ParamUtils.getString(request, "descText5", null);
		String descText6 = ParamUtils.getString(request, "descText6", null);
		String descText7 = ParamUtils.getString(request, "descText7", null);
		String descText8 = ParamUtils.getString(request, "descText8", null);
		String descText9 = ParamUtils.getString(request, "descText9", null);
		
		List<String> picList1 = new ArrayList<String>();
		List<String> descTextList = new ArrayList<String>();

		picList1.add(newUpLoad0);picList1.add(newUpLoad1);picList1.add(newUpLoad2);
		picList1.add(newUpLoad3);picList1.add(newUpLoad4);picList1.add(newUpLoad5);
		picList1.add(newUpLoad6);picList1.add(newUpLoad7);picList1.add(newUpLoad8);
		picList1.add(newUpLoad9);
		descTextList.add(descText0);descTextList.add(descText1);descTextList.add(descText2);
		descTextList.add(descText3);descTextList.add(descText4);descTextList.add(descText5);
		descTextList.add(descText6);descTextList.add(descText7);descTextList.add(descText8);
		descTextList.add(descText9);
		//介绍图片名
		List<EbuyProductIntroducePic> picIntroduceList = new ArrayList<EbuyProductIntroducePic>();
		String filePath= OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir ;
//		String filePath = "D:/usr/uploadImages";//本地测试路径
		try {
			int imgIndex =0;
            //使用BASE64对图片文件数据进行解码操作
            BASE64Decoder decoder = new BASE64Decoder();
			for(int i=0;i<picList1.size();i++){
				if(picList1.get(i)==null){
					break;
				}
				EbuyProductIntroducePic proIntroPic= new EbuyProductIntroducePic();
				String FileName = (productId+"_"+"Introduce"+System.currentTimeMillis()+"_"+imgIndex+".jpg");
	            File f = new File(filePath, FileName);
	            //通过Base64解密，将图片数据解密成字节数组
	            String picmsg = picList1.get(i).substring(picList1.get(i).indexOf(",")  + 1);
	            byte[] bytes = decoder.decodeBuffer(picmsg);
	            //构造字节数组输入流
	            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	            //读取输入流的数据
	            BufferedImage bi = ImageIO.read(bais);
	            //将数据信息写进图片文件中
	            ImageIO.write(bi, "jpg", f);// 不管输出什么格式图片，转为jpg，此处不需改动
	            bais.close();
	            imgIndex++;
	            
	            // 处理压缩图片
	            ProductImageUtil.generateMiniImage(f, PathConstants.Product_Image_Dir);
	            
	            proIntroPic.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
	            proIntroPic.setUrlBig(FileName);
	            proIntroPic.setUrlMini(FileName);
	            proIntroPic.settEbuyProductFId(productId);
	            proIntroPic.setTextDesc(descTextList.get(i));
	            picIntroduceList.add(proIntroPic);
			}
        } catch (IOException e) {
        	e.printStackTrace();
        	try {
        		logger.debug("EbuyMerchantController==>写入商品介绍图片出现异常");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
		return picIntroduceList;
	}
	//获取修改或新增的图片image串
	private List<String> getRquestForImage(BigInteger productId,HttpServletRequest request){
		//获取前端图片input总数
		int picNum = ParamUtils.getInt(request, "imageNums", 10);
		
		//产品介绍图片列表
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyProductFId", productId);
		List<EbuyProductIntroducePic>  ebuyProductIntroducePicList = ebuyMerchantService.selectIntroducePics(paramMap);
		if(ebuyProductIntroducePicList!=null && ebuyProductIntroducePicList.size()>0){
			Collections.reverse(ebuyProductIntroducePicList);//f_id倒序，list要重新排序
		}
		List<EbuyProductIntroducePic>  editProductIntroducePicList = new ArrayList<EbuyProductIntroducePic>();//修改的商品介绍图片list
		List<EbuyProductIntroducePic>  oldProductIntroducePicList = new ArrayList<EbuyProductIntroducePic>();
		List<Integer> IndexedList = new ArrayList<Integer>();//新增的的角标
		List<Integer> DelIndexedList = new ArrayList<Integer>();//删除的的角标
		//判断哪些为修改哪些是新增
		for(int i= 0;i<picNum;i++){
			int index = ParamUtils.getInt(request, "imageindex"+i, 11);
			if(index!=11){
				String newUpLoad = ParamUtils.getString(request, "newUpLoad"+i, null);
				if(newUpLoad!=null){//修改原图
					String fileName = baseStrToPicName(newUpLoad,productId,index);
					ebuyProductIntroducePicList.get(index).setUrlMini(fileName);
					ebuyProductIntroducePicList.get(index).setUrlBig(fileName);
					String descText = ParamUtils.getString(request, "descText"+i, null);
					ebuyProductIntroducePicList.get(index).setTextDesc(descText);
					editProductIntroducePicList.add(ebuyProductIntroducePicList.get(index));
					oldProductIntroducePicList.add(ebuyProductIntroducePicList.get(index));
				}else{
					//原有未修改图片.更新描述
					String descText = ParamUtils.getString(request, "descText"+i, null);
					ebuyProductIntroducePicList.get(index).setTextDesc(descText);
					editProductIntroducePicList.add(ebuyProductIntroducePicList.get(index));
				}
				DelIndexedList.add(index);
			}else{//新增
				String newUpLoad = ParamUtils.getString(request, "newUpLoad"+i, null);
				if(newUpLoad != null){
					String fileName = baseStrToPicName(newUpLoad,productId,index);
					EbuyProductIntroducePic introducePic = new EbuyProductIntroducePic();
					introducePic.settEbuyProductFId(productId);
					introducePic.setUrlBig(fileName);
					introducePic.setUrlMini(fileName);
					introducePic.setSys0AddTime(CnfantasiaCommUtil.getCurrentTime());
					String descText = ParamUtils.getString(request, "descText"+i, null);
					introducePic.setTextDesc(descText);
					editProductIntroducePicList.add(introducePic);
					IndexedList.add(editProductIntroducePicList.size()-1);
				}
			}
		}
		
		//更新描述

		ebuyMerchantService.insertOrUpdateOrDeletePics(editProductIntroducePicList,oldProductIntroducePicList,
				IndexedList,ebuyProductIntroducePicList,DelIndexedList);
		return null;
	}
	
	private String baseStrToPicName(String picStr,BigInteger productId,int imgIndex){
		String filePath= OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Product_Image_Dir ;
//		String filePath = "D:/usr/uploadImages";//本地测试路径
		String FileName ="";
		try {
            	//使用BASE64对图片文件数据进行解码操作
            	BASE64Decoder decoder = new BASE64Decoder();
				FileName = (productId+"_"+"Introduce"+System.currentTimeMillis()+"_"+imgIndex+".jpg");
	            File f = new File(filePath, FileName);
	            //通过Base64解密，将图片数据解密成字节数组
	            String picmsg = picStr.substring(picStr.indexOf(",")  + 1);
	            byte[] bytes = decoder.decodeBuffer(picmsg);
	            //构造字节数组输入流
	            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
	            //读取输入流的数据
	            BufferedImage bi = ImageIO.read(bais);
	            //将数据信息写进图片文件中
	            ImageIO.write(bi, "jpg", f);// 不管输出什么格式图片，转为jpg，此处不需改动
	            bais.close();
	            imgIndex++;
        } catch (IOException e) {
        	e.printStackTrace();
        	try {
        		logger.debug("EbuyMerchantController==>写入商品介绍图片出现异常");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
		return FileName;
	}
	
	@RequestMapping("/editshoopMsg.html")
	@ResponseBody
	public JsonResponse editshoopMsg(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		Long supplyId = getSupplyId(request);
		if(supplyId!=null){
			String editIntroduce= ParamUtils.getString(request, "editIntroduce", null);
			EbuySupplyMerchant merchantSupply = new EbuySupplyMerchant();
			merchantSupply.setId(new BigInteger(supplyId.toString()));
			merchantSupply.setIntroduce(editIntroduce);
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(merchantSupply);
			jsonResponse.setStatus("0000");
		}
		return jsonResponse;
	}

	@RequestMapping("/goToCarCoupon.html")
	public ModelAndView goToCarCoupon(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Long supplyId = getSupplyId(request);
		Map<String, Object> carCouponNums = ebuyMerchantService.getCarCouponNums(supplyId);
		if(!DataUtil.isEmpty(carCouponNums)) {
			Integer cpNum = Integer.valueOf(carCouponNums.get("num").toString());
			String name = carCouponNums.get("name").toString();
			String url = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.LA_BASE_URL) + "scan/carScan.json?storeId=" + supplyId;
			modelAndView.addObject("url", url);
			modelAndView.addObject("cpNum", cpNum);
			modelAndView.addObject("name", name);
		} else {
			modelAndView.addObject("url", "");
			modelAndView.addObject("cpNum", 0);
			modelAndView.addObject("name", "没有可发放的优惠券");
		}
		modelAndView.setViewName("/ebuyMerchant/carCoupon");
		return modelAndView;
	}
}