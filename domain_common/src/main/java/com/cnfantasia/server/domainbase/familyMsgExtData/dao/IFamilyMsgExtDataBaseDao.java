package com.cnfantasia.server.domainbase.familyMsgExtData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;

/**
 * 描述:(家庭留言板拓展信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyMsgExtDataBaseDao {
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FamilyMsgExtData> selectFamilyMsgExtDataByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FamilyMsgExtData> selectFamilyMsgExtDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(家庭留言板拓展信息)信息
	 * @param id
	 * @return
	 */
	public FamilyMsgExtData selectFamilyMsgExtDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectFamilyMsgExtDataCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(家庭留言板拓展信息)新增一条记录
	 * @param familyMsgExtData
	 * @return
	 */
	public int insertFamilyMsgExtData(FamilyMsgExtData familyMsgExtData);
	
	/**
	 * 批量新增(家庭留言板拓展信息)信息
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
	 * 根据Id 批量删除(家庭留言板拓展信息)信息_逻辑删除
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
