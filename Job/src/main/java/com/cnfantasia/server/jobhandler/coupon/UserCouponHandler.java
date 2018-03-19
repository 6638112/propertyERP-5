package com.cnfantasia.server.jobhandler.coupon;

import com.cnfantasia.server.ms.revenue.task.UserCouponTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: UserCouponHandler.
 * @Date: 2017-09-08 14:25
 * @Auther: kangduo
 * @Description: (用户所持消费券过期处理 1 2 0 * * ?)
 */
@JobHander(value="userCouponHandler")
@Component
public class UserCouponHandler extends IJobHandler {

    @Resource
    private UserCouponTask userCouponTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        XxlJobLogger.log("开始将用户的过期券置为失效状态");
        userCouponTask.execTask();
        return ReturnT.SUCCESS;
    }
}
