package com.cnfantasia.server.jobhandler.revenueSubsidy;

import com.cnfantasia.server.ms.revenue.task.PropertySubsidyAmoutAutoApplyCompany;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * 物业补贴收益生成Handler
 * @Author: wenfq
 * @Date: 2017-12-21 9:56
 */
@JobHander(value = "propertySubsidyHandler")
@Component
public class PropertySubsidyHandler extends IJobHandler {
    @Resource
    PropertySubsidyAmoutAutoApplyCompany propertySubsidyAmoutAutoApplyCompany;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        propertySubsidyAmoutAutoApplyCompany.execTask();
        return ReturnT.SUCCESS;
    }
}
