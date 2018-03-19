package com.cnfantasia.server.domainbase.carKeyRoomTemp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carKeyRoomTemp.entity.CarKeyRoomTemp;

/**
 * 描述:(门牌车牌信息临时表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarKeyRoomTempBaseService {
	
	/**
	 * 根据条件查询(门牌车牌信息临时表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarKeyRoomTemp> getCarKeyRoomTempByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(门牌车牌信息临时表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarKeyRoomTemp> getCarKeyRoomTempByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(门牌车牌信息临时表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarKeyRoomTemp> getCarKeyRoomTempByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(门牌车牌信息临时表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarKeyRoomTemp> getCarKeyRoomTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(门牌车牌信息临时表)信息
	 * @param id
	 * @return
	 */
	public CarKeyRoomTemp getCarKeyRoomTempBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(门牌车牌信息临时表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarKeyRoomTempCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(门牌车牌信息临时表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarKeyRoomTempCountDim(Map<String,Object> paramMap);
	/**
	 * 往(门牌车牌信息临时表)新增一条记录
	 * @param carKeyRoomTemp
	 * @return
	 */
	public int insertCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp);
	/**
	 * 批量新增(门牌车牌信息临时表)
	 * @param carKeyRoomTempList
	 * @return
	 */
	public int insertCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList);
	/**
	 * 更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTemp
	 * @return
	 */
	public int updateCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp);
	/**
	 * 批量更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTempList
	 * @return
	 */
	public int updateCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList);
	/**
	 * 根据序列号删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarKeyRoomTempLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarKeyRoomTempLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(门牌车牌信息临时表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarKeyRoomTemp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(门牌车牌信息临时表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarKeyRoomTempBatch(List<java.math.BigInteger> idList);
	
}
