package com.propertySoftwareDock.base.service;

import com.cnfantasia.server.api.plotproperty.entity.PayBillEntity;
import com.propertySoftwareDock.base.entity.RepairPushEntity;

import java.math.BigInteger;

/**
 * @ClassName: IPropertySoftWareDockService
 * @Date: 2016-11-17 20:12
 * @Auther: kangduo
 * @Description:(物业对接，必须实现的方法)
 */
public interface IPropertySoftwareDockService {

    /**
     * 初始化小区数据
     * @param gbId
     */
    public String initData(BigInteger gbId) throws Exception;

    /**
     * 拿取物业费账单信息
     * @param realRoomId 房间号
     */
    public PayBillEntity getPropertyFeeItems(BigInteger realRoomId);

    /**
     * 回调通知物业软件已付款，销账
     * @param payBillId 账单ID
     */
    public void noticePaid(BigInteger payBillId);


    /**
     * @param repairPushEntity 需要的数据对象
     * @return 软件的billNo
     * @throws Exception
     */
    public String pushPropertyRepair(RepairPushEntity repairPushEntity) throws Exception;

    public String synRepairDetail(RepairPushEntity entity) throws Exception;
}
