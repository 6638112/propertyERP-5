package com.cnfantasia.server.api.access.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.HpfCarDict;
import com.cnfantasia.server.api.access.dao.IAccessDao;
import com.cnfantasia.server.api.access.entity.AccessMsgBean;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.NotifyCarPaySuccessParam;
import com.cnfantasia.server.api.access.entity.NotifySuccessParam;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.session.IAccessPublishHandler;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.carMsgTask.entity.WebSocketLinkInfo;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.ebuyOrderExt.entity.EbuyOrderExt;
import com.cnfantasia.server.domainbase.ebuyOrderExt.service.IEbuyOrderExtBaseService;
import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 华鹏飞车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 下午4:24:21
 */
@Service("hpfCarService")
public class HpfCarService extends AbstractCarService implements ICarService {

	private static final Logger logger = Logger.getLogger(HpfCarService.class);
    @Resource
    private IAccessService accessService;
    @Resource
	private IAccessPublishHandler accessPublishHandler;
    @Resource
    private ICarNumPayLogBaseDao carNumPayLogBaseDao;
    @Resource
	private IAccessDao accessDao;
    @Resource
	private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private IEbuyOrderExtBaseService ebuyOrderExtBaseService;
    
	@Override
	public String getCode() {
		return AccessDict.Code.huapengfei.toString();
	}
	
	/**
	 * car工程会从redis里面去"hpfConfig"的值，此处初始化
	 */
	@Override
	public void init() {
		List<HtCarGb> htCarGbList = getHtCarGbList();
		List<Map<String, String>> carGbInfoList = new ArrayList<Map<String,String>>(htCarGbList.size());
		for(HtCarGb htCarGb : htCarGbList){
			Map<String, String> carGbInfoMap = new HashMap<String, String>(2);
			carGbInfoMap.put("parkId", htCarGb.gettParkid());
			carGbInfoMap.put("gbId", htCarGb.gettGroupBuildingFId().toString());
			carGbInfoList.add(carGbInfoMap);
		}
		
		String hpfConfig = JSON.toJSONString(carGbInfoList);
		String cacheKey = "hpfConfig";
		
		ApplicationContext context = CnfantasiaCommbusiUtil.getContext();
		if(context!=null) {
			RedisCacheHandler.set(cacheKey, hpfConfig);
		}
	}
	
	/**
	 * 查询月卡车支付信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @param payNum
	 * @return
	 */
	@Override
	public MonthCarInfo getMonthCarPayInfo(BigInteger gbId, String plateNo, int payNum) {
		return getMonthCarMonthFee(gbId, plateNo, payNum, UUIDGenerater.getId());
	}
	
	/**
	 * 获取月卡车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	@Override
	public MonthCarInfo getMonthCarInfo(BigInteger gbId, String plateNo) {
		String uuid = UUIDGenerater.getId();
		sendQryCarInfoCommand(gbId, plateNo, uuid);
    	
    	Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.HT_QUERY_WAITING_MAX_TIME, 2*1000);
    	String key = getCacheKey(gbId, plateNo, null, uuid, HpfCarDict.Command.REMOTE_REQUEST_CAR_INFO);
    	String response = CarUtil.getResultFromCache(key, HpfCarDict.Command.REMOTE_REQUEST_CAR_INFO, waitTime);
    	if(StringUtils.isBlank(response)){
    		BusinessRuntimeException bexception = new BusinessRuntimeException();
    		bexception.setErrorMsg("查询超时");
			throw bexception;
    	} 
    	JSONObject jsonObject = JSON.parseObject(response);
    	if (HpfCarDict.LocalResult.SUCCESS.equals(jsonObject.getString("result"))) {
    		MonthCarInfo monthCarInfo = getOneMonthCarInfo(gbId, plateNo);
    		Date expire = DateUtils.convertStrToDate(jsonObject.getString("endtime"));
    		monthCarInfo.setExpire(expire.getTime());
    		
    		return monthCarInfo;
    	}
    	
    	BusinessRuntimeException bexception = new BusinessRuntimeException();
    	bexception.setErrorMsg("没有找到该车辆信息！");
		throw bexception;
	}
	
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		return getMonthCarMonthFee(gbId, plateNo, 1, UUIDGenerater.getId());
	}

	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog){
		double actualFee = BigDecimalUtil.div100(BigDecimal.valueOf(carNumPayLog.getFee())).doubleValue();
    	double receivableFee = BigDecimalUtil.div100(BigDecimal.valueOf(carNumPayLog.getReceivableFee())).doubleValue();
    	
    	NotifyCarPaySuccessParam param = new NotifyCarPaySuccessParam();
		param.setGbId(carNumList.gettGroupBuildingFId());
		param.setCarNum(carNumList.gettCarNum());
		param.setChargeTime(carNumPayLog.getPayTime());
		param.setPayNum(carNumPayLog.getPayNum().intValue());
		param.setActualFee(actualFee);
		param.setReceivableFee(receivableFee);
		param.setStarttime(carNumPayLog.getPayStartDate().substring(0, 10));// yyyy-MM-dd
		param.setEndtime(carNumPayLog.getPayEndDate().substring(0, 10));// yyyy-MM-dd
		param.setCnplId(carNumPayLog.getId());
		param.setPushTimes(carNumPayLog.getPushTimes());
    	
		sendNotifyMonthCarPaySuccessCommand(param);
	}

	@Override
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo) {
		String response = getTempCarResponse(gbId, plateNo);

		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if (StringUtils.isNotBlank(response)) {
			JSONObject jsonObject = JSON.parseObject(response);
			if (HpfCarDict.LocalResult.SUCCESS.equals(jsonObject.getString("result"))) {
				TempCarInfo tempCarInfo = new TempCarInfo(true);
				BigDecimal amt = jsonObject.getBigDecimal("receivablefee");
				amt = BigDecimalUtil.mul100(amt);
				tempCarInfo.setAmt(amt.longValue());
				
				BigDecimal realAmt = jsonObject.getBigDecimal("actualfee");
				realAmt = BigDecimalUtil.mul100(realAmt);
				tempCarInfo.setRealAmt(realAmt.longValue());
				tempCarInfo.setPaidFee(0L);
				tempCarInfo.setDiscountFee(0L);
				
				Date enterTime = DateUtils.convertStrToDate(jsonObject.getString("intime"), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
				tempCarInfo.setEnterTime(enterTime.getTime());
				tempCarInfo.setGbId(gbId);

				return tempCarInfo;
			} else {
				bexception.setErrorMsg(jsonObject.getString("message"));
				throw bexception;
			}
		}

		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}
	
	public String getTempCarResponse(BigInteger gbId, String plateNo) {
		String cardsn = accessService.getHpfCardsn(plateNo);
		sendQryTempCarFeeCommand(gbId, plateNo, cardsn);
		// 获取费用
		Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.HT_QUERY_WAITING_MAX_TIME, 2 * 1000);
		String key = getCacheKey(gbId, plateNo, null, cardsn, HpfCarDict.Command.REMOTE_RESPONSE_TEMP_CAR_PAY);
		return CarUtil.getResultFromCache(key, HpfCarDict.Command.REMOTE_RESPONSE_TEMP_CAR_PAY, waitTime);
	}

	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tEbuyOrderFId", carNumPayLog.gettEbuyOrderId());
		List<EbuyOrderExt> ebuyOrderExts = ebuyOrderExtBaseService.getEbuyOrderExtByCondition(paramMap);
		long wyAmountBt = 0;
		if(ebuyOrderExts!=null && ebuyOrderExts.size()>0){
			wyAmountBt = ebuyOrderExts.get(0).getWyCouponAmount();
		}
		
    	double actualFee = BigDecimalUtil.div100(BigDecimal.valueOf(carNumPayLog.getFee()-wyAmountBt)).doubleValue();
    	double receivableFee = BigDecimalUtil.div100(BigDecimal.valueOf(carNumPayLog.getReceivableFee())).doubleValue();
    	
    	NotifyCarPaySuccessParam param = new NotifyCarPaySuccessParam();
		param.setGbId(carNumPayLog.gettGroupBuildingFId());
		param.setCarNum(carNum);
		param.setChargeTime(carNumPayLog.getPayTime());
		param.setActualFee(actualFee);
		param.setReceivableFee(receivableFee);
		param.setCnplId(carNumPayLog.getId());
		param.setCardsn(carNumPayLog.getCardsn());
		param.setPushTimes(carNumPayLog.getPushTimes());
		sendNotifyTempCarPaySuccessCommand(param);
	}
	
	@Override
	public void releaseTaskLock(){
		RedisCacheHandler.del(CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY));
		RedisCacheHandler.del(CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY));
		RedisCacheHandler.del(CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO));
	}
	
	@Override
	public void synMonthCarPayLog() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void pushMonthCarPayLog() {
		pushCarPayLog(AccessDict.JFQCarType.MONTH_CAR,
				CarUtil.getLockValue(getCode(), AccessDict.LockKey.MONTH_CAR_PAY_NOTIFY),
				AccessDict.LockExpire.MONTH_CAR_PAY_NOTIFY);
	}

	@Override
	public void pushTempCarPayLog() {
		pushCarPayLog(AccessDict.JFQCarType.UN_MONTH_CAR,
				CarUtil.getLockValue(getCode(), AccessDict.LockKey.TMP_CAR_PAY_NOTIFY),
				AccessDict.LockExpire.TMP_CAR_PAY_NOTIFY);
	}

	@Override
	public void synCarInfo() {
		String lockKey = CarUtil.getLockValue(getCode(), AccessDict.LockKey.SYNC_MONTH_CAR_INFO);
		if (RedisCacheHandler.Lock.acquireLock(lockKey, AccessDict.LockExpire.SYNC_MONTH_CAR_INFO)) {// 多节点，加锁
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String now = DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
			
			List<WebSocketLinkInfo> websocketLinkList = getWebSocketInfoList();
			if(null!=websocketLinkList && websocketLinkList.size()>0){
				try {// 此处为了以防发送过程中websocket断了，只发送了一部分，所以加了try-catch
					for (WebSocketLinkInfo webSocketLinkInfo:websocketLinkList) {
						BigInteger gbId = webSocketLinkInfo.getGbId();
						paramMap.clear();
						paramMap.put("tGroupBuildingFId",gbId);
						List<CarNumList> carNumLists = carNumListBaseDao.selectCarNumListByCondition(paramMap, true);

						if (null != carNumLists && carNumLists.size() > 0) {
							for (CarNumList carNumList : carNumLists) {
								logger.info("send.synCarInfo.msg==>gbId=" + gbId+";carNum="+carNumList.gettCarNum());
								try{
									synOneMonthCarInfo(gbId, carNumList, now);
				        		} catch(Exception e){
				        			//logger.error(JSON.toJSONString(carNumList), e);
				        		}
							}
						}
					}
				} catch (Exception e) {
					logger.error("[synCarInfo.Exception]", e);
				} finally {
					RedisCacheHandler.Lock.releaseLock(lockKey);
				}
			} else {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}

	@Override
	public void synParkingRecord() {
		// 其他接口已实现，此处不用实现
	}
	
	/*======================================================================================*/
	
	/**
	 * 发送“获取临时车收费金额”
	 * @param gbId
	 * @param carNum
	 */
	public void sendQryTempCarFeeCommand(BigInteger gbId, String carNum, String sessionId) {
		String parkId = getParkIdByGbId(gbId);
		Map<String, Object> paramMap = new HashMap<String, Object>(15);
		paramMap.put("type", HpfCarDict.CommandType.MONEY);
		paramMap.put("command", HpfCarDict.CommandNo.REMOTE_RESPONSE_TEMP_CAR_PAY);
		paramMap.put("shop_id", parkId);
		paramMap.put("cardsn", sessionId);
		paramMap.put("VLP", carNum);
		paramMap.put("intime", "");
		paramMap.put("outtime", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		paramMap.put("recordid", "");
		paramMap.put("parkid", parkId);
		paramMap.put("channelcode", "");
		paramMap.put("Ctype", "");
		paramMap.put("Vtype", "");
		paramMap.put("receivablefee", 0);
		paramMap.put("actualfee", 0);
		paramMap.put("workstationid", 0);
		
		String message = JSON.toJSONString(paramMap);
		sendWebSocketMsgByRedis(gbId, message);
	}

	/**
	 * 通过Redis消息机制发送websocket消息
	 * @param gbId
	 * @param message
	 */
	private void sendWebSocketMsgByRedis(BigInteger gbId, String message){
		AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setGbId(gbId);
    	accessMsgBean.setMessage(message);
    	accessMsgBean.setMsgType(AccessDict.MsgType.WS_SEND_WEBSOCKET_MSG);
    	
    	accessPublishHandler.sendMessage(accessMsgBean);
	}
	
	/**
	 * 获取充值计费缓存Key
	 * @param gbId
	 * @param carNum
	 * @param count
	 * @param cardsn
	 * @param command
	 * @return
	 */
	public static String getCacheKey(Object gbId, Object carNum, Object count, Object cardsn, String command){
		return cardsn+"_"+gbId+"_"+carNum+"_"+count+"_"+command;
	}
	
	public static Map<String, Object> getQryMonthCarFeeCommandMap(String parkId, String carNum, int payNum, String sessionId){
		Map<String, Object> paramMap = new HashMap<String, Object>(11);
		paramMap.put("type", HpfCarDict.CommandType.INFO);
		paramMap.put("command", HpfCarDict.CommandNo.REMOTE_RESPONSE_MONTH_CAR_PAY);
		paramMap.put("shop_id", parkId);
		paramMap.put("cardsn", sessionId);
		paramMap.put("VLP", carNum);
		/**
		 * 此处starttime、endtime需要传一个默认值，不传会报错，金额计算以count的值为准
		 */
		String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		paramMap.put("starttime", now);
		paramMap.put("endtime", now);
		paramMap.put("count", payNum);
		paramMap.put("parkid", parkId);
		paramMap.put("recordid", 0);
		paramMap.put("workstationid", 0);
		return paramMap;
	}
	
	/**
	 * 发送“获取月租车充值金额”
	 * @param gbId
	 * @param carNum
	 */
	public void sendQryMonthCarFeeCommand(BigInteger gbId, String carNum, int payNum, String sessionId) {
		String parkId = getParkIdByGbId(gbId);
		Map<String, Object> paramMap = getQryMonthCarFeeCommandMap(parkId, carNum, payNum, sessionId);
		
		String message = JSON.toJSONString(paramMap);
		sendWebSocketMsgByRedis(gbId, message);
	}
	
	/**
	 * 获取月卡车费用
	 * @param gbId
	 * @param carNum
	 * @param payNum
	 * @return
	 */
	public MonthCarInfo getMonthCarMonthFee(BigInteger gbId, String carNum, int payNum, String sessionId){
		sendQryMonthCarFeeCommand(gbId, carNum, payNum, sessionId);

    	Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.HT_QUERY_WAITING_MAX_TIME, 2*1000);
    	String key = getCacheKey(gbId, carNum, payNum, sessionId, HpfCarDict.Command.REMOTE_RESPONSE_MONTH_CAR_PAY);
    	String response = CarUtil.getResultFromCache(key, HpfCarDict.Command.REMOTE_RESPONSE_MONTH_CAR_PAY, waitTime);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
    	if(StringUtils.isBlank(response)){
    		bexception.setErrorMsg("远程获取费用失败！");
    		throw bexception;
		} else {
			JSONObject jsonObject = JSON.parseObject(response);
			if (HpfCarDict.LocalResult.SUCCESS.equals(jsonObject.getString("result"))) {
				MonthCarInfo monthCarInfo = new MonthCarInfo(true);
				monthCarInfo.setAmt(BigDecimalUtil.mul100(jsonObject.getBigDecimal("receivablefee")).longValue());
				monthCarInfo.setRealAmt(BigDecimalUtil.mul100(jsonObject.getBigDecimal("actualfee")).longValue());
				Date expire = DateUtils.addDays(DateUtils.convertStrToDate(jsonObject.getString("starttime")), -1);
				monthCarInfo.setExpire(expire.getTime());
				monthCarInfo.setLock(false);
				
				return monthCarInfo;
			} else {
				bexception.setErrorMsg(jsonObject.getString("message"));
				throw bexception;
			}
		}
	}
	
	/**
	 * 发送“请求车辆信息”
	 * @param gbId
	 * @param carNum
	 */
	public void sendQryCarInfoCommand(BigInteger gbId, String carNum, String sessionId){
		String parkId = getParkIdByGbId(gbId);
		Map<String, Object> paramMap = new HashMap<String, Object>(8);
		paramMap.put("type", HpfCarDict.CommandType.INFO);
		paramMap.put("command", HpfCarDict.CommandNo.REMOTE_REQUEST_CAR_INFO);
		paramMap.put("shop_id", parkId);
		paramMap.put("cardsn", sessionId);
		paramMap.put("VLP", carNum);
		paramMap.put("parkid", parkId);
		paramMap.put("result", 0);
		paramMap.put("workstationid", 0);
		
		String message = JSON.toJSONString(paramMap);
		sendWebSocketMsgByRedis(gbId, message);
	}
	
	/**
	 * 发送“通知成功”（WSMode=0）
	 * @param param
	 */
	public String getNotifySuccessCommand(NotifySuccessParam param){
		Map<String, Object> paramMap = new HashMap<String, Object>(7);
		//{"parkid":1,"workstationid":1,"recordid":0,"code":1,"command":13,"result":0,"message":"ok"}
		paramMap.put("parkid", param.getParkId());
		paramMap.put("workstationid", param.getWorkstationId());
		paramMap.put("recordid", param.getRecordId());
		Object code = (param.getCode()==null)?0:param.getCode();
		paramMap.put("code", code);
		paramMap.put("command", param.getCommand());
		paramMap.put("result", param.getResult());
		paramMap.put("message", "ok");
		
		return JSON.toJSONString(paramMap);
	}
	
	/**
	 * 获取解放区车辆类型
	 * @param carType
	 * @return
	 */
	public static Integer getJFQCarType(String carType){
		if(HpfCarDict.CarType.TEMP_CAR.equals(carType)){
			return AccessDict.JFQCarType.UN_MONTH_CAR;
		} else {
			return AccessDict.JFQCarType.MONTH_CAR;
		}
	}
	
	/**
	 * 处理华鹏飞车禁响应
	 * @param command
	 * @param msg
	 */
	public String dealResponse(String command, String msg){
		String resultMsg = StringUtils.EMPTY;
		switch (command) {
		case HpfCarDict.Command.UP_IN_OUT:// 进出场不需要写入缓存
			resultMsg = dealInOut(msg);
			break;
		case HpfCarDict.Command.REMOTE_REQUEST_CAR_INFO:
		case HpfCarDict.Command.REMOTE_RESPONSE_MONTH_CAR_PAY:
		case HpfCarDict.Command.REMOTE_RESPONSE_TEMP_CAR_PAY:
			resultMsg = writeToCache(command, msg);
			break;
		default:
			break;
		}
		
		return resultMsg;
	}
	
	/**
	 * 处理进出场
	 * @param msg
	 */
	@Transactional(propagation=Propagation.NESTED)
	private String dealInOut(String msg){
		String resultMsg = StringUtils.EMPTY;
		if(StringUtils.isNotBlank(msg)){
			JSONObject jsonObject = JSON.parseObject(msg);
			if(null != jsonObject){
				String carNum = jsonObject.getString("VLP");
				String parkId = jsonObject.getString("parkid");
				BigInteger gbId = getGbIdByParkId(parkId);
				int command = jsonObject.getIntValue("command");
				if(HpfCarDict.CommandNo.UP_RECHARGE_LOG == command){// 月卡缴费记录同步
					MonthCarInfo monthCarInfo = getOneMonthCarInfo(gbId, carNum);
					if(monthCarInfo!=null && monthCarInfo.isState()){
						jsonObject.put("perMonthFee", monthCarInfo.getRealAmt());
						boolean isSuccess = accessService.synXmfCarPayLog(jsonObject, true);
						if(isSuccess){
							NotifySuccessParam param = new NotifySuccessParam();
							param.setParkId(Integer.valueOf(String.valueOf(parkId)));
							param.setCode(jsonObject.getString("code"));
							param.setCommand(jsonObject.getString("command"));
							param.setRecordId(jsonObject.getString("recordid"));
							param.setResult(HpfCarDict.LocalResult.SUCCESS);
							param.setWorkstationId(jsonObject.getString("workstationid"));
							
							resultMsg = getNotifySuccessCommand(param);
						}
					}
				} else {// 车辆进出
					String direction = jsonObject.getString("direction");
					if(StringUtils.isNotBlank(direction)){
						String inTime = jsonObject.getString("intime");
						String carType = jsonObject.getString("Ctype");
						Integer jfqCarType = getJFQCarType(carType);
						String cardsn = jsonObject.getString("cardsn");
						boolean isSuccess = false;
						
						if(HpfCarDict.Direction.IN.equals(direction)){// 进场
							isSuccess = accessService.vehIn(carNum, gbId, inTime, jfqCarType, cardsn);
						} else if(HpfCarDict.Direction.OUT.equals(direction)){// 出场
							BigDecimal actualFee = jsonObject.getBigDecimal("actualfee");
							long fee = actualFee.multiply(BigDecimal.valueOf(100L)).longValue();
							
							String outTime = jsonObject.getString("outtime");
							isSuccess = accessService.vehOut(carNum, gbId, inTime, outTime, fee, jfqCarType);
							isSuccess &= accessService.synXmfCarPayLog(jsonObject, false);
						}
						
						if(isSuccess){// 发送“通知进出场成功”
							NotifySuccessParam param = new NotifySuccessParam();
							param.setParkId(Integer.valueOf(String.valueOf(parkId)));
							param.setCode(jsonObject.get("code"));
							param.setCommand(jsonObject.get("command"));
							param.setRecordId(jsonObject.get("recordid"));
							param.setResult(HpfCarDict.LocalResult.SUCCESS);
							param.setWorkstationId(jsonObject.get("workstationid"));
							
							resultMsg = getNotifySuccessCommand(param);
						}
					}
				}
			}
		}
		
		return resultMsg;
	}
	
	/**
	 * 将数据写入缓存（Redis）
	 * @param command
	 * @param msg
	 */
	private String writeToCache(String command, String msg){
		String resultMsg = StringUtils.EMPTY;
		if(StringUtils.isNotBlank(msg)){
			JSONObject jsonObject = JSON.parseObject(msg);
			if(null != jsonObject){
				String parkId = jsonObject.getString("parkid");
				if(null != parkId){
					BigInteger gbId = getGbIdByParkId(parkId);
					
					String carNum = jsonObject.getString("VLP");
					Object count = jsonObject.get("count");
					Object sessionId = jsonObject.get("cardsn");
					String key = getCacheKey(gbId, carNum, count, sessionId, command);
					
					if(HpfCarDict.Command.REMOTE_REQUEST_CAR_INFO.equals(command)){
						RedisCacheHandler.set(key, msg, AccessDict.CacheExpire.EXPIRE_600s);
					} else {
						RedisCacheHandler.set(key, msg, AccessDict.CacheExpire.EXPIRE_120s);
					}
					
					if(HpfCarDict.Command.REMOTE_RESPONSE_TEMP_CAR_PAY.equals(command)) {
						RedisCacheHandler.set(carNum, jsonObject.getString("outtime"), AccessDict.CacheExpire.EXPIRE_600s);
					}
					
					NotifySuccessParam param = new NotifySuccessParam();
					param.setParkId(Integer.valueOf(String.valueOf(parkId)));
					param.setCode(jsonObject.get("code"));
					param.setCommand(jsonObject.get("command"));
					param.setRecordId(jsonObject.get("recordid"));
					param.setResult(HpfCarDict.LocalResult.SUCCESS);
					param.setWorkstationId(jsonObject.get("workstationid"));
					
					resultMsg = getNotifySuccessCommand(param);
				}
			}
		}
		
		return resultMsg;
	}
	
	public List<WebSocketLinkInfo> getWebSocketInfoList(){
		String uuid = UUIDGenerater.getId();
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setUuid(uuid);
    	accessMsgBean.setMsgType(AccessDict.MsgType.WS_QUERY_ALL_LINKS);
    	accessPublishHandler.sendMessage(accessMsgBean);
    	
    	Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.QUERY_ALL_LINKS_WAITING_MAX_TIME, 8*1000);
    	long begin = System.currentTimeMillis();
    	long end = System.currentTimeMillis();
    	
    	final String key = uuid + "ws_links";
    	String allWebsocket = RedisCacheHandler.get(key);
    	while (null==allWebsocket && ((end - begin) < waitTime)) {
    		try {
				Thread.sleep(HpfCarDict.WHILE_WAITING_TIME);
			} catch (InterruptedException e) {
				logger.error("InterruptedException", e);
			}
    		end = System.currentTimeMillis();
    		allWebsocket = RedisCacheHandler.get(key);
    	}
    	
    	List<WebSocketLinkInfo> websocketLinkList = null;
    	if(null!=allWebsocket){
    		websocketLinkList = JSON.parseArray(allWebsocket, WebSocketLinkInfo.class);
    		RedisCacheHandler.del(key);
    	}
		
    	return websocketLinkList;
	}
	
	private void pushCarPayLog(int carType, String lockKey, int expire) {
		if (RedisCacheHandler.Lock.acquireLock(lockKey, expire)) {// 多节点，加锁
			List<CarNumPayLog> updateCarNumPayLogList = new ArrayList<CarNumPayLog>();
			List<WebSocketLinkInfo> websocketLinkList = getWebSocketInfoList();
			if(null!=websocketLinkList && websocketLinkList.size()>0){
				try {// 此处为了以防发送过程中websocket断了，只发送了一部分，所以加了try-catch
					for (WebSocketLinkInfo webSocketLinkInfo : websocketLinkList) {
						List<NotifyCarPaySuccessParam> pushCarInfoList = null;
						BigInteger gbId = webSocketLinkInfo.getGbId();
						if (AccessDict.JFQCarType.MONTH_CAR == carType) {
							pushCarInfoList = accessDao.selectPushMonthCarInfo(gbId);
						} else if (AccessDict.JFQCarType.UN_MONTH_CAR == carType) {
							pushCarInfoList = accessDao.selectPushTempCarInfo(gbId);
						}
						if (null != pushCarInfoList && pushCarInfoList.size() > 0) {
							for (NotifyCarPaySuccessParam pushCarInfo : pushCarInfoList) {
								logger.info("send.pushCarPayLog.msg==>" + JSON.toJSONString(pushCarInfo));
								if (AccessDict.JFQCarType.MONTH_CAR == carType) {
									sendNotifyMonthCarPaySuccessCommand(pushCarInfo);
								} else if (AccessDict.JFQCarType.UN_MONTH_CAR == carType) {
									sendNotifyTempCarPaySuccessCommand(pushCarInfo);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("pushCarPayLog:" + carType, e);
				} finally {
					if (updateCarNumPayLogList.size() > 0) {
						carNumPayLogBaseDao.updateCarNumPayLogBatch(updateCarNumPayLogList);
					}
					RedisCacheHandler.Lock.releaseLock(lockKey);
				}
			} else {
				RedisCacheHandler.Lock.releaseLock(lockKey);
			}
		}
	}
	
	/**
	 * 发送“通知月租车充值支付成功”
	 * 
	 * @param param
	 */
	public void sendNotifyMonthCarPaySuccessCommand(NotifyCarPaySuccessParam param) {
		String parkId = getParkIdByGbId(param.getGbId());
		Map<String, Object> paramMap = new HashMap<String, Object>(15);
		paramMap.put("type", HpfCarDict.CommandType.INFO);
		paramMap.put("command", HpfCarDict.CommandNo.REMOTE_RESPONSE_MONTH_CAR_PAY_RESULT);
		paramMap.put("shop_id", parkId);
		paramMap.put("cardsn", "");
		paramMap.put("VLP", param.getCarNum());
		paramMap.put("chargetime", param.getChargeTime());
		
		paramMap.put("starttime", param.getStarttime());
		paramMap.put("endtime", param.getEndtime());
		paramMap.put("count", param.getPayNum());
		paramMap.put("parkid", parkId);
		paramMap.put("receivablefee", param.getReceivableFee());
		paramMap.put("actualfee", param.getActualFee());
		paramMap.put("recordid", 0);
		paramMap.put("result", 0);
		paramMap.put("workstationid", 0);
		
		String message = JSON.toJSONString(paramMap);
		sendWebSocketMsgByRedis(param.getGbId(), message);
		
		boolean isSuccess = isPushMonthCarPaySuccessed(param.getGbId(), param.getCarNum(), param.getEndtime());
		CarNumPayLog carNumPayLog = new CarNumPayLog();
		if(isSuccess){
			carNumPayLog.setPushStatus(1);
		} 

		carNumPayLog.setId(param.getCnplId());
		dealCarNumPayLogForPayNotify(isSuccess, carNumPayLog, param.getPushTimes());
	}
	
	/**
	 * 发送“通知临时车支付结果”
	 * 
	 * @param param
	 */
	public void sendNotifyTempCarPaySuccessCommand(NotifyCarPaySuccessParam param) {
		String parkId = getParkIdByGbId(param.getGbId());
		Map<String, Object> paramMap = new HashMap<String, Object>(12);
		paramMap.put("type", HpfCarDict.CommandType.INFO);
		paramMap.put("command", HpfCarDict.CommandNo.REMOTE_RESPONSE_TEMP_CAR_PAY_RESULT);
		paramMap.put("shop_id", parkId);
		paramMap.put("cardsn", param.getCardsn());
		paramMap.put("VLP", param.getCarNum());
		String chargetime = RedisCacheHandler.get(param.getCarNum());
		if(StringUtils.isBlank(chargetime)) {
			chargetime = param.getChargeTime();
		}
		paramMap.put("chargetime", chargetime);
		paramMap.put("parkid", parkId);
		paramMap.put("receivablefee", param.getReceivableFee());
		paramMap.put("actualfee", param.getActualFee());
		paramMap.put("recordid", 0);
		paramMap.put("result", 0);
		paramMap.put("workstationid", 0);
		
		String message = JSON.toJSONString(paramMap);
		sendWebSocketMsgByRedis(param.getGbId(), message);
		
		boolean isSuccess = isPushTmpCarPaySuccessed(param.getGbId(), param.getCarNum());
		CarNumPayLog carNumPayLog = new CarNumPayLog();
		if(isSuccess){
			carNumPayLog.setPushStatus(1);
		} 

		carNumPayLog.setId(param.getCnplId());
		dealCarNumPayLogForPayNotify(isSuccess, carNumPayLog, param.getPushTimes());
	}
	
	/**
	 * 月卡缴费是否推送成功
	 * 
	 * @param gbId
	 * @param carNum
	 * @param endTime
	 * @return
	 */
	public boolean isPushMonthCarPaySuccessed(BigInteger gbId, String carNum, Object endTime) {
		boolean isSuccess = false;
		MonthCarInfo monthCarInfo = getMonthCarInfo(gbId, carNum);
		if (monthCarInfo != null && monthCarInfo.isState()) {
			String remoteExpire = DateUtil.formatDay.get().format(monthCarInfo.getExpire());
			String localExpire = endTime.toString();
			return localExpire.equals(remoteExpire);
		}
		return isSuccess;
	}
	
	/**
	 * 临停车缴费是否推送成功
	 * 
	 * @param gbId
	 * @param carNum
	 * @param uuid
	 * @return
	 */
	public boolean isPushTmpCarPaySuccessed(BigInteger gbId, String carNum) {
		try {
			Thread.sleep(5000);// websocket可能有延迟，等待5秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean isSuccess = false;
		String response = getTempCarResponse(gbId, carNum);
		if (StringUtils.isNotBlank(response)) {
			JSONObject jsonObject = JSON.parseObject(response);
			if (jsonObject!= null && HpfCarDict.LocalResult.PAID_NONE_OUT_TIME.equals(jsonObject.getString("result"))) {
				isSuccess = true;
			}
		}
		
		return isSuccess;
	}
}
