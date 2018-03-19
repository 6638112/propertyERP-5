/**   
* Filename:    PlotpropertyController.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午7:48:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.access.entity.CarBill;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.cashSqpayBt.dao.ICashSqpayBtDao;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.company.service.ICompanyService;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.payment.util.PayConfigUtil;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyCombEntity;
import com.cnfantasia.server.api.plotproperty.entity.PlotpropertyOrderEntity;
import com.cnfantasia.server.api.plotproperty.entity.PropertyAlterBillInfo;
import com.cnfantasia.server.api.plotproperty.entity.PropertyBillEntity;
import com.cnfantasia.server.api.plotproperty.entity.PropertyBillInfo;
import com.cnfantasia.server.api.plotproperty.entity.PropertyDetail;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.service.IRedenvelopeService;
import com.cnfantasia.server.api.room.entity.AddressBlockEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.business.pub.CommControllerUtils;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.commbusi.plotproperty.entity.BusinessMonthYearFactory;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.cashSqpayBt.entity.CashSqpayBt;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyManagement.service.IPropertyManagementBaseService;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.roomValidate.service.IRoomValidateBaseService;

/**
 * Filename:    PlotpropertyController.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午7:48:00
 * Description:小区物业Controller
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
@RequestMapping("/plotproperty")
@Controller
public class PlotpropertyController extends BaseController{
	Log logger = LogFactory.getLog(this.getClass());

	private IPlotpropertyService plotpropertyService;
	public void setPlotpropertyService(IPlotpropertyService plotpropertyService) {
		this.plotpropertyService = plotpropertyService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
//	private IDualDao dualDao;
//	public void setDualDao(IDualDao dualDao) {
//		this.dualDao = dualDao;
//	}
	
	@Resource
	private IRedenvelopeService redenvelopeService;
	@Resource
	private IPayConfigService payConfigService;
	@Resource
	private ICompanyService companyService;
	@Resource
	private IRoomValidateBaseService roomValidateBaseService;
	@Resource
	private IGroupBuildingBaseService groupBuildingBaseService;
	@Resource
	private IPropertyManagementBaseService propertyManagementBaseService;
	@Resource
	private IBuildingBaseService buildingBaseService;
	@Resource
	private IAccessService accessService;
	@Resource
	private ICashSqpayBtDao cashSqpayBtDao;
	/**
	 * 查询物业服务列表
	 */
	@RequestMapping("/qryPlotpropertyServiceList.json")
	@ResponseBody
	public JsonResponse qryPlotpropertyServiceList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		List<PropertyService> propertyServiceList = plotpropertyService.getPlotpropertyServiceList(userId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(propertyServiceList!=null&&propertyServiceList.size()>0){
			for(PropertyService tmpPropertyService:propertyServiceList){
				Map<String,Object> tmpMap = propertyService2Map(tmpPropertyService);
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
	private Map<String,Object> propertyService2Map(PropertyService propertyService){
		String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.PropertyService_Icon_BasePath);
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("id", propertyService.getId());
		tmpMap.put("name", propertyService.getName());
		tmpMap.put("iconUrl", StringUtils.isEmpty(propertyService.getIconUrl())?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(iconBasePath+propertyService.getIconUrl(),propertyService));
		tmpMap.put("desc", propertyService.getDesc());
		return tmpMap;
	}
	
	/**
	 * 查询当前小区的物业信息
	 */
	@RequestMapping("/qryCurrGroupbuildingInfo.json")
	@ResponseBody
	public JsonResponse qryCurrGroupbuildingInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		//2.交互
		if(userId!=null){
			RoomEntityWithValidate roomEntity = commonRoomService.getDefaultRoomInfoExceptionWhenEmpty(userId);
			GroupBuildingEntity groupBuildingEntity = plotpropertyService.getGroupBuildingEntityById(roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId(),userId);
			AddressBlockEntity addressBlockEntity = groupBuildingEntity==null?null:groupBuildingEntity.getAddressBlockEntity();
			PropertyManagement propertyManagement = groupBuildingEntity==null?null:groupBuildingEntity.getPropertyManagementEntity();
			Map<String,Object> groupBuildingResMap = commEntityConvertService.groupBuilding2Map(groupBuildingEntity,addressBlockEntity, propertyManagement);
			jsonResponse.setDataValue(groupBuildingResMap);
			{
				Map<String,Object> defaultRoomInfoMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(),roomEntity.getId());
				jsonResponse.putData("defaultRoomInfo",defaultRoomInfoMap);
			}
		}else{
			Map<String,Object> groupBuildingResMap = commEntityConvertService.groupBuilding2Map(null,null, null);
			jsonResponse.setDataValue(groupBuildingResMap);
		}
		//3.结果处理
		return jsonResponse;
	}
	
//	/**
//	 * 查询当前时间对应的物业月份 2015-12-15 13:49:01废弃
//	 */
//	@RequestMapping("/qryCurrPropertyMonth.json")
//	@ResponseBody
//	public JsonResponse qryCurrPropertyMonth(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
////		//2.交互
////		String nowTime = dualDao.getNowTime();
////		BusinessMonthYearWithGB monthYearWithGB = new BusinessMonthYearWithGB(nowTime, commonRoomService.getGroupBuildingByUserId(userId), MonthOrTime.time);
////		Date propertyDate = monthYearWithGB.getTimeDate(DataType.property);
////		Date discountDate = monthYearWithGB.getTimeDate(DataType.discount);
////		//3.结果处理
////		jsonResponse.putData("propertyMonth", propertyDate.getTime());
////		jsonResponse.putData("timeDistanceLong", (discountDate!=null&&propertyDate!=null)?(discountDate.getTime()-propertyDate.getTime()):0L);
////		return jsonResponse;
//		
//		//2.交互
//		BigInteger billTypeId =fetchBillTypeIdAndDefault(request, userId);//默认为物业费类别
//		Date timeDate = dualDao.getNowDate();
//		IBusinessMonthYear bmy = plotpropertyCfgService.getBusinessMonthYearByPayTime(userId, timeDate,billTypeId);
//		//3.结果处理
//		ISectionDate billMonth = bmy.getBillMonth();
//		if(billMonth instanceof ISectionDateSignal){
//			ISectionDateSignal tmp = (ISectionDateSignal)billMonth;
//			jsonResponse.putData("propertyMonth",  tmp.getYearMonthLong());
//		}else if(billMonth instanceof ISectionDateMulti){
//			ISectionDateMulti tmp = (ISectionDateMulti)billMonth;
//			//TODO ..
//		}
////		jsonResponse.putData("propertyMonth",  bmy.getPropertyYearMonthLong());
//		jsonResponse.putData("timeDistanceLong", bmy.getDistanceDisc2PropMonthLong());
//		return jsonResponse;
//	}
	
	
	/**
	 * 查询最新一条物业账单信息02
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLastPayBill02.json")
	@ResponseBody
	public JsonResponse qryLastPayBill02(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean useDefaultIfNull = true;//TODO ..
		PayBillType payBillType = fetchBillTypeIdAndDefault(request, userId,true,useDefaultIfNull);//默认为物业费类别
		BigInteger billTypeId = payBillType.getId();
		//查询最新的物业账单信息
		PlotpropertyCombEntity plotpropertyCombEntity=plotpropertyService.getLastPayBill(userId,billTypeId);
		//3.结果处理
		Map<String,Object> resMap = convertCommonPayBill2Map02(userId,plotpropertyCombEntity);
		try {//附加粮票信息
			Double toPayAmount = PriceUtil.div100(plotpropertyCombEntity.getPayBillEntity().getAmount()).doubleValue();
			appendHbAmount(resMap, toPayAmount, userId,plotpropertyCombEntity.getPayBillEntity().getIsPropFee());
		} catch (Exception e) {}
		appendJfqTel(resMap);//附加解放信息
		jsonResponse.setDataValue(resMap);
		logger.debug("qryLastPayBill02:" + JSON.toJSONString(jsonResponse));
		return jsonResponse;
	}
	
	/**
	 * 物业账单列表-单个月02
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPayBillByMonth02.json")
	@ResponseBody
	public JsonResponse qryPayBillByMonth02(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String yearMonth = CommControllerUtils.getYearMonth(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Date billDate = null;
		try {
			billDate = DateUtil.formatMonth.get().parse(yearMonth);
		} catch (ParseException e) {
			logger.debug("qryPayBillByMonth02 cause exception,yearMonth:"+yearMonth,e);
		}
		//2.交互
		boolean useDefaultIfNull = true;//TODO ..
		PayBillType payBillType = fetchBillTypeIdAndDefault(request, userId,true,useDefaultIfNull);//默认为物业费类别
		BigInteger billTypeId = payBillType.getId();
		IBusinessMonthYear monthYearWithGB = plotpropertyService.getBusinessMonthYearByBillMonth(userId, billDate, billTypeId);//TODO ..是否需要更换为缴费月份
		//按月份查询对应账单信息
		PlotpropertyCombEntity plotpropertyCombEntity=plotpropertyService.getPayBillByMonth(userId, monthYearWithGB,true,billTypeId);
		//3.结果处理
		Map<String,Object> resMap = convertCommonPayBill2Map02(userId,plotpropertyCombEntity);
		try {//附加粮票信息
			Double toPayAmount = PriceUtil.div100(plotpropertyCombEntity.getPayBillEntity().getAmount()).doubleValue();
			appendHbAmount(resMap, toPayAmount, userId,plotpropertyCombEntity.getPayBillEntity().getIsPropFee());
		} catch (Exception e) {}
		appendJfqTel(resMap);//附加解放信息
		
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	/**
	 * 根据账单Id查询明细的接口
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPayBillDetailById.json")
	@ResponseBody
	public JsonResponse qryPayBillDetailById(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger billId = ParamUtils.getBigInteger(request, "payBillId", null);
		if(billId==null){throw new BusinessRuntimeException("PlotpropertyController.qryPayBillDetailById.billId.null");}
		//2.交互
		//根据Id查询详情
		PlotpropertyCombEntity plotpropertyCombEntity=plotpropertyService.getPayBillDetailById(userId, billId,true);
		//3.结果处理
		Map<String,Object> resMap = convertCommonPayBill2Map02(userId,plotpropertyCombEntity);
		try {//附加粮票信息
			Double toPayAmount = PriceUtil.div100(plotpropertyCombEntity.getPayBillEntity().getAmount()).doubleValue();
			appendHbAmount(resMap, toPayAmount, userId,plotpropertyCombEntity.getPayBillEntity().getIsPropFee());
		} catch (Exception e) {}
		
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
	private void appendHbAmount(Map<String,Object> tmpMap,Double toPayAmount,BigInteger userId,Integer isPropFee){
		if(PlotpropertyDict.PayBillType_IsPropFee.NO_notMR.compareTo(isPropFee)==0){//非物业费可以使用粮票
			Integer orderType = EbuyDict.EbuyOrder_Type.Property_Bill;
			SimpleHbBalanceEntity simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId, 0);
			Double payPercent = payConfigService.getPayConfigHbPercent(orderType);
			//3.结果处理
			BigDecimal resShow = PayConfigUtil.calculateMaxCouponAmount(toPayAmount,payPercent);
			BigDecimal balDes = simpleHbBalanceEntity.getBalanceDiv100();
			if(resShow.compareTo(balDes)>0){
				resShow = balDes;
			}
			tmpMap.put("hbTotalAmount", balDes.doubleValue());
			tmpMap.put("hbUsePercent", payPercent);
			tmpMap.put("hbUseAmount",resShow.doubleValue());//针对待支付金额获取最大可优惠金额
		}
	}
	
	private void appendJfqTel(Map<String,Object> tmpMap){
		CompanyInfoConfig companyInfoConfig = companyService.getCompanyServiceInfo();
		tmpMap.put("jfqTel", companyInfoConfig.getTel());
	}
	
	/**
	 * 确认门牌信息
	 */
	@RequestMapping("/confirmRealRoomInfo.json")
	@ResponseBody
	public JsonResponse confirmRealRoomInfo(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger realRoomId = new BigInteger(request.getParameter("realRoomId"));
		String proprietorName = request.getParameter("proprietorName");
		String payUserName = request.getParameter("payUserName");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		
		RoomEntityWithValidate roomEntity = plotpropertyService.confirmRealRoomInfo(userId,realRoomId,proprietorName,payUserName);
		// 3.结果处理
		Map<String,Object> resMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(), null);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
//	/**
//	 * 查询已缴账单列表
//	 */
//	@RequestMapping("/qryIsPayBillList.json")
//	@ResponseBody
//	public JsonResponse qryIsPayBillList(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		PageModel pageModel = ControllerUtils.getPageModel(request);
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		//2.交互
//		List<PayBillEntity>  payBillEntityList = plotpropertyService.getIsPayBillList(userId,pageModel);
//		//3.结果处理
//		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
////		for(PayBillEntity tmpPayBillEntity:payBillEntityList){
////			Map<String,Object> tmpMap = convertCommonPayBill2Map02(tmpPayBillEntity, null);
////			resList.add(tmpMap);
////		}
//		resList = payBillEntityListGroupByYear(payBillEntityList);
//		
//		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
//	}
	
	/**
	 * 查询已缴账单列表
	 */
	@RequestMapping("/qryIsPayBillList02.json")
	@ResponseBody
	public JsonResponse qryIsPayBillList02(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean useDefaultIfNull = false;
		PayBillType payBillType = fetchBillTypeIdAndDefault(request, userId,true,useDefaultIfNull);//默认为物业费类别
		if(payBillType==null){
			throw new BusinessRuntimeException("PlotpropertyController.qryIsPayBillList02.payBillType.null");
		}
		BigInteger billTypeId = payBillType.getId();
		List<PayBillInfo>  payBillEntityList = plotpropertyService.getIsPayBillList(userId,pageModel,billTypeId);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(PayBillInfo tmpPayBillEntity:payBillEntityList){
			Map<String,Object> tmpMap = convertCommonPayBill2Map02(tmpPayBillEntity, null,payBillType,null);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	
	/**
	 * 查询已缴账单列表
	 */
	@RequestMapping("/qryIsPayBillList03.json")
	@ResponseBody
	public JsonResponse qryIsPayBillList03(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<PropertyBillInfo>  payBillEntityList = null;
		//获取门牌确认信息
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		RoomValidate validate = new RoomValidate();
		validate.settRoomFId(roomId);
		validate.setUserId(userId);
		validate.setVerifyStatus(4);
		int count  = roomValidateBaseService.getRoomValidateCount(MapConverter.toMap(validate));
		if (count > 0) {
			payBillEntityList = plotpropertyService.getIsPayBillList(userId, pageModel);
			return ControllerUtils.addPageInfo(jsonResponse, payBillEntityList, pageModel.isLast, pageModel.count);
		} else {
			return ControllerUtils.addPageInfo(jsonResponse, payBillEntityList, true, 0);
		}
	}
	
//	/**
//	 * 查询未缴账单列表
//	 */
//	@RequestMapping("/qryNotPayBillList.json")
//	@ResponseBody
//	public JsonResponse qryNotPayBillList(HttpServletRequest request){
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		//2.交互
//		List<PayBillEntity>  payBillEntityList = plotpropertyService.getNotPayBillList(userId);//未缴月份不作分页处理
//		//3.结果处理
//		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//		resList = payBillEntityListGroupByYear(payBillEntityList);
//		
//		return ControllerUtils.addPageInfo(jsonResponse, resList);
//	}
	
	/**
	 * 查询未缴账单列表
	 */
	@RequestMapping("/qryNotPayBillList02.json")
	@ResponseBody
	public JsonResponse qryNotPayBillList02(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
//		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean useDefaultIfNull = false;
		PayBillType payBillType = fetchBillTypeIdAndDefault(request, userId,true,useDefaultIfNull);//默认为物业费类别
		if(payBillType==null){
			throw new BusinessRuntimeException("PlotpropertyController.qryNotPayBillList02.payBillType.null");
		}
		BigInteger billTypeId = payBillType.getId();
		List<PayBillInfo>  payBillEntityList = plotpropertyService.getNotPayBillList(userId,billTypeId);//未缴月份不作分页处理
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		for(PayBillInfo tmpPayBillEntity:payBillEntityList){
			Map<String,Object> tmpMap = convertCommonPayBill2Map02(tmpPayBillEntity, null,payBillType,null);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
//	/**
//	 * 将物业账单列表按日期分组
//	 * @param payBillEntityList
//	 * @return
//	 */
//	private List<Map<String,Object>> payBillEntityListGroupByYear(List<PayBillEntity>  payBillEntityList){
//			//按日期分组<日期，收藏的List>
//			Map<String,List<PayBillEntity>> aaMap = new LinkedHashMap<String, List<PayBillEntity>>();
//			for(PayBillEntity tmpPayBillEntity:payBillEntityList){
////				String time = DateUtil.strFormate(tmpPayBillEntity.getMonth(), DateUtil.formatSecond.get(), DateUtil.formatOnlyYear.get());
//				String time = DateUtil.strFormate(PayBillShowUtil.getBillShowMonth(tmpPayBillEntity), DateUtil.formatSecond.get(), DateUtil.formatOnlyYear.get());
//				List<PayBillEntity> tmpList = aaMap.get(time);
//				if(tmpList!=null){
//					tmpList.add(tmpPayBillEntity);
//				}else{
//					tmpList = new ArrayList<PayBillEntity>();
//					tmpList.add(tmpPayBillEntity);
//					aaMap.put(time, tmpList);
//				}
//			}
//			//转Map
//			List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//			for(String time:aaMap.keySet()){
//				Map<String,Object> resMap = new LinkedHashMap<String, Object>();
//				List<PayBillEntity> tmpPayBillEntityList = aaMap.get(time);
//				resMap.put("time", time);
//				List<Map<String,Object>> tmpPayBillEntityMapList = new ArrayList<Map<String,Object>>();
//				for(PayBillEntity tmpPayBillEntity:tmpPayBillEntityList){
//					Map<String,Object> signalMap = convertCommonPayBill2Map02(tmpPayBillEntity, null);
//					tmpPayBillEntityMapList.add(signalMap);
//				}
//				resMap.put("payBillList", tmpPayBillEntityMapList);
//				resList.add(resMap);
//			}
//			return resList;
//	}
	
	/**
	 * 缴物业费
	 */
	@RequestMapping("/confirmPayBill.json")
	@ResponseBody
	@RepeatReqValidate(expireSecond = 2)
	public JsonResponse confirmPayBill(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger payBillId = null;
		if(!DataUtil.isEmpty(request.getParameter("payBillId"))) {
			payBillId = new BigInteger(request.getParameter("payBillId"));
		}

		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Integer month = ParamUtils.getInteger(request, "month", null);
		Double hbAmount = ParamUtils.getDouble(request, "hbAmount", 0);
		Integer dataFromType = ParamUtils.getInteger(request, "dataFromType", 0);

		//2.交互
		Long subChannelId = parseSubChannelId(request);
		Long hbAmountL = DataUtil.isEmpty(NumberUtils.doubleM100ToLong(hbAmount)) ? 0 : NumberUtils.doubleM100ToLong(hbAmount);
		PreOrderDto preOrder = plotpropertyService.confirmBill(userId, payBillId, subChannelId, HeaderManager.getDeviceId(), hbAmountL, month, dataFromType, false);
		BigInteger orderId = preOrder.getOrderId();
		Boolean isAutoPaySucc = preOrder.isFree();

		//3.结果处理
		jsonResponse.putData("orderId", orderId);//兼容1.5.0版本
		jsonResponse.putData("id", orderId);
		jsonResponse.putData("isAutoPaySucc", isAutoPaySucc);
		return jsonResponse;
	}
	
	/**
	 * 获取支付完成订单详情
	 */
	@RequestMapping("/getPropertyDetail.json")
	@ResponseBody
	public JsonResponse getPropertyDetail(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Long paybillId = ParamUtils.getLong(request, "payBillId", null);
		Long orderId = ParamUtils.getLong(request, "orderId", null);
		paramMap.put("payBillId", paybillId);
		paramMap.put("orderId", orderId);
		paramMap.put("userId", userId);
		
		if(orderId != null) {//物业宝抵扣，代扣卡自动划扣没有生成订单信息
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyOrderDetail(paramMap);
			if(plotpropertyOrder != null) {
				PropertyDetail propertyDetail = convertPropertyDetail(plotpropertyOrder);
				{
					BigDecimal sqAmountBt = getSQPayBt(orderId, plotpropertyOrder.getPayMethod());
					propertyDetail.setSqAmountBt(sqAmountBt);
					if(sqAmountBt!=null && sqAmountBt.doubleValue()>0){
						propertyDetail.setSqCouponDesc("银行卡优惠 ￥"+sqAmountBt);
					}
				}
				jsonResponse.setDataValue(propertyDetail);
			}
		} else if(paybillId != null && orderId == null) {
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyNoOrderDetail(paramMap);
			if(plotpropertyOrder != null) {
				PropertyDetail propertyDetail = convertPropertyDetail02(plotpropertyOrder);
				{
					BigDecimal sqAmountBt = getSQPayBt(orderId, plotpropertyOrder.getPayMethod());
					propertyDetail.setSqAmountBt(sqAmountBt);
					if(sqAmountBt!=null && sqAmountBt.doubleValue()>0){
						propertyDetail.setSqCouponDesc("银行卡优惠 ￥"+sqAmountBt);
					}
				}
				jsonResponse.setDataValue(propertyDetail);
			}
		}
		
		return jsonResponse;
	}
	
	/**
	 * 获取双乾补贴优惠金额（单位：元）
	 * 
	 * @param orderId
	 * @param payMethod
	 * @return
	 */
	public BigDecimal getSQPayBt(Long orderId, String payMethod){
		Long amountBt = 0L;
		Map<String, Object> paramMap = null;
		if(EbuyDict.EbuyPay_PayMethod.SqPay.toString().equals(payMethod)){// 双乾支付，查询双乾优惠
			paramMap = new HashMap<String, Object>();
			paramMap.put("tEbuyOrderFId", orderId);
			List<CashSqpayBt> cashSqpayBts = cashSqpayBtDao.selectCashSqpayBtByCondition(paramMap, false);
			if(cashSqpayBts!=null && cashSqpayBts.size()>0){
				CashSqpayBt cashSqpayBt = cashSqpayBts.get(0);
				amountBt = cashSqpayBt.getAmountBt();
			}
		} 
		return  PriceUtil.div100(amountBt);
	}
	
	private PropertyDetail convertPropertyDetail02(PlotpropertyOrderEntity plotpropertyOrder) {
		PropertyDetail propertyDetail = new PropertyDetail();
		PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();
		List<PropertyFeeDetail> propertyFeeDetailList = payBillEntity.getPropertyFeeDetailList();
		GroupBuildingEntity groupBuilding = payBillEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
		IBusinessMonthYear businessMonthYear = BusinessMonthYearFactory.createByPayBill(payBillEntity, groupBuilding);
		{
			String topName = businessMonthYear.getBillMonth().getPeriodDesc()+payBillEntity.getBillTypeName();
			propertyDetail.setTopName(topName);
			
			List<Map<String,Object>> feeDetail = new ArrayList<Map<String,Object>>();
			if(propertyFeeDetailList!=null&&propertyFeeDetailList.size()>0){
				for(PropertyFeeDetail tmpSrcDetail:propertyFeeDetailList){
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("name", tmpSrcDetail.getName());
					tmpMap.put("totalPrice", "￥" +PriceUtil.div100(BigDecimal.valueOf(tmpSrcDetail.getTotalPrice()==null?0.0:tmpSrcDetail.getTotalPrice()))+ ((tmpSrcDetail.getAllowancePrice() == null || tmpSrcDetail.getAllowancePrice() == 0) ? "" : "（物业宝已抵扣）"));
					feeDetail.add(tmpMap);
				}
			}
			if(payBillEntity.getLastUnpaid() != null && payBillEntity.getLastUnpaid() > 0) {
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("name", "往月欠费");
				tmpMap.put("totalPrice", "￥" + PriceUtil.div100s(payBillEntity.getLastUnpaid()));
				feeDetail.add(tmpMap);
			}
			propertyDetail.setFeeDetail(feeDetail);
		}
		propertyDetail.setPayStatus(2);
		propertyDetail.setTotalAmt(PriceUtil.div100(payBillEntity.getAmount()));
		propertyDetail.setSucPay(PriceUtil.div100(payBillEntity.getSuccPayAmount()));
		propertyDetail.setProprietorName(plotpropertyService.replaceNameToStar(payBillEntity.getRealRoomEntity().getPropertyProprietor().getName()));

		if(plotpropertyOrder.getFinanceStatus() == 1) {
			propertyDetail.setPayWay("物业宝抵扣");
		} else {
			if(payBillEntity.getPaymentWay()!=null && payBillEntity.getPaymentWay()==2) {
				propertyDetail.setPayWay("物业公司手工标记");
			} else if(payBillEntity.getPaymentWay()!=null && payBillEntity.getPaymentWay()==3) {
				propertyDetail.setPayWay("代扣卡续费");
			}
		}

		propertyDetail.setPayLimiteStart(businessMonthYear.getPayTimeStartDesc());
		propertyDetail.setPayLimiteEnd(businessMonthYear.getPayTimeEndDesc());
		propertyDetail.setPayTm(DateUtil.strFormate(payBillEntity.getSys0UpdTime(), DateUtil.formatSecond.get(), DateUtil.formatMinutes.get()));
		propertyDetail.setAddrBuilding(CommonRoomUtil.getAddressArea02(payBillEntity.getRealRoomEntity()));
		propertyDetail.setAddrRoom(CommonRoomUtil.getAddressDetail02(payBillEntity.getRealRoomEntity()));

		return propertyDetail;
	}

	/**
	 * 获取未支付订单详情
	 */
	@RequestMapping("/getPropertyDetailNotPay.json")
	@ResponseBody
	public JsonResponse getPropertyDetailNotPay(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		Long payBillId = ParamUtils.getLong(request, "payBillId", null);
		Integer dataFromType = ParamUtils.getInteger(request, "dataFromType", 0);
		paramMap.put("payBillId", payBillId);
		paramMap.put("userId", userId);

		//判断是否达到缴费上限
		if (!plotpropertyService.isCanPayBill(HeaderManager.getDeviceId(),userId)) {
			jsonResponse.setErrcode("payTimes.overflow");
			jsonResponse.setMessage("已达本月缴费次数上限！");
			jsonResponse.setStatus("0001");
			return jsonResponse;
		}

		PropertyDetail propertyDetail = null;
		if(dataFromType.equals(1)) {//第三方对接物业软件
			propertyDetail = plotpropertyService.getPlotpropertyNotPayOrderDetailFrom3rd(userId);
		} else {//自营物业平台
			PlotpropertyOrderEntity plotpropertyOrder = plotpropertyService.getPlotpropertyNotPayOrderDetail(paramMap);
			propertyDetail = plotpropertyService.convertPropertyDetail2(plotpropertyOrder);
		}

		if(propertyDetail == null) {
			jsonResponse.setErrcode("getData.timeout");
			jsonResponse.setMessage("获取数据失败！");
			jsonResponse.setStatus("0001");
			return jsonResponse;
		}

		jsonResponse.setDataValue(propertyDetail);
		return jsonResponse;
	}

	private PropertyDetail convertPropertyDetail(PlotpropertyOrderEntity plotpropertyOrder) {
		PropertyDetail propertyDetail = new PropertyDetail();
		PayBillEntity payBillEntity = plotpropertyOrder.getPayBillEntity();

		List<PropertyFeeDetail> propertyFeeDetailList = payBillEntity.getPropertyFeeDetailList();
		GroupBuildingEntity groupBuilding = payBillEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();

		long prefereAmt = plotpropertyOrder.getPreferentialAmt();
		{
			String couponDesc = "";//使用物业费折扣***折,为您节省￥**
			if(payBillEntity.getIsPay().compareTo(PlotpropertyDict.PayBill_IsPay.TRUE)==0){
				Long couponAmount = payBillEntity.getAmount() - payBillEntity.getSuccPayAmount() - (payBillEntity.getDeduPrice() == null ? 0 : payBillEntity.getDeduPrice());
				if(couponAmount>0){
					couponDesc = "解放区优惠 ￥" + PriceUtil.div100s(couponAmount);
				}
			}else{
				couponDesc = "系统支付确认中..";
			}
			propertyDetail.setCouponDesc(couponDesc);

			String billMonthStart = payBillEntity.getBillMonthStart();
			String billMonthEnd = payBillEntity.getBillMonthEnd();
			billMonthStart = billMonthStart.substring(0,7);
			billMonthEnd = billMonthEnd.substring(0,7);
			String topName = billMonthStart+"至"+billMonthEnd+payBillEntity.getBillTypeName();
			propertyDetail.setTopName(topName);

			List<Map<String,Object>> feeDetail = new ArrayList<Map<String,Object>>();
			if(propertyFeeDetailList!=null&&propertyFeeDetailList.size()>0){
				for(PropertyFeeDetail tmpSrcDetail:propertyFeeDetailList){
					Map<String,Object> tmpMap = new HashMap<String, Object>();
					tmpMap.put("name", tmpSrcDetail.getName());
					tmpMap.put("totalPrice", "￥" +PriceUtil.div100(BigDecimal.valueOf(tmpSrcDetail.getTotalPrice()==null?0.0:tmpSrcDetail.getTotalPrice()))+ ((tmpSrcDetail.getAllowancePrice() == null || tmpSrcDetail.getAllowancePrice() == 0) ? "" : "（物业宝已抵扣）"));
					feeDetail.add(tmpMap);
				}
			}
			if(payBillEntity.getLastUnpaid() != null && payBillEntity.getLastUnpaid() > 0) {
				Map<String,Object> tmpMap = new HashMap<String, Object>();
				tmpMap.put("name", "往月欠费");
				tmpMap.put("totalPrice", "￥" + PriceUtil.div100s(payBillEntity.getLastUnpaid()));
				feeDetail.add(tmpMap);
			}
			propertyDetail.setFeeDetail(feeDetail);
		}
		propertyDetail.setPayStatus(plotpropertyOrder.getPayStatus());
		propertyDetail.setTotalAmt(PriceUtil.div100(payBillEntity.getAmount()));
		propertyDetail.setSucPay(PriceUtil.div100(plotpropertyOrder.getAmount())); //payBillEntity.getSuccPayAmount()
		propertyDetail.setPreferentialAmt(PriceUtil.div100(prefereAmt));/*订单优惠金额 = 随机立减 + 粮票金额*/
		propertyDetail.setJFBAmount(PriceUtil.div100(plotpropertyOrder.getJFBAmount()));
		propertyDetail.setProprietorName(plotpropertyService.replaceNameToStar(payBillEntity.getRealRoomEntity().getPropertyProprietor().getName()));
		propertyDetail.setPayWay(getPayMethod(plotpropertyOrder.getPayMethod())); //payWay {"1":"微信支付","2":"支付宝","3":"银联支付"}

		propertyDetail.setPayLimiteStart(payBillEntity.getPayDayStart());
		propertyDetail.setPayLimiteEnd(payBillEntity.getPayDayEnd());
//		propertyDetail.setPayLimiteStart(curr.getStartDesc());
//		propertyDetail.setPayLimiteEnd(curr.getEndDesc());

		propertyDetail.setPayTm(DateUtil.strFormate(plotpropertyOrder.getPayTime(), DateUtil.formatSecond.get(), DateUtil.formatMinutes.get()));
		propertyDetail.setAddrBuilding(CommonRoomUtil.getAddressArea02(payBillEntity.getRealRoomEntity()));
		propertyDetail.setAddrRoom(CommonRoomUtil.getAddressDetail02(payBillEntity.getRealRoomEntity()));
		propertyDetail.setOrderNo(plotpropertyOrder.getOrderNo());
		propertyDetail.setClientPayStatus(plotpropertyOrder.getClientPayStatus() == null ? 0 : plotpropertyOrder.getClientPayStatus());
		propertyDetail.setOrderId(plotpropertyOrder.getId()+"");
		return propertyDetail;
	}

	private String getPayMethod(String type) {
		//{"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付","9":"双乾支付"}',
		int typeInt = Integer.valueOf(type);
		String typeStr = "";
		switch (typeInt) {
		case 1:
			typeStr = "微信支付";
			break;
		case 2:
			typeStr = "支付宝";
			break;
		case 3:
			typeStr = "银联支付";
			break;
		case 4:
			typeStr = "纯粮票支付";
			break;
		case 5:
			typeStr = "纯积分支付";
			break;
		case 6:
			typeStr = "微信轻应用支付";
			break;
		case 7:
			typeStr = "纯优惠券支付";
			break;
		case 8:
			typeStr = "随机优惠支付";
			break;
		case 9:
			typeStr = "银行卡支付";
			break;
		default:
			break;
		}
		return typeStr;
	}

	/**
	 * syl-add 2015-5-6 11:11:22
	 * 获取渠道来源 电商模块也有调用
	 */
	protected Long parseSubChannelId(HttpServletRequest request){
		String subChannel = request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_SUB_CHANNEL);
		Long subChannelId = null;
		if(!StringUtils.isEmpty(subChannel)){
			subChannelId = Long.valueOf(subChannel);
		}
		return subChannelId;
	}
	
	/**
	 * 门牌信息和物业账单信息转为Map
	 * @param roomEntity
	 * @param payBillEntity
	 * @return
	 */
	private Map<String,Object> convertCommonPayBill2Map02(BigInteger userId,PlotpropertyCombEntity plotpropertyCombEntity){
		Map<String,Object> resMap = plotpropertyService.plotpropertyCombEntity2Map02(userId,plotpropertyCombEntity);
		return resMap;
	}
	private Map<String,Object> convertCommonPayBill2Map02(PayBillInfo payBillEntity,PrizeRecordSimpleEntity prizeRecordSimpleEntity,PayBillType payBillType,IBusinessMonthYear businessMonthYear){
		Map<String,Object> resMap = plotpropertyService.plotpropertyCombEntity2Map02(payBillEntity,prizeRecordSimpleEntity, payBillEntity.getPropertyFeeDetailList(),payBillType,businessMonthYear);
		return resMap;
	}
	
	/**
	 * 获取账单类别Id,为空则默认取物业费类别Id
	 * @param request
	 * @param userId
	 * @return
	 */
	private PayBillType fetchBillTypeIdAndDefault(HttpServletRequest request,BigInteger userId,boolean defaultProp,boolean useDefaultIfNull){
		BigInteger billTypeId = ParamUtils.getBigInteger(request, "billTypeId",null);
		if(billTypeId==null){
			if(!defaultProp){
				throw new BusinessRuntimeException("PlotpropertyController.billTypeId.null");
			}
		}
		return plotpropertyService.getPropBillTypeInfoByTypeIdAndPropIfNull(billTypeId, userId,useDefaultIfNull);
	}
	
	/**
	 * 查询物业账单列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBillList.json")
	@ResponseBody
	public JsonResponse getBillList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		List<PropertyBillEntity> billList = plotpropertyService.getBillList02(userId, null, null);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (PropertyBillEntity propertyBillEntity : billList) {
			Map<String,Object> resMap = convertPropertyBillList(propertyBillEntity);
			list.add(resMap);
		}
		
		// 物业缴费处车辆缴费卡
		List<Map<String, Object>> carBills = getCarBil(userId);
		list.addAll(carBills);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		jsonResponse.setDataValue(map);
		logger.debug("getBillList:" + JSON.toJSONString(jsonResponse));
		
		return jsonResponse;
	}
	
	public List<Map<String, Object>> getCarBil(BigInteger userId){
		List<CarBill> carBills = accessService.queryCarBill(userId, userId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Date now = new Date();
		for(CarBill carBill:carBills){
			Map<String,Object> resMap = new HashMap<String, Object>();
			resMap.put("carNum", carBill.gettCarNum());
			resMap.put("isSucBill", 1);
			resMap.put("billTitle", carBill.gettCarNum());
			resMap.put("billType", 3);
			resMap.put("billTypeName", "停车费账单");
			String icon = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_BILL_ICON);
			resMap.put("billTypeImg", icon);

			resMap.put("billAmt", carBill.getFee());
			resMap.put("billRelAmt", carBill.getFee());
			resMap.put("isPreferential", 0);
			resMap.put("isPay", 2);
			resMap.put("payDateDesc", getPayDateDesc(carBill.getExpireDate(), now, carBill.getGbName()));
			
			list.add(resMap);
		}
		
		return list;
	}
	
	long oneDay = 1000 * 60 * 60 * 24;
	/**
	 * 获取车禁有效期描述
	 * @param expire
	 * @param now
	 * @param gbName
	 * @return
	 */
	public String getPayDateDesc(String expire, Date now, String gbName){
		Date expireDate = null;
		try {
			expireDate = DateUtil.formatSecond.get().parse(expire);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String payDateDesc = gbName + "月卡 ";
		
		if(expireDate!=null){
			long expireDays = (expireDate.getTime() - now.getTime()) / oneDay;
			if(expireDays>0){
				payDateDesc += expireDays+"天后到期";
			} else if(expireDays==0){
				payDateDesc += "今天已到期";
			} else {
				payDateDesc += "已欠费"+(-expireDays)+"天";
			}
		}
		
		return payDateDesc;
	}
	
	/**
	 * 抽取物业缴费优惠金额
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPreferential.json")
	@ResponseBody
	public JsonResponse getPreferential(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		
		//搜集信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger billId = ParamUtils.getBigInteger(request, "payBillId", null);
		
		//获取结果集
		Map<String,Object> resMap = plotpropertyService.getPreferential(userId, billId);
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
	/**
	 * 小区是否开通缴费和门牌是否确认
	 * @param request
	 * @return
	 */
	@RequestMapping("/isOpenService.json")
	@ResponseBody
	public JsonResponse isOpenService(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();

		//搜集信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		
		//组合数据
		Map<String,Object> resMap = new HashMap<String, Object>();
		//获取门牌确认信息
		/*Integer checkCount = plotpropertyService.getCheckRoomCountByRealRoomId(userId, realRoomId);
		if(checkCount!=null&&checkCount>0){//已存在确认过的门牌
			resMap.put("isConfirmRoom", 1);
		} else {
			resMap.put("isConfirmRoom", 0);
		}*/
		
		//小区是否开通缴费；签约且开启缴费
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		if (groupBuilding != null
				&& groupBuilding.getSignStatus() != null && groupBuilding.getSignStatus().compareTo(1) == 0
				&& groupBuilding.getPropfeeCanpay() != null && groupBuilding.getPropfeeCanpay().compareTo(1) == 0){
			resMap.put("isOpenService", 1);
		} else {
			resMap.put("isOpenService", 0);
		}
		
		//缴费记录查询
		Boolean isHasRecord = plotpropertyService.isHasPayBillRecordByUserIdAndRealRoomId(userId, realRoomId);
		
		//业主信息是否确认过
		Boolean isConfirmPass = plotpropertyService.isConfirmPass(userId, roomId);
		
		//判断是需要进行校验
		if(resMap.get("isOpenService").equals(1)) {
			if(groupBuilding.getVerificationStatus() == 1) {//开启缴费校验
				if(!isHasRecord) {//没有缴费记录
					if(isConfirmPass) {//是否已经确认过
						resMap.put("isConfirmRoom", 1);
					} else {
						resMap.put("isConfirmRoom", 0);
					}
				} else {
					resMap.put("isConfirmRoom", 1);
				}
			} else {
				resMap.put("isConfirmRoom", 1);
			}
		} else {
			resMap.put("isConfirmRoom", 1);
		}
		
		//需要进行确认的话增加返回信息
		if(resMap.get("isConfirmRoom").equals(0)) {//没有进行确认
			PropertyManagement propertyManagement = propertyManagementBaseService.getPropertyManagementBySeqId(groupBuilding.gettPropertyManagementFId());
			if(propertyManagement != null) {
				resMap.put("managerTel", propertyManagement.getTel());
			} else {
				resMap.put("managerTel", "");
			}
			resMap.put("gbName", groupBuilding.getName());
			Building building = buildingBaseService.getBuildingBySeqId(realRoom.gettBuildingFId());
			resMap.put("roomNo", building.getName()+"-"+realRoom.getNum());
		}
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
	/**
	 * 验证门牌的业主姓名
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkProprietorName.json")
	@ResponseBody
	public JsonResponse checkProprietorName(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		
		//搜集信息
		String name = request.getParameter("name");
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		
		Boolean isConfirmPass = plotpropertyService.checkProprietorName(realRoom.getId(), name);
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(isConfirmPass) {
			//更新user_has_room是否确认信息
			plotpropertyService.updateConfirmStatus(userId, roomId);
			resMap.put("isConfirmPass", 1);
		} else {
			resMap.put("isConfirmPass", 0);
		}
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
	/**
	 * 首页公共API
	 * @param request
	 * @return
	 */
	@RequestMapping("/commonApi.json")
	@ResponseBody
	public JsonResponse commonApi(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		
		//搜集信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//查询当期需缴费用总和
		Double totalAmt = plotpropertyService.getTotalAmtNow(userId);
		DecimalFormat df = new DecimalFormat("0.00");
		
		Map<String,Object> potpropertyMap = new HashMap<String, Object>();
		potpropertyMap.put("data_type", "wuyejiaofei");//code使用首页的code:t_operation_home_supply_type:物业缴费
		
		if(totalAmt != 0) {
			potpropertyMap.put("data_str", "本期 ¥"+df.format(totalAmt));
		} else {
			potpropertyMap.put("data_str", "");
		}
		
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		resList.add(potpropertyMap);
		
		Map<String,Object> resMap = new HashMap<String, Object>();
		resMap.put("list", resList);
		resMap.put("hasNext", false);
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
	/**
	 * 获取物业公司联系电话
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryCompanyTel.json")
	@ResponseBody
	public JsonResponse qryCompanyTel(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//搜集信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		
		//查询物业管理处联系电话
		GroupBuilding groupBuilding = commonRoomService.getGroupBuildingByUserId(userId);
		Map<String,Object> resMap = new HashMap<String, Object>();
		if (groupBuilding != null) {
			PropertyManagement propertyManagement = propertyManagementBaseService.getPropertyManagementBySeqId(groupBuilding.gettPropertyManagementFId());
			if(propertyManagement != null) {
				resMap.put("tel", propertyManagement.getTel());
			} else {
				resMap.put("tel", "");
			}
		} else {
			resMap.put("tel", "");
		}
		
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}

	/**
	 * 查询选择周期详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAlterPeriodDetail.json")
	@ResponseBody
	public JsonResponse getAlterPeriodDetail(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();

		//判断是否达到缴费上限
		if (!plotpropertyService.isCanPayBill(HeaderManager.getDeviceId(),userId)) {
			jsonResponse.setErrcode("payTimes.overflow");
			jsonResponse.setMessage("已达本月缴费次数上限！");
			jsonResponse.setStatus("0001");
			return jsonResponse;
		}

		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();

		PropertyAlterBillInfo propertyAlterBillInfo = plotpropertyService.getAlterPeriodDetail(realRoomId);

		Map<String,Object> resMap = plotpropertyService.convertAlterPeriodDetail(propertyAlterBillInfo);
		jsonResponse.setDataValue(resMap);

		return jsonResponse;
	}

	/**
	 * 抽取物业缴费优惠金额（选择周期）
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAlterPeriodPreferential.json")
	@ResponseBody
	public JsonResponse getAlterPeriodPreferential(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();

		//搜集信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		RealRoom realRoom = commonRoomService.selectRealRoomByUserId(userId);
		BigInteger realRoomId = realRoom.getId();
		Integer month = ParamUtils.getInteger(request, "month", null);

		//获取结果集
		Map<String,Object> resMap = plotpropertyService.getAlterPeriodPreferential(userId, realRoomId, month);
		jsonResponse.setDataValue(resMap);

		return jsonResponse;
	}

	/**组装物业账单信息*/
	private Map<String,Object> convertPropertyBillList(PropertyBillEntity propertyBillEntity){
		Map<String,Object> resMap = plotpropertyService.convertPropertyBillList(propertyBillEntity);
		return resMap;
	}

	public void setGroupBuildingBaseService(IGroupBuildingBaseService groupBuildingBaseService) {
		this.groupBuildingBaseService = groupBuildingBaseService;
	}

	public void setPropertyManagementBaseService(IPropertyManagementBaseService propertyManagementBaseService) {
		this.propertyManagementBaseService = propertyManagementBaseService;
	}

	public void setBuildingBaseService(IBuildingBaseService buildingBaseService) {
		this.buildingBaseService = buildingBaseService;
	}
	
}
