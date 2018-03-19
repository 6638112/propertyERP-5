package com.cnfantasia.server.jobhandler.dredge;

import com.cnfantasia.server.ms.revenue.task.DredgeTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: DredgePushHandler.
 * @Date: 2017-09-08 15:02
 * @Auther: kangduo
 * @Description: (定时对新维修订单对不同等级的师傅推送，2分钟执行一次 30 0/2 * * * ?)
 */
@JobHander(value = "dredgePushHandler")
@Component
public class DredgePushHandler extends IJobHandler {

    @Resource
    private DredgeTask dredgeTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        dredgeTask.execTask();
        return ReturnT.SUCCESS;
    }
}
