package com.cnfantasia.server.ms.ebuyMerchant.web;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.constant.EbuySupplyMerchantConstant;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchant4List;
import com.cnfantasia.server.api.ebuyorder.entity.EbuySupplyMerchantDto;
import com.cnfantasia.server.api.ebuyorder.entity.Merchant4AuditBean;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantFeeDto;
import com.cnfantasia.server.api.ebuyorder.entity.MerchantListDto;
import com.cnfantasia.server.api.ebuyorder.service.EbuyMerchantService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.service.IEbuyDeliveryMethodBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.EbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.service.IEbuySupplyMerchantBankAccountBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.service.IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseService;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;
import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.service.ISupplyMerchantDeliveryFeeSettlementBaseService;
import com.cnfantasia.server.ms.ebuyMerchant.entity.SupplyMerchantSearchParam;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;

/**
 * 运营平台，电商商户 Controller
 * @author wenfq
 */
@Controller
@RequestMapping("/supplyMerchant")
public class EbuySupplyMerchantController extends BaseController{
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private EbuyMerchantService ebuyMerchantService;
	@Resource 
	private EbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	@Resource
	private IEbuySupplyMerchantBankAccountBaseService ebuySupplyMerchantBankAccountBaseService;
	@Resource
	private IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseService ebuySupplyMerchantHasTEbuyDeliveryMethodBaseService;
	@Resource
	private IEbuyDeliveryMethodBaseService ebuyDeliveryMethodBaseService;
	@Resource
	private ISupplyMerchantDeliveryFeeSettlementBaseService supplyMerchantDeliveryFeeSettlementBaseService;
	@Resource
	private ISysParamManager sysParamManager;

	/**
	 * 供应商列表
     */
	@RequestMapping(value = "/supplyMerchantList2.html")
	public ModelAndView supplyMerchantList2(SupplyMerchantSearchParam param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/supplyMerchant/supplyMerchantList2");
		String pageIndexName = new org.displaytag.util.ParamEncoder("row")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
				0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);

		PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
		List<MerchantListDto> resList = ebuyMerchantService.getEbuySupplyMerchantType1(MapConverter.toMap(param), pageModel);
		Long total = ebuyMerchantService.getEbuySupplyMerchantType1Count(MapConverter.toMap(param));
		mav.addObject("param", param);
		mav.addObject("total", total.intValue());
		mav.addObject("resList", resList);
		return mav;
	}

	/**
	 * 跳转到新增
	 */
	@RequestMapping(value = "/addSupplyMerchant.html", method = RequestMethod.GET)
	public ModelAndView jumpToAddSupplyMerchant(EbuySupplyMerchantDto dto) {
		ModelAndView mav = new ModelAndView("/supplyMerchant/addSupplyMerchant");
		return mav;
	}

	/**
	 * 新增供应商
	 */
	@RequestMapping(value = "/addSupplyMerchant.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse addSupplyMerchant(EbuySupplyMerchantDto dto) {
		JsonResponse json = new JsonResponse();
		UserContext.getOperIdBigIntegerMustExist();
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setName(dto.getEbuySupplyMerchant().getName());
		int count = ebuySupplyMerchantBaseService.getEbuySupplyMerchantCount(MapConverter.toMap(ebuySupplyMerchant));
		if (count > 0) {
			json.setStatus("0001");
			json.setMessage("该供应商名称已被使用");
			return json;
		}
		if (ebuyMerchantService.isOmsUserExists(dto.getUserAccount())) {
			json.setStatus("0001");
			json.setMessage("分配的运营账号已存在");
			return json;
		}
		dto.getEbuySupplyMerchant().setLeastDeliveryAmt(0L);
		dto.getEbuySupplyMerchant().setExpressType(1);
		dto.getEbuySupplyMerchant().setAutoConfirmReceivePeriod(7);//7天自动收货
		ebuyMerchantService.addEbuySupplyMerchantType1(dto);
		return json;
	}

	/**
	 * 查看供应商详情
	 * @param supplyMerchantId ID
	 * @return
     */
	@RequestMapping(value = "/type1Detail.html", method = RequestMethod.GET)
	public ModelAndView supplyMerchantType1Detail(BigInteger supplyMerchantId) {
		ModelAndView mav = getSupplyMerchantType1Info(supplyMerchantId);
		mav.setViewName("/supplyMerchant/supplyMerchatType1Detail");
		return mav;
	}

	/**
	 * 跳转到编辑
	 * @param supplyMerchantId ID
	 * @return
     */
	@RequestMapping(value = "/type1Update.html", method = RequestMethod.GET)
	public ModelAndView jumpToType1Update(BigInteger supplyMerchantId) {
		ModelAndView mav = getSupplyMerchantType1Info(supplyMerchantId);
		mav.setViewName("/supplyMerchant/updMerchantType1");
		return mav;
	}

	private ModelAndView getSupplyMerchantType1Info(BigInteger supplyMerchantId) {
		ModelAndView mav = new ModelAndView();
		//供应商信息
		EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(supplyMerchantId);
		mav.addObject("ebuySupplyMerchant", ebuySupplyMerchant);
		BigDecimal revenueRate = BigDecimal.valueOf(ebuySupplyMerchant.getRevenueRate() == null ? 0d : ebuySupplyMerchant.getRevenueRate());
		String rate = String.valueOf(PriceUtil.removeTailZero(revenueRate.multiply(new BigDecimal("100"))));
		mav.addObject("revenueRate", rate);
		//供应商配置的城市
		List<Map<String, Object>> cityList = ebuyMerchantService.getSupplyMerchantType1CityList(supplyMerchantId);
		mav.addObject("cityList", cityList);
		//供应商银行账户信息
		EbuySupplyMerchantBankAccount bankAccount = new EbuySupplyMerchantBankAccount();
		bankAccount.settSupplyMerchantId(supplyMerchantId);
		List<EbuySupplyMerchantBankAccount> bankAccounts = ebuySupplyMerchantBankAccountBaseService.
				getEbuySupplyMerchantBankAccountByCondition(MapConverter.toMap(bankAccount));
		if (!DataUtil.isEmpty(bankAccounts)) {
			mav.addObject("bankAccount", bankAccounts.get(0));
		}
		//快递收费方式
		EbuySupplyMerchantHasTEbuyDeliveryMethod deliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
		deliveryMethod.settEbuySupplyMerchantFId(supplyMerchantId);
		List<EbuySupplyMerchantHasTEbuyDeliveryMethod> hasDelivMethod = ebuySupplyMerchantHasTEbuyDeliveryMethodBaseService.
				getEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(MapConverter.toMap(deliveryMethod));
		if (!DataUtil.isEmpty(hasDelivMethod)) {
			Double leastOrderAmt = 0d;
			Double delivFee = 0d;
			for (EbuySupplyMerchantHasTEbuyDeliveryMethod method : hasDelivMethod) {
				EbuyDeliveryMethod ebuyDeliveryMethod = ebuyDeliveryMethodBaseService.getEbuyDeliveryMethodBySeqId(method.gettEbuyDeliveryMethodFId());
				if (PriceUtil.div100(ebuyDeliveryMethod.getFee()).doubleValue() > delivFee) {
					delivFee = PriceUtil.div100(ebuyDeliveryMethod.getFee()).doubleValue();
				}
				if (ebuyDeliveryMethod.getPriceAmountStart() != null && ebuyDeliveryMethod.getPriceAmountEnd() == null) {
					leastOrderAmt = PriceUtil.div100(ebuyDeliveryMethod.getPriceAmountStart()).doubleValue();
				}
			}
			mav.addObject("leastOrderAmt", leastOrderAmt);
			mav.addObject("delivFee", delivFee);
			mav.addObject("hasDelivFee", leastOrderAmt + delivFee > 0.009);
		}
		//与供应商结算邮费方式
		SupplyMerchantDeliveryFeeSettlement feeSettlement = new SupplyMerchantDeliveryFeeSettlement();
		feeSettlement.settEbuySupplyMerchantFId(supplyMerchantId);
		List<SupplyMerchantDeliveryFeeSettlement> feeSettlements = supplyMerchantDeliveryFeeSettlementBaseService.
				getSupplyMerchantDeliveryFeeSettlementByCondition(MapConverter.toMap(feeSettlement));
		if (!DataUtil.isEmpty(feeSettlements)) {
			Double leastSettleOrderAmt = 0d;
			Double settleDelivFee = 0d;
			for (SupplyMerchantDeliveryFeeSettlement settlement : feeSettlements) {
				if (PriceUtil.div100(settlement.getSettlementFee()).doubleValue() > settleDelivFee) {
					settleDelivFee = PriceUtil.div100(settlement.getSettlementFee()).doubleValue();
				}
				if (settlement.getAmountStart() != null && settlement.getAmountEnd() == null) {
					leastSettleOrderAmt = PriceUtil.div100(settlement.getAmountStart()).doubleValue();
				}
			}
			mav.addObject("leastSettleOrderAmt", leastSettleOrderAmt);
			mav.addObject("settleDelivFee", settleDelivFee);
		}

		return mav;
	}

	/**
	 * 修改
	 * @param dto
	 * @return
     */
	@RequestMapping(value = "/type1Update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse type1Update(EbuySupplyMerchantDto dto) {
		JsonResponse json = new JsonResponse();
		UserContext.getOperIdBigIntegerMustExist();
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setName(dto.getEbuySupplyMerchant().getName());
		PageModel pageModel = new PageModel(0, 1);
		List<EbuySupplyMerchant> list = ebuySupplyMerchantBaseService
				.getEbuySupplyMerchantByCondition(MapConverter.toMap(ebuySupplyMerchant), pageModel);
		if (!DataUtil.isEmpty(list) && list.get(0).getId().compareTo(dto.getEbuySupplyMerchant().getId()) != 0) {
			json.setStatus("0001");
			json.setMessage("该供应商名称已被使用");
			return json;
		}
		ebuyMerchantService.updEbuySupplyMerchantType1(dto);
		return json;
	}

	/**
	 * 楼下店铺列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/supplyMerchantList.html")
	public ModelAndView supplyMerchantList(HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("address", request.getParameter("address"));
		paramMap.put("linkPhone", request.getParameter("linkPhone"));
		paramMap.put("linkName", request.getParameter("linkName"));
		
		if(!"-1".equals(request.getParameter("storeAuditStatus"))){
			paramMap.put("storeAuditStatus", request.getParameter("storeAuditStatus"));		
		}
		paramMap.put("storeAuditTimeStart", request.getParameter("storeAuditTimeStart"));
		paramMap.put("storeAuditTimeEnd", request.getParameter("storeAuditTimeEnd"));
		paramMap.put("ownerName", request.getParameter("ownerName"));
		
		if(!"-1".equals(request.getParameter("ownerAuditStatus"))){
			paramMap.put("ownerAuditStatus", request.getParameter("ownerAuditStatus"));
		}
		paramMap.put("ownerAuditTimeStart", request.getParameter("ownerAuditTimeStart"));
		paramMap.put("ownerAuditTimeEnd", request.getParameter("ownerAuditTimeEnd"));
		paramMap.put("storeName", request.getParameter("storeName"));
				
		int resultSize = ebuyMerchantService.qrySupplyMerchantList_forCount(paramMap);
		
		request.setAttribute("resultSize", resultSize);
		
		updatePageParam(request, paramMap);
		
		List<EbuySupplyMerchant4List> esmList = ebuyMerchantService.qrySupplyMerchantList(paramMap);
		
		request.setAttribute("resList", esmList);
		
		return new ModelAndView("/supplyMerchant/supplyMerchantList");
	}

	private void updatePageParam(HttpServletRequest request, Map<String, Object> paramMap) {
		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
	}
	
	/**
	 * 店铺审核
	 * @param request
	 * @return
	 */
	@RequestMapping("/esmAudit.html")
	public ModelAndView esmAudit(HttpServletRequest request) {
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView("/supplyMerchant/shopAudit");
		
		Merchant4AuditBean bean = ebuyMerchantService.getMerchat4AuditOrView(id);
		if(StringUtils.isNotBlank(id)){
			List<MerchantFeeDto> merchantFees = ebuyMerchantService.getMerchantFeeList(new BigInteger(id));
			mav.addObject("merchantFees", merchantFees);
		}
		mav.addObject("bean", bean);
		
		return mav;
	}
	
	/**
	 * 店铺审核保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEsmAudit.json")
	@ResponseBody
	public JsonResponse saveEsmAudit(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		String auditResult = ParamUtils.getString(request, "auditResult");
		String auditDesc = ParamUtils.getString(request, "auditDesc");
		if("pass".equals(auditResult)){
			Double revenueRate = ParamUtils.getDouble(request, "revenueRate", 0);
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setStoreAuditStatus(1);
			esm.setRevenueType(EbuySupplyMerchantConstant.RevenueType.AGENT);
			esm.setStoreAuditTime(DateUtils.getCurrentDate());
			esm.setStoreAuditorId(UserContext.getCurrUser().getId());
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			esm.setRevenueRate(revenueRate/100);
			esm.setAutoConfirmReceivePeriod(3);//3天自动收货
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);

			String isAddOmsUser = ParamUtils.getString(request, "isAddOmsUser");
			if ("1".equals(isAddOmsUser)) {
				ebuyMerchantService.regOmsUserAfterPass(id);
			}
		}else if("notpass".equals(auditResult)){
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setStoreAuditStatus(2);
			esm.setStoreAuditDesc(auditDesc);
			esm.setStoreAuditTime(DateUtils.getCurrentDate());
			esm.setStoreAuditorId(UserContext.getCurrUser().getId());
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("审核结果保存成功");
		return jsonResponse;
	}
	
	/**
	 * 店主审核
	 * @param request
	 * @return
	 */
	@RequestMapping("/esmOwnerAudit.html")
	public ModelAndView esmOwnerAudit(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		Merchant4AuditBean bean = ebuyMerchantService.getMerchat4AuditOrView(id);
		request.setAttribute("bean", bean);
		return new ModelAndView("/supplyMerchant/shopOwnerAudit");
	}
	
	/**
	 * 店主审核保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEsmOwnerAudit.json")
	@ResponseBody
	public JsonResponse saveEsmOwnerAudit(HttpServletRequest request) {
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		String auditResult = ParamUtils.getString(request, "auditResult");
		String auditDesc = ParamUtils.getString(request, "auditDesc");
		
		if("pass".equals(auditResult)){
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setOwnerAuditStatus(1);
			esm.setRevenueType(2);
			esm.setOwnerAuditTime(DateUtils.getCurrentDate());
			esm.setOwnerAuditorId(UserContext.getCurrUser().getId());
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);
		}else if("notpass".equals(auditResult)){
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setRevenueType(2);
			esm.setOwnerAuditStatus(2);
			esm.setOwnerAuditTime(DateUtils.getCurrentDate());
			esm.setOwnerAuditorId(UserContext.getCurrUser().getId());
			esm.setOwnerAuditDesc(auditDesc);
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("审核结果保存成功");
		return jsonResponse;
	}
	
	
	/**
	 * 店铺查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/esmView.html")
	public ModelAndView esmView(String id) {
		ModelAndView mav = new ModelAndView("/supplyMerchant/shopView");
		Merchant4AuditBean bean = ebuyMerchantService.getMerchat4AuditOrView(id);
		if(StringUtils.isNotBlank(id)){
			List<MerchantFeeDto> merchantFees = ebuyMerchantService.getMerchantFeeList(new BigInteger(id));
			mav.addObject("merchantFees", merchantFees);
		}
		mav.addObject("bean", bean);
		return mav;
	}
	
	/**
	 * 店铺启用禁用
	 * @param request
	 * @return
	 */
	@RequestMapping("/esmViewEnabled.json")
	@ResponseBody
	public JsonResponse esmViewEnabled(HttpServletRequest request) {
		JsonResponse response = new JsonResponse();
		BigInteger id = ParamUtils.getBigInteger(request, "esmId", null);
		Integer isEnable = ParamUtils.getInteger(request, "isEnable", null);

		if (id == null || isEnable == null) {
			response.setStatus("0001");
			response.setMessage("esmId and isEnable param can't be null");
			return response;
		}
		
		if (isEnable == 0) { //不启用，即删除
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setSys0DelState(1);
			esm.setSys0UpdTime(DateUtil.formatSecond.format(new Date()));  
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);
			response.setMessage("禁用成功");
		} else {
			EbuySupplyMerchant esm = new EbuySupplyMerchant();
			esm.setId(id);
			esm.setSys0DelState(0);
			esm.setSys0UpdTime(DateUtil.formatSecond.format(new Date()));  
			esm.setSys0UpdUser(UserContext.getCurrUser().getId());
			ebuySupplyMerchantBaseService.updateEbuySupplyMerchant(esm);
			response.setMessage("启用成功");
		}

		return response;
	}

	@RequestMapping("/getSupplyMerchantListByName.json")
	@ResponseBody
	public List<Map<String, Object>> getMerchantListByName(String name) {
		EbuySupplyMerchant merchant = new EbuySupplyMerchant();
		merchant.setName(name);
		List<EbuySupplyMerchant> merchants = ebuySupplyMerchantBaseService.
				getEbuySupplyMerchantByConditionDim(MapConverter.toMap(merchant), new PageModel(0, 20));
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		String storeId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
		for (EbuySupplyMerchant ebuySupplyMerchant : merchants) {
			Map<String, Object> res = new HashMap<String, Object>();
			res.put("name", ebuySupplyMerchant.getName());
			res.put("id", ebuySupplyMerchant.getId());
			res.put("addressDesc", ebuySupplyMerchant.getAddress());
			res.put("type", ebuySupplyMerchant.getType());
			res.put("isExperienceStore", ebuySupplyMerchant.getId().toString().equals(storeId));
			resList.add(res);
		}
		return resList;
	}

	/**
	 * 跳转至店铺专修页面
	 * @param merchantId 店铺ID
	 * @return
     */
	@RequestMapping(value = "/editProPic.html", method = RequestMethod.GET)
	public ModelAndView jumpToEditProPic(BigInteger merchantId) {
		ModelAndView mav = new ModelAndView("/supplyMerchant/editProPic");
		//查询店铺信息
		EbuySupplyMerchant supplyMerchant = ebuySupplyMerchantBaseService
				.getEbuySupplyMerchantBySeqId(merchantId);
		//店铺详情图片
		if (!DataUtil.isEmpty(supplyMerchant.getShopPhotoes())) {
			List<String> pics = Arrays.asList(supplyMerchant.getShopPhotoes().split(";"));
			mav.addObject("picList", pics);
		}

		mav.addObject("supplyMerchant", supplyMerchant);
		return mav;
	}

	/**
	 * 上传专修
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editProPic.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse editProPic(HttpServletRequest request) {
		JsonResponse json = new JsonResponse();
		BigInteger merchantId = ParamUtils.getBigInteger(request, "supplyMerchantId", null);
		String[] imgUrls = request.getParameterValues("imgUrl");
		//未传值直接返回成功
		if (null == merchantId) {
			return json;
		}
		try {
			String fileName = uploadProPic(request);
			String shopPhotoes = uploadProPics(request, imgUrls);
			EbuySupplyMerchant merchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			if(!DataUtil.isEmpty(fileName)) {
				merchant.setProPic(fileName);
			}
			for(String imgUrl : imgUrls) {
				if(!DataUtil.isEmpty(imgUrl)) {
					shopPhotoes += imgUrl + ";";
				}
			}
			if(shopPhotoes.length() < 10) {//没有图片
				shopPhotoes = merchant.getShopPhotoes() == null ? "" :  merchant.getShopPhotoes().split(";")[0];
			}
			merchant.setShopPhotoes(shopPhotoes);
			ebuyMerchantService.updateEbuySupplyMerchantShopPic(merchant);
		} catch (Exception e) {
			e.printStackTrace();
			json.setStatus("0001");
			json.setMessage("上传专修图片失败！");
		}
		return json;
	}

	private String uploadProPics(HttpServletRequest request, String[] imgUrls) throws  IllegalStateException, IOException {
		// 浏览图片
		String picImgs = "";
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 浏览图片
			List<MultipartFile> picImageFiles = multipartRequest.getFiles("shopPhotoes");
			if(null!=picImageFiles && picImageFiles.size()>0){
				for (MultipartFile picImageFile : picImageFiles) {
					if (picImageFile != null && !StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片
						//重命名并使用原后缀
						int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
						String fileName = UUIDGenerater.getFileName() + picImageFile.getOriginalFilename().substring(indexOfDot);
						String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH)
								+ PathConstants.Ebuy_Store_Pic;
						File fileC = new File(filePath + fileName);
						if (!fileC.exists()) {
							fileC.mkdirs();
						}
						picImageFile.transferTo(fileC);
						picImgs += (fileName) + ";";
					}
				}
			}
		}
		return picImgs;
	}

	private String uploadProPic(HttpServletRequest request) throws IllegalStateException, IOException {
		String fileName = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			MultipartFile uploadImageFile = multipartRequest.getFile("photoimage");
			if (uploadImageFile != null && StringUtils.isNotEmpty(uploadImageFile.getOriginalFilename())) {
				//重命名并使用原后缀
				int indexOfDot = uploadImageFile.getOriginalFilename().indexOf(".");
				fileName = UUIDGenerater.getFileName() + uploadImageFile.getOriginalFilename().substring(indexOfDot);
				String filePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH)
						+ PathConstants.Ebuy_Store_Pic;
				File fileC = new File(filePath + fileName);
				if (!fileC.exists()) {
					fileC.mkdirs();
				}
				uploadImageFile.transferTo(fileC);
			}
		}
		return fileName;
	}

	@RequestMapping(value = "/addStoreRange.json", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse addStoreRange(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String[] gbIds = ParamUtils.getStrings(request , "gbIds", ",");
		BigInteger shopId = ParamUtils.getBigInteger(request, "id", null);
		if(shopId != null) {
			String msg = ebuyMerchantService.addStoreRange(gbIds, shopId);
			jsonResponse.setMessage(msg);
		} else {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("信息异常，请刷新重试！");
		}
		return  jsonResponse;
	}

	@RequestMapping(value = "/delStoreRange.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse delStoreRange(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		BigInteger shopId = ParamUtils.getBigInteger(request, "shopId", null);
		if(gbId != null  && shopId != null) {
			String msg = ebuyMerchantService.delStoreRange(shopId, gbId);
			jsonResponse.setMessage(msg);
		} else {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("操作超时，请稍后重试！");
		}
		return  jsonResponse;
	}
	
}
