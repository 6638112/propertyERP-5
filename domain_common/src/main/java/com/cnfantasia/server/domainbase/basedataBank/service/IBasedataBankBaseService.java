package com.cnfantasia.server.domainbase.basedataBank.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;

/**
 * 描述:(银行基础资料) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBasedataBankBaseService {
	
	/**
	 * 根据条件查询(银行基础资料)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BasedataBank> getBasedataBankByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(银行基础资料)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BasedataBank> getBasedataBankByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(银行基础资料)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BasedataBank> getBasedataBankByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(银行基础资料)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BasedataBank> getBasedataBankByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(银行基础资料)信息
	 * @param id
	 * @return
	 */
	public BasedataBank getBasedataBankBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBasedataBankCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBasedataBankCountDim(Map<String,Object> paramMap);
	/**
	 * 往(银行基础资料)新增一条记录
	 * @param basedataBank
	 * @return
	 */
	public int insertBasedataBank(BasedataBank basedataBank);
	/**
	 * 批量新增(银行基础资料)
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
	 * 根据序列号批量删除(银行基础资料)信息_逻辑删除
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
