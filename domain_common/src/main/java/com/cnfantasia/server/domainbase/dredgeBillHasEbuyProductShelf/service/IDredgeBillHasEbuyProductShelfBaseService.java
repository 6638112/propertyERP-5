package com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.entity.DredgeBillHasEbuyProductShelf;

/**
 * 描述:(用户自选耗材明细) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillHasEbuyProductShelfBaseService {
	
	/**
	 * 根据条件查询(用户自选耗材明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户自选耗材明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户自选耗材明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户自选耗材明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillHasEbuyProductShelf> getDredgeBillHasEbuyProductShelfByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户自选耗材明细)信息
	 * @param id
	 * @return
	 */
	public DredgeBillHasEbuyProductShelf getDredgeBillHasEbuyProductShelfBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasEbuyProductShelfCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillHasEbuyProductShelfCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户自选耗材明细)新增一条记录
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	public int insertDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf);
	/**
	 * 批量新增(用户自选耗材明细)
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
	 * 根据序列号批量删除(用户自选耗材明细)信息_逻辑删除
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
