package com.cnfantasia.server.jobhandler.microblog;

import com.cnfantasia.server.commbusi.microblogQueue.task.MicroblogQueueTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: MicroblogPropertyRemindHandler.
 * @Date: 2017-09-08 18:07
 * @Auther: kangduo
 * @Description: (每天凌晨1点执行缴费体系消息 23 00 01 * * ?)
 */
@JobHander(value="microblogPropertyRemindHandler")
@Component
public class MicroblogPropertyRemindHandler extends IJobHandler {

    @Resource
    private MicroblogQueueTask microblogQueueTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        microblogQueueTask.executePropertyPayRemindPush();
        return ReturnT.SUCCESS;
    }
}
