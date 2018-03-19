package com.cnfantasia.server.domainbase.familyMsgPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;

/**
 * 描述:(家庭留言板图片) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyMsgPicBaseService {
	
	/**
	 * 根据条件查询(家庭留言板图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgPic> getFamilyMsgPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(家庭留言板图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgPic> getFamilyMsgPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(家庭留言板图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgPic> getFamilyMsgPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(家庭留言板图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgPic> getFamilyMsgPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(家庭留言板图片)信息
	 * @param id
	 * @return
	 */
	public FamilyMsgPic getFamilyMsgPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(家庭留言板图片)新增一条记录
	 * @param familyMsgPic
	 * @return
	 */
	public int insertFamilyMsgPic(FamilyMsgPic familyMsgPic);
	/**
	 * 批量新增(家庭留言板图片)
	 * @param familyMsgPicList
	 * @return
	 */
	public int insertFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList);
	/**
	 * 更新(家庭留言板图片)信息
	 * @param familyMsgPic
	 * @return
	 */
	public int updateFamilyMsgPic(FamilyMsgPic familyMsgPic);
	/**
	 * 批量更新(家庭留言板图片)信息
	 * @param familyMsgPicList
	 * @return
	 */
	public int updateFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList);
	/**
	 * 根据序列号删除(家庭留言板图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFamilyMsgPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(家庭留言板图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFamilyMsgPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(家庭留言板图片)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFamilyMsgPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板图片)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFamilyMsgPicBatch(List<java.math.BigInteger> idList);
	
}
