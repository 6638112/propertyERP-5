package com.cnfantasia.server.domainbase.ebuyHomeType.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeType.entity.EbuyHomeType;

/**
 * 描述:(首页分类) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyHomeTypeBaseDao {
	/**
	 * 根据条件查询(首页分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHomeType> selectEbuyHomeTypeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(首页分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHomeType> selectEbuyHomeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(首页分类)信息
	 * @param id
	 * @return
	 */
	public EbuyHomeType selectEbuyHomeTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(首页分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyHomeTypeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(首页分类)新增一条记录
	 * @param ebuyHomeType
	 * @return
	 */
	public int insertEbuyHomeType(EbuyHomeType ebuyHomeType);
	
	/**
	 * 批量新增(首页分类)信息
	 * @param ebuyHomeTypeList
	 * @return
	 */
	public int insertEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList);
	
	/**
	 * 更新(首页分类)信息
	 * @param ebuyHomeType
	 * @return
	 */
	public int updateEbuyHomeType(EbuyHomeType ebuyHomeType);
	
	/**
	 * 批量更新(首页分类)信息
	 * @param ebuyHomeTypeList
	 * @return
	 */
	public int updateEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList);
	
	/**
	 * 根据序列号删除(首页分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyHomeTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(首页分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyHomeTypeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(首页分类)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyHomeType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(首页分类)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyHomeTypeBatch(List<java.math.BigInteger> idList);
	
	
}
