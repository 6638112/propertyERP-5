package com.cnfantasia.server.domainbase.communitySupplyCompany.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyCompany.entity.CommunitySupplyCompany;

/**
 * 描述:(社区商户公司（管理多个社区商家）) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyCompanyBaseService {
	
	/**
	 * 根据条件查询(社区商户公司（管理多个社区商家）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(社区商户公司（管理多个社区商家）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(社区商户公司（管理多个社区商家）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(社区商户公司（管理多个社区商家）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(社区商户公司（管理多个社区商家）)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyCompany getCommunitySupplyCompanyBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商户公司（管理多个社区商家）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyCompanyCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(社区商户公司（管理多个社区商家）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyCompanyCountDim(Map<String,Object> paramMap);
	/**
	 * 往(社区商户公司（管理多个社区商家）)新增一条记录
	 * @param communitySupplyCompany
	 * @return
	 */
	public int insertCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany);
	/**
	 * 批量新增(社区商户公司（管理多个社区商家）)
	 * @param communitySupplyCompanyList
	 * @return
	 */
	public int insertCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList);
	/**
	 * 更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompany
	 * @return
	 */
	public int updateCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany);
	/**
	 * 批量更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompanyList
	 * @return
	 */
	public int updateCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList);
	/**
	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyCompanyLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyCompanyLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyCompany(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商户公司（管理多个社区商家）)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyCompanyBatch(List<java.math.BigInteger> idList);
	
}
