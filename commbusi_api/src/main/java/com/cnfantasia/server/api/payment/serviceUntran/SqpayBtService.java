package com.cnfantasia.server.api.payment.serviceUntran;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.ebuyorder.dao.IEbuyorderDao;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.constant.PayConfigConstant;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.api.payment.entity.SqPayBtInfoDto;
import com.cnfantasia.server.api.payment.entity.SqPayBtPrePayDto;
import com.cnfantasia.server.api.payment.entity.SqPayBtResponse;
import com.cnfantasia.server.api.payment.util.SqPayRSAUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.dao.IEbuyOrderRelationBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.dao.IEbuyPrepaySqLogBaseDao;
import com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity.EbuyPrepaySqLog;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

import MoneyUtil.MoneyUtil;

/**
 * 双乾支付补贴优惠
 * 
 * @author liyulin
 * @version 1.0 2016年9月20日 上午9:33:22
 */
public class SqpayBtService implements ISqpayBtService{
	private static final Log logger = LogFactory.getLog(SqpayBtService.class);
	private IDualDao dualDao;
	private ISysParamManager sysParamManager;
	private IUserBaseDao userBaseDao;
	private IUuidManager uuidManager;
	private IEbuyorderDao ebuyorderDao;
	private IEbuyPrepaySqLogBaseDao ebuyPrepaySqLogBaseDao;
	private ICommPayService commPayService;
	private ICommonEbuyService commonEbuyService;
	private CommEbuyPayRecordService commEbuyPayRecordService;
	private IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao;
    
	/**
	 * 预支付请求处理
	 * 
	 * @param orderId
	 * @param request
	 * @return
	 */
	@Override
	public JsonResponse prePayRequestHandler(BigInteger orderId, String merNo, HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		logger.info("sqpPayBt.prePayRequestHandler,orderId is:"+orderId+",userId is:"+userId);
		
		// 2.交互
		OrderPayInfo orderPayInfo = getOrderPayInfoById(userId, orderId);
		String         outTradeNo = orderPayInfo.getOutTradeNo();
		String            subject = orderPayInfo.getProductInfo();
		String              price = PriceUtil.div100(orderPayInfo.getTotalAmount()).toString();//单位转化为元
		String               body = orderPayInfo.getProductDetail()!=null?orderPayInfo.getProductDetail():orderPayInfo.getProductInfo();
		
		// 3.结果处理
		String        MerNo = merNo;
		String      PayType = "CJ01";
		String       BillNo = outTradeNo;
	    String       Amount = price+"";
	    String    MerRemark = subject;
	    String     products = body;  
    
	    SqPayBtPrePayDto sqPayBtPrePayDto = ebuyorderDao.selectSqPayBtRequestByOrderId(orderId);
	    sqPayBtPrePayDto.setMerNo(MerNo);
	    sqPayBtPrePayDto.setBillNo(BillNo);
	    sqPayBtPrePayDto.setAmount(Amount);
	    sqPayBtPrePayDto.setPayType(PayType);
	    sqPayBtPrePayDto.setRemark(MerRemark);
	    sqPayBtPrePayDto.setProducts(products);
	    sqPayBtPrePayDto.setUserId(userId);
		User user = userBaseDao.selectUserBySeqId(userId);
		if(user!=null){
			sqPayBtPrePayDto.setPhone(user.getMobile());
		}
		if(sqPayBtPrePayDto.getOrderType()!=null && sqPayBtPrePayDto.getOrderType()==2){// 物业缴费校验
			if(ebuyorderDao.isWyPaid(orderId)){// 重复缴费校验
				jsonResponse.setMessage("该账单已缴过费，请勿重复操作！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			}
		}
	    
	    //记录日志
		EbuyPrepaySqLog prepaySqLog = new EbuyPrepaySqLog();
		prepaySqLog.setAmout(Amount);
		prepaySqLog.setBillNo(BillNo);
		prepaySqLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_sq_log));
		prepaySqLog.setMerNo(MerNo);
		prepaySqLog.setMerRemark(MerRemark);
		prepaySqLog.setOrderId(orderId);
		prepaySqLog.setPayType(PayType);
		prepaySqLog.setProducts(products);
		prepaySqLog.setOrderType(sqPayBtPrePayDto.getOrderType());
		ebuyPrepaySqLogBaseDao.insertEbuyPrepaySqLog(prepaySqLog);
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("UserNo", sqPayBtPrePayDto.getUserId().toString());
		map.put("PhoneNo", dealMobile(sqPayBtPrePayDto.getPhone()));
		map.put("MerNo", sqPayBtPrePayDto.getMerNo());
		map.put("BillNo", sqPayBtPrePayDto.getBillNo());
		map.put("Amount", sqPayBtPrePayDto.getAmount());
		map.put("PayType", sqPayBtPrePayDto.getPayType());
		map.put("Remark", sqPayBtPrePayDto.getRemark());
		map.put("Products", sqPayBtPrePayDto.getProducts());
		map.put("Province", sqPayBtPrePayDto.getProvince());
		map.put("City", sqPayBtPrePayDto.getCity());
		map.put("Block", sqPayBtPrePayDto.getBlock());
		map.put("GbName", sqPayBtPrePayDto.getGbName());
		map.put("CourtNo", sqPayBtPrePayDto.getGbId());
		if(sqPayBtPrePayDto.getOrderType()==5 || sqPayBtPrePayDto.getOrderType()==99){
			map.put("OrderType", "2");// "其它代收费"当作“物业缴费”优惠
		} else {
			map.put("OrderType", sqPayBtPrePayDto.getOrderType().toString());
		}
		{
			boolean newFlag = ebuyorderDao.isFirstConsumed(userId);
			map.put("NewFlag", newFlag ? "0" : "1");// 0：新用户；1：旧用户
		}
		
		logger.info("sqpPayBt.prePayRequestHandler,param:"+JSON.toJSONString(map));
		checkNullParameters(map);
		
		jsonResponse.putDataAll(map);
		return jsonResponse;
	}
	
	private String dealMobile(String mobile) {
		if(mobile==null || mobile.toString().trim().length()<11 || mobile.toString().trim().length()>13){
			mobile = "11111111111";
		}
		return mobile;
	}
	
	/**
	 * 参数非空校验
	 * @param map
	 */
	public void checkNullParameters(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object v = entry.getValue();
			if ("Remark".equals(key) || "Products".equals(key) || "NewFlag".equals(key)) {
				continue;
			}
			if (v == null || StringUtils.isBlank(v.toString())) {
				ValidateRuntimeException validateRuntimeException = new ValidateRuntimeException(
						CommConstants.ResponseStatus.VALIDATE_ERR);
				validateRuntimeException.setErrorMsg(key + "不能为空！");
				throw validateRuntimeException;
			}
		}
	}
	
	/**
	 * 支付回调
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	@Override
	public boolean payNotify(SqPayBtResponse sqPayBtRequest) {
		boolean isVerifyPassed = sign(sqPayBtRequest);
		logger.info("sqPayBt.payNotify start,isVerifyPassed["+isVerifyPassed+"], " + sqPayBtRequest);
		Boolean notifySuccessed = false;
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);// 默认为失败
		try {
			if (isVerifyPassed) {// 验证成功
				// 查询数据库当前的订单情况
				EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(sqPayBtRequest.getBillNo());
				ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
				if ("0000".equals(sqPayBtRequest.getOrderState())) {
					if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0) {
						logger.info("sqPayBt Repeat notify,the order info has been updated .");
						ebuyPayRecord.setPayErrInfo("sqPayBt Repeat notify,the order info has been updated");
					} else {
						ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);  // 标记为支付成功
						paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.SqPay);// 支付成功处理
						// 写入补贴数据
						boolean isTotalOrder = (ebuyOrder.getType().compareTo(EbuyDict.EbuyOrder_Type.Total_Property_Bill) == 0);
						insertSqPayBt(ebuyOrder.getId(), isTotalOrder, sqPayBtRequest.getBillNo(), sqPayBtRequest.getDiscountAmount());
					}
					notifySuccessed = true;
				} else {
					ebuyPayRecord.setPayErrInfo("交易结果:" + sqPayBtRequest.getOrderState()+"【"+getOrderState(sqPayBtRequest.getOrderState())+"】");
				}
			} else {// 验证失败
				ebuyPayRecord.setPayErrInfo("签名验证失败,sqPayBtVerifyResponse=" + sqPayBtRequest);
			}
		} catch (Exception e) {
			logger.info("sqPayBt payNotify cause Exception " + e.getMessage(), e);
			ebuyPayRecord.setPayErrInfo(e.getMessage());
		} finally {
			try {
				String nowTime = dualDao.getNowTime();
				ebuyPayRecord.setOrderNo(sqPayBtRequest.getBillNo());
				ebuyPayRecord.setPayDesc(sqPayBtRequest.getRemark());
				ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.SqPay);
				ebuyPayRecord.setPayResultInfo(sqPayBtRequest.toString());
				ebuyPayRecord.setPayTime(nowTime);
				
				boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
				if(!isSuccess){
					logger.info("sqPayBt payNotify failed,resCount is " + isSuccess);
				}
			} catch (Exception e2) {
				logger.error("sqPayBt payNotify cause error" + e2.getMessage(), e2);
			}
			logger.info("sqPayBt payNotify end.");
		}
		return notifySuccessed;
	}
	
	/**
	 * 支付回调
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	@Override
	public boolean payNotifyTest(SqPayBtResponse sqPayBtRequest) {
		Boolean notifySuccessed = false;
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);// 默认为失败
		try {
			// 查询数据库当前的订单情况
			EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(sqPayBtRequest.getBillNo());
			ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
			if ("0000".equals(sqPayBtRequest.getOrderState())) {
				if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0) {
					logger.info("sqPayBt Repeat notify,the order info has been updated .");
					ebuyPayRecord.setPayErrInfo("sqPayBt Repeat notify,the order info has been updated");
				} else {
					ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);  // 标记为支付成功
					paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.SqPay);// 支付成功处理
					// 写入补贴数据
					boolean isTotalOrder = (ebuyOrder.getType().compareTo(EbuyDict.EbuyOrder_Type.Total_Property_Bill) == 0);
					insertSqPayBt(ebuyOrder.getId(), isTotalOrder, sqPayBtRequest.getBillNo(), sqPayBtRequest.getDiscountAmount());
				}
				notifySuccessed = true;
			} else {
				ebuyPayRecord.setPayErrInfo("交易结果:" + sqPayBtRequest.getOrderState()+"【"+getOrderState(sqPayBtRequest.getOrderState())+"】");
			}
		} catch (Exception e) {
			logger.info("sqPayBt payNotify cause Exception " + e.getMessage(), e);
			ebuyPayRecord.setPayErrInfo(e.getMessage());
		} finally {
			try {
				String nowTime = dualDao.getNowTime();
				ebuyPayRecord.setOrderNo(sqPayBtRequest.getBillNo());
				ebuyPayRecord.setPayDesc(sqPayBtRequest.getRemark());
				ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.SqPay);
				ebuyPayRecord.setPayResultInfo(sqPayBtRequest.toString());
				ebuyPayRecord.setPayTime(nowTime);
				
				boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
				if(!isSuccess){
					logger.info("sqPayBt payNotify failed,resCount is " + isSuccess);
				}
			} catch (Exception e2) {
				logger.error("sqPayBt payNotify cause error" + e2.getMessage(), e2);
			}
			logger.info("sqPayBt payNotify end.");
		}
		return notifySuccessed;
	}

	/**
	 * 支付成功
	 * @param orderId
	 * @param payMethod
	 */
	@Override
	public void paySuccessOperateComm(BigInteger orderId, Integer payMethod) {
		commPayService.paySuccessOperate(orderId,payMethod);
	}

	/**
	 * 根据Id查询订单信息
	 * @param userId
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderPayInfo getOrderPayInfoById(BigInteger userId,
			BigInteger orderId) {
		return commPayService.getOrderPayInfoById(userId, orderId);
	}
	
	/**
	 * 签名校验
	 * 
	 * @param sqPayBtRequest
	 * @return
	 */
	@Override
	public boolean sign(SqPayBtResponse sqPayBtRequest){
		// 双乾公钥
		String sqPubicKey    = sysParamManager.getSysParaValue(SysParamKey.SQ_PAY_BT_SQ_PUBLIC_KEY);
		
		/**
		 * 此处byte[] dataBytes生成的顺序不能变，即map.put(k,v)的顺序
		 */
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("MerNo", sqPayBtRequest.getMerNo());
		map.put("OrderState", sqPayBtRequest.getOrderState());
		map.put("Amount", sqPayBtRequest.getAmount());
		map.put("UserNo", sqPayBtRequest.getUserId());
		map.put("PayAmount", sqPayBtRequest.getPayAmount());
		map.put("StateExplain", sqPayBtRequest.getStateExplain());
		map.put("Remark", sqPayBtRequest.getRemark());
		map.put("DiscountAmount", sqPayBtRequest.getDiscountAmount());
		map.put("BillNo", sqPayBtRequest.getBillNo());
		
		String data = JSONObject.toJSONString(map);
		boolean signFlag = false;
		try {
			byte[] dataBytes = data.getBytes(PayConfigConstant.CHARTSET_UTF_8);
			// 签名校验
			signFlag = SqPayRSAUtils.verify(dataBytes, sqPubicKey, sqPayBtRequest.getSign());
		} catch (Exception e) {
			logger.error("signAndDecrypt error", e);
		} 
		
		return signFlag;
	}
	
	/**
	 * 写入双乾支付优惠补贴
	 * 
	 * @param orderId
	 * @param billNo
	 * @param amountBt
	 */
	private void insertSqPayBt(BigInteger orderId, boolean isTotalOrder, String billNo, String amountBt){
		if(amountBt!=null && amountBt.trim().length()>0){
			try {
				Double bt_yuan = Double.parseDouble(amountBt.trim());
				if(bt_yuan>0){
					List<SqPayBtInfoDto> sqPayBtInfos = null;
					if(isTotalOrder){
						sqPayBtInfos = getSqPayBtInfos(orderId, bt_yuan);
					} else {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("billNo", billNo);
						List<EbuyPrepaySqLog> ebuyPrepaySqLog = ebuyPrepaySqLogBaseDao.selectEbuyPrepaySqLogByCondition(paramMap, false);
						
						SqPayBtInfoDto sqPayBtInfo = new SqPayBtInfoDto();
						sqPayBtInfo.setOrderId(orderId);
						sqPayBtInfo.setAmountBt(bt_yuan*100);
						sqPayBtInfo.setOrderType(ebuyPrepaySqLog.get(0).getOrderType());
						
						sqPayBtInfos= new ArrayList<SqPayBtInfoDto>();
						sqPayBtInfos.add(sqPayBtInfo);
					}
					
					new Thread(new SqPayBtRunnable(sqPayBtInfos)).start();
				}
			} catch (Exception e) {
				logger.info("SqpayService.insertSqPayBt[orderId="+orderId+"][billNo="+billNo+"][amountBt="+amountBt+"]", e);
			}
		}
	}
	
	/**
	 * 物业一键缴费子账单双乾优惠处理
	 * @param totalOrderId
	 * @param totalAmountBt 补贴金额（单位：元）
	 * @return
	 */
	private List<SqPayBtInfoDto> getSqPayBtInfos(BigInteger totalOrderId, Double totalAmountBt){
		List<SqPayBtInfoDto> sqPayBtInfos= new ArrayList<SqPayBtInfoDto>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentId", totalOrderId);
		List<EbuyOrderRelation> ebuyOrderRelations = ebuyOrderRelationBaseDao.selectEbuyOrderRelationByCondition(paramMap, false);
		List<BigDecimal> subAmts = new ArrayList<BigDecimal>();
		for(EbuyOrderRelation ebuyOrderRelation : ebuyOrderRelations){
			subAmts.add(BigDecimal.valueOf(ebuyOrderRelation.getSubAmount()));
		}
		
		BigDecimal totalBt = BigDecimal.valueOf(totalAmountBt).multiply(BigDecimal.valueOf(100L));// 单位：分
		List<BigDecimal> totalBts = MoneyUtil.getSubMoney(null, subAmts, totalBt);
		
		for(int i=0, size=ebuyOrderRelations.size(); i<size; i++){
			EbuyOrderRelation eor = ebuyOrderRelations.get(i);
			
			SqPayBtInfoDto sqPayBtInfo= new SqPayBtInfoDto();
			sqPayBtInfo.setOrderId(eor.getSubId());
			sqPayBtInfo.setOrderType(eor.getSubType());
			sqPayBtInfo.setAmountBt(totalBts.get(i).doubleValue());
			
			sqPayBtInfos.add(sqPayBtInfo);
		}
		
		// 总订单处理
		SqPayBtInfoDto sqPayBtInfo = new SqPayBtInfoDto();
		sqPayBtInfo.setOrderId(totalOrderId);
		sqPayBtInfo.setAmountBt(totalAmountBt*100);
		sqPayBtInfo.setOrderType(EbuyDict.EbuyOrder_Type.Total_Property_Bill);
		sqPayBtInfos.add(sqPayBtInfo);
		
		return sqPayBtInfos;
	}
	
	/**
	 * <p>获取交易状态</p>
	 * 0000	交易成功<br>
	 * 0001	参数错误<br>
	 * 0002	短信验证码错误<br>
	 * 0003	商户信息错误<br>
	 * 0004	交易金额超出单笔限额<br>
	 * 0005	超出商户日交易限额<br>
	 * 0006	风控校验失败<br>
	 * 0007	验签失败<br>
	 * 0008	交易失败<br>
	 * 0009	待处理<br>
	 * 0010	其它错误
	 * 
	 * @param orderState
	 * @return
	 */
	private final String getOrderState(String orderState) {
		if ("0000".equals(orderState)) {
			return "交易成功";
		} else if ("0001".equals(orderState)) {
			return "参数错误";
		} else if ("0002".equals(orderState)) {
			return "短信验证码错误";
		} else if ("0003".equals(orderState)) {
			return "商户信息错误";
		} else if ("0004".equals(orderState)) {
			return "交易金额超出单笔限额";
		} else if ("0005".equals(orderState)) {
			return "超出商户日交易限额";
		} else if ("0006".equals(orderState)) {
			return "风控校验失败";
		} else if ("0007".equals(orderState)) {
			return "验签失败";
		} else if ("0008".equals(orderState)) {
			return "交易失败";
		} else if ("0009".equals(orderState)) {
			return "待处理";
		} else if ("0010".equals(orderState)) {
			return "其它错误";
		}
		return "未知";
	}

	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}

	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}

	public void setUserBaseDao(IUserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setEbuyorderDao(IEbuyorderDao ebuyorderDao) {
		this.ebuyorderDao = ebuyorderDao;
	}

	public void setEbuyPrepaySqLogBaseDao(
			IEbuyPrepaySqLogBaseDao ebuyPrepaySqLogBaseDao) {
		this.ebuyPrepaySqLogBaseDao = ebuyPrepaySqLogBaseDao;
	}

	public void setCommPayService(ICommPayService commPayService) {
		this.commPayService = commPayService;
	}

	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}

	public void setCommEbuyPayRecordService(
			CommEbuyPayRecordService commEbuyPayRecordService) {
		this.commEbuyPayRecordService = commEbuyPayRecordService;
	}

	public void setEbuyOrderRelationBaseDao(
			IEbuyOrderRelationBaseDao ebuyOrderRelationBaseDao) {
		this.ebuyOrderRelationBaseDao = ebuyOrderRelationBaseDao;
	}
	
}
