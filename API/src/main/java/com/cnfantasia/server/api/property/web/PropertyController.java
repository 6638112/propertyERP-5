package com.cnfantasia.server.api.property.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.property.dto.*;
import com.cnfantasia.server.api.property.service.IPropertyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;
import com.cnfantasia.server.domainbase.roomValidate.service.IRoomValidateBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 物业缴费（version>=503）
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午10:06:22
 */
@Controller
@RequestMapping("/property")
public class PropertyController extends BaseController{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Resource
	private IPropertyService propertyService;
	@Resource
	private IPlotpropertyService plotpropertyService;
	@Resource
	private ICommonRoomService commonRoomService;
	@Resource
	private IRoomValidateBaseService roomValidateBaseService;
	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;

	/**
	 * 获取待缴费账单界面信息
	 * @return
	 */
	@RequestMapping("/getRemainBillInfo.json")
	@ResponseBody
	public JsonResponse getRemainBillInfo(HttpServletRequest request){
		BigInteger userId = null;
		String userIdStr = request.getParameter("userId");
		if(StringUtils.isNotBlank(userIdStr)){
			userId = new BigInteger(userIdStr);
		} else {
			userId = UserContext.getOperIdMustExistBigInteger();
		}
		//-- 切换门牌有问题 弃用 BigInteger gbId = UserContext.getCurrLoginNo().getUserEntity().getDefaultRoomEntity().getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
		BigInteger gbId = plotpropertyCfgService.getGroupBuildingIdByUserId(userId);
		RemainBillInfoDto remainBillInfo = propertyService.getRemainBillInfo(userId, request.getSession().getId(), gbId);
		List<RemainBillDto> remainBills = remainBillInfo.getRemainBills();
		if (!DataUtil.isEmpty(remainBills)) {
			for (RemainBillDto remainBill : remainBills) {
				if (remainBill.getCollectionArrearsType() != null && remainBill.getCollectionArrearsType() == 2) {
					BigInteger payBillId = remainBill.getPayBillId();
					List<RemainBillDto> unpaidBillInfoList = propertyService.getUnpaidBillInfoList(payBillId);
					BigDecimal lateFee = BigDecimal.ZERO;
					int lateCount = 0;
					List<BigInteger> latePayBillIds = new ArrayList<>();
					if (!DataUtil.isEmpty(unpaidBillInfoList)) {
						lateCount = unpaidBillInfoList.size();
						String billTitle = remainBill.getBillTitle();
						remainBill.setBillTitle(unpaidBillInfoList.get(0).getBillMonthStart() + billTitle.substring(billTitle.indexOf("-")));
						for (RemainBillDto remainBillDto : unpaidBillInfoList) {
							latePayBillIds.add(remainBillDto.getPayBillId());
							lateFee = lateFee.add(new BigDecimal(remainBillDto.getBillRelAmt()));
						}
					}
					//2017-09-05 生成欠费查询出现重复id数据，故在此做一下去重，以防万一 add-yangh-2017-09-21
					ArrayList<BigInteger> noRepeatLatePayBillIds = new ArrayList<>(new TreeSet<BigInteger>(latePayBillIds));
					lateCount = noRepeatLatePayBillIds.size();

					Map<String, Object> extInfo = new LinkedHashMap<>();
					extInfo.put("本期物业费", remainBill.getBillAmt());
					if (lateCount > 0) {
						extInfo.put("以往" + lateCount + "期欠费合计", lateFee.toString());
					}
					extInfo.put("latePayBillIds", noRepeatLatePayBillIds);
					remainBill.setBillAmt(lateFee.add(new BigDecimal(remainBill.getBillAmt())).toString());
					remainBill.setExtInfo(extInfo);
				}
			}
		}
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(remainBillInfo);
		return jsonResponse;
	}

	/**
	 * 获取确认支付界面（优惠）信息
	 * @param confirmBillInfoReqs
	 * @return
	 */
	@RequestMapping("/getConfirmBillInfo.json")
	@ResponseBody
	@RepeatReqValidate(expireSecond=1)
	public JsonResponse getConfirmBillInfo(String confirmBillInfoReqs, HttpServletRequest request){
		logger.info("[property.getConfirmBillInfo]confirmBillInfoReqs==>"+confirmBillInfoReqs);
		
		List<ConfirmBillInfoReq> confirmBillInfoReqList = JSON.parseArray(confirmBillInfoReqs, ConfirmBillInfoReq.class);
		JsonResponse jsonResponse = new JsonResponse();
		
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//判断是否达到缴费上限
		if (!plotpropertyService.isCanPayBill(HeaderManager.getDeviceId(), userId)) {
			jsonResponse.setErrcode("payTimes.overflow");
			jsonResponse.setMessage("已达本月缴费次数上限！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
		} else {
			PayAmtDto payAmt = propertyService.getConfirmBillInfo(userId, confirmBillInfoReqList, request.getSession().getId());
			jsonResponse.setDataValue(payAmt);
		}
				
		return jsonResponse;
	}
	
	/**
	 * 确认支付动作
	 * @param confirmPayReqs
	 * @param hbAmount 使用的红包金额（单位：元）
	 * @return
	 */
	@RequestMapping("/confirmPay.json")
	@ResponseBody
	@RepeatReqValidate(expireSecond = 2)
	public JsonResponse confirmPay(String confirmPayReqs, BigDecimal hbAmount, HttpServletRequest request){
		logger.info("[property.getConfirmBillInfo]confirmPayReqs==>"+confirmPayReqs);
		List<ConfirmPayReq> confirmPayReqList = JSON.parseArray(confirmPayReqs, ConfirmPayReq.class);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		String sessionId = request.getSession().getId();
		ConfirmPayResultDto confirmPayResult = propertyService.confirmPay(userId, confirmPayReqList, hbAmount, sessionId);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(confirmPayResult);
		return jsonResponse;
	}
	
	/**
	 * 获取支付详情（支付完成界面）
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/getPayDetail.json")
	@ResponseBody
	public JsonResponse getPayDetail(BigInteger orderId){
		logger.info("[property.getConfirmBillInfo]orderId==>"+orderId);
		PayDetailDto payDetailDto = propertyService.getPayDetail(orderId);
		
		JsonResponse jsonResponse= new JsonResponse();
		jsonResponse.setDataValue(payDetailDto);
		return jsonResponse;
	}
	
	/**
	 * 获取已缴账单界面信息
	 * @param paidBillInfoReq
	 * @return
	 */
	@RequestMapping("/getPaidBillList.json")
	@ResponseBody
	public JsonResponse getPaidBillList(Integer page, Integer pageNum, HttpServletRequest request){
		logger.info("[property.getConfirmBillInfo]page==>"+page+";pageNum"+pageNum);
		PaidBillInfoReq paidBillInfoReq = new PaidBillInfoReq();
		
		BigInteger userId = null;
		String userIdStr = request.getParameter("userId");
		if(StringUtils.isNotBlank(userIdStr)){
			userId = new BigInteger(userIdStr);
		} else {
			userId = UserContext.getOperIdMustExistBigInteger();
		}
		paidBillInfoReq.setUserId(userId);
		paidBillInfoReq.setPage(page);
		paidBillInfoReq.setPageNum(pageNum);

		/**为减少改动和发布增量包的方便性，故在API里面进行（门牌验证通过才能看见已缴账单信息）的控制 v504-yh 2017-04-05 16:46
		 * 参考PlotpropertyController.java-->qryIsPayBillList03.json
		 * */
		PaidBillDto paidBills = new PaidBillDto();
		BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
		RoomValidate validate = new RoomValidate();
		validate.settRoomFId(roomId);
		validate.setUserId(userId);
		validate.setVerifyStatus(4);
		int count  = roomValidateBaseService.getRoomValidateCount(MapConverter.toMap(validate));
		if(count > 0) {
			paidBills = propertyService.getPaidBillInfo(paidBillInfoReq);
		}
		
		JsonResponse jsonResponse= new JsonResponse();
		jsonResponse.setDataValue(paidBills);
		return jsonResponse;
	}
	
	/**
	 * 获取账单详情信息（已缴）
	 * @param orderId
	 * @param type 账单详情类型（1：物业缴费；2：车禁）
	 * @return
	 */
	@RequestMapping("/getPaidBillDetail.json")
	@ResponseBody
	public JsonResponse getPaidBillDetail(BigInteger orderId, String type){
		logger.info("[property.getPaidBillDetail]orderId==>"+orderId+";type="+type);
		Object detail = propertyService.getPaidBillDetail(orderId, type);
		Map<String, Object> infoMap = new HashMap<String, Object>();

		JsonResponse jsonResponse= new JsonResponse();
		if(detail instanceof CarPaidBillDetailDto){
			CarPaidBillDetailDto cpbd = (CarPaidBillDetailDto) detail;
			infoMap.put("desc", cpbd.getDesc());
			infoMap.put("amount", cpbd.getAmount());
			infoMap.put("tel", cpbd.getTel());
			infoMap.put("title", cpbd.getTitle());
			
			List<KeyItem> list = new ArrayList<KeyItem>();
			
			BillPayDto billPay = cpbd.getBillPay();
			PreferAmtDto preferAmt = billPay.getPreferAmt();
			if(preferAmt!=null){
				String zero = "￥ 0.00";
				if(!zero.equals(preferAmt.getJfqDiscount())){
					list.add(new KeyItem("随机立减优惠", preferAmt.getJfqDiscount(), true, false));
				}
				if(!zero.equals(preferAmt.getBankDiscount())){
					list.add(new KeyItem("银行卡支付优惠", preferAmt.getBankDiscount(), true, false));
				}
				if(!zero.equals(preferAmt.getHbAmt())){
					list.add(new KeyItem("粮票优惠", preferAmt.getHbAmt(), true, false));
				}
			}
			list.add(new KeyItem("支付状态", billPay.getResult(), false, false));
			list.add(new KeyItem("支付方式", billPay.getPayMethod(), false, false));
			list.add(new KeyItem("支付时间", billPay.getPayTime(), false, true));
			
			CarBillTailDto carBillTail = cpbd.getCarBillTail();
			list.add(new KeyItem("每月费用", carBillTail.getCarFee(), false, false));
			list.add(new KeyItem("缴费月数", carBillTail.getMonth(), false, false));
			list.add(new KeyItem("车辆信息", carBillTail.getCarNum(), false, false));
			list.add(new KeyItem("停放地址", carBillTail.getParking(), false, false));
			list.add(new KeyItem("到期时间", carBillTail.getExpire(), false, false));
			infoMap.put("list", list);
		} else if(detail instanceof PropertyPaidBillDetailDto){
			PropertyPaidBillDetailDto ppbd = (PropertyPaidBillDetailDto) detail;
			infoMap.put("desc", ppbd.getDesc());
			infoMap.put("amount", ppbd.getAmount());
			infoMap.put("tel", ppbd.getTel());
			infoMap.put("title", ppbd.getTitle());
			
			List<KeyItem> list = new ArrayList<KeyItem>();
			BillPayDto billPay = ppbd.getBillPay();
			PreferAmtDto preferAmt = billPay.getPreferAmt();
			if(preferAmt!=null){
				String zero = "￥ 0.00";
				if(preferAmt.getJfqDiscount()!=null && !zero.equals(preferAmt.getJfqDiscount())){
					list.add(new KeyItem("随机立减优惠", preferAmt.getJfqDiscount(), true, false));
				}
				if(preferAmt.getBankDiscount()!=null && !zero.equals(preferAmt.getBankDiscount())){
					list.add(new KeyItem("银行卡支付优惠", preferAmt.getBankDiscount(), true, false));
				}
				if(preferAmt.getHbAmt()!=null && !zero.equals(preferAmt.getHbAmt())){
					list.add(new KeyItem("粮票优惠", preferAmt.getHbAmt(), true, false));
				}
				if(preferAmt.getDeduAmt()!=null){
					list.add(new KeyItem("物业宝抵扣", preferAmt.getDeduAmt(), true, false));
				}
			}
			list.add(new KeyItem("支付状态", billPay.getResult(), false, false));
			list.add(new KeyItem("支付方式", billPay.getPayMethod(), false, false));
			list.add(new KeyItem("支付时间", billPay.getPayTime(), false, true));
			
			PropertyBillTailDto propertyBillTail = ppbd.getPropertyBillTail();
			list.add(new KeyItem("缴费地址", propertyBillTail.getPayAddress(), false, false));
			
			String proprietorName = propertyBillTail.getProprietorName();
			if(StringUtils.isNotBlank(proprietorName)){
				list.add(new KeyItem("业主姓名", proprietorName, false, false));
			}
			List<PayBillDetailDto> payBillDetails = propertyBillTail.getPayBillDetails();
			/**按数组里面的顺序排序显示*/
			Integer[] feeTypes = {FeeTypeDict.Xuan_Ze_Zhou_Qi, FeeTypeDict.Gu_Ding, FeeTypeDict.Chao_Biao, FeeTypeDict.Lin_Shi, FeeTypeDict.LATEFEE, null};
			for(Integer feeType:feeTypes){
				dealBillListByOrder(feeType, payBillDetails, list);
			}
			infoMap.put("list", list);
		} else {
			jsonResponse.setMessage("参数type传入错误！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		
		jsonResponse.putDataAll(infoMap);
		return jsonResponse;
	}
	
	private void dealBillListByOrder(Integer feeType, List<PayBillDetailDto> payBillDetails, List<KeyItem> list){
		int mrNum = 0;
		String indent = "    ";
		for (int i = payBillDetails.size() - 1; i >= 0; i--) {
			PayBillDetailDto payBillDetail = payBillDetails.get(i);
			if(((null!=payBillDetail.getFeeType() && null!=feeType) && feeType.compareTo(payBillDetail.getFeeType())==0)
					||(null==payBillDetail.getFeeType() && null==feeType)){
				if(StringUtils.isNotBlank(payBillDetail.getStartValue()) && (Double.valueOf(payBillDetail.getStartValue()) > 0 && Double.valueOf(payBillDetail.getEndValue()) > 0)){
					mrNum++;
					BigDecimal totalAmt = BigDecimal.ZERO;
					totalAmt = totalAmt.add(payBillDetail.getAmountBigDecimal());
					
					KeyItem keyItem = new KeyItem();
					keyItem.setName(payBillDetail.getBillName());
					keyItem.setValue(payBillDetail.getBillAmount());
					keyItem.setColor(false);
					keyItem.setAppendLine(false);
					if(mrNum==1 && list.size()>0){
						KeyItem fixKeyItem = list.get(list.size()-1);
						fixKeyItem.setAppendLine(true);
					}
					list.add(keyItem);
					boolean onlyOne = true;
					for(int k=payBillDetails.size()-1; k>=0; k--) {
						PayBillDetailDto pbdTmp = payBillDetails.get(k);
						if(StringUtils.isNotBlank(pbdTmp.getStartValue()) && pbdTmp!=payBillDetail && payBillDetail.getBillName().equals(pbdTmp.getBillName())) {
							if(onlyOne) {
								dealMrItem(payBillDetail, list, indent, false);
							}
							dealMrItem(pbdTmp, list, indent, false);
							totalAmt = totalAmt.add(pbdTmp.getAmountBigDecimal());
							payBillDetails.remove(k);
							k--;
							i--;
							onlyOne = false;
						}
					}
					if(onlyOne) {
						dealMrItem(payBillDetail, list, indent, true);
					}
					DecimalFormat df = new DecimalFormat("0.00");
					keyItem.setValue("￥ "+df.format(totalAmt));
				} else {
					list.add(new KeyItem(payBillDetail.getBillName(), payBillDetail.getBillAmount(), false, false));
				}
			} 
		}
		boolean isLast = (list.size()-mrNum*2 == payBillDetails.size());
		if(null!=feeType && FeeTypeDict.Chao_Biao.compareTo(feeType)==0 && mrNum < payBillDetails.size() && !isLast){
			KeyItem fixKeyItem = list.get(list.size()-1);
			fixKeyItem.setAppendLine(true);
		}
	}
	
	public void dealMrItem(PayBillDetailDto pbd, List<KeyItem> list, String indent, boolean onlyOne) {
		Double startValue = Double.parseDouble(pbd.getStartValue());
		Double endValue = Double.parseDouble(pbd.getEndValue());
		if(onlyOne) {
			if(startValue>0 && endValue>0){
				KeyItem startKeyItem = new KeyItem();
				startKeyItem.setName(indent+"上期读数");
				startKeyItem.setValue(delDotZero(pbd.getStartValue()));
				startKeyItem.setColor(false);
				startKeyItem.setAppendLine(false);
				list.add(startKeyItem);

				KeyItem endKeyItem = new KeyItem();
				endKeyItem.setName(indent+"本期读数");
				endKeyItem.setValue(delDotZero(pbd.getEndValue()));
				endKeyItem.setColor(false);
				endKeyItem.setAppendLine(false);
				list.add(endKeyItem);
			}
		} else {
			KeyItem nameItem = new KeyItem();
			nameItem.setName(indent+pbd.getMrName());
			nameItem.setValue(StringUtils.EMPTY);
			nameItem.setColor(false);
			nameItem.setAppendLine(false);
			list.add(nameItem);

			if(startValue>0 && endValue>0){
				KeyItem startKeyItem = new KeyItem();
				startKeyItem.setName(indent+indent+"上期读数");
				startKeyItem.setValue(delDotZero(pbd.getStartValue()));
				startKeyItem.setColor(false);
				startKeyItem.setAppendLine(false);
				list.add(startKeyItem);

				KeyItem endKeyItem = new KeyItem();
				endKeyItem.setName(indent+indent+"本期读数");
				endKeyItem.setValue(delDotZero(pbd.getEndValue()));
				endKeyItem.setColor(false);
				endKeyItem.setAppendLine(false);
				list.add(endKeyItem);
			}
		}
	}
	
	/**
	 * 获取账单详情信息（未缴）
	 * 
	 * @param carNum
	 * @param paybillId
	 * @param dataFromType
	 * @return
	 */
	@RequestMapping("/getUnPaidBillDetail.json")
	@ResponseBody
	public JsonResponse getUnPaidBillDetail(UnPaidBillDetailReq param, HttpServletRequest request){
		BigInteger userId = null;
		String userIdStr = request.getParameter("userId");
		if(StringUtils.isNotBlank(userIdStr)){
			userId = new BigInteger(userIdStr);
		} else {
			userId = UserContext.getOperIdMustExistBigInteger();
		}
		param.setUserId(userId);
		param.setSessionId(request.getSession().getId());
		
		Object detail = propertyService.getUnPaidBillDetail(param);
		logger.info("[property.getUnPaidBillDetail]param==>"+JSON.toJSONString(param));
		
		Map<String, Object> infoMap = new HashMap<String, Object>();
		if(detail instanceof CarUnPaidBillDetailDto){
			CarUnPaidBillDetailDto cpbd = (CarUnPaidBillDetailDto) detail;
			infoMap.put("desc", cpbd.getDesc());
			infoMap.put("amount", cpbd.getAmount());
			infoMap.put("tel", cpbd.getTel());
			infoMap.put("title", cpbd.getTitle());
			
			List<KeyItem> list = new ArrayList<KeyItem>();
			
			CarBillTailDto carBillTail = cpbd.getCarBillTail();
			list.add(new KeyItem("每月费用", carBillTail.getCarFee(), false, false));
			list.add(new KeyItem("缴费月数", carBillTail.getMonth(), false, false));
			list.add(new KeyItem("车辆信息", carBillTail.getCarNum(), false, false));
			list.add(new KeyItem("停放地址", carBillTail.getParking(), false, false));
			list.add(new KeyItem("到期时间", carBillTail.getExpire(), false, false));
			infoMap.put("list", list);
		} else {
			PropertyUnPaidBillDetailDto ppbd = (PropertyUnPaidBillDetailDto) detail;
			infoMap.put("desc", ppbd.getDesc());
			infoMap.put("amount", ppbd.getAmount());
			infoMap.put("tel", ppbd.getTel());
			infoMap.put("title", ppbd.getTitle());
			
			List<KeyItem> list = new ArrayList<KeyItem>();
			
			PropertyBillTailDto propertyBillTail = ppbd.getPropertyBillTail();
			list.add(new KeyItem("缴费地址", propertyBillTail.getPayAddress(), false, false));
			String proprietorName = propertyBillTail.getProprietorName();
			if(StringUtils.isNotBlank(proprietorName)){
				list.add(new KeyItem("业主姓名", proprietorName, false, false));
			}
			
			BigDecimal lastUnpaid = propertyBillTail.getLastUnpaid();
			if(lastUnpaid!=null && lastUnpaid.doubleValue()>0){
				String billAmount = "￥".concat(PriceUtil.div100s(lastUnpaid.longValue()));
				list.add(new KeyItem("往月欠费", billAmount, false, false));
			}
			
			infoMap.put("list", list);
			if (ppbd.getCollectionArrearsType() != null && ppbd.getCollectionArrearsType() == 2) {
				infoMap.put("unpaidList", propertyService.getUnpaidBillInfoList(new BigInteger(param.getPayBillId())));
			}
			
			List<PayBillDetailDto> payBillDetails = propertyBillTail.getPayBillDetails();
			/**按数组里面的顺序排序显示*/
			Integer[] feeTypes = {FeeTypeDict.Xuan_Ze_Zhou_Qi, FeeTypeDict.Gu_Ding, FeeTypeDict.Chao_Biao, FeeTypeDict.Lin_Shi, FeeTypeDict.LATEFEE, null};
			for(Integer feeType:feeTypes){
				dealBillListByOrder(feeType, payBillDetails, list);
			}
		}
		
		JsonResponse jsonResponse= new JsonResponse();
		jsonResponse.setDataValue(infoMap);
		return jsonResponse;
	} 
	
	private static final String delDotZero(String s) {
		if (StringUtils.isNotBlank(s)) {
			if (s.indexOf(".") > 0) {
				s = s.replaceAll("0+?$", "");//去掉多余的0
				s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
		}
		return s;
	}
	
}
