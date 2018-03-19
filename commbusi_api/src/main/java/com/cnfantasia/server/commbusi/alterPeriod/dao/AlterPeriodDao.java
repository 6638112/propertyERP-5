package com.cnfantasia.server.commbusi.alterPeriod.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.plotproperty.entity.PropertyAlterBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.alterPeriod.entity.AlterPeroidDetailWithItem;
import com.cnfantasia.server.commbusi.alterPeriod.entity.RoomAlterPeroidDetail;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;

/**
 * Created by yangh on 2016/10/20.
 */
public class AlterPeriodDao extends AbstractBaseDao implements IAlterPeriodDao {
    @Override
    public Long getNeedCalculateLatefeeSum(BigInteger realRoomId) {
        return sqlSession.selectOne("alterPeriod.getNeedCalculateLatefeeSum", realRoomId);
    }

    @Override
    public PropertyAlterBillInfo getAlterPeriodDetail(BigInteger realRoomId) {
        return sqlSession.selectOne("alterPeriod.getAlterPeriodDetail", realRoomId);
    }

    @Override
    public List<AlterPeriodCfg> getAlterPeriodCfgList(Map<String, Object> param, PageModel pageModel) {
        param.put("_begin", pageModel.getBegin());
        param.put("_length", pageModel.getLength());
        return sqlSession.selectList("alterPeriod.alterPeriodCfgList", param);
    }

    @Override
    public Integer getAlterPeriodCfgCount(Map<String, Object> param) {
        return sqlSession.selectOne("alterPeriod.alterPeriodCfgCount", param);
    }

    @Override
    public List<RoomAlterPeroidDetail> getRoomAlterPeroidList(BigInteger gbId, String bName, String unitName, String roomNum, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("bName", bName);
        param.put("unitName", unitName);
        param.put("roomNum", roomNum);
        param.put("_begin", pageModel.getBegin());
        param.put("_length", pageModel.getLength());
        return sqlSession.selectList("alterPeriod.getRoomAlterPeroidDetail", param);
    }

    @Override
    public Integer getRoomAlterPeroidCount(BigInteger gbId, String bName, String unitName, String roomNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("bName", bName);
        param.put("unitName", unitName);
        param.put("roomNum", roomNum);
        return sqlSession.selectOne("alterPeriod.getRoomAlterPeroidCount", param);
    }

    @Override
    public Long getPreferentialRandom(BigInteger userId, BigInteger realRoomId, Long amt, Integer month) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("realRoomId", realRoomId);
        parMap.put("userId", userId);
        parMap.put("amt", amt);
        parMap.put("month", month);

        Long preAmt  = sqlSession.selectOne("alterPeriod.select_preferential_random", parMap);
        if(preAmt == null) {
            preAmt = 0l;
        }

        return preAmt;
    }
    
    public List<AlterPeroidDetailWithItem> getAlterPeroidDetailWithItemList(List<AlterPeroidDetailWithItem> alterPeroidDetailWithItemList) {
    	if(alterPeroidDetailWithItemList == null || alterPeroidDetailWithItemList.size() == 0) {
    		return new ArrayList<AlterPeroidDetailWithItem>();
    	}
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("alterItemList", alterPeroidDetailWithItemList);
    	paramMap.put("gbId", alterPeroidDetailWithItemList.get(0).getGbId());
    	return sqlSession.selectList("alterPeriod.getAlterPeroidDetailWithItemList", paramMap);
    }

    @Override
    public List<Map<String, Object>> getAlterPeriodItemsFee(BigInteger realRoomId) {
        return sqlSession.selectList("alterPeriod.getAlterPeriodItemsFee", realRoomId);
    }

    @Override
    public Long getLateFeeByRealRoomId(BigInteger realRoomId) {
        return sqlSession.selectOne("alterPeriod.getLateFeeByRealRoomId", realRoomId);
    }

    @Override
    public List<Map<String, Object>> getRoomStrByGbId(BigInteger gbId) {
        return sqlSession.selectList("alterPeriod.getRoomStrByGbId", gbId);
    }

    @Override
    public Long getBasicSumAmt(BigInteger realRoomId) {
        return sqlSession.selectOne("alterPeriod.getBasicSumAmt", realRoomId);
    }
}
