package com.cnfantasia.server.jobhandler.egu;

import com.cnfantasia.server.api.ebuy.task.EguTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: EguExpressHandler.
 * @Date: 2017-09-11 11:18
 * @Auther: kangduo
 * @Description: ()
 */
@JobHander(value="eguExpressHandler")
@Component
public class EguExpressHandler extends IJobHandler {

    @Resource
    private EguTask eguTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        eguTask.eguOrderExpressTraceTask();
        return ReturnT.SUCCESS;
    }
}
