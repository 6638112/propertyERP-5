package com.cnfantasia.server.domainbase.propertyFeeDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 描述:(物业费费用清单详情) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyFeeDetailBaseService {
	
	/**
	 * 根据条件查询(物业费费用清单详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyFeeDetail> getPropertyFeeDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业费费用清单详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PropertyFeeDetail> getPropertyFeeDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业费费用清单详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyFeeDetail> getPropertyFeeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业费费用清单详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PropertyFeeDetail> getPropertyFeeDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业费费用清单详情)信息
	 * @param id
	 * @return
	 */
	public PropertyFeeDetail getPropertyFeeDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyFeeDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPropertyFeeDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业费费用清单详情)新增一条记录
	 * @param propertyFeeDetail
	 * @return
	 */
	public int insertPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail);
	/**
	 * 批量新增(物业费费用清单详情)
	 * @param propertyFeeDetailList
	 * @return
	 */
	public int insertPropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList);
	/**
	 * 更新(物业费费用清单详情)信息
	 * @param propertyFeeDetail
	 * @return
	 */
	public int updatePropertyFeeDetail(PropertyFeeDetail propertyFeeDetail);
	/**
	 * 批量更新(物业费费用清单详情)信息
	 * @param propertyFeeDetailList
	 * @return
	 */
	public int updatePropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList);
	/**
	 * 根据序列号删除(物业费费用清单详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePropertyFeeDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业费费用清单详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePropertyFeeDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业费费用清单详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePropertyFeeDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业费费用清单详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePropertyFeeDetailBatch(List<java.math.BigInteger> idList);
	
}
