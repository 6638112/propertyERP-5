package com.cnfantasia.server.domainbase.bcFinanceOrg.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;

/**
 * 描述:(银行托收金融机构信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcFinanceOrgBaseService {
	
	/**
	 * 根据条件查询(银行托收金融机构信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcFinanceOrg> getBcFinanceOrgByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(银行托收金融机构信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcFinanceOrg> getBcFinanceOrgByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcFinanceOrg> getBcFinanceOrgByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcFinanceOrg> getBcFinanceOrgByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(银行托收金融机构信息)信息
	 * @param id
	 * @return
	 */
	public BcFinanceOrg getBcFinanceOrgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcFinanceOrgCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcFinanceOrgCountDim(Map<String,Object> paramMap);
	/**
	 * 往(银行托收金融机构信息)新增一条记录
	 * @param bcFinanceOrg
	 * @return
	 */
	public int insertBcFinanceOrg(BcFinanceOrg bcFinanceOrg);
	/**
	 * 批量新增(银行托收金融机构信息)
	 * @param bcFinanceOrgList
	 * @return
	 */
	public int insertBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList);
	/**
	 * 更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrg
	 * @return
	 */
	public int updateBcFinanceOrg(BcFinanceOrg bcFinanceOrg);
	/**
	 * 批量更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrgList
	 * @return
	 */
	public int updateBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList);
	/**
	 * 根据序列号删除(银行托收金融机构信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcFinanceOrgLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(银行托收金融机构信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcFinanceOrgLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(银行托收金融机构信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcFinanceOrg(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(银行托收金融机构信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcFinanceOrgBatch(List<java.math.BigInteger> idList);
	
}
