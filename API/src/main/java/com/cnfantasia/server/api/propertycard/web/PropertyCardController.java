package com.cnfantasia.server.api.propertycard.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.propertycard.entity.PropertyCardEntity;
import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.propertycard.entity.TransLog;
import com.cnfantasia.server.api.propertycard.service.PropertyCardService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;
import com.cnfantasia.server.domainbase.propertyAccountInfo.service.IPropertyAccountInfoBaseService;
import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;
import com.cnfantasia.server.domainbase.propertyCard.service.IPropertyCardBaseService;
import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;
import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.service.IPropertyCardDiscountTermBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物业代扣卡
 * @author wenfq 2016-04-27
 */
@RequestMapping("/propertyCard")
@Controller
public class PropertyCardController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private ICommonRoomService commonRoomService;

	@Resource
	private IPropertyAccountInfoBaseService propertyAccountInfoBaseService;

	@Resource
	private IRoomService roomService;

	@Resource
	private PropertyCardService propertyCardService;

	@Resource
	private IPropertyCardBaseService propertyCardBaseService;

	@Resource
	private IPropertyCardDiscountTermBaseService propertyCardDiscountTermBaseService;

	/**
	 * 当前用户所在小区是否开通物业缴费服务, 以及当前用户的物业缴费卡余额
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isOpenService.json")
	public JsonResponse isOpenService() {

		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		int isOpenService = 0;
		long balanceAmt = 0;

		//签约且开启缴费
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		if (groupBuilding != null
				&& groupBuilding.getSignStatus() != null && groupBuilding.getSignStatus().compareTo(1) == 0
				&& groupBuilding.getPropfeeCanpay() != null && groupBuilding.getPropfeeCanpay().compareTo(1) == 0){
			isOpenService = 1;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tUserFId", userId);
		//查询当前账户情况
		List<PropertyAccountInfo> accounts = propertyAccountInfoBaseService.getPropertyAccountInfoByCondition(param);
		if (accounts != null && accounts.size() > 0) {
			balanceAmt = accounts.get(0).getBalanceAmt();
		}

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("isOpenService", isOpenService);
		jsonResponse.putData("balanceAmt", PriceUtil.div100(balanceAmt));

		// 3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询我的账户余额，以及名下各房间的划扣配置
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/viewMyAccountInfo.json")
	public JsonResponse viewMyAccountInfo() {
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		int isOpenService = 0;
		long balanceAmt = 0;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tUserFId", userId);
		//查询当前账户情况
		List<PropertyAccountInfo> accounts = propertyAccountInfoBaseService.getPropertyAccountInfoByCondition(param);
		if (accounts != null && accounts.size() > 0) {
			balanceAmt = accounts.get(0).getBalanceAmt();
		}

		List<RealRoomConfig> rooms = roomService.getPropfeeCanPayRoomByUserId(userId);
		//有开启的则总开关显示开
		for (RealRoomConfig room : rooms) {
			if (room.getIsOpenService() > 0) {
				isOpenService = 1;
				break;
			}
		}

		BigInteger realRoomFId = roomService.getDefaultRoomInfo(userId).getRealRoomEntity().getId();
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("realRoomId", realRoomFId);
		jsonResponse.putData("isOpenService", isOpenService);
		jsonResponse.putData("balanceAmt", PriceUtil.div100(balanceAmt));
		jsonResponse.putData("list", rooms);
		return jsonResponse;
	}
	
	
	/**
	 * 保存划扣配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveDeductionConfig.json")
	@ResponseBody
	public JsonResponse saveDeductionConfig(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
        UserContext.getOperIdMustExistBigInteger();
        //1.搜集参数
        List<RealRoomConfig> configs = JSON.parseArray(request.getParameter("configList"), RealRoomConfig.class);
        propertyCardService.saveDeductionConfig(configs);
		//2.交互
		//dredgeService.addDredgeBill(userId, dredgeAddress, dredgeTypeId, dredgeContent, expectDate, tel, referrerMobile, roomId, blockId, picList);

		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 列出所有可售代扣卡，优惠方案，短信通知手机号
	 * @return
	 */
	@RequestMapping("/qryPayCardList.json")
	@ResponseBody
	public JsonResponse qryPayCardList() {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Map<String, Object> param = new HashMap<String, Object>();

		//正常出售卡
		param.put("sellState", 1);
		param.put("discountType", propertyCardService.getDiscountLevel(userId));
		List<PropertyCard> propertyCardList = propertyCardBaseService.getPropertyCardByCondition(param);
		List<PropertyCardEntity> entities = new ArrayList<PropertyCardEntity>();
		PropertyCardEntity entity = null;
		//转为propertycardEntity, 去除不必要字段
		for (PropertyCard propertyCard : propertyCardList) {
			entity = new PropertyCardEntity(propertyCard);
			entities.add(entity);
		}
		jsonResponse.putData("propertyCardList", entities);

		param.clear();
		param.put("tUserFId", userId);
		//查询当前账户情况，不存在则首充，查首充优惠。存在则有上次通知手机号传过去
		List<PropertyAccountInfo> accounts = propertyAccountInfoBaseService.getPropertyAccountInfoByCondition(param);

		if (accounts != null && accounts.size() > 0 && accounts.get(0).getNotifyPhone() != null) {
			jsonResponse.putData("notifyPhone", accounts.get(0).getNotifyPhone());
			jsonResponse.putData("discountTerm", null);
		} else {
			param.clear();
			List<PropertyCardDiscountTerm> discounts = propertyCardDiscountTermBaseService.getPropertyCardDiscountTermByCondition(param);
			if (discounts != null && discounts.size() > 0) {
				Map<String, Object> discount = new HashMap<String, Object>();
				PropertyCardDiscountTerm propertyCardDiscountTerm = discounts.get(0);
				discount.put("goalAmt", propertyCardDiscountTerm.getGoalAmt());
				discount.put("sendAmt", propertyCardDiscountTerm.getSendAmt());
				discount.put("termDescription", propertyCardDiscountTerm.getTermDescription());
				jsonResponse.putData("discountTerm", discount);
			} else {
				jsonResponse.putData("discountTerm", null);
			}
			jsonResponse.putData("notifyPhone", null);
		}

		return jsonResponse;
	}

	/**
	 * 列出交易明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryTransLoglist.json")
	@ResponseBody
	public JsonResponse qryTransLoglist(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//分页信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		PageModel pageModel = ControllerUtils.getPageModel(request);
		//按分页查出交易流水
		List<TransLog> transLogList = propertyCardService.getTransLogListByUserId(userId, pageModel);
		jsonResponse.putData("list", transLogList);

		//3.结果处理
		return ControllerUtils.addPageInfo(jsonResponse, transLogList, pageModel.isLast, pageModel.count);
	}
	
	/**
	 * 确认充值
	 */
	@RequestMapping("/confirmPay.json")
	@ResponseBody
	@RepeatReqValidate
	public JsonResponse confirmPay(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger propertyCardId = ParamUtils.getBigInteger(request, "id",null);
		if(propertyCardId == null){
			throw new ValidateRuntimeException("id can't be null");
		}
		String notifyPhone = ParamUtils.getString(request, "notifyPhone", null);
		if(notifyPhone == null){
			throw new ValidateRuntimeException("notifyPhone can't be null");
		}
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		//2.交互
		Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
		BigInteger orderId = propertyCardService.confirmPay(propertyCardId, notifyPhone, subChannelId);

		//3.结果处理
		jsonResponse.putData("orderId", orderId); //订单ID
		return jsonResponse;
	}
	
	/**
	 * 查看订单详情
	 */
	@RequestMapping("/viewEbuyOrderDetail.json")
	@ResponseBody
	public JsonResponse viewEbuyOrderDetail(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger orderId = ParamUtils.getBigInteger(request, "orderId",null);
		if(orderId == null){
			throw new ValidateRuntimeException("orderId can't be null");
		}
		
		//BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		//2.交互

		//3.结果处理
//		jsonResponse.putData("payAmt", 149.80); 
//		jsonResponse.putData("productInfo", "150元-物业代扣卡充值"); 
//		jsonResponse.putData("payStatus", 2); //支付状态=={"1":"未支付","2":"支付完成","3":"支付失败","4":"退款"}',
//		jsonResponse.putData("payMethod", 1);//支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付","9":"双乾支付"}',
//		jsonResponse.putData("tranNo", "20160429252362522");//交易单号 
//		jsonResponse.putData("tranTime", "2015-004-29");//交易时间 
		
		TransLog transLog = propertyCardService.getTransLogByOrderId(orderId);
		jsonResponse.putDataAll(MapConverter.toMap(transLog));
		
		return jsonResponse;
	}
	
	/**
	 * 物业代扣卡说明
	 */
	@RequestMapping("/description.html")
	public ModelAndView explain(HttpServletRequest request) {
		return new ModelAndView("/propertyCard/description");
	}
	
	/**
	 * 物业代扣卡协议
	 */
	@RequestMapping("/agreement.html")
	public ModelAndView agreement(HttpServletRequest request) {
		return new ModelAndView("/propertyCard/agreement");
	}
}
