package com.cnfantasia.server.domainbase.carNumList.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

/**
 * 描述:(车牌表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarNumListBaseService {
	
	/**
	 * 根据条件查询(车牌表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarNumList> getCarNumListByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(车牌表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarNumList> getCarNumListByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(车牌表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarNumList> getCarNumListByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(车牌表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarNumList> getCarNumListByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	public CarNumList getCarNumListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarNumListCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(车牌表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarNumListCountDim(Map<String,Object> paramMap);
	/**
	 * 往(车牌表)新增一条记录
	 * @param carNumList
	 * @return
	 */
	public int insertCarNumList(CarNumList carNumList);
	/**
	 * 批量新增(车牌表)
	 * @param carNumListList
	 * @return
	 */
	public int insertCarNumListBatch(List<CarNumList> carNumListList);
	/**
	 * 更新(车牌表)信息
	 * @param carNumList
	 * @return
	 */
	public int updateCarNumList(CarNumList carNumList);
	/**
	 * 批量更新(车牌表)信息
	 * @param carNumListList
	 * @return
	 */
	public int updateCarNumListBatch(List<CarNumList> carNumListList);
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarNumListLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarNumListLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarNumList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarNumListBatch(List<java.math.BigInteger> idList);
	
}
