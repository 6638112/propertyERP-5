package com.cnfantasia.server.jobhandler.bankCollection;

import com.cnfantasia.server.ms.revenue.task.BankCollectionTask;
import com.cnfantasia.server.ms.revenue.task.IRevenueTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @Author: wenfq
 * @Date: 2017-09-21 11:24
 */
@JobHander(value = "bankCollectionHandler")
@Component
public class BankCollectionHandler extends IJobHandler {
    @Resource
    BankCollectionTask bankCollectionTask;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        bankCollectionTask.execTask();
        return ReturnT.SUCCESS;
    }
}
