package com.cnfantasia.server.domainbase.kitchenDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * 描述:(厨房菜谱详情) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IKitchenDetailBaseDao {
	/**
	 * 根据条件查询(厨房菜谱详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenDetail> selectKitchenDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(厨房菜谱详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<KitchenDetail> selectKitchenDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(厨房菜谱详情)信息
	 * @param id
	 * @return
	 */
	public KitchenDetail selectKitchenDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(厨房菜谱详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectKitchenDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(厨房菜谱详情)新增一条记录
	 * @param kitchenDetail
	 * @return
	 */
	public int insertKitchenDetail(KitchenDetail kitchenDetail);
	
	/**
	 * 批量新增(厨房菜谱详情)信息
	 * @param kitchenDetailList
	 * @return
	 */
	public int insertKitchenDetailBatch(List<KitchenDetail> kitchenDetailList);
	
	/**
	 * 更新(厨房菜谱详情)信息
	 * @param kitchenDetail
	 * @return
	 */
	public int updateKitchenDetail(KitchenDetail kitchenDetail);
	
	/**
	 * 批量更新(厨房菜谱详情)信息
	 * @param kitchenDetailList
	 * @return
	 */
	public int updateKitchenDetailBatch(List<KitchenDetail> kitchenDetailList);
	
	/**
	 * 根据序列号删除(厨房菜谱详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteKitchenDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(厨房菜谱详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteKitchenDetailLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(厨房菜谱详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteKitchenDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteKitchenDetailBatch(List<java.math.BigInteger> idList);
	
	
}
