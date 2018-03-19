package com.cnfantasia.server.domainbase.htCarGb.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 描述:(华庭车禁小区表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IHtCarGbBaseDao {
	/**
	 * 根据条件查询(华庭车禁小区表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<HtCarGb> selectHtCarGbByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(华庭车禁小区表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<HtCarGb> selectHtCarGbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(华庭车禁小区表)信息
	 * @param id
	 * @return
	 */
	public HtCarGb selectHtCarGbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(华庭车禁小区表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectHtCarGbCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(华庭车禁小区表)新增一条记录
	 * @param htCarGb
	 * @return
	 */
	public int insertHtCarGb(HtCarGb htCarGb);
	
	/**
	 * 批量新增(华庭车禁小区表)信息
	 * @param htCarGbList
	 * @return
	 */
	public int insertHtCarGbBatch(List<HtCarGb> htCarGbList);
	
	/**
	 * 更新(华庭车禁小区表)信息
	 * @param htCarGb
	 * @return
	 */
	public int updateHtCarGb(HtCarGb htCarGb);
	
	/**
	 * 批量更新(华庭车禁小区表)信息
	 * @param htCarGbList
	 * @return
	 */
	public int updateHtCarGbBatch(List<HtCarGb> htCarGbList);
	
	/**
	 * 根据序列号删除(华庭车禁小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteHtCarGbLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(华庭车禁小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteHtCarGbLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(华庭车禁小区表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteHtCarGb(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(华庭车禁小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteHtCarGbBatch(List<java.math.BigInteger> idList);
	
	
}
