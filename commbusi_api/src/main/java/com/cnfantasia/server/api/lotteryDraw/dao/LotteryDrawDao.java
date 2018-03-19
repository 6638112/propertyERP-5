package com.cnfantasia.server.api.lotteryDraw.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawEntity;
import com.cnfantasia.server.api.lotteryDraw.entity.LotteryDrawProductEntity;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;

/**
 * @ClassName: LotteryDrawDao
 * @Date: 2016-11-25 14:48
 * @Auther: yanghua
 * @Description:(幸运抽奖)
 */
public class LotteryDrawDao extends AbstractBaseDao implements ILotteryDrawDao {
    @Override
    public LotteryDrawEntity getDrawRecordByUser(BigInteger userId) {
        return sqlSession.selectOne("lotteryDraw.getDrawRecordByUser", userId);
    }

    @Override
    public List<LotteryDrawEntity> getDrawRecordList(BigInteger userId) {
        return sqlSession.selectList("lotteryDraw.getDrawRecordList", userId);
    }
    
    /**
     * 抽奖处理
     * 
     * @param paramMap
     * @return
     */
    @Override
    public BigInteger drawLottery(Map<String, Object> paramMap){
    	return sqlSession.selectOne("lotteryDraw.draw_lottery", paramMap);
    }

    @Override
    public List<LotteryDrawProductEntity> getLotteryDrawProduct() {
        return sqlSession.selectList("lotteryDraw.getLotteryDrawProduct");
    }

	public int selectMicroblogContentCountByUserId(Map<String, Object> paramMap) {
		return sqlSession.selectOne("lotteryDraw.selectMicroblogContentCountByUserId", paramMap);
	}
}
