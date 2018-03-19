package com.cnfantasia.server.api.access.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

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
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.PayCarFeeLog;
import com.cnfantasia.server.domainbase.parkCache.entity.ParkCache;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

public class AccessDao extends AbstractBaseDao implements IAccessDao {

    @Override
    public void setSqlSession(final SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public CarGroupBuilding getCarGroupBuilding(final String trade_code) {
        return sqlSession.selectOne("access.getCarGroupBuilding", trade_code);
    }

    @Override
    public CarNumList getCardetail(Map<String, Object> paramMap) {
        return sqlSession.selectOne("access.get_car_num_id", paramMap);
    }

    /**
     * 查询停车记录（老版本）
     */
    @Override
    public void updatecarNumendTime(final Map<String, Object> paramMap) {
        sqlSession.update("access.updatecarpaylog", paramMap);
    }

    @Override
    public List<ParkingRecord> qryParkingRecord(final BigInteger carId, final PageModel pageModel) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("carid", carId);
 //       tmpMap.put("gbId", gbId);
        final String pageSqlKey = "access.select_Parking_Record_Page";
        final String countSqlKey = "access.select_Parking_Record_Count";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }
    
    /**
     * 查询停车记录（version>=V325）
     */
    @Override
    public List<ParkingRecordEntity> qryParkingRecord2(final BigInteger carId, final PageModel pageModel) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("carid", carId);
        final String pageSqlKey = "access.select_Parking_Record_Page2";
        final String countSqlKey = "access.select_Parking_Record_Count2";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }

    @Override
    public List<CarDetailEntity> qryCarNumListByRealRoom(final BigInteger userId) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("t_user_id", userId);
        return sqlSession.selectList("access.select_carNumList_by_realroomid", paramMap);
    }
    
    @Override
    public CarDetailEntity qryCarInfo(final BigInteger userId, final String carNum) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("carNum", carNum);
        return sqlSession.selectOne("access.select_car_info", paramMap);
    }

    @Override
    public List<CarNumPayLogDetail> qryPaymentRecord(BigInteger userId, final BigInteger carId, final PageModel pageModel) {
        final Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("userId", userId);
        tmpMap.put("carid", carId);
        final String pageSqlKey = "access.select_Payment_Record_Page";
        final String countSqlKey = "access.select_Payment_Record_Count";
        return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
    }
    
    /**
     * 根据userId和小区Id查询缴费记录
     * @param userId
     * @param groupBuildingId
     * @return
     */
    @Override
    public List<PayCarFeeLog> qryPaymentCarFeeRecords(BigInteger userId, BigInteger groupBuildingId){
    	final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("groupBuildingId", groupBuildingId);
        return sqlSession.selectList("access.select_Payment_CarNo_Records", paramMap);
    }

    @Override
    public GroupBuildingParkingInfo qryParkingInfo(final BigInteger buildingId) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("buidingId", buildingId);
        return sqlSession.selectOne("access.select_Building_Parking_Info", paramMap);
    }

    @Override
    public boolean qryRoomAndCar(final String plateNum) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("realroomId", realroomId);
        paramMap.put("plateNum", plateNum);
        final Integer count = sqlSession.selectOne("access.select_RealroomId_PlateNum_Count", paramMap);
        return null != count && 0 < count;
    }

    @Override
    public boolean qryBinded(final BigInteger userId, final BigInteger realroomId) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("realroomId", realroomId);
        final Integer count = sqlSession.selectOne("access.select_RealroomId_UserId_Count", paramMap);
        return null != count && 0 < count;
    }
    
	@Override
	public CarNumPayLog getCarPayLogDetail(BigInteger orderId) {
		return sqlSession.selectOne("access.select_Car_Pay_record_detail", orderId);
	}

	@Override
	public RealRoom getValidateUserId(Map<String, Object> paramMap) {
		return sqlSession.selectOne("access.select_userId_by_gbId_roomId", paramMap);
	}

	@Override
	public String getGbNameByOrderId(BigInteger orderId) {
		return sqlSession.selectOne("access.select_gbName_by_orderId", orderId);
	}

	/**
	 * 根据orderId获取停车缴费支付备注。格式：{物业公司名称}车牌，缴费期间[停车费]
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public String getPayNoteWithCarByOrderId(BigInteger orderId){
		return sqlSession.selectOne("access.select_payNote_withCar_by_orderId", orderId);
	}
	
	@Override
	public List<UserDoorKeyMsg> qryUserDoorKeyDetail(Map<String, Object> paramMap) {
		return sqlSession.selectList("access.select_user_door_key_msg", paramMap);
	}

	@Override
	public List<CarNumPayLogDetail> qryjiefangquPayLog(BigInteger gbid) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gbid", gbid);
		return sqlSession.selectList("access.select_jiefangqu_pay_log",paramMap);
	}

	@Override
	public CarGroupBuilding qryCarGroupBuilding(BigInteger orderId) {
		return sqlSession.selectOne("access.select_gbName_by_orderId_carBmsg", orderId);
	}

	@Override
	public List<UserCarpushMsg> qrycarexpirePushMsg() {
		return sqlSession.selectList("access.select_car_expire_push_msg");
	}

	@Override
	public RoomHasCarNum qryCarBindedUser(Map<String, Object> paramMap) {
		return sqlSession.selectOne("access.select_carbinded_info", paramMap);
	}

	@Override
	public void updateCarexpireDate(Map<String, Object> paramMap) {
		sqlSession.update("access.update_carpay_expire_date", paramMap);
	}
	
	/**
	 * 根据carId查询car信息
	 * @param carId
	 * @return
	 */
	@Override
	public Map<String, Object> queryCarInfoByCardId(BigInteger carId){
		return sqlSession.selectOne("access.select_car_info_by_carid", carId);
	}
	
	/**
	 * 根据carNum查询car信息(carId、carNum、fee)
	 * @param carId
	 * @return
	 */
	public Map<String, Object> queryCarInfoByCardNum(String carNum){
		return sqlSession.selectOne("access.select_car_info_by_carNum", carNum);
	}

    @Override
    public List<ParkCache> getCache(String parkCode, List<BigInteger> remains) {
        final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parkCode", parkCode);
        paramMap.put("remains", remains);
        return sqlSession.selectList("access.getCache", paramMap);
    }
    
    /**
	 * 查询临时车费用
	 * @param orderId
	 * @return
	 */
    @Override
	public TmpCarPayDetail qryParkingFeeDetail(BigInteger orderId){
		final Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId);
        
    	return sqlSession.selectOne("access.select_parking_fee", paramMap); 
	}
    
    /**
     * 临时车查询小区gbId、carId
     * @param paramMap ==>carNum
     * @return
     */
    @Override
    public  Map<String, Object> qryParkingInfo(Map<String, Object> paramMap){
    	return sqlSession.selectOne("access.select_gbId_and_carId_for_fee", paramMap);
    }
    
    /**
     * 查询车禁缴费随机立减优惠金额
     * 
     * @param carPreferParam
     * @return
     */
    @Override
    public CarPreferDto getPayCarPrefer(CarPreferParam carPreferParam){
    	CarPreferDto carPreferDto = sqlSession.selectOne("access.getPayCarPrefer", MapConverter.toMap(carPreferParam));
    	if(carPreferDto==null){
    		carPreferDto = new CarPreferDto();
    		carPreferDto.setAmount(BigInteger.ZERO);
    	}
    	return carPreferDto;
    }

    /**
     * 查询物业缴费处车辆缴费卡
     * 
     *
     * @param gbId
     * @param userId
     * @return
     */
    @Override
    public List<CarBill> queryCarBill(BigInteger gbId, BigInteger userId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gbId", gbId);
        paramMap.put("userId", userId);
    	return sqlSession.selectList("access.select_car_bill", paramMap);
    }
    
    @Override
    public CarNumList queryCarNumListByCondition(Map<String, Object> paramMap){
    	return sqlSession.selectOne("access.queryCarNumListByCondition", paramMap);
    }
    
    /**
     * 月卡车缴费推送
     * @param gbId
     * @return
     */
    @Override
    public List<NotifyCarPaySuccessParam> selectPushMonthCarInfo(BigInteger gbId){
    	return sqlSession.selectList("access.select_push_monthCar_info", gbId);
    }
    
    /**
     * 临停车缴费推送
     * @param gbId
     * @return
     */
    @Override
    public List<NotifyCarPaySuccessParam> selectPushTempCarInfo(BigInteger gbId){
    	return sqlSession.selectList("access.select_push_tempCar_info", gbId);
    }
    
    /**
     * 查询小区对应的车牌前缀
     */
    @Override
    public String selectCarNumPrefixByGbId(BigInteger gbId){
    	return sqlSession.selectOne("access.selectCarNumPrefixByGbId", gbId);
    }
    
    /**
     * 查询所有车牌前缀
     */
    @Override
    public List<CarNumPrefixEntity> selectCarNumPrefixList(){
    	return sqlSession.selectList("access.selectCarNumPrefixList");
    }

    @Override
    public BigInteger getGbIdByCarNum(String carNum) {
        return sqlSession.selectOne("access.getGbIdByCarNum", carNum);
    }
    
    /**
     * 查询解放区停车场
     * @param name
     * @return
     */
	@Override
    public List<JfqPlotEntity> selectJFQPlots(String name){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		
		return sqlSession.selectList("access.selectJFQPlots", paramMap);
    }
	
    /**
	 * 查询停车场信息（轻应用导航）
	 * @param city
	 * @param plotName
	 * @return
	 */
	public List<PlotInfo> selectPlots(Map<String, Object> paramMap){
		return sqlSession.selectList("access.selectPlots", paramMap);
	}
}
