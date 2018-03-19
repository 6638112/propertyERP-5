package com.cnfantasia.server.ms.propertyPayConfig.entity;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VerifyMrDataEntity
 * @Date: 2017-10-17 15:04
 * @Auther: yanghua
 * @Description:(抄表校验参数)
 */
public class VerifyMrDataEntity {
    /**导入的excel*/
    private HSSFWorkbook wb;
    /**小区ID*/
    private BigInteger gbId;
    /**房间IDMap:楼栋_单元_房号 : realRoomId*/
    private Map<String, BigInteger> roomInfoMap;
    /**查询房间计算规则使用：key:房间ID_费用项ID_计费表名称,value:t_mr_standard_room表ID*/
    private Map<String, BigInteger> mrStandardRoomInfoMap;
    /**每个房间每个收费项各个表的用量*/
    private Map<BigInteger, Map<BigInteger, Map<String, Double>>> roomMrValues;
    /**excel包含需要导入数据的行数*/
    private int validCounts;
    /**mrFeeItemId key:name value:itemId*/
    private Map<String, BigInteger> mrFeeItemIds;
    /**每一行excel的数据*/
    private List<MrImportDataEntity> mrImportDataEntities;

    public VerifyMrDataEntity() {
        roomInfoMap = new HashMap<String, BigInteger>();
        mrStandardRoomInfoMap = new HashMap<String, BigInteger>();
        roomMrValues = new HashMap<BigInteger, Map<BigInteger, Map<String, Double>>>();
    }

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public Map<String, BigInteger> getMrStandardRoomInfoMap() {
        return mrStandardRoomInfoMap;
    }

    public void setMrStandardRoomInfoMap(Map<String, BigInteger> mrStandardRoomInfoMap) {
        this.mrStandardRoomInfoMap = mrStandardRoomInfoMap;
    }

    public Map<String, BigInteger> getRoomInfoMap() {
        return roomInfoMap;
    }

    public void setRoomInfoMap(Map<String, BigInteger> roomInfoMap) {
        this.roomInfoMap = roomInfoMap;
    }

    public Map<BigInteger, Map<BigInteger, Map<String, Double>>> getRoomMrValues() {
        return roomMrValues;
    }

    public void setRoomMrValues(Map<BigInteger, Map<BigInteger, Map<String, Double>>> roomMrValues) {
        this.roomMrValues = roomMrValues;
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }

    public int getValidCounts() {
        return validCounts;
    }

    public void setValidCounts(int validCounts) {
        this.validCounts = validCounts;
    }

    public Map<String, BigInteger> getMrFeeItemIds() {
        return mrFeeItemIds;
    }

    public void setMrFeeItemIds(Map<String, BigInteger> mrFeeItemIds) {
        this.mrFeeItemIds = mrFeeItemIds;
    }

    public List<MrImportDataEntity> getMrImportDataEntities() {
        return mrImportDataEntities;
    }

    public void setMrImportDataEntities(List<MrImportDataEntity> mrImportDataEntities) {
        this.mrImportDataEntities = mrImportDataEntities;
    }
}
