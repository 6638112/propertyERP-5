package com.cnfantasia.server.domainbase.basedataBank.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;

/**
 * 描述:(银行基础资料) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBasedataBankBaseDao {
	/**
	 * 根据条件查询(银行基础资料)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BasedataBank> selectBasedataBankByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(银行基础资料)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BasedataBank> selectBasedataBankByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(银行基础资料)信息
	 * @param id
	 * @return
	 */
	public BasedataBank selectBasedataBankBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBasedataBankCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(银行基础资料)新增一条记录
	 * @param basedataBank
	 * @return
	 */
	public int insertBasedataBank(BasedataBank basedataBank);
	
	/**
	 * 批量新增(银行基础资料)信息
	 * @param basedataBankList
	 * @return
	 */
	public int insertBasedataBankBatch(List<BasedataBank> basedataBankList);
	
	/**
	 * 更新(银行基础资料)信息
	 * @param basedataBank
	 * @return
	 */
	public int updateBasedataBank(BasedataBank basedataBank);
	
	/**
	 * 批量更新(银行基础资料)信息
	 * @param basedataBankList
	 * @return
	 */
	public int updateBasedataBankBatch(List<BasedataBank> basedataBankList);
	
	/**
	 * 根据序列号删除(银行基础资料)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteBasedataBankLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据Id 批量删除(银行基础资料)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteBasedataBankLogicBatch(List<java.math.BigInteger> idList);
	 */
	
//	/**
//	 * 根据序列号删除(银行基础资料)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBasedataBank(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(银行基础资料)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBasedataBankBatch(List<java.math.BigInteger> idList);
	
	
}
