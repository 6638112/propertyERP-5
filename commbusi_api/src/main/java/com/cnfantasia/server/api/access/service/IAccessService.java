package com.cnfantasia.server.api.access.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.access.entity.CarBill;
import com.cnfantasia.server.api.access.entity.CarDetailEntity;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.entity.CarPreferDto;
import com.cnfantasia.server.api.access.entity.CarPreferParam;
import com.cnfantasia.server.api.access.entity.GroupBuildingParkingInfo;
import com.cnfantasia.server.api.access.entity.JfqPlotEntity;
import com.cnfantasia.server.api.access.entity.ParkingRecordEntity;
import com.cnfantasia.server.api.access.entity.PayCarKeyOrderParam;
import com.cnfantasia.server.api.access.entity.PlotInfo;
import com.cnfantasia.server.api.access.entity.TmpCarPayDetail;
import com.cnfantasia.server.api.access.entity.UserCarpushMsg;
import com.cnfantasia.server.api.access.entity.UserDoorKeyMsg;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.PayCarFeeLog;
import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

public interface IAccessService {
    CarGroupBuilding getCarGroupBuilding(String trade_code);

    CarNumList getCardetail(Map<String, Object> paramMap);

    void updatecarNumendTime(Map<String, Object> paramMap);
    
    /**
     * 根据userId和小区Id查询缴费记录
     * @param userId
     * @param groupBuildingId
     * @return
     */
    List<PayCarFeeLog> qryPaymentCarFeeRecords(BigInteger userId, BigInteger groupBuildingId);
    
    /**
	 * 外来车不允许在APP上缴费
	 * @param amount
	 */
	public void forbiddenOuterCarPay(Object from) ;
	
    List<ParkingRecord> qryParkingRecord(BigInteger carId, PageModel pageModel);
    
    /**
     * 查询停车记录（version>=V325）
     */
    List<ParkingRecordEntity> qryParkingRecord2(final BigInteger carId, final PageModel pageModel);

    GroupBuildingParkingInfo qryParkingInfo(BigInteger buildingId);

    List<CarDetailEntity> getCarInfos(BigInteger userId);
    
    CarDetailEntity qryCarInfo(BigInteger userId, String carNum);

    List<CarNumPayLogDetail> qryPaymentRecord(BigInteger userId, BigInteger carId, PageModel pageModel);

    boolean qryBinded(BigInteger userId, BigInteger realroomId);

    boolean qryRoomAndCar(String plateNum);
    
    PreOrderDto payCarKeyOrder(PayCarKeyOrderParam param);
    public String dealPayExpire(long expire, int payMonth);
    RealRoom getValidateUserId(Map<String, Object> paramMap);
    
    CarNumPayLog getCarPayLogDetail(BigInteger orderId);
    
    String getGbNameByOrderId(BigInteger orderId);
    
    /**
	 * 根据orderId获取停车缴费支付备注。格式：{物业公司名称}车牌，缴费期间[停车费]
	 * 
	 * @param orderId
	 * @return
	 */
    String getPayNoteWithCarByOrderId(BigInteger orderId);
    
    public void updateCarexpireDate(Map<String, Object> paramMap);
    
    BigInteger getRealRoomIdByGbId(BigInteger gbId, String buildingName, String roomNum, String unit_num);
    
    BigInteger payDoorKeyOrder(BigInteger payId, Long payFee, BigInteger userId, Integer subChannelId);
    
    List<UserDoorKeyMsg> qryUserDoorKeyDetail(Map<String, Object> paramMap);
    
    List<CarNumPayLogDetail> qryjiefangquPayLog();
    
    List<CarNumPayLogDetail> updateCarNumPayLog();

    /**
     * @author wangzhe
     * @date 2016年4月1日
     * @description 
     * @param gbid TODO
     *
     * @return
     */
    List<CarNumPayLogDetail> getPendingCarNumPayLog(BigInteger gbid);
    
    void saveOpenDoorLog(List<OpenDoorLog> logList);
    
    List<UserCarpushMsg> qrycarexpirePushMsg();
    
    void pushTouserCarBill(List<UserCarpushMsg> carmsgList);
    
    RoomHasCarNum qryCarBindedUser(Map<String, Object> paramMap);
    
    List<ParkCache> getCache(String parkCode, List<BigInteger> remains);
    
    /**
	 * 根据carId查询car信息(carNum、fee)
	 * @param carId
	 * @return
	 */
    Map<String, Object> queryCarInfoByCardId(BigInteger carId);
	
	/**
	 * 根据carNum查询car信息(carId、carNum、fee)
	 * @param carId
	 * @return
	 */
    Map<String, Object> queryCarInfoByCardNum(String carNum);
	
	/**
	 * 查询临时车费用
	 * 
	 * @param orderId
	 * @return
	 */
    TmpCarPayDetail qryParkingFeeDetail(BigInteger orderId);
	
	/**
     * 临时车查询小区gbId、carId
     * @param paramMap ==>carNum
     * @return
     */
    Map<String, Object> qryParkingInfo(Map<String, Object> paramMap);
    
    /**
     * 查询车禁缴费随机立减优惠金额
     * 
     * @param carPreferParam
     * @return
     */
    CarPreferDto getPayCarPrefer(CarPreferParam carPreferParam);
    
    /**
     * 查询物业缴费处车辆缴费卡
     * 
     *
     * @param gbId
     * @param userId
     * @return
     */
    List<CarBill> queryCarBill(BigInteger gbId, BigInteger userId);
    
    CarNumList queryCarNumListByCondition(Map<String, Object> paramMap);
    
    /**
     * 车辆进场记录
     * @param carNum
     * @param gbId
     * @param inDate
     * @param carType 车辆类型
     */
    boolean vehIn(String carNum, BigInteger gbId, String inDate, Integer carType, String cardsn);
    
    String getHpfCardsn(String carnum);
    
    /**
     * 车辆出场记录
     * 
     * @param carNum
     * @param gbId
     * @param inDate
     * @param outDate
     * @param fee 费用（单位：分）
     * @param carType 车辆类型
     * @return
     */
    boolean vehOut(String carNum, BigInteger gbId, String inDate, String outDate, long fee, Integer carType);
    
    /**
     * 线下缴费同步
     * @param jsonObject
     * @param isMonthCar 是否为月卡缴费
     * @return
     */
    boolean synXmfCarPayLog(JSONObject jsonObject, boolean isMonthCar);
    
    /**
     * 处理车牌前缀
     */
    String getCarNumPrefixByGbId(BigInteger gbId);
    
    /**
     * 刷新车牌前缀缓存
     */
    void refreshCarNumPrefix();
    
    /**
     * 获取除小蜜蜂之外的费用列表
     * @param fee
     * @return
     */
    List<CarFeeType> getCarFeeTypeListNotXMF(BigDecimal fee);

    /**
     * 根据车牌获取小区ID
     * @param carNum
     * @return
     */
    BigInteger getGbIdByCarNum(String carNum);
    
    /**
     * 查询解放区停车场
     * @param name
     * @return
     */
    List<JfqPlotEntity> getJFQPlots(String name);
    
    /**
	 * 查询停车场信息（轻应用导航）
	 * @param city
	 * @param plotName
	 * @return
	 */
	public List<PlotInfo> getPlots(String city, String plotName);
}
