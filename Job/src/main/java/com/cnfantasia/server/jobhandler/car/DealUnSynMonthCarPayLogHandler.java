package com.cnfantasia.server.jobhandler.car;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

@JobHander(value="dealUnSynMonthCarPayLog")
@Component
@Deprecated
public class DealUnSynMonthCarPayLogHandler  extends IJobHandler {

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        XxlJobLogger.log("开始处理未同步的线下缴费数据");
        return ReturnT.SUCCESS;
    }
}
