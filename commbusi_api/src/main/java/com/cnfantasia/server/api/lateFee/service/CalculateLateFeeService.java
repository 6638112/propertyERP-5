package com.cnfantasia.server.api.lateFee.service;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.lateFee.constant.LateFeeDict;
import com.cnfantasia.server.api.lateFee.dao.CalculateLateFeeDao;
import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyFeeDetail.service.IPropertyFeeDetailBaseService;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

/**
 * @className: CalculateLateFeeService
 * @date: 2017-10-31 16:05
 * @author: yanghua
 * @description:(滞纳金计算)
 */
public class CalculateLateFeeService {
    private static Log logger = LogFactory.getLog(CalculateLateFeeService.class);
    @Resource
    private IPayBillService payBillService;
    @Resource
    private IPropertyFeeDetailBaseService propertyFeeDetailBaseService;
    @Resource
    private CalculateLateFeeDao calculateLateFeeDao;

    /**
     * 计算滞纳金
     * @param payBills需要更新滞纳金的账单
     * @param hasCalculateLateFeeItems 需要计算滞纳金的费用项ID
     *@param userId  @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean calculate(List<PayBillForLateFee> payBills, FeeItemHasLateFee feeItemHasLateFee, BigInteger userId) {
        //需要新增的滞纳金明细
        List<PropertyFeeDetail> propertyFeeDetailsForAdd = new ArrayList<>();
        //需要更新的账单
        List<PayBill> payBillList = new ArrayList<>();
        //需要更新的账单ID:包含当期和欠费
        List<BigInteger> payBillIds = new ArrayList<>();
        //更新明细数
        Integer detailCount = 0;
        //更新账单数
        Integer billCount = 0;

        //组装数据
        for (PayBillForLateFee payBill : payBills) {
            logger.debug("[payBill--latefee--"+payBill.getId()+"]" + JSON.toJSONString(payBill));
            GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule = payBill.getGroupBuildingCalculateLatefeeRule();
            logger.debug("[groupBuildingCalculateLatefeeRule]" + JSON.toJSONString(groupBuildingCalculateLatefeeRule));
            //判断是否到计算滞纳金的时间（第一次需要进行收取时间的判断，只要生成过滞纳金后就不用判断了，因为只要收取过那就是刷新滞纳金）
            //系统当前日
            Calendar systemTime = Calendar.getInstance();
            systemTime.setTime(new Date());
            int currentDay = systemTime.get(Calendar.DAY_OF_MONTH);
            //账单没有生成过滞纳金，并且系统时间没有到收取滞纳金的时间则不进行计算滞纳金
            if(!isHasCreatedLateFee(payBill.getId()) && currentDay < groupBuildingCalculateLatefeeRule.getCalculateStart()) {
                continue;
            }

            //计算每个费用项的滞纳金金额（key=费用项@feeType  用于处理不同类型之间费用项名称可以重复）
            Map<String, Double> lateFeeItems = new HashMap<>();
            for (UnPaidPayBillEntity unPaidPayBillEntity : payBill.getUnPaidPayBillEntities()) {
                logger.debug("[unPaidPayBillEntity]" + JSON.toJSONString(unPaidPayBillEntity));
                for (PropertyFeeDetail propertyFeeDetail : unPaidPayBillEntity.getPropertyFeeDetailList()) {
                    logger.debug("[propertyFeeDetail]" + JSON.toJSONString(propertyFeeDetail));
                    Integer feeType = propertyFeeDetail.getFeeType();
                    String feeName = propertyFeeDetail.getName();
                    String mapKey = feeName + "@" + feeType;

                    //是否需要计算滞纳金
                    if(FeeTypeDict.Chao_Biao.equals(feeType) && !feeItemHasLateFee.getMrFeeItemNames().contains(feeName)) {
                        continue;
                    }
                    if(FeeTypeDict.Gu_Ding.equals(feeType) && !feeItemHasLateFee.getFixedFeeItemNames().contains(feeName)) {
                        continue;
                    }
                    if(FeeTypeDict.Lin_Shi.equals(feeType) && !feeItemHasLateFee.getTmpFeeItemNames().contains(feeName)) {
                        continue;
                    }

                    //计算滞纳金金额
                    Double lateFee = calculateLateFee(unPaidPayBillEntity, propertyFeeDetail, groupBuildingCalculateLatefeeRule);
                    if(!DataUtil.isEmpty(lateFeeItems.get(mapKey))) {
                        lateFee = BigDecimal.valueOf(lateFee).add(BigDecimal.valueOf(lateFeeItems.get(mapKey))).doubleValue();
                    }
                    lateFeeItems.put(mapKey, lateFee);
                }
                payBillIds.add(unPaidPayBillEntity.getId());
            }

            logger.debug("[lateFeeItems]" + JSON.toJSONString(lateFeeItems));
            //如果没有需要计算的滞纳金费用项 则跳过
            if(DataUtil.isEmpty(lateFeeItems)) {
                continue;
            }

            //组装账单明细
            Long totalLateFee = 0L;
            for(Map.Entry<String, Double> entry : lateFeeItems.entrySet()) {
                //费用项名称=原费用项名称 + 滞纳金
                String feeName = entry.getKey().split("@")[0] + "滞纳金";
                Double totalAmount = entry.getValue();
                PropertyFeeDetail propertyFeeDetail = createPropertyFeeDetail(payBill, feeName, totalAmount);
                propertyFeeDetailsForAdd.add(propertyFeeDetail);
                totalLateFee += totalAmount.longValue();
            }

            //更新账单金额
            Long payBillAmount = payBill.getAmount() + totalLateFee;
            payBill.setAmount(payBillAmount);
            payBillList.add(payBill);
            payBillIds.add(payBill.getId());
            logger.debug("[payBill--latefee--"+payBill.getId()+"]" + JSON.toJSONString(payBill));
        }

        //更新数据
        //删除需要计算滞纳金金额的账单的上次计算滞纳金结果明细
        if(!DataUtil.isEmpty(payBillIds)) {
            int i = calculateLateFeeDao.deleteOldLateFeeDetailByPayBillIds(payBillIds);
        }

        //新增滞纳金明细
        if(!DataUtil.isEmpty(propertyFeeDetailsForAdd)) {
            detailCount += addLateFee(propertyFeeDetailsForAdd, userId);
        }

        //更新账单金额
        if(!DataUtil.isEmpty(payBillIds)) {
            billCount = updatePayBillAmount(payBillIds, userId);
        }

        return (billCount + detailCount) > 0;
    }

    /**
     * 组装明细信息
     *
     * @param payBill
     * @param feeName
     * @param totalAmount
     * @return
     */
    private PropertyFeeDetail createPropertyFeeDetail(PayBillForLateFee payBill, String feeName, Double totalAmount) {
        PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
        propertyFeeDetail.settPayBillFId(payBill.getId());
        propertyFeeDetail.settCycleId(payBill.gettBillCycleId());
        propertyFeeDetail.setName(feeName);
        propertyFeeDetail.setType(PayBillDict.PropertyFeeDetailDict.FeeType_Other);
        propertyFeeDetail.setTotalPrice(totalAmount);
        propertyFeeDetail.setFeeType(FeeTypeDict.LATEFEE);
        propertyFeeDetail.setAllowancePrice(0L);
        return propertyFeeDetail;
    }

    /**
     * 计算滞纳金（明细项）
     * @param unPaidPayBillEntity
     * @param propertyFeeDetail
     * @param groupBuildingCalculateLatefeeRule
     * @return
     */
    private Double calculateLateFee(UnPaidPayBillEntity unPaidPayBillEntity, PropertyFeeDetail propertyFeeDetail, GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule) {
        //滞纳天数
        int lateFeeDays =0;
        Date systemDate = new Date();
        //账单月份截止时间
        Date billMonthStart = DateUtils.convertStrToDate(unPaidPayBillEntity.getBillMonthEnd());
        //系统时间 和 账单月份开始时间相差的月份数
        int diffMonths = DateUtils.getDiffMonths(billMonthStart, systemDate);
        Calendar systemTime = Calendar.getInstance();
        systemTime.setTime(systemDate);

        //按月计算--天数
        if(LateFeeDict.CALCULATE_BY_MONTH.equals(groupBuildingCalculateLatefeeRule.getCalculateType())) {
            //按月计算天数大于0 并且收取号数大于系统号数
            if(!DataUtil.isEmpty(groupBuildingCalculateLatefeeRule.getCalculateDaysByMonth()) &&
                    groupBuildingCalculateLatefeeRule.getCalculateDaysByMonth().intValue() > 0) {
                lateFeeDays = diffMonths * groupBuildingCalculateLatefeeRule.getCalculateDaysByMonth().intValue();
            } else {
                Calendar billStartTime = Calendar.getInstance();
                billStartTime.setTime(billMonthStart);
                //计算滞纳金的时间为账单月份的下个月才开始
                billStartTime.add(Calendar.MONTH, 1);
                lateFeeDays = getCalculateByMonthDays(billStartTime.getTime(), systemDate, 0);
            }
        }
        //按天计算--天数
        if(LateFeeDict.CALCULATE_BY_DAY.equals(groupBuildingCalculateLatefeeRule.getCalculateType())) {
            //相差一个月情况下有两种情况:1.没开始计算滞纳金 2.已经开始计算滞纳金了（开始计算时间含当天），这里只处理开始计算滞纳金的情况
            if(diffMonths == 1) {
                //系统当前日
                int currentDay = systemTime.get(Calendar.DAY_OF_MONTH);
                lateFeeDays = currentDay - groupBuildingCalculateLatefeeRule.getCalculateStart().intValue();
            } else {
                Calendar instance = Calendar.getInstance();
                instance.setTime(billMonthStart);
                //往后延一个月：例如2017年9月1日的账单  开始计算滞纳金的时间为 2017年10月1日  滞纳金计算延后一个月，当期账单没有滞纳金
                instance.add(Calendar.MONTH , 1);
                int year = instance.get(Calendar.YEAR);
                //Calendar月份是从0开始的所以要加1
                int months = instance.get(Calendar.MONTH) + 1;
                int day = groupBuildingCalculateLatefeeRule.getCalculateStart().intValue();
                String calStr = year + "-" + months + "-" + day;
                lateFeeDays = getDateSpace(calStr, DateUtils.getCurrentDate());
            }
            //前面计算的是自然天数，实际滞纳金计算包含系统时间当天，所以需要加1
            lateFeeDays += 1;
        }
        logger.debug("[lateFeeDays]=" + lateFeeDays);
        BigDecimal latefeeRate = BigDecimal.valueOf(groupBuildingCalculateLatefeeRule.getCalculateRate()).divide(BigDecimal.valueOf(100));
        //滞纳金=滞纳天数*费用项金额*滞纳金利率
        //单位元:为保持和界面中看到的一致先转换成两位小数的元进行计算然后在转换成分保存
        BigDecimal bigDecimal = BigDecimal.valueOf(propertyFeeDetail.getTotalPrice()).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        //单位元
        BigDecimal lateFee = bigDecimal.multiply(BigDecimal.valueOf(lateFeeDays)).multiply(latefeeRate);
        //单位分
        lateFee = lateFee.multiply(BigDecimal.valueOf(100));
        logger.debug("[lateFee]=" + lateFee);

        return lateFee.doubleValue() < 0 ? 0 : lateFee.doubleValue();
    }

    /**
     * 判断账单是否已将计算过滞纳金了
     * @param payBill
     * @return
     */
    private boolean isHasCreatedLateFee(BigInteger payBillId) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("tPayBillFId", payBillId);
        paraMap.put("feeType", FeeTypeDict.LATEFEE);
        List<PropertyFeeDetail> propertyFeeDetailByCondition = propertyFeeDetailBaseService.getPropertyFeeDetailByCondition(paraMap);

        return !DataUtil.isEmpty(propertyFeeDetailByCondition) && propertyFeeDetailByCondition.size() > 0;
    }

    /**
     * 计算账单截止时间和系统时间之间的自然天数
     * @param afterBillMonthStart 账期截止日后一个月开始计算
     * @param systemDate
     * @param days
     * @return
     */
    private static int getCalculateByMonthDays(Date afterBillMonthStart, Date systemDate, int days) {
        Calendar systemTime = Calendar.getInstance();
        systemTime.setTime(systemDate);
        Calendar billMonthStartTime = Calendar.getInstance();
        billMonthStartTime.setTime(afterBillMonthStart);
        if(DateUtils.getDiffMonths(billMonthStartTime.getTime(), systemTime.getTime()) >= 0) {
            days += billMonthStartTime.getActualMaximum(Calendar.DATE);
            billMonthStartTime.add(Calendar.MONTH, 1);
            return getCalculateByMonthDays(billMonthStartTime.getTime(), systemTime.getTime(), days);
        }
        return days;
    }

    /**
     * 更新明细
     * @param propertyFeeDetails
     * @param userId
     * @return
     */
    private Integer updateLateFee(List<PropertyFeeDetail> propertyFeeDetails, BigInteger userId) {
        newStandardList(propertyFeeDetails, SEQConstants.t_property_fee_detail, userId, false);
        return propertyFeeDetailBaseService.insertPropertyFeeDetailBatch(propertyFeeDetails);
    }

    /**
     * 新增明细
     * @param propertyFeeDetails
     * @param userId
     * @return
     */
    private Integer addLateFee(List<PropertyFeeDetail> propertyFeeDetails, BigInteger userId) {
        newStandardList(propertyFeeDetails, SEQConstants.t_property_fee_detail, userId, true);
        return propertyFeeDetailBaseService.insertPropertyFeeDetailBatch(propertyFeeDetails);
    }

    /**
     * 更新账单
     * @param payBillIds
     * @param userId
     * @return
     */
    private Integer updatePayBillAmount(List<BigInteger> payBills, BigInteger userId) {
        return calculateLateFeeDao.updatePayBillLateFeeBatch(payBills, userId);
    }

    private static void newStandardList(List objs, String tableName, BigInteger userId, Boolean isInsert){
        try{
            List<BigInteger> objIds = null;
            if(isInsert) {
                IUuidManager uuidManager = (IUuidManager)CnfantasiaCommbusiUtil.getBeanManager("uuidManager");
                objIds = uuidManager.getNextUuidBigInteger(tableName, objs.size());
            }
            Method method = null;
            Object obj = null;
            for (int i = 0; i < objs.size(); i++) {
                obj = objs.get(i);
                Class clazz = obj.getClass();
                if(isInsert){
                    method = clazz.getMethod("setId",BigInteger.class);
                    method.invoke(obj, objIds.get(i));
                    method = clazz.getMethod("setSys0AddUser",BigInteger.class);
                    method.invoke(obj, userId);
                    method = clazz.getMethod("setSys0AddTime",String.class);
                    method.invoke(obj, CnfantasiaCommbusiUtil.getCurrentTime());
                } else {
                    method = clazz.getMethod("setSys0UpdUser",BigInteger.class);
                    method.invoke(obj, userId);
                    method = clazz.getMethod("setSys0UpdTime",String.class);
                    method.invoke(obj, CnfantasiaCommbusiUtil.getCurrentTime());
                }

                method = clazz.getMethod("setSys0DelState",Integer.class);
                method.invoke(obj, 0);
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private static int getDateSpace(String startDate, String endDate)
            throws ParseException {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();

        calst.setTime(DateUtils.convertStrToDate(startDate));
        caled.setTime(DateUtils.convertStrToDate(endDate));

        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;

        return days;
    }
}
