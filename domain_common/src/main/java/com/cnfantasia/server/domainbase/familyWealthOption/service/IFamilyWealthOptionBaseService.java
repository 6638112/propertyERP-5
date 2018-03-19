package com.cnfantasia.server.domainbase.familyWealthOption.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.familyWealthOption.entity.FamilyWealthOption;

/**
 * 描述:(家庭财富选项) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyWealthOptionBaseService {
	
	/**
	 * 根据条件查询(家庭财富选项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyWealthOption> getFamilyWealthOptionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(家庭财富选项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyWealthOption> getFamilyWealthOptionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(家庭财富选项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyWealthOption> getFamilyWealthOptionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(家庭财富选项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyWealthOption> getFamilyWealthOptionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(家庭财富选项)信息
	 * @param id
	 * @return
	 */
	public FamilyWealthOption getFamilyWealthOptionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭财富选项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyWealthOptionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(家庭财富选项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyWealthOptionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(家庭财富选项)新增一条记录
	 * @param familyWealthOption
	 * @return
	 */
	public int insertFamilyWealthOption(FamilyWealthOption familyWealthOption);
	/**
	 * 批量新增(家庭财富选项)
	 * @param familyWealthOptionList
	 * @return
	 */
	public int insertFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList);
	/**
	 * 更新(家庭财富选项)信息
	 * @param familyWealthOption
	 * @return
	 */
	public int updateFamilyWealthOption(FamilyWealthOption familyWealthOption);
	/**
	 * 批量更新(家庭财富选项)信息
	 * @param familyWealthOptionList
	 * @return
	 */
	public int updateFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList);
	/**
	 * 根据序列号删除(家庭财富选项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFamilyWealthOptionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(家庭财富选项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFamilyWealthOptionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(家庭财富选项)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFamilyWealthOption(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(家庭财富选项)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFamilyWealthOptionBatch(List<java.math.BigInteger> idList);
	
}
