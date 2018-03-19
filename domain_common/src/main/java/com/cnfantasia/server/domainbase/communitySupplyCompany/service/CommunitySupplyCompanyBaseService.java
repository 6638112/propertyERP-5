package com.cnfantasia.server.domainbase.communitySupplyCompany.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyCompany.dao.ICommunitySupplyCompanyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyCompany.entity.CommunitySupplyCompany;

/**
 * 描述:(社区商户公司（管理多个社区商家）) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyCompanyBaseService implements ICommunitySupplyCompanyBaseService{
	
	private ICommunitySupplyCompanyBaseDao communitySupplyCompanyBaseDao;
	public void setCommunitySupplyCompanyBaseDao(ICommunitySupplyCompanyBaseDao communitySupplyCompanyBaseDao) {
		this.communitySupplyCompanyBaseDao = communitySupplyCompanyBaseDao;
	}
	/**
	 * 根据条件查询(社区商户公司（管理多个社区商家）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByCondition(Map<String,Object> paramMap){
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商户公司（管理多个社区商家）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByConditionDim(Map<String,Object> paramMap){
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商户公司（管理多个社区商家）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商户公司（管理多个社区商家）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> getCommunitySupplyCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商户公司（管理多个社区商家）)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyCompany getCommunitySupplyCompanyBySeqId(java.math.BigInteger id){
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商户公司（管理多个社区商家）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCompanyCount(Map<String,Object> paramMap){
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商户公司（管理多个社区商家）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyCompanyCountDim(Map<String,Object> paramMap){
		return communitySupplyCompanyBaseDao.selectCommunitySupplyCompanyCount(paramMap,true);
	}
	/**
	 * 往(社区商户公司（管理多个社区商家）)新增一条记录
	 * @param communitySupplyCompany
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany){
		return communitySupplyCompanyBaseDao.insertCommunitySupplyCompany(communitySupplyCompany);
	}
	/**
	 * 批量新增(社区商户公司（管理多个社区商家）)
	 * @param communitySupplyCompanyList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList){
		return communitySupplyCompanyBaseDao.insertCommunitySupplyCompanyBatch(communitySupplyCompanyList);
	}
	/**
	 * 更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompany
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany){
		return communitySupplyCompanyBaseDao.updateCommunitySupplyCompany(communitySupplyCompany);
	}
	/**
	 * 批量更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompanyList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList){
		return communitySupplyCompanyBaseDao.updateCommunitySupplyCompanyBatch(communitySupplyCompanyList);
	}
	/**
	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyLogic(java.math.BigInteger id){
		return communitySupplyCompanyBaseDao.deleteCommunitySupplyCompanyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyCompanyBaseDao.deleteCommunitySupplyCompanyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompany(java.math.BigInteger id){
//		return communitySupplyCompanyBaseDao.deleteCommunitySupplyCompany(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商户公司（管理多个社区商家）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyBatch(List<java.math.BigInteger> idList){
//		return communitySupplyCompanyBaseDao.deleteCommunitySupplyCompanyBatch(idList);
//	}
	
}
