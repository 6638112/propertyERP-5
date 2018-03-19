package com.cnfantasia.server.ms.dredgeRefund.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.service.DredgePushService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.dredgeRefund.entity.DredgeRefund;
import com.cnfantasia.server.domainbase.dredgeRefund.service.IDredgeRefundBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.payRedEnvelope.service.IPayRedEnvelopeBaseService;
import com.cnfantasia.server.domainbase.refundRecord.entity.RefundRecord;
import com.cnfantasia.server.domainbase.refundRecord.service.IRefundRecordBaseService;
import com.cnfantasia.server.ms.dredgeRefund.dao.IDredgeRefundDao;
import com.cnfantasia.server.ms.dredgeRefund.entity.DredgeRefundEntity;
import com.cnfantasia.server.ms.pub.utils.DateUtil;
import com.cnfantasia.server.ms.pub.utils.MapConverter;
import com.propertySoftwareDock.base.entity.MailSendThread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @ClassName: DredgeRefundService.
 * @Date: 2017-10-12 14:19
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeRefundService implements IDredgeRefundService {

    @Resource
    private IDredgeBillBaseService dredgeBillBaseService;
    @Resource
    private IDredgeRefundBaseService dredgeRefundBaseService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IDredgeRefundDao dredgeRefundDao;
    @Resource
    private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
    @Resource
    private ISysParamManager sysParamManager;
    @Resource
    private DredgePushService dredgePushService;
    @Resource
    private IPayRedEnvelopeBaseService payRedEnvelopeBaseService;
    @Resource
    private IRefundRecordBaseService refundRecordBaseService;

    @Override
    public Integer addDredgeRefund(DredgeRefundEntity entity) {
        //校验状态
        BigInteger billId = entity.getDredgeBillFId();
        DredgeBill bill = dredgeBillBaseService.getDredgeBillBySeqId(billId);
        if (bill.getBillType() != DredgeConstant.DredgeBillType.Dredge_Pay_First ||
                (bill.getStatus() != DredgeConstant.DredgeBill.DredgeBill_Status_Accepted
                        && bill.getStatus() != DredgeConstant.DredgeBill.DredgeBill_Status_Submited)) {
            throw new BusinessRuntimeException("dredgeRefundController.addDredgeRefund.statusWrong");
        }
        DredgeRefund refund = new DredgeRefund();
        BigInteger refundId = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_refund);
        refund.setId(refundId);
        refund.settDredgeBillFId(billId);
        refund.setRefundStatus(DredgeConstant.DredgeRefundStatus.APPLY);
        refund.setDredgeBillStatus(bill.getStatus());
        refund.setRefundReason(entity.getRefundReason());
        refund.setNote(entity.getNote());
        refund.setRefundType(entity.getRefundType());
        Long amount = entity.getRefundAmountDecimalAdd() == null ? 0 : PriceUtil.multiply100(entity.getRefundAmountDecimalAdd().doubleValue());
        Long couponAmount = entity.getRefundCouponAmountDecimalAdd() == null ? 0 : PriceUtil.multiply100(entity.getRefundCouponAmountDecimalAdd().doubleValue());
        refund.setRefundAmount(amount);
        refund.setRefundCouponAmount(couponAmount);
        dredgeRefundBaseService.insertDredgeRefund(refund);
        DredgeBill updDb = new DredgeBill();
        updDb.setId(billId);
        updDb.setStatus(DredgeConstant.DredgeBill.Apply_Refund);
        dredgeBillBaseService.updateDredgeBill(updDb);
        //发送邮件给财务
        String notifyEmail = sysParamManager.getSysParaValue(SysParamKey.DREDGE_REFUND_NOTIFY_EMAIL);
        if (!StringUtils.isEmpty(notifyEmail)) {
            DredgeRefundEntity detail = getDredgeRefundDetail(refundId);
            String content = "您好，" + detail.getDredgeProductName() + "服务（订单号" + detail.getBillNo() + "）已申请退款，请尽快处理！";
            new MailSendThread("到家服务退款通知", content, notifyEmail).start();
        }
        //发送推送给用户
        dredgePushService.refundDredgeBillMsg(entity.getDredgeBillFId());
        return 1;
    }

    @Override
    public List<DredgeRefundEntity> getDredgeRefundList(DredgeRefundEntity entity, PageModel pageModel) {
        Map<String, Object> param = MapConverter.toMap(entity);
        param = param == null ? new HashMap<String, Object>() : param;
        if (pageModel != null) {
            param.put("_begin", pageModel.getBegin());
            param.put("_length", pageModel.getLength());
        }
        return dredgeRefundDao.getDredgeRefundList(param);
    }

    @Override
    public Integer getDredgeRefundCount(DredgeRefundEntity entity) {
        return dredgeRefundDao.getDredgeRefundCount(entity);
    }

    @Override
    public Integer audit(BigInteger refundId, String result, String auditReason) {
        DredgeRefund refund = dredgeRefundBaseService.getDredgeRefundBySeqId(refundId);
        if (refund.getRefundStatus() != DredgeConstant.DredgeRefundStatus.APPLY) {
            throw new BusinessRuntimeException("dredgeRefundController.audit.statusWrong");
        }
        if ("pass".equals(result)) {
            DredgeBill updDb = new DredgeBill();
            updDb.setId(refund.gettDredgeBillFId());
            updDb.setStatus(DredgeConstant.DredgeBill.Refund_Success);
            dredgeBillBaseService.updateDredgeBill(updDb);
            // 处理红包和带有耗材的运单
            backHb(dredgeBillBaseService.getDredgeBillBySeqId(refund.gettDredgeBillFId()), refund);
        } else {
            DredgeBill updDb = new DredgeBill();
            updDb.setId(refund.gettDredgeBillFId());
            updDb.setStatus(refund.getDredgeBillStatus());
            dredgeBillBaseService.updateDredgeBill(updDb);
        }
        DredgeRefund updRefund = new DredgeRefund();
        updRefund.setId(refundId);
        updRefund.setAuditReason(auditReason);
        updRefund.setRefundStatus("pass".equals(result) ? 2 : 3);
        dredgeRefundBaseService.updateDredgeRefund(updRefund);
        return 1;
    }

    @Override
    public DredgeRefundEntity getDredgeRefundDetail(BigInteger refundId) {
        return dredgeRefundDao.getDredgeRefundDetail(refundId);
    }

    private void backHb(DredgeBill dredgeBill, DredgeRefund refund) {

        if (refund.getRefundCouponAmount() > 0) {
            String nowTime = DateUtil.formatSecond.format(new Date());
            //往退款粮票表添加数据
            BigInteger refundRecordId = uuidManager.getNextUuidBigInteger(SEQConstants.t_refund_record);
            RefundRecord refundRecord = new RefundRecord();
            refundRecord.setId(refundRecordId);
            refundRecord.setRefundTime(nowTime);
            refundRecord.setStatus(0);
            refundRecord.settRefundOrderFId(dredgeBill.gettEbuyOrderFId());
            refundRecord.settUserHasTRoomFId(dredgeBill.getRoomid());
            refundRecord.settUserFId(dredgeBill.gettUserFId());
            refundRecord.setSavedMoney(refund.getRefundCouponAmount());
            refundRecord.setSys0DelState(0);
            refundRecordBaseService.insertRefundRecord(refundRecord);
            //生成粮票
            PayRedEnvelope payred = new PayRedEnvelope();
            payred.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope));
            payred.setAmountAvaible(refund.getRefundCouponAmount());
            payred.setAmountTotal(refund.getRefundCouponAmount());
            payred.setRoomId(dredgeBill.getRoomid());
            payred.setStatus(1);
            payred.setConverterId(dredgeBill.gettUserFId());
            payred.setUserId(dredgeBill.gettUserFId());
            payred.setSys0AddTime(nowTime);
            payred.setFromType(4);
            payred.setFromTime(nowTime);
            payred.setFromId(refundRecordId);
            payRedEnvelopeBaseService.insertPayRedEnvelope(payred);
        }
        //删除订单下运单
        EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
        ebuyDeliveryOrder.settEbuyOrderFId(dredgeBill.gettEbuyOrderFId());
        List<EbuyDeliveryOrder> deliveryOrders = ebuyDeliveryOrderBaseService.getEbuyDeliveryOrderByCondition(com.cnfantasia.server.business.pub.utils.MapConverter.toMap(ebuyDeliveryOrder));
        if (!DataUtil.isEmpty(deliveryOrders)) {
            List<BigInteger> delivOrderIds = new ArrayList<>(deliveryOrders.size());
            for (EbuyDeliveryOrder deliveryOrder : deliveryOrders) {
                delivOrderIds.add(deliveryOrder.getId());
            }
            ebuyDeliveryOrderBaseService.deleteEbuyDeliveryOrderLogicBatch(delivOrderIds);
        }
    }

}
