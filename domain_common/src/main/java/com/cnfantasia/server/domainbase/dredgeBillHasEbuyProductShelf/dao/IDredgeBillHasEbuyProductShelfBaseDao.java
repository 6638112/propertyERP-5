package com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.entity.DredgeBillHasEbuyProductShelf;

/**
 * 描述:(用户自选耗材明细) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillHasEbuyProductShelfBaseDao {
	/**
	 * 根据条件查询(用户自选耗材明细)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> selectDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户自选耗材明细)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> selectDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户自选耗材明细)信息
	 * @param id
	 * @return
	 */
	public DredgeBillHasEbuyProductShelf selectDredgeBillHasEbuyProductShelfBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeBillHasEbuyProductShelfCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户自选耗材明细)新增一条记录
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	public int insertDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf);
	
	/**
	 * 批量新增(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	public int insertDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList);
	
	/**
	 * 更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	public int updateDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf);
	
	/**
	 * 批量更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	public int updateDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList);
	
	/**
	 * 根据序列号删除(用户自选耗材明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillHasEbuyProductShelfLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户自选耗材明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillHasEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户自选耗材明细)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillHasEbuyProductShelf(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户自选耗材明细)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillHasEbuyProductShelfBatch(List<java.math.BigInteger> idList);
	
	
}
