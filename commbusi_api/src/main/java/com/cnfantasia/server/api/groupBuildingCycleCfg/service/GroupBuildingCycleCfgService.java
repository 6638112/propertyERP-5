package com.cnfantasia.server.api.groupBuildingCycleCfg.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.groupBuildingCycleCfg.constant.CycleCfgDict;
import com.cnfantasia.server.api.groupBuildingCycleCfg.dao.GroupBuildingCycleCfgDao;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.FixedFeeItemInitEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.GroupBuildingBillCycleConfigEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.GroupBuildingHasFeeItemEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillInitEntity;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.paybill.constant.PropertyFeeDetailTypeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.service.IGroupBuildingBillCycleConfigBaseService;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.service.IGroupBuildingCalculateLatefeeRuleBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.entity.PropertyFeeDetailUnpaid;
import com.cnfantasia.server.domainbase.propertyFeeDetailUnpaid.service.IPropertyFeeDetailUnpaidBaseService;
import com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.dao.IPropertyRechargePreferGroupbuildingBaseDao;
import com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.entity.PropertyRechargePreferGroupbuilding;
import com.cnfantasia.server.domainbase.propertyRechargePreferRule.dao.IPropertyRechargePreferRuleBaseDao;
import com.cnfantasia.server.domainbase.propertyRechargePreferRule.entity.PropertyRechargePreferRule;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.ms.payBill.constant.PropIconUtil;
import com.cnfantasia.server.ms.revenue.entity.AlterUnPaidEntity;
import com.cnfantasia.server.ms.revenue.entity.PropertyFeeDetailTempEntity;

/**
 * @ClassName: GroupBuildingCycleCfgService
 * @Date: 2017-05-08 11:29
 * @Auther: yanghua
 * @Description:(账期配置)
 */
public class GroupBuildingCycleCfgService {
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private GroupBuildingCycleCfgDao groupBuildingCycleCfgDao;
    @Resource
    private IPropertyFeeDetailUnpaidBaseService propertyFeeDetailUnpaidBaseService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IPayBillTypeBaseDao payBillTypeBaseDao;
    @Resource
    private IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao;
    @Resource
    private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
    @Resource
    private IPayBillBaseDao payBillBaseDao;
    @Resource
    private IGroupBuildingBaseService groupBuildingBaseService;
    @Resource
    private IGroupBuildingBillCycleConfigBaseService groupBuildingBillCycleConfigBaseService;
    @Resource
    private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
    @Resource
    private IPropertyRechargePreferGroupbuildingBaseDao propertyRechargePreferGroupbuildingBaseDao;
    @Resource
    private IPropertyRechargePreferRuleBaseDao propertyRechargePreferRuleBaseDao;

    @Resource
    private IGroupBuildingCalculateLatefeeRuleBaseService groupBuildingCalculateLatefeeRuleBaseService;
    /**
     * 查询可以进行自动生成账期的列表.
     */
    public List<GroupBuildingBillCycleConfig> getGroupBuildingBillCycleConfigsForAuto() {
        return groupBuildingCycleCfgDao.getGroupBuildingBillCycleConfigsForAuto();
    }

    /**
     * 自动记录欠费信息（维护账单欠费关系）.
     * @param payBillTypeId 账期名称对应的id.
     * @param propertyFeeDetailList 明细列表.
     * @param gbId 小区id.
     * @param payBillList 账单列表.
     * @param chargingMode
     */
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
     * 注：任何地方不能再次引用该方法
     * 防止自动生成失败时间过了，所以使用手动生成
     * @param cycleCfgId
     * @return
     */
    public void autoCreateCycleAndPayBill(BigInteger cycleCfgId) {
        List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigsForAutoById = groupBuildingCycleCfgDao.getGroupBuildingBillCycleConfigsForAutoById(cycleCfgId);
        autoCreateCycleAndPayBill(groupBuildingBillCycleConfigsForAutoById);
    }

    public Boolean isHashSameBillCycle(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        PayBillType payBillType = new PayBillType();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", groupBuildingBillCycleConfig.getBillName().trim());
        paramMap.put("gbId",  groupBuildingBillCycleConfig.gettGbId());
        List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap, false);
        if(!DataUtil.isEmpty(payBillTypes)) {//存在账单名称
            payBillType = payBillTypes.get(0);
        } else {
            return false;
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("gbId", groupBuildingBillCycleConfig.gettGbId());
        paraMap.put("billTypeId", payBillType.getId());
        paraMap.put("billTypeName", groupBuildingBillCycleConfig.getBillName().trim());
        paraMap.put("billMonthStart", groupBuildingBillCycleConfig.getBillMonthStart());
        paraMap.put("billMonthEnd", groupBuildingBillCycleConfig.getBillMonthEnd());
        paraMap.put("billPayStart", groupBuildingBillCycleConfig.getBillPayStart());
        paraMap.put("billPayEnd", groupBuildingBillCycleConfig.getBillPayEnd());
        int count = groupBuildingCycleCfgDao.isHashSameBillCycle(paraMap);

        return count > 0;
    }

    /**
     * 自动生成账期账单 后 进行欠费转结
     * @param groupBuildingBillCycleConfigsForAuto
     */
    @Transactional(propagation = Propagation.NESTED)
    public void autoCreateCycleAndPayBill(List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigsForAuto) {
        for (GroupBuildingBillCycleConfig groupBuildingBillCycleConfig : groupBuildingBillCycleConfigsForAuto) {
            logger.info("[groupBuildingBillCycleConfig]:"+ JSON.toJSONString(groupBuildingBillCycleConfig));
            Boolean hashSameBillCycle = isHashSameBillCycle(groupBuildingBillCycleConfig);
            logger.info("[hashSameBillCycle]:"+ JSON.toJSONString(hashSameBillCycle));
            //校验账期是否重叠:常规的，选择周期在生成的时候进行校验
            if(hashSameBillCycle && groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.FIXED_CYCLE)){
                //原有账期配置账期时间增加
                /*if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.ALTER_CYCLE)) {
                    List<Map<String, String>> monthsForCyclePay = getMonthsForCyclePay(groupBuildingBillCycleConfig);//更新账期的时间
                    if(!DataUtil.isEmpty(monthsForCyclePay)) {
                        Map<String, String> map = monthsForCyclePay.get(monthsForCyclePay.size() - 1);
                        groupBuildingBillCycleConfig.setBillMonthStart(map.get("billMonthStart"));
                        groupBuildingBillCycleConfig.setBillMonthEnd(map.get("billMonthEnd"));
                        groupBuildingBillCycleConfig.setBillPayStart(map.get("billPayStart"));
                        groupBuildingBillCycleConfig.setBillPayEnd(map.get("billPayEnd"));
                    }
                }*/
                updateCycleCfgDate(groupBuildingBillCycleConfig);
            } else {
                if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.FIXED_CYCLE)) {
                    createFixedCyclePayBills(groupBuildingBillCycleConfig);
                } else {
                    createAlterCyclePayBills(groupBuildingBillCycleConfig);
                }
                //原有账期配置账期时间增加
                String billPayStart = groupBuildingBillCycleConfig.getBillPayStart();
                Calendar dateStart = Calendar.getInstance();
                dateStart.setTime(DateUtils.convertStrToDate(billPayStart, "yyyy-MM-dd"));
                int day = dateStart.get(Calendar.DAY_OF_MONTH);
                if(day >= 28) {//自动生成的第二个日期开始，如果时间为为28号，自动顺延至下月一号
                    dateStart.add(Calendar.MONTH, 1);
                    dateStart.set(Calendar.DAY_OF_MONTH, 1);
                    dateStart.set(Calendar.HOUR_OF_DAY, 0);
                    dateStart.set(Calendar.MINUTE, 0);
                    dateStart.set(Calendar.SECOND, 0);
                    dateStart.set(Calendar.MILLISECOND, 0);
                    billPayStart = DateUtils.convertDateToStr(dateStart.getTime(), "yyyy-MM-dd");
                    groupBuildingBillCycleConfig.setBillPayStart(billPayStart);
                }
                updateCycleCfgDate(groupBuildingBillCycleConfig);
            }
        }

    }

    /**
     * 选择周期账单生成
     * @param groupBuildingBillCycleConfig
     */
    private void createAlterCyclePayBills(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        //需要生成的账期时间跨度
        List<Map<String, String>> monthsForCyclePay = getMonthsForCyclePay(groupBuildingBillCycleConfig);
        logger.info("[monthsForCyclePay]:"+ JSON.toJSONString(monthsForCyclePay));
        if(DataUtil.isEmpty(monthsForCyclePay)) return;

        for (int i = 0; i < monthsForCyclePay.size(); i++) {
            Map<String, String> map = monthsForCyclePay.get(i);
            //设置账期和账单时间   选择周期一个配置会生成多个账期 所以使用此方法共用一套方法
            groupBuildingBillCycleConfig.setBillMonthStart(map.get("billMonthStart"));
            groupBuildingBillCycleConfig.setBillMonthEnd(map.get("billMonthEnd"));
            groupBuildingBillCycleConfig.setBillPayStart(map.get("billPayStart"));
            groupBuildingBillCycleConfig.setBillPayEnd(map.get("billPayEnd"));

            //校验账期是否重叠
            if(isHashSameBillCycle(groupBuildingBillCycleConfig)) continue;
            //生成账期信息
            GroupBuildingBillCycle groupBuildingBillCycle = createCycle(groupBuildingBillCycleConfig);

            //生成账单信息
            List<PayBill> payBillList = new ArrayList<PayBill>();
            List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
            List<BigInteger> payBillIds = new ArrayList<BigInteger>();
            List<BigInteger> feeDetailIds = new ArrayList<BigInteger>();
            //常规项账单生成截止月份list
            List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
            createPayBillAndDetail(payBillList, propertyFeeDetailList, groupBuildingBillCycle, groupBuildingBillCycleConfig, payBillIds, feeDetailIds, fixedFeeItemHasRooms);

            if(i==0) {//只有第一个账期才会结算欠费信息，以后的账期通过job自动生成
                //保存账单、维护欠费关系
                savePayBillAndCarryoverUnpaid(groupBuildingBillCycleConfig, groupBuildingBillCycle, payBillList, propertyFeeDetailList);
            }
            //保存账单信息
            if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailList)) {
                payBillBaseDao.insertPayBillBatch(payBillList);
                propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
            }

            //更新常规项的费用生成截止时间
            if(!DataUtil.isEmpty(fixedFeeItemHasRooms)) {
                fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
                logger.info("[fixedFeeItemHasRooms.size]:"+ fixedFeeItemHasRooms.size());
            }
        }
    }

    /**
     * 生成固定周期的账单
     * @param groupBuildingBillCycleConfig
     */
    private void createFixedCyclePayBills(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        //生成账期信息
        GroupBuildingBillCycle groupBuildingBillCycle = createCycle(groupBuildingBillCycleConfig);
        logger.info("[groupBuildingBillCycle]:"+ JSON.toJSONString(groupBuildingBillCycle));
        //只包含固定收费项（常规收费项）的才进行账单的生成：1：抄表收费，2：固定收费，3：临时收费',
        String feeTypes = groupBuildingBillCycleConfig.getFeeType();
        if(!DataUtil.isEmpty(feeTypes) && feeTypes.length()<=2 && feeTypes.contains("2")) {
            //生成账单信息
            List<PayBill> payBillList = new ArrayList<PayBill>();
            List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
            List<BigInteger> payBillIds = new ArrayList<BigInteger>();
            List<BigInteger> feeDetailIds = new ArrayList<BigInteger>();
            //常规项账单生成截止月份list
            List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
            createPayBillAndDetail(payBillList, propertyFeeDetailList, groupBuildingBillCycle, groupBuildingBillCycleConfig,payBillIds, feeDetailIds, fixedFeeItemHasRooms);
            logger.info("[payBillList.size]:"+ payBillList.size());
            logger.info("[propertyFeeDetailList.size]:"+ propertyFeeDetailList.size());
            if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailList)) {
                //保存账单、维护欠费关系
                savePayBillAndCarryoverUnpaid(groupBuildingBillCycleConfig, groupBuildingBillCycle, payBillList, propertyFeeDetailList);
            }

            //保存账单信息
            if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailList)) {
                payBillBaseDao.insertPayBillBatch(payBillList);
                propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
            }

            //更新常规项的费用生成截止时间
            if(!DataUtil.isEmpty(fixedFeeItemHasRooms)) {
                fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
            }
        }
    }

    private void savePayBillAndCarryoverUnpaid(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig, GroupBuildingBillCycle groupBuildingBillCycle, List<PayBill> payBillList, List<PropertyFeeDetail> propertyFeeDetailList) {
        //进行欠费账单记录（欠费关系维护）
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getArrearsTransfer()) && groupBuildingBillCycleConfig.getArrearsTransfer().equals(2)) {
            autoCarryoverUnPaid(groupBuildingBillCycle.gettGroupBuildingId(), groupBuildingBillCycleConfig.getBillName(), payBillList, groupBuildingBillCycleConfig.getChargingMode());
        }
    }

    private void updateCycleCfgDate(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        Date start = DateUtils.convertStrToDate(groupBuildingBillCycleConfig.getBillMonthStart());
        Date end = DateUtils.convertStrToDate(groupBuildingBillCycleConfig.getBillMonthEnd());
        Date payStart = DateUtils.convertStrToDate(groupBuildingBillCycleConfig.getBillPayStart());
        Date payEnd = DateUtils.convertStrToDate(groupBuildingBillCycleConfig.getBillPayEnd());
        //计算时间间隔
        int billMonths = DateUtils.getDiffMonths(start, end) + 1;
        Date startDate = DateUtils.addMonths(start, billMonths);
        Date endDate = DateUtils.addMonths(end, billMonths);
        Date payDate = DateUtils.addMonths(payStart, billMonths);
        Date payEndDate = DateUtils.addMonths(payEnd, billMonths);

        groupBuildingBillCycleConfig.setBillMonthStart(DateUtils.convertDateToStr(startDate, "yyyy-MM-dd"));
        groupBuildingBillCycleConfig.setBillMonthEnd(DateUtils.convertDateToStr(endDate, "yyyy-MM-dd"));
        groupBuildingBillCycleConfig.setBillPayStart(DateUtils.convertDateToStr(payDate, "yyyy-MM-dd HH:mm:ss"));
        groupBuildingBillCycleConfig.setBillPayEnd(DateUtils.convertDateToStr(payEndDate, "yyyy-MM-dd HH:mm:ss"));

        //更新配置时间
        groupBuildingBillCycleConfig.setSys0UpdTime(DateUtils.getCurrentDate());
        groupBuildingBillCycleConfigBaseService.updateGroupBuildingBillCycleConfig(groupBuildingBillCycleConfig);
    }

    private GroupBuildingBillCycle createCycle (GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(groupBuildingBillCycleConfig.gettGbId());
        //生成账单费用名称
        PayBillType payBillType = new PayBillType();
        payBillType.setId(uuidManager.getNextUuidBigInteger("t_pay_bill_type"));
        payBillType.setName(groupBuildingBillCycleConfig.getBillName());
        payBillType.setGbId(groupBuildingBillCycleConfig.gettGbId());
        payBillType.setIcon(PropIconUtil.getBillIcon(groupBuildingBillCycleConfig.getBillName()));
        payBillType.setPreferStatus(groupBuilding.getIsPrefer());//v507自动生成账期不再对这两个字段进行维护,默认为随机立减和开启缴费
        payBillType.setActiveStatus(groupBuilding.getPropfeeCanpay());
        payBillType.setPaytimeType(2);//暂时以原来周期缴费的方式存储
        payBillType.setIsPropFee(2);
        payBillType.setSys0AddTime(DateUtils.getCurrentDate());
        payBillType.setSys0UpdTime(DateUtils.getCurrentDate());
        payBillType.setLastUpdTime(DateUtils.getCurrentDate());
        payBillTypeBaseDao.insertPayBillType(payBillType);

        //生成账期信息
        GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
        groupBuildingBillCycle.setId(uuidManager.getNextUuidBigInteger("t_group_building_bill_cycle"));
        groupBuildingBillCycle.settGroupBuildingId(groupBuildingBillCycleConfig.gettGbId());
        groupBuildingBillCycle.settPayBillTypeId(payBillType.getId());
        groupBuildingBillCycle.setBillPayStart(groupBuildingBillCycleConfig.getBillPayStart());
        groupBuildingBillCycle.setBillPayEnd(groupBuildingBillCycleConfig.getBillPayEnd());
        groupBuildingBillCycle.setBillMonthStart(groupBuildingBillCycleConfig.getBillMonthStart());
        groupBuildingBillCycle.setBillMonthEnd(groupBuildingBillCycleConfig.getBillMonthEnd());
        groupBuildingBillCycle.setPaytimeType(2);
        groupBuildingBillCycle.setFeeType(groupBuildingBillCycleConfig.getFeeType());
        groupBuildingBillCycle.setGbbcCfgId(groupBuildingBillCycleConfig.getId());
        groupBuildingBillCycle.setIsCollectArrears(groupBuildingBillCycleConfig.getArrearsTransfer());
        groupBuildingBillCycle.setPeriodMonths(groupBuildingBillCycleConfig.getPeriodMonths());
        groupBuildingBillCycle.setChargingMode(groupBuildingBillCycleConfig.getChargingMode());
        groupBuildingBillCycle.setBankCollectionStatus(groupBuildingBillCycleConfig.getBankCollectionStatus());
        groupBuildingBillCycleBaseDao.insertGroupBuildingBillCycle(groupBuildingBillCycle);

        return groupBuildingBillCycle;
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

                    //更新常规项的费用生成截止时间
                    FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
                    fixedFeeItemHasRoom.setId(propertyFeeDetailTemp.getTargetId());
                    fixedFeeItemHasRoom.setCreateBillMonth(DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(groupBuildingBillCycle.getBillMonthEnd()), 1), "yyyy-MM-dd"));
                    fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
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

    /**
     * 查询常规费用的生成账单的最大时间
     * @param feeName
     * @param realRoomId
     * @return
     */
    public String getMaxBillEndMonthByFixedItem(String feeName, BigInteger realRoomId) {
        String billMonth = groupBuildingCycleCfgDao.getMaxBillEndMonthByFixedItem(feeName, realRoomId);
        if(!DataUtil.isEmpty(billMonth)) {
            return DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billMonth), 1), "yyyy-MM-dd");
        }
        return null;
    }

    /**
     * 计算选择周期月份数组
     * @return
     */
    private List<Map<String, String>> getMonthsForCyclePay(GroupBuildingBillCycleConfig groupBuildingBillCycleConfig) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        String billMonthStart = groupBuildingBillCycleConfig.getBillMonthStart();
        String billMonthEnd = groupBuildingBillCycleConfig.getBillMonthEnd();
        String billPayStart = groupBuildingBillCycleConfig.getBillPayStart();
        String billPayEnd = groupBuildingBillCycleConfig.getBillPayEnd();

        //无论是什么模式都默认第一个周期是要生成账单的
        Map<String, String> map = new HashMap<String, String>();
        map.put("billMonthStart", billMonthStart);
        map.put("billMonthEnd", billMonthEnd);
        map.put("billPayStart", billPayStart);
        map.put("billPayEnd", billPayEnd);
        list.add(map);

        if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.ALTER_CYCLE)) {
            String periodMonths = groupBuildingBillCycleConfig.getPeriodMonths();
            String[] strings = periodMonths.split(",");
            //设置的最大月份数
            int maxMonth = getMaxByArray(strings);
            //配置的缴费期间月份数
            int monthSize = getMonthToMonthSize(billMonthStart, billMonthEnd);
            for (int i = 1; i < maxMonth/monthSize; i++) {//需要生成多少期数据

                billMonthStart = DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billMonthStart, "yyyy-MM-dd"), monthSize), "yyyy-MM-dd");
                billMonthEnd = DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billMonthEnd, "yyyy-MM-dd"), monthSize), "yyyy-MM-dd");

                billPayStart = DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billPayStart, "yyyy-MM-dd"), monthSize), "yyyy-MM-dd");
                billPayEnd = DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billPayEnd, "yyyy-MM-dd"), monthSize), "yyyy-MM-dd");

                Calendar dateStart = Calendar.getInstance();
                dateStart.setTime(DateUtils.convertStrToDate(billPayStart, "yyyy-MM-dd"));
                int day = dateStart.get(Calendar.DAY_OF_MONTH);
                if(day >= 28 && i > 1) {//自动生成的第二个日期开始，如果时间为为28号，自动顺延至下月一号
                    dateStart.set(dateStart.get(Calendar.YEAR), dateStart.get(Calendar.MONTH) + 1, 1);
                    billPayStart = DateUtils.convertDateToStr(dateStart.getTime(), "yyyy-MM-dd");
                }

                Map<String, String> map1 = new HashMap<String, String>();
                map1.put("billMonthStart", billMonthStart);
                map1.put("billMonthEnd", billMonthEnd);
                map1.put("billPayStart", billPayStart);
                map1.put("billPayEnd", billPayEnd);
                list.add(map1);
            }
        }

        return list;
    }

    private static int getMaxByArray(String[] strings) {
        int max = 0;//最大月份数
        for (int i = 0; i < strings.length; i++) {
            int tmp = Integer.parseInt(strings[i]);
            if(tmp > max) {
                max = tmp;
            }
        }
        return max;
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

    /**
     * 查询小区的账期配置
     * @param paramMap
     * @return
     */
    public List<GroupBuildingBillCycleConfigEntity> getGroupBuildingBillCycleConfigByCondition(Map<String, Object> paramMap) {
        return groupBuildingCycleCfgDao.getGroupBuildingBillCycleConfigByCondition(paramMap);
    }

    /**
     * 查询小区包含的费用项信息
     * @param gbId
     * @param cycleType
     * @return
     */
    public GroupBuildingHasFeeItemEntity getBuildingHasFeeItemEntitiesByGbId(BigInteger gbId, Integer cycleType) {
        GroupBuildingHasFeeItemEntity groupBuildingHasFeeItemEntity = new GroupBuildingHasFeeItemEntity();
        //常规
        List<FixedFeeItem> buildingHasFixedFeeItemList = groupBuildingCycleCfgDao.getBuildingHasFixedFeeItemList(gbId);
        groupBuildingHasFeeItemEntity.setFixedFeeItems(buildingHasFixedFeeItemList);

        if(cycleType == 1) {//固定收费的才有这两项
            //抄表
            List<MrFeeItem> buildingHasMrFeeItemList = groupBuildingCycleCfgDao.getBuildingHasMrFeeItemList(gbId);
            groupBuildingHasFeeItemEntity.setMrFeeItems(buildingHasMrFeeItemList);
            //临时
            List<TmpFeeItem> buildingHasTmpFeeItemList = groupBuildingCycleCfgDao.getBuildingHasTmpFeeItemList(gbId);
            groupBuildingHasFeeItemEntity.setTmpFeeItems(buildingHasTmpFeeItemList);
        }

        return groupBuildingHasFeeItemEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveCollectFeesCfg(GroupBuildingBillCycleConfigEntity groupBuildingBillCycleConfig) {
        Boolean saveOrUpdate = DataUtil.isEmpty(groupBuildingBillCycleConfig.getId());
        boolean isSuccess = true;
        if(!saveOrUpdate) {
        	isSuccess=groupBuildingBillCycleConfigBaseService.updateGroupBuildingBillCycleConfig(groupBuildingBillCycleConfig)>0;
        } else {
            CnfantasiaCommbusiUtil.newStandard(groupBuildingBillCycleConfig, SEQConstants.t_group_building_bill_cycle_config);
            isSuccess &= (groupBuildingBillCycleConfigBaseService.insertGroupBuildingBillCycleConfig(groupBuildingBillCycleConfig)>0);
            
            if(CycleCfgDict.RechargeMode.PROPERTY_RECHARGE.compareTo(groupBuildingBillCycleConfig.getChargingMode())==0) {
            	Map<String, Object> paramMap = new HashMap<String, Object>();
            	paramMap.put("tGroupBuildingFId", groupBuildingBillCycleConfig.gettGbId());
            	int count = propertyRechargePreferGroupbuildingBaseDao.selectPropertyRechargePreferGroupbuildingCount(paramMap, false);
            	if(count==0) {
            		List<PropertyRechargePreferRule> propertyRechargePreferRuleList = propertyRechargePreferRuleBaseDao.selectPropertyRechargePreferRuleByCondition(null, false);
            		if(propertyRechargePreferRuleList!=null && propertyRechargePreferRuleList.size()>0) {
            			PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding = new PropertyRechargePreferGroupbuilding();
            			propertyRechargePreferGroupbuilding.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_recharge_prefer_groupbuilding));
            			propertyRechargePreferGroupbuilding.settGroupBuildingFId(groupBuildingBillCycleConfig.gettGbId());
            			propertyRechargePreferGroupbuilding.settPropertyRechargePreferRuleFId(propertyRechargePreferRuleList.get(0).getId());
            			
            			propertyRechargePreferGroupbuildingBaseDao.insertPropertyRechargePreferGroupbuilding(propertyRechargePreferGroupbuilding);
            		}
            	}
            }
        }

        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getFixedFeeItemsIds())) {
            for (BigInteger bigInteger : groupBuildingBillCycleConfig.getFixedFeeItemsIds()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", bigInteger);
                map.put("type", 2);
                itemList.add(map);
            }
        }
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getMrFeeItemsIds())) {
            for (BigInteger bigInteger : groupBuildingBillCycleConfig.getMrFeeItemsIds()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", bigInteger);
                map.put("type", 1);
                itemList.add(map);
            }
        }
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfig.getTmpFeeItemsIds())) {
            for (BigInteger bigInteger : groupBuildingBillCycleConfig.getTmpFeeItemsIds()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", bigInteger);
                map.put("type", 3);
                itemList.add(map);
            }
        }
        if(!DataUtil.isEmpty(itemList)) {
            //更新小区费用关系表
            groupBuildingCycleCfgDao.saveGroupBuildingBillCycleConfigHasFeeItem(itemList, groupBuildingBillCycleConfig.getId(), groupBuildingBillCycleConfig.gettGbId());
        }

        //初始化以往欠费信息:新增才进行初始化
        if(groupBuildingBillCycleConfig.getChargingMode().equals(CycleCfgDict.RechargeMode.ALTER_CYCLE)) {
            //initAlterCycleData(groupBuildingBillCycleConfig);
            if(saveOrUpdate) {
                initAlterCycleData(groupBuildingBillCycleConfig);
            }
        }

        return isSuccess;
    }

    /**
     * 初始化选择周期数据：费用起始时间2016-5
     * @param groupBuildingBillCycleConfig
     */
    private void initAlterCycleData(GroupBuildingBillCycleConfigEntity groupBuildingBillCycleConfig) {
        //查询当前小区下所有门牌的数据详情:仅包含所配置的收费项
        List<FixedFeeItemInitEntity> fixedFeeItemInitEntityList = groupBuildingCycleCfgDao.getNeedInitFixedData(groupBuildingBillCycleConfig.getFixedFeeItemsIds());
        if(DataUtil.isEmpty(fixedFeeItemInitEntityList)) return;
        String minDate = groupBuildingCycleCfgDao.getNeedInitFixedDataMinDate(groupBuildingBillCycleConfig.getFixedFeeItemsIds());
        if(DataUtil.isEmpty(minDate)) return;
        Date date = DateUtils.convertStrToDate(minDate, "yyyy-MM");

        int monthToMonthSize = getMonthToMonthSize(minDate, groupBuildingBillCycleConfig.getBillMonthStart());
        //生成账期--费用
        List<GroupBuildingBillCycle> billCycles = new ArrayList<GroupBuildingBillCycle>();
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

        //生成账单
        for (int i = 0; i < fixedFeeItemInitEntityList.size(); i++) {
            List<PayBill> payBillList = new ArrayList<PayBill>();
            List<PropertyFeeDetail> feeDetails = new ArrayList<PropertyFeeDetail>();
            List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
            List<BigInteger> payBillIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, fixedFeeItemInitEntityList.size()*billCycles.size());
            int k = 0;
            for (GroupBuildingBillCycle billCycle : billCycles) {
                Date billStartDate = DateUtils.convertStrToDate(billCycle.getBillMonthStart(), "yyyy-MM");
                BigDecimal amount = BigDecimal.ZERO;
                FixedFeeItemInitEntity fixedFeeItemInitEntity = fixedFeeItemInitEntityList.get(i);
                List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
                for (int i1 = 0; i1 < fixedFeeItemInitEntity.getFixedFeeItemHasRooms().size(); i1++) {
                    FixedFeeItemHasRoom fixedFeeItemHasRoom = fixedFeeItemInitEntity.getFixedFeeItemHasRooms().get(i1);
                    Date feeStartDate = DateUtils.convertStrToDate(fixedFeeItemHasRoom.getBillMonthStart(), "yyyy-MM");
                    if(feeStartDate.getTime() <= billStartDate.getTime()) {
                        //组装费用明细
                        PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
                        propertyFeeDetail.setName(fixedFeeItemHasRoom.getName());
                        //费用类型=={"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费","9":"其它"}
                        //固定收费项的费用类型设置为1管理费，因为物业宝仅会抵扣管理费和主体金，固定收费全部可用进行物业宝抵扣
                        propertyFeeDetail.setType(PropertyFeeDetailTypeDict.GUAN_LI);
                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getTotalPrice())) {
                            propertyFeeDetail.setTotalPrice(BigDecimal.valueOf(fixedFeeItemHasRoom.getTotalPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
                        }
                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getSignalPrice())) {
                            propertyFeeDetail.setSignalPrice(fixedFeeItemHasRoom.getSignalPrice().doubleValue());//单价
                        }
                        if (!DataUtil.isEmpty(fixedFeeItemHasRoom.getPriceUnitValue())) {
                            propertyFeeDetail.setPriceUnitValue(fixedFeeItemHasRoom.getPriceUnitValue());//用量/面积
                        }
                        propertyFeeDetail.settPayBillFId(payBillIds.get(k));
                        propertyFeeDetail.settCycleId(billCycle.getId());
                        propertyFeeDetail.setFeeType(FeeTypeDict.Gu_Ding);
                        propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDate());
                        propertyFeeDetail.setAllowancePrice(0l);
                        propertyFeeDetail.setBillMonthSize(1L);
                        BigDecimal detailAmt = BigDecimal.valueOf(fixedFeeItemHasRoom.getTotalPrice()).multiply(new BigDecimal(propertyFeeDetail.getBillMonthSize())).setScale(0, BigDecimal.ROUND_HALF_UP);
                        amount = amount.add(detailAmt);
                        if(detailAmt.doubleValue() > 0) {
                            propertyFeeDetailList.add(propertyFeeDetail);
                        }
                        fixedFeeItemHasRoom.setCreateBillMonth(DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(billCycle.getBillMonthEnd()), 1), "yyyy-MM-dd"));
                        fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
                    }
                }

                if(DataUtil.isEmpty(propertyFeeDetailList)) continue;

                //组装账单信息
                PayBill payBill = new PayBill();
                payBill.setId(payBillIds.get(k));
                payBill.setAmount(amount.longValue());
                payBill.setIsPay(2);//未缴
                payBill.settRealRoomFId(fixedFeeItemInitEntity.getRealRoomId());
                payBill.setPropertyProprietorId(fixedFeeItemInitEntity.getPropertyProprietorFId().toString());
                payBill.setBillTypeId(billCycle.gettPayBillTypeId());
                payBill.setIsPropFee(1);//都设置为物业费
                payBill.setBillMonthSize(1L);//默认都是一个月的：初始化数据都是生成一个月的
                payBill.setBillMonthStart(billCycle.getBillMonthStart());
                payBill.setBillMonthEnd(billCycle.getBillMonthEnd());
                payBill.setPayDayStart(billCycle.getBillPayStart());
                payBill.setPayDayEnd(billCycle.getBillPayEnd());
                BigInteger ptcId = billCycle.gettPayBillTimeCfgId();
                if(ptcId==null){
                    ptcId = BigInteger.valueOf(1);/*数据库中提示不能为空 所以写了一个默认值*/
                }
                payBill.setBillTimeCfgId(ptcId);
                payBill.setBillTypeName(groupBuildingBillCycleConfig.getBillName());
                payBill.setPaytimeType(2);/*默认改为 以前的周期缴费方式*/
                payBill.setPreferType(1);
                payBill.setCycleType(billCycle.getChargingMode());//固定周期缴费(1固定，2选择周期)
                payBill.settBillCycleId(billCycle.getId());
                payBill.setSys0AddTime(DateUtils.getCurrentDate());
                payBill.setBankCollectionStatus(0);
                if(payBill.getAmount() > 0 ) {
                    payBillList.add(payBill);
                    feeDetails.addAll(propertyFeeDetailList);
                    k ++;
                }
            }

            //保存账单和明细
            payBillBaseDao.insertPayBillBatch(payBillList);

            CnfantasiaCommbusiUtil.newStandardList(feeDetails, SEQConstants.t_property_fee_detail);
            propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(feeDetails);

            //更新账单生成截止时间
            if(!DataUtil.isEmpty(fixedFeeItemHasRooms)) {
                fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("2017-07-22".substring(0,7));
    }

    public String isHasSameFeeItem(GroupBuildingBillCycleConfigEntity groupBuildingBillCycleConfig) {
        String str = "";

        List<String> strs = groupBuildingCycleCfgDao.getHasFeeItems(groupBuildingBillCycleConfig);
        for (String s : strs) {
            str += s + "、";
        }

        return str;
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
     * 删除收费账单配置
     *
     * @param id
     * @param userId
     * @return
     */
    public int delCollectFeesCfg(BigInteger id, BigInteger userId) {
        return groupBuildingCycleCfgDao.delCollectFeesCfg(id, userId);
    }

    /**
     * 查询需要进行计算欠费的选择周期信息
     * @return
     */
    public List<AlterUnPaidEntity> getNeedUnpaidPayBillAndCycle() {
        return groupBuildingCycleCfgDao.getNeedUnpaidPayBillAndCycle();
    }

    /**
     * 根据 itemHashRoomId查询到需要进行更新的账单信息
     * @param itemHasRoomIds
     * @return
     */
    public List<UpdatePayBillEntity> getNeedUpdatedPayBills(List<BigInteger> itemHasRoomIds) {
        return groupBuildingCycleCfgDao.getNeedUpdatedPayBills(itemHasRoomIds);
    }

    public List<GroupBuildingBillCycle> getGroupBuildingBillCycleByItemId(BigInteger itemId) {
        return groupBuildingCycleCfgDao.getGroupBuildingBillCycleByItemId(itemId);
    }

    /**
     * 根据房间ID查询对应的选择周期缴费的所有未缴账单
     * @param realRoomId
     * @return
     */
    public List<UpdatePayBillEntity> getNeedUpdatedPayBillsByRoom(UpdatePayBillInitEntity updatePayBillInitEntity) {
        BigInteger realRoomId = updatePayBillInitEntity.getRealRoomId();
        List<BigInteger> itemIds = new ArrayList<BigInteger>();
        for (FixedFeeItemHasRoom fixedFeeItemHasRoom : updatePayBillInitEntity.getFixedFeeItemHasRoomList()) {
            itemIds.add(fixedFeeItemHasRoom.gettFixedFeeItemId());
        }
        return groupBuildingCycleCfgDao.getNeedUpdatedPayBillsByRoom(realRoomId, itemIds);
    }

    public List<AlterUnPaidEntity> getNeedUnpaidPayBillAndCycle02(BigInteger cycleCfgId) {
        return groupBuildingCycleCfgDao.getNeedUnpaidPayBillAndCycle02(cycleCfgId);
    }

    public List<GroupBuildingBillCycleConfig> getBuildingBillCycleConfigsByItemIds(List<BigInteger> itemIds) {
        return groupBuildingCycleCfgDao.getBuildingBillCycleConfigsByItemIds(itemIds);
    }

	    /**
     * 保存滞纳金配置
     * @param groupBuildingCalculateLatefeeRule
     */
    public int savelateFeeCfg(GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule) {
        if(DataUtil.isEmpty(groupBuildingCalculateLatefeeRule.getCalculateType())) {//空的话给默认值0
            groupBuildingCalculateLatefeeRule.setCalculateDaysByMonth(0L);
        }
        int i = 0;
        if(!DataUtil.isEmpty(groupBuildingCalculateLatefeeRule.getId())) {
            i = groupBuildingCalculateLatefeeRuleBaseService.updateGroupBuildingCalculateLatefeeRule(groupBuildingCalculateLatefeeRule);
        } else {
            CnfantasiaCommbusiUtil.newStandard(groupBuildingCalculateLatefeeRule, SEQConstants.t_group_building_calculate_latefee_rule);
            i = groupBuildingCalculateLatefeeRuleBaseService.insertGroupBuildingCalculateLatefeeRule(groupBuildingCalculateLatefeeRule);
        }

        return i;
    }
	
    public List<MrFeeItem> getMrFeeItemByCondition(Map<String, Object> mriQryMap) {
        return groupBuildingCycleCfgDao.getMrFeeItemByCondition(mriQryMap);
    }

    public List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> mriQryMap) {
        return groupBuildingCycleCfgDao.getTmpFeeItemByCondition(mriQryMap);
    }
}
