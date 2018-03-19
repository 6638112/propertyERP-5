package com.cnfantasia.server.domainbase.propertyFeeDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 描述:(物业费费用清单详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PropertyFeeDetailBaseService implements IPropertyFeeDetailBaseService{
	
	private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	public void setPropertyFeeDetailBaseDao(IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao) {
		this.propertyFeeDetailBaseDao = propertyFeeDetailBaseDao;
	}
	/**
	 * 根据条件查询(物业费费用清单详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> getPropertyFeeDetailByCondition(Map<String,Object> paramMap){
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业费费用清单详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> getPropertyFeeDetailByConditionDim(Map<String,Object> paramMap){
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业费费用清单详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> getPropertyFeeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业费费用清单详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> getPropertyFeeDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业费费用清单详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyFeeDetail getPropertyFeeDetailBySeqId(java.math.BigInteger id){
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailCount(Map<String,Object> paramMap){
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPropertyFeeDetailCountDim(Map<String,Object> paramMap){
		return propertyFeeDetailBaseDao.selectPropertyFeeDetailCount(paramMap,true);
	}
	/**
	 * 往(物业费费用清单详情)新增一条记录
	 * @param propertyFeeDetail
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail){
		return propertyFeeDetailBaseDao.insertPropertyFeeDetail(propertyFeeDetail);
	}
	/**
	 * 批量新增(物业费费用清单详情)
	 * @param propertyFeeDetailList
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList){
		return propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
	}
	/**
	 * 更新(物业费费用清单详情)信息
	 * @param propertyFeeDetail
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetail(PropertyFeeDetail propertyFeeDetail){
		return propertyFeeDetailBaseDao.updatePropertyFeeDetail(propertyFeeDetail);
	}
	/**
	 * 批量更新(物业费费用清单详情)信息
	 * @param propertyFeeDetailList
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList){
		return propertyFeeDetailBaseDao.updatePropertyFeeDetailBatch(propertyFeeDetailList);
	}
	/**
	 * 根据序列号删除(物业费费用清单详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailLogic(java.math.BigInteger id){
		return propertyFeeDetailBaseDao.deletePropertyFeeDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业费费用清单详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailLogicBatch(List<java.math.BigInteger> idList){
		return propertyFeeDetailBaseDao.deletePropertyFeeDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业费费用清单详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetail(java.math.BigInteger id){
//		return propertyFeeDetailBaseDao.deletePropertyFeeDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业费费用清单详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailBatch(List<java.math.BigInteger> idList){
//		return propertyFeeDetailBaseDao.deletePropertyFeeDetailBatch(idList);
//	}
	
}
