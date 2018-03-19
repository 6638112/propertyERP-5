package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.entity.EbuyProductHasTEbuyAuth;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductHasTEbuyAuthBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyProductHasTEbuyAuth getEbuyProductHasTEbuyAuthBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductHasTEbuyAuthCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductHasTEbuyAuthCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	public int insertEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth);
	/**
	 * 批量新增()
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	public int insertEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList);
	/**
	 * 更新()信息
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	public int updateEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth);
	/**
	 * 批量更新()信息
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	public int updateEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductHasTEbuyAuthLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductHasTEbuyAuthLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductHasTEbuyAuth(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductHasTEbuyAuthBatch(List<java.math.BigInteger> idList);
	
}
