package com.cnfantasia.server.jobhandler.coupon;

import com.cnfantasia.server.ms.revenue.task.CouponTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: CouponHandler.
 * @Date: 2017-09-08 14:24
 * @Auther: kangduo
 * @Description: (消费券过期处理 6 2 0 * * ?)
 */
@JobHander(value="couponHandler")
@Component
public class CouponHandler extends IJobHandler {

    @Resource
    private CouponTask couponTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        XxlJobLogger.log("开始将已过期的消费券关闭");
        couponTask.execTask();
        return ReturnT.SUCCESS;
    }
}
