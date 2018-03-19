package com.cnfantasia.server.ms.revenue.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.groupBuildingCycleCfg.service.GroupBuildingCycleCfgService;
import com.cnfantasia.server.api.plotproperty.service.FinanceService;
import com.cnfantasia.server.api.plotproperty.util.FinanceDeductionRunnable;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.ms.revenue.entity.AlterUnPaidEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: CreateCycleAndPayBillTask
 * @Date: 2017-05-08 11:23
 * @Auther: yanghua
 * @Description:(自动生成账期、账单)
 */
public class CreateCycleAndPayBillTask implements ISynTask{
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private GroupBuildingCycleCfgService groupBuildingCycleCfgService;
    @Resource
    protected FinanceService financeService;

    @Override
    public Integer execTask() {
        //查询需要自动生成账期的数据
        List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigsForAuto = groupBuildingCycleCfgService.getGroupBuildingBillCycleConfigsForAuto();
        logger.info("[groupBuildingBillCycleConfigsForAuto]:"+ JSON.toJSONString(groupBuildingBillCycleConfigsForAuto));
        if(!DataUtil.isEmpty(groupBuildingBillCycleConfigsForAuto)) {
            groupBuildingCycleCfgService.autoCreateCycleAndPayBill(groupBuildingBillCycleConfigsForAuto);

            //物业宝抵扣
            FutureTask<Boolean> task = new FutureTask<Boolean>(new FinanceDeductionRunnable(financeService, null));
            new Thread(task).start();
        }

        //选择周期欠费计算
        List<AlterUnPaidEntity> alterUnPaidEntities = groupBuildingCycleCfgService.getNeedUnpaidPayBillAndCycle();
        logger.info("[alterUnPaidEntities]:"+ JSON.toJSONString(alterUnPaidEntities));
        for (AlterUnPaidEntity alterUnPaidEntity : alterUnPaidEntities) {
            logger.info("[alterUnPaidEntity]:"+ JSON.toJSONString(alterUnPaidEntity));
            groupBuildingCycleCfgService.autoCarryoverUnPaid(alterUnPaidEntity.gettGroupBuildingId(), alterUnPaidEntity.getBillName(), alterUnPaidEntity.getPayBills(), alterUnPaidEntity.getChargingMode());
        }


        return null;
    }


    public static void main(String[] args) {
        Date start = DateUtils.convertStrToDate("2017-07-01 17:46:48");
        Date end = DateUtils.convertStrToDate("2017-07-01 17:46:48");
        Date payStart = DateUtils.convertStrToDate("2017-07-01 17:46:48");
        Date payEnd = DateUtils.convertStrToDate("2017-07-01 17:46:48");
        //计算时间间隔
        for (int i = 0; i < 12; i++) {
            int billMonths = DateUtils.getDiffMonths(start, end) + 1;
            int payBillMonths = DateUtils.getDiffMonths(payStart, payEnd) + 1;
            start = DateUtils.addMonths(start, billMonths);
            end = DateUtils.addMonths(end, billMonths);
            payStart = DateUtils.addMonths(payStart, payBillMonths);
            payEnd = DateUtils.addMonths(payEnd, payBillMonths);
        }

        System.out.println(DateUtils.convertDateToStr(start,"yyyy-MM-dd")+"==="+DateUtils.convertDateToStr(end,"yyyy-MM-dd"));
        System.out.println(DateUtils.convertDateToStr(payStart,"yyyy-MM-dd")+"==="+DateUtils.convertDateToStr(payEnd,"yyyy-MM-dd"));
    }
}
