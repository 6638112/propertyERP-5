package com.cnfantasia.server.jobhandler.lateFee;

import com.cnfantasia.server.api.lateFee.task.CalculateLateFeeTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @className: CalculateLateFeeHandler
 * @date: 2017-12-04 15:32
 * @author: yanghua
 * @description:(计算滞纳金 频率：0 0 2 * * ?)
 */
@JobHander(value="calculateLateFeeHandler")
@Component
public class CalculateLateFeeHandler extends IJobHandler {

    @Resource
    private CalculateLateFeeTask calculateLateFeeTask;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        calculateLateFeeTask.execTask();
        return ReturnT.SUCCESS;
    }
}
