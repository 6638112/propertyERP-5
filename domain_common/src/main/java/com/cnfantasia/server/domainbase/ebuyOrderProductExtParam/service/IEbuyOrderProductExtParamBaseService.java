package com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.entity.EbuyOrderProductExtParam;

/**
 * 描述:(订单商品关系附件参数信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyOrderProductExtParamBaseService {
	
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(订单商品关系附件参数信息)信息
	 * @param id
	 * @return
	 */
	public EbuyOrderProductExtParam getEbuyOrderProductExtParamBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderProductExtParamCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyOrderProductExtParamCountDim(Map<String,Object> paramMap);
	/**
	 * 往(订单商品关系附件参数信息)新增一条记录
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	public int insertEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam);
	/**
	 * 批量新增(订单商品关系附件参数信息)
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
	 * 根据序列号批量删除(订单商品关系附件参数信息)信息_逻辑删除
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
