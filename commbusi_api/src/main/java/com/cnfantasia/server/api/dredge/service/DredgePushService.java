package com.cnfantasia.server.api.dredge.service;

import com.cnfantasia.jfq.wechat.util.TemplateMsgSender;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IPushAddService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.payment.constant.WeiXinPayConstantUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: DredgePushService.
 * @Date: 2017-10-16 16:38
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgePushService {

    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private IDredgeBillBaseService dredgeBillBaseService;
    @Resource
    private ISysParamManager sysParamManager;
    @Resource
    private IPushAddService pushAddService;
    @Resource
    private DredgeService dredgeService;

    //您已成功预约【商品名称】服务（订单号xxx），我们将尽快与您联系确认，请注意接听客服来电，以电话确认的时间为准提供服务，感谢支持！

    /**
     * 您好，您已成功预约！
     * 预约项目：取商品名称
     * 预约时间：取预约时间
     * 我们将尽快与您联系，请注意接听客服来电，以电话确认的时间为准提供服务，感谢支持！
     * 点击查看预约服务订单详情！
     */
    public void submitBillSuccessMsg(BigInteger dredgeBillId) {
        DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
        DredgeDetails detail = dredgeService.getDredgeBillDetail(dredgeBillId.toString());
        if (db.getSubmitChannel() == 1) {
            String title = "到家服务预约成功";
            String content = "您已成功预约【" + detail.getDredgeProductName() + "】服务（订单号" + detail.getBillNo() + "），我们将尽快与您联系确认，请注意接听客服来电，以电话确认的时间为准提供服务，感谢支持！";
            if (db.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
                content = "您已成功预约【" + detail.getDredgeProductName() + "】服务（订单号" + detail.getBillNo() + "），我们将尽快与您联系确认，以电话确认的时间为准提供服务，感谢支持！";
            }
            pushAppMsg(dredgeBillId, db, title, content);
        } else {
            String pushId = sysParamManager.getSysParaValue(SysParamKey.SUBMIT_DREDGE_BILL_PUSH_ID);
            String postDataTemplate = "{\"touser\":\"OPENID\",\"template_id\":\"%s\",\"url\":" + "\"FORWARDURL\"" +",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
            String remark = "我们将尽快与您联系，请注意接听客服来电，以电话确认的时间为准提供服务，感谢支持！";
            if (db.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
                remark = "我们将尽快与您联系，请注意接听客服来电，感谢支持！";
            }
            String postData = String.format(postDataTemplate, pushId, "您好，您已成功预约！", detail.getDredgeProductName(), db.getExpectdate(), remark);
            try {
                pushWxMsg(db, postData);
            } catch (IOException e) {
                logger.debug("发微信消息产生异常：" + e);
            }
        }
    }


    //您预约的XX服务（订单号xxxx）已申请退款，我们将尽快处理，如有任何问题请咨询客服：0755-86713324

    /**
     * 您好，您的退款申请已受理！
     * 订单编号：
     * 订单金额：
     * 下单时间：
     * 钱款将在1-7个工作日内退还至您的原支付账户，请耐心等待！如有任何问题请咨询客服：0755-86713324
     * 点击查看预约服务订单详情！
     */
    public void refundDredgeBillMsg(BigInteger dredgeBillId) {
        DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
        DredgeDetails detail = dredgeService.getDredgeBillDetail(dredgeBillId.toString());
        CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
        CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
        String servePhone = companyInfoConfig.getTel();
        if (db.getSubmitChannel() == 1) {
            String title = "到家服务已申请退款";
            String content = "您预约的" + detail.getDredgeProductName() + "服务（订单号" + db.getBillNo() + "）已申请退款，我们将尽快处理，如有任何问题请咨询客服：" + servePhone;
            pushAppMsg(dredgeBillId, db, title, content);
        } else {
            String pushId = sysParamManager.getSysParaValue(SysParamKey.DREDGE_BILL_REFUND_PUSH_ID);
            String postDataTemplate = "{\"touser\":\"OPENID\",\"template_id\":\"%s\",\"url\":" + "\"FORWARDURL\"" +",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
            String remark = "钱款将在1-7个工作日内退还至您的原支付账户，请耐心等待！如有任何问题请咨询客服：" + servePhone;
            String postData = String.format(postDataTemplate, pushId, "您好，您的退款申请已受理！", db.getBillNo(), PriceUtil.div100(db.getPayAmount()), db.getSys0AddTime(), remark);
            try {
                pushWxMsg(db, postData);
            } catch (IOException e) {
                logger.debug("发微信消息产生异常：" + e);
            }
        }
    }

    //

    /**
     * 您好，您的预约服务已完成！
     * 服务项目：取商品名称
     * 订单编号：
     * 订单状态：已服务
     * 服务时间：取上门时间
     * 感谢您的认可！请点击查看订单并确认服务完成，还可以打分评价哦~
     * 感谢您的认可！请确认付款金额并完成支付，如有任何问题请咨询客服：0755-86713324
     */
    public void oosConfirmFinishMsg(BigInteger dredgeBillId) {
        DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
        DredgeDetails detail = dredgeService.getDredgeBillDetail(dredgeBillId.toString());
        CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
        CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
        String servePhone = companyInfoConfig.getTel();
        if (db.getSubmitChannel() == 1) {
            String title = "到家服务已确认完成";
            String content = "您预约的" + detail.getDredgeProductName() + "服务（订单号" + db.getBillNo() + "）已完成，感谢您的认可！请点击查看订单并确认服务完成，还可以打分评价哦~";
            if (db.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
                content = "您预约的" + detail.getDredgeProductName() + "服务（订单号" + db.getBillNo() + "）已完成，感谢您的认可！请确认付款金额并完成支付，如有任何问题请咨询客服：" + servePhone;
            }
            pushAppMsg(dredgeBillId, db, title, content);
        } else {
            String pushId = sysParamManager.getSysParaValue(SysParamKey.DREDGE_BILL_FINISH_OOS_PUSH_ID);
            String postDataTemplate = "{\"touser\":\"OPENID\",\"template_id\":\"%s\",\"url\":" + "\"FORWARDURL\"" +",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"%s\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"%s\",\"color\":\"#173177\"},\"remark\":{\"value\":\"%s\",\"color\":\"#173177\"}}}";
            String remark = "感谢您的认可！请点击查看订单并确认服务完成，还可以打分评价哦~";
            if (db.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First) {
                remark = "感谢您的认可！请确认付款金额并完成支付，如有任何问题请咨询客服：" + servePhone;
            }
            String postData = String.format(postDataTemplate, pushId, "您好，您的预约服务已完成！",
                    detail.getDredgeProductName(), db.getBillNo(), "已服务", StringUtils.isEmpty(db.getExpectWorkTime()) ? db.getExpectdate() : db.getExpectWorkTime(), remark);
            try {
                pushWxMsg(db, postData);
            } catch (IOException e) {
                logger.debug("发微信消息产生异常：" + e);
            }
        }
    }

    private void pushAppMsg(BigInteger dredgeBillId, DredgeBill db, String title, String content) {
        List<CommUserDataEntity> userList = new ArrayList<>();
        CommUserDataEntity entity = new CommUserDataEntity();
        entity.setDefaultRoomId(db.getRoomid());
        entity.setUserId(db.gettUserFId());
        entity.setUserType(1);
        userList.add(entity);
        MsgToAddBasic basic = new MsgToAddBasic();
        basic.setTitle(title);
        basic.setContent(content);
        basic.setIsReleatRoom(MsgpushDict.PushReleatRoom.TRUE);
        basic.setiOSMsgType(NoticeDict.Message_Type.OperateMessage);
        basic.setAndroidPushId(MsgpushDict.PushId.OperateMessge);

        //参数信息
        Map<String, Object> msgParams = new HashMap<>();
        String iosParam = "{'type':'1','controll':'HSOrderInfoController','params':{'title':'订单详情','orderId':" + db.getId() + ",'billType': " + db.getBillType()+ "}}";
        msgParams.put("iosParams", iosParam);
        String android = "com.jiefangqu.living.act.dredge.DredgeOrderDetailAct;S.id=" + db.getId()+ ";S.billType=" + db.getBillType()
                + ";S.billStatus=" + db.getStatus() + ";B.canTrans=false;B.needLogin=false";
        msgParams.put("androidParams", android);
        String huawei = "intent:#Intent;action=com.jiefangqu.living.act.dredge.DredgeOrderDetailAct;launchFlags=0x10000000;component=com.jiefangqu.living/.act.dredge.DredgeOrderDetailAct;B.FLAG_FROM_PUSH=true;B.fromPush=true;S.id=" + db.getId() + ";S.billType=" + db.getBillType()
                + ";S.billStatus=" + db.getStatus() + ";B.canTrans=false;S.userHasMessageId=userMsgId;end";
        msgParams.put("huaweiParams", huawei);
        basic.setMsgParameters(msgParams);
        pushAddService.addMessage2Pool(dredgeBillId, userList, basic);
    }

    private void pushWxMsg(DredgeBill db, String postData) throws IOException {
        String requestURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&" +
                "redirect_uri=REDIRECT_URI" +
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        String redirect_uri_src = sysParamManager.getSysParaValue(SysParamKey.LA_BASE_URL) + "dredge/qryDredgeBillDetail.json?id=" + db.getId() + "&billType=" + db.getBillType();
        String forwardURL = requestURL.replace("APPID", WeiXinPayConstantUtil.getAPP_ID(WeiXinPayConstantUtil.WeiXinPay_GoalAccType.JieFangQu_LightApp)).replace("REDIRECT_URI", redirect_uri_src);
        postData = postData.replace(redirect_uri_src, URLEncoder.encode(redirect_uri_src, "UTF-8"));
        String openId = dredgeService.getOpenId(db.gettUserFId());
        postData = postData.replace("OPENID", openId);
        postData = postData.replace("FORWARDURL", forwardURL);
        String sendResultResponse = TemplateMsgSender.sendMessage(postData);
        logger.info("推送给用户微信消息，返回结果是：" + sendResultResponse);
    }
}
