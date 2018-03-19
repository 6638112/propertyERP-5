package com.cnfantasia.server.ms.fixedFeeCfg.dao;

import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillInitEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.RoomFixedFeeItemDetail;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FixedFeeCfgDao
 * @Date: 2017-01-18 18:02
 * @Auther: yanghua
 * @Description:(固定收费项配置)
 */
public class FixedFeeCfgDao extends AbstractBaseDao {
    public int getGroupBuildingListCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("fixedFeeCfg.getGroupBuildingListCount",paramMap);
    }

    public List<HashMap<String, Object>> getGroupBuildingList(Map<String, Object> paramMap, PageModel pageModel) {
        paramMap.put("_begin", pageModel.getBegin());
        paramMap.put("_length", pageModel.getLength());
        return sqlSession.selectList("fixedFeeCfg.getGroupBuildingList", paramMap);
    }

    public int delFeeItem(BigInteger id, BigInteger gbId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("id", id);
        return sqlSession.update("fixedFeeCfg.deleteFixedFeeItemById",param);
    }

    public List<RoomFixedFeeItemDetail> getRoomFixedFeeItemDetailList(BigInteger gbId, String bName, String unitName, String roomNum, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("bName", bName);
        param.put("unitName", unitName);
        param.put("roomNum", roomNum);
        param.put("_begin", pageModel.getBegin());
        param.put("_length", pageModel.getLength());
        return sqlSession.selectList("fixedFeeCfg.getRoomFixedFeeItemDetailList", param);
    }

    public int getRoomFixedFeeItemDetailCount(BigInteger gbId, String bName, String unitName, String roomNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("bName", bName);
        param.put("unitName", unitName);
        param.put("roomNum", roomNum);
        return sqlSession.selectOne("fixedFeeCfg.getRoomFixedFeeItemDetailCount", param);
    }

    public RoomFixedFeeItemDetail getRoomFixedFeeItemDetailByDataId(BigInteger dataId) {
        return sqlSession.selectOne("fixedFeeCfg.getRoomFixedFeeItemDetailByDataId", dataId);
    }

    public int delFixedFeeDetail(BigInteger id) {
        return sqlSession.update("fixedFeeCfg.delFixedFeeDetailById",id);
    }

    public int delFixedFeeDetailAll(BigInteger gbId) {
        return sqlSession.update("fixedFeeCfg.delFixedFeeDetailAll", gbId);
    }

    public List<RoomFixedFeeItemDetail> getRoomFixedFeeItemDetailListForImportCheck(BigInteger gbId) {
        return sqlSession.selectList("fixedFeeCfg.getRoomFixedFeeItemDetailListForImportCheck", gbId);
    }

    public List<Map<String, Object>> getRoomStrByGbId(BigInteger gbId) {
        return sqlSession.selectList("fixedFeeCfg.getRoomStrByGbId", gbId);
    }

    public int delFeeItemHasRoom(BigInteger dataId) {
        return sqlSession.delete("fixedFeeCfg.delFeeItemHasRoom", dataId);
    }

    /**
     * 查询 需要同步的数据
     *
     * @param cfgId
     * @param gbId
     * @return
     */
    public List<PropertyFeeDetailTemp> getNeedSynchroData(BigInteger gbId, BigInteger cfgId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("cfgId", cfgId);
        return sqlSession.selectList("fixedFeeCfg.getNeedSynchroData01", param);
    }

    /**
     * 根据itemId查询出房间对的费用明细信息
     * @param id
     * @return
     */
    public List<UpdatePayBillInitEntity> itemHasRoomByItemId(BigInteger id) {
        return sqlSession.selectList("fixedFeeCfg.itemHasRoomByItemId", id);
    }

    /**
     * 根据小区ID查询对应账期配置的费用项
     * @param gbId
     * @return
     */
    public List<BigInteger> getCycleCfgFeeItemsByGbId(BigInteger gbId) {
        return sqlSession.selectList("fixedFeeCfg.getCycleCfgFeeItemsByGbId", gbId);
    }
}
