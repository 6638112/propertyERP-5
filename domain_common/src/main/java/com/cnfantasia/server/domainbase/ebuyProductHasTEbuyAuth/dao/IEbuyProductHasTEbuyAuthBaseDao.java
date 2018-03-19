package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.entity.EbuyProductHasTEbuyAuth;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductHasTEbuyAuthBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> selectEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductHasTEbuyAuth> selectEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyProductHasTEbuyAuth selectEbuyProductHasTEbuyAuthBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductHasTEbuyAuthCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	public int insertEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
