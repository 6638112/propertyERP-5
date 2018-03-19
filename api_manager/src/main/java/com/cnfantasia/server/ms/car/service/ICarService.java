package com.cnfantasia.server.ms.car.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.ms.car.entity.AutoCompleteEntity;
import com.cnfantasia.server.ms.car.entity.CarEntity;

/**
 * 车辆管理
 * 
 * @author liyulin
 * @version 1.0 2016年11月23日 下午5:13:08
 */
public interface ICarService {

	/**
	 * 车辆管理首页数据查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CarEntity> queryCarListForPage(Map<String, Object> paramMap);
	
	/**
	 * 车辆管理首页数据数量
	 * 
	 * @param paramMap
	 * @return
	 */
	public int queryCarListForCount(Map<String, Object> paramMap);

	/**
	 * 根据小区名称查询小区信息
	 * 
	 * @param gbName
	 * @return
	 */
	public List<AutoCompleteEntity> qryGbByName(String gbName);

	/**
	 * 根据楼栋名称、gbId查询楼栋信息
	 * 
	 * @param buildingName
	 * @param gbId
	 * @return
	 */
	public List<AutoCompleteEntity> qryBuildingByName(String buildingName, String gbId);

	/**
	 * 根据buildingId查询房间信息
	 * 
	 * @param buildingId
	 * @param roomName
	 * @return
	 */
	public List<AutoCompleteEntity> qryRealRoomByName(String buildingId, String roomName);

	/**
	 * 插入车牌
	 * 
	 * @param carNumList
	 * @return
	 */
	public JsonResponse addCar(CarNumList carNumList);

	/**
	 * 删除车牌
	 * 
	 * @param carId
	 * @param userId
	 * @return
	 */
	public boolean delCar(BigInteger carId, BigInteger userId);
}
