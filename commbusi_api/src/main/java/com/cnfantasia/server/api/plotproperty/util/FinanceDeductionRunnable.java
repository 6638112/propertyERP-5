package com.cnfantasia.server.api.plotproperty.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.entity.PayBillMessageEntity;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.service.IEbuyOrderHasTPayBillBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.room.service.IRoomBaseService;

public class FinanceDeductionRunnable implements Callable<Boolean>{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private FinanceService financeService;
	
	private ICommonRoomService commonRoomService;
	
	private IRoomBaseService roomBaseService;
	
	private String orderNo;
	
	private Long realRoomId;
	
	private Long huaId;

	private BigInteger gbId;
	
	public FinanceDeductionRunnable(FinanceService financeService, BigInteger gbId){
		this.financeService = financeService;
		this.gbId = gbId;
	}
	
	public FinanceDeductionRunnable(FinanceService financeService, String orderNo, Long realRoomId, Long hudId){
		this.financeService = financeService;
		this.orderNo = orderNo;
		this.realRoomId = realRoomId;
		this.huaId = hudId;
		
		commonRoomService = (ICommonRoomService) CnfantasiaCommbusiUtil.getBeanManager("commonRoomService");
		roomBaseService = (IRoomBaseService) CnfantasiaCommbusiUtil.getBeanManager("roomBaseService");
	}
	

	@Override
	public Boolean call() throws Exception {
		IHomeMessageService homeMessageService = financeService.getHomeMessageService();
		List<PayBillMessageEntity> unPaidBillMessageList = null;
		if(StringUtils.isEmpty(orderNo)) {
			financeService.deductionPropertyBill();
			unPaidBillMessageList = homeMessageService.getUnPaidBillMessageList(gbId, null, null);
		} else {
			logger.info("orderNo:" + orderNo);
			financeService.deductionPropertyBill(orderNo, null);
			unPaidBillMessageList = homeMessageService.getUnPaidBillMessageList(null, BigInteger.valueOf(realRoomId), null);
			try {
				logger.info("FinanceDeductionRunnable realRoomId:" + realRoomId + ",huaId:" + huaId);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tRealRoomFId", realRoomId);
				paramMap.put("creater", huaId);
				List<Room> roomList = roomBaseService.getRoomByCondition(paramMap);
				BigInteger roomId = roomList.get(0).getId();
				logger.info("FinanceDeductionRunnable roomId:" + roomId);
				commonRoomService.addValidateSuccessInfo(BigInteger.valueOf(huaId), roomId, RoomDict.RoomValidte_SourceType.BUY_FINANCE, "购买物业宝自动验证通过");//增加验证成功的信息
				logger.info("购买物业宝自动验证通过成功！");

				/*//购买物业宝删除首页消息
				if (DataUtil.isEmpty(unPaidBillMessageList)) {
					List<UserHasHomeMessage> messageList = new ArrayList<>(roomList.size());
					for (Room room : roomList) {
						UserHasHomeMessage message = new UserHasHomeMessage();
						message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_PAYBILL);
						message.settRoomFId(room.getId());
						messageList.add(message);
					}
					homeMessageService.delUserHomeMsgByParam(messageList);
				}*/
			} catch(Exception e) {
				logger.error("购买物业宝自动验证通过门牌失败！");
				logger.error(e.getMessage(), e);
			}
		}

		/*//产生首页消息
		if (!DataUtil.isEmpty(unPaidBillMessageList)) {
			List<UserHasHomeMessage> messageList = new ArrayList<>(unPaidBillMessageList.size());
			for (PayBillMessageEntity payBillMessageEntity : unPaidBillMessageList) {
				UserHasHomeMessage message = new UserHasHomeMessage();
				message.setResouceId(payBillMessageEntity.getPayBillId());
				message.settRoomFId(payBillMessageEntity.getRoomId());
				message.setUserId(payBillMessageEntity.getUserId());
				message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_PAYBILL);
				message.setContent("￥" + payBillMessageEntity.getPayBillAmount());
				message.setExtInfo("包含" + payBillMessageEntity.getFeeDetailCount() + "项待缴费用");
				message.setValidTime(payBillMessageEntity.getPayStart());
				message.setExpireTime(payBillMessageEntity.getPayEnd());
				if (payBillMessageEntity.getPayBillId() != null) {
					messageList.add(message);
				}
			}
			if (!DataUtil.isEmpty(messageList)) {
				homeMessageService.generateCommonMsg(messageList);
			}
		}*/
		return true;
	}
	
}
