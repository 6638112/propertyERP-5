package com.cnfantasia.server.domainbase.propertyRechargePrefer.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyRechargePrefer.dao.IPropertyRechargePreferBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePrefer.entity.PropertyRechargePrefer;

/**
 * 描述:(物业充值随机立减记录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyRechargePreferBaseService implements IPropertyRechargePreferBaseService{
	
	private IPropertyRechargePreferBaseDao propertyRechargePreferBaseDao;
	public void setPropertyRechargePreferBaseDao(IPropertyRechargePreferBaseDao propertyRechargePreferBaseDao) {
		this.propertyRechargePreferBaseDao = propertyRechargePreferBaseDao;
	}
	/**
	 * 根据条件查询(物业充值随机立减记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> getPropertyRechargePreferByCondition(Map<String,Object> paramMap){
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业充值随机立减记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> getPropertyRechargePreferByConditionDim(Map<String,Object> paramMap){
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业充值随机立减记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> getPropertyRechargePreferByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业充值随机立减记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> getPropertyRechargePreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业充值随机立减记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePrefer getPropertyRechargePreferBySeqId(java.math.BigInteger id){
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业充值随机立减记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferCount(Map<String,Object> paramMap){
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业充值随机立减记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyRechargePreferCountDim(Map<String,Object> paramMap){
		return propertyRechargePreferBaseDao.selectPropertyRechargePreferCount(paramMap,true);
	}
	/**
	 * 往(物业充值随机立减记录表)新增一条记录
	 * @param propertyRechargePrefer
	 * @return
	 */
	@Override
	public int insertPropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer){
		return propertyRechargePreferBaseDao.insertPropertyRechargePrefer(propertyRechargePrefer);
	}
	/**
	 * 批量新增(物业充值随机立减记录表)
	 * @param propertyRechargePreferList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList){
		return propertyRechargePreferBaseDao.insertPropertyRechargePreferBatch(propertyRechargePreferList);
	}
	/**
	 * 更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePrefer
	 * @return
	 */
	@Override
	public int updatePropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer){
		return propertyRechargePreferBaseDao.updatePropertyRechargePrefer(propertyRechargePrefer);
	}
	/**
	 * 批量更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePreferList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList){
		return propertyRechargePreferBaseDao.updatePropertyRechargePreferBatch(propertyRechargePreferList);
	}
	/**
	 * 根据序列号删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferLogic(java.math.BigInteger id){
		return propertyRechargePreferBaseDao.deletePropertyRechargePreferLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferLogicBatch(List<java.math.BigInteger> idList){
		return propertyRechargePreferBaseDao.deletePropertyRechargePreferLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业充值随机立减记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePrefer(java.math.BigInteger id){
//		return propertyRechargePreferBaseDao.deletePropertyRechargePrefer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业充值随机立减记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferBatch(List<java.math.BigInteger> idList){
//		return propertyRechargePreferBaseDao.deletePropertyRechargePreferBatch(idList);
//	}
	
}
