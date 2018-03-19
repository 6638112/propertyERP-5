package com.cnfantasia.server.domainbase.ebuyProductFightGroups.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductFightGroups.dao.IEbuyProductFightGroupsBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;

/**
 * 描述:(拼购商品规则表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductFightGroupsBaseService implements IEbuyProductFightGroupsBaseService{
	
	private IEbuyProductFightGroupsBaseDao ebuyProductFightGroupsBaseDao;
	public void setEbuyProductFightGroupsBaseDao(IEbuyProductFightGroupsBaseDao ebuyProductFightGroupsBaseDao) {
		this.ebuyProductFightGroupsBaseDao = ebuyProductFightGroupsBaseDao;
	}
	/**
	 * 根据条件查询(拼购商品规则表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByCondition(Map<String,Object> paramMap){
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼购商品规则表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByConditionDim(Map<String,Object> paramMap){
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼购商品规则表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼购商品规则表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼购商品规则表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductFightGroups getEbuyProductFightGroupsBySeqId(java.math.BigInteger id){
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductFightGroupsCount(Map<String,Object> paramMap){
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductFightGroupsCountDim(Map<String,Object> paramMap){
		return ebuyProductFightGroupsBaseDao.selectEbuyProductFightGroupsCount(paramMap,true);
	}
	/**
	 * 往(拼购商品规则表)新增一条记录
	 * @param ebuyProductFightGroups
	 * @return
	 */
	@Override
	public int insertEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups){
		return ebuyProductFightGroupsBaseDao.insertEbuyProductFightGroups(ebuyProductFightGroups);
	}
	/**
	 * 批量新增(拼购商品规则表)
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	@Override
	public int insertEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList){
		return ebuyProductFightGroupsBaseDao.insertEbuyProductFightGroupsBatch(ebuyProductFightGroupsList);
	}
	/**
	 * 更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroups
	 * @return
	 */
	@Override
	public int updateEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups){
		return ebuyProductFightGroupsBaseDao.updateEbuyProductFightGroups(ebuyProductFightGroups);
	}
	/**
	 * 批量更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	@Override
	public int updateEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList){
		return ebuyProductFightGroupsBaseDao.updateEbuyProductFightGroupsBatch(ebuyProductFightGroupsList);
	}
	/**
	 * 根据序列号删除(拼购商品规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightGroupsLogic(java.math.BigInteger id){
		return ebuyProductFightGroupsBaseDao.deleteEbuyProductFightGroupsLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼购商品规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightGroupsLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductFightGroupsBaseDao.deleteEbuyProductFightGroupsLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼购商品规则表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightGroups(java.math.BigInteger id){
//		return ebuyProductFightGroupsBaseDao.deleteEbuyProductFightGroups(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购商品规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightGroupsBatch(List<java.math.BigInteger> idList){
//		return ebuyProductFightGroupsBaseDao.deleteEbuyProductFightGroupsBatch(idList);
//	}
	
}
