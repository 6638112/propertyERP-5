package com.cnfantasia.server.domainbase.ebuyProductSpec.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductSpec.dao.IEbuyProductSpecBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * 描述:(商品价格规格表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductSpecBaseService implements IEbuyProductSpecBaseService{
	
	private IEbuyProductSpecBaseDao ebuyProductSpecBaseDao;
	public void setEbuyProductSpecBaseDao(IEbuyProductSpecBaseDao ebuyProductSpecBaseDao) {
		this.ebuyProductSpecBaseDao = ebuyProductSpecBaseDao;
	}
	/**
	 * 根据条件查询(商品价格规格表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductSpec> getEbuyProductSpecByCondition(Map<String,Object> paramMap){
		return ebuyProductSpecBaseDao.selectEbuyProductSpecByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品价格规格表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductSpec> getEbuyProductSpecByConditionDim(Map<String,Object> paramMap){
		return ebuyProductSpecBaseDao.selectEbuyProductSpecByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品价格规格表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductSpec> getEbuyProductSpecByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductSpecBaseDao.selectEbuyProductSpecByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品价格规格表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductSpec> getEbuyProductSpecByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductSpecBaseDao.selectEbuyProductSpecByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品价格规格表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductSpec getEbuyProductSpecBySeqId(java.math.BigInteger id){
		return ebuyProductSpecBaseDao.selectEbuyProductSpecBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品价格规格表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductSpecCount(Map<String,Object> paramMap){
		return ebuyProductSpecBaseDao.selectEbuyProductSpecCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品价格规格表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductSpecCountDim(Map<String,Object> paramMap){
		return ebuyProductSpecBaseDao.selectEbuyProductSpecCount(paramMap,true);
	}
	/**
	 * 往(商品价格规格表)新增一条记录
	 * @param ebuyProductSpec
	 * @return
	 */
	@Override
	public int insertEbuyProductSpec(EbuyProductSpec ebuyProductSpec){
		return ebuyProductSpecBaseDao.insertEbuyProductSpec(ebuyProductSpec);
	}
	/**
	 * 批量新增(商品价格规格表)
	 * @param ebuyProductSpecList
	 * @return
	 */
	@Override
	public int insertEbuyProductSpecBatch(List<EbuyProductSpec> ebuyProductSpecList){
		return ebuyProductSpecBaseDao.insertEbuyProductSpecBatch(ebuyProductSpecList);
	}
	/**
	 * 更新(商品价格规格表)信息
	 * @param ebuyProductSpec
	 * @return
	 */
	@Override
	public int updateEbuyProductSpec(EbuyProductSpec ebuyProductSpec){
		return ebuyProductSpecBaseDao.updateEbuyProductSpec(ebuyProductSpec);
	}
	/**
	 * 批量更新(商品价格规格表)信息
	 * @param ebuyProductSpecList
	 * @return
	 */
	@Override
	public int updateEbuyProductSpecBatch(List<EbuyProductSpec> ebuyProductSpecList){
		return ebuyProductSpecBaseDao.updateEbuyProductSpecBatch(ebuyProductSpecList);
	}
	/**
	 * 根据序列号删除(商品价格规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductSpecLogic(java.math.BigInteger id){
		return ebuyProductSpecBaseDao.deleteEbuyProductSpecLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品价格规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductSpecLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductSpecBaseDao.deleteEbuyProductSpecLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品价格规格表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSpec(java.math.BigInteger id){
//		return ebuyProductSpecBaseDao.deleteEbuyProductSpec(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品价格规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductSpecBatch(List<java.math.BigInteger> idList){
//		return ebuyProductSpecBaseDao.deleteEbuyProductSpecBatch(idList);
//	}
	
}
