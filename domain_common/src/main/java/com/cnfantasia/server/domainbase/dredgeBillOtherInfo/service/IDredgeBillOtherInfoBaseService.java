package com.cnfantasia.server.domainbase.dredgeBillOtherInfo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillOtherInfo.entity.DredgeBillOtherInfo;

/**
 * 描述:(维修单的与第三方供应商的信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillOtherInfoBaseService {
	
	/**
	 * 根据条件查询(维修单的与第三方供应商的信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(维修单的与第三方供应商的信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(维修单的与第三方供应商的信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(维修单的与第三方供应商的信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillOtherInfo> getDredgeBillOtherInfoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(维修单的与第三方供应商的信息)信息
	 * @param id
	 * @return
	 */
	public DredgeBillOtherInfo getDredgeBillOtherInfoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修单的与第三方供应商的信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillOtherInfoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修单的与第三方供应商的信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillOtherInfoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(维修单的与第三方供应商的信息)新增一条记录
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	public int insertDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo);
	/**
	 * 批量新增(维修单的与第三方供应商的信息)
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	public int insertDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList);
	/**
	 * 更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	public int updateDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo);
	/**
	 * 批量更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	public int updateDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList);
	/**
	 * 根据序列号删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillOtherInfoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillOtherInfoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(维修单的与第三方供应商的信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillOtherInfo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修单的与第三方供应商的信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillOtherInfoBatch(List<java.math.BigInteger> idList);
	
}
