package com.cnfantasia.server.jobhandler.livingPay;

import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @Author: wenfq
 * @Date: 2017-11-16 15:54
 */
@JobHander(value = "livingPayRevenueHandler")
@Component
public class LivingPayRevenueHandler extends IJobHandler {
    @Resource
    LivingPayService livingPayService;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        livingPayService.generateRevenueSignalAmount();
        return ReturnT.SUCCESS;
    }
}
