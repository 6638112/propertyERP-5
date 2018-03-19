package com.cnfantasia.server.domainbase.ebuyProductTemp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductTemp.dao.IEbuyProductTempBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;

/**
 * 描述:(商品表，临时存储从外部抓取的数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductTempBaseService implements IEbuyProductTempBaseService{
	
	private IEbuyProductTempBaseDao ebuyProductTempBaseDao;
	public void setEbuyProductTempBaseDao(IEbuyProductTempBaseDao ebuyProductTempBaseDao) {
		this.ebuyProductTempBaseDao = ebuyProductTempBaseDao;
	}
	/**
	 * 根据条件查询(商品表，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> getEbuyProductTempByCondition(Map<String,Object> paramMap){
		return ebuyProductTempBaseDao.selectEbuyProductTempByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品表，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> getEbuyProductTempByConditionDim(Map<String,Object> paramMap){
		return ebuyProductTempBaseDao.selectEbuyProductTempByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品表，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> getEbuyProductTempByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductTempBaseDao.selectEbuyProductTempByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品表，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> getEbuyProductTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductTempBaseDao.selectEbuyProductTempByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品表，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductTemp getEbuyProductTempBySeqId(java.math.BigInteger id){
		return ebuyProductTempBaseDao.selectEbuyProductTempBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品表，临时存储从外部抓取的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductTempCount(Map<String,Object> paramMap){
		return ebuyProductTempBaseDao.selectEbuyProductTempCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品表，临时存储从外部抓取的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductTempCountDim(Map<String,Object> paramMap){
		return ebuyProductTempBaseDao.selectEbuyProductTempCount(paramMap,true);
	}
	/**
	 * 往(商品表，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductTemp
	 * @return
	 */
	@Override
	public int insertEbuyProductTemp(EbuyProductTemp ebuyProductTemp){
		return ebuyProductTempBaseDao.insertEbuyProductTemp(ebuyProductTemp);
	}
	/**
	 * 批量新增(商品表，临时存储从外部抓取的数据)
	 * @param ebuyProductTempList
	 * @return
	 */
	@Override
	public int insertEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList){
		return ebuyProductTempBaseDao.insertEbuyProductTempBatch(ebuyProductTempList);
	}
	/**
	 * 更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTemp
	 * @return
	 */
	@Override
	public int updateEbuyProductTemp(EbuyProductTemp ebuyProductTemp){
		return ebuyProductTempBaseDao.updateEbuyProductTemp(ebuyProductTemp);
	}
	/**
	 * 批量更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTempList
	 * @return
	 */
	@Override
	public int updateEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList){
		return ebuyProductTempBaseDao.updateEbuyProductTempBatch(ebuyProductTempList);
	}
	/**
	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTempLogic(java.math.BigInteger id){
		return ebuyProductTempBaseDao.deleteEbuyProductTempLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTempLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductTempBaseDao.deleteEbuyProductTempLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTemp(java.math.BigInteger id){
//		return ebuyProductTempBaseDao.deleteEbuyProductTemp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTempBatch(List<java.math.BigInteger> idList){
//		return ebuyProductTempBaseDao.deleteEbuyProductTempBatch(idList);
//	}
	
}
