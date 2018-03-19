package com.cnfantasia.server.api.access.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.entity.AccessMsgBean;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.session.IAccessPublishHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.carYhsMsg.dao.ICarYhsMsgDao;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 易豪升车禁
 * 
 * @author liyulin
 * @version 1.0 2017年12月11日 下午4:24:03
 */
@Service("yhsCarService")
public class YHSCarService extends AbstractCarService implements ICarService {
	private static final Logger logger = Logger.getLogger(YHSCarService.class);
    @Resource
    private IAccessPublishHandler accessPublishHandler;
    @Resource
    private ICarNumListBaseDao carNumListBaseDao;
	@Resource
	private ICarYhsMsgDao carYhsMsgDao;
	@Resource
    private IUuidManager uuidManager;

	@Override
	public String getCode() {
		return AccessDict.Code.yihaosheng.toString();
	}
	
	@Override
	public void init() {
	}
    
	@Override
	public MonthCarInfo getOneMonthCarInfo(BigInteger gbId, String plateNo) {
		CarNumList paramBean = new CarNumList();
		paramBean.settGroupBuildingFId(gbId);
		paramBean.settCarNum(plateNo);

		List<CarNumList> carNumLists = carNumListBaseDao.selectCarNumListByCondition(MapConverter.toMap(paramBean), false);
		if (carNumLists != null && carNumLists.size() > 0) {
			CarNumList carNumList = carNumLists.get(0);
			
			MonthCarInfo monthCarInfo = new MonthCarInfo();
			String expireStr = carNumList.getExpireDate();
			Date expire = DateUtils.convertStrToDate(expireStr, AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
			monthCarInfo.setExpire(expire.getTime());
			monthCarInfo.setAmt(carNumList.getFee());
			monthCarInfo.setRealAmt(carNumList.getFee());
			monthCarInfo.setLock(false);
			
			return monthCarInfo;
		}
		
		BusinessRuntimeException bexception = new BusinessRuntimeException();
		bexception.setErrorMsg("暂未查询到该车辆信息！");
		throw bexception;
	}
	
	@Override
	public void monthCardPayNotify(String orderNo, CarNumList carNumList, CarNumPayLog carNumPayLog) {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public TempCarInfo getTempCarInfo(BigInteger gbId, String plateNo) {
		String uuid = UUIDGenerater.getId();
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setCarNum(plateNo);
    	accessMsgBean.setGbId(gbId);
    	accessMsgBean.setUuid(uuid);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_QUERY_FEE);
    	accessPublishHandler.sendMessage(accessMsgBean);
    	
    	Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.QUERY_WAITING_MAX_TIME, 5*1000);
    	String key = uuid + "fee";
    	String response = CarUtil.getResultFromCache(key, null, waitTime);
    	
    	if(StringUtils.isBlank(response)) {
    		BusinessRuntimeException bexception = new BusinessRuntimeException();
    		bexception.setErrorMsg("暂未查询到该车辆信息！");
			throw bexception;
    	}
    	JSONObject result = JSON.parseObject(response);
    	
    	TempCarInfo tempCarInfo = new TempCarInfo(true);
    	long amt = BigDecimalUtil.mul100(result.getBigDecimal("fee")).longValue();
		tempCarInfo.setAmt(amt);
		tempCarInfo.setRealAmt(amt);
		tempCarInfo.setPaidFee(0L);
		tempCarInfo.setDiscountFee(0L);
		tempCarInfo.setEnterTime(result.getLongValue("enterTime"));
		tempCarInfo.setGbId(gbId);

		return tempCarInfo;
	}

	@Override
	public void tempCardPayNotify(String carNum, CarNumPayLog carNumPayLog, String orderNo) {
		CarYhsMsg carYhsMsg = new CarYhsMsg();
		carYhsMsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_yhs_msg));
		carYhsMsg.settCarNumPayLogFId(carNumPayLog.getId());
		carYhsMsg.settGroupBuildingFId(carNumPayLog.gettGroupBuildingFId());
		carYhsMsg.settCarNum(carNum);
		carYhsMsg.setFee(carNumPayLog.getFee());
		carYhsMsg.setPayTime(carNumPayLog.getPayTime());
		carYhsMsg.setSendStatus(0);
		carYhsMsg.setSys0AddTime(DateUtils.getCurrentDate());
		carYhsMsg.setSys0DelState(0);
		carYhsMsgDao.insertCarYhsMsg(carYhsMsg);
		
		// 触发redis发送消息
		AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setCarNum(carNum);
    	accessMsgBean.setFee(PriceUtil.div100(carNumPayLog.getFee()));
    	accessMsgBean.setGbId(carNumPayLog.gettGroupBuildingFId());
    	accessMsgBean.setPayDate(carNumPayLog.getPayTime());
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_SEND_PAY_SUCCESS);
    	accessPublishHandler.sendMessage(accessMsgBean);
	}
	
	@Override
	public void releaseTaskLock() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void synMonthCarPayLog() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void pushMonthCarPayLog() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void pushTempCarPayLog() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void synCarInfo() {
		// 其他接口已实现，此处不用实现
	}

	@Override
	public void synParkingRecord() {
		// 其他接口已实现，此处不用实现
	}

}
