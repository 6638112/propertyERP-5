package com.cnfantasia.server.jobhandler.dredge;

import com.cnfantasia.server.ms.revenue.task.DredgeTask;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * @ClassName: DredgeAutoFinishHandler.
 * @Date: 2017-09-08 15:02
 * @Auther: kangduo
 * @Description: (服务前付款订单，物业维修免费订单，在师傅端确认完成后，7天内用户未确认完成，则系统自动把订单变为完成状态 30 0 0/2 * * ?)
 */
@JobHander(value = "dredgeAutoFinishHandler")
@Component
public class DredgeAutoFinishHandler extends IJobHandler {

    @Resource
    private DredgeTask dredgeTask;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        dredgeTask.autoFinishBillTask();
        return ReturnT.SUCCESS;
    }
}
