package com.cnfantasia.server.ms.car.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carNumList.dao.ICarNumListBaseDao;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.ms.car.dao.ICarDao;
import com.cnfantasia.server.ms.car.entity.AutoCompleteEntity;
import com.cnfantasia.server.ms.car.entity.CarEntity;

/**
 * 车辆管理
 * 
 * @author liyulin
 * @version 1.0 2016年11月23日 下午5:13:29
 */
public class CarService implements ICarService {
	private ICarDao carDao;
	private ICarNumListBaseDao carNumListBaseDao;
	private IUuidManager uuidManager;
	
	public void setCarDao(ICarDao carDao) {
		this.carDao = carDao;
	}

	public void setCarNumListBaseDao(ICarNumListBaseDao carNumListBaseDao) {
		this.carNumListBaseDao = carNumListBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	/**
	 * 车辆管理首页数据查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<CarEntity> queryCarListForPage(Map<String, Object> paramMap) {
		return carDao.queryCarListForPage(paramMap);
	}

	/**
	 * 车辆管理首页数据数量
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public int queryCarListForCount(Map<String, Object> paramMap) {
		return carDao.queryCarListForCount(paramMap);
	}
	
	/**
	 * 根据小区名称查询小区信息
	 * 
	 * @param gbName
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryGbByName(String gbName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", gbName);
		return carDao.qryGbByName(paramMap);
	}

	/**
	 * 根据楼栋名称、gbId查询楼栋信息
	 * 
	 * @param buildingName
	 * @param gbId
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryBuildingByName(String buildingName, String gbId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("buildingName", buildingName);
		paramMap.put("gbId", gbId);
		return carDao.qryBuildingByName(paramMap);
	}

	/**
	 * 根据buildingId查询房间信息
	 * 
	 * @param buildingId
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryRealRoomByName(String buildingId, String roomName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("buildingId", buildingId);
		paramMap.put("roomName", roomName);
		return carDao.qryRealRoomByName(paramMap);
	}

	/**
	 * 插入车牌
	 * 
	 * @param carNumList
	 * @return
	 */
	@Override
	public JsonResponse addCar(CarNumList carNumList) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1、查询车牌是否存在
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNum", carNumList.gettCarNum());
		paramMap.put("sys0DelState", 0);
		if(carNumListBaseDao.selectCarNumListCount(paramMap, false)>0){// 车牌已存在
			jsonResponse.setMessage("该车牌已存在，不能重复添加！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		} else {
			paramMap.put("sys0DelState", 1);
			List<CarNumList> carNumLists = carNumListBaseDao.selectCarNumListByCondition(paramMap, false);
			if(carNumLists!=null && carNumLists.size()>0){// 之前已删除过
				CarNumList car = carNumLists.get(0);
				car.setSys0DelState(0);
				car.setSys0UpdUser(carNumList.getSys0AddUser());
				car.setSys0UpdTime(carNumList.getSys0AddTime());
				car.setSys0AddTime(null);
				if(carNumListBaseDao.updateCarNumList(car)>0){
					jsonResponse.setMessage("该车牌已存于系统中，状态已从禁用改为启用！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
				} else {
					jsonResponse.setMessage("操作失败！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				}
			} else {
				carNumList.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
				if(carNumListBaseDao.insertCarNumList(carNumList)>0){
					jsonResponse.setMessage("操作成功！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
				} else {
					jsonResponse.setMessage("操作失败！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
				}
			}
		}
		return jsonResponse;
	}

	/**
	 * 删除车牌
	 * 
	 * @param carId
	 * @param userId
	 * @return
	 */
	@Override
	public boolean delCar(BigInteger carId, BigInteger userId) {
		CarNumList car = carNumListBaseDao.selectCarNumListBySeqId(carId);
		car.setSys0DelState(1);
		car.setSys0UpdUser(userId);
		car.setSys0UpdTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		
		return carNumListBaseDao.updateCarNumList(car)>0;
	}

}
