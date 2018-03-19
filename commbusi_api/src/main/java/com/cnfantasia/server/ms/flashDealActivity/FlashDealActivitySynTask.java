package com.cnfantasia.server.ms.flashDealActivity;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.flashDealActivity.constant.FlashDealActivityDict;
import com.cnfantasia.server.api.flashDealActivity.service.FlashDealActivityService;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProduct.service.EbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.service.IEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.EbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;
import com.cnfantasia.server.domainbase.flashDealActivity.service.FlashDealActivityBaseService;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.entity.FlashDealBuyRecord;
import com.cnfantasia.server.domainbase.flashDealBuyRecord.service.FlashDealBuyRecordBaseService;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.message.service.IMessageBaseService;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.messageParameter.service.IMessageParameterBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userHasTMessage.service.IUserHasTMessageBaseService;
import com.cnfantasia.server.ms.revenue.task.ISynTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: FlashDealActivitySynTask
 * @Date: 2016-10-31 11:31
 * @Auther: yanghua
 * @Description:(幸运夺宝派奖逻辑处理定时器)
 */
public class FlashDealActivitySynTask implements ISynTask {
    @Resource
    private FlashDealActivityBaseService flashDealActivityBaseService;
    @Resource
    private FlashDealBuyRecordBaseService flashDealBuyRecordBaseService;
    @Resource
    private FlashDealActivityService flashDealActivityService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IMessageBaseService messageBaseService;
    @Resource
    private IUserHasTMessageBaseService userHasTMessageBaseService;
    @Resource
    private IMessageParameterBaseService messageParameterBaseService;
    @Resource
    private IEbuyProductBaseService ebuyProductBaseService;
    @Resource
    private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
    private Log logger = LogFactory.getLog(getClass());


    private static final String sucPushStr = "您在解放区“幸运GO”活动中购买成功，请稍等片刻，货品马上可送达自提点！";
    private static final String unsucPushStr = "您在解放区“幸运GO”活动中未购买成功，您的1元钱已转入粮票账户！";

    @Override
    public Integer execTask() {
        //未进行抽奖的活动记录
        Map<String,Object> paraMap = new HashMap<String, Object>();
        paraMap.put("isSettle", 0);
        // 结束时间要+ 5分钟, 避免快结束才支付的
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -5);
        paraMap.put("activityEndTime_END", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
        logger.debug("Start to execute FlashDealActivitySynTask.");
        List<FlashDealActivity> flashDealActivityList = flashDealActivityBaseService.getFlashDealActivityByCondition(paraMap);
        //进行抽奖操作
        for (FlashDealActivity flashDealActivity : flashDealActivityList) {
            //进行抽奖
            boolean status = flashDealActivityService.luckDrawByActivityId(flashDealActivity.getId());
            if(status) {//1抽奖成功   0未抽奖
                send(flashDealActivity);
            }
        }
        logger.debug("End to execute FlashDealActivitySynTask.");
        return 1;
    }

    /**
     * 发送  推送 和 短息
     * @param activityId
     */
    public void send(FlashDealActivity flashDealActivity){
        Map<String,Object> paraMap = new HashMap<String, Object>();
        paraMap.put("tFlashDealActivityFId", flashDealActivity.getId());
        List<FlashDealBuyRecord> flashDealBuyRecordByList = flashDealBuyRecordBaseService.getFlashDealBuyRecordByCondition(paraMap);
        if(flashDealBuyRecordByList != null && flashDealBuyRecordByList.size() > 0) {
            //发送短息
            sendMsg(flashDealBuyRecordByList, flashDealActivity);
            //推送
            pushMsg(flashDealBuyRecordByList);
        }
    }

    private void pushMsg(List<FlashDealBuyRecord> flashDealBuyRecordByList) {
        List<Message> msgList = new ArrayList<Message>();
        List<UserHasTMessage> userHasTMessageList = new ArrayList<UserHasTMessage>();
        //中奖的推送
        Message sucPush = new Message();
        sucPush.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
        sucPush.setTitle("幸运GO");
        sucPush.setContent(sucPushStr);
        sucPush.setTime(DateUtils.getCurrentDate());
        sucPush.setIsRelateRoom(0);//消息与门牌无关
        sucPush.setType(NoticeDict.Message_Type.FlashDeal);

        Message unsucPush = new Message(sucPush);
        unsucPush.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));
        unsucPush.setContent(unsucPushStr);

        msgList.add(sucPush);
        msgList.add(unsucPush);

        if (flashDealBuyRecordByList != null && flashDealBuyRecordByList.size() > 0) {
            List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message, flashDealBuyRecordByList.size());
            int idx = 0;
            for (FlashDealBuyRecord flashDealBuyRecord : flashDealBuyRecordByList){
                UserHasTMessage tmpUserHasTMessage = new UserHasTMessage();
                tmpUserHasTMessage.setId(ids.get(idx++));
                tmpUserHasTMessage.setTryFailedCount(0L);
                tmpUserHasTMessage.setSendStatus(NoticeDict.Message_SendStatus.UnDo);
                tmpUserHasTMessage.setStatus(NoticeDict.Message_ReadStatus.NotRead);
                tmpUserHasTMessage.settUserFId(flashDealBuyRecord.gettUserFId());
                tmpUserHasTMessage.setUserType(1);
                if(flashDealBuyRecord.getRecordStatus() != null && flashDealBuyRecord.getRecordStatus().equals(FlashDealActivityDict.BuyRecord.Result_Win)) {
                    tmpUserHasTMessage.settMessageFId(sucPush.getId());
                    userHasTMessageList.add(tmpUserHasTMessage);
                }
                if(flashDealBuyRecord.getRecordStatus() != null && flashDealBuyRecord.getRecordStatus().equals(FlashDealActivityDict.BuyRecord.Result_Fail)) {
                    tmpUserHasTMessage.settMessageFId(unsucPush.getId());
                    userHasTMessageList.add(tmpUserHasTMessage);
                }
            }

            //messageParameter
            MessageParameter sucParam = new MessageParameter();
            sucParam.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
            sucParam.settMessageFId(sucPush.getId());
            sucParam.setKey("pushId");
            sucParam.setValue(MsgpushDict.PushId.FlashDealActivity_Settle);

            MessageParameter unsucPara = new MessageParameter(sucParam);
            unsucPara.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));

            List<MessageParameter> paramList = new ArrayList<MessageParameter>();
            paramList.add(sucParam);
            paramList.add(unsucPara);

            messageBaseService.insertMessageBatch(msgList);
            messageParameterBaseService.insertMessageParameterBatch(paramList);
            if (userHasTMessageList.size() > 0) {
                userHasTMessageBaseService.insertUserHasTMessageBatch(userHasTMessageList);
            }
        }
    }

    private void sendMsg(List<FlashDealBuyRecord> flashDealBuyRecordByList, FlashDealActivity flashDealActivity) {
        String sucTemplateKey = "flashDeal.win";
        String unsucTemplateKey = "flashDeal.fail";
        //List<String> mobileSucc = new ArrayList<String>();//中奖用户手机号

        //中奖的短信一样，可以群发，没中奖的不一样，不可以群发
        for (FlashDealBuyRecord flashDealBuyRecord : flashDealBuyRecordByList){
            if(flashDealBuyRecord.getRecordStatus() != null && flashDealBuyRecord.getRecordStatus().equals(4)) {//中奖
                //mobileSucc.add(flashDealBuyRecord.getUserMobile());
                EbuyProduct ebuyProduct = ebuyProductBaseService.getEbuyProductBySeqId(flashDealActivity.gettEbuyProductFId());
                EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(ebuyProduct.gettSupplyMerchantFId());
                ShortMsgUtil.sendMessage(flashDealBuyRecord.getUserMobile(), sucTemplateKey, ebuyProduct.getName(), ebuySupplyMerchant.getName());
            }if(flashDealBuyRecord.getRecordStatus() != null && flashDealBuyRecord.getRecordStatus().equals(5)) {//未中奖
                ShortMsgUtil.sendMessage(flashDealBuyRecord.getUserMobile(), unsucTemplateKey, flashDealBuyRecord.getSys0AddTime());
            }
        }
        //调用发送短息的线程 进行批量发送短息
        /*if(mobileSucc.size() > 0) {
            ShortMsgUtil.sendMessages(mobileSucc, sucTemplateKey, null);
        }*/
    }
}
