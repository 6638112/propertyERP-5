package com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.dao.IPropertyCompanyThirdPayCfgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;

/**
 * 描述:(物业公司/管理处/自有平台的支付宝信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyCompanyThirdPayCfgBaseService implements IPropertyCompanyThirdPayCfgBaseService{
	
	private IPropertyCompanyThirdPayCfgBaseDao propertyCompanyThirdPayCfgBaseDao;
	public void setPropertyCompanyThirdPayCfgBaseDao(IPropertyCompanyThirdPayCfgBaseDao propertyCompanyThirdPayCfgBaseDao) {
		this.propertyCompanyThirdPayCfgBaseDao = propertyCompanyThirdPayCfgBaseDao;
	}
	/**
	 * 根据条件查询(物业公司/管理处/自有平台的支付宝信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByCondition(Map<String,Object> paramMap){
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司/管理处/自有平台的支付宝信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByConditionDim(Map<String,Object> paramMap){
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司/管理处/自有平台的支付宝信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司/管理处/自有平台的支付宝信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> getPropertyCompanyThirdPayCfgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyThirdPayCfg getPropertyCompanyThirdPayCfgBySeqId(java.math.BigInteger id){
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司/管理处/自有平台的支付宝信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyThirdPayCfgCount(Map<String,Object> paramMap){
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司/管理处/自有平台的支付宝信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyCompanyThirdPayCfgCountDim(Map<String,Object> paramMap){
		return propertyCompanyThirdPayCfgBaseDao.selectPropertyCompanyThirdPayCfgCount(paramMap,true);
	}
	/**
	 * 往(物业公司/管理处/自有平台的支付宝信息)新增一条记录
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	@Override
	public int insertPropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg){
		return propertyCompanyThirdPayCfgBaseDao.insertPropertyCompanyThirdPayCfg(propertyCompanyThirdPayCfg);
	}
	/**
	 * 批量新增(物业公司/管理处/自有平台的支付宝信息)
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList){
		return propertyCompanyThirdPayCfgBaseDao.insertPropertyCompanyThirdPayCfgBatch(propertyCompanyThirdPayCfgList);
	}
	/**
	 * 更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	@Override
	public int updatePropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg){
		return propertyCompanyThirdPayCfgBaseDao.updatePropertyCompanyThirdPayCfg(propertyCompanyThirdPayCfg);
	}
	/**
	 * 批量更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList){
		return propertyCompanyThirdPayCfgBaseDao.updatePropertyCompanyThirdPayCfgBatch(propertyCompanyThirdPayCfgList);
	}
	/**
	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyThirdPayCfgLogic(java.math.BigInteger id){
		return propertyCompanyThirdPayCfgBaseDao.deletePropertyCompanyThirdPayCfgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyThirdPayCfgLogicBatch(List<java.math.BigInteger> idList){
		return propertyCompanyThirdPayCfgBaseDao.deletePropertyCompanyThirdPayCfgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyThirdPayCfg(java.math.BigInteger id){
//		return propertyCompanyThirdPayCfgBaseDao.deletePropertyCompanyThirdPayCfg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyThirdPayCfgBatch(List<java.math.BigInteger> idList){
//		return propertyCompanyThirdPayCfgBaseDao.deletePropertyCompanyThirdPayCfgBatch(idList);
//	}
	
}
