package com.cnfantasia.server.ms.revenue.task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;

import com.cnfantasia.server.api.access.codec.SocketBeanUtil;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.MessageType;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.ReqMessage;
import com.cnfantasia.server.api.access.codec.ake.MessageInfo.Message.ServiceType;
import com.cnfantasia.server.api.access.codec.ake.bean.request.RenewalFeesOfMonthCardReq;
import com.cnfantasia.server.api.access.constant.AccessConstant;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkCache.service.IParkCacheBaseService;

public class AccessPushTask implements ISynTask {

    @Resource
    private ICarNumListBaseService carNumListBaseService;

    @Resource
    private ICarNumPayLogBaseService carNumPayLogBaseService;

    @Resource
    private IAccessService accessService;

    @Resource
    private IUuidManager uuidManager;

    @Resource
    private IParkCacheBaseService parkCacheBaseService;

    @Override
    public Integer execTask() {
        List<CarNumPayLogDetail> paylogs = accessService.getPendingCarNumPayLog(null);
        if (null != paylogs && 0 < paylogs.size()) {
            for (CarNumPayLogDetail detail : paylogs) {
                if (null != detail) {
                    // 构造字节流
                    Message.Builder builder = Message.newBuilder();
                    builder.setMessageId(Long.toString(new Date().getTime()));
                    builder.setMessageType(MessageType.SERVICE_REQ);
                    builder.setServiceType(ServiceType.RENEWALFEES_OF_MONTH_CARD);

                    ReqMessage.Builder reqBuilder = ReqMessage.newBuilder();
                    reqBuilder.setReqTime(new Date().getTime());
                    reqBuilder.setVer("1.0");

                    RenewalFeesOfMonthCardReq payMonthCard = new RenewalFeesOfMonthCardReq();
                    CarNumList carmsg = carNumListBaseService.getCarNumListBySeqId(detail.gettCarNumId());
                    payMonthCard.setCarNO(carmsg.gettCarNum());
                    payMonthCard.setParkCode(detail.getTradeCode());
                    try {
                        payMonthCard.setPayDate(DateUtil.formatSecond.get().parse(detail.getPayTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        payMonthCard.setPayDate(new Date());
                    }
                    payMonthCard.setPayMonths(detail.getPayNum().intValue());
                    try {
                        payMonthCard.setPayExpiryDate(DateUtil.formatSecond.get().parse(carmsg.getExpireDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    reqBuilder.addAllBusData(SocketBeanUtil.convert2KeyStore(payMonthCard));
                    builder.setReqMeesage(reqBuilder.build());

                    // 保存字节流
                    ParkCache cache = new ParkCache();
                    cache.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_parking_record));
                    cache.setTradeCode(detail.getTradeCode());
                    cache.setBuffer(ArrayUtils.toObject(builder.build().toByteArray()));
                    parkCacheBaseService.insertParkCache(cache);

                    detail.setPushStatus(AccessConstant.push_status_suc);
                    carNumPayLogBaseService.updateCarNumPayLog(detail);
                } else {
                }
            }
        } else {
        }
        return 1;
    }
}
