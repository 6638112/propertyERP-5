package com.cnfantasia.server.domainbase.familyMsgPic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;

/**
 * 描述:(家庭留言板图片) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyMsgPicBaseDao {
	/**
	 * 根据条件查询(家庭留言板图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FamilyMsgPic> selectFamilyMsgPicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(家庭留言板图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<FamilyMsgPic> selectFamilyMsgPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(家庭留言板图片)信息
	 * @param id
	 * @return
	 */
	public FamilyMsgPic selectFamilyMsgPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectFamilyMsgPicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(家庭留言板图片)新增一条记录
	 * @param familyMsgPic
	 * @return
	 */
	public int insertFamilyMsgPic(FamilyMsgPic familyMsgPic);
	
	/**
	 * 批量新增(家庭留言板图片)信息
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
	 * 根据Id 批量删除(家庭留言板图片)信息_逻辑删除
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
