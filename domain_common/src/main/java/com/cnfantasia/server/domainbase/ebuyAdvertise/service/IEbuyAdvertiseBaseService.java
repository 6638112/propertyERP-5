package com.cnfantasia.server.domainbase.ebuyAdvertise.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyAdvertiseBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyAdvertise> getEbuyAdvertiseByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyAdvertise> getEbuyAdvertiseByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyAdvertise> getEbuyAdvertiseByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyAdvertise> getEbuyAdvertiseByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public EbuyAdvertise getEbuyAdvertiseBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyAdvertiseCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyAdvertiseCountDim(Map<String, Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param ebuyAdvertise
	 * @return
	 */
	public int insertEbuyAdvertise(EbuyAdvertise ebuyAdvertise);
	/**
	 * 批量新增()
	 * @param ebuyAdvertiseList
	 * @return
	 */
	public int insertEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList);
	/**
	 * 更新()信息
	 * @param ebuyAdvertise
	 * @return
	 */
	public int updateEbuyAdvertise(EbuyAdvertise ebuyAdvertise);
	/**
	 * 批量更新()信息
	 * @param ebuyAdvertiseList
	 * @return
	 */
	public int updateEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyAdvertiseLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyAdvertiseLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyAdvertise(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyAdvertiseBatch(List<java.math.BigInteger> idList);
	
}
