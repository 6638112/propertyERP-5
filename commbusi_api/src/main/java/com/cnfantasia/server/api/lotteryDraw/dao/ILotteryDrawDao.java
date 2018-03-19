package com.cnfantasia.server.api.lotteryDraw.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawEntity;
import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawProductEntity;

/**
 * @ClassName: ILotteryDrawDao
 * @Date: 2016-11-25 14:48
 * @Auther: yanghua
 * @Description:(幸运抽奖)
 */
public interface ILotteryDrawDao {
    /**
     *当前用户的参与记录了
     * @param userId
     * @return
     */
    LotteryDrawEntity getDrawRecordByUser(BigInteger userId);

    /**
     * 其他用户的中奖信息
     * @param userId
     * @return
     */
    List<LotteryDrawEntity> getDrawRecordList(BigInteger userId);
    
    /**
     * 抽奖处理
     * 
     * @param paramMap
     * @return
     */
    BigInteger drawLottery(Map<String, Object> paramMap);

    /**
     * 查询抽奖奖品
     * @return
     */
    List<LotteryDrawProductEntity> getLotteryDrawProduct();
}
