package com.cnfantasia.server.domainbase.bcFinanceOrg.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;

/**
 * 描述:(银行托收金融机构信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcFinanceOrgBaseDao {
	/**
	 * 根据条件查询(银行托收金融机构信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcFinanceOrg> selectBcFinanceOrgByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcFinanceOrg> selectBcFinanceOrgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(银行托收金融机构信息)信息
	 * @param id
	 * @return
	 */
	public BcFinanceOrg selectBcFinanceOrgBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcFinanceOrgCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(银行托收金融机构信息)新增一条记录
	 * @param bcFinanceOrg
	 * @return
	 */
	public int insertBcFinanceOrg(BcFinanceOrg bcFinanceOrg);
	
	/**
	 * 批量新增(银行托收金融机构信息)信息
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
	 * 根据Id 批量删除(银行托收金融机构信息)信息_逻辑删除
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
