package com.cnfantasia.server.jobhandler.microblog;

import com.cnfantasia.server.commbusi.microblogQueue.task.MicroblogQueueTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: MicroblogHandler.
 * @Date: 2017-09-08 18:03
 * @Auther: kangduo
 * @Description: (街坊消息定时推送 52 0/5 * * * ?)
 */
@JobHander(value="microblogPushHandler")
@Component
public class MicroblogPushHandler extends IJobHandler {

    @Resource
    private MicroblogQueueTask microblogQueueTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        microblogQueueTask.excutePushTask();
        return ReturnT.SUCCESS;
    }
}
