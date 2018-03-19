package com.cnfantasia.server.api.ebuy.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.entity.SimpleResultMap;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.ebuy.domain.Share;
import com.cnfantasia.server.api.ebuy.entity.FightgroupProductEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.ebuy.service.IGroupPurchaseService;
import com.cnfantasia.server.api.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service.IEbuyProductIntroducePicBaseService;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductParameters.service.IEbuyProductParametersBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;


/**
 * 超市拼购
 * @author ligs
 *
 */
@Controller
@RequestMapping("/groupPurchase")
public class GroupPurchaseController extends BaseController{
//	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IGroupPurchaseService groupPurchaseService;
	
    @Resource
    private IRoomService roomService;
    
	@Resource
	private ISysParamManager sysParamManager;
	
	@Resource
	private ISysParamParser fileServerParamParser;
	
	@Resource
	private ICompanyService companyService;
	
	@Resource
	private IEbuyProductIntroducePicBaseService  ebuyProductIntroducePicBaseService;
	
	@Resource
	private IEbuyProductParametersBaseService ebuyProductParametersBaseService;
	
	@Resource
	private IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService;

	@Resource
	private IEbuyFightSupplyMerchantService ebuyFightSupplyMerchantService;

	@Resource
	private IUserCouponService userCouponService;
	
	@Resource
	private IEbuyAdvertiseService ebuyAdvertiseService;

	/**
	 * 根据当前门牌选择不同自提点拼购商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/productList.html")
	public String qrygroupPurchaseList(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//1.搜集参数
//		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//获取登录用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("50003");
		RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
		BigInteger gbId = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId();
//		BigInteger gbId = new BigInteger("10090124");
		model.addAttribute("picserverUrl", getrefundPicUrl());
        String ziTiId=request.getParameter("ziTiId");
		String productId = request.getParameter("productId");
		model.addAttribute("productId", productId);
		BigInteger productIdQry = productId == null ? null : new BigInteger(productId);

        if(ziTiId!=null && !ziTiId.equals("")){
        	EbuyFightSupplyMerchant zitiMsg = ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantBySeqId(new BigInteger(ziTiId));
        	paramMap.put("fightMerchantId", zitiMsg.getId());
        	//商品列表
			if (productIdQry != null) {
				paramMap.put("productId", productIdQry);
			}
			paramMap.put("appType", "1");
			List<FightgroupProductEntity> proList = groupPurchaseService.selectproList(paramMap);
        	request.getSession().setAttribute("ziTiId", ziTiId);
        	if(proList != null && proList.size() > 0){
        		//一个商品直接跳详情页
        		return "forward:/groupPurchase/productDetail.html?productId="+proList.get(0).getFightProductId();
        	} else {
        		return "/ebuy/ebuyFight/noItem";
        	}/*else{
            	model.addAttribute("proList", proList);
            	return "/ebuy/ebuyFight/groupBuyingList";
        	}*/
        }else{
        	//未选择过自提点
			paramMap.put("tGroupBuildingId", gbId);
			List<EbuyFightMerchantHasGroupBuilding> ziTiGbList = ebuyFightSupplyMerchantService.getEbuyFightMerchantHasGbByGbidAndProductId(gbId, productIdQry);
        	if(ziTiGbList!=null && ziTiGbList.size()>0){
        		if(ziTiGbList.size()==1){//只有一个则不用选择自提点
                	paramMap.put("fightMerchantId", ziTiGbList.get(0).gettEbuyFightSupplyMerchantFId());
                	//商品列表
					if (productIdQry != null) {
						paramMap.put("productId", productIdQry);
					}
					List<FightgroupProductEntity> proList = groupPurchaseService.selectproList(paramMap);
                	if(proList!=null && proList.size()==1){
                    	request.getSession().setAttribute("ziTiId", ziTiId);
                		//一个商品直接跳详情页
                		return "forward:/groupPurchase/productDetail.html?productId="+proList.get(0).getFightProductId();
                	}else if(proList==null || proList.size()<1){
                		return "/ebuy/ebuyFight/noItem";
                	}else{
                    	model.addAttribute("proList", proList);
                    	return "/ebuy/ebuyFight/groupBuyingList";
                	}
        		}else{//多个就返回自提点列表
        			List<EbuyFightSupplyMerchant> zitiList = new ArrayList<EbuyFightSupplyMerchant>();
        			for(EbuyFightMerchantHasGroupBuilding fightGb:ziTiGbList){
        				zitiList.add(ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantBySeqId
        						(fightGb.gettEbuyFightSupplyMerchantFId()));
        			}
                	model.addAttribute("zitiList", zitiList);
                	return "/ebuy/ebuyFight/groupBuyingAddressList";
        		}
        	}else{//如果当前门牌没有自提点，显示所有自提点
//            	paramMap.put("fightMerchantId", new BigInteger("1"));
//            	//商品列表
//            	List<FightgroupProductEntity> proList = groupPurchaseService.selectproList(paramMap);
            	model.addAttribute("zitiList", ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantByCondition(null));
            	return "/ebuy/ebuyFight/groupBuyingAddressList";
        	}
        }
	}
	/**
	 * 拼购商品详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/productDetail.html")
	public String qrygroupPurchaseDetail(HttpServletRequest request,ModelMap model){
		//1.搜集参数
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		FightgroupProductEntity fightProduct = groupPurchaseService.selectProDetail(productId, 1);
		//客服电话
		String phone = companyService.getCompanyServiceInfo().getTel();
		model.addAttribute("phone", phone);
		model.addAttribute("picserverUrl", getrefundPicUrl());
		model.addAttribute("fightProduct", fightProduct);
		model.addAttribute("picList",fightProduct.getTitlePics());
		model.addAttribute("endTime", fightProduct.getExpireDate());
		model.addAttribute("proItroduces", fightProduct.getProIntroduceList());
		
		Share share = new Share();
		if(fightProduct.getTitlePics() != null && fightProduct.getTitlePics().size() > 0) {
			String pic = fightProduct.getTitlePics().get(0);
			pic = pic.substring(0, pic.lastIndexOf(".")) + "/72" + pic.substring(pic.lastIndexOf("."), pic.length());
			share.setPic(getrefundPicUrl() + pic);
//			share.setPic(getrefundPicUrl() + fightProduct.getTitlePics().get(0));
		}
		share.setTittle(fightProduct.getName());
		if(fightProduct.getDesc() != null) {
			share.setDesc(fightProduct.getDesc().replaceAll("\\n|\\t|\\r", ""));
		}
		share.setUrl(CnfantasiaCommbusiUtil.getSysParaValue("laUrl") + "/laGroupPurchase/laProductDetail.do?productId=" + productId);
		model.addAttribute("share", share);
		
		if(fightProduct.getFightStatus() == 1 || fightProduct.getFightStatus() == 1){
			return "/ebuy/ebuyFight/groupBuying";
		} else if (fightProduct.getFightStatus() == 0) {
			return "/ebuy/ebuyFight/groupBuyingPresell";
		} else {
			return "/ebuy/ebuyFight/groupBuyingEnd";
		}
	}
	
	@RequestMapping("/qryProIntroduce.html")
	public String qryProIntroduce(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//1.搜集参数
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		if(productId!=null){
			paramMap.put("tEbuyProductFId", productId);
			List<EbuyProductParameters> parameteList = ebuyProductParametersBaseService.getEbuyProductParametersByCondition(paramMap);
			List<EbuyProductIntroducePic> picList = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);
			model.addAttribute("parameteList", parameteList);
			model.addAttribute("picList", picList);
			model.addAttribute("picserverUrl", getrefundPicUrl());
		}
		return "/ebuy/ebuyFight/groupBuyingDetails";
	}
	
	@RequestMapping("/getFightOrderMsg.json")
	@ResponseBody
	public JsonResponse getFightOrderMsg(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		Integer buyNum = ParamUtils.getInteger(request, "productNum", 1);
		buyNum = buyNum < 1 ? 1 : buyNum;
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Map<String,Object> resMap = new SimpleResultMap<String, Object>();
		FightgroupProductEntity fightProduct = groupPurchaseService.selectProDetail(productId, 1);
		if(fightProduct!=null){
			// resMap.put("id", fightProduct.getZitiDian()!=null?fightProduct.getZitiDian().getId():"");//自提点id
			resMap.put("id", fightProduct.getMerchantName().getId());//自提点id--改为供应商id，月亮说前端用这个id当供应商id使用的
			resMap.put("signalMer_Fee", 0);
			resMap.put("signalMer_totalCount_productType", 1);
			resMap.put("leastDeliveryAmt", 0);
			resMap.put("signalMer_totalCountsignalMer_totalCount", buyNum);//拼购商品为1
			resMap.put("address", fightProduct.getZitiDian().getDelivAddress());
			resMap.put("productList",productListToJsonList(fightProduct, buyNum));//商品信息
			resMap.put("tel", fightProduct.getZitiDian().getTel());
			resMap.put("name", fightProduct.getZitiDian().getName());
			resMap.put("signalMer_Deliv_Info","运费0元");
			resMap.put("signalMer_totalMoney", PriceUtil.div100(fightProduct.getFightPrice()*buyNum));
			resMap.put("maxDeliveryAmt", 0.00);
			resMap.put("signalMer_totalMoney_Fee", PriceUtil.div100(fightProduct.getFightPrice()*buyNum));
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list.add(resMap);
			jsonResponse.putData("list", list);
			//消费券
			jsonResponse.putData("ext_totalCount", 10);//商品总数为1
			jsonResponse.putData("ext_totalCount_productType", 1);
			jsonResponse.putData("ext_totalMoney", PriceUtil.div100(fightProduct.getFightPrice()*buyNum));
			jsonResponse.putData("ext_Fee", 0);
			jsonResponse.putData("ext_totalMoney_Fee", PriceUtil.div100(fightProduct.getFightPrice()*buyNum));

			//查询可用优惠券 todo 物品兑换券没考虑
			UserCoupon userCoupon = new UserCoupon();
			Coupon coupon = new Coupon();
			coupon.setLeastSpendUse(PriceUtil.div100(fightProduct.getFightPrice()*buyNum).intValue());
			coupon.setUseType(CouponUseTypeConstant.EBUY_PRODUCT);
			userCoupon.setCoupon(coupon);
			userCoupon.settUserFId(userId);
			userCoupon.setStatus(UserCouponStatus.VALID);
			Map param = MapConverter.toMap(userCoupon);
			List<UserCouponEntity> coupons = userCouponService.getUserCouponList(param);
			jsonResponse.putData("ext_isContainCoupon", !(coupons == null || coupons.isEmpty()));
			jsonResponse.putData("ext_couponCombiInfo", coupons);
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("该商品不存在或商品id为空！！！");
		}
		return jsonResponse;
	}
	
	private List<Map<String, Object>> productListToJsonList(FightgroupProductEntity fightProduct, Integer buyNum) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<String> resList = new ArrayList<String>();
		Map<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("selNum", 0);
		proMap.put("priceDiscountPoint", PriceUtil.div100(fightProduct.getFightPrice()));
		proMap.put("ebuyAuthList", resList);
		proMap.put("picBaseMini", getrefundPicUrl()+fightProduct.getPicName());
		proMap.put("desc", fightProduct.getDesc()!=null?fightProduct.getDesc():"");
		proMap.put("productQty", buyNum);
		proMap.put("supplyMerchantName", fightProduct.getMerchantName());
		proMap.put("defaultDeliveryFee", 0);
		proMap.put("defaultDeliveryName", 0);
		proMap.put("priceUnit", "元");
		proMap.put("filmTickets",0);
		proMap.put("pricePoint",fightProduct.getPricePoint());
		proMap.put("picBase", getrefundPicUrl()+fightProduct.getPicName());
		proMap.put("id",fightProduct.getId());
		proMap.put("picBigDescs", resList);
		proMap.put("specialProductType", 1);
		proMap.put("price", PriceUtil.div100(fightProduct.getFightPrice()));
		proMap.put("name",fightProduct.getName());
		proMap.put("specification","");
		proMap.put("picMiniDescs", resList);
		proMap.put("picSpecial", "");
		proMap.put("leftCount",fightProduct.getFightNum());
		proMap.put("priceDiscount",PriceUtil.div100(fightProduct.getFightPrice()));
		proMap.put("priceDiscountPointDesc",0);
		proMap.put("pointType", "1");
		proMap.put("productLeft", fightProduct.getLeftCount());
		list.add(proMap);
		return list;
	}
	@RequestMapping("/qryZitiAddressDetail.json")
	@ResponseBody
	public JsonResponse qryZitiAddressDetail(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//获取登录用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		EbuyFightSupplyMerchant  merchant= null;
		//获取当前的自提点id(安卓无法从Session获取自提点Id)
//      String ziTiId = (String)request.getSession().getAttribute("ziTiId");
		BigInteger ziTiId = ParamUtils.getBigInteger(request, "ziTiId", null);
        if(ziTiId!=null){
        	merchant=ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantBySeqId
			(ziTiId);
    		jsonResponse.putData("id",merchant.getId());//岗亭id
    		jsonResponse.putData("addressTotal",merchant.getName()+"("+merchant.getDelivAddress()+")");//岗亭信息
    		jsonResponse.putData("isDefault", 0);
    		jsonResponse.putData("targetId", "1");
    		jsonResponse.putData("addressArea", merchant.getName());
    		jsonResponse.putData("addressDetail", merchant.getDelivAddress());
    		jsonResponse.putData("targetType", "2");//普通输入
    		EbuyOrder order = groupPurchaseService.selectUserFightMsg(userId);
    		jsonResponse.putData("userName", order!=null?order.getDelivPeopleName():null);
    		jsonResponse.putData("userPhone", order!=null?order.getDelivPhone():null);
        }else{
        	jsonResponse.setStatus("0001");
        	jsonResponse.setMessage("未选择自提点！！");
        }
		return jsonResponse;
	}
	
	@RequestMapping("/getEbuyFightOrder.json")
	@ResponseBody
	@RepeatReqValidate
	public JsonResponse getEbuyFightOrder(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String userName = ParamUtils.getString(request, "userName", null);
		String phone = ParamUtils.getString(request, "phone",null);
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		Integer buyNum = ParamUtils.getInteger(request, "productNum", 1);
		buyNum = buyNum < 1 ? 1 : buyNum;
		Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
		//粮票
		Long hbAmount = null;
		if (!StringUtils.isEmpty(request.getParameter("hbAmount"))) {
			Double hbAmountDouble = null;
			hbAmountDouble = Double.parseDouble(request.getParameter("hbAmount"));//粮票金额
			hbAmount = PriceUtil.multiply100(hbAmountDouble);
		}
		//消费券
		Set<BigInteger> couponIdSet = null;
		if(!org.apache.commons.lang.StringUtils.isEmpty(request.getParameter("couponIdList"))){//优惠的Id列表
			List<BigInteger> couponIdList = JSON.parseArray(request.getParameter("couponIdList"), BigInteger.class);
			couponIdSet = new HashSet<BigInteger>();
			couponIdSet.addAll(couponIdList);
		}

		//查询拼购商品详情
		FightgroupProductEntity fightProduct = groupPurchaseService.selectProDetail(productId, 1);
		if (fightProduct.getLeftCount().intValue() < buyNum) {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("该商品不足，只有" + fightProduct.getLeftCount() + "份了！");
		}
		//库存为零时，不能生成订单
		if(fightProduct.getFightStatus()==2){
			//返回结果
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("该商品已经售罄!!!");
		} else if (fightProduct.getFightStatus() == 3) {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("该拼购已结束!!!");
		} else if (fightProduct.getFightStatus() == 0) {
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("该拼购尚未开始!!!");
		} else if (fightProduct.getFightStatus() == 1) {
			//生成订单
			Map<String, Object> resMap = groupPurchaseService.saveEbuyOrderFightNew(fightProduct, hbAmount,
					couponIdSet, subChannelId, userId, userName, phone, buyNum);
			//返回结果
			jsonResponse.setDataValue(resMap);
		}
		return jsonResponse;
	}
	
	//获取图片地址
	private String getrefundPicUrl() {
		String basePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
		/*FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
		String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();*/
		String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
		return imageServerUrl+basePath;
	}
	
	protected Integer parsePointType(HttpServletRequest request){
		Integer pointType = EbuyDict.PointType.EBUY_PRODUCT;//默认电商商品
//		String pointTypeStr = request.getParameter("pointType");
//		if(!StringUtils.isEmpty(pointTypeStr)){
//			pointType = Integer.parseInt(pointTypeStr);
//		}
		return pointType;
	}
	/**
	 * 轻应用超市接口
	 */
	//自提点列表
	@RequestMapping("/ziTiDianList.html")
	@ResponseBody
	public JsonResponse ZiTiDianList(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		List<EbuyFightSupplyMerchant> resList = ebuyFightSupplyMerchantBaseService.getEbuyFightSupplyMerchantByCondition(null);
		List<EbuyFightSupplyMerchant> resList = ebuyAdvertiseService.queryFightGroupProductZiti();
		jsonResponse.putData("zitiList", resList);
		return jsonResponse;
	}
	/**
	 * 轻应用拼购商品列表
	 */
	@RequestMapping("/laProductList.html")
	@ResponseBody
	public JsonResponse qryLaGroupPurchaseList(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String ziTiId=request.getParameter("ziTiId");
		if(ziTiId!=null){
	    	paramMap.put("fightMerchantId", ziTiId);
	    	paramMap.put("appType", "3");
	    	//商品列表
	    	List<FightgroupProductEntity> proList = groupPurchaseService.selectproList(paramMap);
			jsonResponse.putData("picserverUrl", getrefundPicUrl());
	    	jsonResponse.putData("fightProductList", proList);
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("自提点为空！！！");
		}
		return jsonResponse;
	}

	
	/**
	 * 轻应用拼购商品详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/laProductDetail.html")
	@ResponseBody
	public JsonResponse qryLaGroupPurchaseDetail(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		if(productId!=null){
			FightgroupProductEntity fightProduct = groupPurchaseService.selectProDetail(productId, 3);
			if(fightProduct!=null){
				jsonResponse.putData("fightProduct", fightProduct);
				jsonResponse.putData("picserverUrl", getrefundPicUrl());
				//客服电话
				String phone = companyService.getCompanyServiceInfo().getTel();
				jsonResponse.putData("phone", phone);
			}else{
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("商品不存在！！！");
			}
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("商品id为空！！！");
		}
		return jsonResponse;
	}
	/**
	 * 轻应用图文详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/qryLaProIntroduce.html")
	@ResponseBody
	public JsonResponse qryLaProIntroduce(HttpServletRequest request,ModelMap model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		//获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		if(productId!=null){
			paramMap.put("tEbuyProductFId", productId);
			List<EbuyProductParameters> parameteList = ebuyProductParametersBaseService.getEbuyProductParametersByCondition(paramMap);
			List<EbuyProductIntroducePic> picList = ebuyProductIntroducePicBaseService.getEbuyProductIntroducePicByCondition(paramMap);
			jsonResponse.putData("picList", picList);
			jsonResponse.putData("parameteList", parameteList);
			jsonResponse.putData("picserverUrl", getrefundPicUrl());
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("商品id为空！！！");
		}
		return jsonResponse;
	}
	@RequestMapping("/getLaFightOrderMsg.json")
	@ResponseBody
	public JsonResponse getLaFightOrderMsg(HttpServletRequest request,ModelMap model){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger productId = ParamUtils.getBigInteger(request, "productId", null);
		Integer buyNum = ParamUtils.getInteger(request, "productNum", 1);
		//获取登录用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		FightgroupProductEntity fightProduct = groupPurchaseService.selectProDetail(productId, 3);
		jsonResponse.putData("fightProduct", fightProduct);
		jsonResponse.putData("picserverUrl", getrefundPicUrl());
		EbuyOrder order = groupPurchaseService.selectUserFightMsg(userId);
		jsonResponse.putData("userName", order!=null?order.getDelivPeopleName():"");
		jsonResponse.putData("userPhone", order!=null?order.getDelivPhone():"");

		//查询可用优惠券 todo 物品兑换券没考虑
		UserCoupon userCoupon = new UserCoupon();
		Coupon coupon = new Coupon();
		coupon.setLeastSpendUse(PriceUtil.div100(fightProduct.getFightPrice()*buyNum).intValue());
		coupon.setUseType(CouponUseTypeConstant.EBUY_PRODUCT);
		userCoupon.setCoupon(coupon);
		userCoupon.settUserFId(userId);
		userCoupon.setStatus(UserCouponStatus.VALID);
		Map param = MapConverter.toMap(userCoupon);
		List<UserCouponEntity> coupons = userCouponService.getUserCouponList(param);
		jsonResponse.putData("ext_isContainCoupon", !(coupons == null || coupons.isEmpty()));
		jsonResponse.putData("ext_couponCombiInfo", coupons);

		return jsonResponse;
	}
}
