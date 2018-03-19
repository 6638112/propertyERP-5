package com.cnfantasia.server.api.msgpush.service;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.msgpush.constant.MsgToAddBasic;

import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName: IMsgAddService
 * @Date: 2017-01-22 17:09
 * @Auther: kangduo
 * @Description: ()
 */
public interface IPushAddService {
    //根据基本信息插入表中推送
    public Integer addMessage2Pool(BigInteger sourceId, List<CommUserDataEntity> commUserDataList, MsgToAddBasic msgToAddBasic);

    //根据基本信息插入表中推送,定时发送
    public Integer addMessage2Pool(BigInteger sourceId, List<CommUserDataEntity> commUserDataList, MsgToAddBasic msgToAddBasic, String sendTime);
}
