package com.cnfantasia.server.api.access.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.access.entity.CarBill;
import com.cnfantasia.server.api.access.entity.CarDetailEntity;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.entity.CarNumPrefixEntity;
import com.cnfantasia.server.api.access.entity.CarPreferDto;
import com.cnfantasia.server.api.access.entity.CarPreferParam;
import com.cnfantasia.server.api.access.entity.GroupBuildingParkingInfo;
import com.cnfantasia.server.api.access.entity.JfqPlotEntity;
import com.cnfantasia.server.api.access.entity.NotifyCarPaySuccessParam;
import com.cnfantasia.server.api.access.entity.ParkingRecordEntity;
import com.cnfantasia.server.api.access.entity.PlotInfo;
import com.cnfantasia.server.api.access.entity.TmpCarPayDetail;
import com.cnfantasia.server.api.access.entity.UserCarpushMsg;
import com.cnfantasia.server.api.access.entity.UserDoorKeyMsg;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.PayCarFeeLog;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

public interface IAccessDao {
    CarNumList getCardetail(Map<String, Object> paramMap);

    CarGroupBuilding getCarGroupBuilding(String trade_code);

    List<CarDetailEntity> qryCarNumListByRealRoom(BigInteger userId);
    
    CarDetailEntity qryCarInfo(BigInteger userId, String carNum);
    
    /**
     * 根据userId和小区Id查询缴费记录
     * @param userId
     * @param groupBuildingId
     * @return
     */
    List<PayCarFeeLog> qryPaymentCarFeeRecords(BigInteger userId, BigInteger groupBuildingId);

    List<ParkingRecord> qryParkingRecord(BigInteger carId, PageModel pageModel);
    
    List<ParkingRecordEntity> qryParkingRecord2(BigInteger carId, PageModel pageModel);

    void updatecarNumendTime(Map<String, Object> paramMap);

    List<CarNumPayLogDetail> qryPaymentRecord(BigInteger userId, BigInteger carId, PageModel pageModel);

    GroupBuildingParkingInfo qryParkingInfo(BigInteger buildingId);

    boolean qryRoomAndCar(String plateNum);

    boolean qryBinded(BigInteger userId, BigInteger realroomId);

    CarNumPayLog getCarPayLogDetail(BigInteger orderId);
    
    RealRoom getValidateUserId(Map<String, Object> paramMap);
    
    String getGbNameByOrderId(BigInteger orderId);
    
    /**
	 * 根据orderId获取停车缴费支付备注。格式：{物业公司名称}车牌，缴费期间[停车费]
	 * 
	 * @param orderId
	 * @return
	 */
    String getPayNoteWithCarByOrderId(BigInteger orderId);
    
    List<UserDoorKeyMsg> qryUserDoorKeyDetail(Map<String, Object> paramMap);
    
    List<CarNumPayLogDetail> qryjiefangquPayLog(BigInteger gbid);
    
    CarGroupBuilding qryCarGroupBuilding(BigInteger orderId);
    
    List<UserCarpushMsg> qrycarexpirePushMsg();
    
    RoomHasCarNum qryCarBindedUser(Map<String, Object> paramMap);
    
    void updateCarexpireDate(Map<String, Object> paramMap);

    List<ParkCache> getCache(String parkCode, List<BigInteger> remains);
    
    /**
	 * 根据carId查询car信息
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
     * 月卡车缴费推送
     * @param gbId
     * @return
     */
    List<NotifyCarPaySuccessParam> selectPushMonthCarInfo(BigInteger gbId);
    
    /**
     * 临停车缴费推送
     * @param gbId
     * @return
     */
    List<NotifyCarPaySuccessParam> selectPushTempCarInfo(BigInteger gbId);
    
    /**
     * 查询小区对应的车牌前缀
     */
    String selectCarNumPrefixByGbId(BigInteger gbId);
    
    /**
     * 查询所有车牌前缀
     */
    List<CarNumPrefixEntity> selectCarNumPrefixList();

    /**
     * 根据车牌号获取小区ID
     * @param carNum
     * @return
     */
    BigInteger getGbIdByCarNum(String carNum);
    
    /**
     * 查询解放区停车场
     * @param name
     * @return
     */
    List<JfqPlotEntity> selectJFQPlots(String name);
    
    /**
	 * 查询停车场信息（轻应用导航）
	 * @param city
	 * @param plotName
	 * @return
	 */
	public List<PlotInfo> selectPlots(Map<String, Object> paramMap);
}
