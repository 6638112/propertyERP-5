package com.cnfantasia.server.domainbase.msPrizeOption.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * 描述:(奖项表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeOptionBaseService {
	
	/**
	 * 根据条件查询(奖项表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeOption> getMsPrizeOptionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(奖项表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeOption> getMsPrizeOptionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(奖项表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOption> getMsPrizeOptionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(奖项表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOption> getMsPrizeOptionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(奖项表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeOption getMsPrizeOptionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖项表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeOptionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(奖项表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeOptionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(奖项表)新增一条记录
	 * @param msPrizeOption
	 * @return
	 */
	public int insertMsPrizeOption(MsPrizeOption msPrizeOption);
	/**
	 * 批量新增(奖项表)
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
	 * 根据序列号批量删除(奖项表)信息_逻辑删除
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
