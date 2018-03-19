package com.cnfantasia.server.domainbase.ebuyProductShelf.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductShelf.dao.IEbuyProductShelfBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

/**
 * 描述:(货架商品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductShelfBaseService implements IEbuyProductShelfBaseService{
	
	private IEbuyProductShelfBaseDao ebuyProductShelfBaseDao;
	public void setEbuyProductShelfBaseDao(IEbuyProductShelfBaseDao ebuyProductShelfBaseDao) {
		this.ebuyProductShelfBaseDao = ebuyProductShelfBaseDao;
	}
	/**
	 * 根据条件查询(货架商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> getEbuyProductShelfByCondition(Map<String,Object> paramMap){
		return ebuyProductShelfBaseDao.selectEbuyProductShelfByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(货架商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> getEbuyProductShelfByConditionDim(Map<String,Object> paramMap){
		return ebuyProductShelfBaseDao.selectEbuyProductShelfByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(货架商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> getEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductShelfBaseDao.selectEbuyProductShelfByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(货架商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> getEbuyProductShelfByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductShelfBaseDao.selectEbuyProductShelfByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(货架商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductShelf getEbuyProductShelfBySeqId(java.math.BigInteger id){
		return ebuyProductShelfBaseDao.selectEbuyProductShelfBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductShelfCount(Map<String,Object> paramMap){
		return ebuyProductShelfBaseDao.selectEbuyProductShelfCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductShelfCountDim(Map<String,Object> paramMap){
		return ebuyProductShelfBaseDao.selectEbuyProductShelfCount(paramMap,true);
	}
	/**
	 * 往(货架商品表)新增一条记录
	 * @param ebuyProductShelf
	 * @return
	 */
	@Override
	public int insertEbuyProductShelf(EbuyProductShelf ebuyProductShelf){
		return ebuyProductShelfBaseDao.insertEbuyProductShelf(ebuyProductShelf);
	}
	/**
	 * 批量新增(货架商品表)
	 * @param ebuyProductShelfList
	 * @return
	 */
	@Override
	public int insertEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList){
		return ebuyProductShelfBaseDao.insertEbuyProductShelfBatch(ebuyProductShelfList);
	}
	/**
	 * 更新(货架商品表)信息
	 * @param ebuyProductShelf
	 * @return
	 */
	@Override
	public int updateEbuyProductShelf(EbuyProductShelf ebuyProductShelf){
		return ebuyProductShelfBaseDao.updateEbuyProductShelf(ebuyProductShelf);
	}
	/**
	 * 批量更新(货架商品表)信息
	 * @param ebuyProductShelfList
	 * @return
	 */
	@Override
	public int updateEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList){
		return ebuyProductShelfBaseDao.updateEbuyProductShelfBatch(ebuyProductShelfList);
	}
	/**
	 * 根据序列号删除(货架商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductShelfLogic(java.math.BigInteger id){
		return ebuyProductShelfBaseDao.deleteEbuyProductShelfLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(货架商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductShelfBaseDao.deleteEbuyProductShelfLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(货架商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductShelf(java.math.BigInteger id){
//		return ebuyProductShelfBaseDao.deleteEbuyProductShelf(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(货架商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductShelfBatch(List<java.math.BigInteger> idList){
//		return ebuyProductShelfBaseDao.deleteEbuyProductShelfBatch(idList);
//	}
	
}
