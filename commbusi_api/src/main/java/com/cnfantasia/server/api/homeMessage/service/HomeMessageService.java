package com.cnfantasia.server.api.homeMessage.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.common.util.LicaiUil;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.dao.IHomeMessageDao;
import com.cnfantasia.server.api.homeMessage.entity.HomeMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.PayBillMessageEntity;
import com.cnfantasia.server.api.homeMessage.entity.UserUnReceiveDelivOrderEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.propertyRepair.constant.PropertyRepairDict;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HtmlTagFilterUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.service.IAlterPeriodDataDetailBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepair.service.IPropertyRepairBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * @ClassName: HomeMessageService
 * @Date: 2017-02-23 14:14
 * @Auther: kangduo
 * @Description: ()
 */
public class HomeMessageService implements IHomeMessageService {

    private Log logger = LogFactory.getLog(getClass());

    private IAddressOperationService addressOperationService;
    public void setAddressOperationService(IAddressOperationService addressOperationService) {
        this.addressOperationService = addressOperationService;
    }

    private IHomeMessageDao homeMessageDao;
    public void setHomeMessageDao(IHomeMessageDao homeMessageDao) {
        this.homeMessageDao = homeMessageDao;
    }

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private IUserBaseService userBaseService;
    public void setUserBaseService(IUserBaseService userBaseService) {
        this.userBaseService = userBaseService;
    }

    private IPropertyRepairBaseService propertyRepairBaseService;
    public void setPropertyRepairBaseService(IPropertyRepairBaseService propertyRepairBaseService) {
        this.propertyRepairBaseService = propertyRepairBaseService;
    }

    private ISysParamManager sysParamManager;
    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    private IDredgeBillBaseService dredgeBillBaseService;
    public void setDredgeBillBaseService(IDredgeBillBaseService dredgeBillBaseService) {
        this.dredgeBillBaseService = dredgeBillBaseService;
    }

    private IEbuyOrderBaseService ebuyOrderBaseService;
    public void setEbuyOrderBaseService(IEbuyOrderBaseService ebuyOrderBaseService) {
        this.ebuyOrderBaseService = ebuyOrderBaseService;
    }

    private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
    public void setEbuyDeliveryOrderBaseService(IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService) {
        this.ebuyDeliveryOrderBaseService = ebuyDeliveryOrderBaseService;
    }
    
    private ICommonRoomDao commonRoomDao;
    public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
		this.commonRoomDao = commonRoomDao;
	}

	private IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService;
	public void setAlterPeriodDataDetailBaseService(
			IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService) {
		this.alterPeriodDataDetailBaseService = alterPeriodDataDetailBaseService;
	}

	@Override
    public List<HomeMessageEntity> getHomeMessageListByUserId(BigInteger userId, BigInteger gbId, boolean isUserAuth, Integer version) {
        BigInteger roomId = userId == null ? null :userBaseService.getUserBySeqId(userId).getDefaultRoomId();

        List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, gbId);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("version", version);
        param.put("userId", userId);
        param.put("roomId", roomId);
        param.put("gbId", gbId);
        param.put("codeIdList", codeIdList);
        List<HomeMessageEntity> entityList = homeMessageDao.getHomeMessageListByUserId(param);
        List<HomeMessageEntity> resList = new ArrayList<HomeMessageEntity>(entityList.size());
        for (HomeMessageEntity homeMessageEntity : entityList) {
            boolean hasThis = false;
            for (HomeMessageEntity messageEntity : resList) {
                if (homeMessageEntity.getCode().equals(messageEntity.getCode())) {
                    hasThis = true;
                    break;
                }
            }
            if (!hasThis) {
                if (HomeMessageDict.MessageCode.NOTICE_MESSAGE.equals(homeMessageEntity.getCode())) {
                    homeMessageEntity.setContent(HtmlTagFilterUtil.removeHtmlTagInfo(homeMessageEntity.getContent()));
                    resList.add(homeMessageEntity);
                } else if (HomeMessageDict.MessageCode.PROPERTY_PAYBILL.equals(homeMessageEntity.getCode()) && homeMessageEntity.getContent() == null && roomId != null) {
                    //账单信息实时查
                    List<PayBillMessageEntity> unPaidBillMessageList = getUnPaidBillMessageList(userId);
                    if (!DataUtil.isEmpty(unPaidBillMessageList)) {
                        dealPropertyInfo(userId, isUserAuth, unPaidBillMessageList.get(0).getFeeDetailCount(), 
                        		unPaidBillMessageList.get(0).getPayBillAmount(), homeMessageEntity);
                        
                        homeMessageEntity.setUpdTime(unPaidBillMessageList.get(0).getMaxBillTime());
                        resList.add(homeMessageEntity);
                    }
                } else if (!HomeMessageDict.MessageCode.PROPERTY_PAYBILL.equals(homeMessageEntity.getCode())){
                    resList.add(homeMessageEntity);
                }
            }
        }
        Collections.sort(resList);
        
        //根据IOS的灰度策略去掉理财类处理
        LicaiUil.filter(resList);
        return resList;
    }
    
	/**
	 * 计算物业费数量（如果选择性周期物业费存在，则加1）
	 * @param userId
	 * @param unAlterCount
	 * @param payBillAmount
	 * @param homeMessageEntity
	 */
    private void dealPropertyInfo(BigInteger userId, boolean isUserAuth, int unAlterCount, BigDecimal payBillAmount, HomeMessageEntity homeMessageEntity){
    	if(isUserAuth){// 验证通过的
    		homeMessageEntity.setContent("￥" + payBillAmount);
    		homeMessageEntity.setExtInfo("包含" + unAlterCount + "张待缴费账单");
    	} else {
    		RealRoom realRoom = commonRoomDao.selectRealRoomByUserId(userId);
    		BigInteger realRoomId = realRoom.getId();
        	Map<String, Object> paraMap = new HashMap<String, Object>();
    		paraMap.put("tRealRoomId",realRoomId);
    		List<AlterPeriodDataDetail> alterPeriodDataDetailList = alterPeriodDataDetailBaseService.getAlterPeriodDataDetailByCondition(paraMap);
    		if(alterPeriodDataDetailList!=null && alterPeriodDataDetailList.size()>0){
    			String dateStartStr = alterPeriodDataDetailList.get(0).getBillMonthStart();
    			if(StringUtils.isNotBlank(dateStartStr)){
    				Date dateStart = DateUtils.convertStrToDate(dateStartStr);
    				if(dateStart.after(new Date())){
    					unAlterCount++;
    				}
    			}
    		}
            homeMessageEntity.setContent("您有" +unAlterCount +"张待缴费账单");
            homeMessageEntity.setExtInfo("避免产生滞纳金，请尽快缴费哦~");
    	}
    }

    @Override
    public UserUnReceiveDelivOrderEntity getUnReceivedDelivOrderCount(BigInteger deliveryOrderId) {
        return homeMessageDao.getUnReceivedDelivOrderCount(deliveryOrderId);
    }

    @Override
    public List<PayBillMessageEntity> getUnPaidBillMessageList(BigInteger gbId, BigInteger realRoomId, BigInteger roomId) {
        Map<String, Object> param = new HashMap<>();
        param.put("gbId", gbId);
        param.put("realRoomId", realRoomId);
        param.put("roomId", roomId);
        return homeMessageDao.getUnPaidBillMessageList(param);
    }
    
    @Override
    public List<PayBillMessageEntity> getUnPaidBillMessageList(BigInteger userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return homeMessageDao.getUnPaidBillMessageList(param);
    }

    @Override
    public Integer delUserHomeMsgByParam(List<UserHasHomeMessage> messageList) {
        int count = 0;
        if (!DataUtil.isEmpty(messageList)) {
            for (UserHasHomeMessage message : messageList) {
                count += homeMessageDao.delUserHomeMsgByParam(message.getUserId(), message.gettRoomFId(), message.getMessageCode(), message.getResouceId());
            }
        }
        return count;
    }

    @Override
    public void generateGroupMsg(GroupHomeMessage message, List<BigInteger> gbIds) {
        if (!DataUtil.isEmpty(gbIds)) {
            List<GroupHomeMessage> messageList = new ArrayList<GroupHomeMessage>(gbIds.size());
            List<BigInteger> msgIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_home_message, gbIds.size());
            int idx = 0;
            for (BigInteger gbId : gbIds) {
                GroupHomeMessage newMsg = new GroupHomeMessage(message);
                newMsg.setId(msgIds.get(idx++));
                newMsg.setGbId(gbId);
                messageList.add(newMsg);
            }
            new Thread(new MsgProductThread(HomeMessageDict.MessageCode.NOTICE_MESSAGE, messageList, null)).start();
        }
    }

    @Override
    public void generateCommonMsg(UserHasHomeMessage message) {
        if (message.getId() == null) {
            BigInteger id = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_home_message);
            message.setId(id);
        }
        List<UserHasHomeMessage> messageList = new ArrayList<>(1);
        messageList.add(message);
        new Thread(new MsgProductThread(message.getMessageCode(), null, messageList)).start();
    }

    public void generateCommonMsg(List<UserHasHomeMessage> messageList) {
        if (!DataUtil.isEmpty(messageList)) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_home_message, messageList.size());
            int idx = 0;
            for (UserHasHomeMessage userHasHomeMessage : messageList) {
                userHasHomeMessage.setId(ids.get(idx++));
            }
            new Thread(new MsgProductThread(messageList.get(0).getMessageCode(), null, messageList)).start();
        }
    }

    private class MsgProductThread implements Runnable {
        private String msgCode;
        private List<GroupHomeMessage> groupHomeMessageList;
        private List<UserHasHomeMessage> userHasHomeMessageList;

        MsgProductThread(String msgCode, List<GroupHomeMessage> groupHomeMessageList, List<UserHasHomeMessage> userHasHomeMessageList) {
            this.msgCode = msgCode;
            this.groupHomeMessageList = groupHomeMessageList;
            this.userHasHomeMessageList = userHasHomeMessageList;
        }

        @Override
        public void run() {
            logger.debug("产生首页消息，数据为groupHomeMessageList：" + JSON.toJSONString(groupHomeMessageList) + "|| userHasHomeMessageList：" + JSON.toJSONString(userHasHomeMessageList));
            switch (msgCode) {
                case HomeMessageDict.MessageCode.NOTICE_MESSAGE:
                    homeMessageDao.generateGroupMsg(groupHomeMessageList);
                    break;
                case HomeMessageDict.MessageCode.EBUY_STORE_ALERT:
                    UserUnReceiveDelivOrderEntity info = getUnReceivedDelivOrderCount(userHasHomeMessageList.get(0).getResouceId());
                    if (info != null && info.getDelivOrderCount() > 0) {
                        UserHasHomeMessage message = userHasHomeMessageList.get(0);
                        message.setMessageCode(HomeMessageDict.MessageCode.EBUY_STORE_ALERT);
                        message.setContent("订单状态更新提醒");
                        message.setExtInfo(info.getDelivOrderCount() + "个订单状态已更新");
                        message.setUserId(info.getUserId());
                        homeMessageDao.generateCommonMsg(message);
                    } else {
                        EbuyDeliveryOrder deliveryOrder = ebuyDeliveryOrderBaseService.getEbuyDeliveryOrderBySeqId(userHasHomeMessageList.get(0).getResouceId());
                        EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(deliveryOrder.gettEbuyOrderFId());
                        homeMessageDao.delUserHomeMsgByParam(ebuyOrder.getBuyerId(), null, HomeMessageDict.MessageCode.EBUY_STORE_ALERT, null);
                    }
                    break;
                case HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT:
                    homeMessageDao.delUserHomeMsgByParam(null, null, HomeMessageDict.MessageCode.CAR_TIMEOUT_ALERT, null);
                    homeMessageDao.generateCommonMsg(userHasHomeMessageList, false);
                    break;
                case HomeMessageDict.MessageCode.PROPERTY_PAYBILL:
                    homeMessageDao.generateCommonMsg(userHasHomeMessageList, true);
                    break;
                case HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT:
                    DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(userHasHomeMessageList.get(0).getResouceId());
                    int count = homeMessageDao.getDredgeBillInServiceCount(db.gettUserFId(), 2);
                    Integer day = Integer.parseInt(sysParamManager.getSysParaValue(SysParamKey.REPAIR_CONVERT_DAY));
                    int count2 = homeMessageDao.canTransRepairCount(db.gettUserFId(), db.getRoomid(), day);
                    if (count + count2 > 0) {
                        UserHasHomeMessage message = new UserHasHomeMessage();
                        message.setId(userHasHomeMessageList.get(0).getId());
                        message.setMessageCode(HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT);
                        message.setContent("物业已安排工作人员处理");
                        message.setResouceId(db.getId());
                        if (count == 0) {
                            message.setExtInfo(count2 + "项不能处理");
                        } else if (count2 == 0) {
                            message.setExtInfo(count + "项报修正在处理");
                        } else {
                            message.setExtInfo(count + "项报修正在处理，" + count2 + "项不能处理");
                        }
                        message.setUserId(db.gettUserFId());
                        message.settRoomFId(db.getRoomid());
                        homeMessageDao.generateCommonMsg(message);
                    } else {
                        homeMessageDao.delUserHomeMsgByParam(db.gettUserFId(), db.getRoomid(),
                                HomeMessageDict.MessageCode.PROPERTY_REPAIR_ALERT, null);
                    }
                    break;
                case HomeMessageDict.MessageCode.DREDGE_SERVICE_ALERT:
                    DredgeBill db2 = dredgeBillBaseService.getDredgeBillBySeqId(userHasHomeMessageList.get(0).getResouceId());
                    DredgeBill dredgeBill1 = new DredgeBill();
                    dredgeBill1.settUserFId(db2.gettUserFId());
                    dredgeBill1.setRoomid(db2.getRoomid());
                    dredgeBill1.setStatus(1);
                    int serviceCount = homeMessageDao.getDredgeBillInServiceCount(db2.gettUserFId(), 1);
                    if (serviceCount > 0) {
                        UserHasHomeMessage message = new UserHasHomeMessage();
                        message.setId(userHasHomeMessageList.get(0).getId());
                        message.setMessageCode(HomeMessageDict.MessageCode.DREDGE_SERVICE_ALERT);
                        message.setContent("您共有" + serviceCount + "张服务订单处理中");
                        message.setResouceId(db2.getId());
                        message.setUserId(db2.gettUserFId());
                        message.settRoomFId(db2.getRoomid());
                        homeMessageDao.generateCommonMsg(message);
                    } else {
                        homeMessageDao.delUserHomeMsgByParam(db2.gettUserFId(), db2.getRoomid(),
                                HomeMessageDict.MessageCode.DREDGE_SERVICE_ALERT, null);
                    }
                    break;
                default:
                    homeMessageDao.generateCommonMsg(userHasHomeMessageList.get(0));
                    break;
            }
        }
    }
}
