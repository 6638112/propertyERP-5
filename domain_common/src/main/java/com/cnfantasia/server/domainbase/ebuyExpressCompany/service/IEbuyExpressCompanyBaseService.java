package com.cnfantasia.server.domainbase.ebuyExpressCompany.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;

/**
 * 描述:(快递公司) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyExpressCompanyBaseService {
	
	/**
	 * 根据条件查询(快递公司)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyExpressCompany> getEbuyExpressCompanyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(快递公司)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyExpressCompany> getEbuyExpressCompanyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(快递公司)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyExpressCompany> getEbuyExpressCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(快递公司)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyExpressCompany> getEbuyExpressCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(快递公司)信息
	 * @param id
	 * @return
	 */
	public EbuyExpressCompany getEbuyExpressCompanyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(快递公司)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyExpressCompanyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(快递公司)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyExpressCompanyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(快递公司)新增一条记录
	 * @param ebuyExpressCompany
	 * @return
	 */
	public int insertEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany);
	/**
	 * 批量新增(快递公司)
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
	 * 根据序列号批量删除(快递公司)信息_逻辑删除
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
