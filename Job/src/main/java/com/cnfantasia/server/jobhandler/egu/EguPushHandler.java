package com.cnfantasia.server.jobhandler.egu;

import com.cnfantasia.server.api.ebuy.task.EguTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: EguPushHandler.
 * @Date: 2017-09-11 11:17
 * @Auther: kangduo
 * @Description: ()
 */
@JobHander(value="eguPushHandler")
@Component
public class EguPushHandler extends IJobHandler {

    @Resource
    private EguTask eguTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        eguTask.eguOrderPush();
        return ReturnT.SUCCESS;
    }
}
