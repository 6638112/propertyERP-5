package com.cnfantasia.server.api.groupBuildingCycleCfg.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.cnfantasia.server.api.groupBuildingCycleCfg.constant.CycleCfgDict;
import com.cnfantasia.server.api.groupBuildingCycleCfg.dao.GroupBuildingCycleCfgDao;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.FixedFeeItemInitEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillInitEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillTypeDict;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.paybill.constant.PropertyFeeDetailTypeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.payBill.dao.PayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.PropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity.PropertyFeeDetailUnpaid;
import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.service.IPropertyFeeDetailUnpaidBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.payBill.constant.PropIconUtil;
import com.cnfantasia.server.ms.revenue.entity.PropertyFeeDetailTempEntity;

/**
 * @ClassName: UpdatePayBillThread
 * @Date: 2017-08-31 14:44
 * @Auther: yanghua
 * @Description:(更新账单数据)
 */
public class UpdatePayBillRunnable implements Callable<Boolean>{
    private List<UpdatePayBillInitEntity> updatePayBillInitEntities;
    private PayBillBaseDao payBillBaseDao;
    private PropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
    private GroupBuildingCycleCfgService groupBuildingCycleCfgService;
    private IUuidManager uuidManager;
    private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
    private GroupBuildingCycleCfgDao groupBuildingCycleCfgDao;
    private IPropertyFeeDetailUnpaidBaseService propertyFeeDetailUnpaidBaseService;
    private IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService;
    private IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao;
    private IPayBillTypeBaseDao payBillTypeBaseDao;

    public UpdatePayBillRunnable(List<UpdatePayBillInitEntity> updatePayBillInitEntities) {
        this.updatePayBillInitEntities = updatePayBillInitEntities;
        payBillBaseDao = (PayBillBaseDao)CnfantasiaCommbusiUtil.getBeanManager("payBillBaseDao");
        propertyFeeDetailBaseDao = (PropertyFeeDetailBaseDao)CnfantasiaCommbusiUtil.getBeanManager("propertyFeeDetailBaseDao");
        groupBuildingCycleCfgService = (GroupBuildingCycleCfgService)CnfantasiaCommbusiUtil.getBeanManager("groupBuildingCycleCfgService");
        uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
        fixedFeeItemHasRoomBaseService = (IFixedFeeItemHasRoomBaseService)CnfantasiaCommbusiUtil.getBeanManager("fixedFeeItemHasRoomBaseService");
        groupBuildingCycleCfgDao = (GroupBuildingCycleCfgDao)CnfantasiaCommbusiUtil.getBeanManager("groupBuildingCycleCfgDao");
        propertyFeeDetailUnpaidBaseService = (IPropertyFeeDetailUnpaidBaseService)CnfantasiaCommbusiUtil.getBeanManager("propertyFeeDetailUnpaidBaseService");
        groupBuildingBillCycleBaseService = (IGroupBuildingBillCycleBaseService)CnfantasiaCommbusiUtil.getBeanManager("groupBuildingBillCycleBaseService");
        groupBuildingBillCycleBaseDao = (IGroupBuildingBillCycleBaseDao) CnfantasiaCommbusiUtil.getBeanManager("groupBuildingBillCycleBaseDao");
        payBillTypeBaseDao = (IPayBillTypeBaseDao) CnfantasiaCommbusiUtil.getBeanManager("payBillTypeBaseDao");
    }

    @Override
    public Boolean call() throws Exception {
        try {
            for (UpdatePayBillInitEntity updatePayBillInitEntity : updatePayBillInitEntities) {
                if(UpdatePayBillTypeDict.UPDATEALL == updatePayBillInitEntity.getType()) {
                    updatePayBills(updatePayBillInitEntity);
                }
                if(UpdatePayBillTypeDict.ADDITEM == updatePayBillInitEntity.getType()) {
                    addItemToPayBill(updatePayBillInitEntity);
                }
                if(UpdatePayBillTypeDict.ADDROOM == updatePayBillInitEntity.getType()) {
                    createPayBills(updatePayBillInitEntity);
                }
                if(UpdatePayBillTypeDict.DELETEITEM == updatePayBillInitEntity.getType()) {
                    updatePayBillsByDel(updatePayBillInitEntity);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 新增房间账单信息
     * @param updatePayBillInitEntity
     */
    private void createPayBills(UpdatePayBillInitEntity updatePayBillInitEntity) {
        List<BigInteger> itemIds = new ArrayList<BigInteger>();
        List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = updatePayBillInitEntity.getFixedFeeItemHasRoomList();
        for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
            itemIds.add(fixedFeeItemHasRoom.gettFixedFeeItemId());
        }

        //根据费用项来查询对应房间所在小区的预缴费账期配置
        List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigs = groupBuildingCycleCfgService.getBuildingBillCycleConfigsByItemIds(itemIds);
        for (GroupBuildingBillCycleConfig groupBuildingBillCycleConfig : groupBuildingBillCycleConfigs) {
            //根据账期配置ID查询对应的账期信息
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("gbbcCfgId", groupBuildingBillCycleConfig.getId());
            List<GroupBuildingBillCycle> billCycles = groupBuildingBillCycleBaseService.getGroupBuildingBillCycleByCondition(paraMap);

            //初始化欠费信息
            initAlterCycleData(groupBuildingBillCycleConfig, itemIds, fixedFeeItemHasRooms, billCycles);

            for (GroupBuildingBillCycle groupBuildingBillCycle : billCycles) {
                if(!groupBuildingBillCycle.getChargingMode().equals(CycleCfgDict.RechargeMode.ALTER_CYCLE)) continue;//仅仅操作选择周期
                //生成账单信息
                List<PayBill> payBillList = new ArrayList<PayBill>();
                List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
                List<BigInteger> payBillIds = new ArrayList<BigInteger>();
                List<BigInteger> feeDetailIds = new ArrayList<BigInteger>();
                //常规项账单生成截止月份list
                createPayBillAndDetail(payBillList, propertyFeeDetailList, groupBuildingBillCycle, groupBuildingBillCycleConfig, payBillIds, feeDetailIds, fixedFeeItemHasRooms);

                savePayBillAndCarryoverUnpaid(groupBuildingBillCycleConfig, groupBuildingBillCycle, payBillList, propertyFeeDetailList);
                //保存账单信息
                if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailList)) {
                    payBillBaseDao.insertPayBillBatch(payBillList);
                    propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
                }
            }
        }
    }

    /**
     * 根据房间ID、费用项ID更新选择周期预缴账单
     */
    private void updatePayBills(UpdatePayBillInitEntity updatePayBillInitEntity) {
        List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = updatePayBillInitEntity.getFixedFeeItemHasRoomList();
        List<BigInteger> itemHasRoomIds = new ArrayList<BigInteger>();
        for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
            itemHasRoomIds.add(fixedFeeItemHasRoom.getId());
        }

        //根据itemHasRoomIds查询出现有的账单和明细信息（账单的缴费窗口必须在当前系统时间后：预缴账单）
        List<UpdatePayBillEntity> updatePayBillEntities = groupBuildingCycleCfgService.getNeedUpdatedPayBills(itemHasRoomIds);

        //更新账单和明细
        List<PayBill> payBillList = new ArrayList<PayBill>();
        List<PropertyFeeDetail> feeDetails = new ArrayList<PropertyFeeDetail>();
        for (UpdatePayBillEntity updatePayBillEntity : updatePayBillEntities) {
            PayBill payBill = updatePayBillEntity;
            long amplitudeAmt = 0L;//价格振幅
            for (PropertyFeeDetail propertyFeeDetail : updatePayBillEntity.getPropertyFeeDetailList()) {
                for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
                    if(fixedFeeItemHasRoom.getId().equals(propertyFeeDetail.getItemHasRoomId())) {
                        BigDecimal newTotalPrice = BigDecimal.valueOf(fixedFeeItemHasRoom.getTotalPrice());
                        BigDecimal oldTotalPrice = BigDecimal.valueOf(propertyFeeDetail.getTotalPrice());

                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getTotalPrice())) {
                            propertyFeeDetail.setTotalPrice(BigDecimal.valueOf(fixedFeeItemHasRoom.getTotalPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getSignalPrice())) {
                            propertyFeeDetail.setSignalPrice(fixedFeeItemHasRoom.getSignalPrice().doubleValue());//单价
                        }
                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getPriceUnitValue())) {
                            propertyFeeDetail.setPriceUnitValue(fixedFeeItemHasRoom.getPriceUnitValue());//用量/面积
                        }

                        propertyFeeDetail.setSys0UpdTime(DateUtils.getCurrentDate());
                        //账单金额=费用项*缴费时长，振幅也同样需要进行相乘
                        amplitudeAmt += (newTotalPrice.longValue() - oldTotalPrice.longValue())*(DataUtil.isEmpty(propertyFeeDetail.getBillMonthSize()) ? 1 : propertyFeeDetail.getBillMonthSize());
                    }
                }
                feeDetails.add(propertyFeeDetail);
            }

            long amt = payBill.getAmount() + amplitudeAmt;
            //账单金额为零 证明收费项全部删除  则不进行账单金额的修改  直接把账单设置为删除状态
            if(amt <= 0) {
                payBill.setSys0DelState(1);
                payBill.setSys0DelTime(DateUtils.getCurrentDate());
            } else {
                payBill.setAmount(amt);
            }
            payBill.setSys0UpdTime(DateUtils.getCurrentDate());
            payBillList.add(payBill);
        }
        if(!DataUtil.isEmpty(payBillList)) {
            int i = payBillBaseDao.updatePayBillBatch(payBillList);
        }
        if(!DataUtil.isEmpty(feeDetails)) {
            int i = propertyFeeDetailBaseDao.updatePropertyFeeDetailBatch(feeDetails);
        }
    }


    /**
     * 更新收费项删除后账单的数据 --预缴账单
     * @param updatePayBillInitEntity
     */
    private void updatePayBillsByDel(UpdatePayBillInitEntity updatePayBillInitEntity) {
        List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = updatePayBillInitEntity.getFixedFeeItemHasRoomList();
        List<BigInteger> itemHasRoomIds = new ArrayList<BigInteger>();
        for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
            itemHasRoomIds.add(fixedFeeItemHasRoom.getId());
        }

        //根据itemHasRoomIds查询出现有的账单和明细信息（账单的缴费窗口必须在当前系统时间后：预缴账单）
        List<UpdatePayBillEntity> updatePayBillEntities = groupBuildingCycleCfgService.getNeedUpdatedPayBills(itemHasRoomIds);

        //更新账单和明细
        List<PayBill> payBillList = new ArrayList<PayBill>();
        List<PropertyFeeDetail> feeDetails = new ArrayList<PropertyFeeDetail>();
        for (UpdatePayBillEntity updatePayBillEntity : updatePayBillEntities) {
            PayBill payBill = updatePayBillEntity;
            long amplitudeAmt = 0L;//价格振幅
            for (PropertyFeeDetail propertyFeeDetail : updatePayBillEntity.getPropertyFeeDetailList()) {
                for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
                    if(fixedFeeItemHasRoom.getId().equals(propertyFeeDetail.getItemHasRoomId())) {
                        propertyFeeDetail.setSys0DelTime(DateUtils.getCurrentDate());
                        propertyFeeDetail.setSys0DelState(1);
                        amplitudeAmt -= BigDecimal.valueOf(propertyFeeDetail.getTotalPrice()).longValue()*(DataUtil.isEmpty(propertyFeeDetail.getBillMonthSize()) ? 1 : propertyFeeDetail.getBillMonthSize());
                        feeDetails.add(propertyFeeDetail);
                    }
                }
            }
            long amt = payBill.getAmount() + amplitudeAmt;
            //账单金额为零 证明收费项全部删除  则不进行账单金额的修改  直接把账单设置为删除状态
            if(amt <= 0) {
                payBill.setSys0DelState(1);
                payBill.setSys0DelTime(DateUtils.getCurrentDate());
            } else {
                payBill.setAmount(amt);
            }
            payBill.setSys0UpdTime(DateUtils.getCurrentDate());
            payBillList.add(payBill);
        }
        if(!DataUtil.isEmpty(payBillList)) {
            int i = payBillBaseDao.updatePayBillBatch(payBillList);
        }
        if(!DataUtil.isEmpty(feeDetails)) {
            int i = propertyFeeDetailBaseDao.updatePropertyFeeDetailBatch(feeDetails);
        }

        //删除没有任何账单的账期数据（所有费用项删除完后，未缴账单全部删除，则需要将没有账单的账期删除）
        List<BigInteger> cycleIds = new ArrayList<BigInteger>();
        for (PayBill payBill : payBillList) {
            cycleIds.add(payBill.gettBillCycleId());
        }
        if(!DataUtil.isEmpty(cycleIds)) {
            groupBuildingCycleCfgDao.delNoPayBillsGroupBuildingCycles(cycleIds);
        }
    }

    /**
     * 为已经存在的账单增加费用项信息
     */
    private void addItemToPayBill(UpdatePayBillInitEntity updatePayBillInitEntity) {
        List<PayBill> payBillList = new ArrayList<PayBill>();
        List<PropertyFeeDetail> feeDetails = new ArrayList<PropertyFeeDetail>();
        //查询需要进行组装的数据
        List<UpdatePayBillEntity> updatePayBillEntities = groupBuildingCycleCfgService.getNeedUpdatedPayBillsByRoom(updatePayBillInitEntity);

        //处理特殊问题：A，B费用项，先对所有门牌生成了A费用项对应的账单和部分门牌的B费用项对应账单（自动），事后
        //需要更新B费用项对应的未自动生成的账单的情况。在前一步判断的时候就把这种情况定为更新，所有进入了这个方法
        //之后未查询到B费用项对应账单，这种情况实际上属于新增账单，所有这里把UpdatePayBillInitEntity的type修改后continue
        if(DataUtil.isEmpty(updatePayBillEntities)) {
            updatePayBillInitEntity.setType(UpdatePayBillTypeDict.ADDROOM);
        } else {
            //组装账单明细数据
            List<PropertyFeeDetail> feeDetailList = createPayBillDetails(updatePayBillInitEntity);

            for (int i = 0; i < updatePayBillEntities.size(); i++) {
                PayBill payBill = updatePayBillEntities.get(i);
                long amplitudeAmt = 0L;//价格振幅
                if(!updatePayBillInitEntity.getRealRoomId().equals(payBill.gettRealRoomFId())) continue;
                boolean isAddPayBill = false;
                for (PropertyFeeDetail propertyFeeDetail : feeDetailList) {
                    if(propertyFeeDetail.gettCycleId().equals(payBill.gettBillCycleId())) {
                        propertyFeeDetail.settPayBillFId(payBill.getId());
                        BigDecimal detailAmt = BigDecimal.valueOf(propertyFeeDetail.getTotalPrice()).multiply(new BigDecimal(propertyFeeDetail.getBillMonthSize())).setScale(0, BigDecimal.ROUND_HALF_UP);
                        propertyFeeDetail.setSys0UpdTime(DateUtils.getCurrentDate());
                        amplitudeAmt += detailAmt.longValue();
                        feeDetails.add(propertyFeeDetail);
                        isAddPayBill = true;
                    }
                }

                if(isAddPayBill) {
                    payBill.setAmount(payBill.getAmount() + amplitudeAmt);
                    payBill.setSys0UpdTime(DateUtils.getCurrentDate());
                    payBillList.add(payBill);
                }
            }

            if(!DataUtil.isEmpty(payBillList)) {
                int i = payBillBaseDao.updatePayBillBatch(payBillList);
            }
            if(!DataUtil.isEmpty(feeDetails)) {
                int i = propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(feeDetails);
            }
        }
    }

    private List<PropertyFeeDetail> createPayBillDetails(UpdatePayBillInitEntity updatePayBillInitEntity) {
        List<PropertyFeeDetail> feeDetails = new ArrayList<PropertyFeeDetail>();
        for (FixedFeeItemHasRoom fixedFeeItemHasRoom : updatePayBillInitEntity.getFixedFeeItemHasRoomList()) {
            List<GroupBuildingBillCycle> billCycles = groupBuildingCycleCfgService.getGroupBuildingBillCycleByItemId(fixedFeeItemHasRoom.gettFixedFeeItemId());
            for (GroupBuildingBillCycle buildingBillCycle : billCycles) {
                int monthToMonthSize = 1;
                //起始时间在账期中的才进行费用项计算生成
                if(comparaStrDate(fixedFeeItemHasRoom.getBillMonthStart(), buildingBillCycle.getBillMonthEnd()) == 1) continue;
                if(comparaStrDate(fixedFeeItemHasRoom.getBillMonthStart(), buildingBillCycle.getBillMonthStart()) != -1) {
                    monthToMonthSize = getMonthToMonthSize(fixedFeeItemHasRoom.getBillMonthStart(), buildingBillCycle.getBillMonthEnd());
                } else {
                    monthToMonthSize = getMonthToMonthSize(buildingBillCycle.getBillMonthStart(), buildingBillCycle.getBillMonthEnd());
                }
                PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
                propertyFeeDetail.setName(fixedFeeItemHasRoom.getName());
                //费用类型=={"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费","9":"其它"}
                //固定收费项的费用类型设置为1管理费，因为物业宝仅会抵扣管理费和主体金，固定收费全部可用进行物业宝抵扣
                //选择周期type=10
                propertyFeeDetail.setType(PropertyFeeDetailTypeDict.ALTER);
                if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getTotalPrice())) {
                    propertyFeeDetail.setTotalPrice(BigDecimal.valueOf(fixedFeeItemHasRoom.getTotalPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getSignalPrice())) {
                    propertyFeeDetail.setSignalPrice(fixedFeeItemHasRoom.getSignalPrice().doubleValue());//单价
                }
                if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getPriceUnitValue())) {
                    propertyFeeDetail.setPriceUnitValue(fixedFeeItemHasRoom.getPriceUnitValue());//用量/面积
                }
                propertyFeeDetail.settCycleId(buildingBillCycle.getId());
                propertyFeeDetail.setFeeType(FeeTypeDict.Gu_Ding);
                propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDate());
                propertyFeeDetail.setAllowancePrice(0l);
                propertyFeeDetail.setItemHasRoomId(fixedFeeItemHasRoom.getId());
                propertyFeeDetail.setBillMonthSize(Long.parseLong(monthToMonthSize+""));
                feeDetails.add(propertyFeeDetail);
            }
        }
        CnfantasiaCommbusiUtil.newStandardList(feeDetails, SEQConstants.t_property_fee_detail);

        return feeDetails;
    }

    /**
     * 账单、明细生成.
     * @param payBillList
     * @param propertyFeeDetailList
     * @param groupBuildingBillCycle
     * @param groupBuildingBillCycleConfig
     */
    private void createPayBillAndDetail(List<PayBill> payBillList, List<PropertyFeeDetail> propertyFeeDetailList,
                                        GroupBuildingBillCycle groupBuildingBillCycle, GroupBuildingBillCycleConfig groupBuildingBillCycleConfig,
                                        List<BigInteger> payBillIds0, List<BigInteger> feeDetailIds0, List<FixedFeeItemHasRoom> fixedFeeItemHasRooms) {
        List<PropertyFeeDetailTempEntity> propertyFeeDetailTempList = groupBuildingCycleCfgDao.getNeedSynchroData(groupBuildingBillCycleConfig.gettGbId(), groupBuildingBillCycleConfig.getId());
        if (DataUtil.isEmpty(propertyFeeDetailTempList)) return;

        List<RealRoom> realRooms = groupBuildingCycleCfgDao.getNeedCreateBillRealRoom(groupBuildingBillCycle.getId(), groupBuildingBillCycleConfig.gettGbId());
        if (DataUtil.isEmpty(realRooms)) return;

        List<BigInteger> payBillIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, realRooms.size() + payBillIds0.size());
        payBillIds = payBillIds.subList(payBillIds0.size(), payBillIds.size());
        List<BigInteger> feeDetailIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailTempList.size() + feeDetailIds0.size());
        feeDetailIds = feeDetailIds.subList(feeDetailIds0.size(), feeDetailIds.size());
        int i = 0;
        int j = 0;
        for (RealRoom realRoom : realRooms) {
            BigDecimal amount = BigDecimal.ZERO;
            for (PropertyFeeDetailTempEntity propertyFeeDetailTemp : propertyFeeDetailTempList) {
                //只生成传进来的房间信息
                boolean isContinue = false;
                for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRooms) {
                    if(!fixedFeeItemHasRoom.getId().equals(propertyFeeDetailTemp.getTargetId())) continue;
                    isContinue = true;
                }
                if(!isContinue) continue;

                if (propertyFeeDetailTemp.gettRealRoomId().equals(realRoom.getId())) {
                    //计算费用时长
                    int monthToMonthSize = 1;
                    if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.ALTER_CYCLE)) {
                        //起始时间在账期中的才进行费用项计算生成
                        if(comparaStrDate(propertyFeeDetailTemp.getBillMonthStart(), groupBuildingBillCycle.getBillMonthEnd()) == 1) continue;
                        if(comparaStrDate(propertyFeeDetailTemp.getBillMonthStart(), groupBuildingBillCycle.getBillMonthStart()) != -1) {
                            monthToMonthSize = getMonthToMonthSize(propertyFeeDetailTemp.getBillMonthStart(), groupBuildingBillCycle.getBillMonthEnd());
                        } else {
                            monthToMonthSize = getMonthToMonthSize(groupBuildingBillCycle.getBillMonthStart(), groupBuildingBillCycle.getBillMonthEnd());
                        }
                    } else {
                        monthToMonthSize = getMonthToMonthSize(groupBuildingBillCycle.getBillMonthStart(), groupBuildingBillCycle.getBillMonthEnd());
                    }
                    //组装费用明细
                    PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
                    propertyFeeDetail.setId(feeDetailIds.get(j));
                    propertyFeeDetail.setName(propertyFeeDetailTemp.getName());
                    //费用类型=={"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费","9":"其它"}
                    //固定收费项的费用类型设置为1管理费，因为物业宝仅会抵扣管理费和主体金，固定收费全部可用进行物业宝抵扣
                    //选择周期type=10
                    if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.FIXED_CYCLE)) {
                        propertyFeeDetail.setType(PropertyFeeDetailTypeDict.GUAN_LI);
                    } else if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.ALTER_CYCLE)) {
                        propertyFeeDetail.setType(PropertyFeeDetailTypeDict.ALTER);
                    }
                    if (!DataUtil.isEmpty(propertyFeeDetailTemp.getPrice())) {
                        propertyFeeDetail.setTotalPrice(BigDecimal.valueOf(propertyFeeDetailTemp.getPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    if (!DataUtil.isEmpty(propertyFeeDetailTemp.getSignalPrice())) {
                        propertyFeeDetail.setSignalPrice(propertyFeeDetailTemp.getSignalPrice().doubleValue());//单价
                    }
                    if (!DataUtil.isEmpty(propertyFeeDetailTemp.getPriceUnitValue())) {
                        propertyFeeDetail.setPriceUnitValue(propertyFeeDetailTemp.getPriceUnitValue());//用量/面积
                    }
                    propertyFeeDetail.setTotalAmount(propertyFeeDetailTemp.getTotalAmount());
                    propertyFeeDetail.settPayBillFId(payBillIds.get(i));
                    propertyFeeDetail.settCycleId(groupBuildingBillCycle.getId());
                    propertyFeeDetail.setFeeType(FeeTypeDict.Gu_Ding);
                    propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDate());
                    propertyFeeDetail.setAllowancePrice(0l);
                    propertyFeeDetail.setBillMonthSize(Long.parseLong(monthToMonthSize+""));
                    propertyFeeDetail.setItemHasRoomId(propertyFeeDetailTemp.getTargetId());
                    BigDecimal detailAmt = BigDecimal.valueOf(propertyFeeDetailTemp.getPrice()).multiply(new BigDecimal(monthToMonthSize)).setScale(0, BigDecimal.ROUND_HALF_UP);
                    amount = amount.add(detailAmt);
                    if(amount.doubleValue() > 0) {
                        propertyFeeDetailList.add(propertyFeeDetail);
                        j++;
                    }
                }
            }

            if(DataUtil.isEmpty(propertyFeeDetailList) || amount.longValue() <= 0) continue;

            //组装账单信息
            PayBill payBill = new PayBill();
            payBill.setId(payBillIds.get(i));
            payBill.setAmount(amount.longValue());
            payBill.setIsPay(2);//未缴
            payBill.settRealRoomFId(realRoom.getId());
            payBill.setPropertyProprietorId(realRoom.gettPropertyProprietorFId().toString());
            payBill.setBillTypeId(groupBuildingBillCycle.gettPayBillTypeId());
            payBill.setIsPropFee(1);//都设置为物业费
            if(org.apache.commons.lang.StringUtils.isNotBlank(groupBuildingBillCycle.getBillMonthStart()) && org.apache.commons.lang.StringUtils.isNotBlank(groupBuildingBillCycle.getBillMonthEnd())){
                long monthSize = (long)DateUtils.getDiffMonths(DateUtils.convertStrToDate(groupBuildingBillCycle.getBillMonthStart()), DateUtils.convertStrToDate(groupBuildingBillCycle.getBillMonthEnd()))+1;
                payBill.setBillMonthSize(monthSize);
            }
            payBill.setBillMonthStart(groupBuildingBillCycle.getBillMonthStart());
            payBill.setBillMonthEnd(groupBuildingBillCycle.getBillMonthEnd());
            payBill.setPayDayStart(groupBuildingBillCycle.getBillPayStart());
            payBill.setPayDayEnd(groupBuildingBillCycle.getBillPayEnd());
            BigInteger ptcId = groupBuildingBillCycle.gettPayBillTimeCfgId();
            if(ptcId==null){
                ptcId = BigInteger.valueOf(1);/*数据库中提示不能为空 所以写了一个默认值*/
            }
            payBill.setBillTimeCfgId(ptcId);
            payBill.setBillTypeName(groupBuildingBillCycleConfig.getBillName());
            payBill.setPaytimeType(2);/*默认改为 以前的周期缴费方式*/
            payBill.setPreferType(1);
            payBill.setCycleType(groupBuildingBillCycleConfig.getChargingMode());//固定周期缴费(1固定，2选择周期)
            payBill.settBillCycleId(groupBuildingBillCycle.getId());
            payBill.setSys0AddTime(DateUtils.getCurrentDate());
            payBill.setBankCollectionStatus(0);
            if(payBill.getAmount() > 0 ) {
                payBillList.add(payBill);
                i ++;
            }
        }
    }

    private void savePayBillAndCarryoverUnpaid(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig, GroupBuildingBillCycle groupBuildingBillCycle, List<PayBill> payBillList, List<PropertyFeeDetail> propertyFeeDetailList) {
        //进行欠费账单记录（欠费关系维护）
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getArrearsTransfer()) && groupBuildingBillCycleConfig.getArrearsTransfer().equals(2)) {
            autoCarryoverUnPaid(groupBuildingBillCycle.gettGroupBuildingId(), groupBuildingBillCycleConfig.getBillName(), payBillList, groupBuildingBillCycleConfig.getChargingMode());
        }
    }

    public void autoCarryoverUnPaid(BigInteger gbId, String payBillTypeName, List<PayBill> payBillList, Integer chargingMode) {
        //查询对应的未缴账单及明细
        List<UnPaidPayBillEntity> unPaidPayBillList = groupBuildingCycleCfgDao.getUnPaidPayBillList(gbId, payBillTypeName.trim(), chargingMode);
        //计算账单欠费信息
        List<PropertyFeeDetailUnpaid> propertyFeeDetailUnpaids = new ArrayList<PropertyFeeDetailUnpaid>();
        for (PayBill payBill : payBillList) {
            for (UnPaidPayBillEntity unPaidPayBillEntity : unPaidPayBillList) {
                //门牌不匹配
                if (!payBill.gettRealRoomFId().equals(unPaidPayBillEntity.gettRealRoomFId())) continue;

                //记录欠费信息
                PropertyFeeDetailUnpaid propertyFeeDetailUnpaid = new PropertyFeeDetailUnpaid();
                propertyFeeDetailUnpaid.settGbId(gbId);
                propertyFeeDetailUnpaid.settPayBillId(payBill.getId());
                propertyFeeDetailUnpaid.settUnpaidPayBillId(unPaidPayBillEntity.getId());
                propertyFeeDetailUnpaid.settRealRoomId(payBill.gettRealRoomFId());
                propertyFeeDetailUnpaids.add(propertyFeeDetailUnpaid);

            }
        }

        //记录欠费相关信息
        if(!DataUtil.isEmpty(propertyFeeDetailUnpaids)) {
            CnfantasiaCommbusiUtil.newStandardListForId(propertyFeeDetailUnpaids, SEQConstants.t_property_fee_detail_unpaid);
            propertyFeeDetailUnpaidBaseService.insertPropertyFeeDetailUnpaidBatch(propertyFeeDetailUnpaids);
        }
    }

    /**
     * 初始化选择周期数据：费用起始时间2016-5
     * @param groupBuildingBillCycleConfig
     * @param billCycles
     */
    private void initAlterCycleData(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig, List<BigInteger> itemIds, List<FixedFeeItemHasRoom> fixedFeeItemHasRooms, List<GroupBuildingBillCycle> billCycles) {
        //查询当前小区下所有门牌的数据详情:仅包含所配置的收费项
        List<FixedFeeItemInitEntity> fixedFeeItemInitEntityList = groupBuildingCycleCfgDao.getNeedInitFixedData(itemIds);
        if(DataUtil.isEmpty(fixedFeeItemInitEntityList)) return;
        String minDate = groupBuildingCycleCfgDao.getNeedInitFixedDataMinDate(itemIds);
        if(DataUtil.isEmpty(minDate)) return;
        Date date = DateUtils.convertStrToDate(minDate, "yyyy-MM");
        //如果费用起止时间小于当前的所有账期的开始时间才进行账期的新增
        //没有生成账期时去配置账期时间进行欠费初始化欠费（此情况在新增完收费项后-->配置账期-->导入房间收费明细下出现billCycles为空）
        String cycleMinDate = DataUtil.isEmpty(billCycles) ? groupBuildingBillCycleConfig.getBillMonthStart() : billCycles.get(0).getBillMonthStart();
        for (GroupBuildingBillCycle billCycle : billCycles) {
            if(comparaStrDate(cycleMinDate, billCycle.getBillMonthStart()) == -1) continue;
            cycleMinDate = billCycle.getBillMonthStart();
        }

        int monthToMonthSize = getMonthToMonthSize(minDate, cycleMinDate);
        //生成账期--费用
        for (int i = 0; i < monthToMonthSize - 1; i++) {//一个月不进行处理 起始2017-07  配置开始2017-07
            String dateStr = DateUtils.convertDateToStr(date, "yyyy-MM-dd");
            //生成账单费用名称
            PayBillType payBillType = new PayBillType();
            payBillType.setId(uuidManager.getNextUuidBigInteger("t_pay_bill_type"));
            payBillType.setName(groupBuildingBillCycleConfig.getBillName());
            payBillType.setGbId(groupBuildingBillCycleConfig.gettGbId());
            payBillType.setIcon(PropIconUtil.getBillIcon(groupBuildingBillCycleConfig.getBillName()));
            payBillType.setPreferStatus(0);//都为欠费数据不生成优惠
            payBillType.setActiveStatus(1);//默认可以缴费费
            payBillType.setPaytimeType(2);//暂时以原来周期缴费的方式存储
            payBillType.setIsPropFee(2);
            payBillType.setSys0AddTime(DateUtils.getCurrentDate());
            payBillType.setSys0UpdTime(DateUtils.getCurrentDate());
            payBillType.setLastUpdTime(DateUtils.getCurrentDate());
            payBillTypeBaseDao.insertPayBillType(payBillType);

            GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
            groupBuildingBillCycle.setId(uuidManager.getNextUuidBigInteger("t_group_building_bill_cycle"));
            groupBuildingBillCycle.settGroupBuildingId(groupBuildingBillCycleConfig.gettGbId());
            groupBuildingBillCycle.settPayBillTypeId(payBillType.getId());
            groupBuildingBillCycle.setBillPayStart(dateStr);
            groupBuildingBillCycle.setBillPayEnd(dateStr);
            groupBuildingBillCycle.setBillMonthStart(dateStr);
            groupBuildingBillCycle.setBillMonthEnd(dateStr);
            groupBuildingBillCycle.setPaytimeType(2);
            groupBuildingBillCycle.setFeeType(groupBuildingBillCycleConfig.getFeeType());
            groupBuildingBillCycle.setChargingMode(groupBuildingBillCycleConfig.getChargingMode());
            groupBuildingBillCycle.setBankCollectionStatus(2);
            groupBuildingBillCycle.setGbbcCfgId(groupBuildingBillCycleConfig.getId());
            groupBuildingBillCycleBaseDao.insertGroupBuildingBillCycle(groupBuildingBillCycle);
            billCycles.add(groupBuildingBillCycle);
            //往后设置一期
            date = DateUtils.addMonths(date, 1);
        }

        for (GroupBuildingBillCycle groupBuildingBillCycle : billCycles) {
            //生成账单信息
            List<PayBill> payBillList = new ArrayList<PayBill>();
            List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
            List<BigInteger> payBillIds = new ArrayList<BigInteger>();
            List<BigInteger> feeDetailIds = new ArrayList<BigInteger>();
            createPayBillAndDetail(payBillList, propertyFeeDetailList, groupBuildingBillCycle, groupBuildingBillCycleConfig, payBillIds, feeDetailIds, fixedFeeItemHasRooms);
            if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailList)) {
                payBillBaseDao.insertPayBillBatch(payBillList);
                propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
            }
        }

    }

    /**
     *计算两个日期的月份间隔数
     * 2017-07-01 - 2017-07-02 = 1
     * 2017-07-01 - 2017-08-01 = 2
     * @param startStr
     * @param endStr
     * @return
     */
    public static int getMonthToMonthSize(String startStr, String endStr) {
        Date start = DateUtils.convertStrToDate(startStr, "yyyy-MM");
        Date end = DateUtils.convertStrToDate(endStr, "yyyy-MM");
        //计算时间间隔
        int size = DateUtils.getDiffMonths(start, end) + 1;
        return size;
    }

    /**
     * 字符串时间比较 str >= str1 == true;
     * @param str
     * @param str1
     * @return
     */
    private static int comparaStrDate(String str, String str1) {
        if(DataUtil.isEmpty(str) && !DataUtil.isEmpty(str1)) {
            return -1;
        }
        if(!DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
            return 1;
        }
        if(DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
            return 1;
        }
        long date = DateUtils.convertStrToDate(str, "yyyy-MM").getTime();
        long date1 = DateUtils.convertStrToDate(str1, "yyyy-MM").getTime();
        if(date >  date1) {
            return 1;
        } else if(date == date1) {
            return 0;
        }
        return -1;
    }
}
