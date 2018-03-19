package com.cnfantasia.server.jobhandler.car;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName: dealUnSynParkingRecord.
 * @Date: 2017-09-20 14:24
 * @Auther: liyl
 * @Description: (小蜜蜂车禁月卡缴费记录每30分钟同步1次 00 45 14 20 09 ? 2017)
 */
@JobHander(value="dealUnSynParkingRecord")
@Component
@Deprecated
public class DealUnSynParkingRecordHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        XxlJobLogger.log("开始处理未同步的停车记录数据");
        return ReturnT.SUCCESS;
    }
}
