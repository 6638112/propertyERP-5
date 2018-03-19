package com.cnfantasia.server.domainbase.dredgeBillAmountDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;

/**
 * 描述:(上门维修费用明细) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillAmountDetailBaseService {
	
	/**
	 * 根据条件查询(上门维修费用明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(上门维修费用明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(上门维修费用明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(上门维修费用明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillAmountDetail> getDredgeBillAmountDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(上门维修费用明细)信息
	 * @param id
	 * @return
	 */
	public DredgeBillAmountDetail getDredgeBillAmountDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(上门维修费用明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillAmountDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(上门维修费用明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillAmountDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(上门维修费用明细)新增一条记录
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	public int insertDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail);
	/**
	 * 批量新增(上门维修费用明细)
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	public int insertDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList);
	/**
	 * 更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	public int updateDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail);
	/**
	 * 批量更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	public int updateDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList);
	/**
	 * 根据序列号删除(上门维修费用明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillAmountDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(上门维修费用明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillAmountDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(上门维修费用明细)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillAmountDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(上门维修费用明细)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillAmountDetailBatch(List<java.math.BigInteger> idList);
	
}
