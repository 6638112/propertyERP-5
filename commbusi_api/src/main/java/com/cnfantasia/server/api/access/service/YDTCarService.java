package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.YDTCarDict;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.YdtResponse;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.Md5Util;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.dao.ICarNumPayLogBaseDao;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 一道通车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月7日 上午11:40:01
 */
@Service("ydtCarService")
public class YDTCarService extends AbstractCarService implements ICarService {
	private static final Logger logger = Logger.getLogger(YDTCarService.class);
	
    @Resource
    private ICarNumPayLogBaseDao carNumPayLogBaseDao;

	@Override
	public String getCode() {
		return AccessDict.Code.yidaotong.toString();
	}
	
	@Override
	public void init() {
	}

	/**
	 * 查询月卡车信息
	 * 
	 * @param gbId
	 * @param plateNo
	 * @return
	 */
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		SortedMap<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("plateNo", plateNo);

		String response = httpPostYDT(paramMap, YDTCarDict.Method.GET_CAR_INFO, gbId);
		if (StringUtils.isNotBlank(response)) {
			YdtResponse ydtResponse = JSON.parseObject(response, YdtResponse.class);
			if (YDTCarDict.ResponseState.SUCCESS.equals(ydtResponse.getErrorCode())) {
				JSONObject result = ydtResponse.getResult();
				if (null != result) {
					MonthCarInfo monthCarInfo = new MonthCarInfo(true);
					monthCarInfo.setAmt(result.getLongValue("monthlyFee"));
					monthCarInfo.setRealAmt(result.getLongValue("monthlyFee"));
					
					String expireDateStr = result.getString("expireDate");
					Date expireDate = DateUtils.convertStrToDate(expireDateStr);
					monthCarInfo.setExpire(expireDate.getTime());
					monthCarInfo.setLock(result.getBooleanValue("lockStatus"));

					return monthCarInfo;
				}
			} else {
				BusinessRuntimeException bexception = new BusinessRuntimeException();
				bexception.setErrorMsg(ydtResponse.getErrorMsg());
				throw bexception;
			}
		}

		BusinessRuntimeException bexception = new BusinessRuntimeException();
		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}

	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog) {
		SortedMap<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("plateNo", carNumList.gettCarNum());
		paramMap.put("payDate", carNumPayLog.getPayTime());
		paramMap.put("payMos", carNumPayLog.getPayNum());
		paramMap.put("amount", carNumPayLog.getFee());
		paramMap.put("chargePaidNo", orderNo);

		String response = httpPostYDT(paramMap, YDTCarDict.Method.MONTHLY_CARD_PAYMENT, carNumList.gettGroupBuildingFId());
		boolean isSuccess = false;
		if (StringUtils.isNotBlank(response)) {
			YdtResponse ydtResponse = JSON.parseObject(response, YdtResponse.class);
			isSuccess = YDTCarDict.ResponseState.SUCCESS.equals(ydtResponse.getErrorCode());
		}
		
		dealCarNumPayLogForPayNotify(isSuccess, carNumPayLog, carNumPayLog.getPushTimes());
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
		SortedMap<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("plateNo", plateNo);

		String response = httpPostYDT(paramMap, YDTCarDict.Method.GET_TEMPCARD_INFO, gbId);
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		if (StringUtils.isNotBlank(response)) {
			YdtResponse ydtResponse = JSON.parseObject(response, YdtResponse.class);
			if (YDTCarDict.ResponseState.SUCCESS.equals(ydtResponse.getErrorCode())) {
				JSONObject result = ydtResponse.getResult();
				if (null != result) {
					String state = result.getString("state");
					if (YDTCarDict.TempState.IN.equals(state)) {
						TempCarInfo tempCarInfo = new TempCarInfo(true);
						tempCarInfo.setAmt(result.getLongValue("amount"));
						tempCarInfo.setRealAmt(result.getLongValue("amount"));
						tempCarInfo.setPaidFee(result.getLongValue("beforePay"));
						tempCarInfo.setDiscountFee(0L);
						Date enterTime = DateUtils.convertStrToDate(result.getString("enterTime"), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
						tempCarInfo.setEnterTime(enterTime.getTime());
						tempCarInfo.setGbId(gbId);

						return tempCarInfo;
					} else if (YDTCarDict.TempState.OUT.equals(state)) {
						bexception.setErrorMsg("该车辆不在场！");
						throw bexception;
					}
					if (YDTCarDict.TempState.CAN_LEAVE.equals(state)) {
						bexception.setErrorMsg("该车辆未产生费用，可以离去！");
						throw bexception;
					}
				}
			} else {
				bexception.setErrorMsg(ydtResponse.getErrorMsg());
				throw bexception;
			}
		}

		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}

	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo)  {
		SortedMap<String, Object> paramMap = new TreeMap<String, Object>();
		paramMap.put("plateNo", carNum);
		paramMap.put("payDate", carNumPayLog.getPayTime());
		paramMap.put("amount", carNumPayLog.getFee());
		paramMap.put("orderNo", orderNo);

		String response = httpPostYDT(paramMap, YDTCarDict.Method.TEMPCARD_PAYMENT, carNumPayLog.gettGroupBuildingFId());
		boolean isSuccess = false;
		if (StringUtils.isNotBlank(response)) {
			YdtResponse ydtResponse = JSON.parseObject(response, YdtResponse.class);
			isSuccess = YDTCarDict.ResponseState.SUCCESS.equals(ydtResponse.getErrorCode());
		}
		dealCarNumPayLogForPayNotify(isSuccess, carNumPayLog, carNumPayLog.getPushTimes());
	}

	@Override
	public void releaseTaskLock() {
		
	}
	
	@Override
	public void synMonthCarPayLog() {
		
	}

	@Override
	public void pushMonthCarPayLog() {
		
	}

	@Override
	public void pushTempCarPayLog() {
		
	}

	@Override
	public void synCarInfo() {
		
	}

	@Override
	public void synParkingRecord() {
		
	}
	
	/**
	 * post请求一道通车禁接口
	 * 
	 * @param parameter
	 * @param methodName
	 * @return
	 */
	private String httpPostYDT(SortedMap<String, Object> paramMap, String methodName, BigInteger gbId) {
		String serverUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_YDT_SERVER_URL);
		String userKey = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.CAR_YDT_USERKEY);

		paramMap.put("parkingCode", getHTCarGbByGbId(gbId).gettParkid());
		paramMap.put("requestTime", DateUtils.getCurrentDate());

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			// 参数值为空的不参与签名组串
			if (entry.getValue() == null || entry.getValue().toString().trim().length() == 0) {
				continue;
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		sb.append(userKey);

		String signature = Md5Util.md5(sb.toString());
		paramMap.put("signature", signature);

		String url = serverUrl + methodName;
		logger.info("[httpPostYDT]url=" + url + "; parameter=" + paramMap);

		String result = HttpUtil.postRaw(url, paramMap);
		logger.info("[httpPostYDT]result=" + result);

		return result;
	}

}
