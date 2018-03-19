package com.cnfantasia.server.domainbase.carPrefer.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carPrefer.entity.CarPrefer;

/**
 * 描述:(车禁随机立减) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarPreferBaseService {
	
	/**
	 * 根据条件查询(车禁随机立减)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarPrefer> getCarPreferByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(车禁随机立减)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarPrefer> getCarPreferByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(车禁随机立减)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarPrefer> getCarPreferByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(车禁随机立减)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarPrefer> getCarPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(车禁随机立减)信息
	 * @param id
	 * @return
	 */
	public CarPrefer getCarPreferBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(车禁随机立减)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarPreferCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(车禁随机立减)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarPreferCountDim(Map<String,Object> paramMap);
	/**
	 * 往(车禁随机立减)新增一条记录
	 * @param carPrefer
	 * @return
	 */
	public int insertCarPrefer(CarPrefer carPrefer);
	/**
	 * 批量新增(车禁随机立减)
	 * @param carPreferList
	 * @return
	 */
	public int insertCarPreferBatch(List<CarPrefer> carPreferList);
	/**
	 * 更新(车禁随机立减)信息
	 * @param carPrefer
	 * @return
	 */
	public int updateCarPrefer(CarPrefer carPrefer);
	/**
	 * 批量更新(车禁随机立减)信息
	 * @param carPreferList
	 * @return
	 */
	public int updateCarPreferBatch(List<CarPrefer> carPreferList);
	/**
	 * 根据序列号删除(车禁随机立减)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteCarPreferLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(车禁随机立减)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteCarPreferLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(车禁随机立减)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarPrefer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(车禁随机立减)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarPreferBatch(List<java.math.BigInteger> idList);
	
}
