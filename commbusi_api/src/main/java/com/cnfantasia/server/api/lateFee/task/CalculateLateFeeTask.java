package com.cnfantasia.server.api.lateFee.task;

import com.cnfantasia.server.api.lateFee.calculateMethod.CalculateLateFeeByGbImp;
import com.cnfantasia.server.api.lateFee.dao.CalculateLateFeeDao;
import com.cnfantasia.server.api.lateFee.util.CalculateLateFeeRunnable;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.entity.GroupBuildingCalculateLatefeeRule;
import com.cnfantasia.server.domainbase.groupBuildingCalculateLatefeeRule.service.IGroupBuildingCalculateLatefeeRuleBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.service.IPropertyFeeDetailBaseService;
import com.cnfantasia.server.ms.revenue.task.ISynTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * @className: CalculateLateFeeTask
 * @date: 2017-11-08 18:34
 * @author: yanghua
 * @description:(定时计算滞纳金)
 */
public class CalculateLateFeeTask implements ISynTask{
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private IGroupBuildingCalculateLatefeeRuleBaseService groupBuildingCalculateLatefeeRuleBaseService;
    @Resource
    private CalculateLateFeeDao calculateLateFeeDao;

    @Override
    public Integer execTask() {
        List<GroupBuildingCalculateLatefeeRule> groupBuildingCalculateLatefeeRuleByCondition = groupBuildingCalculateLatefeeRuleBaseService.getGroupBuildingCalculateLatefeeRuleByCondition(null);
        logger.debug("[groupBuildingCalculateLatefeeRuleByCondition]=" + groupBuildingCalculateLatefeeRuleByCondition);
        for (GroupBuildingCalculateLatefeeRule groupBuildingCalculateLatefeeRule : groupBuildingCalculateLatefeeRuleByCondition) {
            CalculateLateFeeByGbImp calculateLateFeeByGbImp = (CalculateLateFeeByGbImp) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeByGbImp");
            FutureTask<Boolean> booleanFutureTask = new FutureTask<>(new CalculateLateFeeRunnable(calculateLateFeeByGbImp, groupBuildingCalculateLatefeeRule.gettGbId(), new BigInteger("1")));
            booleanFutureTask.run();
        }

        //物理清除明细表中的已经删除的滞纳金明细
       /* Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("feeType", FeeTypeDict.LATEFEE);
        calculateLateFeeDao.deleteLogicLateFeeDetails(paraMap);*/
        return null;
    }
}
