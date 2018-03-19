package com.cnfantasia.server.domainbase.msPrizeOption.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * 描述:(奖项表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeOptionBaseDao {
	/**
	 * 根据条件查询(奖项表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeOption> selectMsPrizeOptionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(奖项表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeOption> selectMsPrizeOptionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(奖项表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeOption selectMsPrizeOptionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖项表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMsPrizeOptionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(奖项表)新增一条记录
	 * @param msPrizeOption
	 * @return
	 */
	public int insertMsPrizeOption(MsPrizeOption msPrizeOption);
	
	/**
	 * 批量新增(奖项表)信息
	 * @param msPrizeOptionList
	 * @return
	 */
	public int insertMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList);
	
	/**
	 * 更新(奖项表)信息
	 * @param msPrizeOption
	 * @return
	 */
	public int updateMsPrizeOption(MsPrizeOption msPrizeOption);
	
	/**
	 * 批量更新(奖项表)信息
	 * @param msPrizeOptionList
	 * @return
	 */
	public int updateMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList);
	
	/**
	 * 根据序列号删除(奖项表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeOptionLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(奖项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeOptionLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(奖项表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeOption(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(奖项表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeOptionBatch(List<java.math.BigInteger> idList);
	
	
}
