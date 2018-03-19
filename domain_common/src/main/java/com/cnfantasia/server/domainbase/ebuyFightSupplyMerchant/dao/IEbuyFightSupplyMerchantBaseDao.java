package com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

/**
 * 描述:(自提点表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyFightSupplyMerchantBaseDao {
	/**
	 * 根据条件查询(自提点表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyFightSupplyMerchant> selectEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(自提点表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyFightSupplyMerchant> selectEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(自提点表)信息
	 * @param id
	 * @return
	 */
	public EbuyFightSupplyMerchant selectEbuyFightSupplyMerchantBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(自提点表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyFightSupplyMerchantCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(自提点表)新增一条记录
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	public int insertEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant);
	
	/**
	 * 批量新增(自提点表)信息
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	public int insertEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList);
	
	/**
	 * 更新(自提点表)信息
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	public int updateEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant);
	
	/**
	 * 批量更新(自提点表)信息
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	public int updateEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList);
	
	/**
	 * 根据序列号删除(自提点表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyFightSupplyMerchantLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(自提点表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyFightSupplyMerchantLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(自提点表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyFightSupplyMerchant(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(自提点表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyFightSupplyMerchantBatch(List<java.math.BigInteger> idList);
	
	
}
