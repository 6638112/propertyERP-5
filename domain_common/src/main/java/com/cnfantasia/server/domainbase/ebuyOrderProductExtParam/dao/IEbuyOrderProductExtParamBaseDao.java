package com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.entity.EbuyOrderProductExtParam;

/**
 * 描述:(订单商品关系附件参数信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderProductExtParamBaseDao {
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderProductExtParam> selectEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyOrderProductExtParam> selectEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(订单商品关系附件参数信息)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderProductExtParam selectEbuyOrderProductExtParamBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyOrderProductExtParamCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(订单商品关系附件参数信息)新增一条记录
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	public int insertEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam);
	
	/**
	 * 批量新增(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	public int insertEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList);
	
	/**
	 * 更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	public int updateEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam);
	
	/**
	 * 批量更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	public int updateEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList);
	
	/**
	 * 根据序列号删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyOrderProductExtParamLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyOrderProductExtParamLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(订单商品关系附件参数信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyOrderProductExtParam(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系附件参数信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyOrderProductExtParamBatch(List<java.math.BigInteger> idList);
	
	
}
