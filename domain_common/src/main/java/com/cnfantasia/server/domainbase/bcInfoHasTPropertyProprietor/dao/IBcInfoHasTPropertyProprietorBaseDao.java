package com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.entity.BcInfoHasTPropertyProprietor;

/**
 * 描述:(物业公司托收银行信息包含的业主) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcInfoHasTPropertyProprietorBaseDao {
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> selectBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcInfoHasTPropertyProprietor> selectBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的业主)信息
	 * @param id
	 * @return
	 */
	public BcInfoHasTPropertyProprietor selectBcInfoHasTPropertyProprietorBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcInfoHasTPropertyProprietorCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业公司托收银行信息包含的业主)新增一条记录
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	public int insertBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor);
	
	/**
	 * 批量新增(物业公司托收银行信息包含的业主)信息
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
	 * 根据Id 批量删除(物业公司托收银行信息包含的业主)信息_逻辑删除
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
