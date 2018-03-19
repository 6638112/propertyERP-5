package com.cnfantasia.server.ms.AlterPeriod;

import com.cnfantasia.server.commbusi.alterPeriod.service.LatefeeCalculateService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodCfg.service.IAlterPeriodCfgBaseService;
import com.cnfantasia.server.ms.revenue.task.ISynTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangh on 2016/10/20.
 */
public class AlterPeriodSynTask implements ISynTask {

    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private LatefeeCalculateService latefeeCalculateService;
    @Resource
    private IAlterPeriodCfgBaseService alterPeriodCfgBaseService;

    @Override
    public Integer execTask() {
        logger.debug("AlterPeriodSynTask start:" + DateUtils.getCurrentDate());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("latefeeStatus",1);
        paramMap.put("sys0DelState",0);
        List<AlterPeriodCfg> cfgList = alterPeriodCfgBaseService.getAlterPeriodCfgByCondition(paramMap);
        for (int i = 0; i < cfgList.size(); i++) {
            AlterPeriodCfg alterPeriodCfg = cfgList.get(i);
            latefeeCalculateService.calculateByGroupBuildingByThread(alterPeriodCfg.gettGbId());
        }
        logger.debug("AlterPeriodSynTask end:" + DateUtils.getCurrentDate());
        return 1;
    }
}
