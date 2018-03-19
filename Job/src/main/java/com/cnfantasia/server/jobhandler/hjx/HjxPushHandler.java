package com.cnfantasia.server.jobhandler.hjx;

import com.cnfantasia.server.api.ebuyorder.serviceUn.EbuyOrderPusherTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: HjxPushHandler.
 * @Date: 2017-09-11 11:12
 * @Auther: kangduo
 * @Description: ()
 */
@JobHander(value="hjxPushHandler")
@Component
public class HjxPushHandler extends IJobHandler {

    @Resource
    private EbuyOrderPusherTask ebuyOrderPusherTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        ebuyOrderPusherTask.pushOrder4HJXSplMerchat();
        return ReturnT.SUCCESS;
    }
}
