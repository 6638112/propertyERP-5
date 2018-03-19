package com.cnfantasia.server.domainbase.ebuyExpressCompany.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;

/**
 * 描述:(快递公司) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyExpressCompanyBaseDao {
	/**
	 * 根据条件查询(快递公司)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyExpressCompany> selectEbuyExpressCompanyByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(快递公司)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyExpressCompany> selectEbuyExpressCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(快递公司)信息
	 * @param id
	 * @return
	 */
	public EbuyExpressCompany selectEbuyExpressCompanyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(快递公司)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyExpressCompanyCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(快递公司)新增一条记录
	 * @param ebuyExpressCompany
	 * @return
	 */
	public int insertEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany);
	
	/**
	 * 批量新增(快递公司)信息
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	public int insertEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList);
	
	/**
	 * 更新(快递公司)信息
	 * @param ebuyExpressCompany
	 * @return
	 */
	public int updateEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany);
	
	/**
	 * 批量更新(快递公司)信息
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	public int updateEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList);
	
	/**
	 * 根据序列号删除(快递公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyExpressCompanyLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(快递公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyExpressCompanyLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(快递公司)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyExpressCompany(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(快递公司)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyExpressCompanyBatch(List<java.math.BigInteger> idList);
	
	
}
