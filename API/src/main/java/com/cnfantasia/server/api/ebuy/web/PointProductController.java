/**   
* Filename:    PointProductController.java   
* @version:    1.0  
* Create at:   2014年12月30日 上午8:36:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonPointService;
import com.cnfantasia.server.api.commonBusiness.util.RelativeDateFormat;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyOrderHasTEbuyProductEntity_Product;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductWithCurrProductSpec;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;

/**
 * Filename:    PointProductController.java
 * @version:    1.0.0
 * Create at:   2014年12月30日 上午8:36:57
 * Description: 积分商品Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月30日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/pointProduct")
public class PointProductController extends EbuyController{
	private ICommonPointService commonPointService;
	public void setCommonPointService(ICommonPointService commonPointService) {
		this.commonPointService = commonPointService;
	}

	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	/**
	 * 解析请求的商品类别，电商商品还是积分商品
	 * @param request
	 * @return
	 */
	@Override
	public Integer parsePointType(HttpServletRequest request){
		Integer pointType = EbuyDict.PointType.POINT_PRODUCT;//设定默认为积分商品
		return pointType;
	}
	
	/**
	 * 查询积分商品类别列表
	 */
	@RequestMapping("/qryProductTypes.json")
	@ResponseBody
	public JsonResponse qryProductTypes(HttpServletRequest request){
		return super.qryProductTypes(request);
	}
	
	/**
	 * 根据类别查询积分商品列表
	 */
	@RequestMapping("/qryProductList.json")
	@ResponseBody
	public JsonResponse qryProductList(HttpServletRequest request){
		return super.qryProductList(request);
	}
	
	/**
	 * 查询推荐的积分商品列表
	 */
	@RequestMapping("/getRecommend.json")
	@ResponseBody
	public JsonResponse getRecommend(HttpServletRequest request){
		return super.getRecommend(request);
	}
	
	/**
	 * 查询积分商品详情
	 */
	@RequestMapping("/qryProductDetail.json")
	@ResponseBody
	public JsonResponse qryProductDetail(HttpServletRequest request){
		JsonResponse jsonResponse = super.qryProductDetail(request);
		Long currPoint = null;
		BigInteger userId = UserContext.getOperIdBigInteger();
		if(userId!=null){
			PointData tmpPointData = commonPointService.getPointDataByUserId(userId);
			currPoint = tmpPointData==null?0:tmpPointData.getPointValue();
		}
		jsonResponse.putData("ext_userCurrPoint", currPoint);
		return jsonResponse;
	}
	
//	/**
//	 * 提交订单并且使用积分支付
//	 */
//	@RequestMapping("/submitOrder.json")
//	@ResponseBody
//	public JsonResponse submitOrder(HttpServletRequest request){
//		// 商品规格的处理
//		return super.submitOrder(request);
//	}
	
	/**
	 * 查询购买的积分商品列表(订单列表)
	 */
	@RequestMapping("/qryPurchasedGiftList.json")
	@ResponseBody
	public JsonResponse qryPurchasedGiftList(HttpServletRequest request){//TODO 订单状态是否需要筛选
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		PageModel pageModel = ControllerUtils.getPageModel(request);
		List<Integer> yesStatus = null;
//		{//需要的订单状态
//			String orderStatusStr = request.getParameter("orderStatus");
//			if(!StringUtils.isEmpty(orderStatusStr)){
//				yesStatus = JSON.parseArray(orderStatusStr, Integer.class);
//			}
//		}
		{//仅获取支付成功的
			yesStatus = new ArrayList<Integer>();
			yesStatus.add(EbuyDict.EbuyOrder_Status.DaiFaHuo);
			yesStatus.add(EbuyDict.EbuyOrder_Status.DaiShouHuo);
			yesStatus.add(EbuyDict.EbuyOrder_Status.DaiPingJia);
			yesStatus.add(EbuyDict.EbuyOrder_Status.YiWanCheng);
		}
		//默认不需要的订单状态
		List<Integer> noStatus=new ArrayList<Integer>();
		noStatus.add(EbuyDict.EbuyOrder_Status.YiShanChu);//不包含已删除的订单
		//用户
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<EbuyOrderEntity> ebuyOrderList = ebuyService.getOrderList(userId, yesStatus, noStatus, pageModel,pointType,parseWlAppType(request));
		String nowTime = dualDao.getNowTime();
		Long nowTimeLong = DateUtil.timeToLong(nowTime);
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		//3.结果处理
		for(EbuyOrderEntity ebuyOrderEntity:ebuyOrderList){
			Map<String,Object> tmpMap = ebuyOrderEntity2PointCostRecordMap(ebuyOrderEntity,nowTimeLong);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	/**
	 * 查询兑换的商品详情
	 */
	@RequestMapping("/qryPurchasedGiftDetail.json")
	@ResponseBody
	public JsonResponse qryPurchasedGiftDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("id"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity=ebuyService.getEbuyOrderEntityDetail(userId, orderId,pointType,parseWlAppType(request));
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2PointCostRecordMap(ebuyOrderEntity,null);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private Map<String,Object> ebuyOrderEntity2PointCostRecordMap (EbuyOrderEntity ebuyOrderEntity,Long nowTimeLong){
		if(nowTimeLong==null){
			String nowTime = dualDao.getNowTime();
			nowTimeLong = DateUtil.timeToLong(nowTime);
		}
		//TODO 默认取第一个
		EbuyOrderHasTEbuyProductEntity_Product tmpEbuyOrderHasTEbuyProductEntity_Product = ebuyOrderEntity.getEbuyOrderHasTEbuyProductEntity_ProductList().get(0);
		//商品及规格信息
		EbuyProductWithCurrProductSpec tmpProductInfo= new EbuyProductWithCurrProductSpec(tmpEbuyOrderHasTEbuyProductEntity_Product.getEbuyProductEntity(), tmpEbuyOrderHasTEbuyProductEntity_Product.getEbuyProductSpec());;
		//兑换码列表
		List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList = tmpEbuyOrderHasTEbuyProductEntity_Product.getEbuyOrderProductHasCodeList();
		
		String desc = tmpProductInfo.getName();//
		String time = ebuyOrderEntity.getPayTime();
		BigInteger recordId = ebuyOrderEntity.getId();
		BigInteger roomId = ebuyOrderEntity.getCurrRoomId();
		BigInteger userId = ebuyOrderEntity.getBuyerId();
		Long pointValue = ebuyOrderEntity.getAmountPoint();
		Integer specialProductType = tmpProductInfo.getSpecialProductType();
		
		Integer orderStatus = ebuyOrderEntity.getStatus();//订单状态
		if(orderStatus!=null&&orderStatus.compareTo(EbuyDict.EbuyOrder_Status.DaiFaHuo)==0){
			//手机话费或者虚拟家券 若是待发货则改为已发货
			if(specialProductType!=null&&
					(specialProductType.compareTo(EbuyDict.Product_SpecialType.PhoneFee)==0||
					specialProductType.compareTo(EbuyDict.Product_SpecialType.MoneyTicket)==0)){
				orderStatus = EbuyDict.EbuyOrder_Status.DaiShouHuo;
			}
		}
		String codeValue = null;//兑换码
		String endTime = null;//兑换码有效期
		if(ebuyOrderEntity.getStatus().compareTo(EbuyDict.EbuyOrder_Status.DaiFaHuo)>=0){//只有支付成功，才返回兑换码信息
			if(ebuyOrderProductHasCodeList!=null&&ebuyOrderProductHasCodeList.size()>0){//TODO 取第一个
				codeValue = ebuyOrderProductHasCodeList.get(0).getCodeValue();
				endTime = ebuyOrderProductHasCodeList.get(0).getOutTime();
			}
		}
		
		String picUrl = null;//产品小图
		String picUrlDetail = null;//详情图片地址
		{
			String productPicBasePath = sysParamManager.getSysParaValue(SysParamKey.PRODUCT_PIC_BASE_PATH);//产品图片信息根路径
			String picBase = null;
			String picBaseMini = null;
			if(StringUtils.isEmpty(tmpProductInfo.getPicBase())){
				picBase = null;
			}else{
				picBase = fileServerService.getAccessUrl(productPicBasePath+tmpProductInfo.getPicBase(),tmpProductInfo.getSys0UpdTime());
			}
			if(StringUtils.isEmpty(tmpProductInfo.getPicBaseMini())){
				picBaseMini = picBase;//为空使用picBase
			}else{
				picBaseMini = fileServerService.getAccessUrl(productPicBasePath+tmpProductInfo.getPicBaseMini(),tmpProductInfo.getSys0UpdTime());
			}
			picUrl = picBaseMini;
			picUrlDetail = picBase;
		}
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("desc", desc);
		resMap.put("time", time);
		resMap.put("timeLong", DateUtil.timeToLong(time));
		resMap.put("timeInfo",RelativeDateFormat.format(nowTimeLong-DateUtil.timeToLong(time),nowTimeLong));
		resMap.put("id", recordId);
		resMap.put("roomId", roomId);
		resMap.put("userId", userId);
		resMap.put("pointValue", pointValue);
		resMap.put("type", specialProductType);
		resMap.put("picUrl", picUrl);//产品小图
		resMap.put("picUrlDetail", picUrlDetail);//详情图片地址
		resMap.put("orderStatus", orderStatus);//订单状态
		resMap.put("useStatus", 1);//TODO 使用状态
		resMap.put("codeValue", codeValue);//兑换码
		resMap.put("endTimeLong", DateUtil.timeToLong(endTime));//兑换码有效期
		resMap.put("endTime", endTime);//兑换码有效期
		return resMap;
	}
	
	/**
	 * 确认兑换话费
	 */
	@RequestMapping("/confirmPhoneCharge.json")
	@ResponseBody
	public JsonResponse confirmPhoneCharge(HttpServletRequest request){ 
		JsonResponse jsonResponse = new JsonResponse();//注意校验商品规格是否为空
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//商品Id
		BigInteger productId = null;
		{
			String productIdStr = request.getParameter("productId");
			if(!StringUtils.isEmpty(productIdStr)){
				productId = new BigInteger(productIdStr);
			}
			if(StringUtils.isEmpty(productId)){
				throw new ValidateRuntimeException("PointProductController.confirmPhoneCharge.productId.empty");
			}
		}
		//商品规格Id
		BigInteger productSpecId = null;
		{
			String productSpecIdStr = request.getParameter("productSpecId");
			if(!StringUtils.isEmpty(productSpecIdStr)){
				productSpecId = new BigInteger(productSpecIdStr);
			}
			if(StringUtils.isEmpty(productSpecId)){
				throw new ValidateRuntimeException("PointProductController.confirmPhoneCharge.productSpecId.empty");
			}
		}
		//待充值的手机号
		String phoneNum = null;
		{
			phoneNum = request.getParameter("phoneNum");
			if(StringUtils.isEmpty(phoneNum)){
				throw new ValidateRuntimeException("PointProductController.confirmPhoneCharge.phoneNum.empty");
			}
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity = ebuyService.confirmPhoneChargeOrder(userId, productId, productSpecId, phoneNum, pointType,parseWlAppType(request),parseJfq_SubType(request),parseSubChannelId(request));
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 确认兑换现金券
	 */
	@RequestMapping("/confirmCashCoupon.json")
	@ResponseBody
	public JsonResponse confirmCashCoupon(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		String productIdStr = request.getParameter("productId");
		BigInteger productId = null;
		if(!StringUtils.isEmpty(productIdStr)){
			productId = new BigInteger(productIdStr);
		}
		if(StringUtils.isEmpty(productId)){
			throw new ValidateRuntimeException("PointProductController.confirmCashCoupon.productId.empty");
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity = ebuyService.confirmCashCoupon(userId, productId, null, pointType,parseWlAppType(request),parseJfq_SubType(request),parseSubChannelId(request));
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 确认兑换普通商品
	 */
	@RequestMapping("/confirmCommProduct.json")
	@ResponseBody
	public JsonResponse confirmCommProduct(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		//商品Id
		BigInteger productId = null;
		{
			String productIdStr = request.getParameter("productId");
			if(!StringUtils.isEmpty(productIdStr)){
				productId = new BigInteger(productIdStr);
			}
			if(StringUtils.isEmpty(productId)){
				throw new ValidateRuntimeException("PointProductController.confirmCommProduct.productId.empty");
			}
		}
		//配送地址Id
		BigInteger deliveryAddressId = null;
		{
			String deliveryAddressIdStr = request.getParameter("deliveryAddressId");
			if(!StringUtils.isEmpty(deliveryAddressIdStr)){
				deliveryAddressId = new BigInteger(deliveryAddressIdStr);
			}else{
				throw new ValidateRuntimeException("PointProductController.confirmCommProduct.deliveryAddressId.empty");
			}
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity = ebuyService.confirmCommProduct(userId, deliveryAddressId, productId, null, pointType,parseWlAppType(request),parseJfq_SubType(request),parseSubChannelId(request));
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 查询订单详情(普通商品)
	 */
	@RequestMapping("/qryOrderDetailCommProduct.json")
	@ResponseBody
	public JsonResponse qryOrderDetailCommProduct(HttpServletRequest request){//TODO 包含商品规格 是订单详情的商品展示
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		Integer pointType = parsePointType(request);//商品归类类别：积分商品或者电商商品
		BigInteger orderId = new BigInteger(request.getParameter("orderId"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		EbuyOrderEntity ebuyOrderEntity=ebuyService.getEbuyOrderEntityDetail(userId, orderId,pointType,parseWlAppType(request));
		//3.结果处理
		Map<String,Object> resMap = ebuyOrderEntity2Map(ebuyOrderEntity);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
}
