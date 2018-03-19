package com.cnfantasia.server.domainbase.microblogContent.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

/**
 * 描述:(微博信息表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMicroblogContentBaseDao {
	/**
	 * 根据条件查询(微博信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogContent> selectMicroblogContentByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(微博信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MicroblogContent> selectMicroblogContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(微博信息表)信息
	 * @param id
	 * @return
	 */
	public MicroblogContent selectMicroblogContentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微博信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMicroblogContentCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(微博信息表)新增一条记录
	 * @param microblogContent
	 * @return
	 */
	public int insertMicroblogContent(MicroblogContent microblogContent);
	
	/**
	 * 批量新增(微博信息表)信息
	 * @param microblogContentList
	 * @return
	 */
	public int insertMicroblogContentBatch(List<MicroblogContent> microblogContentList);
	
	/**
	 * 更新(微博信息表)信息
	 * @param microblogContent
	 * @return
	 */
	public int updateMicroblogContent(MicroblogContent microblogContent);
	
	/**
	 * 批量更新(微博信息表)信息
	 * @param microblogContentList
	 * @return
	 */
	public int updateMicroblogContentBatch(List<MicroblogContent> microblogContentList);
	
	/**
	 * 根据序列号删除(微博信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMicroblogContentLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(微博信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMicroblogContentLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(微博信息表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMicroblogContent(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微博信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMicroblogContentBatch(List<java.math.BigInteger> idList);
	
	
}
