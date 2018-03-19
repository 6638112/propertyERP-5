package com.cnfantasia.server.domainbase.commonGatherData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * 描述:(通用模块信息归集) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommonGatherDataBaseService {
	
	/**
	 * 根据条件查询(通用模块信息归集)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommonGatherData> getCommonGatherDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(通用模块信息归集)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommonGatherData> getCommonGatherDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(通用模块信息归集)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommonGatherData> getCommonGatherDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(通用模块信息归集)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommonGatherData> getCommonGatherDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(通用模块信息归集)信息
	 * @param id
	 * @return
	 */
	public CommonGatherData getCommonGatherDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(通用模块信息归集)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommonGatherDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(通用模块信息归集)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommonGatherDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(通用模块信息归集)新增一条记录
	 * @param commonGatherData
	 * @return
	 */
	public int insertCommonGatherData(CommonGatherData commonGatherData);
	/**
	 * 批量新增(通用模块信息归集)
	 * @param commonGatherDataList
	 * @return
	 */
	public int insertCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList);
	/**
	 * 更新(通用模块信息归集)信息
	 * @param commonGatherData
	 * @return
	 */
	public int updateCommonGatherData(CommonGatherData commonGatherData);
	/**
	 * 批量更新(通用模块信息归集)信息
	 * @param commonGatherDataList
	 * @return
	 */
	public int updateCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList);
	/**
	 * 根据序列号删除(通用模块信息归集)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommonGatherDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(通用模块信息归集)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommonGatherDataLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(通用模块信息归集)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommonGatherData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(通用模块信息归集)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommonGatherDataBatch(List<java.math.BigInteger> idList);
	
}
