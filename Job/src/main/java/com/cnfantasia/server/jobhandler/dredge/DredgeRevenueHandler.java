package com.cnfantasia.server.jobhandler.dredge;

import com.cnfantasia.server.ms.revenue.task.IRevenueTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 上门服务收益产生
 * @Author: wenfq
 * @Date: 2017-09-14 14:52
 */
@JobHander(value = "dredgeRevenueHandler")
@Component
public class DredgeRevenueHandler extends IJobHandler {

    @Resource
    IRevenueTask revenueTask;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        revenueTask.synDredgePayAmoutForMaster();
        return ReturnT.SUCCESS;
    }
}
