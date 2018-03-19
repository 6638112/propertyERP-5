package com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.dao.IEbuyOrderProductExtParamBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.entity.EbuyOrderProductExtParam;

/**
 * 描述:(订单商品关系附件参数信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderProductExtParamBaseService implements IEbuyOrderProductExtParamBaseService{
	
	private IEbuyOrderProductExtParamBaseDao ebuyOrderProductExtParamBaseDao;
	public void setEbuyOrderProductExtParamBaseDao(IEbuyOrderProductExtParamBaseDao ebuyOrderProductExtParamBaseDao) {
		this.ebuyOrderProductExtParamBaseDao = ebuyOrderProductExtParamBaseDao;
	}
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap){
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> getEbuyOrderProductExtParamByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单商品关系附件参数信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderProductExtParam getEbuyOrderProductExtParamBySeqId(java.math.BigInteger id){
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderProductExtParamCount(Map<String,Object> paramMap){
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderProductExtParamCountDim(Map<String,Object> paramMap){
		return ebuyOrderProductExtParamBaseDao.selectEbuyOrderProductExtParamCount(paramMap,true);
	}
	/**
	 * 往(订单商品关系附件参数信息)新增一条记录
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam){
		return ebuyOrderProductExtParamBaseDao.insertEbuyOrderProductExtParam(ebuyOrderProductExtParam);
	}
	/**
	 * 批量新增(订单商品关系附件参数信息)
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList){
		return ebuyOrderProductExtParamBaseDao.insertEbuyOrderProductExtParamBatch(ebuyOrderProductExtParamList);
	}
	/**
	 * 更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam){
		return ebuyOrderProductExtParamBaseDao.updateEbuyOrderProductExtParam(ebuyOrderProductExtParam);
	}
	/**
	 * 批量更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList){
		return ebuyOrderProductExtParamBaseDao.updateEbuyOrderProductExtParamBatch(ebuyOrderProductExtParamList);
	}
	/**
	 * 根据序列号删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductExtParamLogic(java.math.BigInteger id){
		return ebuyOrderProductExtParamBaseDao.deleteEbuyOrderProductExtParamLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductExtParamLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderProductExtParamBaseDao.deleteEbuyOrderProductExtParamLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(订单商品关系附件参数信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductExtParam(java.math.BigInteger id){
//		return ebuyOrderProductExtParamBaseDao.deleteEbuyOrderProductExtParam(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系附件参数信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductExtParamBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderProductExtParamBaseDao.deleteEbuyOrderProductExtParamBatch(idList);
//	}
	
}
