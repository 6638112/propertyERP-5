package com.cnfantasia.server.jobhandler.revenueApplyPush;

import com.cnfantasia.server.ms.revenue.task.RevenueApplyPush2EASTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 提款单合并
 * @Author: wenfq
 * @Date: 2017-12-21 11:07
 */
@JobHander(value = "revenueApplyPushMerge")
@Component
public class RevenueApplyPushMerge extends IJobHandler {
    @Resource
    RevenueApplyPush2EASTask revenueApplyPush2EASTask;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        revenueApplyPush2EASTask.mergeTask();
        return ReturnT.SUCCESS;
    }
}
