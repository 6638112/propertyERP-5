package com.cnfantasia.server.domainbase.payKeyList.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payKeyList.entity.PayKeyList;

/**
 * 描述:(云钥匙付款列表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPayKeyListBaseDao {
	/**
	 * 根据条件查询(云钥匙付款列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayKeyList> selectPayKeyListByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(云钥匙付款列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PayKeyList> selectPayKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(云钥匙付款列表)信息
	 * @param id
	 * @return
	 */
	public PayKeyList selectPayKeyListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPayKeyListCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(云钥匙付款列表)新增一条记录
	 * @param payKeyList
	 * @return
	 */
	public int insertPayKeyList(PayKeyList payKeyList);
	
	/**
	 * 批量新增(云钥匙付款列表)信息
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
	 * 根据Id 批量删除(云钥匙付款列表)信息_逻辑删除
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
