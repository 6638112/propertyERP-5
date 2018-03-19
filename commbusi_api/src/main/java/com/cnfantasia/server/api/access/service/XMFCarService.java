package com.cnfantasia.server.api.access.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.XMFCarDict;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.MFMonthCarNotifyParam;
import com.cnfantasia.server.api.access.entity.MFMonthCarPayLog;
import com.cnfantasia.server.api.access.entity.MFMonthCarType;
import com.cnfantasia.server.api.access.entity.MFParkingRecord;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.XmfResponse;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.carMsgTask.dao.CarXMFDao;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.carXmfMsg.dao.ICarXmfMsgBaseDao;
import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 小蜜蜂车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 下午4:23:47
 */
@Service("xmfCarService")
public class XMFCarService extends AbstractCarService implements ICarService {
	private static final Logger logger = Logger.getLogger(XMFCarService.class);
	@Resource
	private ICarXmfMsgBaseDao carXmfMsgBaseDao;
	@Resource
	private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private CarXMFDao carXMFDao;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private ICarNumPayLogBaseService carNumPayLogBaseService;
	@Resource
	private IAccessService accessService;
	/**carTypeId, carType*/
	private static Map<String, MFMonthCarType> monthCarTypeMap = null;
	
	@Override
	public void init() {
		monthCarTypeMap = new ConcurrentHashMap<String, MFMonthCarType>();
	}
	
	@Override
	public String getCode() {
		return AccessDict.Code.xiaomifeng.toString();
	}
	
	/**
	 * 根据停车场名称获取对应的解放区gbId
	 * @param ploname
	 * @return
	 */
	public BigInteger getGbIdByPlotName(String ploname) {
		if (StringUtils.isNotBlank(ploname)) {
			List<HtCarGb> htCarGbList = getHtCarGbList();
			for (HtCarGb htCarGb : htCarGbList) {
				if (ploname.equals(htCarGb.getPlotName())) {
					return htCarGb.gettGroupBuildingFId();
				}
			}
		}

		return null;
	}
	
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(4);
		parameter.put("carnumber", plateNo);
		String comid = getComid(gbId);
		parameter.put("comid", comid);
		parameter.put("pageIndex", "1");
		parameter.put("pageSize", "1");
		
		String response = httpGetMF(parameter, XMFCarDict.Method.GET_CARDLIST_BY_CARNUM);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if (StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray outList = xmfResponse.getOutList();
				if (null != outList && outList.size()>0) {
					JSONObject jsonObject = outList.getJSONObject(0);
					if (XMFCarDict.State.SUCCESS.equals(jsonObject.getString("state"))) {
						if(StringUtils.isBlank(jsonObject.getString("datalist"))) {
							bexception.setErrorMsg("暂未查询到车辆信息！");
							throw bexception;
						}
						JSONArray jsonArray = jsonObject.getJSONArray("datalist");
						if(jsonArray!=null && jsonArray.size()>0) {
							JSONObject json = jsonArray.getJSONObject(0);
							MonthCarInfo monthCarInfo = new MonthCarInfo(true);
							long expire = json.getLongValue("enddate");
							monthCarInfo.setExpire(expire);
							monthCarInfo.setCarId(json.getString("id"));
							String carTypeId = json.getString("cardtypeid");
							monthCarInfo.setCarTypeId(carTypeId);
							monthCarInfo.setLock(false);
							MFMonthCarType  mfMonthCarType = getMFMonthCarTypeByGbId(comid, carTypeId);
							long amt = BigDecimalUtil.mul100(mfMonthCarType.getUnitPrice()).longValue();
							monthCarInfo.setAmt(amt);
							monthCarInfo.setRealAmt(amt);
							
							return monthCarInfo;
						}
					} else {
						bexception.setErrorMsg(jsonObject.getString("message"));
						throw bexception;
					}
				}
			} else {
				bexception.setErrorMsg(xmfResponse.getMessage());
				throw bexception;
			}
		}
		
		bexception.setErrorMsg("远程获取数据失败！");
		throw bexception;
	}

	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog) {
		CarXmfMsg carXmfMsg = updateXMFPayStatus(carNumPayLog.getId(), carNumPayLog.getFee(), true, carNumList.gettGroupBuildingFId(), carNumList.gettCarNum());
		notifyMonthCar(carXmfMsg, carNumList.gettGroupBuildingFId(), carNumPayLog);
	}
	
	/**
	 * 小蜜蜂车禁临停车缴费成功推送
	 * @param carXmfMsg
	 * @param plotId
	 * @param carNumPayLog
	 */
	@Transactional
	public void notifyMonthCar(CarXmfMsg carXmfMsg, BigInteger plotId, CarNumPayLog carNumPayLog){
		MFMonthCarNotifyParam param = new MFMonthCarNotifyParam();
		param.setCardnum(carNumPayLog.getPayNum().toString());
		
		String comid = getComid(plotId);
		param.setComid(comid);
		param.setCardid(carXmfMsg.getXmfCarId());
		param.setPaymode(XMFCarDict.PayMode.XIAN_JIN);
		param.setPaytype(XMFCarDict.RechargeType.XU_FEI);
		BigDecimal totalprice = BigDecimalUtil.div100(BigDecimal.valueOf(carNumPayLog.getFee()));
		param.setTotalprice(totalprice);
		
		long startdate = DateUtils.convertStrToDate(carNumPayLog.getPayStartDate()).getTime();
		param.setStartdate(startdate);
		long enddate = DateUtils.convertStrToDate(carNumPayLog.getPayEndDate()).getTime();
		param.setEnddate(enddate);
		
		boolean isSuccess = sendNotifyMonthCarPaySuccess(param);
		if(isSuccess){
			carXmfMsg.setPushStatus(1);
		}
		int times = (carXmfMsg.getPushTimes()==null?1:carXmfMsg.getPushTimes()) + 1;
		carXmfMsg.setPushTimes(times);
		carXmfMsg.setSendTime(CarUtil.getSendTime(times));
		carXmfMsg.setSys0UpdTime(DateUtils.getCurrentDate());
		carXmfMsgBaseDao.updateCarXmfMsg(carXmfMsg);
	}

	/**
	 * 月卡车缴费成功后通知
	 * @param orderId 小蜜蜂方订单号
	 * @param discountAmount 优惠金额（元）
	 * @param totalAmount 用户付款金额（元）
	 * @return
	 */
	private boolean sendNotifyMonthCarPaySuccess(MFMonthCarNotifyParam param){
		//parameter={"cardid":"20170531161304548317957845112111","cardnum":"1","comid":"000000109","enddate":"1510588800000","paymode":"1","paytype":"1","startdate":"1507996800000","totalprice":"100"}
		String response = httpGetMF(JSON.toJSONString(param), XMFCarDict.Method.ADD_CARD_ORDER_LIST);
		if(StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if(XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())){
				JSONArray jsonArray = xmfResponse.getOutList();
	    		if(jsonArray==null || jsonArray.size()==0){
	    			return false;
	    		} else {
	    			JSONObject jsonObject = jsonArray.getJSONObject(0);
					return XMFCarDict.State.SUCCESS.equals(jsonObject.getString("state"));
	    		}
			}
		}
		
		
		return false;
	}
	
	/**
	 * 查询临停车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	@Override
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo) {
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(3);
		parameter.put("carnumber", plateNo);
		parameter.put("amount", 0);
		parameter.put("time", 0);
		
		String response = httpGetMF(parameter, XMFCarDict.Method.GET_ORDER);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if (StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray outList = xmfResponse.getOutList();
				if (null != outList && outList.size()>0) {
					JSONObject jsonObject = outList.getJSONObject(0);
					if (XMFCarDict.State.SUCCESS.equals(jsonObject.getString("state"))
							&& XMFCarDict.IsParking.YES.equals(jsonObject.getString("isparking"))) {
						String ploname = jsonObject.getString("ploname");
						gbId = getGbIdByPlotName(ploname);
						if(gbId==null){
        					BusinessRuntimeException businessRuntimeException = new BusinessRuntimeException();
        					businessRuntimeException.setErrorMsg("["+ploname+"]暂未开通在线缴费功能！");
        					throw businessRuntimeException;
            			}
						
						TempCarInfo tempCarInfo = new TempCarInfo(true);
						BigDecimal amt = jsonObject.getBigDecimal("receivableprice");
						amt = BigDecimalUtil.mul100(amt);
						tempCarInfo.setAmt(amt.longValue());
						tempCarInfo.setRealAmt(amt.longValue());
						
						BigDecimal paidFee = jsonObject.getBigDecimal("prepaymentamount");
						paidFee = BigDecimalUtil.mul100(paidFee);
						tempCarInfo.setPaidFee(paidFee.longValue());
						
						BigDecimal discountFee = jsonObject.getBigDecimal("discountamount");
						discountFee = BigDecimalUtil.mul100(discountFee);
						tempCarInfo.setDiscountFee(discountFee.longValue());
						
						tempCarInfo.setEnterTime(jsonObject.getLongValue("intime"));
						tempCarInfo.setOrderId(jsonObject.getString("orderid"));
						tempCarInfo.setPlotName(jsonObject.getString("ploname"));
						tempCarInfo.setGbId(gbId);

						return tempCarInfo;
					} else {
						String resultmsg = jsonObject.getString("resultmsg");
						if(StringUtils.isNotBlank(resultmsg)) {
							bexception.setErrorMsg(resultmsg);
							throw bexception;
						}
					}
				}
			} else {
				bexception.setErrorMsg(xmfResponse.getMessage());
				throw bexception;
			}
		}

		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}

	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo){
		CarXmfMsg carXmfMsg = updateXMFPayStatus(carNumPayLog.getId(), null, false, null, null);
		notifyXMFTempCar(carXmfMsg);
	}
	
	/**
	 * 小蜜蜂车禁临停车缴费成功推送
	 * @param cnplId
	 */
	@Transactional
	public void notifyXMFTempCar(CarXmfMsg carXmfMsg){
		boolean isSuccess = sendNotifyTempCarPaySuccess(carXmfMsg.gettXmfOrderId(), carXmfMsg.getDiscountAmount(), carXmfMsg.getFee());
		if(isSuccess){
			carXmfMsg.setPushStatus(1);
		}
		int times = (carXmfMsg.getPushTimes()==null?1:carXmfMsg.getPushTimes()) + 1;
		carXmfMsg.setPushTimes(times);
		carXmfMsg.setSendTime(CarUtil.getSendTime(times));
		carXmfMsg.setSys0UpdTime(DateUtils.getCurrentDate());
		carXmfMsgBaseDao.updateCarXmfMsg(carXmfMsg);
	}
	
	private CarXmfMsg updateXMFPayStatus(BigInteger cnplId, Long fee, boolean isMonthCar, BigInteger monthCarGbId, String monthCarNum){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNumPayLogFId", cnplId);
		List<CarXmfMsg> carXmfMsgList = carXmfMsgBaseDao.selectCarXmfMsgByCondition(paramMap, true);
		CarXmfMsg carXmfMsg = null;
		if(isMonthCar && (carXmfMsgList==null || carXmfMsgList.size()==0)) {// 停车宝处月卡
			carXmfMsg = new CarXmfMsg();
			carXmfMsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_xmf_msg));
			carXmfMsg.settCarNumPayLogFId(cnplId);
			carXmfMsg.setDiscountAmount(0L);
			carXmfMsg.setCarType(XMFCarDict.CarType.MONTH_CAR);
			carXmfMsg.setFee(fee);
			MonthCarInfo monthCarInfo = getOneMonthCarInfo(monthCarGbId, monthCarNum);
			carXmfMsg.setXmfCarId(monthCarInfo.getCarId());
			carXmfMsg.setPayStatus(1);
			carXmfMsg.setPushStatus(0);
			int times = 0;
			carXmfMsg.setPushTimes(times);
			carXmfMsg.setSendTime(CarUtil.getSendTime(times));
			carXmfMsg.setSys0DelState(0);
			carXmfMsg.setSys0AddTime(DateUtils.getCurrentDate());

			carXmfMsgBaseDao.insertCarXmfMsg(carXmfMsg);
		} else {
			carXmfMsg = carXmfMsgList.get(0);
			carXmfMsg.setPayStatus(1);
			carXmfMsgBaseDao.updateCarXmfMsg(carXmfMsg);
		}
		
		return carXmfMsg;
	}
	
	/**
	 * 临停车缴费成功后通知
	 * @param orderId 小蜜蜂方订单号
	 * @param discountAmount 优惠金额（元）
	 * @param totalAmount 用户付款金额（元）
	 * @return
	 */
	public boolean sendNotifyTempCarPaySuccess(String orderId, long discountAmount, long totalAmount){
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(4);
		parameter.put("orderid", orderId);
		parameter.put("amount", BigDecimalUtil.div100(discountAmount).doubleValue());// 优惠金额（元）
		parameter.put("time", 0);                                                    // 优惠时间（毫秒）
		parameter.put("payAmount", BigDecimalUtil.div100(totalAmount).doubleValue());// 用户付款金额（元）
		
		String response = httpGetMF(parameter, XMFCarDict.Method.PAY_ORDER);
		if(StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray jsonArray = xmfResponse.getOutList();
	    		if(jsonArray==null || jsonArray.size()==0){
	    			return false;
	    		} else {
	    			JSONObject json = jsonArray.getJSONObject(0);
					return XMFCarDict.State.SUCCESS.equals(json.getString("state"));
	    		}
			}
			
		}
		
		return false;
	}
	
	/**
	 * 获取月卡类型列表
	 *
	 * @param comid 物业ID
	 */
	public List<MFMonthCarType> getCardTypeList(String comid){
		//parameter={"comid":"000000109","typestatus":"1"}
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(2);
		parameter.put("comid", comid);
		parameter.put("typestatus", "1");
		
		String response = httpGetMF(parameter, XMFCarDict.Method.GET_CARDTYPE_LIST);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if (StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray outList = xmfResponse.getOutList();
				if (null != outList && outList.size()>0) {
					JSONObject json = outList.getJSONObject(0);
					if(XMFCarDict.State.SUCCESS.equals(json.getString("state"))) {
						if(StringUtils.isBlank(json.getString("datalist"))) {
							return null;
						}
						JSONArray jsonArray = json.getJSONArray("datalist");
						
						List<MFMonthCarType> typeList = new ArrayList<MFMonthCarType>();
						for(int i=0; i<jsonArray.size(); i++) {
							JSONObject jsonTemp = jsonArray.getJSONObject(i);

							MFMonthCarType mfMonthCarType = new MFMonthCarType();
							mfMonthCarType.setId(jsonTemp.getString("id"));
							mfMonthCarType.setTypeName(jsonTemp.getString("typename"));
							mfMonthCarType.setUnit(jsonTemp.getString("unit"));
							mfMonthCarType.setMaxMonthNum(jsonTemp.getIntValue("maxnum"));
							int unitNum = jsonTemp.getIntValue("number");
							mfMonthCarType.setUnitNum(unitNum);
							
							BigDecimal unitPrice = jsonTemp.getBigDecimal("unitprice");
							unitPrice = BigDecimalUtil.div(unitPrice, BigDecimal.valueOf(unitNum), 2);
							mfMonthCarType.setUnitPrice(unitPrice);
							
							typeList.add(mfMonthCarType);
						}
						
						return typeList;
					} else {
						bexception.setErrorMsg(json.getString("message"));
						throw bexception;
					}
				}
			} else {
				bexception.setErrorMsg(xmfResponse.getMessage());
				throw bexception;
			}
		}
		
		bexception.setErrorMsg("远程获取数据失败！");
		throw bexception;
	}
	
	public String getComid(BigInteger gbId){
		List<HtCarGb> htCarGbList= getHtCarGbList();
		for(HtCarGb htCarGbTmp:htCarGbList){
			if(htCarGbTmp.gettGroupBuildingFId().compareTo(gbId)==0){
				HtCarGb htCarGb = htCarGbTmp;
				return htCarGb.getComid();
			}
		}
		
		return null;
	}
	
	/**
	 * 根据有效期获取最大可缴费月数
	 * @param expire
	 * @return
	 */
	public static int getPayMaxMonths(String expire) {
		expire = expire.substring(0, 10);
		String expireEnd = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_XMF_EXPIRE_THRESHOLD);
		final int payMaxMonth = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.CAR_XMF_PAY_MAX_MONTH, 3);
		DateTime threeMonthDateTime = DateTime.now().plusMonths(payMaxMonth);

		DateTimeFormatter formatter = DateTimeFormat.forPattern(AccessDict.DateFormat.yyyy_MM_dd);
		DateTime expireThreshold = DateTime.parse(expireEnd, formatter);

		DateTime end = threeMonthDateTime;
		if (expireThreshold.isBefore(threeMonthDateTime)) {
			end = expireThreshold;
		}

		DateTime start = DateTime.parse(expire, formatter);
		int maxMonth = Months.monthsBetween(start, end).getMonths();
		if (maxMonth < 0) {
			maxMonth = 0;
		}

		return maxMonth;
	}

	/**
	 * 可缴费月数
	 * 
	 * @param gbId
	 * @param fee
	 * @param expire
	 * @param carTypeId
	 * @param carFeeTypeList
	 * @return
	 */
	@Override
	public List<CarFeeType> getCarFeeTypeList(BigInteger gbId, Long fee, String expire, String carTypeId, List<CarFeeType> carFeeTypeList) {
		return getCarFeeTypeListByExpire(gbId, expire, carTypeId, carFeeTypeList);
	}
	
	@Override
	public void releaseTaskLock() {
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_CASH_PAYLOG));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_PARKING_RECORD));
	}
	
	@Override
	public void synMonthCarPayLog() {
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_CASH_PAYLOG);
		/**
		 * 此处不用通过事务去枷锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_MONTH_CAR_CASH_PAYLOG);
			if (isAcquireLock) {
				long now = new Date().getTime();
				long starttime = now - XMFCarDict.SynTaskIntervalTime.PAY_LOG;
				List<HtCarGb> htCarGbList = getHtCarGbList();
				for (HtCarGb htCarGb : htCarGbList) {
					BigInteger gbId = htCarGb.gettGroupBuildingFId();
					String comid = htCarGb.getComid();
					dealMonthCarPayLog(gbId, comid, starttime, now, 1);
				}
			}
		} finally {
			if(isAcquireLock) {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}

	@Override
	public void pushMonthCarPayLog() {
		/**
		 * 此处不用通过事务去枷锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.MONTH_CAR_PAY_NOTIFY);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("carType", XMFCarDict.CarType.MONTH_CAR);
				List<CarXmfMsg> carXmfMsgList = carXMFDao.selectCarXmfMsgByCondition(paramMap);

				if(null!=carXmfMsgList && carXmfMsgList.size()>0){
					for(CarXmfMsg carXmfMsg : carXmfMsgList){
						CarNumPayLog carNumPayLog = carNumPayLogBaseService.getCarNumPayLogBySeqId(carXmfMsg.gettCarNumPayLogFId());
						CarNumList carNumList = carNumListBaseDao.selectCarNumListBySeqId(carNumPayLog.gettCarNumId());
						notifyMonthCar(carXmfMsg, carNumList.gettGroupBuildingFId(), carNumPayLog);
					}
				}
			}
		} finally {
			if(isAcquireLock) {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}

	@Override
	public void pushTempCarPayLog() {
		/**
		 * 此处不用通过事务去枷锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.TMP_CAR_PAY_NOTIFY);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("carType", XMFCarDict.CarType.TMP_CAR);
				List<CarXmfMsg> carXmfMsgList = carXMFDao.selectCarXmfMsgByCondition(paramMap);

				List<CarXmfMsg> updateCarXmfMsgList = null;
				if(null!=carXmfMsgList && carXmfMsgList.size()>0){
					updateCarXmfMsgList = new ArrayList<CarXmfMsg>();
					String now = DateUtils.getCurrentDate();
					for(CarXmfMsg carXmfMsg : carXmfMsgList){
						boolean isSuccess = sendNotifyTempCarPaySuccess(carXmfMsg.gettXmfOrderId(), carXmfMsg.getDiscountAmount(), carXmfMsg.getFee());
						if(isSuccess){
							carXmfMsg.setPushStatus(1);
						}
						int times = (carXmfMsg.getPushTimes()==null?1:carXmfMsg.getPushTimes()) + 1;
						carXmfMsg.setPushTimes(times);
						carXmfMsg.setSendTime(CarUtil.getSendTime(times));
						carXmfMsg.setSys0UpdTime(now);
						
						updateCarXmfMsgList.add(carXmfMsg);
					}
				}

				if(null!=updateCarXmfMsgList && updateCarXmfMsgList.size()>0){
					carXmfMsgBaseDao.updateCarXmfMsgBatch(updateCarXmfMsgList);
				}
			}
		} finally {
			if(isAcquireLock) {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}

	@Override
	public void synCarInfo() {
		/**
		 * 此处不用通过事务去枷锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_MONTH_CAR_INFO);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				List<HtCarGb> htCarGbList= getHtCarGbList();
				String now = DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				for(HtCarGb htCarGb:htCarGbList){
					BigInteger gbId = htCarGb.gettGroupBuildingFId();
					paramMap.clear();
					paramMap.put("tGroupBuildingFId",gbId);
					List<CarNumList> carNumLists = carNumListBaseDao.selectCarNumListByCondition(paramMap, true);
					for(CarNumList carNumList:carNumLists){
						try{
							synOneMonthCarInfo(gbId, carNumList, now);
							TimeUnit.MILLISECONDS.sleep(500);
		        		} catch(Exception e){
		        			//logger.error(JSON.toJSONString(carNumList), e);
		        		}
					}
				}
			}
		} finally {
			if(isAcquireLock) {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}

	@Override
	public void synParkingRecord() {
		/**
		 * 此处不用通过事务去枷锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_PARKING_RECORD);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_PARKING_RECORD);
			if(isAcquireLock) {
				long now = new Date().getTime();
				long synStartTime = now-XMFCarDict.SynTaskIntervalTime.PARKING_RECORD;

				List<HtCarGb> allHtCarGbList = getHtCarGbList();
				for(HtCarGb htCarGb:allHtCarGbList){
					String plotId = htCarGb.gettParkid();
					dealSynParkingRecord(htCarGb.gettGroupBuildingFId(), plotId, synStartTime, now, null, now, 1);
					dealSynParkingRecord(htCarGb.gettGroupBuildingFId(), plotId, null, now, synStartTime, now, 1);
				}
			}
		} finally {
			if(isAcquireLock) {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}
	
	/**
	 * 出入车记录处理
	 * @param gbId
	 * @param plotId
	 * @param instarttime
	 * @param outstarttime
	 */
	private void dealSynParkingRecord(BigInteger gbId, String plotId, Long intimefrom, Long intimeto, Long outtimefrom, Long outtimeto, int pageIndex) {
		List<MFParkingRecord> parkingRecordList = getOrderList(plotId, intimefrom, intimeto, outtimefrom, outtimeto, pageIndex);
		if(null!=parkingRecordList && parkingRecordList.size()>0) {
			for(MFParkingRecord parkingRecord:parkingRecordList) {
				if(StringUtils.isBlank(parkingRecord.getOutDate())) {
					accessService.vehIn(parkingRecord.getCarNum(), gbId, parkingRecord.getInDate(), parkingRecord.getCarType(), null);
				} else {
					accessService.vehOut(parkingRecord.getCarNum(), gbId, parkingRecord.getInDate(), parkingRecord.getOutDate(), parkingRecord.getFee(), parkingRecord.getCarType());
				}
			}
			if(parkingRecordList.size()==XMFCarDict.MAX_PAGE_SIZE) {
				dealSynParkingRecord(gbId, plotId, intimefrom, intimeto, outtimefrom, outtimeto, ++pageIndex);
			}
		}
	}
	
	/**
	 * 根据月卡费过期时间计算可缴月份
	 * @param expire
	 * @param comid
	 * @param carTypeId
	 * @param carFeeTypeList
	 * @return
	 */
	public List<CarFeeType> getCarFeeTypeListByExpire(BigInteger gbId, String expire, String carTypeId, List<CarFeeType> carFeeTypeList) {
		if(carFeeTypeList==null) {
			carFeeTypeList = new ArrayList<CarFeeType>();
		}
		int maxMonth = getPayMaxMonths(expire);

		MFMonthCarType carType = getMFMonthCarTypeByGbId(getComid(gbId), carTypeId);
		for (int i = 1; i <= maxMonth; i++) {
			CarFeeType carFeeType = new CarFeeType();
			carFeeType.setFee(carType.getUnitPrice().multiply(BigDecimal.valueOf(i)));
			carFeeType.setNum(i);
			carFeeType.setUnit(carType.getUnit());
			String typeName = StringUtils.EMPTY;
			if (carType.getUnit().contains("月")) {
				typeName = i + "个" + carType.getUnit();
			} else {
				typeName = i + carType.getUnit();
			}
			carFeeType.setTypeName(typeName);

			carFeeTypeList.add(carFeeType);
		}

		return carFeeTypeList;
	}
	
	public MFMonthCarType getMFMonthCarTypeByGbId(String comid, String carTypeId){
		MFMonthCarType monthCarType = monthCarTypeMap.get(carTypeId);
		if(null==monthCarType){// 重新获取
			getMFMonthCarTypeByComidWithRemote(comid);
			monthCarType = monthCarTypeMap.get(carTypeId);
			if(null==monthCarType){
				BusinessRuntimeException bexception = new BusinessRuntimeException();
				bexception.setErrorMsg("远程获取月卡类型失败！");
				throw bexception;
			}
		}
		
		return monthCarType;
	}
	
	/**
	 * 根据gbId远程获取月卡类型列表
	 * 
	 * @param gbId
	 * @return
	 */
	private List<MFMonthCarType> getMFMonthCarTypeByComidWithRemote(String comid){
		List<MFMonthCarType> monthCarTypeList = getCardTypeList(comid);
		if(null!=monthCarTypeList){
			for(MFMonthCarType mFMonthCarType:monthCarTypeList){
				monthCarTypeMap.put(mFMonthCarType.getId(), mFMonthCarType);
			}
		} else {
			BusinessRuntimeException bexception = new BusinessRuntimeException();
			bexception.setErrorMsg("远程获取月卡类型列表失败！");
			throw bexception;
		}
		
		return monthCarTypeList;
	}
	
	/**
	 * 获取停车场列表
	 * @param carNum
	 * @return
	 */
	public JSONObject getPloList() {
		String response = httpGetMF(new HashMap<String, Object>(), XMFCarDict.Method.GET_PLOLIST);
		return JSON.parseObject(response);
	}
	
	public List<MFMonthCarPayLog> getMonthCarPayLogList(String comid, String carNum, long starttime, Long endtime, int pageIndex){
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(7);
		parameter.put("keyvalue", carNum);
		parameter.put("comid", comid);
		parameter.put("starttime", starttime);
		parameter.put("endtime", endtime);
		parameter.put("query", "all");
		parameter.put("pageSize", XMFCarDict.MAX_PAGE_SIZE);//小蜜蜂最大可查询条数为50
		parameter.put("pageIndex", pageIndex);
		
		String response = httpGetMF(parameter, XMFCarDict.Method.GET_CARD_ORDER_LIST);
		if (StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray jsonArray = xmfResponse.getOutList();
				if (jsonArray != null && jsonArray.size() > 0) {
					JSONObject jsonObject = jsonArray.getJSONObject(0);
					if (XMFCarDict.State.SUCCESS.equals(jsonObject.getString("state"))) {
						if(StringUtils.isBlank(jsonObject.getString("datalist"))) {
							return null;
						}
						JSONArray dataJSONArray = jsonObject.getJSONArray("datalist");
						if (dataJSONArray != null && dataJSONArray.size() > 0) {
							List<MFMonthCarPayLog> carPayLogList = new ArrayList<MFMonthCarPayLog>(dataJSONArray.size());
							for (int i = 0; i < dataJSONArray.size(); i++) {
								JSONObject dataJSONObject = dataJSONArray.getJSONObject(i);
								
								MFMonthCarPayLog carPayLog = new MFMonthCarPayLog();
								carPayLog.setCarNum(dataJSONObject.getString("carnumber"));
								carPayLog.setPayNum(dataJSONObject.getLongValue("cardnum"));
								
	    						String payTime = DateUtil.formatSecond.get().format(new Date(dataJSONObject.getLongValue("paytime")));
								carPayLog.setPayTime(payTime);
								
								long totalprice = BigDecimalUtil.mul100(dataJSONObject.getBigDecimal("totalprice")).longValue();
								carPayLog.setTotalPrice(totalprice);
								
	    						String startDate = DateUtil.formatSecond.get().format(new Date(dataJSONObject.getLongValue("startdate")));
								carPayLog.setStartDate(startDate);
								
	    						String endDate = DateUtil.formatSecond.get().format(new Date(dataJSONObject.getLongValue("enddate")));
								carPayLog.setEndDate(endDate);
								
								carPayLogList.add(carPayLog);
							}
							return carPayLogList;
						}
					} else {
						logger.error(jsonObject.getString("message"));
					}
				}
			} else {
				logger.error(xmfResponse.getMessage());
			}
		}
		return null;
	}
	
	/**
	 * 同步月卡线下缴费记录
	 * @param gbId
	 * @param comid
	 * @param starttime
	 * @param endtime
	 * @param pageIndex
	 */
	private void dealMonthCarPayLog(BigInteger gbId, String comid, Long starttime, Long endtime, int pageIndex) {
		List<MFMonthCarPayLog> carPayLogList = null;
		try{
			carPayLogList = getMonthCarPayLogList(comid, null, starttime, endtime, pageIndex);
			if(carPayLogList!=null && carPayLogList.size()>0){
				for(int k=0; k<carPayLogList.size(); k++){
					MFMonthCarPayLog carPayLog = carPayLogList.get(k);
					BigInteger carId = dealNewCarNum(gbId, carPayLog);

					CarNumPayLog carNumPayLog = new CarNumPayLog();
					carNumPayLog.settCarNumId(carId);
					carNumPayLog.setFee(carPayLog.getTotalPrice());
					carNumPayLog.setPayNum(carPayLog.getPayNum());
					carNumPayLog.setPayEndDate(carPayLog.getEndDate());

					int count = carNumPayLogBaseService.getCarNumPayLogCount(MapConverter.toMap(carNumPayLog));
					if(count==0){// 没有记录则新增
						BigInteger cnplId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log);
						carNumPayLog.setId(cnplId);
						carNumPayLog.setStatus(1);
						carNumPayLog.setCouponAmount(0L);
						carNumPayLog.setPushStatus(1);
						carNumPayLog.setPayTime(carPayLog.getPayTime());
						carNumPayLog.setPayStartDate(carPayLog.getStartDate());
						carNumPayLog.setCashStatus(1);
						carNumPayLog.setNeedBill(0);
						carNumPayLog.setSys0AddTime(DateUtils.getCurrentDate());

						carNumPayLogBaseService.insertCarNumPayLog(carNumPayLog);
					}
				}
				if(carPayLogList.size()==XMFCarDict.MAX_PAGE_SIZE) {
					dealMonthCarPayLog(gbId, comid, starttime, endtime, ++pageIndex);
				}
			}
		} catch(Exception e){
			//logger.error(JSON.toJSONString(carNumList), e);
		}
	}
	
	/**
	 * 车牌不存在则新增
	 * @param gbId
	 * @param carPayLog
	 * @return
	 */
	private BigInteger dealNewCarNum(BigInteger gbId, MFMonthCarPayLog carPayLog) {
		BigInteger carId = null;
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("carNum", carPayLog.getCarNum());
    	paramMap.put("gbId", gbId);
    	CarNumList carNumList = accessService.queryCarNumListByCondition(paramMap);
    	if(carNumList==null){
    		carId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list);
			CarNumList insertCarNumList = new CarNumList();
			insertCarNumList.setId(carId);
			insertCarNumList.settCarNum(carPayLog.getCarNum());
			insertCarNumList.setStatus(1);
			insertCarNumList.setFee(carPayLog.getTotalPrice()/carPayLog.getPayNum());
			insertCarNumList.setLockStatus(0);
			insertCarNumList.settGroupBuildingFId(gbId);
			String expireDate = String.valueOf(carPayLog.getEndDate()).trim();
			insertCarNumList.setExpireDate(expireDate);
			insertCarNumList.setSys0AddTime(DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss));
			insertCarNumList.setSys0DelState(0);
			carNumListBaseDao.insertCarNumList(insertCarNumList);
    	} else if(carNumList.getSys0DelState()==1){
    		carId = carNumList.getId();
    		carNumList.setSys0DelState(0);
    		String expireDate = String.valueOf(carPayLog.getEndDate()).trim();
    		carNumList.setExpireDate(expireDate);
    		carNumList.setSys0UpdTime(DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss));
    		carNumListBaseDao.updateCarNumList(carNumList);
    	}
    	return carId;
	}
	
	/**
	 * 获取停车记录
	 * @param carNum
	 * @return
	 */
	public List<MFParkingRecord> getOrderList(String plotId, Long intimefrom, Long intimeto, Long outtimefrom, Long outtimeto, int pageIndex){
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<String, Object>(8);
		parameter.put("ploid", plotId);
		parameter.put("query", "all");
		parameter.put("intimefrom", intimefrom);
		parameter.put("intimeto", intimeto);
		parameter.put("outtimefrom", outtimefrom);
		parameter.put("outtimeto", outtimeto);
		parameter.put("pageSize", XMFCarDict.MAX_PAGE_SIZE);//小蜜蜂最大可查询条数为50
		parameter.put("pageIndex", pageIndex);
		
		String response = httpGetMF(parameter, XMFCarDict.Method.GET_ORDER_LIST);
		if(StringUtils.isNotBlank(response)) {
			XmfResponse xmfResponse = JSON.parseObject(response, XmfResponse.class);
			if (XMFCarDict.State.SUCCESS.equals(xmfResponse.getState())) {
				JSONArray jsonArray = xmfResponse.getOutList();
				if(jsonArray!=null && jsonArray.size()>0) {
					JSONObject jsonObject = jsonArray.getJSONObject(0);
					if(XMFCarDict.State.SUCCESS.equals(jsonObject.getString("state"))) {
						if(StringUtils.isBlank(jsonObject.getString("datalist"))) {
							return null;
						}
						JSONArray dataJSONArray = jsonObject.getJSONArray("datalist");
						if(dataJSONArray!=null && dataJSONArray.size()>0) {
    						List<MFParkingRecord> parkingRecordList = new ArrayList<MFParkingRecord>(dataJSONArray.size());
							for(int i=0; i<dataJSONArray.size(); i++){
								JSONObject dataJSONObject = dataJSONArray.getJSONObject(i);
								
								MFParkingRecord parkingRecord = new MFParkingRecord();
    							parkingRecord.setCarNum(dataJSONObject.getString("carplate"));
    							BigDecimal actualpay = dataJSONObject.getBigDecimal("actualpay");
    							BigDecimal shouldpay = dataJSONObject.getBigDecimal("shouldpay");
    							if(actualpay!=null) {
        							parkingRecord.setFee(BigDecimalUtil.mul100(actualpay).longValue());
    							} else if(shouldpay!=null) {
        							parkingRecord.setFee(BigDecimalUtil.mul100(shouldpay).longValue());
    							}
    							
	    						String intime = DateUtil.formatSecond.get().format(new Date(dataJSONObject.getLongValue("intime")));
    							parkingRecord.setInDate(intime);
    							
        						String outtime = DateUtil.formatSecond.get().format(new Date(dataJSONObject.getLongValue("outtime")));
    							parkingRecord.setOutDate(outtime);
    							if("月卡".equals(dataJSONObject.getString("paymentwayname"))){
    								parkingRecord.setCarType(AccessDict.JFQCarType.MONTH_CAR);
    							} else {
    								parkingRecord.setCarType(AccessDict.JFQCarType.UN_MONTH_CAR);
    							}
    							
    							parkingRecordList.add(parkingRecord);
							}
							return parkingRecordList;
						}
					} else {
						logger.error(jsonObject.getString("message"));
					}
				}
			} else {
				logger.error(xmfResponse.getMessage());
			}
		}
		
		return null;
	}
	
	/**
	 * get请求蜜蜂车禁接口
	 * 
	 * @param parameter
	 * @param methodName
	 * @return
	 */
	public static String httpGetMF(Object parameter, String methodName){
		String gcode = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.MF_CAR_GCODE);
		String serverUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.MF_CAR_SERVER_URL);
		String requestKey = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.MF_CAR_REQUEST_KEY);
		
		// 参数名（自然排序）排序
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("clientType", "java");
		paramMap.put("method", "doActivity");
		paramMap.put("module", "activity");
		paramMap.put("service", "Entrance");
		paramMap.put("typeflag", "ThirdQuery");
		paramMap.put("methodname", methodName);
		paramMap.put("gcode", gcode);
		paramMap.put("ve", 1);
		paramMap.put("ms", System.currentTimeMillis());
		try {
			if(parameter instanceof Map){
				paramMap.put("parameter", URLEncoder.encode(JSON.toJSONString(parameter), "utf-8"));
			} else {
				paramMap.put("parameter", URLEncoder.encode(parameter.toString(), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("parameter encode error", e);
		}
  	    
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry:paramMap.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		String params = sb.substring(0, sb.length()-1);
		// requestKey不参与排序
		String urlParam = params.concat("&requestKey=").concat(requestKey);
		String sign = Md5Util.MD5(urlParam);
		sign = sign.toLowerCase();// 小蜜蜂要求sign为小写
		
		//logger.info("[httpGetMF]serverUrl="+serverUrl+"; params==>"+params+"; sign="+sign);
		
		String url = serverUrl + "?" + params +"&sign="+sign;
		HttpUtil httpUtil = new HttpUtil();
		
		String result = null;
		try {
			result = httpUtil.get(url);
		} catch (HttpException e) {
			logger.error("HttpException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
		
		logger.info("[httpGetMF][url="+url+"][result=" + result+"]");
		return result;
	}
	
	/**==============================================================**/
	public void synMonthCarPayLog20180103() {
		final long now = new Date().getTime();
		long end = DateUtils.convertStrToDate("2018-01-18 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
		if(now<end) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					logger.info("synMonthCarPayLog20180103 start");
					String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_CASH_PAYLOG+"_xx");
					boolean isAcquireLock = false;
					try {
						isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_MONTH_CAR_CASH_PAYLOG*20);
						if (isAcquireLock) {
							long starttime = DateUtils.convertStrToDate("2018-01-03 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
							long endTime = DateUtils.convertStrToDate("2018-01-17 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
							List<HtCarGb> htCarGbList = getHtCarGbList();
							for (HtCarGb htCarGb : htCarGbList) {
								BigInteger gbId = htCarGb.gettGroupBuildingFId();
								String comid = htCarGb.getComid();
								dealMonthCarPayLog(gbId, comid, starttime, endTime, 1);
							}
						}
					} finally {
						if(isAcquireLock) {
							RedisCacheHandler.Lock.releaseLock(lockKey);
						}
					}
					logger.info("synMonthCarPayLog20180103 ends");
				}
			}).start();
		}
	}
	
	public void synParkingRecord20180103() {
		final long now = new Date().getTime();
		long end = DateUtils.convertStrToDate("2018-01-18 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
		if(now<end) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					logger.info("synParkingRecord20180103 start");
					String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_PARKING_RECORD+"_xx");
					boolean isAcquireLock = false;
					try {
						isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_PARKING_RECORD*60);
						if(isAcquireLock) {
							long starttime = DateUtils.convertStrToDate("2018-01-03 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
							long endTime = DateUtils.convertStrToDate("2018-01-17 14:00:00", AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss).getTime();
							List<HtCarGb> allHtCarGbList = getHtCarGbList();
							for(HtCarGb htCarGb:allHtCarGbList){
								String plotId = htCarGb.gettParkid();
								dealSynParkingRecord(htCarGb.gettGroupBuildingFId(), plotId, null, null, starttime, endTime, 1);
							}
						}
					} finally {
						if(isAcquireLock) {
							RedisCacheHandler.Lock.releaseLock(lockKey);
						}
					}
					logger.info("synParkingRecord20180103 end");
				}
			}).start();
		}
	}
}
