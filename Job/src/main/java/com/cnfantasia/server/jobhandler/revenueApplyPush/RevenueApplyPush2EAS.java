package com.cnfantasia.server.jobhandler.revenueApplyPush;

import com.cnfantasia.server.ms.revenue.task.RevenueApplyPush2EASTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 提款单推送至EAS
 * @Author: wenfq
 * @Date: 2017-12-21 11:10
 */
@JobHander(value = "revenueApplyPush2EAS")
@Component
public class RevenueApplyPush2EAS extends IJobHandler {
    @Resource
    RevenueApplyPush2EASTask revenueApplyPush2EASTask;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        revenueApplyPush2EASTask.execTask();
        return ReturnT.SUCCESS;
    }
}
