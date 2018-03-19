package com.cnfantasia.server.commbusi.alterPeriod.dao;

import com.cnfantasia.server.api.plotproperty.entity.PropertyAlterBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.alterPeriod.entity.AlterPeroidDetailWithItem;
import com.cnfantasia.server.commbusi.alterPeriod.entity.RoomAlterPeroidDetail;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by yangh on 2016/10/20.
 */
public interface IAlterPeriodDao {
    /**
     * 查询门牌对应的需要进行计算滞纳金的费用总和
     * @param realRoomId
     * @return
     */
    Long getNeedCalculateLatefeeSum(BigInteger realRoomId);

    /**
     * 查询门牌对应的需要缴费费的基数总和
     * @param realRoomId
     * @return
     */
    Long getBasicSumAmt(BigInteger realRoomId);

    /**
     * 选择周期缴费支付详情
     * @param realRoomId
     * @return
     */
    PropertyAlterBillInfo getAlterPeriodDetail(BigInteger realRoomId);

    /**
     * 常规收费项列表
     * @param gbName 小区名
     * @return
     */
    List<AlterPeriodCfg> getAlterPeriodCfgList(Map<String, Object> param, PageModel pageModel);

    /**
     * 常规收费项总数
     * @param gbName 小区名
     * @return
     */
    Integer getAlterPeriodCfgCount(Map<String, Object> param);

    /**
     * 获取 选择周期随机立减
     * @param userId
     * @param realRoomId
     * @param amt
     * @param month
     * @return
     */
    Long getPreferentialRandom(BigInteger userId, BigInteger realRoomId, Long amt, Integer month);

    List<RoomAlterPeroidDetail> getRoomAlterPeroidList(BigInteger gbId, String bName, String unitName, String roomNum, PageModel pageModel);

    Integer getRoomAlterPeroidCount(BigInteger gbId, String bName, String unitName, String roomNum);
    
    List<AlterPeroidDetailWithItem> getAlterPeroidDetailWithItemList(List<AlterPeroidDetailWithItem> alterPeroidDetailWithItemList);

    List<Map<String,Object>> getAlterPeriodItemsFee(BigInteger bigInteger);

    /**
     * 根据真实房间id获取对应的滞纳金
     * @param realRoomId
     * @return
     */
    Long getLateFeeByRealRoomId(BigInteger realRoomId);

    /**
     * 导入校验门牌id是否存在
     * @param gbId
     * @return
     */
    List<Map<String,Object>> getRoomStrByGbId(BigInteger gbId);
}
