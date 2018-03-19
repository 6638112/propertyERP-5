package com.cnfantasia.server.domainbase.payKeyList.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.payKeyList.entity.PayKeyList;

/**
 * 描述:(云钥匙付款列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayKeyListBaseService {
	
	/**
	 * 根据条件查询(云钥匙付款列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayKeyList> getPayKeyListByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(云钥匙付款列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PayKeyList> getPayKeyListByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(云钥匙付款列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayKeyList> getPayKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(云钥匙付款列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PayKeyList> getPayKeyListByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(云钥匙付款列表)信息
	 * @param id
	 * @return
	 */
	public PayKeyList getPayKeyListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPayKeyListCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPayKeyListCountDim(Map<String,Object> paramMap);
	/**
	 * 往(云钥匙付款列表)新增一条记录
	 * @param payKeyList
	 * @return
	 */
	public int insertPayKeyList(PayKeyList payKeyList);
	/**
	 * 批量新增(云钥匙付款列表)
	 * @param payKeyListList
	 * @return
	 */
	public int insertPayKeyListBatch(List<PayKeyList> payKeyListList);
	/**
	 * 更新(云钥匙付款列表)信息
	 * @param payKeyList
	 * @return
	 */
	public int updatePayKeyList(PayKeyList payKeyList);
	/**
	 * 批量更新(云钥匙付款列表)信息
	 * @param payKeyListList
	 * @return
	 */
	public int updatePayKeyListBatch(List<PayKeyList> payKeyListList);
	/**
	 * 根据序列号删除(云钥匙付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePayKeyListLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(云钥匙付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePayKeyListLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(云钥匙付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePayKeyList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePayKeyListBatch(List<java.math.BigInteger> idList);
	
}
