package com.cnfantasia.server.domainbase.propertyFeeDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 描述:(物业费费用清单详情) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPropertyFeeDetailBaseDao {
	/**
	 * 根据条件查询(物业费费用清单详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyFeeDetail> selectPropertyFeeDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业费费用清单详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PropertyFeeDetail> selectPropertyFeeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业费费用清单详情)信息
	 * @param id
	 * @return
	 */
	public PropertyFeeDetail selectPropertyFeeDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPropertyFeeDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业费费用清单详情)新增一条记录
	 * @param propertyFeeDetail
	 * @return
	 */
	public int insertPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail);
	
	/**
	 * 批量新增(物业费费用清单详情)信息
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
	 * 根据Id 批量删除(物业费费用清单详情)信息_逻辑删除
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
