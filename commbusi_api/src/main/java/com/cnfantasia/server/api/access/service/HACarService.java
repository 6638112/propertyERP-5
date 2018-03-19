package com.cnfantasia.server.api.access.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.HACarDict;
import com.cnfantasia.server.api.access.constant.XMFCarDict;
import com.cnfantasia.server.api.access.entity.HAMonthCarNotifyParam;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.carMsgTask.dao.CarHADao;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carHuaanMsg.dao.ICarHuaanMsgBaseDao;
import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

@Service("haCarService")
public class HACarService extends AbstractCarService implements ICarService {

	private static final Logger logger = Logger.getLogger(HACarService.class);
	@Resource
	private ICarHuaanMsgBaseDao carHuaanMsgBaseDao;	
	@Resource
	private ICarNumPayLogBaseDao carNumPayLogBaseDao;
	@Resource
	private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private IAccessService accessService;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private ICarNumPayLogBaseService carNumPayLogBaseService;
	@Resource
	private CarHADao carHADao;
    private static final long MILS = 1000L;// 毫秒
	
	@Override
	public String getCode() {
		return AccessDict.Code.huaan.toString();
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		Map<String, Object> parameter = new HashMap<String, Object>(2);
		String smallareaNumber = getSmallareaNumberByGbId(gbId);
    	parameter.put("smallarea_number", smallareaNumber);
    	parameter.put("plate_number", plateNo);

        String response = httpPostHA(parameter, HACarDict.Method.GET_MONTH_CAR_FEE);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if(StringUtils.isNotBlank(response)) {
			JSONObject jsonObject = JSON.parseObject(response);
			if(HACarDict.State.SUCCESS.equals(jsonObject.getString("status"))) {
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				if(jsonArray!=null && jsonArray.size()>0) {
					JSONObject jsonInfo = jsonArray.getJSONObject(0);
					if(HACarDict.MonthCarPayStatus.YES.equals(jsonInfo.getString("car_status"))) {
						MonthCarInfo monthCarInfo = new MonthCarInfo(true);
						long amt = BigDecimalUtil.mul100(jsonInfo.getBigDecimal("currency_amount")).longValue();
						monthCarInfo.setAmt(amt);
						monthCarInfo.setRealAmt(amt);
						
						long expire = jsonInfo.getLongValue("end_time")*MILS;
						monthCarInfo.setExpire(expire);
						monthCarInfo.setLock(false);
						
						return monthCarInfo;
					} else {
						bexception.setErrorMsg("该车辆不允许继续缴费！");
            			throw bexception;
					}
				}
			} else {
				bexception.setErrorMsg(jsonObject.getString("msg"));
				throw bexception;
			}
		}
		
		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}

	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog){
		CarHuaanMsg carHuaanMsg = updateHAPayStatus(carNumPayLog.getId(), carNumPayLog.getFee(), true, carNumList.gettCarNum());
		notifyMonthCar(carHuaanMsg, carNumList, carNumPayLog);
	}
	
	/**
	 * 小蜜蜂车禁临停车缴费成功推送
	 * @param cnplId
	 */
	@Transactional
	public void notifyMonthCar(CarHuaanMsg carHuaanMsg, CarNumList carNumList, CarNumPayLog carNumPayLog){
		HAMonthCarNotifyParam param = new HAMonthCarNotifyParam();
		param.setCarNum(carNumList.gettCarNum());
		String plotNo = getSmallareaNumberByGbId(carNumList.gettGroupBuildingFId());
		param.setPlotNo(plotNo);
		param.setFee(carNumPayLog.getFee());
		param.setStartTime(carNumPayLog.getPayStartDate());
		param.setEndTime(carNumPayLog.getPayEndDate());
		
		boolean isSuccess = sendNotifyMonthCarPaySuccess(param);
		if(isSuccess){
			carHuaanMsg.setPushStatus(1);
		}
		int times = (carHuaanMsg.getPushTimes()==null?1:carHuaanMsg.getPushTimes()) + 1;
		carHuaanMsg.setPushTimes(times);
		carHuaanMsg.setSendTime(CarUtil.getSendTime(times));
		carHuaanMsg.setSys0UpdTime(DateUtils.getCurrentDate());

		carHuaanMsgBaseDao.updateCarHuaanMsg(carHuaanMsg);
	}
	
	/**
	 * 月卡车缴费成功后通知
	 * @param orderId 小蜜蜂方订单号
	 * @param discountAmount 优惠金额（元）
	 * @param totalAmount 用户付款金额（元）
	 * @return
	 */
	private boolean sendNotifyMonthCarPaySuccess(HAMonthCarNotifyParam param){
		Map<String, Object> parameter = new HashMap<String, Object>(5);
		parameter.put("smallarea_number", param.getPlotNo());
		parameter.put("plate_number", param.getCarNum());
		parameter.put("currency_amount", BigDecimalUtil.div100(param.getFee()));
		
		long startdate = DateUtils.convertStrToDate(param.getStartTime()).getTime()/MILS;
		parameter.put("begin_time", startdate);
		long enddate = DateUtils.convertStrToDate(param.getEndTime()).getTime()/MILS;
		parameter.put("end_time", enddate);
		
		String response = httpPostHA(parameter, HACarDict.Method.NOTIFY_MONTH_CAR_FEE_PAY);
		if(StringUtils.isNotBlank(response)) {
			JSONObject jsonObject = JSON.parseObject(response);
			if(HACarDict.State.SUCCESS.equals(jsonObject.getString("status"))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo) {
		String smallareaNumber = getSmallareaNumberByGbId(gbId);
		Map<String, Object> parameter = new HashMap<String, Object>(2);
		parameter.put("smallarea_number", smallareaNumber);
		parameter.put("plate_number", plateNo);

		String response = httpPostHA(parameter, HACarDict.Method.GET_TEMP_CAR_FEE);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if(StringUtils.isNotBlank(response)) {
			JSONObject jsonObject = JSON.parseObject(response);
			if(HACarDict.State.SUCCESS.equals(jsonObject.getString("status"))) {
				TempCarInfo tempCarInfo = new TempCarInfo(true);
				BigDecimal amt = jsonObject.getBigDecimal("receiveMoney");
				amt = BigDecimalUtil.mul100(amt);
				tempCarInfo.setAmt(amt.longValue());
				
				BigDecimal paidFee = jsonObject.getBigDecimal("prepayMoney");
				BigDecimal realAmt = jsonObject.getBigDecimal("factMoney").subtract(paidFee);
				realAmt = BigDecimalUtil.mul100(realAmt);
				tempCarInfo.setRealAmt(realAmt.longValue());
				
				
				paidFee = BigDecimalUtil.mul100(paidFee);
				tempCarInfo.setPaidFee(paidFee.longValue());
				
				BigDecimal discountFee = jsonObject.getBigDecimal("discountMoney");
				discountFee = BigDecimalUtil.mul100(discountFee);
				tempCarInfo.setDiscountFee(discountFee.longValue());
				
				tempCarInfo.setEnterTime(jsonObject.getLongValue("enterTime")*MILS);
				tempCarInfo.setGbId(gbId);
				
				return tempCarInfo;
			} else {
				bexception.setErrorMsg(jsonObject.getString("msg"));
				throw bexception;
			}
		}
		
		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}
	
	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo)  {
		CarHuaanMsg carHuaanMsg = updateHAPayStatus(carNumPayLog.getId(), null, false, null);
		notifyHATempCar(carHuaanMsg, carNumPayLog.gettGroupBuildingFId());
	}
	
	/**
	 * 更新 华安车禁支付状态
	 * @param cnplId
	 * @return
     */
	private CarHuaanMsg updateHAPayStatus(BigInteger cnplId, Long fee, boolean isMonthCar, String monthCarNum){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNumPayLogFId", cnplId);
		List<CarHuaanMsg> carHuaanMsgs = carHuaanMsgBaseDao.selectCarHuaanMsgByCondition(paramMap, false);
		CarHuaanMsg carHuaanMsg = null;
		if(isMonthCar && (carHuaanMsgs==null || carHuaanMsgs.size()==0)) {// 停车宝处月卡
			carHuaanMsg = new CarHuaanMsg();
			carHuaanMsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_huaan_msg));
			carHuaanMsg.settCarNumPayLogFId(cnplId);
			carHuaanMsg.settCarNum(monthCarNum);
			carHuaanMsg.setCarType(XMFCarDict.CarType.MONTH_CAR);
			carHuaanMsg.setFee(fee);
			int times = 0;
			carHuaanMsg.setPushTimes(times);
			carHuaanMsg.setPayStatus(0);
			carHuaanMsg.setSendTime(CarUtil.getSendTime(times));
			carHuaanMsg.setPushStatus(0);
			carHuaanMsg.setSys0DelState(0);
			carHuaanMsg.setSys0AddTime(DateUtils.getCurrentDate());

			carHuaanMsgBaseDao.insertCarHuaanMsg(carHuaanMsg);
		} else {
			carHuaanMsg = carHuaanMsgs.get(0);
			carHuaanMsg.setPayStatus(1);

			carHuaanMsgBaseDao.updateCarHuaanMsg(carHuaanMsg);
		}

		return carHuaanMsg;
	}
	
    /**
     *缴费成功后通知岗亭，更新车辆信息
     * @param carHuaanMsg
     * @param gbId
     */
    @Transactional
    public void notifyHATempCar(CarHuaanMsg carHuaanMsg, BigInteger gbId) {
        String smallareaNumber = getSmallareaNumberByGbId(gbId);
        String plateNumber = carHuaanMsg.gettCarNum();
        Double factMoney = BigDecimalUtil.div100(carHuaanMsg.getFee()).doubleValue();
        boolean isSuccess = sendNotifyTempCarPaySuccess(smallareaNumber, plateNumber, factMoney);
        if(isSuccess){
            carHuaanMsg.setPushStatus(1);
        }
        int times = (carHuaanMsg.getPushTimes()==null?1:carHuaanMsg.getPushTimes()) + 1;
		carHuaanMsg.setPushTimes(times);
		carHuaanMsg.setSendTime(CarUtil.getSendTime(times));
		carHuaanMsg.setSys0UpdTime(DateUtils.getCurrentDate());

		carHuaanMsgBaseDao.updateCarHuaanMsg(carHuaanMsg);
    }
    
    /**
     * 通知岗亭缴费完成
     * @param smallareaNumber
     * @param plateNumber
     * @param factMoney
     * @return
     */
    public boolean sendNotifyTempCarPaySuccess(String smallareaNumber, String plateNumber, Double factMoney){
        Map<String, Object> parameter = new HashMap<String, Object>(3);
        parameter.put("smallarea_number", smallareaNumber);
        parameter.put("plate_number", plateNumber);
        parameter.put("factMoney", factMoney);

        String response = httpPostHA(parameter, HACarDict.Method.NOTIFY_TEMP_CAR_FEE_PAY);
        JSONObject jsonObject = JSON.parseObject(response);
        if(jsonObject!=null) {
            if(HACarDict.State.SUCCESS.equals(jsonObject.getString("status"))){
                return true;
            }
        }

        return false;
    }
	
	@Override
	public void releaseTaskLock() {
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY));
		RedisCacheHandler.Lock.releaseLock(CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO));
	}
	
	@Override
	public void synMonthCarPayLog() {
		
	}

	@Override
	public void pushMonthCarPayLog() {
		/**
		 * 此处不用通过事务去加锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.MONTH_CAR_PAY_NOTIFY);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("carType", HACarDict.CarType.MONTH_CAR);
				List<CarHuaanMsg> carHuaanMsgList = carHADao.selectCarHuaanMsgByCondition(paramMap);
				
				if(null!=carHuaanMsgList && carHuaanMsgList.size()>0){
					for(CarHuaanMsg carHuaanMsg : carHuaanMsgList){
						CarNumPayLog carNumPayLog = carNumPayLogBaseDao.selectCarNumPayLogBySeqId(carHuaanMsg.gettCarNumPayLogFId());
						CarNumList carNumList = carNumListBaseDao.selectCarNumListBySeqId(carNumPayLog.gettCarNumId());
						notifyMonthCar(carHuaanMsg, carNumList, carNumPayLog);
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
		 * 此处不用通过事务去加锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.TMP_CAR_PAY_NOTIFY);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("carType", HACarDict.CarType.TMP_CAR);
				List<CarHuaanMsg> carHuaanMsgList = carHADao.selectCarHuaanMsgByCondition(paramMap);

				List<CarHuaanMsg> updateCarHuaanMsgList = null;
				if(null!=carHuaanMsgList && carHuaanMsgList.size()>0){
					updateCarHuaanMsgList = new ArrayList<CarHuaanMsg>();
					String now = DateUtils.getCurrentDate();
					for (CarHuaanMsg carHuaanMsg : carHuaanMsgList) {
						CarNumPayLog carNumPayLog = carNumPayLogBaseDao.selectCarNumPayLogBySeqId(carHuaanMsg.gettCarNumPayLogFId());
						String smallareaNumber = getSmallareaNumberByGbId(carNumPayLog.gettGroupBuildingFId());
						String plateNumber = carHuaanMsg.gettCarNum();
						Double factMoney = BigDecimalUtil.div100(carHuaanMsg.getFee()).doubleValue();
						boolean isSuccess = sendNotifyTempCarPaySuccess(smallareaNumber, plateNumber, factMoney);
						if(isSuccess){
							carHuaanMsg.setPushStatus(1);
						}
						int times = (carHuaanMsg.getPushTimes()==null?1:carHuaanMsg.getPushTimes()) + 1;
						carHuaanMsg.setPushTimes(times);
						carHuaanMsg.setSendTime(CarUtil.getSendTime(times));
						carHuaanMsg.setSys0UpdTime(now);

						updateCarHuaanMsgList.add(carHuaanMsg);
					}
				}

				if(null!=updateCarHuaanMsgList && updateCarHuaanMsgList.size()>0){
					carHuaanMsgBaseDao.updateCarHuaanMsgBatch(updateCarHuaanMsgList);
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
		 * 此处不用通过事务去加锁：1、以防超时；2、一个异常会导致整个回滚
		 * */
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO);
		boolean isAcquireLock = false;
		try {
			isAcquireLock = RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_MONTH_CAR_INFO);
			if(isAcquireLock) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				List<HtCarGb> htCarGbList = getHtCarGbList();
				String now = DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				for(HtCarGb htCarGb:htCarGbList){
					BigInteger gbId = htCarGb.gettGroupBuildingFId();
					paramMap.clear();
					paramMap.put("tGroupBuildingFId",gbId);
					List<CarNumList> carNumLists = carNumListBaseDao.selectCarNumListByCondition(paramMap, true);
					if(null!=carNumLists && carNumLists.size()>0) {
						for(CarNumList carNumList:carNumLists){
							try{
								synOneMonthCarInfo(gbId, carNumList, now);
			        		} catch(Exception e){
			        			//logger.error(JSON.toJSONString(carNumList), e);
			        		}
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
		
	}
	
    /**
     * 根据小区id获取停车场编号
     * @param gbId
     * @return
     */
    public String getSmallareaNumberByGbId(BigInteger gbId) {
        String smallareaNumber = null;
        if(null!=gbId) {
        	List<HtCarGb> htCarGbList = getHtCarGbList();
        	for (HtCarGb htCarGb : htCarGbList) {
                if(htCarGb.gettGroupBuildingFId().equals(gbId)) {
                    smallareaNumber = htCarGb.gettParkid();
                    break;
                }
            }
        }
        
        return smallareaNumber;
    }
    
    /**
     * 同步车辆出入场记录
     * 
     * @param request
     * @return
     */
    public boolean synParkingRecord(HttpServletRequest request) {
    	String jsonParams = ParamUtils.getRequestBodyJson(request);
    	logger.info("HACarService.synParkingRecord.jsonParams==>"+jsonParams);
    	
    	JSONObject jsonObject = JSON.parseObject(jsonParams);
    	
    	int enterExitType = jsonObject.getIntValue("EnterExitType");
    	String carNum = jsonObject.getString("PlateNumber");
    	String smallareaNumber = jsonObject.getString("smallarea_number");
    	BigInteger gbId = getGbIdByParkId(smallareaNumber);
    	long enterTimeL = jsonObject.getLongValue("EnterTime")*MILS;
		String enterTime = DateUtils.convertDateToStr(new Date(enterTimeL), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
    	int parkType = jsonObject.getIntValue("CustomerType");
    	Integer carType = getCarType(parkType);
    	
    	if(HACarDict.EnterExitType.IN == enterExitType) {
    		return accessService.vehIn(carNum, gbId, enterTime, carType, null);
    	} else if(HACarDict.EnterExitType.OUT == enterExitType) {
        	long exitTimeL = jsonObject.getLongValue("ExitTime")*MILS;
    		String exitTime = DateUtils.convertDateToStr(new Date(exitTimeL), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
        	BigDecimal factMoney = jsonObject.getBigDecimal("FactMoney");
        	long fee = factMoney.multiply(BigDecimal.valueOf(100L)).longValue();
        	return accessService.vehOut(carNum, gbId, enterTime, exitTime, fee, carType);
    	}
    	return false;
    }
	
    /**
     * 同步线下月卡缴费记录
     * 
     * @param request
     * @return
     */
    public boolean sysRechargeLog(HttpServletRequest request) {
    	String jsonParams = ParamUtils.getRequestBodyJson(request);
    	logger.info("HACarService.sysRechargeLog.jsonParams==>"+jsonParams);
    	
    	JSONObject jsonObject = JSON.parseObject(jsonParams);
    	String carNum = jsonObject.getString("PlateNumber");
    	String smallareaNumber = jsonObject.getString("smallarea_number");
    	BigInteger gbId = getGbIdByParkId(smallareaNumber);
    	int parkType = jsonObject.getIntValue("AxisType");
    	Integer carType = getCarType(parkType);
    	
    	long startTimeL = jsonObject.getLongValue("BeginTime")*MILS;
		String startTime = DateUtils.convertDateToStr(new Date(startTimeL), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
		long endTimeL = jsonObject.getLongValue("EndTime")*MILS;
		String endTime = DateUtils.convertDateToStr(new Date(endTimeL), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
    	
    	CarNumList carNumList = new CarNumList();
    	carNumList.settCarNum(carNum);
    	carNumList.settGroupBuildingFId(gbId);
    	BigInteger carId = null;
    	List<CarNumList> carNumListList = carNumListBaseDao.selectCarNumListByCondition(MapConverter.toMap(carNumList), false);
    	if(carNumListList==null || carNumListList.size()==0) {
    		carId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list);
    		carNumList.setId(carId);
        	carNumList.setStatus(carType);
    		
        	MonthCarInfo monthCarInfo = null;
    		try {
    			monthCarInfo = getMonthCarInfo(gbId, carNum);
    		} catch (Exception e) {}
    		long fee = 0;
    		String expire = endTime;
    		if(monthCarInfo!=null) {
    			fee = monthCarInfo.getRealAmt();
    			expire = DateUtil.formatSecond.get().format(new Date(monthCarInfo.getExpire()));
    		}
    		carNumList.setFee(fee);
    		carNumList.setExpireDate(expire);
    		carNumList.setLockStatus(0);
    		carNumList.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
    		carNumList.setSys0DelState(0);
    		carNumListBaseDao.insertCarNumList(carNumList);
    	} else {
    		carId = carNumListList.get(0).getId();
    	}
    	
    	CarNumPayLog carNumPayLog = new CarNumPayLog();
		carNumPayLog.settCarNumId(carId);
		
		BigDecimal factMoney = jsonObject.getBigDecimal("FactMoney");
    	long fee = factMoney.multiply(BigDecimal.valueOf(100L)).longValue();
		carNumPayLog.setFee(fee);
		
		carNumPayLog.setPayStartDate(startTime);
		carNumPayLog.setPayEndDate(endTime);
		
		long payNum = getPayNum(startTime, endTime);
		carNumPayLog.setPayNum(payNum);
		
		int paylogCount = carNumPayLogBaseService.getCarNumPayLogCount(MapConverter.toMap(carNumPayLog));
		if(paylogCount==0){// 没有记录则新增
			BigInteger cnplId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log);
			carNumPayLog.setId(cnplId);
			carNumPayLog.setCashStatus(1);
			carNumPayLog.setPushStatus(1);
			carNumPayLog.setStatus(1);
			carNumPayLog.setCouponAmount(0L);
			carNumPayLog.setNeedBill(0);
			carNumPayLog.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
			long payTimeL = jsonObject.getLongValue("OperateTime")*MILS;
			String payTime = DateUtils.convertDateToStr(new Date(payTimeL), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
			carNumPayLog.setPayTime(payTime);
			
			return carNumPayLogBaseService.insertCarNumPayLog(carNumPayLog) > 0;
		} else {
	    	return true;
		}
    }
    
	private static int getPayNum(String startTimeStr, String endTimeStr) {
		if (StringUtils.isBlank(startTimeStr) || StringUtils.isBlank(endTimeStr)) {
			return 0;
		}

		Date d1 = DateUtils.convertStrToDate(startTimeStr, AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
		Date d2 = DateUtils.convertStrToDate(endTimeStr, AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
		long startTimeL = d1.getTime();
		long endTimeL = d2.getTime();
		DateTime startTime = new DateTime(startTimeL);
		DateTime endTime = new DateTime(endTimeL);
		Months months = Months.monthsBetween(startTime, endTime);
		int num = months.getMonths();

		if (!startTimeStr.equals(endTimeStr)) {
			if (!startTimeStr.substring(11, 19).equals(endTimeStr.substring(11, 19))) {
				num = num + 1;
			}
		}

		return num;
	}
    
    /**
     * 获取车辆类型
     * 
     * @param parkType
     * @return
     */
    private Integer getCarType(int parkType) {
    	Integer carType = AccessDict.JFQCarType.MONTH_CAR;
    	switch (parkType) {
		case HACarDict.ParkType.PARK_CUSTTYPE_TEMP:
		case HACarDict.ParkType.PARK_CUSTTYPE_DAY:
		case HACarDict.ParkType.PARK_CUSTTYPE_FREE:
		case HACarDict.ParkType.PARK_CUSTTYPE_BOOKINGTEMP:
		case HACarDict.ParkType.PARK_CUSTTYPE_RENTED:
			carType = AccessDict.JFQCarType.UN_MONTH_CAR;
			break;
		default:
			carType = AccessDict.JFQCarType.MONTH_CAR;
			break;
		}
    	
    	return carType;
    }
    
    public static String httpPostHA(Map<String, Object> parameter, String methodName){
    	String serverUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.HA_CAR_SERVER_URL);

    	String url = serverUrl + methodName;
    	logger.info("[httpPostHA]url="+url+"; parameter="+parameter);

    	String result = HttpUtil.postRaw(url, parameter);
    	logger.info("[httpPostHA]result="+result);

    	return result;
    }

}
