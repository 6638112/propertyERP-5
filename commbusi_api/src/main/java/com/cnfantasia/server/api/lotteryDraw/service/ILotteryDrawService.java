package com.cnfantasia.server.api.lotteryDraw.service;

import java.math.BigInteger;

import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawListEntity;
import com.cnfantasia.server.common.json.JsonResponse;

/**
 * @ClassName: ILotteryDrawService
 * @Date: 2016-11-25 14:49
 * @Auther: yanghua
 * @Description:(幸运抽奖)
 */
public interface ILotteryDrawService {

    /**
     * 获取幸运抽奖页面数据
     * @param userId
     * @return
     */
    LotteryDrawListEntity getLotteryDraw(BigInteger userId);
    
    /**
     * 抽奖
     * 
     * @param userId
     * @return
     */
    public JsonResponse drawLottery(BigInteger userId);
}
