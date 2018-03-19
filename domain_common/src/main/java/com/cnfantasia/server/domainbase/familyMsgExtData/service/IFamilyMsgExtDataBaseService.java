package com.cnfantasia.server.domainbase.familyMsgExtData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;

/**
 * 描述:(家庭留言板拓展信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyMsgExtDataBaseService {
	
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgExtData> getFamilyMsgExtDataByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgExtData> getFamilyMsgExtDataByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgExtData> getFamilyMsgExtDataByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgExtData> getFamilyMsgExtDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(家庭留言板拓展信息)信息
	 * @param id
	 * @return
	 */
	public FamilyMsgExtData getFamilyMsgExtDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgExtDataCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgExtDataCountDim(Map<String,Object> paramMap);
	/**
	 * 往(家庭留言板拓展信息)新增一条记录
	 * @param familyMsgExtData
	 * @return
	 */
	public int insertFamilyMsgExtData(FamilyMsgExtData familyMsgExtData);
	/**
	 * 批量新增(家庭留言板拓展信息)
	 * @param familyMsgExtDataList
	 * @return
	 */
	public int insertFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList);
	/**
	 * 更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtData
	 * @return
	 */
	public int updateFamilyMsgExtData(FamilyMsgExtData familyMsgExtData);
	/**
	 * 批量更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtDataList
	 * @return
	 */
	public int updateFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList);
	/**
	 * 根据序列号删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFamilyMsgExtDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFamilyMsgExtDataLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(家庭留言板拓展信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFamilyMsgExtData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板拓展信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFamilyMsgExtDataBatch(List<java.math.BigInteger> idList);
	
}
