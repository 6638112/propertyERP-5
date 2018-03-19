/**   
* Filename:    EbuyController.java   
* @version:    1.0  
* Create at:   2014年5月16日 上午12:36:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.EbuyAuthPicConfig;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.comments.entity.CommentsEntity;
import com.cnfantasia.server.api.commonBusiness.entity.UserIdType;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonDeviceService;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.commonBusiness.util.EbuyChannelParseUtil;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.coupon.service.ICouponService;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.ebuy.dao.IEbuyDao;
import com.cnfantasia.server.api.ebuy.domain.FilmTicketDo;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarEntityFamilyGroup;
import com.cnfantasia.server.api.ebuy.entity.EbuyBuyCarHasTEbuyProductEntity_Product;
import com.cnfantasia.server.api.ebuy.entity.EbuyConfirm_ProductAndCount;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryAddressEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyExtandBuyParam;
import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;
import com.cnfantasia.server.api.ebuy.entity.EbuyFlowRecharge;
import com.cnfantasia.server.api.ebuy.entity.EbuyIdentifyInfo;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderHasTEbuyProductEntity_Product;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductHasTEbuyAuthEntity_EbuyAuth;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductRecommendEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.ebuy.entity.EbuySupplyMerchantEntity;
import com.cnfantasia.server.api.ebuy.entity.MerchantIdDeliveryType;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.ebuy.entity.SimpleDelivAddress;
import com.cnfantasia.server.api.ebuy.service.IEbuyFilmTicketService;
import com.cnfantasia.server.api.ebuy.service.IEbuyFlowRechargeService;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.ebuy.service.IEbuyService;
import com.cnfantasia.server.api.ebuyInvoice.service.IEbuyInvoiceService;
import com.cnfantasia.server.api.ebuyorder.util.RechargePackageUtils;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.flashDealActivity.constant.FlashDealActivityDict;
import com.cnfantasia.server.api.limitBuy.service.ILimitBuyService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.RoomBaseInfo;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.api.userCoupon.entity.CouponUseEndDateComparator;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.service.EbuyDeliveryAddressBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;
import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;
import com.cnfantasia.server.domainbase.ebuyFightOrder.service.IEbuyFightOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service.IEbuyOrderHasTEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuyRefundAudit.entity.EbuyRefundAudit;
import com.cnfantasia.server.domainbase.ebuyRefundAudit.service.IEbuyRefundAuditBaseService;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;
import com.cnfantasia.server.domainbase.ebuyRefundOrder.service.IEbuyRefundOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;
import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.service.IEbuyRefundOrderProductBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.service.IFlashDealBuyRecordBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Filename:    EbuyController.java
 * @version:    1.0.0
 * Create at:   2014年5月16日 上午12:36:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月16日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/ebuy")
public class EbuyController extends BaseController{
	
	private Log logger = LogFactory.getLog(getClass());
	
	private IEbuyFilmTicketService ebuyFilmTicketService;
	
	private IEbuyFlowRechargeService ebuyFlowRechargeService;

	
	
	protected IEbuyService ebuyService;
	public void setEbuyService(IEbuyService ebuyService) {
		this.ebuyService = ebuyService;
	}
	protected IEbuyNewService ebuyNewService;
	
	public void setEbuyNewService(IEbuyNewService ebuyNewService) {
		this.ebuyNewService = ebuyNewService;
	}
	protected IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
//	private IEbuyProductTypeBaseService ebuyProductTypeBaseService;
//	public void setEbuyProductTypeBaseService(IEbuyProductTypeBaseService ebuyProductTypeBaseService) {
//		this.ebuyProductTypeBaseService = ebuyProductTypeBaseService;
//	}
	protected ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	private ISysParamParser ebuyAuthPicParamParser;
	public void setEbuyAuthPicParamParser(ISysParamParser ebuyAuthPicParamParser) {
		this.ebuyAuthPicParamParser = ebuyAuthPicParamParser;
	}
	
	private ICommonDeviceService commonDeviceService;
	public void setCommonDeviceService(ICommonDeviceService commonDeviceService) {
		this.commonDeviceService = commonDeviceService;
	}
	
//	private ISysParamParser productPicSpecialPathParamParser;
//	public void setProductPicSpecialPathParamParser(ISysParamParser productPicSpecialPathParamParser) {
//		this.productPicSpecialPathParamParser = productPicSpecialPathParamParser;
//	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private IEbuyInvoiceService ebuyInvoiceService;
	public void setEbuyInvoiceService(IEbuyInvoiceService ebuyInvoiceService) {
		this.ebuyInvoiceService = ebuyInvoiceService;
	}
	
	@Resource
	private IEbuyRefundOrderBaseService ebuyRefundOrderService;
	
	@Resource
	private IEbuyRefundOrderProductBaseService ebuyRefundOderProductService;
	
	@Resource
	private IEbuyDeliveryOrderBaseService ebuyDeliveryOderService;
	
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private IEbuyRefundAuditBaseService ebuyRefundAuditBaseService;
	
	@Resource
	private IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService;
	
	@Resource
	private IEbuyOrderBaseService ebuyOrderBaseService;

	@Resource
	private IEbuyFightOrderBaseService ebuyFightOrderBaseService;

	@Resource
	private IUserCouponService userCouponService;

	@Resource
	private IFlashDealBuyRecordBaseService flashDealBuyRecordBaseService;

	@Resource
	private ICouponService couponService;

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
	
	/**
	 * syl-add 2015-4-27 11:11:33 
	 * 增加判断解放区来源的细分 
	 */
	protected Integer parseJfq_SubType(HttpServletRequest request){
		return EbuyChannelParseUtil.parseJfq_SubType_ByHeader(request);
	}
	/**
	 * syl-add 2015-5-6 11:11:22
	 * 获取渠道来源
	 */
	protected Long parseSubChannelId(HttpServletRequest request){
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		return subChannelId;
	}
	
	@RequestMapping("/searchProductList.json")
	@ResponseBody
	public JsonResponse searchProductList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		String searchKey = request.getParameter("searchKey");
		String productTypeIdStr = request.getParameter("productTypeId");
		BigInteger productTypeId = null;
		if(!StringUtils.isEmpty(productTypeIdStr)){
			productTypeId = new BigInteger(productTypeIdStr);
		}
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<EbuyProductEntity> productList = ebuyService.getProductList(searchKey,productTypeId,pageModel,pointType,parseWlAppType(request));
		//3.结果处理
//		String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EbuyProductEntity ebuyProductInfoAA:productList){
			EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(ebuyProductInfoAA, null);
			//配送名称
			String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
			//认证信息
			List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
			Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, null, null,null);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}

	@RequestMapping("/qryProductList.json")
	@ResponseBody
	public JsonResponse qryProductList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		String productTypeIdStr = request.getParameter("productTypeId");
		BigInteger productTypeId = null;
		if(!StringUtils.isEmpty(productTypeIdStr)){
			productTypeId = new BigInteger(productTypeIdStr);
		}
		//分页信息
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		String searchKey = null;
		//查询最新的更新时间
		String nowUpdTime = ebuyService.fetchAllProductLastUpdTime(searchKey, productTypeId, pageModel, pointType, parseWlAppType(request));
		if(!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)){//若没有新数据，则直接返回
			return jsonResponse;
		}
		List<EbuyProductEntity> productList = ebuyService.getProductList(searchKey,productTypeId,pageModel,pointType,parseWlAppType(request));
		//3.结果处理
//		String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EbuyProductEntity ebuyProductInfoAA:productList){
			EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(ebuyProductInfoAA, null);
			//配送名称
			String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
			//认证信息
			List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
			Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, null, null,null);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/qryProductDetail.json")
	@ResponseBody
	public JsonResponse qryProductDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//获取登录用户信息
		BigInteger userId = UserContext.getOperIdBigInteger();//可以为空
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		//2.交互
//		EbuyProductEntity ebuyProductInfo = ebuyService.getProductById(userId,productId,pointType);
		EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(ebuyService.getProductById(userId,productId,pointType,parseWlAppType(request)), null);

		String themeAdDesc = ebuyNewService.getEbuyThemeDescByShelfId(productId, 1);
		//3.结果处理
		//配送名称
		String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
		//认证信息
		List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
		//运费信息
		Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
		//产品图片列表
		List<EbuyProductPic>  ebuyProductPicList = ebuyProductInfo.doEntity().getEbuyProductPicList();
		//产品介绍图片列表
		List<EbuyProductIntroducePic>  ebuyProductIntroducePicList = ebuyProductInfo.doEntity().getEbuyProductIntroducePicList();
		//产品规格列表
		List<EbuyProductSpec> ebuyProductSpecList = ebuyProductInfo.doEntity().getEbuyProductSpecList();
		//供应商名称
		String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
		Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList
				, defaultDeliveryFee, ebuyProductPicList,supplyMerchantName
				,ebuyProductSpecList,ebuyProductIntroducePicList);
		if(ebuyProductInfo.getFilmTicketNum() !=null && ebuyProductInfo.getFilmTicketNum() >= 1 && ebuyProductInfo.getFilmTicketNum() < 100){
			tmpMap.put("filmStatus", 1);
			tmpMap.put("filmPayUrl", sysParamManager.getSysParaValue(SysParamKey.FILE_PAY_URL));
			tmpMap.put("filmLookUrl", sysParamManager.getSysParaValue(SysParamKey.FILE_LOOK_URL));
		}
		
		jsonResponse.putDataAll(tmpMap);
		//加载其他信息
		jsonResponse.putData("commentTotalCount", ebuyProductInfo.doEntity().getCommentTotalCount());
		jsonResponse.putData("firstComentContent", ebuyProductInfo.doEntity().getFirstComentContent());
		jsonResponse.putData("isCollected", ebuyProductInfo.doEntity().getIsCollected());
		jsonResponse.putData("themeAdDesc", themeAdDesc);
		return jsonResponse;
	}
	
	@RequestMapping("/qryProductParameters.json")
	@ResponseBody
	public JsonResponse qryProductParameters(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		//2.交互
		List<EbuyProductParameters> productParametersList = ebuyService.getProductParameters(productId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(productParametersList!=null){
			for(EbuyProductParameters tmpEbuyProductParameters:productParametersList){
				Map<String,Object> tmpMap = commEntityConvertService.ebuyProductParameters2Map(tmpEbuyProductParameters);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse,resList);
	}
	
	
	@RequestMapping("/qryProductComments.json")
	@ResponseBody
	public JsonResponse qryProductComments(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
//		BigInteger productId = new BigInteger(request.getParameter("productId"));
		Long shelfId = ParamUtils.getLong(request, "productId", null);
		Long productId = ebuyNewService.getProductIdByShelfId(shelfId);
		
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<CommentsEntity> commentsList = ebuyService.getProductComments(BigInteger.valueOf(productId), pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(commentsList!=null){
			for(CommentsEntity tmpComments:commentsList){
				Map<String,Object> tmpMap = commEntityConvertService.comments2Map(tmpComments,tmpComments.getUserGroupBuilding(), tmpComments.getUser(), tmpComments.getNoticeUserList(), tmpComments.getCommentsLabelList(),tmpComments.getCommentsHasTCommentsPointsEntityList(),tmpComments.getAvgTotalStarLevel());
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast,pageModel.count);
	}
	
	@RequestMapping("/qryDeliveryByProductId.json")
	@ResponseBody
	public JsonResponse qryDeliveryByProductId(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		BigInteger productId = new BigInteger(request.getParameter("productId"));
		
		Long shelfId = ParamUtils.getLong(request, "productId", null);
		Long productId = ebuyNewService.getProductIdByShelfId(shelfId);
		
		//2.交互
		List<EbuyDeliveryMethod> deliveryMethodList = ebuyService.getEbuyDeliveryMethodList(BigInteger.valueOf(productId),pointType,parseWlAppType(request));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(deliveryMethodList!=null){
			for(EbuyDeliveryMethod tmpEbuyDeliveryMethod:deliveryMethodList){
				Map<String,Object> tmpMap = commEntityConvertService.ebuyDeliveryMethod2Map(tmpEbuyDeliveryMethod);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse,resList);
	}
	
	/**
	 * 查询推荐的商品列表
	 */
	@RequestMapping("/getRecommend.json")
	@ResponseBody
	public JsonResponse getRecommend(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//2.交互
		List<EbuyProductRecommendEntity> productList = ebuyService.getRecommendProducts(pointType,parseWlAppType(request));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EbuyProductRecommendEntity tmpEntity:productList){
//			EbuyProductEntity ebuyProductInfo = tmpEntity.getEbuyProductInfo();
			EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(tmpEntity.getEbuyProductInfo(), null);
			//配送名称
			String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
			//认证信息
			List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
			Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, null, null,null);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	/**
	 * 查询推荐的商品列表For3
	 */
	@RequestMapping("/getRecommendFor3.json")
	@ResponseBody
	public JsonResponse getRecommendFor3(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//2.交互
		List<EbuyProductRecommendEntity> productList = ebuyService.getRecommendProducts(pointType,parseWlAppType(request));
		//3.结果处理
		LinkedList<Map<String,Object>> resList = new LinkedList<Map<String,Object>>();
		List<Map<String,Object>> specialPicProductList = new ArrayList<Map<String,Object>>();
		for(EbuyProductRecommendEntity tmpEntity:productList){
//			EbuyProductEntity ebuyProductInfo = tmpEntity.getEbuyProductInfo();
			EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(tmpEntity.getEbuyProductInfo(), null);
				//配送名称
				String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
				//认证信息
				List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
				Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, null, null,null);
				if(!StringUtils.isEmpty(ebuyProductInfo.getNameMini())){//推荐的产品特殊处理
					tmpMap.put("name", ebuyProductInfo.getNameMini());
				}
				if(!StringUtils.isEmpty(ebuyProductInfo.getPicSpecial())){
					specialPicProductList.add(tmpMap);
				}else{
					resList.add(tmpMap);
				}
		}
		for(Map<String,Object> tmp:specialPicProductList){
			resList.addFirst(tmp);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	/**
	 * 查询商品类别
	 */
	@RequestMapping("/qryProductTypes.json")
	@ResponseBody
	public JsonResponse qryProductTypes(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		
		Integer version = HeaderManager.getVersionNum();
		//2.交互
		List<EbuyProductType> list = ebuyService.getProductTypeList(pointType,parseWlAppType(request), version);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EbuyProductType ebuyProductType:list){
			Map<String,Object> tmpMap = commEntityConvertService.ebuyProductType2Map(ebuyProductType);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse,resList);
	}
	
	/**
	 * 查询收货地址
	 */
	@RequestMapping("/qryDeliveryAddressList.json")
	@ResponseBody
	public JsonResponse qryDeliveryAddressList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("50270");
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//2.交互
		List<EbuyDeliveryAddressEntity> deliveryAddressList = ebuyService.getEbuyDeliveryAddressList(userId, pageModel);
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(EbuyDeliveryAddressEntity delvAddress:deliveryAddressList){
			SimpleDelivAddress  simpleDelivAddress = delvAddress.getSimpleDelivAddress();
			Map<String,Object> tmpMap=commEntityConvertService.delvAddressEntity2Map(delvAddress,simpleDelivAddress);
			tmpMap.put("gbId", delvAddress.getGbId());
			tmpMap.put("gbName", delvAddress.getGbName());
			tmpMap.put("blockId", delvAddress.getBlockId());
			tmpMap.put("blockName", delvAddress.getBlockName());
			tmpMap.put("cityId", delvAddress.getCityId());
			tmpMap.put("cityName", delvAddress.getCityName());
			tmpMap.put("provinceId", delvAddress.getProvinceId());
			tmpMap.put("provinceName", delvAddress.getProvinceName());
			resList.add(tmpMap);
		}
		//3.结果处理
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 添加收货地址
	 */
	@RequestMapping("/addDeliveryAddress.json")
	@ResponseBody
	public JsonResponse addDeliveryAddress(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		String encoding2 = System.getProperty("file.encoding");
		logger.debug("--encoding1:" + "encoding2:" + encoding2);
		//1.搜集参数
		Integer targetType = Integer.parseInt(request.getParameter("targetType"));
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		Integer isDefault = null;
		String isDefaultStr = request.getParameter("isDefault");
		if(!StringUtils.isEmpty(isDefaultStr)&&EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.equals(Integer.parseInt(isDefaultStr))){
			isDefault = EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE;
		}else{
			isDefault = EbuyDict.EbuyDeliveryAddress_ISDEFAULT.FALSE;
		}
		BigInteger targetId = null;
		String targetIdStr = request.getParameter("targetId");
		if(!StringUtils.isEmpty(targetIdStr)){
			targetId=new BigInteger(targetIdStr);
		}
		BigInteger groupBuildingId = null;
		String groupBuildingIdStr = request.getParameter("groupBuildingId");
		if(!StringUtils.isEmpty(groupBuildingIdStr)){
			groupBuildingId = new BigInteger(groupBuildingIdStr);
		}
		String addressDetail = request.getParameter("addressDetail");
		String addressArea = request.getParameter("addressArea");
		String noWriteRoom = request.getParameter("noWriteRoom");
		String gbName = ParamUtils.getString(request, "gbName");
		BigInteger blockId = ParamUtils.getBigInteger(request, "blockId", null);
		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyDeliveryAddressEntity adddRes=ebuyService.addDeliveryAddress(userId,targetType, userName, userPhone, isDefault, targetId, groupBuildingId, addressDetail, addressArea, noWriteRoom, gbName, blockId);
		//3.结果处理
		SimpleDelivAddress  simpleDelivAddress = adddRes.getSimpleDelivAddress();
		Map<String,Object> resMap = commEntityConvertService.delvAddressEntity2Map(adddRes,simpleDelivAddress);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	/**
	 * 编辑收货地址
	 */
	@RequestMapping("/updDeliveryAddress.json")
	@ResponseBody
	public JsonResponse updDeliveryAddress(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("id"));
		Integer targetType = Integer.parseInt(request.getParameter("targetType"));
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		Integer isDefault = null;
		String isDefaultStr = request.getParameter("isDefault");
		if(!StringUtils.isEmpty(isDefaultStr)&&EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE.equals(Integer.parseInt(isDefaultStr))){
			isDefault = EbuyDict.EbuyDeliveryAddress_ISDEFAULT.TRUE;
		}else{
			isDefault = EbuyDict.EbuyDeliveryAddress_ISDEFAULT.FALSE;
		}
		BigInteger targetId = null;
		String targetIdStr = request.getParameter("targetId");
		if(!StringUtils.isEmpty(targetIdStr)){
			targetId=new BigInteger(targetIdStr);
		}
		BigInteger groupBuildingId = null;
		String groupBuildingIdStr = request.getParameter("groupBuildingId");
		if(!StringUtils.isEmpty(groupBuildingIdStr)){
			groupBuildingId = new BigInteger(groupBuildingIdStr);
		}
		String addressDetail = request.getParameter("addressDetail");
		String addressArea = request.getParameter("addressArea");
		String noWriteRoom = request.getParameter("noWriteRoom");
		String gbName = ParamUtils.getString(request, "gbName");
		BigInteger blockId = ParamUtils.getBigInteger(request, "blockId", null);

		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyDeliveryAddressEntity updRes = ebuyService.updateDeliveryAddress(deliveryAddressId, userId, targetType, userName, userPhone, isDefault, targetId, groupBuildingId, addressDetail, addressArea, noWriteRoom, gbName, blockId);
		//3.结果处理
		SimpleDelivAddress  simpleDelivAddress = updRes.getSimpleDelivAddress();
		Map<String,Object> resMap = commEntityConvertService.delvAddressEntity2Map(updRes,simpleDelivAddress);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	/**
	 * 设置默认收货地址
	 */
	@RequestMapping("/setDefaultDeliveryAddress.json")
	@ResponseBody
	public JsonResponse setDefaultDeliveryAddress(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ebuyService.setDefaultDeliveryAddress(deliveryAddressId, userId);
		//3.结果处理
		return jsonResponse;
	}
	/**
	 * 根据Id查询收货地址详情
	 */
	@RequestMapping("/qryDeliveryAddressDetailById.json")
	@ResponseBody
	public JsonResponse qryDeliveryAddressDetailById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyDeliveryAddressEntity delvAddressEntityDetail = ebuyService.getDeliveryAddressDetail(deliveryAddressId, userId);
		//3.结果处理
		SimpleDelivAddress  simpleDelivAddress = delvAddressEntityDetail.getSimpleDelivAddress();
		Map<String,Object> resMap = commEntityConvertService.delvAddressEntity2Map(delvAddressEntityDetail,simpleDelivAddress);
		resMap.put("gbName", delvAddressEntityDetail.getGbName());
		resMap.put("gbId", delvAddressEntityDetail.getGbId());
		resMap.put("blockId", delvAddressEntityDetail.getBlockId());
		resMap.put("cityId", delvAddressEntityDetail.getCityId());
		resMap.put("provinceId", delvAddressEntityDetail.getProvinceId());
		jsonResponse.setDataValue(resMap);
		
		Object singalDetail = delvAddressEntityDetail.getSingalDetail();
		if(singalDetail!=null){
			if(singalDetail instanceof RoomEntity){
				RoomEntity roomEntity = (RoomEntity)singalDetail;
				if(roomEntity!=null){
					RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
					jsonResponse.putData("roomBaseInfo", roomBaseInfo);
				}
			}
		}
		
		return jsonResponse;
	}
	/**
	 * 查询默认收货地址
	 */
	@RequestMapping("/qryDeliveryAddressDetailDefault.json")
	@ResponseBody
	public JsonResponse qryDeliveryAddressDetailDefault(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("50270");
		//2.交互
		EbuyDeliveryAddressEntity delvAddressEntityDetail = ebuyService.qryDeliveryAddressDetailDefault(userId);
		if (delvAddressEntityDetail != null) {
			//3.结果处理
			SimpleDelivAddress  simpleDelivAddress = delvAddressEntityDetail.getSimpleDelivAddress();
			Map<String,Object> resMap = commEntityConvertService.delvAddressEntity2Map(delvAddressEntityDetail,simpleDelivAddress);
			resMap.put("gbId", delvAddressEntityDetail.getGbId());
			jsonResponse.setDataValue(resMap);
		}
		return jsonResponse;
	}
	/**
	 * 移除收货地址
	 */
	@RequestMapping("/delDeliveryAddress.json")
	@ResponseBody
	public JsonResponse delDeliveryAddress(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ebuyService.deleteDeliveryAddress(deliveryAddressId, userId);
		//3.结果处理
		return jsonResponse;
	}

	/**
	 * 编辑发票
	 */
	@RequestMapping("/updInvoice.json")
	@ResponseBody
	public JsonResponse updInvoice(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger invoiceId = StringUtils.isEmpty(request.getParameter("invoiceId")) ? null : new BigInteger(request.getParameter("invoiceId"));
		String companyName = request.getParameter("companyName");
		String productTypeName = request.getParameter("productTypeName");

		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		EbuyInvoice ebuyInvoice = new EbuyInvoice();

		ebuyInvoice.setId(invoiceId);
		ebuyInvoice.setCompanyName(companyName);
		ebuyInvoice.setProductTypeName(productTypeName);
		ebuyInvoice.setIsdefault(1);
		ebuyInvoice.settUserFId(userId);

		//2.交互
		if (ebuyInvoice.getId() == null) {
			ebuyInvoiceService.insertEbuyInvoice(ebuyInvoice);
		} else {
			ebuyInvoiceService.updateEbuyInvoice(ebuyInvoice);
		}

		//3.结果处理
		ebuyInvoice = ebuyInvoiceService.getEbuyInvoiceBySeqId(ebuyInvoice.getId());//再取一次新的值回来
		jsonResponse.setDataValue(ebuyInvoice);
		return jsonResponse;
	}

	/**
	 * 获取发票信息
	 */
	@RequestMapping("/qryInvoice.json")
	@ResponseBody
	public JsonResponse qryInvoice(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数

		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		//2.交互
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		List<EbuyInvoice> ebuyInvoiceList = ebuyInvoiceService.getEbuyInvoiceByCondition(paramMap);
		EbuyInvoice ebuyInvoice = null;
		if (ebuyInvoiceList.size() > 0)
			ebuyInvoice = ebuyInvoiceService.getEbuyInvoiceByCondition(paramMap).get(0);

		//3.结果处理
		jsonResponse.setDataValue(ebuyInvoice);
		return jsonResponse;
	}

	/**
	 * 加入购物车 
	 */
	@RequestMapping("/add2BuyCar.json")
	@ResponseBody
	public JsonResponse add2BuyCar(HttpServletRequest request){
		long startTime = System.currentTimeMillis();
		logger.info("start add2BuyCar.json ----- ");
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		BigInteger productSpecId = null;
		{
			String productSpecIdStr = request.getParameter("productSpecId");
			if(!StringUtils.isEmpty(productSpecIdStr)){
				productSpecId = new BigInteger(productSpecIdStr);//syl-add 商品规格Id
			}
		}
		Long productQty = Long.valueOf(request.getParameter("productQty"));
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.add2BuyCar(userIdType.getUserId(),userIdType.getUserType(),productId, productQty,pointType,parseWlAppType(request),productSpecId);
		//3.结果处理
		jsonResponse.putData("productCount", 0);
		logger.info("use time: " + (System.currentTimeMillis() - startTime));
		logger.info("end add2BuyCar.json------");
		return jsonResponse;
	}
	
	/**
	 * 移除购物车 
	 */
	@RequestMapping("/remove2BuyCar.json")
	@ResponseBody
	public JsonResponse remove2BuyCar(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.removeProdFromBuyCarOld(userIdType.getUserId(),userIdType.getUserType(), productId,pointType,parseWlAppType(request));
		//3.结果处理
		jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		return jsonResponse;
	}
	/**
	 * 移除购物车-支持商品规格
	 */
	@RequestMapping("/remove2BuyCarWithSpec.json")
	@ResponseBody
	public JsonResponse remove2BuyCarWithSpec(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		BigInteger productSpecId = null;
		{//增加规格后续处理
			String productSpecIdStr = request.getParameter("productSpecId");
			if(!StringUtils.isEmpty(productSpecIdStr)){
				productSpecId = new BigInteger(productSpecIdStr);
			}
		}
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.removeProdFromBuyCar(userIdType.getUserId(),userIdType.getUserType(), productId,pointType,parseWlAppType(request),productSpecId);
		//3.结果处理
		jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		return jsonResponse;
	}
	
	/**
	 * 移除购物车 批量
	 */
	@RequestMapping("/remove2BuyCarBatch.json")
	@ResponseBody
	public JsonResponse remove2BuyCarBatch(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		List<BigInteger> productIds = JSON.parseArray(request.getParameter("productIdList"), BigInteger.class);
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.removeProdFromBuyCarBatchLogicOld(userIdType.getUserId(),userIdType.getUserType(), productIds,pointType,parseWlAppType(request));
		//3.结果处理
		if(ebuyBuyCarEntity!=null){
			jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		}else{
			jsonResponse.putData("productCount",0);
		}
		return jsonResponse;
	}
	/**
	 * 移除购物车 批量
	 */
	@RequestMapping("/remove2BuyCarBatchWithSpec.json")
	@ResponseBody
	public JsonResponse remove2BuyCarBatchWithSpec(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
		if(productIdQtyList==null||productIdQtyList.size()<=0){
			throw new BusinessRuntimeException("EbuyController.remove2BuyCarBatchWithSpec.productList.empty");
		}
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.removeProdFromBuyCarBatchLogic(userIdType.getUserId(),userIdType.getUserType(), productIdQtyList,pointType,parseWlAppType(request));
		//3.结果处理
		if(ebuyBuyCarEntity!=null){
			jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		}else{
			jsonResponse.putData("productCount",0);
		}
		return jsonResponse;
	}
	
	
	
	/**
	 * 清空购物车 
	 */
	@RequestMapping("/remove2BuyCarAll.json")
	@ResponseBody
	public JsonResponse remove2BuyCarAll(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity=ebuyService.removeProdFromBuyCarAllLogic(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		//3.结果处理
		if(ebuyBuyCarEntity!=null){
			jsonResponse.putData("productCount", ebuyBuyCarEntity.getProductTotalCount());
		}else{
			jsonResponse.putData("productCount", 0);
		}
		return jsonResponse;
	}
	
	/**
	 * 查询购物车商品数量
	 */
	@RequestMapping("/qryBuyCarProductCount.json")
	@ResponseBody
	public JsonResponse qryBuyCarProductCount(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		Integer count = ebuyService.getBuyCarProductTotalCount(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		//3.结果处理
		if(count!=null){
			jsonResponse.putData("productCount",count);
		}else{
			jsonResponse.putData("productCount",0);
		}
		return jsonResponse;
	}
	/**
	 * 查询购物车信息 
	 */
	@RequestMapping("/qryBuyCar.json")
	@ResponseBody
	public JsonResponse qryBuyCar(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity = ebuyService.getBuyCarDetail(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(ebuyBuyCarEntity!=null){
			Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroup = ebuyBuyCarEntity.groupByMerchant();
			for(Map.Entry<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroupEntry:merchantGroup.entrySet()){
				EbuySupplyMerchantEntity merchant = merchantGroupEntry.getKey();
				List<EbuyConfirm_ProductAndCount> buyCarProdList = merchantGroupEntry.getValue();
				//供应商
				Map<String,Object> merch = merchant2Map(merchant);
				Long totalCount = 0L;
				Long totalMoney = 0L;
				{//供应商对应的产品信息
					List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
					for(EbuyConfirm_ProductAndCount buyCarProdRela:buyCarProdList){
//						EbuyProductEntity ebuyProductInfo = buyCarProdRela.getEbuyProductEntity();
						EbuyProductWithCurrProductSpec ebuyProductInfo = buyCarProdRela.getEbuyProductWithSpec();
						//配送名称
						String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
						//认证信息
						List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
						Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
						String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
						Map<String,Object>  tmpProdMap = productInfo2Map(ebuyProductInfo,defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList
								,defaultDeliveryFee, null, supplyMerchantName);
						//商品购买的数量
						tmpProdMap.put("productQty", buyCarProdRela.getProductQty());
//						if(buyCarProdRela.getBuyCarEbuyProductSpec()!=null){//syl-add附加商品规格信息
//							tmpProdMap.put("ext_product_specInfo", ebuyProductSpec2Map(buyCarProdRela.getBuyCarEbuyProductSpec()));
//						}
						resProductList.add(tmpProdMap);
						totalCount+=buyCarProdRela.getProductQty();
						totalMoney+=ebuyProductInfo.getPriceDiscount()*buyCarProdRela.getProductQty();//乘以商品数量
					}
					merch.put("productList", resProductList);
					merch.put("signalMer_totalCount", totalCount);//当前供应商商品总数
					merch.put("signalMer_totalCount_productType", buyCarProdList.size());//当前供应商商品类目总数
					merch.put("signalMer_totalMoney", PriceUtil.div100(totalMoney));//当前供应商商品总价
				}
				resList.add(merch);
			}
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", ebuyBuyCarEntity.getProductTotalCount());//商品总数
			jsonResponse.putData("ext_totalCount_productType", ebuyBuyCarEntity.getProductTotalCount_productType());//商品类目总数
			jsonResponse.putData("ext_totalMoney", ebuyBuyCarEntity.getProductTotalPriceDiv100());//商品总价
			{//syl-add 2015-1-30 17:47:11
				List<Map<String,Object>> resHasProductBuyerList = new ArrayList<Map<String,Object>>();
				List<UserSimpleEntity> hasProductBuyerList = ebuyBuyCarEntity.getHasProductBuyerList();//参与购买的用户列表
				if(hasProductBuyerList!=null&&hasProductBuyerList.size()>0){
					for(UserSimpleEntity tmpUserSimpleEntity:hasProductBuyerList){
						Map<String,Object> tmpMap = commEntityConvertService.baseUser2Map(tmpUserSimpleEntity);
						resHasProductBuyerList.add(tmpMap);
					}
				}
				jsonResponse.putData("ext_buyerList", resHasProductBuyerList);//参与购买的用户列表
			}
		}else{
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", 0);//商品总数
			jsonResponse.putData("ext_totalCount_productType", 0);//商品类目总数
			jsonResponse.putData("ext_totalMoney", 0);//商品总价
		}
		return jsonResponse;
	}
	
	/**
	 * 查询购物车信息(按用户分组) 
	 */
	@RequestMapping("/qryBuyCarGroupByUser.json")
	@ResponseBody
	public JsonResponse qryBuyCarGroupByUser(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity = ebuyService.getBuyCarDetail(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		List<EbuyBuyCarEntity> ebuyBuyCarEntityList = ebuyBuyCarEntity.getEbuyBuyCarEntityList();
		//3.结果处理
		LinkedList<Map<String,Object>> resList = new LinkedList<Map<String,Object>>();
		if(ebuyBuyCarEntityList!=null&&ebuyBuyCarEntityList.size()>0){
//			Long allCarTotalCount = 0L;//所有购物车商品总数
//			Long allCarTotalMoney = 0L;//所有购物车商品总价
//			Long allCarTotalCount_productType = 0L;//所有购物车商品类目总数
			for(EbuyBuyCarEntity tmpBuyCar:ebuyBuyCarEntityList){
				List<EbuyBuyCarHasTEbuyProductEntity_Product> buyCarProdList = tmpBuyCar.getEbuyBuyCarHasTEbuyProductEntity_ProductList();
				UserSimpleEntity baseUser = tmpBuyCar.getRegUser();
				//按用户分组
				Map<String,Object> userGroupMap = null;
				if(tmpBuyCar.checkIsRegUser()){
					userGroupMap = commEntityConvertService.baseUser2MapForEbuyBuyCar(baseUser);
					if(baseUser.getId().compareTo(userIdType.getUserId())==0){
						userGroupMap.put("isSelf", true);
					}else{
						userGroupMap.put("isSelf", false);
					}
				}else{
					Map<String,Object> userMap = new HashMap<String, Object>();
					userMap.put("name", "临时用户");
					userMap.put("imgProfile", null);
					userMap.put("isSelf", true);
					userGroupMap = userMap;
				}
				Long totalCount = 0L;
				Long totalMoney = 0L;
				{//供应商对应的产品信息
					List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
					for(EbuyBuyCarHasTEbuyProductEntity_Product buyCarProdRela:buyCarProdList){
//						EbuyProductEntity ebuyProductInfo = buyCarProdRela.getEbuyProductEntity();
						EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(buyCarProdRela.getEbuyProductEntity(), buyCarProdRela.getBuyCarEbuyProductSpec());
						//配送名称
						String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
						//认证信息
						List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
						Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
						String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
						Map<String,Object>  tmpProdMap = productInfo2Map(ebuyProductInfo,defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList
								,defaultDeliveryFee, null, supplyMerchantName);
						//商品购买的数量
						tmpProdMap.put("productQty", buyCarProdRela.getProductQty());
						resProductList.add(tmpProdMap);
						
						totalCount+=buyCarProdRela.getProductQty();
						totalMoney+=ebuyProductInfo.getPriceDiscount()*buyCarProdRela.getProductQty();//乘以商品数量
					}
					userGroupMap.put("productList", resProductList);
					userGroupMap.put("signalMer_totalCount", totalCount);//当前供应商商品总数
					userGroupMap.put("signalMer_totalCount_productType", buyCarProdList.size());//当前供应商商品类目总数
					userGroupMap.put("signalMer_totalMoney", PriceUtil.div100(totalMoney));//当前供应商商品总价
//					allCarTotalCount+=totalCount;
//					allCarTotalMoney+=totalMoney;
//					allCarTotalCount_productType+=buyCarProdList.size();
				}
				if(tmpBuyCar.getUserId().compareTo(userIdType.getUserId())==0&&tmpBuyCar.getUserType().compareTo(userIdType.getUserType())==0){
					resList.addFirst(userGroupMap);
				}else{
					resList.add(userGroupMap);
				}
			}
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", ebuyBuyCarEntity.getProductTotalCount());//商品总数
			jsonResponse.putData("ext_totalCount_productType", ebuyBuyCarEntity.getProductTotalCount_productType());//商品类目总数
			jsonResponse.putData("ext_totalMoney", ebuyBuyCarEntity.getProductTotalPriceDiv100());//商品总价
		}else{
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", 0);//商品总数
			jsonResponse.putData("ext_totalCount_productType", 0);//商品类目总数
			jsonResponse.putData("ext_totalMoney", 0);//商品总价
		}
		return jsonResponse;
	}
	/**
	 * 查询购物车信息(按商品Id分组) 
	 */
	@RequestMapping("/qryBuyCarGroupByProductId.json")
	@ResponseBody
	public JsonResponse qryBuyCarGroupByProductId(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		UserIdType userIdType = commonDeviceService.getUserIdType();
		EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity = ebuyService.getBuyCarDetail(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(ebuyBuyCarEntity!=null){
			Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroup = ebuyBuyCarEntity.groupByMerchant();
//			EbuySupplyMerchantEntity merchant = merchantGroupEntry.getKey();
			List<EbuyConfirm_ProductAndCount> buyCarProdList = new ArrayList<EbuyConfirm_ProductAndCount>();
			for(Map.Entry<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroupEntry:merchantGroup.entrySet()){
				if(merchantGroupEntry.getValue()!=null){
					buyCarProdList.addAll(merchantGroupEntry.getValue());
				}
			}
			{//syl-upd 2015-2-5 18:05:39 组合成一个供应商
				//供应商
//				Map<String,Object> merch = merchant2Map(merchant);
				Map<String,Object> merch = new HashMap<String, Object>();
				Long totalCount = 0L;
				Long totalMoney = 0L;
				{//供应商对应的产品信息
					List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
					//排序
					{
						Collections.sort(buyCarProdList, new Comparator<EbuyConfirm_ProductAndCount>() {
							@Override
							public int compare(EbuyConfirm_ProductAndCount o1, EbuyConfirm_ProductAndCount o2) {
								if(o1==null||o2==null){
									return 0;
								}
								return o1.compareTo(o2);
							}
						});
					}
					for(EbuyConfirm_ProductAndCount buyCarProdRela:buyCarProdList){
						if(buyCarProdRela.getProductQty()==null||buyCarProdRela.getProductQty()<=0){
							continue;//商品数量为0的跳过
						}
//						EbuyProductEntity ebuyProductInfo = buyCarProdRela.getEbuyProductEntity();
						EbuyProductWithCurrProductSpec ebuyProductInfo = buyCarProdRela.getEbuyProductWithSpec();
						//配送名称
						String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
						//认证信息
						List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
						Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
						String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
						Map<String,Object>  tmpProdMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList
								,defaultDeliveryFee, null, supplyMerchantName);
						//商品购买的数量
						tmpProdMap.put("productQty", buyCarProdRela.getProductQty());
						//syl-add-2015-2-3 15:34:09 单个商品购买的用户列表
						{
							Set<UserSimpleEntity> buyerList = buyCarProdRela.getBuyerList();
							List<Map<String,Object>> buyerListRes = new ArrayList<Map<String,Object>>();
							if(buyerList!=null&&buyerList.size()>0){
								for(UserSimpleEntity tmpU:buyerList){
									buyerListRes.add(commEntityConvertService.baseUser2Map(tmpU));
								}
							}
							tmpProdMap.put("buyerList", buyerListRes);
						}
						resProductList.add(tmpProdMap);
						totalCount+=buyCarProdRela.getProductQty();
						totalMoney+=ebuyProductInfo.getPriceDiscount()*buyCarProdRela.getProductQty();//乘以商品数量
					}
					merch.put("productList", resProductList);
					merch.put("signalMer_totalCount", totalCount);//当前供应商商品总数
					merch.put("signalMer_totalCount_productType", buyCarProdList.size());//当前供应商商品类目总数
					merch.put("signalMer_totalMoney", PriceUtil.div100(totalMoney));//当前供应商商品总价
				}
				resList.add(merch);
			}
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", ebuyBuyCarEntity.getProductTotalCount());//商品总数
			jsonResponse.putData("ext_totalCount_productType", ebuyBuyCarEntity.getProductTotalCount_productType());//商品类目总数
			jsonResponse.putData("ext_totalMoney", ebuyBuyCarEntity.getProductTotalPriceDiv100());//商品总价
			{//syl-add 2015-1-30 17:47:11
				List<Map<String,Object>> resHasProductBuyerList = new ArrayList<Map<String,Object>>();
				List<UserSimpleEntity> hasProductBuyerList = ebuyBuyCarEntity.getHasProductBuyerList();//参与购买的用户列表
				if(hasProductBuyerList!=null&&hasProductBuyerList.size()>0){
					for(UserSimpleEntity tmpUserSimpleEntity:hasProductBuyerList){
						Map<String,Object> tmpMap = commEntityConvertService.baseUser2Map(tmpUserSimpleEntity);
						resHasProductBuyerList.add(tmpMap);
					}
				}
				jsonResponse.putData("ext_buyerList", resHasProductBuyerList);//参与购买的用户列表
			}
		}else{
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
			jsonResponse.putData("ext_totalCount", 0);//商品总数
			jsonResponse.putData("ext_totalCount_productType", 0);//商品类目总数
			jsonResponse.putData("ext_totalMoney", 0);//商品总价
		}
		return jsonResponse;
	}
	@RequestMapping("/checkProdctInfo.json")
	@ResponseBody
	public JsonResponse checkProdctInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		Long flowProductId = ParamUtils.getLong(request, "productId", null);
		String flow = ParamUtils.getString(request, "flow", null);
		String phone = ParamUtils.getString(request, "phone", null);
//		BigDecimal price = ParamUtils.getBigDecimal(request, "price", null);
		BigDecimal price = RechargePackageUtils.getRechargePrice(phone, flow);
		if(flow != null && price == null) {
			throw new BusinessRuntimeException("EbuyController.rechargePackage.failed");
		}
		
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
//		String[] productIdsStr = request.getParameterValues("productIds");
		List<ProductIdQtyEntity> productIdQtyList =null;
		if(!StringUtils.isEmpty(request.getParameter("productList"))){
			productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
//		UserIdType userIdType = commonDeviceService.getUserIdType();
		UserIdType userIdType = new UserIdType(userId, LoginDict.UserRegistOrTmp.REGIST_USER);//注册用户
		Long totalAmount;
		List<Map<String,Object>> resMerchList = new ArrayList<Map<String,Object>>();
		if(flowProductId != null && flow != null) {
			BigInteger productId = BigInteger.valueOf(flowProductId);
			EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(ebuyService.getProductById(null,productId,pointType,parseWlAppType(request)), null);
			String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
			List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
			Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
			String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
			
			Map<String,Object> merch = merchant2Map(ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity());
			Map<String,Object>  tmpProdMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, defaultDeliveryFee, null, supplyMerchantName);
			tmpProdMap.put("productQty", 1);
			tmpProdMap.put("flow", flow);
			tmpProdMap.put("phone", phone);
			tmpProdMap.put("priceDiscount", price);
			tmpProdMap.put("price", price);
			if(flow.contains("HF")) {
				String name = tmpProdMap.get("name") + " " + flow.replace("YDHF", "移动").replace("LTHF", "联通").replace("DXHF", "电信") + "元\n手机号码：" + phone;
				tmpProdMap.put("name", name.replace("流量", "话费"));
			} else {
				String name = tmpProdMap.get("name") + " " + flow.replace("YD", "移动").replace("LT", "联通").replace("DX", "电信") + "M\n手机号码：" + phone;
				tmpProdMap.put("name", name);
			}
			
			List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
			resProductList.add(tmpProdMap);
			merch.put("productList", resProductList);
			
			merch.put("signalMer_totalCount", 1);//当前供应商商品总数
			merch.put("signalMer_totalCount_productType", 1);//当前供应商商品类目总数
			merch.put("signalMer_totalMoney", price);//当前供应商商品总价
			
			merch.put("signalMer_Fee", 0);
			merch.put("signalMer_Deliv_Info", "免运费");
			merch.put("signalMer_totalMoney_Fee", price);
			
			resMerchList.add(merch);
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resMerchList);
			jsonResponse.putData("ext_totalCount", 1);//商品总数
			jsonResponse.putData("ext_totalCount_productType", 1);//商品类目总数
			jsonResponse.putData("ext_totalMoney", price);//商品总价
			jsonResponse.putData("ext_Fee", 0);
			jsonResponse.putData("ext_totalMoney_Fee", price);
			
			totalAmount = (price.multiply(BigDecimal.valueOf(100L))).longValue();
		} else {
			EbuyBuyCarEntityFamilyGroup ebuyBuyCarEntity = null;
			if(productIdQtyList==null){
				ebuyBuyCarEntity = ebuyService.getBuyCarAllDetail(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
				if(ebuyBuyCarEntity==null||ebuyBuyCarEntity.getProductTotalCount()==0){
					throw new BusinessRuntimeException("EbuyController.checkProdctInfo.ebuyBuyCar.empty");
				}
			}else{
				ebuyBuyCarEntity = ebuyService.getBuyCarPartDetailByProdIds(userIdType.getUserId(),userIdType.getUserType(), productIdQtyList,pointType,parseWlAppType(request));
			}
			//3.结果处理
			Long totalMerFee = 0L;
			if(ebuyBuyCarEntity!=null){
				Map<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroup = ebuyBuyCarEntity.groupByMerchant();
				for(Map.Entry<EbuySupplyMerchantEntity,List<EbuyConfirm_ProductAndCount>> merchantGroupEntry:merchantGroup.entrySet()){
					EbuySupplyMerchantEntity merchant = merchantGroupEntry.getKey();
					List<EbuyConfirm_ProductAndCount> buyCarProdList = merchantGroupEntry.getValue();
					//供应商
					Map<String,Object> merch = merchant2Map(merchant);
					Long totalCount = 0L;
					Long totalMoney = 0L;
					Long totalFee = null;
					String deliveDesc = null;
					{//供应商对应的产品信息
						List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
						Map<BigInteger,Long> productIdQtyMapForDelvMethod = new HashMap<BigInteger, Long>();
						for(EbuyConfirm_ProductAndCount buyCarProdRela:buyCarProdList){
//							EbuyProduct ebuyProductInfo = buyCarProdRela.getEbuyProductEntity();
							EbuyProductWithCurrProductSpec ebuyProductInfo = buyCarProdRela.getEbuyProductWithSpec();
							//配送名称
							String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
							//认证信息
							List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.doEntity().getEbuyProductHasTEbuyAuthEntityList();
							Long defaultDeliveryFee = ebuyProductInfo.getDefaultDeliveryFee();
							String supplyMerchantName = ebuyProductInfo.doEntity().getEbuySupplyMerchantEntity().getName();
							Map<String,Object>  tmpProdMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList
									,defaultDeliveryFee, null, supplyMerchantName);
							//商品购买的数量
							tmpProdMap.put("productQty", buyCarProdRela.getProductQty());
							resProductList.add(tmpProdMap);
							
							totalCount+=buyCarProdRela.getProductQty();
							totalMoney+=ebuyProductInfo.getPriceDiscount()*buyCarProdRela.getProductQty();//乘以商品数量
							{//存储商品购买信息，为后面获取配送方式准备数据
								if(productIdQtyMapForDelvMethod.get(ebuyProductInfo.getId())==null){
									productIdQtyMapForDelvMethod.put(ebuyProductInfo.getId(), buyCarProdRela.getProductQty());
								}else{
									productIdQtyMapForDelvMethod.put(ebuyProductInfo.getId(), productIdQtyMapForDelvMethod.get(ebuyProductInfo.getId())+buyCarProdRela.getProductQty());
								}
							}
							
							if(ebuyProductInfo.getShelfProduct() != null && ebuyProductInfo.getShelfProduct().getOpType() != null && ebuyProductInfo.getShelfProduct().getOpType() == 1) {
								totalFee = 0L;
								deliveDesc = ebuyProductInfo.getShelfProduct().getOpName();
							}
						}
						merch.put("productList", resProductList);
						merch.put("signalMer_totalCount", totalCount);//当前供应商商品总数
						merch.put("signalMer_totalCount_productType", buyCarProdList.size());//当前供应商商品类目总数
						merch.put("signalMer_totalMoney", PriceUtil.div100(totalMoney));//当前供应商商品总价
						//查询总运费
//						EbuyDeliveryMethod ebuyDeliveryMethod = ebuyService.fetchEbuyDeliveryMethod(null, totalMoney);
						//相同商品Id的数量需要合并
						List<ProductIdQtyEntity> productIdQtyEntityListForDelvMethod = new ArrayList<ProductIdQtyEntity>();
						for(BigInteger productId:productIdQtyMapForDelvMethod.keySet()){
							BigInteger productSpecId = null;//购物车暂时不支持商品规格
							EbuyExtandBuyParam ebuyExtandBuyParam = null;
							ProductIdQtyEntity tmpProductIdQtyEntity = new ProductIdQtyEntity(productId, productIdQtyMapForDelvMethod.get(productId), productSpecId, ebuyExtandBuyParam);
							productIdQtyEntityListForDelvMethod.add(tmpProductIdQtyEntity);
						}
						
						if(totalFee == null) {
							List deliveryInfo = ebuyService.fetchEbuyDeliveryMethod(merchant.getId(),null, productIdQtyEntityListForDelvMethod, null, pointType,parseWlAppType(request));
							EbuyDeliveryMethod ebuyDeliveryMethod = (EbuyDeliveryMethod) deliveryInfo.get(0);
							totalFee = ebuyDeliveryMethod.getFee();
							totalMerFee+=totalFee;
							merch.put("signalMer_Fee", PriceUtil.div100(totalFee));
							merch.put("signalMer_Deliv_Info", ebuyDeliveryMethod.getDesc());
						} else {
							merch.put("signalMer_Fee", PriceUtil.div100(totalFee));
							merch.put("signalMer_Deliv_Info", "新用户专享商品包邮");
							merch.put("leastDeliveryAmt", 0);
						}

						merch.put("signalMer_totalMoney_Fee", PriceUtil.div100(totalMoney+totalFee));
					}
					resMerchList.add(merch);
				}
				jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resMerchList);
				jsonResponse.putData("ext_totalCount", ebuyBuyCarEntity.getProductTotalCount());//商品总数
				jsonResponse.putData("ext_totalCount_productType", ebuyBuyCarEntity.getProductTotalCount_productType());//商品类目总数
				jsonResponse.putData("ext_totalMoney", ebuyBuyCarEntity.getProductTotalPriceDiv100());//商品总价
				jsonResponse.putData("ext_Fee", PriceUtil.div100(totalMerFee));
				jsonResponse.putData("ext_totalMoney_Fee", PriceUtil.div100(ebuyBuyCarEntity.getProductTotalPrice()+totalMerFee));
			}else{
				jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resMerchList);
				jsonResponse.putData("ext_totalCount", 0);//商品总数
				jsonResponse.putData("ext_totalCount_productType", 0);//商品类目总数
				jsonResponse.putData("ext_totalMoney", 0);//商品总价
				jsonResponse.putData("ext_Fee", 0);
				jsonResponse.putData("ext_totalMoney_Fee", 0);
			}
//			totalAmount = ebuyBuyCarEntity.getProductTotalPrice()+totalMerFee;
			totalAmount = ebuyBuyCarEntity.getProductTotalPrice();
		}

		//查询可用优惠券 todo 物品兑换券没考虑
		UserCoupon userCoupon = new UserCoupon();
		Coupon coupon = new Coupon();
		coupon.setLeastSpendUse(PriceUtil.div100(totalAmount).intValue());
		coupon.setUseType(CouponUseTypeConstant.EBUY_PRODUCT);
		userCoupon.setCoupon(coupon);
		userCoupon.settUserFId(userId);
		userCoupon.setStatus(UserCouponStatus.VALID);
		Map<String, Object> param = MapConverter.toMap(userCoupon);
		List<Object> supplyMerchantIds = new ArrayList<Object>();
		for (Map<String, Object> map : resMerchList) {
			supplyMerchantIds.add(map.get("id"));
		}
		param.put("supplyMerchantIds", supplyMerchantIds);
		List<UserCouponEntity> coupons = userCouponService.getUserCouponList(param);
		if (!DataUtil.isEmpty(productIdQtyList)) {
			List<UserCouponEntity> couponsEbuyProduct = userCouponService.getUserCouponList4EbuyProduct(productIdQtyList, userId);
			coupons.addAll(couponsEbuyProduct);
			Collections.sort(coupons, new CouponUseEndDateComparator());
		}
		jsonResponse.putData("ext_isContainCoupon", !(coupons == null || coupons.isEmpty()));
		jsonResponse.putData("ext_couponCombiInfo", coupons);
		return jsonResponse;
	}
	
	/**
	 * 申请退款单
	 */
	@RequestMapping("/applyRefund.json")
	@ResponseBody
	public JsonResponse applyRefund(HttpServletRequest request,String edoId){
		JsonResponse jsonResponse = new JsonResponse();		
		//1.搜集参数
//		PageModel pageModel = ControllerUtils.getPageModel(request);
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//用户
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("5259694");
		//订单Id
		String serverurl = getrefundPicUrl();
		Map<String, Object> paramMaps = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(edoId)){
			BigInteger deliveryOrderId = new BigInteger(edoId);
			paramMaps.put("edoId", deliveryOrderId);
			EbuyDeliveryOrderEntity deliveryOrderEntity = ebuyService.getebuyDeliveryOrderEntityById(paramMaps);
			EbuyOrderEntity ebuyOrderEntity = ebuyService.getEbuyOrderEntityDetail(userId, deliveryOrderEntity.gettEbuyOrderFId(), pointType, parseWlAppType(request));
			Set<EbuyOrderHasTEbuyProductEntity_Product> respro = ebuyOrderEntity.fetchEbuyOrderHasTEbuyProductEntityList(deliveryOrderEntity);
			List<Map<String, Object>> resList = new ArrayList<Map<String,Object>>();
			for(EbuyOrderHasTEbuyProductEntity_Product orderProduct :respro){
				Map<String, Object> proList = new HashMap<String, Object>();
				proList.put("productName", orderProduct.getEbuyProductEntity().getName());
				proList.put("productNum", orderProduct.getProductQty());
				//对应的流量价格
				if(orderProduct.getEbuyProductEntity().getFilmTicketNum() != null){
					if(orderProduct.getEbuyProductEntity().getFilmTicketNum() == -1){
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("orderId", orderProduct.gettEbuyOrderFId());
						paramMap.put("productId", orderProduct.gettEbuyProductFId());
						EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
						if(flowRecharge!=null){
							proList.put("productPrice",flowRecharge.getPrice());
						}else{
							proList.put("productPrice",PriceUtil.div100(orderProduct.getProductPrice()));
						}
					}else{
						proList.put("productPrice",PriceUtil.div100(orderProduct.getProductPrice()));
					}
				} else {
					proList.put("productPrice",PriceUtil.div100(orderProduct.getProductPrice()));
				}
				proList.put("productPic",serverurl+orderProduct.getEbuyProductEntity().getPicBaseMini());
				proList.put("productId",orderProduct.gettEbuyProductFId().toString());
				resList.add(proList);
			}
			jsonResponse.putData("supplyName", deliveryOrderEntity.getEbuySupplyMerchant().getName());
			jsonResponse.putData("deliveryOrderId", deliveryOrderId);
			jsonResponse.putData("deliveryRealFee", PriceUtil.div100(deliveryOrderEntity.getDeliveryRealFee()));//运单费
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resList);
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("请选择要退款的运单");
		}
		return jsonResponse;
	}
	
	/**
	 * 保存退款订单
	 */
	@RequestMapping("/saveRefund.json")
	@ResponseBody
	public JsonResponse productRefundOrder(HttpServletRequest request,String reason,String deliID,String productId){
		JsonResponse jsonResponse = new JsonResponse();		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("5259694");
		BigInteger refundId = ParamUtils.getBigInteger(request, "refundId", null);
		BigInteger reasonId = ParamUtils.getBigInteger(request, "reasonId", null);
		try {
			if(refundId!=null){
				EbuyRefundOrder ebuyRefund = new EbuyRefundOrder();
				ebuyRefund.setId(refundId);
				ebuyRefund.setSys0DelState(3);
				ebuyRefundOrderService.updateEbuyRefundOrder(ebuyRefund);
			}
			if(StringUtils.isNotEmpty(deliID) && reasonId!=null){
				EbuyDeliveryOrder ebuyDeliveryOrder = ebuyDeliveryOderService.getEbuyDeliveryOrderBySeqId(new BigInteger(deliID));
				if (ebuyDeliveryOrder.getRefundStatus() != null && ebuyDeliveryOrder.getRefundStatus() == 1) {
					throw new BusinessRuntimeException("ebuy.refund.is.inRefund");
				}
				Map<String, Object> paramMaps = new HashMap<String, Object>();
				BigInteger deliveryOrderId = new BigInteger(deliID);
				paramMaps.put("edoId", deliveryOrderId);
				EbuyDeliveryOrderEntity deliveryOrderEntity = ebuyService.getebuyDeliveryOrderEntityById(paramMaps);
				//设置运单状态为退款
				EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
				deliveryOrder.setId(deliveryOrderId);
				deliveryOrder.setRefundStatus(1);
				ebuyDeliveryOderService.updateEbuyDeliveryOrder(deliveryOrder);
				List<EbuyDeliveryOrderProduct> delProductList =  deliveryOrderEntity.getEbuyDeliveryOrderProductList();
				EbuyRefundOrder ebuyRefundOrder = new EbuyRefundOrder();
				EbuyRefundOrderProduct ebuyRefundOrderProduct = new EbuyRefundOrderProduct();
				ebuyRefundOrder.setReason(reason);
				BigInteger refundTableId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_refund_order);
				//上传审核图片
				List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "photoimage");
				String picUrl = uploadrefundPic(userId,picList);
				//申请选择的原因
				ebuyRefundOrder.setRefundPhotoes(picUrl);
				ebuyRefundOrder.settEbuyRefundAuditFId(reasonId);
				ebuyRefundOrder.settSupplyMerchantFId(deliveryOrderEntity.gettSupplyMerchantFId());
				ebuyRefundOrder.setBuyerId(userId);
				ebuyRefundOrder.setCreateTime(CnfantasiaCommbusiUtil.getCurrentTime());
				ebuyRefundOrder.settEbuyDeliveryOrderFId(deliveryOrderId);
				ebuyRefundOrder.setId(refundTableId);
				ebuyRefundOrder.setRefundStatus(1);
				//判断退款账户
				EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(deliveryOrderEntity.gettEbuyOrderFId());
				EbuyDeliveryOrder refundDeliveryOrder = ebuyDeliveryOderService.getEbuyDeliveryOrderBySeqId(deliveryOrderId);
				if (refundDeliveryOrder.getTotalCoupon() == null
						|| refundDeliveryOrder.getTotalCoupon().compareTo(0L) == 0) {
					if(ebuyOrder.getPayMethod()!=null){
						ebuyRefundOrder.setPayStatus(Integer.parseInt(ebuyOrder.getPayMethod()));
					}
				}else{
					ebuyRefundOrder.setPayStatus(4);
				}
				long applyfee = 0L;

				//部分退款
				if(StringUtils.isNotEmpty(productId)){
					List<String> proList = Arrays.asList(productId.split(","));
					for(String proId :proList){
						for(EbuyDeliveryOrderProduct deliOrder:delProductList){
							if(deliOrder.gettEbuyProductFId().compareTo(new BigInteger(proId))==0){
								ebuyRefundOrderProduct.settEbuyDeliveryOrderFId(deliveryOrderId);
								ebuyRefundOrderProduct.settEbuyProductFId(new BigInteger(proId));
								ebuyRefundOrderProduct.settEbuyRefundOrderFId(refundTableId);
								ebuyRefundOrderProduct.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_refund_order_product));
								ebuyRefundOrderProduct.settEbuyOrderHasTEbuyProductFId(deliOrder.gettEbuyOrderHasTEbuyProductFId());
								EbuyOrderHasTEbuyProduct eoHasPro =ebuyOrderHasTEbuyProductBaseService.
										getEbuyOrderHasTEbuyProductBySeqId(deliOrder.gettEbuyOrderHasTEbuyProductFId());

								//充值流量的价格
								if(deliOrder.gettEbuyProductFId().compareTo(new BigInteger("-1"))==0){
									Map<String, Object> paramMap = new HashMap<String, Object>();
									paramMap.put("orderId", deliOrder.gettEbuyOrderFId());
									paramMap.put("productId", deliOrder.gettEbuyProductFId());
									EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
									if(flowRecharge!=null){
										applyfee += (flowRecharge.getPrice().multiply(new BigDecimal(100))).longValue()*eoHasPro.getProductQty();
									}else{
										applyfee += eoHasPro.getProductPrice()*eoHasPro.getProductQty();
									}
								}else{
									applyfee += eoHasPro.getProductPrice()*eoHasPro.getProductQty();
								}
								ebuyRefundOderProductService.insertEbuyRefundOrderProduct(ebuyRefundOrderProduct);
								break;
							}
						}
					}
					long refundRedEnvelope = applyfee > deliveryOrderEntity.getTotalCoupon() ? deliveryOrderEntity.getTotalCoupon() : applyfee;
					long refundMoney = applyfee - refundRedEnvelope;
					ebuyRefundOrder.setApplyFee(PriceUtil.div100(applyfee).doubleValue());
					ebuyRefundOrder.setRefundFee(PriceUtil.div100(refundMoney + refundRedEnvelope).doubleValue());
					ebuyRefundOrder.setRefundRedEnvelope(PriceUtil.div100(refundRedEnvelope).doubleValue());
					ebuyRefundOrder.setRefundMoney(PriceUtil.div100(refundMoney).doubleValue());
					ebuyRefundOrder.setStatus(1);
				//全部退款
				}else{
					for(EbuyDeliveryOrderProduct delOrder:delProductList){
						ebuyRefundOrderProduct.settEbuyDeliveryOrderFId(deliveryOrderId);
						ebuyRefundOrderProduct.settEbuyRefundOrderFId(refundTableId);
						ebuyRefundOrderProduct.settEbuyProductFId(delOrder.gettEbuyProductFId());
						ebuyRefundOrderProduct.settEbuyOrderHasTEbuyProductFId(delOrder.gettEbuyOrderHasTEbuyProductFId());
						ebuyRefundOrderProduct.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_refund_order_product));
						ebuyRefundOderProductService.insertEbuyRefundOrderProduct(ebuyRefundOrderProduct);
//						EbuyOrderHasTEbuyProduct eoHasPro =ebuyOrderHasTEbuyProductBaseService.getEbuyOrderHasTEbuyProductBySeqId(delOrder.gettEbuyOrderHasTEbuyProductFId());
						//充值流量的价格
						/*if(delOrder.gettEbuyProductFId().compareTo(new BigInteger("-1"))==0){
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("orderId", delOrder.gettEbuyOrderFId());
							paramMap.put("productId", delOrder.gettEbuyProductFId());
							EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
							if(flowRecharge!=null){
								applyfee += (flowRecharge.getPrice().multiply(new BigDecimal(100))).longValue()*eoHasPro.getProductQty();
							}else{
								applyfee += eoHasPro.getProductPrice()*eoHasPro.getProductQty();
							}
						}else{
							applyfee += eoHasPro.getProductPrice()*eoHasPro.getProductQty();
						}*/
					}
					/*BigDecimal realFeal = PriceUtil.div100(deliveryOrderEntity.getDeliveryRealFee());
					BigDecimal feal = PriceUtil.div100(applyfee);
					BigDecimal reFundradiofee = PriceUtil.div100(reFundradio);
					BigDecimal refundFeal = realFeal.add(feal);
					ebuyRefundOrder.setApplyFee(realFeal.add(PriceUtil.div100(applyfee)).doubleValue());
					ebuyRefundOrder.setRefundFee(refundFeal.multiply(reFundradiofee).doubleValue());*/
					long refundRedEnvelope = deliveryOrderEntity.getTotalCoupon();
					long refundMoney = deliveryOrderEntity.getAmount();
					ebuyRefundOrder.setApplyFee(PriceUtil.div100(refundMoney + refundRedEnvelope).doubleValue());
					ebuyRefundOrder.setRefundFee(PriceUtil.div100(refundMoney + refundRedEnvelope).doubleValue());
					ebuyRefundOrder.setRefundRedEnvelope(PriceUtil.div100(refundRedEnvelope).doubleValue());
					ebuyRefundOrder.setRefundMoney(PriceUtil.div100(refundMoney).doubleValue());
					ebuyRefundOrder.setStatus(2);
				}
				//更新订单时间，退款订单重新排序
				ebuyOrder.setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
				ebuyOrderBaseService.updateEbuyOrder(ebuyOrder);
				ebuyRefundOrderService.insertEbuyRefundOrder(ebuyRefundOrder);
				jsonResponse.setStatus("0000");
				jsonResponse.setMessage("保存成功");
			}else{
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("保存失败");
			}
		}catch (BusinessRuntimeException e){
			logger.error(e.getMessage(), e);
			jsonResponse.setStatus("0002");
			Object[] paramArrayOfObject = e.getParamArrayOfObject();
			String msg = MessageSourceUtil.getMessage(e.getErrCode(), paramArrayOfObject);
			jsonResponse.setMessage(msg);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
			jsonResponse.setStatus("0003");
			jsonResponse.setMessage("上传图片失败，请联系管理员！");
		}
		
		return jsonResponse;
	}
	
	/**退款原因列表
	 * 
	 */
	@RequestMapping("/refundReasonList.json")
	@ResponseBody
	public JsonResponse refundReasonList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//退款原因
		List<Map<String, Object>> reasonList = new ArrayList<Map<String,Object>>();			
		List<EbuyRefundAudit> reasons = ebuyRefundAuditBaseService.getEbuyRefundAuditByCondition(null);
		for(EbuyRefundAudit auditReason :reasons){
			Map<String, Object> reason = new HashMap<String, Object>();
			reason.put("reasonId", auditReason.getId());
			reason.put("reason", auditReason.getReason());
			reasonList.add(reason);
		}
		jsonResponse = ControllerUtils.addPageInfo(jsonResponse, reasonList);
		return jsonResponse;
	}
	/**
	 * 查看运单退款详情
	 */
	@RequestMapping("/refundDetails.json")
	@ResponseBody
	public JsonResponse refundDetails(HttpServletRequest request,String deliID){
		JsonResponse jsonResponse = new JsonResponse();	
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String serverurl = getrefundPicUrl();
//		BigInteger userId = new BigInteger("5259694");
		if(StringUtils.isNotEmpty(deliID)){
			BigInteger deliveryOrderId = new BigInteger(deliID);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			EbuyRefundOrder ebuyRefundOrder = ebuyService.getRefundOrderbydelId(deliveryOrderId);
			List<EbuyRefundOrderProduct> productList = ebuyService.getRefundProductList(ebuyRefundOrder.getId());
			paramMap.put("edoId", deliveryOrderId);
			EbuyDeliveryOrderEntity deliveryOrderEntity = ebuyService.getebuyDeliveryOrderEntityById(paramMap);
			EbuyOrderEntity ebuyOrderEntity = ebuyService.getEbuyOrderEntityDetail(userId, deliveryOrderEntity.gettEbuyOrderFId(), null, null);
			Set<EbuyOrderHasTEbuyProductEntity_Product> respro = ebuyOrderEntity.fetchEbuyOrderHasTEbuyProductEntityList(deliveryOrderEntity);
			List<EbuyOrderHasTEbuyProductEntity_Product> resList = new ArrayList<EbuyOrderHasTEbuyProductEntity_Product>();
			long productNum = 0L;
			if(productList !=null && productList.size()>0){
				for(EbuyRefundOrderProduct erProduct :productList){
					for(EbuyOrderHasTEbuyProductEntity_Product eHproduct:respro){
						if(erProduct.gettEbuyOrderHasTEbuyProductFId().compareTo(eHproduct.getId())==0){
							resList.add(eHproduct);
							productNum += eHproduct.getProductQty();
							break;
						}
					}
				}
			}
			List<Map<String, Object>> resLists = new ArrayList<Map<String,Object>>();
			for(EbuyOrderHasTEbuyProductEntity_Product resProduct:resList){
				Map<String, Object> proList = new HashMap<String, Object>();
				proList.put("productName", resProduct.getEbuyProductEntity().getName());
				//充值时流量的价格
				if(resProduct.getEbuyProductEntity().getFilmTicketNum() != null && resProduct.getEbuyProductEntity().getFilmTicketNum() ==-1) {
					Map<String, Object> paramMaps = new HashMap<String, Object>();
					paramMaps.put("orderId", resProduct.gettEbuyOrderFId());
					paramMaps.put("productId", resProduct.gettEbuyProductFId());
					EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMaps);
					if(flowRecharge!=null){
						proList.put("productPrice", flowRecharge.getPrice());
					}else{
						proList.put("productPrice", PriceUtil.div100(resProduct.getProductPrice()));
					}
				}else{
					proList.put("productPrice", PriceUtil.div100(resProduct.getProductPrice()));
				}
				proList.put("productNum", resProduct.getProductQty());
				proList.put("productPic", serverurl+resProduct.getEbuyProductEntity().getPicBase());
				resLists.add(proList);
			}
			String reason = "";
			if(deliveryOrderEntity.getRefundStatus()==1){
				reason += "审核中，请静候佳音";
			}else if(deliveryOrderEntity.getRefundStatus()==2){
				if(ebuyRefundOrder.getPayStatus() == 4){
					reason +="退款成功，退款金额已放入粮票中";
				}else{
					reason +="退款成功，退款将汇入您的付款账户中";
				}
			}else if(deliveryOrderEntity.getRefundStatus()==3){
				reason +="退款审核不通过(" + ebuyRefundOrder.getRefundReason() +")";
			}
			jsonResponse.putData("applytime", ebuyRefundOrder.getCreateTime());
			jsonResponse.putData("refundstatus", deliveryOrderEntity.getRefundStatus());
			jsonResponse.putData("refundReason", ebuyRefundOrder.getReason());
			jsonResponse.putData("choiceReason", ebuyRefundAuditBaseService.getEbuyRefundAuditBySeqId(ebuyRefundOrder.gettEbuyRefundAuditFId()).getReason());//选择的原因
			jsonResponse.putData("refundfailReason", reason);
			jsonResponse.putData("refundTime", ebuyRefundOrder.getSys0UpdTime());
			jsonResponse.putData("refundId", ebuyRefundOrder.getId());
			jsonResponse.putData("deliID", deliveryOrderId);
			if(StringUtils.isNotEmpty(ebuyRefundOrder.getRefundPhotoes())){
				List<String> pics = Arrays.asList(ebuyRefundOrder.getRefundPhotoes().split(";"));
				List<String> picList = new ArrayList<String>();
				for(String pic:pics){
					String  picUri= pic.substring(0,pic.lastIndexOf("."));
					String picSuffix=pic.substring(pic.lastIndexOf("."),pic.length());
					picList.add(serverurl+picUri+"/150"+picSuffix);
				}
				jsonResponse.putData("refundproPic",picList);
			}else{
				jsonResponse.putData("refundproPic",null);
			}
			jsonResponse.putData("refundproPrice",ebuyRefundOrder.getRefundFee()); //退款金额
			jsonResponse.putData("refundproNum",productNum); //退款商品件数
			jsonResponse.putData("supplyName", deliveryOrderEntity.getEbuySupplyMerchant().getName());
			jsonResponse = ControllerUtils.addPageInfo(jsonResponse, resLists);
		}else{
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("请选择要退款的运单");
		}
		return jsonResponse;
	}
	//获取图片地址
	private String getrefundPicUrl() {
		String basePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
		/*FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
		String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();*/
		String imageServerUrl = sysParamManager.getImageServerUrl(SysParamKey.PRODUCT_PIC_BASE_PATH);
//		List<String> picList = new ArrayList<String>();
//		for(String pic:pics){
//			String  picUri= pic.substring(0,pic.lastIndexOf("."));
//			String picSuffix=pic.substring(pic.lastIndexOf("."),pic.length());
//			picList.add(accessBaseUrl+basePath+picUri+"/150"+picSuffix);
//		}
		return imageServerUrl+basePath;
	}
	
	/**
	 * 提交订单,并生成设置订单的粮票信息
	 */
	@RequestMapping("/submitOrder.json")
	@ResponseBody
	public JsonResponse submitOrder(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("deliveryAddressId"));
		BigInteger productId = new BigInteger(request.getParameter("productId"));
		BigInteger productSpecId = null;
		{
			String productSpecIdStr = request.getParameter("productSpecId");
			if(!StringUtils.isEmpty(productSpecIdStr)){
				productSpecId = new BigInteger(productSpecIdStr);
			}
		}
		Long productQty = Long.parseLong(request.getParameter("productQty"));
		Long hbAmount = null;
		if(!StringUtils.isEmpty(request.getParameter("hbAmount"))){
			Double hbAmountDouble = null;
			hbAmountDouble = Double.parseDouble(request.getParameter("hbAmount"));//粮票金额
			hbAmount = PriceUtil.multiply100(hbAmountDouble);
		}
		Set<BigInteger> couponIdSet = null;
		if(!StringUtils.isEmpty(request.getParameter("couponIdList"))){//优惠的Id列表
			List<BigInteger> couponIdList = JSON.parseArray(request.getParameter("couponIdList"), BigInteger.class);
			couponIdSet = new HashSet<BigInteger>();
			couponIdSet.addAll(couponIdList);
		}
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		String invoice_companyName = request.getParameter("companyName");
		String invoice_productTypeName = request.getParameter("productTypeName");

		int deliveryType = ParamUtils.getInt(request, "deliveryType", EbuyDict.EbuyDeliveryMethodType.Express);
		
		//海淘商品用到这两个字段
		String identify = ParamUtils.getString(request, "identify", null);
		String idName = ParamUtils.getString(request, "idName", null);
		EbuyIdentifyInfo idInfo = null;
		if(!StringUtils.isEmpty(identify)) {
			idInfo = new EbuyIdentifyInfo();
			idInfo.setUserId(userId.longValue());
			idInfo.setIdentify(identify);
			idInfo.setIdName(idName);
		}
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", new BigInteger("-1"));
		//2.交互
		EbuyExtandBuyParam ebuyExtandBuyParam = null;
//		EbuyOrderEntity ebuyOrderEntity=ebuyService.submitOrder(userId, deliveryAddressId, productId, productQty, deliveryMethodId,hbAmount,pointType);
		EbuyOrderEntity ebuyOrderEntity = ebuyService.submitOrder(userId, deliveryAddressId, gbId, productId, productQty, productSpecId, ebuyExtandBuyParam,
				hbAmount, pointType,parseWlAppType(request), invoice_companyName, invoice_productTypeName,couponIdSet,parseJfq_SubType(request),parseSubChannelId(request), idInfo, null);
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 提交订单（多类商品时）,并生成设置订单的粮票信息
	 */
	@RequestMapping("/submitOrderMulti.json")
	@ResponseBody
	@RepeatReqValidate
	public JsonResponse submitOrderMulti(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger deliveryAddressId = new BigInteger(request.getParameter("deliveryAddressId"));
		List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
		if(productIdQtyList==null||productIdQtyList.size()<=0){
			throw new BusinessRuntimeException("EbuyController.submitOrderMulti.productList.empty");
		}
//		BigInteger deliveryMethodId = null;//new BigInteger(request.getParameter("deliveryMethodId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Long hbAmount = null;
		if(!StringUtils.isEmpty(request.getParameter("hbAmount"))){
			Double hbAmountDouble = null;
			hbAmountDouble = Double.parseDouble(request.getParameter("hbAmount"));//粮票金额
			hbAmount = PriceUtil.multiply100(hbAmountDouble);
		}
		Set<BigInteger> couponIdSet = null;
		if(!StringUtils.isEmpty(request.getParameter("couponIdList"))){//优惠的Id列表
			List<BigInteger> couponIdList = JSON.parseArray(request.getParameter("couponIdList"), BigInteger.class);
			couponIdSet = new HashSet<BigInteger>();
			couponIdSet.addAll(couponIdList);
		}
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", new BigInteger("-1"));
		String invoice_companyName = request.getParameter("companyName");
		String invoice_productTypeName = request.getParameter("productTypeName");
		
		//海淘商品用到这两个字段
		String identify = ParamUtils.getString(request, "identify", null);
		String idName = ParamUtils.getString(request, "idName", null);

		//供应商id-配送方式  ， 配送方式，1需要计算运费，2是自提（没有运费）
		List<MerchantIdDeliveryType> merchantIdDeliveryTypeList = new ArrayList<MerchantIdDeliveryType>();
		if(!StringUtils.isEmpty(request.getParameter("merchantIdDeliveryTypeList"))){
			merchantIdDeliveryTypeList = JSON.parseArray(request.getParameter("merchantIdDeliveryTypeList"), MerchantIdDeliveryType.class);
		}

		EbuyIdentifyInfo idInfo = null;
		if(!StringUtils.isEmpty(identify)) {
			idInfo = new EbuyIdentifyInfo();
			idInfo.setUserId(userId.longValue());
			idInfo.setIdentify(identify);
			idInfo.setIdName(idName);
		}
		
		//2.交互
		{//增加校验： 配送地址的收货人或收货人的电话不能为空   added by wenfq 2016-01-20
			EbuyDeliveryAddressBaseService ebuyDeliveryAddressBaseService = (EbuyDeliveryAddressBaseService) CnfantasiaCommbusiUtil.getBeanManager("ebuyDeliveryAddressBaseService");
			EbuyDeliveryAddress ebuyDeliveryAddress = ebuyDeliveryAddressBaseService.getEbuyDeliveryAddressBySeqId(deliveryAddressId);
			if(StringUtils.isEmpty(ebuyDeliveryAddress.getPhone()) || StringUtils.isEmpty(ebuyDeliveryAddress.getPeopleName())){
				throw new BusinessRuntimeException("ebuy.order.submit.phoneORpeople.isNull");
			}
		}
		
		EbuyOrderEntity ebuyOrderEntity = ebuyService.submitOrderMulti(userId, deliveryAddressId, gbId, productIdQtyList, hbAmount, pointType,parseWlAppType(request), invoice_companyName,
				invoice_productTypeName,couponIdSet,parseJfq_SubType(request),parseSubChannelId(request), idInfo, merchantIdDeliveryTypeList);
		
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		
		//清除购物车新用户专享商品
		UserIdType userIdType = commonDeviceService.getUserIdType();
		ebuyService.removeProdFromBuyCarNewUser(userIdType.getUserId(),userIdType.getUserType(),pointType,parseWlAppType(request));
		
		return jsonResponse;
	}
	/**
	 * 查询订单详情
	 */
	@RequestMapping("/qryOrderDetail.json")
	@ResponseBody
	public JsonResponse qryOrderDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity=ebuyService.getEbuyOrderEntityDetail(userId, orderId,pointType,parseWlAppType(request));
		
		//拿相应的电影票
		for(EbuyOrderHasTEbuyProductEntity_Product orderProduct : ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList()) {
			if(orderProduct.getEbuyProductEntity().getFilmTicketNum() != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("orderId", orderProduct.gettEbuyOrderFId());
				paramMap.put("productId", orderProduct.gettEbuyProductFId());
				if(orderProduct.getEbuyProductEntity().getFilmTicketNum() > 0) { //电影票
					List<EbuyFilmTicket> filmTicketList = ebuyFilmTicketService.getFilmTickList(paramMap);
					orderProduct.setFilmTicketList(filmTicketList);
				} else if(orderProduct.getEbuyProductEntity().getFilmTicketNum() == -1) { //充值流量
					EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
					orderProduct.setFlowRecharge(flowRecharge);
				}
			}
		}
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		if ("Y".equals(request.getParameter("fromLA"))) {
			List<Coupon> couponList = couponService.getShareOrderCouponCanSend(orderId);
			resMap.put("shareCouponSize", couponList == null ? 0 : couponList.size());
		}
		resMap.put("filmPayUrl", sysParamManager.getSysParaValue(SysParamKey.FILE_PAY_URL));
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	/**
	 * 查询订单列表
	 */
	@RequestMapping("/qryOrderList.json")
	@ResponseBody
	public JsonResponse qryOrderList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		PageModel pageModel = ControllerUtils.getPageModel(request);
		List<Integer> yesStatus = null;
		{//需要的订单状态
			String orderStatusStr = request.getParameter("orderStatus");
			if(!StringUtils.isEmpty(orderStatusStr)){
				yesStatus = JSON.parseArray(orderStatusStr, Integer.class);
			}
		}
		//默认不需要的订单状态
		List<Integer> noStatus=new ArrayList<Integer>();
		noStatus.add(EbuyDict.EbuyOrder_Status.YiShanChu);//不包含已删除的订单
		//用户
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("50003");
		//2.交互
		List<EbuyOrderEntity> ebuyOrderList = ebuyService.getOrderList(userId, yesStatus, noStatus, pageModel,pointType,parseWlAppType(request));
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < ebuyOrderList.size(); i++){
			EbuyOrderEntity ebuyOrderEntity=ebuyOrderList.get(i);
			//拿相应的电影票
			for(EbuyOrderHasTEbuyProductEntity_Product orderProduct : ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList()) {
				if(orderProduct.getEbuyProductEntity().getFilmTicketNum() != null) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("orderId", orderProduct.gettEbuyOrderFId());
					paramMap.put("productId", orderProduct.gettEbuyProductFId());
					
					if(orderProduct.getEbuyProductEntity().getFilmTicketNum() > 0) { //电影票
						List<EbuyFilmTicket> filmTicketList = ebuyFilmTicketService.getFilmTickList(paramMap);
						orderProduct.setFilmTicketList(filmTicketList);
					} else if(orderProduct.getEbuyProductEntity().getFilmTicketNum() == -1) { //充值流量
						EbuyFlowRecharge flowRecharge = ebuyFlowRechargeService.getFlowRecharge(paramMap);
						orderProduct.setFlowRecharge(flowRecharge);
					}
				}
			}
			
			Map<String,Object> tmpMap = ebuyOrderEntity2Map(ebuyOrderEntity);
			resList.add(tmpMap);
		}
		//3.结果处理
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 客户端标记订单已支付成功
	 */
	@RequestMapping("/markOrderClientPayById.json")
	@ResponseBody
	public JsonResponse markOrderClientPayById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ebuyService.markOrderIsClientPay(userId, orderId,pointType,parseWlAppType(request));

		//如果是一元夺宝，会更新购买记录；不是一元夺宝，没记录
		FlashDealBuyRecord record = new FlashDealBuyRecord();
		record.settEbuyOrderFId(orderId);
		List<FlashDealBuyRecord> recordList = flashDealBuyRecordBaseService.getFlashDealBuyRecordByCondition(MapConverter.toMap(record));
		if (!DataUtil.isEmpty(recordList)) {
			EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
			if (ebuyOrder.getStatus().equals(EbuyDict.EbuyOrder_Status.DaiFuKuan)) {
				for (FlashDealBuyRecord flashDealBuyRecord : recordList) {
					flashDealBuyRecord.setRecordStatus(FlashDealActivityDict.BuyRecord.Alread_Pay);
				}
				flashDealBuyRecordBaseService.updateFlashDealBuyRecordBatch(recordList);
			}
		}

		/*
		//如果支付宝回调成功了，立即返回此接口，如果没有回调，循环等待回调后立即返回，或者N次后仍未回调则返回
		//此种方式处理是不合理的，因为服务端是不能等待。在等待的时候，占用了一个tomcat的连接，如果此接口调用频繁且等待的机率大，则容易导致tomcat线程耗完。考虑到暂时不存在此情况，也没有更好的方案。
		boolean isPaySuccess = false;
		int count = 0;
		while(!isPaySuccess) {
			isPaySuccess = RedisCacheHandler.exsits(orderId.toString());
			if(isPaySuccess || count++ > 14) {
				return jsonResponse;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
		*/
		boolean isPaySuccess = RedisCacheHandler.exsits(orderId.toString());
		jsonResponse.putData("paySuccess", isPaySuccess);
		
		//3.结果处理
		return jsonResponse;
	}
	
	@Resource
	ILimitBuyService limitBuyService;
	
	/**
	 * 根据Id删除订单
	 */
	@RequestMapping("/delOrderById.json")
	@ResponseBody
	public JsonResponse delOrderById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ebuyService.updateOrder2Delete(userId, orderId,pointType,parseWlAppType(request));
		
		limitBuyService.deleteLimitActivityByOrderId(orderId);
		//3.结果处理
		return jsonResponse;
	}
//	/**
//	 * 纯粮票优惠支付
//	 */
//	@RequestMapping("/couponOnlyPay.json")
//	@ResponseBody
//	public JsonResponse couponOnlyPay(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//`.搜集参数
//		//订单Id,使用的粮票金额
//		//2.交互
//		{//同时完成预支付和支付流程
//			//2.1.订单刷新及订单优惠信息 生成支付记录
//			//2.2.标记订单状态为已支付
//			//2.3.
//		}
//		//3.结果处理
//		return jsonResponse;
//	}
	
	/**
	 * 确认收货
	 */
	@RequestMapping("/confirmReceived.json")
	@ResponseBody
	public JsonResponse confirmReceived(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		ebuyService.updateOrder2Received(userId, orderId,pointType,parseWlAppType(request));
		//3.结果处理
		return jsonResponse;
	}
	
	//************************************公共处理****************************************

	protected Map<String, Object> ebuyOrderEntity2Map(EbuyOrderEntity ebuyOrderEntity) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		//收货地址信息
		{
			EbuyDeliveryAddressEntity delvAddress = new EbuyDeliveryAddressEntity();
			delvAddress.setTargetType(ebuyOrderEntity.getDelivTargetType());
			delvAddress.setPeopleName(ebuyOrderEntity.getDelivPeopleName());
			delvAddress.setPhone(ebuyOrderEntity.getDelivPhone());
			delvAddress.setAddressDetail(ebuyOrderEntity.getDelivAddressDetail());
			SimpleDelivAddress simpleDelivAddress = new SimpleDelivAddress();
			simpleDelivAddress.setAddressArea(ebuyOrderEntity.getDelivAddressArea());
			simpleDelivAddress.setAddressDetail(ebuyOrderEntity.getDelivAddressDetail());
			simpleDelivAddress.setTargetId(ebuyOrderEntity.getDelivTargetId());

			Map<String, Object> delvAddressMap = commEntityConvertService.delvAddressEntity2Map(delvAddress, simpleDelivAddress);
			resMap.put("deliveryAddress", delvAddressMap);
		}
		{//供应商及商品信息
			BigDecimal flowTotalPrice = null;
			List<Map<String, Object>> resMerchList = new ArrayList<Map<String, Object>>();
			List<EbuyDeliveryOrderEntity> ebuyDeliveryOrderEntityList = ebuyOrderEntity.getEbuyDeliveryOrderEntityList();
			for (EbuyDeliveryOrderEntity ebuyDeliveryOrderEntity : ebuyDeliveryOrderEntityList) {
				EbuySupplyMerchant merchant = ebuyDeliveryOrderEntity.getEbuySupplyMerchant();
				Set<EbuyOrderHasTEbuyProductEntity_Product> buyCarProdList = ebuyOrderEntity.fetchEbuyOrderHasTEbuyProductEntityList(ebuyDeliveryOrderEntity);
				//供应商
				Map<String, Object> merch = merchant2Map(merchant);
				merch.put("isDelivOrderFullyRefund", ebuyDeliveryOrderEntity.isHasFullyRefund());
				merch.put("isSelfGet", ebuyDeliveryOrderEntity.getDeliveryType());
				Long totalCount = 0L;
				Long totalMoney = 0L;
				{//供应商对应的产品信息
					List<Map<String, Object>> resProductList = new ArrayList<Map<String, Object>>();
					for (EbuyOrderHasTEbuyProductEntity_Product buyCarProdRela : buyCarProdList) {
						EbuyProductWithCurrProductSpec ebuyProductInfo = new EbuyProductWithCurrProductSpec(buyCarProdRela.getEbuyProductEntity(), buyCarProdRela.getEbuyProductSpec());
						//2014-10-16 14:45:09 syl-upd 设置商品订单时候的价格
						ebuyProductInfo.setPriceDiscount(buyCarProdRela.getProductPrice());
						Map<String, Object> tmpProdMap = productInfo2Map(ebuyProductInfo, null, null, null, null, null);
						//商品购买的数量
						tmpProdMap.put("productQty", buyCarProdRela.getProductQty());
						tmpProdMap.put("priceDiscount", PriceUtil.div100(buyCarProdRela.getProductPrice()));

						List<FilmTicketDo> filmTicketList = new ArrayList<FilmTicketDo>();
						if (buyCarProdRela.getFilmTicketList() != null) {
							for (EbuyFilmTicket ticket : buyCarProdRela.getFilmTicketList()) {
								FilmTicketDo fileTicketDo = new FilmTicketDo();
								fileTicketDo.setIdentifyCode(ticket.getIdentifyCode());
								fileTicketDo.setExpireTm(DateUtils.formatDate(ticket.getExpireTm()));
								if (ticket.getType() == 1) {
									resMap.put("hasFilmTicket", 1);
								}
								filmTicketList.add(fileTicketDo);
							}
						} else if (buyCarProdRela.getFlowRecharge() != null) {
							EbuyFlowRecharge flowRecharge = buyCarProdRela.getFlowRecharge();
							tmpProdMap.put("priceDiscount", flowRecharge.getPrice());
							tmpProdMap.put("price", flowRecharge.getPrice());
							tmpProdMap.put("name", flowRecharge.getName());
							flowTotalPrice = flowRecharge.getPrice();
						}
						tmpProdMap.put("filmTicketList", filmTicketList);
						tmpProdMap.put("isProductRefund", buyCarProdRela.isHasRefund());

						resProductList.add(tmpProdMap);

						totalCount += buyCarProdRela.getProductQty();
						totalMoney += buyCarProdRela.getProductPrice() * buyCarProdRela.getProductQty();//乘以商品数量
					}
					merch.put("productList", resProductList);
					merch.put("signalMer_totalCount", totalCount);//当前供应商商品总数
					merch.put("signalMer_totalCount_productType", buyCarProdList.size());//当前供应商商品类目总数
					merch.put("signalMer_totalMoney", PriceUtil.div100(totalMoney));    //当前供应商商品总价
					if (flowTotalPrice != null) {
						merch.put("signalMer_totalMoney", flowTotalPrice);
					}
				}
				{//供应商对应的配送单信息
					merch.put("deliveryOrder", ebuyDeliveryOrder2Map(ebuyDeliveryOrderEntity));
				}
				{//配送单的快递公司信息
					EbuyExpressCompany expressCompany = ebuyDeliveryOrderEntity.getEbuyExpressCompany();
					if (expressCompany != null) {
						merch.put("expressCompany", ebuyExpressCompany2Map(ebuyDeliveryOrderEntity.getEbuyExpressCompany()));
					} else {
						merch.put("expressCompany", null);
					}
				}
				{//供应商对应的配送方式信息
					EbuyDeliveryMethod ebuyDeliveryMethod = ebuyDeliveryOrderEntity.getEbuyDeliveryMethod();
					ebuyDeliveryMethod.setFee(ebuyDeliveryOrderEntity.getDeliveryRealFee());//2014-10-16 15:38:14 syl-upd 增加设定下单时的费用
					merch.put("deliveryMethod", deliveryMethod2Map(ebuyDeliveryMethod));
				}
				resMerchList.add(merch);
			}
			resMap.put("productInfo", resMerchList);
			resMap.put("ext_totalCount", ebuyOrderEntity.getProductTotalCount());//商品总数
			resMap.put("ext_totalCount_productType", ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList().size());//商品类目总数
			resMap.put("ext_totalMoney", ebuyOrderEntity.getProductTotalPriceDiv100());//商品总价
			if (flowTotalPrice != null) {
				resMap.put("ext_totalMoney", flowTotalPrice);
			}
		}

		{//订单的优惠总额
			Long totalCouponAmount = ebuyOrderEntity.getTotalCouponAmount();
			if (totalCouponAmount != null) {
				resMap.put("totalCouponAmount", PriceUtil.div100(totalCouponAmount));
			} else {
				resMap.put("totalCouponAmount", 0);
			}
		}
		//订单信息
		{
			resMap.put("orderId", ebuyOrderEntity.getId());
			resMap.put("orderNo", ebuyOrderEntity.getOrderNo());
			resMap.put("createTime", ebuyOrderEntity.getBuyTime());//创建时间
			resMap.put("totalAmount", PriceUtil.div100(ebuyOrderEntity.getAmount()));//总金额
			resMap.put("deliveryFee", PriceUtil.div100(ebuyOrderEntity.getTotalDeliveryFee()));//运费
			resMap.put("totalRefundFee", ebuyOrderEntity.getTotalRefundFee());//总退款金额
			resMap.put("payStatus", ebuyOrderEntity.getPayStatus());//支付状态
			resMap.put("payTime", ebuyOrderEntity.getPayTime());//支付时间
			resMap.put("payMethod", ebuyOrderEntity.getPayMethod());//支付方式
			{//订单状态
				resMap.put("orderStatus", ebuyOrderEntity.getStatus());
				if (EbuyDict.EbuyOrder_Status.DaiFaHuo.compareTo(ebuyOrderEntity.getStatus()) == 0) {//部分发货的处理
					resMap.put("orderStatus", ebuyOrderEntity.getStatus() + "." + ebuyOrderEntity.getDelivStatus());
				}
			}
			{//订单展示状态
				if (EbuyDict.EbuyOrder_Status.DaiFuKuan.compareTo(ebuyOrderEntity.getStatus()) == 0) {
					resMap.put("orderStatusShow", "DaiFuKuan");
				} else if (EbuyDict.EbuyOrder_Status.YiQuXiao.compareTo(ebuyOrderEntity.getStatus()) == 0) {
					resMap.put("orderStatusShow", "YiQuXiao");
				} else if (EbuyDict.EbuyOrder_Status.DaiFaHuo.compareTo(ebuyOrderEntity.getStatus()) == 0
						|| EbuyDict.EbuyOrder_Status.DaiShouHuo.compareTo(ebuyOrderEntity.getStatus()) == 0
						|| EbuyDict.EbuyOrder_Status.DaiPingJia.compareTo(ebuyOrderEntity.getStatus()) == 0
						|| EbuyDict.EbuyOrder_Status.YiWanCheng.compareTo(ebuyOrderEntity.getStatus()) == 0
						) {
					resMap.put("orderStatusShow", "YiFuKuan");
				} else {
					resMap.put("orderStatusShow", null);
				}
			}
			resMap.put("clientPayStatus", ebuyOrderEntity.getClientPayStatus());
		}
		return resMap;
	}
	
//	/**
//	 * 2014-7-3 8:47:23 02
//	 * @param ebuyOrderEntity
//	 * @return
//	 */
//	private Map<String,Object> ebuyOrderEntity2Map(EbuyOrderEntity ebuyOrderEntity){
//		Map<String,Object> resMap = new HashMap<String, Object>();
//		//收货地址信息
//		{
////			EbuyDeliveryAddressEntity delvAddress=ebuyOrderEntity.getEbuyDeliveryAddressEntity();
////			if(delvAddress!=null){
//				Map<String,Object> delvAddressMap = new HashMap<String, Object>();
//				delvAddressMap.put("targetType", ebuyOrderEntity.getDelivTargetType());
//				delvAddressMap.put("targetId", ebuyOrderEntity.getDelivTargetId());
//				delvAddressMap.put("userName", ebuyOrderEntity.getDelivPeopleName());
//				delvAddressMap.put("userPhone", ebuyOrderEntity.getDelivPhone());
//				delvAddressMap.put("addressArea", ebuyOrderEntity.getDelivAddressArea());
//				delvAddressMap.put("addressDetail", ebuyOrderEntity.getDelivAddressDetail());
//				delvAddressMap.put("addressTotal", ebuyOrderEntity.getDelivAddressArea()+ebuyOrderEntity.getDelivAddressDetail());
//				resMap.put("deliveryAddress", delvAddressMap);
////			}
//		}
//		//商品信息
//		{
////			String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
//			List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
//			List<EbuyOrderHasTEbuyProductEntity_Product> ebuyProductEntityList=ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList();
//			for(EbuyOrderHasTEbuyProductEntity_Product ebuyOrderHasTEbuyProduct:ebuyProductEntityList){
//				EbuyProductEntity ebuyProductInfo=ebuyOrderHasTEbuyProduct.getEbuyProductEntity();
//				String defaultDeliveryName = ebuyProductInfo.getDefaultDeliveryName();
//				List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList=ebuyProductInfo.getEbuyProductHasTEbuyAuthEntityList();
//				String supplyMerchantName = ebuyProductInfo.getEbuySupplyMerchantEntity().getName();
//				ebuyProductInfo.setPriceDiscount(ebuyOrderHasTEbuyProduct.getProductPrice());//使用订单中保存的商品实际单价覆盖商品当前单价
//				ebuyProductInfo.setPrice(null);//设置商品原始价格为空
//				Map<String,Object> tmpMap = productInfo2Map(ebuyProductInfo, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, null, null, supplyMerchantName);
////				//供应商信息 暂时去掉
////				EbuySupplyMerchantEntity ebuySupplyMerchant=tmpEntity.getEbuySupplyMerchantEntity();
////				if(ebuySupplyMerchant!=null){
////					tmpMap.put("merchantId", ebuySupplyMerchant.getId());
////					tmpMap.put("merchantName", ebuySupplyMerchant.getName());
////					tmpMap.put("merchantAddress", ebuySupplyMerchant.getAddress());
////					tmpMap.put("merchantTel", ebuySupplyMerchant.getTel());
////				}
//				//订单相关信息
//				tmpMap.put("productQty", ebuyOrderHasTEbuyProduct.getProductQty());//购买数量
////				tmpMap.put("productPrice", PriceUtil.div100(ebuyOrderHasTEbuyProduct.getProductPrice()));//商品实际单价
////				tmpMap.put("deliveryRealFee", PriceUtil.div100(ebuyOrderHasTEbuyProduct.getDeliveryRealFee()));//商品实际配送价格 不需要
////				tmpMap.put("deliveryId", ebuyOrderHasTEbuyProduct.getDeliveryId());//配送方式Id 不需要
//				productInfoList.add(tmpMap);
//			}
//			resMap.put("productInfo", productInfoList);
//		}
//		//配送信息
//		{
////			EbuyDeliveryMethod ebuyDeliveryMethod=ebuyOrderEntity.getEbuyDeliveryMethod();
////			if(ebuyDeliveryMethod!=null){
//				Map<String,Object> ebuyDeliveryMethod = new HashMap<String, Object>();
//				ebuyDeliveryMethod.put("name", ebuyOrderEntity.getDelivmName());
//				ebuyDeliveryMethod.put("fee", ebuyOrderEntity.getDelivmFee());
//				ebuyDeliveryMethod.put("desc", ebuyOrderEntity.getDelivmDesc());
//				resMap.put("deliveryMethod", ebuyDeliveryMethod);
////			}
//		}
//		//订单信息
//		{
//			resMap.put("orderId", ebuyOrderEntity.getId());
//			resMap.put("orderNo", ebuyOrderEntity.getOrderNo());
//			resMap.put("createTime", ebuyOrderEntity.getBuyTime());//创建时间
//			resMap.put("totalAmount", PriceUtil.div100(ebuyOrderEntity.getAmount()));//总金额
//			resMap.put("deliveryFee",  PriceUtil.div100(ebuyOrderEntity.getTotalDeliveryFee()));//运费
//			resMap.put("payStatus", ebuyOrderEntity.getPayStatus());//支付状态
//			resMap.put("payTime", ebuyOrderEntity.getPayTime());//支付时间
//			resMap.put("orderStatus", ebuyOrderEntity.getStatus());//订单状态
//		}
//		return resMap;
//	}
//	private Map<String,Object> ebuyOrderEntity2Map(EbuyOrderEntity ebuyOrderEntity){
//		Map<String,Object> resMap = new HashMap<String, Object>();
//		//收货地址信息
//		{
////			EbuyDeliveryAddressEntity delvAddress=ebuyOrderEntity.getEbuyDeliveryAddressEntity();
////			if(delvAddress!=null){
//			Map<String,Object> delvAddressMap = new HashMap<String, Object>();
//			delvAddressMap.put("targetType", ebuyOrderEntity.getDelivTargetType());
//			delvAddressMap.put("targetId", ebuyOrderEntity.getDelivTargetId());
//			delvAddressMap.put("userName", ebuyOrderEntity.getDelivPeopleName());
//			delvAddressMap.put("userPhone", ebuyOrderEntity.getDelivPhone());
//			delvAddressMap.put("addressArea", ebuyOrderEntity.getDelivAddressArea());
//			delvAddressMap.put("addressDetail", ebuyOrderEntity.getDelivAddressDetail());
//			delvAddressMap.put("addressTotal", ebuyOrderEntity.getDelivAddressArea()+ebuyOrderEntity.getDelivAddressDetail());
//			resMap.put("deliveryAddress", delvAddressMap);
////			}
//		}
//		//商品信息
//		{
//			String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
//			List<Map<String,Object>> productInfoList = new ArrayList<Map<String,Object>>();
//			List<EbuyOrderHasTEbuyProductEntity_Product> ebuyProductEntityList=ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList();
//			for(EbuyOrderHasTEbuyProductEntity_Product ebuyOrderHasTEbuyProduct:ebuyProductEntityList){
//				EbuyProductEntity tmpEntity=ebuyOrderHasTEbuyProduct.getEbuyProductEntity();
//				Map<String,Object> tmpMap = new HashMap<String, Object>();
//				tmpMap.put("name", tmpEntity.getName());
////				tmpMap.put("price", tmpEntity.getPrice());
////				tmpMap.put("priceDiscount", tmpEntity.getPriceDiscount());
////				tmpMap.put("defaultDeliveryName", tmpEntity.getDefaultEbuyDeliveryMethod().getName());
////				tmpMap.put("selNum", tmpEntity.getSelNum());
////				tmpMap.put("leftCount", tmpEntity.getLeftCount());
//				if(StringUtils.isEmpty(tmpEntity.getPicBase())){
//					tmpMap.put("picBase",null);
//				}else{
//					tmpMap.put("picBase", fileServerService.getAccessUrl(productPicBasePath+tmpEntity.getPicBase()));
//				}
//				if(StringUtils.isEmpty(tmpEntity.getPicBaseMini())){
//					tmpMap.put("picBaseMini",null);
//				}else{
//					tmpMap.put("picBaseMini", fileServerService.getAccessUrl(productPicBasePath+tmpEntity.getPicBaseMini()));
//				}
//				tmpMap.put("productId", tmpEntity.getId());
//				//供应商信息
//				EbuySupplyMerchantEntity ebuySupplyMerchant=tmpEntity.getEbuySupplyMerchantEntity();
//				if(ebuySupplyMerchant!=null){
//					tmpMap.put("merchantId", ebuySupplyMerchant.getId());
//					tmpMap.put("merchantName", ebuySupplyMerchant.getName());
//					tmpMap.put("merchantAddress", ebuySupplyMerchant.getAddress());
//					tmpMap.put("merchantTel", ebuySupplyMerchant.getTel());
//				}
//				//订单相关信息
//				tmpMap.put("productQty", ebuyOrderHasTEbuyProduct.getProductQty());//购买数量
//				tmpMap.put("productPrice", PriceUtil.div100(ebuyOrderHasTEbuyProduct.getProductPrice()));//商品实际单价
//				tmpMap.put("deliveryRealFee", PriceUtil.div100(ebuyOrderHasTEbuyProduct.getDeliveryRealFee()));//商品实际配送价格
//				tmpMap.put("deliveryId", ebuyOrderHasTEbuyProduct.getDeliveryId());//配送方式Id
//				productInfoList.add(tmpMap);
//			}
//			resMap.put("productInfo", productInfoList);
//		}
//		//配送信息
//		{
////			EbuyDeliveryMethod ebuyDeliveryMethod=ebuyOrderEntity.getEbuyDeliveryMethod();
////			if(ebuyDeliveryMethod!=null){
//			Map<String,Object> ebuyDeliveryMethod = new HashMap<String, Object>();
//			ebuyDeliveryMethod.put("name", ebuyOrderEntity.getDelivmName());
//			ebuyDeliveryMethod.put("fee", ebuyOrderEntity.getDelivmFee());
//			ebuyDeliveryMethod.put("desc", ebuyOrderEntity.getDelivmDesc());
//			resMap.put("deliveryMethod", ebuyDeliveryMethod);
////			}
//		}
//		//订单信息
//		{
//			resMap.put("orderId", ebuyOrderEntity.getId());
//			resMap.put("orderNo", ebuyOrderEntity.getOrderNo());
//			resMap.put("createTime", ebuyOrderEntity.getBuyTime());//创建时间
//			resMap.put("totalAmount", PriceUtil.div100(ebuyOrderEntity.getAmount()));//总金额
//			resMap.put("deliveryFee",  PriceUtil.div100(ebuyOrderEntity.getTotalDeliveryFee()));//运费
//			resMap.put("payStatus", ebuyOrderEntity.getPayStatus());//支付状态
//			resMap.put("payTime", ebuyOrderEntity.getPayTime());//支付时间
//			resMap.put("orderStatus", ebuyOrderEntity.getStatus());//订单状态
//		}
//		return resMap;
//	}
	/**
	 * 产品信息转Map
	 * @param ebuyProductInfo 产品基本信息
	 * @param defaultDeliveryName 默认配送方式名称
	 * @param ebuyProductHasTEbuyAuthEntityList 产品认证关系
	 * @param defaultDeliveryFee 默认配送费用
	 * @param ebuyProductPicList 产品图片列表
	 * @param supplyMerchantName 供应商名称
	 * @return
	 */
	private Map<String,Object> productInfo2Map(EbuyProductWithCurrProductSpec ebuyProductInfo
			,String defaultDeliveryName
			,List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList
			,Long defaultDeliveryFee
			,List<EbuyProductPic>  ebuyProductPicList
			,String supplyMerchantName,List<EbuyProductSpec> ebuyProductSpecList,List<EbuyProductIntroducePic> ebuyProductIntroducePicList){
		String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
		Map<String,Object> tmpMap = commEntityConvertService.productInfo2Map(ebuyProductInfo);
		String picBase = null;
		List<String> picBigDescs = new LinkedList<String>();//大图片地址列表
		List<String> picMiniDescs = new LinkedList<String>();//小图片地址列表
		picBase = StringUtils.isEmpty(ebuyProductInfo.getPicBase())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+ebuyProductInfo.getPicBase(),ebuyProductInfo);
		//商品图片列表
		if(ebuyProductPicList!=null&&ebuyProductPicList.size()>0){
			for(EbuyProductPic pp:ebuyProductPicList){
				picBigDescs.add(StringUtils.isEmpty(pp.getUrlBig())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+pp.getUrlBig(),pp));
				picMiniDescs.add(StringUtils.isEmpty(pp.getUrlMini())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productPicBasePath+pp.getUrlMini(),pp));
			}
		}else{//为空使用picBase
			picBigDescs.add(picBase);
			picMiniDescs.add(picBase);
		}
		tmpMap.put("picBigDescs", picBigDescs);
		tmpMap.put("picMiniDescs", picMiniDescs);
		
		//配送方式名称
		if(defaultDeliveryName!=null){
			tmpMap.put("defaultDeliveryName",defaultDeliveryName);
		}
		//认证信息
		if(ebuyProductHasTEbuyAuthEntityList!=null){
			List<Map<String,Object>> ebuyAuthList = new ArrayList<Map<String,Object>>();
			EbuyAuthPicConfig ebuyAuthPicConfig=ebuyAuthPicParamParser.parseParamValue();
			for(EbuyProductHasTEbuyAuthEntity_EbuyAuth tmpAuth:ebuyProductHasTEbuyAuthEntityList){
				Map<String,Object> ebuyAuthSignal = ebuyProductHasTEbuyAuthEntity_EbuyAuth2Map(tmpAuth, ebuyAuthPicConfig);
				ebuyAuthList.add(ebuyAuthSignal);
			}
			tmpMap.put("ebuyAuthList", ebuyAuthList);
		}
		//配送费用
		if(defaultDeliveryFee!=null){
			tmpMap.put("defaultDeliveryFee", PriceUtil.div100(defaultDeliveryFee));
		}
		//供应商名称
		if(supplyMerchantName!=null){
			tmpMap.put("supplyMerchantName", supplyMerchantName);
		}
		//产品规格列表
		if(ebuyProductSpecList!=null){
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			for(EbuyProductSpec tmpEbuyProductSpec:ebuyProductSpecList){
				Map<String,Object> tmpEbuyProductSpecMap = ebuyProductSpec2Map(tmpEbuyProductSpec);
				tmpList.add(tmpEbuyProductSpecMap);
			}
			tmpMap.put("ebuyProductSpecList", tmpList);
		}
		if(ebuyProductIntroducePicList!=null&&ebuyProductIntroducePicList.size()>0){
			String productIntroducePicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
			List<String> picMiniList = new ArrayList<String>();
			List<String> picBigList = new ArrayList<String>();
			for(EbuyProductIntroducePic tmpIntroduce:ebuyProductIntroducePicList){
				picMiniList.add(StringUtils.isEmpty(tmpIntroduce.getUrlMini())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productIntroducePicBasePath+tmpIntroduce.getUrlMini(),tmpIntroduce));
				picBigList.add(StringUtils.isEmpty(tmpIntroduce.getUrlBig())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(productIntroducePicBasePath+tmpIntroduce.getUrlBig(),tmpIntroduce));
//				picMiniList.add(tmpIntroduce.getUrlMini());
//				picBigList.add(tmpIntroduce.getUrlBig());
			}
			tmpMap.put("ebuyProductIntroducePicMiniList", picMiniList);
			tmpMap.put("ebuyProductIntroducePicBigList", picBigList);
		}
		return tmpMap;
	
	}
	
	/**
	 * 产品信息转Map
	 * @param ebuyProductInfoSpec 产品基本信息
	 * @param defaultDeliveryName 默认配送方式名称
	 * @param ebuyProductHasTEbuyAuthEntityList 产品认证关系
	 * @param defaultDeliveryFee 默认配送费用
	 * @param ebuyProductPicList 产品图片列表
	 * @param supplyMerchantName 供应商名称
	 * @return
	 */
	private Map<String,Object> productInfo2Map(EbuyProductWithCurrProductSpec ebuyProductInfoSpec
			,String defaultDeliveryName
			,List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList
			,Long defaultDeliveryFee
			,List<EbuyProductPic>  ebuyProductPicList
			,String supplyMerchantName){
		return productInfo2Map(ebuyProductInfoSpec, defaultDeliveryName, ebuyProductHasTEbuyAuthEntityList, defaultDeliveryFee, ebuyProductPicList, supplyMerchantName, null, null);
	}
	
	//认证信息转map
	private Map<String,Object> ebuyProductHasTEbuyAuthEntity_EbuyAuth2Map(EbuyProductHasTEbuyAuthEntity_EbuyAuth tmpAuth,EbuyAuthPicConfig ebuyAuthPicConfig){
		Map<String,Object> ebuyAuthSignal = new HashMap<String, Object>();
		EbuyAuth ebuyAuthTmp=tmpAuth.getEbuyAuth();
		if(ebuyAuthTmp!=null){
			ebuyAuthSignal.put("id", ebuyAuthTmp.getId());
			ebuyAuthSignal.put("name", ebuyAuthTmp.getName());
			ebuyAuthSignal.put("desc", ebuyAuthTmp.getDesc());
			ebuyAuthSignal.put("icon", StringUtils.isEmpty(ebuyAuthTmp.getIco())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(ebuyAuthPicConfig.getAuthIcoBasePath()+ebuyAuthTmp.getIco(),ebuyAuthTmp) );
			ebuyAuthSignal.put("iconBig", StringUtils.isEmpty(ebuyAuthTmp.getIcoBig())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(ebuyAuthPicConfig.getAuthIcoBasePath()+ebuyAuthTmp.getIcoBig(),ebuyAuthTmp) );
		}
		ebuyAuthSignal.put("productAuthPic", StringUtils.isEmpty(tmpAuth.getAuthDetailImg())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(ebuyAuthPicConfig.getPicAuthFilePath()+tmpAuth.getAuthDetailImg(),tmpAuth));
		return ebuyAuthSignal;
	}
	
	//供应商信息转Map
	private Map<String,Object> merchant2Map(EbuySupplyMerchant merchant){
		Map<String,Object> merch = new HashMap<String, Object>();
		merch.put("id", merchant.getId());
		merch.put("name", merchant.getName());
		merch.put("expressType", merchant.getExpressType());
		merch.put("type", merchant.getType());
		merch.put("address", merchant.getAddress());
		merch.put("tel", merchant.getTel());
		if (merchant.getType() != null && merchant.getType() == 2) {
			merch.put("leastDeliveryAmt", 0);//348版本改为XXX起邮的，楼下店不满足也同样可以买
		} else {
			merch.put("leastDeliveryAmt", PriceUtil.div100s(merchant.getLeastDeliveryAmt()));
		}
		merch.put("maxDeliveryAmt", PriceUtil.div100s(merchant.getMaxDeliveryAmt() == null ? 0 : merchant.getMaxDeliveryAmt()));
		return merch;
	}
	//配送方式信息转Map
	private Map<String,Object> deliveryMethod2Map(EbuyDeliveryMethod ebuyDeliveryMethod){
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", ebuyDeliveryMethod.getId());
		resMap.put("name", ebuyDeliveryMethod.getName());
		resMap.put("fee", PriceUtil.div100(ebuyDeliveryMethod.getFee()));
		resMap.put("desc", ebuyDeliveryMethod.getDesc());
		return resMap;
	}
	//配送单信息转Map
	private Map<String,Object> ebuyDeliveryOrder2Map(EbuyDeliveryOrder ebuyDeliveryOrder){
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", ebuyDeliveryOrder.getId());
		resMap.put("deliveryNo", ebuyDeliveryOrder.getDeliveryNo());//物流编号
		resMap.put("expressNo", ebuyDeliveryOrder.getExpressNo());//快递编号
		resMap.put("status", ebuyDeliveryOrder.getStatus());//配送状态
		{//配送状态展示
			if (EbuyDict.EbuyDeliveryOrder_Status.NotStart.compareTo(ebuyDeliveryOrder.getStatus()) == 0
					|| EbuyDict.EbuyDeliveryOrder_Status.DaiFaHuo.compareTo(ebuyDeliveryOrder.getStatus()) == 0) {
				if(ebuyDeliveryOrder.getRefundStatus()!=null){
					resMap.put("faHuoStatus", "WeiFaHuo");
					if(ebuyDeliveryOrder.getRefundStatus().compareTo(1)==0){
						resMap.put("statusShow", "TuiKuanChuLiZhong");
					}else if(ebuyDeliveryOrder.getRefundStatus().compareTo(2)==0){
						resMap.put("statusShow", "TuiKuanChengGong");
					}else if(ebuyDeliveryOrder.getRefundStatus().compareTo(3)==0){
						resMap.put("statusShow", "TuiKuanShiBai");
					}else{
						resMap.put("statusShow", "WeiFaHuo");
					}
				}else{
					resMap.put("statusShow", "WeiFaHuo");
				}
//				resMap.put("statusShow", "WeiFaHuo");
			} else if (EbuyDict.EbuyDeliveryOrder_Status.DaiShouHuo.compareTo(ebuyDeliveryOrder.getStatus()) == 0) {
				if(ebuyDeliveryOrder.getRefundStatus()!=null){
					resMap.put("faHuoStatus", "YiFaHuo");
					if(ebuyDeliveryOrder.getRefundStatus().compareTo(1)==0){
						resMap.put("statusShow", "TuiKuanChuLiZhong");
					}else if(ebuyDeliveryOrder.getRefundStatus().compareTo(2)==0){
						resMap.put("statusShow", "TuiKuanChengGong");
					}else if(ebuyDeliveryOrder.getRefundStatus().compareTo(3)==0){
						resMap.put("statusShow", "TuiKuanShiBai");
					}else{
						resMap.put("statusShow", "YiFaHuo");
					}
				}else{
					resMap.put("statusShow", "YiFaHuo");
				}
//				resMap.put("statusShow", "YiFaHuo");
			} else if (EbuyDict.EbuyDeliveryOrder_Status.QueRenShowHuo.compareTo(ebuyDeliveryOrder.getStatus()) == 0) {
				if(ebuyDeliveryOrder.getRefundStatus()!=null){
					if(ebuyDeliveryOrder.getRefundStatus().compareTo(2)==0){
							resMap.put("statusShow", "TuiKuanChengGong");
					}else{
						resMap.put("statusShow", "QueRenShowHuo");
					}
				}else{
					resMap.put("statusShow", "QueRenShowHuo");
				}
			} else {
				resMap.put("statusShow", null);
			}
			//是否为拼购订单
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tEbuyOrderFId", ebuyDeliveryOrder.gettEbuyOrderFId());
			List<EbuyFightOrder> fightOrderList = ebuyFightOrderBaseService.getEbuyFightOrderByCondition(paramMap);
			if(fightOrderList!=null && fightOrderList.size()>0){
				resMap.put("isNotFight", true);
			}else{
				resMap.put("isNotFight", false);
			}
		}
		return resMap;
	}
	/**
	 * @param tmpEbuyProductSpec 产品价格规格信息转Map
	 * @return
	 */
	private Map<String, Object> ebuyProductSpec2Map(EbuyProductSpec tmpEbuyProductSpec) {
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", tmpEbuyProductSpec.getId());
		resMap.put("phoneAmount", tmpEbuyProductSpec.getPhoneAmount());//话费金额 例如 50 元
		resMap.put("phoneAgentType", tmpEbuyProductSpec.getPhoneAgentType());//话费代理商 例如 电信
		resMap.put("price", PriceUtil.div100(tmpEbuyProductSpec.getPrice()));
		resMap.put("priceDiscount", PriceUtil.div100(tmpEbuyProductSpec.getPriceDiscount()));
		resMap.put("pricePoint", tmpEbuyProductSpec.getPricePoint());
		resMap.put("priceDiscountPoint", tmpEbuyProductSpec.getPriceDiscountPoint());
		resMap.put("ebuyProductFId", tmpEbuyProductSpec.gettEbuyProductFId());
		resMap.put("pointType", tmpEbuyProductSpec.getPointType());
		
		resMap.put("size", tmpEbuyProductSpec.getSize());
		resMap.put("colour", tmpEbuyProductSpec.getColour());
		return resMap;
	}
	
	/**
	 * @param ebuyExpressCompany 快递公司信息转Map
	 * @return
	 */
	private Map<String,Object> ebuyExpressCompany2Map(EbuyExpressCompany ebuyExpressCompany) {
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("id", ebuyExpressCompany.getId());
		resMap.put("name",ebuyExpressCompany.getName());
		resMap.put("phone",ebuyExpressCompany.getPhone());
		resMap.put("desc",ebuyExpressCompany.getDesc());
		return resMap;
	}
//	{//供应商
//	Map<String,Object> merch = new HashMap<String, Object>();
//	merch.put("id", 200);
//	merch.put("name", "平湖农批市场直供");
//	merch.put("address", "深圳市龙岗区平湖区");
//	merch.put("tel", null);
//	{
//		List<Map<String,Object>> resProductList = new ArrayList<Map<String,Object>>();
//		{
//			Map<String,Object> recMap = new HashMap<String, Object>();
//			recMap.put("productQty", 3);
//			recMap.put("selNum", 0);
//			recMap.put("desc", "绿色食品");
//			recMap.put("picBaseMini", null);
//			recMap.put("defaultDeliveryName", "满59免运费");
//			recMap.put("priceUnit", "盒");
//			recMap.put("picBase", "http://192.168.1.31:8080/productPic/hjx_310.jpg");
//			recMap.put("id", 310);
//			recMap.put("price", 39.98);
//			recMap.put("name", "红提");
//			recMap.put("specification", "2斤/盒");
//			recMap.put("leftCount", 99);
//			recMap.put("priceDiscount",36.7 );
//			{//ebuyAuthList
//				List<Map<String,Object>> authList = new ArrayList<Map<String,Object>>();
//				{
//					Map<String,Object> authMap = new HashMap<String, Object>();
//					authMap.put("id",1 );
//					authMap.put("icon", "http://192.168.1.31:8080/ebuyAuthPic/a.jpg");
//					authMap.put("desc", "FQT认证");
//					authMap.put("name", "FQT");
//					authMap.put("productAuthPic", "http://192.168.1.31:8080/ebuyAuthPic/product/wuzihongti.jpg");
//					authList.add(authMap);
//				}
//				recMap.put("ebuyAuthList", authList);
//			}
//			resProductList.add(recMap);
//		}
//		{
//			Map<String,Object> recMap = new HashMap<String, Object>();
//			recMap.put("productQty", 2);
//			recMap.put("selNum", 0);
//			recMap.put("desc", "绿色食品");
//			recMap.put("picBaseMini", null);
//			recMap.put("defaultDeliveryName", "满59免运费");
//			recMap.put("priceUnit", "盒");
//			recMap.put("picBase", "http://192.168.1.31:8080/productPic/hjx_312.jpg");
//			recMap.put("id", 312);
//			recMap.put("price", 39.98);
//			recMap.put("name", "红提");
//			recMap.put("specification", "2斤/盒");
//			recMap.put("leftCount", 99);
//			recMap.put("priceDiscount",36.7 );
//			{//ebuyAuthList
//				List<Map<String,Object>> authList = new ArrayList<Map<String,Object>>();
//				{
//					Map<String,Object> authMap = new HashMap<String, Object>();
//					authMap.put("id",1 );
//					authMap.put("icon", "http://192.168.1.31:8080/ebuyAuthPic/a.jpg");
//					authMap.put("desc", "FQT认证");
//					authMap.put("name", "FQT");
//					authMap.put("productAuthPic", "http://192.168.1.31:8080/ebuyAuthPic/product/wuzihongti.jpg");
//					authList.add(authMap);
//				}
//				recMap.put("ebuyAuthList", authList);
//			}
//			resProductList.add(recMap);
//		}
//		merch.put("productList", resProductList);
//	}
//	resMerchList.add(merch);
//}
	
	//上传图片
	private String uploadrefundPic(BigInteger userId, List<RequestFileEntity> picList) {
		StringBuilder picUrl = new StringBuilder();

		if (picList != null && picList.size() > 0) {
			String dredgePicBase = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);
//			String dredgePicBase = "tmpfile";
			for (int i = 0; i < picList.size(); i++) {//上传图片
				RequestFileEntity requestFile = picList.get(i);
				//生成文件名 userId+time+index+3位随机数
				String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append("_").append(i + 1).append("_")
						.append(RandomUtils.createRandom(true, 3)).append(".").append(requestFile.getFileType()).toString();
				picUrl.append(resFileName).append(";");
				String serverPath = dredgePicBase + resFileName;
				try {
					fileServerService.uploadFile(serverPath, requestFile.getFileContent());
					generateMiniImage(new File(fileServerService.getFileServierUploadBasePath() + serverPath));
				} catch (IOException e) {
					logger.info("uploadRepairPic upload Repair file cause exception:" + e.getMessage(), e);
					throw new BusinessRuntimeException("RepairService.uploadRepairPic.uploadFile.error", new Object[] { requestFile.getFileName() });
				}
			}
		}

		return picUrl.toString();
	}
	
	//生成小图
	private void generateMiniImage(File bigImageFile) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Dredge.getGuigeList();

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		//String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		//   ../dredgePic/5038472015-08-12_10_00_16_1_755.jpg 
		String goalDirectoryFilePath = bigImageFile.getAbsolutePath().substring(0, bigImageFile.getAbsolutePath().lastIndexOf("."));

		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if (!goalFileDir.exists()) {
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), 1f, smallIcon);
			} catch (Exception e) {
				logger.info(smallIcon + "create small image failure. ");
			}
		}
	}
	
	public void setEbuyFilmTicketService(IEbuyFilmTicketService ebuyFilmTicketService) {
		this.ebuyFilmTicketService = ebuyFilmTicketService;
	}

	public void setEbuyFlowRechargeService(
			IEbuyFlowRechargeService ebuyFlowRechargeService) {
		this.ebuyFlowRechargeService = ebuyFlowRechargeService;
	}

}
