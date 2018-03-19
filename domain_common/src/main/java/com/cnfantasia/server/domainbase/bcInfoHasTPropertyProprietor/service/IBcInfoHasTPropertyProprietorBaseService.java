package com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.entity.BcInfoHasTPropertyProprietor;

/**
 * 描述:(物业公司托收银行信息包含的业主) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcInfoHasTPropertyProprietorBaseService {
	
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> getBcInfoHasTPropertyProprietorByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的业主)信息
	 * @param id
	 * @return
	 */
	public BcInfoHasTPropertyProprietor getBcInfoHasTPropertyProprietorBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcInfoHasTPropertyProprietorCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcInfoHasTPropertyProprietorCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业公司托收银行信息包含的业主)新增一条记录
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	public int insertBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor);
	/**
	 * 批量新增(物业公司托收银行信息包含的业主)
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	public int insertBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList);
	/**
	 * 更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	public int updateBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor);
	/**
	 * 批量更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	public int updateBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList);
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcInfoHasTPropertyProprietorLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcInfoHasTPropertyProprietorLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcInfoHasTPropertyProprietor(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的业主)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcInfoHasTPropertyProprietorBatch(List<java.math.BigInteger> idList);
	
}
